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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.sink.SinkRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CamelSinkTaskTest {

    private static final String SEDA_URI = "seda:test";
    private static final String TOPIC_NAME = "my-topic";
    private static final long RECEIVE_TIMEOUT = 1_000;

    @Test
    public void testOnlyBody() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSinkConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().createConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertEquals(LoggingLevel.OFF.toString(), sinkTask.getCamelSinkConnectorConfig(props)
            .getString(CamelSinkConnectorConfig.CAMEL_SINK_CONTENT_LOG_LEVEL_CONF));

        sinkTask.stop();
    }

    @Test
    public void testBodyAndHeaders() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSinkConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        Byte myByte = new Byte("100");
        Float myFloat = new Float("100");
        Short myShort = new Short("100");
        Double myDouble = new Double("100");
        int myInteger = 100;
        Long myLong = new Long("100");

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        record.headers().addBoolean("CamelHeaderMyBoolean", true);
        record.headers().addByte("CamelHeaderMyByte", myByte);
        record.headers().addFloat("CamelHeaderMyFloat", myFloat);
        record.headers().addShort("CamelHeaderMyShort", myShort);
        record.headers().addDouble("CamelHeaderMyDouble", myDouble);
        record.headers().addInt("CamelHeaderMyInteger", myInteger);
        record.headers().addLong("CamelHeaderMyLong", myLong);
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().createConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertTrue(exchange.getIn().getHeader("MyBoolean", Boolean.class));
        assertEquals(myByte, exchange.getIn().getHeader("MyByte", Byte.class));
        assertEquals(myFloat, exchange.getIn().getHeader("MyFloat", Float.class));
        assertEquals(myShort, exchange.getIn().getHeader("MyShort", Short.class));
        assertEquals(myDouble, exchange.getIn().getHeader("MyDouble", Double.class));
        assertEquals(myInteger, exchange.getIn().getHeader("MyInteger"));
        assertEquals(myLong, exchange.getIn().getHeader("MyLong", Long.class));

        sinkTask.stop();
    }

    @Test
    public void testBodyAndProperties() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSinkConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        Byte myByte = new Byte("100");
        Float myFloat = new Float("100");
        Short myShort = new Short("100");
        Double myDouble = new Double("100");
        int myInteger = 100;
        Long myLong = new Long("100");

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        record.headers().addBoolean("CamelPropertyMyBoolean", true);
        record.headers().addByte("CamelPropertyMyByte", myByte);
        record.headers().addFloat("CamelPropertyMyFloat", myFloat);
        record.headers().addShort("CamelPropertyMyShort", myShort);
        record.headers().addDouble("CamelPropertyMyDouble", myDouble);
        record.headers().addInt("CamelPropertyMyInteger", myInteger);
        record.headers().addLong("CamelPropertyMyLong", myLong);
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().createConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertTrue((boolean) exchange.getProperties().get("MyBoolean"));
        assertEquals(myByte, (Byte) exchange.getProperties().get("MyByte"));
        assertEquals(myFloat, (Float) exchange.getProperties().get("MyFloat"));
        assertEquals(myShort, (Short) exchange.getProperties().get("MyShort"));
        assertEquals(myDouble, (Double) exchange.getProperties().get("MyDouble"));
        assertEquals(myInteger, exchange.getProperties().get("MyInteger"));
        assertEquals(myLong, (Long) exchange.getProperties().get("MyLong"));

        sinkTask.stop();
    }

    @Test
    public void testBodyAndPropertiesHeadersMixed() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSinkConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        Byte myByte = new Byte("100");
        Float myFloat = new Float("100");
        Short myShort = new Short("100");
        Double myDouble = new Double("100");
        int myInteger = 100;
        Long myLong = new Long("100");

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        record.headers().addBoolean("CamelPropertyMyBoolean", true);
        record.headers().addByte("CamelPropertyMyByte", myByte);
        record.headers().addFloat("CamelPropertyMyFloat", myFloat);
        record.headers().addShort("CamelPropertyMyShort", myShort);
        record.headers().addDouble("CamelPropertyMyDouble", myDouble);
        record.headers().addInt("CamelPropertyMyInteger", myInteger);
        record.headers().addLong("CamelPropertyMyLong", myLong);
        record.headers().addBoolean("CamelHeaderMyBoolean", true);
        record.headers().addByte("CamelHeaderMyByte", myByte);
        record.headers().addFloat("CamelHeaderMyFloat", myFloat);
        record.headers().addShort("CamelHeaderMyShort", myShort);
        record.headers().addDouble("CamelHeaderMyDouble", myDouble);
        record.headers().addInt("CamelHeaderMyInteger", myInteger);
        record.headers().addLong("CamelHeaderMyLong", myLong);
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().createConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertTrue((boolean) exchange.getProperties().get("MyBoolean"));
        assertEquals(myByte, (Byte) exchange.getProperties().get("MyByte"));
        assertEquals(myFloat, (Float) exchange.getProperties().get("MyFloat"));
        assertEquals(myShort, (Short) exchange.getProperties().get("MyShort"));
        assertEquals(myDouble, (Double) exchange.getProperties().get("MyDouble"));
        assertEquals(myInteger, exchange.getProperties().get("MyInteger"));
        assertEquals(myLong, (Long) exchange.getProperties().get("MyLong"));
        assertTrue(exchange.getIn().getHeader("MyBoolean", Boolean.class));
        assertEquals(myByte, exchange.getIn().getHeader("MyByte", Byte.class));
        assertEquals(myFloat, exchange.getIn().getHeader("MyFloat", Float.class));
        assertEquals(myShort, exchange.getIn().getHeader("MyShort", Short.class));
        assertEquals(myDouble, exchange.getIn().getHeader("MyDouble", Double.class));
        assertEquals(myInteger, exchange.getIn().getHeader("MyInteger"));
        assertEquals(myLong, exchange.getIn().getHeader("MyLong", Long.class));

        sinkTask.stop();
    }

    @Test
    public void testBodyAndHeadersMap() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSinkConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        Byte myByte = new Byte("100");
        Float myFloat = new Float("100");
        Short myShort = new Short("100");
        Double myDouble = new Double("100");
        int myInteger = 100;
        Long myLong = new Long("100");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("a", "a");
        HashMap<Integer, String> map1 = new HashMap<Integer, String>();
        map1.put(1, "a");
        HashMap<Integer, Integer> map2 = new HashMap<Integer, Integer>();
        map2.put(1, 1);

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        record.headers().addBoolean("CamelHeaderMyBoolean", true);
        record.headers().addByte("CamelHeaderMyByte", myByte);
        record.headers().addFloat("CamelHeaderMyFloat", myFloat);
        record.headers().addShort("CamelHeaderMyShort", myShort);
        record.headers().addDouble("CamelHeaderMyDouble", myDouble);
        record.headers().addInt("CamelHeaderMyInteger", myInteger);
        record.headers().addLong("CamelHeaderMyLong", myLong);
        record.headers().addMap("CamelHeaderMyMap", map, SchemaBuilder.map(Schema.STRING_SCHEMA, Schema.STRING_SCHEMA));
        record.headers().addMap("CamelHeaderMyMap1", map1, SchemaBuilder.map(Schema.INT64_SCHEMA, Schema.STRING_SCHEMA));
        record.headers().addMap("CamelHeaderMyMap2", map2, SchemaBuilder.map(Schema.INT64_SCHEMA, Schema.INT64_SCHEMA));
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().createConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertTrue(exchange.getIn().getHeader("MyBoolean", Boolean.class));
        assertEquals(myByte, exchange.getIn().getHeader("MyByte", Byte.class));
        assertEquals(myFloat, exchange.getIn().getHeader("MyFloat", Float.class));
        assertEquals(myShort, exchange.getIn().getHeader("MyShort", Short.class));
        assertEquals(myDouble, exchange.getIn().getHeader("MyDouble", Double.class));
        assertEquals(myInteger, exchange.getIn().getHeader("MyInteger"));
        assertEquals(myLong, exchange.getIn().getHeader("MyLong", Long.class));
        assertEquals(map, exchange.getIn().getHeader("MyMap", Map.class));
        assertEquals(map1, exchange.getIn().getHeader("MyMap1", Map.class));
        assertEquals(map2, exchange.getIn().getHeader("MyMap2", Map.class));
        sinkTask.stop();
    }

    @Test
    public void testBodyAndPropertiesHeadersMapMixed() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSinkConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        Byte myByte = new Byte("100");
        Float myFloat = new Float("100");
        Short myShort = new Short("100");
        Double myDouble = new Double("100");
        int myInteger = 100;
        Long myLong = new Long("100");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("a", "a");
        HashMap<Integer, String> map1 = new HashMap<Integer, String>();
        map1.put(1, "a");
        HashMap<Integer, Integer> map2 = new HashMap<Integer, Integer>();
        map2.put(1, 1);

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        record.headers().addBoolean("CamelPropertyMyBoolean", true);
        record.headers().addByte("CamelPropertyMyByte", myByte);
        record.headers().addFloat("CamelPropertyMyFloat", myFloat);
        record.headers().addShort("CamelPropertyMyShort", myShort);
        record.headers().addDouble("CamelPropertyMyDouble", myDouble);
        record.headers().addInt("CamelPropertyMyInteger", myInteger);
        record.headers().addLong("CamelPropertyMyLong", myLong);
        record.headers().addMap("CamelPropertyMyMap", map, SchemaBuilder.map(Schema.STRING_SCHEMA, Schema.STRING_SCHEMA));
        record.headers().addMap("CamelPropertyMyMap1", map1, SchemaBuilder.map(Schema.INT64_SCHEMA, Schema.STRING_SCHEMA));
        record.headers().addMap("CamelPropertyMyMap2", map2, SchemaBuilder.map(Schema.INT64_SCHEMA, Schema.INT64_SCHEMA));
        record.headers().addBoolean("CamelHeaderMyBoolean", true);
        record.headers().addByte("CamelHeaderMyByte", myByte);
        record.headers().addFloat("CamelHeaderMyFloat", myFloat);
        record.headers().addShort("CamelHeaderMyShort", myShort);
        record.headers().addDouble("CamelHeaderMyDouble", myDouble);
        record.headers().addInt("CamelHeaderMyInteger", myInteger);
        record.headers().addLong("CamelHeaderMyLong", myLong);
        record.headers().addMap("CamelHeaderMyMap", map, SchemaBuilder.map(Schema.STRING_SCHEMA, Schema.STRING_SCHEMA));
        record.headers().addMap("CamelHeaderMyMap1", map1, SchemaBuilder.map(Schema.INT64_SCHEMA, Schema.STRING_SCHEMA));
        record.headers().addMap("CamelHeaderMyMap2", map2, SchemaBuilder.map(Schema.INT64_SCHEMA, Schema.INT64_SCHEMA));
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().createConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertTrue((boolean) exchange.getProperties().get("MyBoolean"));
        assertEquals(myByte, (Byte) exchange.getProperties().get("MyByte"));
        assertEquals(myFloat, (Float) exchange.getProperties().get("MyFloat"));
        assertEquals(myShort, (Short) exchange.getProperties().get("MyShort"));
        assertEquals(myDouble, (Double) exchange.getProperties().get("MyDouble"));
        assertEquals(myInteger, exchange.getProperties().get("MyInteger"));
        assertEquals(myLong, (Long) exchange.getProperties().get("MyLong"));
        assertEquals(map, exchange.getProperties().get("MyMap"));
        assertEquals(map1, exchange.getProperties().get("MyMap1"));
        assertEquals(map2, exchange.getProperties().get("MyMap2"));
        assertTrue(exchange.getIn().getHeader("MyBoolean", Boolean.class));
        assertEquals(myByte, exchange.getIn().getHeader("MyByte", Byte.class));
        assertEquals(myFloat, exchange.getIn().getHeader("MyFloat", Float.class));
        assertEquals(myShort, exchange.getIn().getHeader("MyShort", Short.class));
        assertEquals(myDouble, exchange.getIn().getHeader("MyDouble", Double.class));
        assertEquals(myInteger, exchange.getIn().getHeader("MyInteger"));
        assertEquals(myLong, exchange.getIn().getHeader("MyLong", Long.class));
        assertEquals(map, exchange.getIn().getHeader("MyMap", Map.class));
        assertEquals(map1, exchange.getIn().getHeader("MyMap1", Map.class));
        assertEquals(map2, exchange.getIn().getHeader("MyMap2", Map.class));

        sinkTask.stop();
    }

    @Test
    public void testBodyAndHeadersList() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSinkConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        Byte myByte = new Byte("100");
        Float myFloat = new Float("100");
        Short myShort = new Short("100");
        Double myDouble = new Double("100");
        int myInteger = 100;
        Long myLong = new Long("100");
        List<String> list = new ArrayList<String>();
        list.add("a");
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        record.headers().addBoolean("CamelHeaderMyBoolean", true);
        record.headers().addByte("CamelHeaderMyByte", myByte);
        record.headers().addFloat("CamelHeaderMyFloat", myFloat);
        record.headers().addShort("CamelHeaderMyShort", myShort);
        record.headers().addDouble("CamelHeaderMyDouble", myDouble);
        record.headers().addInt("CamelHeaderMyInteger", myInteger);
        record.headers().addLong("CamelHeaderMyLong", myLong);
        record.headers().addList("CamelHeaderMyList", list, SchemaBuilder.array(Schema.STRING_SCHEMA));
        record.headers().addList("CamelHeaderMyList1", list1, SchemaBuilder.array(Schema.INT64_SCHEMA));
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().createConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertTrue(exchange.getIn().getHeader("MyBoolean", Boolean.class));
        assertEquals(myByte, exchange.getIn().getHeader("MyByte", Byte.class));
        assertEquals(myFloat, exchange.getIn().getHeader("MyFloat", Float.class));
        assertEquals(myShort, exchange.getIn().getHeader("MyShort", Short.class));
        assertEquals(myDouble, exchange.getIn().getHeader("MyDouble", Double.class));
        assertEquals(myInteger, exchange.getIn().getHeader("MyInteger"));
        assertEquals(myLong, exchange.getIn().getHeader("MyLong", Long.class));
        assertEquals(list, exchange.getIn().getHeader("MyList", List.class));
        assertEquals(list1, exchange.getIn().getHeader("MyList1", List.class));
        sinkTask.stop();
    }

    @Test
    public void testBodyAndPropertiesHeadersListMixed() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSinkConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        Byte myByte = new Byte("100");
        Float myFloat = new Float("100");
        Short myShort = new Short("100");
        Double myDouble = new Double("100");
        int myInteger = 100;
        Long myLong = new Long("100");
        List<String> list = new ArrayList<String>();
        list.add("a");
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        record.headers().addBoolean("CamelPropertyMyBoolean", true);
        record.headers().addByte("CamelPropertyMyByte", myByte);
        record.headers().addFloat("CamelPropertyMyFloat", myFloat);
        record.headers().addShort("CamelPropertyMyShort", myShort);
        record.headers().addDouble("CamelPropertyMyDouble", myDouble);
        record.headers().addInt("CamelPropertyMyInteger", myInteger);
        record.headers().addLong("CamelPropertyMyLong", myLong);
        record.headers().addBoolean("CamelHeaderMyBoolean", true);
        record.headers().addByte("CamelHeaderMyByte", myByte);
        record.headers().addFloat("CamelHeaderMyFloat", myFloat);
        record.headers().addShort("CamelHeaderMyShort", myShort);
        record.headers().addDouble("CamelHeaderMyDouble", myDouble);
        record.headers().addInt("CamelHeaderMyInteger", myInteger);
        record.headers().addLong("CamelHeaderMyLong", myLong);
        record.headers().addList("CamelHeaderMyList", list, SchemaBuilder.array(Schema.STRING_SCHEMA));
        record.headers().addList("CamelHeaderMyList1", list1, SchemaBuilder.array(Schema.INT64_SCHEMA));
        record.headers().addList("CamelPropertyMyList", list, SchemaBuilder.array(Schema.STRING_SCHEMA));
        record.headers().addList("CamelPropertyMyList1", list1, SchemaBuilder.array(Schema.INT64_SCHEMA));
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().createConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertTrue((boolean) exchange.getProperties().get("MyBoolean"));
        assertEquals(myByte, (Byte) exchange.getProperties().get("MyByte"));
        assertEquals(myFloat, (Float) exchange.getProperties().get("MyFloat"));
        assertEquals(myShort, (Short) exchange.getProperties().get("MyShort"));
        assertEquals(myDouble, (Double) exchange.getProperties().get("MyDouble"));
        assertEquals(myInteger, exchange.getProperties().get("MyInteger"));
        assertEquals(myLong, (Long) exchange.getProperties().get("MyLong"));
        assertEquals(list, exchange.getProperties().get("MyList"));
        assertEquals(list1, exchange.getProperties().get("MyList1"));
        assertTrue(exchange.getIn().getHeader("MyBoolean", Boolean.class));
        assertEquals(myByte, exchange.getIn().getHeader("MyByte", Byte.class));
        assertEquals(myFloat, exchange.getIn().getHeader("MyFloat", Float.class));
        assertEquals(myShort, exchange.getIn().getHeader("MyShort", Short.class));
        assertEquals(myDouble, exchange.getIn().getHeader("MyDouble", Double.class));
        assertEquals(myInteger, exchange.getIn().getHeader("MyInteger"));
        assertEquals(myLong, exchange.getIn().getHeader("MyLong", Long.class));
        assertEquals(list, exchange.getIn().getHeader("MyList", List.class));
        assertEquals(list1, exchange.getIn().getHeader("MyList1", List.class));

        sinkTask.stop();
    }

    @Test
    public void testUrlPrecedenceOnComponentProperty() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSinkConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_COMPONENT_CONF, "shouldNotBeUsed");
        props.put(CamelSinkTask.getCamelSinkEndpointConfigPrefix() + "endpointProperty", "shouldNotBeUsed");
        props.put(CamelSinkTask.getCamelSinkPathConfigPrefix() + "pathChunk", "shouldNotBeUsed");

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().createConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));

        sinkTask.stop();
    }

    @Test
    public void testOnlyBodyUsingComponentProperty() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSinkConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_COMPONENT_CONF, "seda");
        props.put(CamelSinkTask.getCamelSinkEndpointConfigPrefix() + "bridgeErrorHandler", "true");
        props.put(CamelSinkTask.getCamelSinkPathConfigPrefix() + "name", "test");

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().createConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertEquals(1, sinkTask.getCms().getEndpoints()
            .stream().filter(e -> e.getEndpointUri().equals("seda://test?bridgeErrorHandler=true")).count());

        sinkTask.stop();
    }

    @Test
    public void testOnlyBodyUsingMultipleComponentProperties() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSinkConnectorConfig.TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_COMPONENT_CONF, "seda");
        props.put(CamelSinkTask.getCamelSinkEndpointConfigPrefix() + "bridgeErrorHandler", "true");
        props.put(CamelSinkTask.getCamelSinkEndpointConfigPrefix() + "size", "50");
        props.put(CamelSinkTask.getCamelSinkPathConfigPrefix() + "name", "test");

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().createConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));

        assertEquals(1, sinkTask.getCms().getEndpoints()
            .stream().filter(e -> e.getEndpointUri().equals("seda://test?bridgeErrorHandler=true&size=50")).count());

        sinkTask.stop();
    }

}
