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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.camel.LoggingLevel;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.kafkaconnector.utils.StringJoinerAggregator;
import org.apache.kafka.connect.data.Decimal;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.header.Header;
import org.apache.kafka.connect.source.SourceRecord;
import org.junit.jupiter.api.Test;

import static org.apache.camel.util.CollectionHelper.mapOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CamelSourceTaskTest {

    private static final String DIRECT_URI = "direct:start";
    private static final String TOPIC_NAME = "my-topic";

    private void sendBatchOfRecords(CamelSourceTask sourceTask, long size) {
        final ProducerTemplate template = sourceTask.getCms().getProducerTemplate();
        for (int i = 0; i < size; i++) {
            template.sendBody(DIRECT_URI, "test" + i);
        }
    }

    @Test
    public void testSourcePolling() {
        final long size = 2;
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF, DIRECT_URI);

        CamelSourceTask sourceTask = new CamelSourceTask();
        sourceTask.start(props);

        sendBatchOfRecords(sourceTask, size);
        List<SourceRecord> poll = sourceTask.poll();

        assertEquals(size, poll.size());
        assertEquals(TOPIC_NAME, poll.get(0).topic());
        assertEquals(LoggingLevel.OFF.toString(), sourceTask.getCamelSourceConnectorConfig(props)
            .getString(CamelSourceConnectorConfig.CAMEL_SOURCE_CONTENT_LOG_LEVEL_CONF));

        sourceTask.stop();
    }

    @Test
    public void testSourcePollingMaxBatchPollSize() {
        final long size = 2;
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF, DIRECT_URI);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_MAX_BATCH_POLL_SIZE_CONF, String.valueOf(size));

        CamelSourceTask sourceTask = new CamelSourceTask();
        sourceTask.start(props);

        sendBatchOfRecords(sourceTask, size + 1);
        List<SourceRecord> poll = sourceTask.poll();
        int pollSize = poll.size();

        assertTrue(pollSize >= 0 && pollSize <= size, "Batch size: " + pollSize + ", expected between 0 and " + size);
        sourceTask.stop();
    }

    @Test
    public void testSourcePollingTimeout() {
        final long size = 999;
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF, DIRECT_URI);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_MAX_POLL_DURATION_CONF, "2");

        CamelSourceTask sourceTask = new CamelSourceTask();
        sourceTask.start(props);

        sendBatchOfRecords(sourceTask, size);
        List<SourceRecord>  poll = sourceTask.poll();
        int pollSize = poll.size();

        assertTrue(pollSize < size, "Batch size: " + pollSize + ", expected strictly less than " + size);
        sourceTask.stop();
    }

    @Test
    public void testSourcePollingWithKey() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF, DIRECT_URI);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_MESSAGE_HEADER_KEY_CONF, "CamelSpecialTestKey");

        CamelSourceTask sourceTask = new CamelSourceTask();
        sourceTask.start(props);
        final ProducerTemplate template = sourceTask.getCms().getProducerTemplate();

        // key in the message with body
        template.sendBodyAndHeader(DIRECT_URI, "test", "CamelSpecialTestKey", 1234);

        List<SourceRecord> poll1 = sourceTask.poll();
        assertEquals(1, poll1.size());
        assertEquals(1234, poll1.get(0).key());
        assertEquals(Schema.Type.INT32, poll1.get(0).keySchema().type());

        // no key under the header
        template.sendBodyAndHeader(DIRECT_URI, "test", "WrongHeader", 1234);

        List<SourceRecord> poll2 = sourceTask.poll();
        assertEquals(1, poll2.size());
        assertNull(poll2.get(0).key());
        assertNull(poll2.get(0).keySchema());

        // header with null value
        template.sendBodyAndHeader(DIRECT_URI, "test", "CamelSpecialTestKey", null);

        List<SourceRecord> poll3 = sourceTask.poll();
        assertEquals(1, poll3.size());
        assertNull(poll3.get(0).key());
        assertNull(poll3.get(0).keySchema());

        sourceTask.stop();
    }

    @Test
    public void testSourcePollingWithBody() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF, DIRECT_URI);

        CamelSourceTask sourceTask = new CamelSourceTask();
        sourceTask.start(props);
        final ProducerTemplate template = sourceTask.getCms().getProducerTemplate();

        // send String
        template.sendBody(DIRECT_URI, "test");

        List<SourceRecord> poll1 = sourceTask.poll();
        assertEquals(1, poll1.size());
        assertEquals("test", poll1.get(0).value());
        assertEquals(Schema.Type.STRING, poll1.get(0).valueSchema().type());
        assertNull(poll1.get(0).key());
        assertNull(poll1.get(0).keySchema());

        // send boolean
        template.sendBody(DIRECT_URI, true);

        List<SourceRecord> poll2 = sourceTask.poll();
        assertEquals(1, poll2.size());
        assertTrue((boolean)poll2.get(0).value());
        assertEquals(Schema.Type.BOOLEAN, poll2.get(0).valueSchema().type());
        assertNull(poll2.get(0).key());
        assertNull(poll2.get(0).keySchema());

        // send long
        template.sendBody(DIRECT_URI, 1234L);

        List<SourceRecord> poll3 = sourceTask.poll();
        assertEquals(1, poll3.size());
        assertEquals(1234L, poll3.get(0).value());
        assertEquals(Schema.Type.INT64, poll3.get(0).valueSchema().type());
        assertNull(poll3.get(0).key());
        assertNull(poll3.get(0).keySchema());

        // send null
        template.sendBody(DIRECT_URI, null);

        List<SourceRecord> poll4 = sourceTask.poll();
        assertNull(poll4.get(0).key());
        assertNull(poll4.get(0).keySchema());
        assertNull(poll4.get(0).value());
        assertNull(poll4.get(0).valueSchema());

        sourceTask.stop();
    }

    @Test
    public void testUrlPrecedenceOnComponentProperty() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF, "timer:foo?period=10&repeatCount=2");
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_COMPONENT_CONF, "shouldNotBeUsed");
        props.put(CamelSourceTask.getCamelSourcePathConfigPrefix() + "timerName", "shouldNotBeUsed");
        props.put(CamelSourceTask.getCamelSourceEndpointConfigPrefix() + "repeatCount", "999");

        CamelSourceTask sourceTask = new CamelSourceTask();
        sourceTask.start(props);

        assertEquals(2, sourceTask.getCms().getCamelContext().getEndpoints().size());

        sourceTask.getCms().getCamelContext().getEndpoints().stream()
                .filter(e -> e.getEndpointUri().startsWith("timer"))
                .forEach(e -> {
                    assertTrue(e.getEndpointUri().contains("foo"));
                    assertTrue(e.getEndpointUri().contains("period=10"));
                    assertTrue(e.getEndpointUri().contains("repeatCount=2"));
                });

        sourceTask.stop();
    }

    @Test
    public void testSourcePollingConsumerOptions() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF, "timer:foo?period=10&repeatCount=2");
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_POLLING_CONSUMER_QUEUE_SIZE_CONF, "10");
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_TIMEOUT_CONF, "10");
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_WHEN_FULL_CONF, "false");

        CamelSourceTask sourceTask = new CamelSourceTask();
        sourceTask.start(props);

        assertEquals(2, sourceTask.getCms().getCamelContext().getEndpoints().size());

        sourceTask.getCms().getCamelContext().getEndpoints().stream()
                .filter(e -> e.getEndpointUri().startsWith("direct"))
                .forEach(e -> {
                    assertTrue(e.getEndpointUri().contains("end"));
                    assertTrue(e.getEndpointUri().contains("pollingConsumerQueueSize=10"));
                    assertTrue(e.getEndpointUri().contains("pollingConsumerBlockTimeout=10"));
                    assertTrue(e.getEndpointUri().contains("pollingConsumerBlockWhenFull=false"));
                });

        sourceTask.stop();
    }

    @Test
    public void testSourceUsingComponentProperties() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_COMPONENT_CONF, "timer");
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_MAX_BATCH_POLL_SIZE_CONF, "2");
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_MAX_POLL_DURATION_CONF, "10");
        props.put(CamelSourceTask.getCamelSourcePathConfigPrefix() + "timerName", "foo");
        props.put(CamelSourceTask.getCamelSourceEndpointConfigPrefix() + "period", "10");
        props.put(CamelSourceTask.getCamelSourceEndpointConfigPrefix() + "repeatCount", "2");

        CamelSourceTask sourceTask = new CamelSourceTask();
        sourceTask.start(props);

        sourceTask.getCms().getCamelContext().getEndpoints().stream()
            .filter(e -> e.getEndpointUri().startsWith("timer"))
            .forEach(e -> {
                assertTrue(e.getEndpointUri().contains("foo"));
                assertTrue(e.getEndpointUri().contains("period=10"));
                assertTrue(e.getEndpointUri().contains("repeatCount=2"));
            });

        sourceTask.stop();
    }

    @Test
    public void testSourceBigDecimalHeader() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_COMPONENT_CONF, "direct");
        props.put(CamelSourceTask.getCamelSourcePathConfigPrefix() + "name", "start");

        CamelSourceTask sourceTask = new CamelSourceTask();
        sourceTask.start(props);


        final ProducerTemplate template = sourceTask.getCms().getProducerTemplate();
        template.sendBodyAndHeader(DIRECT_URI, "test", "bigdecimal", new BigDecimal(1234567890));

        List<SourceRecord> results = sourceTask.poll();
        assertEquals(1, results.size());
        Header bigDecimalHeader = results.get(0).headers().allWithName(CamelSourceTask.HEADER_CAMEL_PREFIX + "bigdecimal").next();
        assertEquals("[B", bigDecimalHeader.value().getClass().getName());
        assertEquals(Decimal.class.getCanonicalName(), bigDecimalHeader.schema().name());
        assertEquals(Schema.Type.BYTES, bigDecimalHeader.schema().type());

        sourceTask.stop();
    }

    @Test
    public void testSourceByteArrayHeader() {
        CamelSourceTask sourceTask = new CamelSourceTask();
        sourceTask.start(mapOf(
            CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME,
            CamelSourceConnectorConfig.CAMEL_SOURCE_COMPONENT_CONF, "direct",
            CamelSourceTask.getCamelSourcePathConfigPrefix() + "name", "start"
        ));

        sourceTask.getCms().getProducerTemplate().sendBodyAndHeader(DIRECT_URI, "test", "byteArray", new Byte[] {
            1, 2
        });

        try {
            List<SourceRecord> results = sourceTask.poll();
            assertThat(results).hasSize(1);

            Header header = results.get(0).headers().allWithName(CamelSourceTask.HEADER_CAMEL_PREFIX + "byteArray").next();

            assertThat(header.schema().type()).isEqualTo(Schema.Type.BYTES);
            assertThat(header.value()).isInstanceOfSatisfying(byte[].class, b -> {
                assertThat(b).contains(1, 2);
            });
        } finally {
            sourceTask.stop();
        }
    }

    @Test
    public void testSourcePollingWithAggregationBySize() {
        final int size = 10;
        final int chunkSize = 5;

        CamelSourceTask sourceTask = new CamelSourceTask();
        sourceTask.start(mapOf(
            CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME,
            CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF, DIRECT_URI,
            CamelSourceConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_CONF, "#class:org.apache.camel.kafkaconnector.utils.StringJoinerAggregator",
            CamelSourceConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_CONF + ".delimiter", "|",
            CamelSourceConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_SIZE_CONF, chunkSize
        ));

        try {
            assertThat(sourceTask.getCms().getCamelContext().getRegistry().lookupByName(CamelSourceConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_NAME))
                .isInstanceOf(StringJoinerAggregator.class)
                .hasFieldOrPropertyWithValue("delimiter", "|");

            for (int i = 0; i < size; i++) {
                sourceTask.getCms().getProducerTemplate().sendBody(DIRECT_URI,  Integer.toString(i));
            }

            List<SourceRecord> records = sourceTask.poll();

            assertThat(records).hasSize(size / chunkSize);

            for (int i = 0; i < size / chunkSize; i++) {
                assertThat(records)
                    .element(i)
                    .hasFieldOrPropertyWithValue(
                        "value",
                        IntStream.range(i * chunkSize, (i * chunkSize) + chunkSize).mapToObj(Integer::toString).collect(Collectors.joining("|"))
                    );
            }

        } finally {
            sourceTask.stop();
        }
    }

    @Test
    public void testSourcePollingWithAggregationBySizeAndTimeout() {
        final int size = 3;
        final int chunkSize = 2;
        final long chunkTimeout = 500L;

        CamelSourceTask sourceTask = new CamelSourceTask();
        sourceTask.start(mapOf(
            CamelSourceConnectorConfig.TOPIC_CONF, TOPIC_NAME,
            CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF, DIRECT_URI,
            CamelSourceConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_CONF, "#class:org.apache.camel.kafkaconnector.utils.StringJoinerAggregator",
            CamelSourceConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_CONF + ".delimiter", "|",
            CamelSourceConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_TIMEOUT_CONF, chunkTimeout,
            CamelSourceConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_SIZE_CONF, chunkSize
        ));

        try {
            assertThat(sourceTask.getCms().getCamelContext().getRegistry().lookupByName(CamelSourceConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_NAME))
                .isInstanceOf(StringJoinerAggregator.class)
                .hasFieldOrPropertyWithValue("delimiter", "|");

            for (int i = 0; i < size; i++) {
                sourceTask.getCms().getProducerTemplate().sendBody(DIRECT_URI, Integer.toString(i));
            }

            List<SourceRecord> records = new ArrayList<>();
            while (records.size() < 2) {
                records.addAll(sourceTask.poll());
            }

            assertThat(records).hasSize(2);
            assertThat(records).element(0).hasFieldOrPropertyWithValue("value", "0|1");
            assertThat(records).element(1).hasFieldOrPropertyWithValue("value", "2");
        } finally {
            sourceTask.stop();
        }
    }
}
