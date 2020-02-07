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

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.sink.SinkRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CamelSinkTaskTest {

    @Test
    public void testOnlyBody() throws JsonProcessingException, InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.sink.url", "seda:test");
        props.put("topics", "mytopic");

        CamelSinkTask camelSinkTask = new CamelSinkTask();
        camelSinkTask.start(props);

        String topic = "mytopic";

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(topic, 1, null, "test", null, "camel", 42);
        records.add(record);
        camelSinkTask.put(records);

        ConsumerTemplate c = camelSinkTask.getCms().createConsumerTemplate();
        Exchange exchange = c.receive("seda:test", 1000L);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));

        camelSinkTask.stop();
    }

    @Test
    public void testBodyAndHeaders() throws JsonProcessingException, InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.sink.url", "seda:test");
        props.put("topics", "mytopic");

        CamelSinkTask camelSinkTask = new CamelSinkTask();
        camelSinkTask.start(props);

        String topic = "mytopic";
        Byte myByte = new Byte("100");
        Float myFloat = new Float("100");
        Short myShort = new Short("100");
        Double myDouble = new Double("100");
        int myInteger = 100;
        Long myLong = new Long("100");

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(topic, 1, null, "test", null, "camel", 42);
        record.headers().addBoolean("CamelHeaderMyBoolean", true);
        record.headers().addByte("CamelHeaderMyByte", myByte);
        record.headers().addFloat("CamelHeaderMyFloat", myFloat);
        record.headers().addShort("CamelHeaderMyShort", myShort);
        record.headers().addDouble("CamelHeaderMyDouble", myDouble);
        record.headers().addInt("CamelHeaderMyInteger", myInteger);
        record.headers().addLong("CamelHeaderMyLong", myLong);
        records.add(record);
        camelSinkTask.put(records);

        ConsumerTemplate c = camelSinkTask.getCms().createConsumerTemplate();
        Exchange exchange = c.receive("seda:test", 1000L);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertTrue(exchange.getIn().getHeader("CamelHeaderMyBoolean", Boolean.class));
        assertEquals(myByte, exchange.getIn().getHeader("CamelHeaderMyByte", Byte.class));
        assertEquals(myFloat, exchange.getIn().getHeader("CamelHeaderMyFloat", Float.class));
        assertEquals(myShort, exchange.getIn().getHeader("CamelHeaderMyShort", Short.class));
        assertEquals(myDouble, exchange.getIn().getHeader("CamelHeaderMyDouble", Double.class));
        assertEquals(myInteger, exchange.getIn().getHeader("CamelHeaderMyInteger"));
        assertEquals(myLong, exchange.getIn().getHeader("CamelHeaderMyLong", Long.class));

        camelSinkTask.stop();
    }
    
    @Test
    public void testBodyAndProperties() throws JsonProcessingException, InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.sink.url", "seda:test");
        props.put("topics", "mytopic");

        CamelSinkTask camelSinkTask = new CamelSinkTask();
        camelSinkTask.start(props);

        String topic = "mytopic";
        Byte myByte = new Byte("100");
        Float myFloat = new Float("100");
        Short myShort = new Short("100");
        Double myDouble = new Double("100");
        int myInteger = 100;
        Long myLong = new Long("100");

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(topic, 1, null, "test", null, "camel", 42);
        record.headers().addBoolean("CamelPropertyMyBoolean", true);
        record.headers().addByte("CamelPropertyMyByte", myByte);
        record.headers().addFloat("CamelPropertyMyFloat", myFloat);
        record.headers().addShort("CamelPropertyMyShort", myShort);
        record.headers().addDouble("CamelPropertyMyDouble", myDouble);
        record.headers().addInt("CamelPropertyMyInteger", myInteger);
        record.headers().addLong("CamelPropertyMyLong", myLong);
        records.add(record);
        camelSinkTask.put(records);

        ConsumerTemplate c = camelSinkTask.getCms().createConsumerTemplate();
        Exchange exchange = c.receive("seda:test", 1000L);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertTrue((boolean) exchange.getProperties().get("CamelPropertyMyBoolean"));
        assertEquals(myByte, (Byte) exchange.getProperties().get("CamelPropertyMyByte"));
        assertEquals(myFloat, (Float) exchange.getProperties().get("CamelPropertyMyFloat"));
        assertEquals(myShort, (Short) exchange.getProperties().get("CamelPropertyMyShort"));
        assertEquals(myDouble, (Double) exchange.getProperties().get("CamelPropertyMyDouble"));
        assertEquals(myInteger, exchange.getProperties().get("CamelPropertyMyInteger"));
        assertEquals(myLong, (Long) exchange.getProperties().get("CamelPropertyMyLong"));

        camelSinkTask.stop();
    }
    
    @Test
    public void testBodyAndPropertiesHeadersMixed() throws JsonProcessingException, InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.sink.url", "seda:test");
        props.put("topics", "mytopic");

        CamelSinkTask camelSinkTask = new CamelSinkTask();
        camelSinkTask.start(props);

        String topic = "mytopic";
        Byte myByte = new Byte("100");
        Float myFloat = new Float("100");
        Short myShort = new Short("100");
        Double myDouble = new Double("100");
        int myInteger = 100;
        Long myLong = new Long("100");

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(topic, 1, null, "test", null, "camel", 42);
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
        camelSinkTask.put(records);

        ConsumerTemplate c = camelSinkTask.getCms().createConsumerTemplate();
        Exchange exchange = c.receive("seda:test", 1000L);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertTrue((boolean) exchange.getProperties().get("CamelPropertyMyBoolean"));
        assertEquals(myByte, (Byte) exchange.getProperties().get("CamelPropertyMyByte"));
        assertEquals(myFloat, (Float) exchange.getProperties().get("CamelPropertyMyFloat"));
        assertEquals(myShort, (Short) exchange.getProperties().get("CamelPropertyMyShort"));
        assertEquals(myDouble, (Double) exchange.getProperties().get("CamelPropertyMyDouble"));
        assertEquals(myInteger, exchange.getProperties().get("CamelPropertyMyInteger"));
        assertEquals(myLong, (Long) exchange.getProperties().get("CamelPropertyMyLong"));
        assertTrue(exchange.getIn().getHeader("CamelHeaderMyBoolean", Boolean.class));
        assertEquals(myByte, exchange.getIn().getHeader("CamelHeaderMyByte", Byte.class));
        assertEquals(myFloat, exchange.getIn().getHeader("CamelHeaderMyFloat", Float.class));
        assertEquals(myShort, exchange.getIn().getHeader("CamelHeaderMyShort", Short.class));
        assertEquals(myDouble, exchange.getIn().getHeader("CamelHeaderMyDouble", Double.class));
        assertEquals(myInteger, exchange.getIn().getHeader("CamelHeaderMyInteger"));
        assertEquals(myLong, exchange.getIn().getHeader("CamelHeaderMyLong", Long.class));

        camelSinkTask.stop();
    }
    
    @Test
    public void testBodyAndHeadersMap() throws JsonProcessingException, InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.sink.url", "seda:test");
        props.put("topics", "mytopic");

        CamelSinkTask camelSinkTask = new CamelSinkTask();
        camelSinkTask.start(props);

        String topic = "mytopic";
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
        SinkRecord record = new SinkRecord(topic, 1, null, "test", null, "camel", 42);
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
        camelSinkTask.put(records);

        ConsumerTemplate c = camelSinkTask.getCms().createConsumerTemplate();
        Exchange exchange = c.receive("seda:test", 1000L);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertTrue(exchange.getIn().getHeader("CamelHeaderMyBoolean", Boolean.class));
        assertEquals(myByte, exchange.getIn().getHeader("CamelHeaderMyByte", Byte.class));
        assertEquals(myFloat, exchange.getIn().getHeader("CamelHeaderMyFloat", Float.class));
        assertEquals(myShort, exchange.getIn().getHeader("CamelHeaderMyShort", Short.class));
        assertEquals(myDouble, exchange.getIn().getHeader("CamelHeaderMyDouble", Double.class));
        assertEquals(myInteger, exchange.getIn().getHeader("CamelHeaderMyInteger"));
        assertEquals(myLong, exchange.getIn().getHeader("CamelHeaderMyLong", Long.class));
        assertEquals(map, exchange.getIn().getHeader("CamelHeaderMyMap", Map.class));
        assertEquals(map1, exchange.getIn().getHeader("CamelHeaderMyMap1", Map.class));
        assertEquals(map2, exchange.getIn().getHeader("CamelHeaderMyMap2", Map.class));
        camelSinkTask.stop();
    }
    
    @Test
    public void testBodyAndPropertiesHeadersMapMixed() throws JsonProcessingException, InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.sink.url", "seda:test");
        props.put("topics", "mytopic");

        CamelSinkTask camelSinkTask = new CamelSinkTask();
        camelSinkTask.start(props);

        String topic = "mytopic";
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
        SinkRecord record = new SinkRecord(topic, 1, null, "test", null, "camel", 42);
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
        camelSinkTask.put(records);

        ConsumerTemplate c = camelSinkTask.getCms().createConsumerTemplate();
        Exchange exchange = c.receive("seda:test", 1000L);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertTrue((boolean) exchange.getProperties().get("CamelPropertyMyBoolean"));
        assertEquals(myByte, (Byte) exchange.getProperties().get("CamelPropertyMyByte"));
        assertEquals(myFloat, (Float) exchange.getProperties().get("CamelPropertyMyFloat"));
        assertEquals(myShort, (Short) exchange.getProperties().get("CamelPropertyMyShort"));
        assertEquals(myDouble, (Double) exchange.getProperties().get("CamelPropertyMyDouble"));
        assertEquals(myInteger, exchange.getProperties().get("CamelPropertyMyInteger"));
        assertEquals(myLong, (Long) exchange.getProperties().get("CamelPropertyMyLong"));
        assertEquals(map, exchange.getProperties().get("CamelPropertyMyMap"));
        assertEquals(map1, exchange.getProperties().get("CamelPropertyMyMap1"));
        assertEquals(map2, exchange.getProperties().get("CamelPropertyMyMap2"));
        assertTrue(exchange.getIn().getHeader("CamelHeaderMyBoolean", Boolean.class));
        assertEquals(myByte, exchange.getIn().getHeader("CamelHeaderMyByte", Byte.class));
        assertEquals(myFloat, exchange.getIn().getHeader("CamelHeaderMyFloat", Float.class));
        assertEquals(myShort, exchange.getIn().getHeader("CamelHeaderMyShort", Short.class));
        assertEquals(myDouble, exchange.getIn().getHeader("CamelHeaderMyDouble", Double.class));
        assertEquals(myInteger, exchange.getIn().getHeader("CamelHeaderMyInteger"));
        assertEquals(myLong, exchange.getIn().getHeader("CamelHeaderMyLong", Long.class));
        assertEquals(map, exchange.getIn().getHeader("CamelHeaderMyMap", Map.class));
        assertEquals(map1, exchange.getIn().getHeader("CamelHeaderMyMap1", Map.class));
        assertEquals(map2, exchange.getIn().getHeader("CamelHeaderMyMap2", Map.class));

        camelSinkTask.stop();
    }
    
    @Test
    public void testBodyAndHeadersList() throws JsonProcessingException, InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.sink.url", "seda:test");
        props.put("topics", "mytopic");

        CamelSinkTask camelSinkTask = new CamelSinkTask();
        camelSinkTask.start(props);

        String topic = "mytopic";
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
        SinkRecord record = new SinkRecord(topic, 1, null, "test", null, "camel", 42);
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
        camelSinkTask.put(records);

        ConsumerTemplate c = camelSinkTask.getCms().createConsumerTemplate();
        Exchange exchange = c.receive("seda:test", 1000L);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertTrue(exchange.getIn().getHeader("CamelHeaderMyBoolean", Boolean.class));
        assertEquals(myByte, exchange.getIn().getHeader("CamelHeaderMyByte", Byte.class));
        assertEquals(myFloat, exchange.getIn().getHeader("CamelHeaderMyFloat", Float.class));
        assertEquals(myShort, exchange.getIn().getHeader("CamelHeaderMyShort", Short.class));
        assertEquals(myDouble, exchange.getIn().getHeader("CamelHeaderMyDouble", Double.class));
        assertEquals(myInteger, exchange.getIn().getHeader("CamelHeaderMyInteger"));
        assertEquals(myLong, exchange.getIn().getHeader("CamelHeaderMyLong", Long.class));
        assertEquals(list, exchange.getIn().getHeader("CamelHeaderMyList", List.class));
        assertEquals(list1, exchange.getIn().getHeader("CamelHeaderMyList1", List.class));
        camelSinkTask.stop();
    }
    
    @Test
    public void testBodyAndPropertiesHeadersListMixed() throws JsonProcessingException, InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.sink.url", "seda:test");
        props.put("topics", "mytopic");

        CamelSinkTask camelSinkTask = new CamelSinkTask();
        camelSinkTask.start(props);

        String topic = "mytopic";
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
        SinkRecord record = new SinkRecord(topic, 1, null, "test", null, "camel", 42);
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
        camelSinkTask.put(records);

        ConsumerTemplate c = camelSinkTask.getCms().createConsumerTemplate();
        Exchange exchange = c.receive("seda:test", 1000L);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertTrue((boolean) exchange.getProperties().get("CamelPropertyMyBoolean"));
        assertEquals(myByte, (Byte) exchange.getProperties().get("CamelPropertyMyByte"));
        assertEquals(myFloat, (Float) exchange.getProperties().get("CamelPropertyMyFloat"));
        assertEquals(myShort, (Short) exchange.getProperties().get("CamelPropertyMyShort"));
        assertEquals(myDouble, (Double) exchange.getProperties().get("CamelPropertyMyDouble"));
        assertEquals(myInteger, exchange.getProperties().get("CamelPropertyMyInteger"));
        assertEquals(myLong, (Long) exchange.getProperties().get("CamelPropertyMyLong"));
        assertEquals(list, exchange.getProperties().get("CamelPropertyMyList"));
        assertEquals(list1, exchange.getProperties().get("CamelPropertyMyList1"));
        assertTrue(exchange.getIn().getHeader("CamelHeaderMyBoolean", Boolean.class));
        assertEquals(myByte, exchange.getIn().getHeader("CamelHeaderMyByte", Byte.class));
        assertEquals(myFloat, exchange.getIn().getHeader("CamelHeaderMyFloat", Float.class));
        assertEquals(myShort, exchange.getIn().getHeader("CamelHeaderMyShort", Short.class));
        assertEquals(myDouble, exchange.getIn().getHeader("CamelHeaderMyDouble", Double.class));
        assertEquals(myInteger, exchange.getIn().getHeader("CamelHeaderMyInteger"));
        assertEquals(myLong, exchange.getIn().getHeader("CamelHeaderMyLong", Long.class));
        assertEquals(list, exchange.getIn().getHeader("CamelHeaderMyList", List.class));
        assertEquals(list1, exchange.getIn().getHeader("CamelHeaderMyList1", List.class));

        camelSinkTask.stop();
    }

    @Test
    public void testUrlPrecedenceOnComponentProperty() throws JsonProcessingException, InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.sink.url", "seda:test");
        props.put("topics", "mytopic");
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_COMPONENT_CONF, "shouldNotBeUsed");
        props.put(CamelSinkTask.getCamelSinkEndpointConfigPrefix() + "endpointProperty", "shouldNotBeUsed");
        props.put(CamelSinkTask.getCamelSinkPathConfigPrefix() + "pathChunk", "shouldNotBeUsed");

        CamelSinkTask camelSinkTask = new CamelSinkTask();
        camelSinkTask.start(props);

        String topic = "mytopic";

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(topic, 1, null, "test", null, "camel", 42);
        records.add(record);
        camelSinkTask.put(records);

        ConsumerTemplate c = camelSinkTask.getCms().createConsumerTemplate();
        Exchange exchange = c.receive("seda:test", 1000L);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));

        camelSinkTask.stop();
    }

    @Test
    public void testOnlyBodyUsingComponentProperty() throws JsonProcessingException, InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("topics", "mytopic");
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_COMPONENT_CONF, "seda");
        props.put(CamelSinkTask.getCamelSinkEndpointConfigPrefix() + "bridgeErrorHandler", "true");
        props.put(CamelSinkTask.getCamelSinkPathConfigPrefix() + "pathChunk", "test");

        CamelSinkTask camelSinkTask = new CamelSinkTask();
        camelSinkTask.start(props);

        String topic = "mytopic";

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(topic, 1, null, "test", null, "camel", 42);
        records.add(record);
        camelSinkTask.put(records);

        ConsumerTemplate c = camelSinkTask.getCms().createConsumerTemplate();
        Exchange exchange = c.receive("seda:test", 1000L);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));

        assertEquals(1, camelSinkTask.getCms().getEndpoints().stream().filter(e -> e.getEndpointUri().equals("seda://test?bridgeErrorHandler=true")).count());

        camelSinkTask.stop();
    }

    @Test
    public void testOnlyBodyUsingMultipleComponentProperties() throws JsonProcessingException, InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("topics", "mytopic");
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_COMPONENT_CONF, "seda");
        props.put(CamelSinkTask.getCamelSinkEndpointConfigPrefix() + "bridgeErrorHandler", "true");
        props.put(CamelSinkTask.getCamelSinkEndpointConfigPrefix() + "size", "50");
        props.put(CamelSinkTask.getCamelSinkPathConfigPrefix() + "pathChunk", "test");

        CamelSinkTask camelSinkTask = new CamelSinkTask();
        camelSinkTask.start(props);

        String topic = "mytopic";

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(topic, 1, null, "test", null, "camel", 42);
        records.add(record);
        camelSinkTask.put(records);

        ConsumerTemplate c = camelSinkTask.getCms().createConsumerTemplate();
        Exchange exchange = c.receive("seda:test", 1000L);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));

        assertEquals(1, camelSinkTask.getCms().getEndpoints().stream().filter(e -> e.getEndpointUri().equals("seda://test?bridgeErrorHandler=true&size=50")).count());

        camelSinkTask.stop();
    }

}
