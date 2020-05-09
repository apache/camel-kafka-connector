/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.kafkaconnector;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.camel.ProducerTemplate;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.header.Header;
import org.apache.kafka.connect.header.Headers;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CamelSourceTaskTest {

    private static final long PERIOD_MS = 10L;
    private static final String TIMER_URI = "timer:foo?delay=0&fixedRate=true&period=" + PERIOD_MS;
    private static final String TOPIC_NAME = "my-topic";

    private CamelSourceTask getTaskWithFixedNumberOfRecords(Map<String, String> props, int size) throws InterruptedException {
        final long sleepTime = size * PERIOD_MS;
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_MAX_BATCH_POLL_SIZE_CONF, String.valueOf(size));
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_MAX_POLL_DURATION_CONF, String.valueOf(sleepTime));
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_POLLING_CONSUMER_QUEUE_SIZE_CONF, String.valueOf(size));
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_TIMEOUT_CONF, String.valueOf(sleepTime));
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_WHEN_FULL_CONF, "false");
        CamelSourceTask sourceTask = new CamelSourceTask();
        sourceTask.start(props);
        Thread.sleep(sleepTime);
        return sourceTask;
    }

    @Test
    public void testSourcePollingConsumerOptions() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF, "timer:foo");
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_POLLING_CONSUMER_QUEUE_SIZE_CONF, "10");
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_TIMEOUT_CONF, "1000");
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_WHEN_FULL_CONF, "false");

        CamelSourceTask sourceTask = new CamelSourceTask();
        sourceTask.start(props);

        assertEquals(2, sourceTask.getCms().getEndpoints().size());

        sourceTask.getCms().getEndpoints().stream()
                .filter(e -> e.getEndpointUri().startsWith("direct"))
                .forEach(e -> assertEquals("direct://end"
                    + "?pollingConsumerBlockTimeout=1000"
                    + "&pollingConsumerBlockWhenFull=false"
                    + "&pollingConsumerQueueSize=10", e.getEndpointUri()));

        sourceTask.stop();
    }

    @Test
    public void testSourcePollingMaxBatchPollSize() {
        final long max = 2;
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF, TIMER_URI);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_MAX_BATCH_POLL_SIZE_CONF, String.valueOf(max));

        CamelSourceTask sourceTask = new CamelSourceTask();
        sourceTask.start(props);
        List<SourceRecord> poll = sourceTask.poll();
        int size = poll.size();

        assertTrue(size >= 0 && size <= max, "Batch size: " + size + ", expected between 0 and " + max);
        sourceTask.stop();
    }

    @Test
    public void testSourcePollingTimeout() {
        final long max = 2;
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF, TIMER_URI);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_MAX_POLL_DURATION_CONF, String.valueOf(max));

        CamelSourceTask sourceTask = new CamelSourceTask();
        sourceTask.start(props);

        final long start = System.currentTimeMillis();
        sourceTask.poll();
        long duration = System.currentTimeMillis() - start;

        assertTrue(duration >= 0 && duration <= max, "Poll duration: " + duration + ", expected between 0 and " + max);
        sourceTask.stop();
    }

    @Test
    public void testSourcePolling() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF, TIMER_URI);

        SourceTask sourceTask = getTaskWithFixedNumberOfRecords(props, 2);
        List<SourceRecord> poll = sourceTask.poll();

        assertEquals(2, poll.size());
        assertEquals(TOPIC_NAME, poll.get(0).topic());
        Headers headers = poll.get(0).headers();
        boolean containsHeader = false;
        for (Iterator iterator = headers.iterator(); iterator.hasNext();) {
            Header header = (Header)iterator.next();
            if (header.key().equalsIgnoreCase("CamelPropertyCamelTimerPeriod")) {
                containsHeader = true;
                break;
            }
        }
        assertTrue(containsHeader);
        sourceTask.stop();
    }

    @Test
    public void testSourcePollingWithKey() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF, "direct:start");
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_MESSAGE_HEADER_KEY_CONF, "CamelSpecialTestKey");

        // key in the message with body
        CamelSourceTask sourceTask1 = getTaskWithFixedNumberOfRecords(props, 1);
        final ProducerTemplate template1 = sourceTask1.getCms().createProducerTemplate();
        template1.sendBodyAndHeader("direct:start", "awesome!", "CamelSpecialTestKey", 1234);

        List<SourceRecord> poll1 = sourceTask1.poll();
        assertEquals(1, poll1.size());
        assertEquals(1234, poll1.get(0).key());
        assertEquals(Schema.Type.INT32, poll1.get(0).keySchema().type());
        sourceTask1.stop();

        // no key under the header
        CamelSourceTask sourceTask2 = getTaskWithFixedNumberOfRecords(props, 1);
        final ProducerTemplate template2 = sourceTask2.getCms().createProducerTemplate();
        template2.sendBodyAndHeader("direct:start", "awesome!", "WrongHeader", 1234);

        List<SourceRecord> poll2 = sourceTask2.poll();
        assertEquals(1, poll2.size());
        assertNull(poll2.get(0).key());
        assertNull(poll2.get(0).keySchema());
        sourceTask2.stop();

        // header with null value
        CamelSourceTask sourceTask3 = getTaskWithFixedNumberOfRecords(props, 1);
        final ProducerTemplate template3 = sourceTask3.getCms().createProducerTemplate();
        template3.sendBodyAndHeader("direct:start", "awesome!", "CamelSpecialTestKey", null);

        List<SourceRecord> thirdPoll = sourceTask3.poll();
        assertEquals(1, thirdPoll.size());
        assertNull(thirdPoll.get(0).key());
        assertNull(thirdPoll.get(0).keySchema());
        sourceTask3.stop();
    }

    @Test
    public void testSourcePollingWithBody() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF, "direct:start");

        // send String
        CamelSourceTask sourceTask1 = getTaskWithFixedNumberOfRecords(props, 1);
        final ProducerTemplate template1 = sourceTask1.getCms().createProducerTemplate();
        template1.sendBody("direct:start", "testing kafka connect");

        List<SourceRecord> poll1 = sourceTask1.poll();
        assertEquals(1, poll1.size());
        assertEquals("testing kafka connect", poll1.get(0).value());
        assertEquals(Schema.Type.STRING, poll1.get(0).valueSchema().type());
        assertNull(poll1.get(0).key());
        assertNull(poll1.get(0).keySchema());
        sourceTask1.stop();

        // send boolean
        CamelSourceTask sourceTask2 = getTaskWithFixedNumberOfRecords(props, 1);
        final ProducerTemplate template2 = sourceTask2.getCms().createProducerTemplate();
        template2.sendBody("direct:start", true);

        List<SourceRecord> poll2 = sourceTask2.poll();
        assertEquals(1, poll2.size());
        assertTrue((boolean)poll2.get(0).value());
        assertEquals(Schema.Type.BOOLEAN, poll2.get(0).valueSchema().type());
        assertNull(poll2.get(0).key());
        assertNull(poll2.get(0).keySchema());
        sourceTask2.stop();

        // send long
        CamelSourceTask sourceTask3 = getTaskWithFixedNumberOfRecords(props, 1);
        final ProducerTemplate template3 = sourceTask3.getCms().createProducerTemplate();
        template3.sendBody("direct:start", 1234L);

        List<SourceRecord> poll3 = sourceTask3.poll();
        assertEquals(1, poll3.size());
        assertEquals(1234L, poll3.get(0).value());
        assertEquals(Schema.Type.INT64, poll3.get(0).valueSchema().type());
        assertNull(poll3.get(0).key());
        assertNull(poll3.get(0).keySchema());
        sourceTask3.stop();

        // send null
        CamelSourceTask sourceTask4 = getTaskWithFixedNumberOfRecords(props, 1);
        final ProducerTemplate template4 = sourceTask4.getCms().createProducerTemplate();
        template4.sendBody("direct:start", null);

        List<SourceRecord> poll4 = sourceTask4.poll();
        assertNull(poll4.get(0).key());
        assertNull(poll4.get(0).keySchema());
        assertNull(poll4.get(0).value());
        assertNull(poll4.get(0).valueSchema());
        sourceTask4.stop();
    }

    @Test
    public void testUrlPrecedenceOnComponentProperty() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF, TIMER_URI);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_COMPONENT_CONF, "shouldNotBeUsed");
        props.put(CamelSourceTask.getCamelSourceEndpointConfigPrefix() + "endpointProperty", "shouldNotBeUsed");
        props.put(CamelSourceTask.getCamelSourcePathConfigPrefix() + "pathChunk", "shouldNotBeUsed");

        SourceTask sourceTask = getTaskWithFixedNumberOfRecords(props, 2);
        List<SourceRecord> poll = sourceTask.poll();

        assertEquals(2, poll.size());
        assertEquals(TOPIC_NAME, poll.get(0).topic());
        Headers headers = poll.get(0).headers();
        boolean containsHeader = false;
        for (Iterator iterator = headers.iterator(); iterator.hasNext();) {
            Header header = (Header)iterator.next();
            if (header.key().equalsIgnoreCase("CamelPropertyCamelTimerPeriod")) {
                containsHeader = true;
                break;
            }
        }
        assertTrue(containsHeader);
        sourceTask.stop();
    }

    @Test
    public void testSourcePollingUsingComponentProperty() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_COMPONENT_CONF, "timer");
        props.put(CamelSourceTask.getCamelSourcePathConfigPrefix() + "timerName", "foo");
        props.put(CamelSourceTask.getCamelSourceEndpointConfigPrefix() + "delay", "0");

        CamelSourceTask sourceTask = getTaskWithFixedNumberOfRecords(props, 1);
        List<SourceRecord> poll = sourceTask.poll();

        assertEquals(1, poll.size());
        assertEquals(TOPIC_NAME, poll.get(0).topic());
        Headers headers = poll.get(0).headers();
        boolean containsHeader = false;
        for (Iterator iterator = headers.iterator(); iterator.hasNext();) {
            Header header = (Header)iterator.next();
            if (header.key().equalsIgnoreCase("CamelPropertyCamelTimerPeriod")) {
                containsHeader = true;
                break;
            }
        }
        assertTrue(containsHeader);

        assertEquals(1, sourceTask.getCms().getEndpoints().stream()
            .filter(e -> e.getEndpointUri().equals("timer://foo?delay=0")).count());
        sourceTask.stop();
    }

    @Test
    public void testSourcePollingUsingMultipleComponentProperties() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_COMPONENT_CONF, "timer");
        props.put(CamelSourceTask.getCamelSourcePathConfigPrefix() + "timerName", "foo");
        props.put(CamelSourceTask.getCamelSourceEndpointConfigPrefix() + "period", String.valueOf(PERIOD_MS));
        props.put(CamelSourceTask.getCamelSourceEndpointConfigPrefix() + "repeatCount", "2");
        props.put(CamelSourceTask.getCamelSourceEndpointConfigPrefix() + "delay", "0");

        CamelSourceTask sourceTask = getTaskWithFixedNumberOfRecords(props, 2);
        List<SourceRecord> poll = sourceTask.poll();

        assertEquals(2, poll.size());
        assertEquals(TOPIC_NAME, poll.get(0).topic());
        Headers headers = poll.get(0).headers();
        boolean containsHeader = false;
        for (Iterator iterator = headers.iterator(); iterator.hasNext();) {
            Header header = (Header)iterator.next();
            if (header.key().equalsIgnoreCase("CamelPropertyCamelTimerPeriod")) {
                containsHeader = true;
                break;
            }
        }
        assertTrue(containsHeader);

        assertEquals(1, sourceTask.getCms().getEndpoints().stream()
            .filter(e -> e.getEndpointUri().equals("timer://foo?delay=0&period=" + PERIOD_MS + "&repeatCount=2")).count());
        sourceTask.stop();
    }
}
