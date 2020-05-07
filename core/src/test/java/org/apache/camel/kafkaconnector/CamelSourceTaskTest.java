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
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CamelSourceTaskTest {

    private static final String TIMER_URI = "timer:kafkaconnector?period=10&fixedRate=true&delay=0";

    @Test
    public void testSourcePolling() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", TIMER_URI);
        props.put("topics", "mytopic");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        Thread.sleep(11L);
        List<SourceRecord> poll = camelSourceTask.poll();
        assertEquals(2, poll.size());
        assertEquals("mytopic", poll.get(0).topic());
        Headers headers = poll.get(0).headers();
        boolean containsHeader = false;
        for (Iterator iterator = headers.iterator(); iterator.hasNext();) {
            Header header = (Header)iterator.next();
            if (header.key().equalsIgnoreCase("CamelPropertyCamelTimerPeriod")) {
                containsHeader = true;
                break;
            }
        }

        camelSourceTask.stop();
        assertTrue(containsHeader);
    }

    @Test
    public void testSourcePollingWithKey() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "direct:start");
        props.put("topics", "mytopic");
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_MESSAGE_HEADER_KEY_CONF, "CamelSpecialTestKey");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        final ProducerTemplate template = camelSourceTask.getCms().createProducerTemplate();

        // first we test if we have a key in the message with body
        template.sendBodyAndHeader("direct:start", "awesome!", "CamelSpecialTestKey", 1234);

        Thread.sleep(11L);

        List<SourceRecord> poll = camelSourceTask.poll();
        assertEquals(1, poll.size());
        assertEquals(1234, poll.get(0).key());
        assertEquals(Schema.Type.INT32, poll.get(0).keySchema().type());

        // second we test if we have no key under the header
        template.sendBodyAndHeader("direct:start", "awesome!", "WrongHeader", 1234);

        Thread.sleep(11L);

        poll = camelSourceTask.poll();
        assertEquals(1, poll.size());
        assertNull(poll.get(0).key());
        assertNull(poll.get(0).keySchema());

        // third we test if we have the header but with null value
        template.sendBodyAndHeader("direct:start", "awesome!", "CamelSpecialTestKey", null);

        Thread.sleep(10L);

        camelSourceTask.poll();
        assertEquals(1, poll.size());
        assertNull(poll.get(0).key());
        assertNull(poll.get(0).keySchema());

        camelSourceTask.stop();
    }

    @Test
    public void testSourcePollingWithBody() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "direct:start");
        props.put("topics", "mytopic");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        final ProducerTemplate template = camelSourceTask.getCms().createProducerTemplate();

        // send first data
        template.sendBody("direct:start", "testing kafka connect");

        Thread.sleep(11L);

        List<SourceRecord> poll = camelSourceTask.poll();
        assertEquals(1, poll.size());
        assertEquals("testing kafka connect", poll.get(0).value());
        assertEquals(Schema.Type.STRING, poll.get(0).valueSchema().type());
        assertNull(poll.get(0).key());
        assertNull(poll.get(0).keySchema());

        // send second data
        template.sendBody("direct:start", true);

        Thread.sleep(11L);

        poll = camelSourceTask.poll();
        assertEquals(1, poll.size());
        assertTrue((boolean)poll.get(0).value());
        assertEquals(Schema.Type.BOOLEAN, poll.get(0).valueSchema().type());
        assertNull(poll.get(0).key());
        assertNull(poll.get(0).keySchema());

        // second third data
        template.sendBody("direct:start", 1234L);

        Thread.sleep(10L);

        poll = camelSourceTask.poll();
        assertEquals(1, poll.size());
        assertEquals(1234L, poll.get(0).value());
        assertEquals(Schema.Type.INT64, poll.get(0).valueSchema().type());
        assertNull(poll.get(0).key());
        assertNull(poll.get(0).keySchema());

        // third with null data
        template.sendBody("direct:start", null);

        Thread.sleep(10L);
        poll = camelSourceTask.poll();
        assertNull(poll.get(0).key());
        assertNull(poll.get(0).keySchema());
        assertNull(poll.get(0).value());
        assertNull(poll.get(0).valueSchema());

        camelSourceTask.stop();
    }

    @Test
    public void testSourcePollingTimeout() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", TIMER_URI);
        props.put("topics", "mytopic");
        props.put("camel.source.maxPollDuration", "1");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        long sleepTime = 30L;
        Thread.sleep(sleepTime);
        List<SourceRecord> poll;
        int retries = 3;
        do {
            poll = camelSourceTask.poll();
            if (poll == null) {
                retries--;
                if (retries == 0) {
                    fail("Exhausted the maximum retries and no record was returned");
                }
                Thread.sleep(sleepTime);
            }
        } while (poll == null && retries > 0);

        assertTrue(poll.size() >= 1, "Received messages are: " + poll.size() + ", expected between 1 and 2.");
        assertTrue(poll.size() <= 2, "Received messages are: " + poll.size() + ", expected between 1 and 2.");
        camelSourceTask.stop();
    }

    @Test
    public void testSourcePollingMaxRecordNumber() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", TIMER_URI);
        props.put("topics", "mytopic");
        props.put("camel.source.maxBatchPollSize", "1");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        Thread.sleep(11L);
        List<SourceRecord> poll = camelSourceTask.poll();
        camelSourceTask.stop();

        assertEquals(1, poll.size());
    }

    @Test
    public void testSourcePollingConsumerOptions() {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "timer:kafkaconnector");
        props.put("topics", "mytopic");
        props.put("camel.source.pollingConsumerQueueSize", "10");
        props.put("camel.source.pollingConsumerBlockTimeout", "1000");
        props.put("camel.source.pollingConsumerBlockWhenFull", "false");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        assertEquals(2, camelSourceTask.getCms().getEndpoints().size());

        camelSourceTask.getCms().getEndpoints().stream()
                .filter(e -> e.getEndpointUri().startsWith("direct"))
                .forEach(e -> assertEquals("direct://end?pollingConsumerBlockTimeout=1000&pollingConsumerBlockWhenFull=false&pollingConsumerQueueSize=10", e.getEndpointUri()));

        camelSourceTask.stop();
    }

    @Test
    public void testUrlPrecedenceOnComponentProperty() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", TIMER_URI);
        props.put("topics", "mytopic");
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_COMPONENT_CONF, "shouldNotBeUsed");
        props.put(CamelSourceTask.getCamelSourceEndpointConfigPrefix() + "endpointProperty", "shouldNotBeUsed");
        props.put(CamelSourceTask.getCamelSourcePathConfigPrefix() + "pathChunk", "shouldNotBeUsed");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        Thread.sleep(11L);
        List<SourceRecord> poll = camelSourceTask.poll();
        assertEquals(2, poll.size());
        assertEquals("mytopic", poll.get(0).topic());
        Headers headers = poll.get(0).headers();
        boolean containsHeader = false;
        for (Iterator iterator = headers.iterator(); iterator.hasNext();) {
            Header header = (Header)iterator.next();
            if (header.key().equalsIgnoreCase("CamelPropertyCamelTimerPeriod")) {
                containsHeader = true;
                break;
            }
        }
        camelSourceTask.stop();

        assertTrue(containsHeader);
    }

    @Test
    public void testSourcePollingUsingComponentProperty() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("topics", "mytopic");
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_COMPONENT_CONF, "timer");
        props.put(CamelSourceTask.getCamelSourceEndpointConfigPrefix() + "period", "1000");
        props.put(CamelSourceTask.getCamelSourcePathConfigPrefix() + "timerName", "kafkaconnector");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        Thread.sleep(2100L);
        List<SourceRecord> poll = camelSourceTask.poll();
        assertEquals(2, poll.size());
        assertEquals("mytopic", poll.get(0).topic());
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

        assertEquals(1, camelSourceTask.getCms().getEndpoints().stream()
            .filter(e -> e.getEndpointUri().equals("timer://kafkaconnector?period=1000")).count());

        camelSourceTask.stop();
    }

    @Test
    public void testSourcePollingUsingMultipleComponentProperties() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("topics", "mytopic");
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_COMPONENT_CONF, "timer");
        props.put(CamelSourceTask.getCamelSourceEndpointConfigPrefix() + "period", "1000");
        props.put(CamelSourceTask.getCamelSourceEndpointConfigPrefix() + "repeatCount", "0");
        props.put(CamelSourceTask.getCamelSourcePathConfigPrefix() + "timerName", "kafkaconnector");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        Thread.sleep(2100L);
        List<SourceRecord> poll = camelSourceTask.poll();
        assertEquals(2, poll.size());
        assertEquals("mytopic", poll.get(0).topic());
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

        assertEquals(1, camelSourceTask.getCms().getEndpoints().stream()
            .filter(e -> e.getEndpointUri().equals("timer://kafkaconnector?period=1000&repeatCount=0")).count());

        camelSourceTask.stop();
    }
}
