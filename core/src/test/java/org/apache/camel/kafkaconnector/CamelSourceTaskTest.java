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
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.camel.ProducerTemplate;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.source.SourceRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CamelSourceTaskTest {

    @Test
    public void testSourcePolling() {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "direct:start");
        props.put("topics", "mytopic");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        final ProducerTemplate template = camelSourceTask.getCms().createProducerTemplate();
        template.sendBody("direct:start", "awesome!");

        List<SourceRecord> poll = camelSourceTaskPollWithRetries(camelSourceTask, 5);
        assertEquals(1, poll.size());
        assertEquals("mytopic", poll.get(0).topic());

        camelSourceTask.stop();
    }

    @Test
    public void testSourcePollingWithKey() {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "direct:start");
        props.put("topics", "mytopic");
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_MESSAGE_HEADER_KEY_CONF, "CamelSpecialTestKey");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        final ProducerTemplate template = camelSourceTask.getCms().createProducerTemplate();

        // first we test if we have a key in the message with body
        template.sendBodyAndHeader("direct:start", "awesome!", "CamelSpecialTestKey", 1234);

        List<SourceRecord> poll = camelSourceTaskPollWithRetries(camelSourceTask, 5);
        assertEquals(1, poll.size());
        assertEquals(1234, poll.get(0).key());
        assertEquals(Schema.Type.INT32, poll.get(0).keySchema().type());

        // second we test if we have no key under the header
        template.sendBodyAndHeader("direct:start", "awesome!", "WrongHeader", 1234);

        poll = camelSourceTaskPollWithRetries(camelSourceTask, 5);
        assertEquals(1, poll.size());
        assertNull(poll.get(0).key());
        assertNull(poll.get(0).keySchema());

        // third we test if we have the header but with null value
        template.sendBodyAndHeader("direct:start", "awesome!", "CamelSpecialTestKey", null);

        poll = camelSourceTaskPollWithRetries(camelSourceTask, 5);
        assertEquals(1, poll.size());
        assertNull(poll.get(0).key());
        assertNull(poll.get(0).keySchema());

        camelSourceTask.stop();
    }

    @Test
    public void testSourcePollingWithBody() {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "direct:start");
        props.put("topics", "mytopic");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        final ProducerTemplate template = camelSourceTask.getCms().createProducerTemplate();

        // send first data
        template.sendBody("direct:start", "testing kafka connect");

        List<SourceRecord> poll = camelSourceTaskPollWithRetries(camelSourceTask, 5);
        assertEquals(1, poll.size());
        assertEquals("testing kafka connect", poll.get(0).value());
        assertEquals(Schema.Type.STRING, poll.get(0).valueSchema().type());
        assertNull(poll.get(0).key());
        assertNull(poll.get(0).keySchema());

        // send second data
        template.sendBody("direct:start", true);

        poll = camelSourceTaskPollWithRetries(camelSourceTask, 5);
        assertEquals(1, poll.size());
        assertTrue((boolean)poll.get(0).value());
        assertEquals(Schema.Type.BOOLEAN, poll.get(0).valueSchema().type());
        assertNull(poll.get(0).key());
        assertNull(poll.get(0).keySchema());

        // second third data
        template.sendBody("direct:start", 1234L);

        poll = camelSourceTaskPollWithRetries(camelSourceTask, 5);
        assertEquals(1, poll.size());
        assertEquals(1234L, poll.get(0).value());
        assertEquals(Schema.Type.INT64, poll.get(0).valueSchema().type());
        assertNull(poll.get(0).key());
        assertNull(poll.get(0).keySchema());

        // third with null data
        template.sendBody("direct:start", null);

        poll = camelSourceTaskPollWithRetries(camelSourceTask, 5);
        assertNull(poll.get(0).key());
        assertNull(poll.get(0).keySchema());
        assertNull(poll.get(0).value());
        assertNull(poll.get(0).valueSchema());

        camelSourceTask.stop();
    }

    @Test
    public void testSourcePollingTimeout() {
        final int nuberOfMessagesSent = 999;
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "direct:start");
        props.put("topics", "mytopic");
        props.put("camel.source.maxPollDuration", "1");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        final ProducerTemplate template = camelSourceTask.getCms().createProducerTemplate();

        // first we send nuberOfMessagesSent of messages
        Stream.of(nuberOfMessagesSent).forEach(i -> template.sendBody("direct:start", "awesome!"));

        // then we assert we received only a fraction of them (proving that polling timeout of 1 Millisecond is working)
        List<SourceRecord> poll = camelSourceTask.poll();
        assertTrue(poll.size() < nuberOfMessagesSent, "Expected received messages count to be strictly less than " + nuberOfMessagesSent + ", got " + poll.size());

        camelSourceTask.stop();
    }

    @Test
    public void testSourcePollingMaxRecordNumber() {
        final int nuberOfMessagesSent = 2;
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "direct:start");
        props.put("topics", "mytopic");
        props.put("camel.source.maxBatchPollSize", "1");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        final ProducerTemplate template = camelSourceTask.getCms().createProducerTemplate();

        // first we send nuberOfMessagesSent of messages > camel.source.maxBatchPollSize
        Stream.of(nuberOfMessagesSent).forEach(i -> template.sendBody("direct:start", "awesome!"));

        List<SourceRecord> poll = camelSourceTaskPollWithRetries(camelSourceTask, 5);

        // then we assert we received just camel.source.maxBatchPollSize
        assertEquals(1, poll.size());
        camelSourceTask.stop();
    }

    @Test
    public void testSourceConsumerOptions() {
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
                .forEach(e -> {
                    assertTrue(e.getEndpointUri().contains("end"));
                    assertTrue(e.getEndpointUri().contains("pollingConsumerBlockTimeout=1000"));
                    assertTrue(e.getEndpointUri().contains("pollingConsumerBlockWhenFull=false"));
                    assertTrue(e.getEndpointUri().contains("pollingConsumerQueueSize=10"));
                });

        camelSourceTask.stop();
    }

    @Test
    public void testSourceUrlPrecedenceOnComponentProperty() {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "timer:kafkaconnector?period=10&fixedRate=true&delay=0");
        props.put("topics", "mytopic");
        //these properties should be ignored
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_COMPONENT_CONF, "shouldNotBeUsed");
        props.put(CamelSourceTask.getCamelSourceEndpointConfigPrefix() + "delay", "100000000");
        props.put(CamelSourceTask.getCamelSourcePathConfigPrefix() + "name", "shouldNotBeUsed");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        assertEquals(2, camelSourceTask.getCms().getEndpoints().size());

        camelSourceTask.getCms().getEndpoints().stream()
                .filter(e -> e.getEndpointUri().startsWith("timer"))
                .forEach(e -> {
                    assertTrue(e.getEndpointUri().contains("kafkaconnector"));
                    assertTrue(e.getEndpointUri().contains("period=10"));
                    assertTrue(e.getEndpointUri().contains("fixedRate=true"));
                    assertTrue(e.getEndpointUri().contains("delay=0"));
                });

        camelSourceTask.stop();
    }

    @Test
    public void testSourceUsingComponentProperty() {
        Map<String, String> props = new HashMap<>();
        props.put("topics", "mytopic");
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_COMPONENT_CONF, "timer");
        props.put(CamelSourceTask.getCamelSourceEndpointConfigPrefix() + "period", "10000");
        props.put(CamelSourceTask.getCamelSourceEndpointConfigPrefix() + "delay", "0");
        props.put(CamelSourceTask.getCamelSourcePathConfigPrefix() + "timerName", "kafkaconnector");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        camelSourceTask.getCms().getEndpoints().stream()
            .filter(e -> e.getEndpointUri().startsWith("timer"))
            .forEach(e -> {
                assertTrue(e.getEndpointUri().contains("kafkaconnector"));
                assertTrue(e.getEndpointUri().contains("period=1000"));
                assertTrue(e.getEndpointUri().contains("delay=0"));
            });

        camelSourceTask.stop();
    }

    @Test
    public void testSourceUsingMultipleComponentProperties() {
        Map<String, String> props = new HashMap<>();
        props.put("topics", "mytopic");
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_COMPONENT_CONF, "timer");
        props.put(CamelSourceTask.getCamelSourceEndpointConfigPrefix() + "period", "1000");
        props.put(CamelSourceTask.getCamelSourceEndpointConfigPrefix() + "repeatCount", "0");
        props.put(CamelSourceTask.getCamelSourcePathConfigPrefix() + "timerName", "kafkaconnector");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        camelSourceTask.getCms().getEndpoints().stream()
            .filter(e -> e.getEndpointUri().startsWith("timer"))
            .forEach(e -> {
                assertTrue(e.getEndpointUri().contains("kafkaconnector"));
                assertTrue(e.getEndpointUri().contains("period=1000"));
                assertTrue(e.getEndpointUri().contains("repeatCount=0"));
            });

        camelSourceTask.stop();
    }

    private List<SourceRecord> camelSourceTaskPollWithRetries(CamelSourceTask camelSourceTask, int retries) {
        List<SourceRecord> poll;
        do {
            poll = camelSourceTask.poll();
            if (poll == null) {
                retries--;
            }
        } while (poll == null && retries > 0);
        return poll;
    }
}
