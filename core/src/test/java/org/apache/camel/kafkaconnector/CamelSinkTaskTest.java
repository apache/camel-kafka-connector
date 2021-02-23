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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.kafka.connect.data.Decimal;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaAndValue;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.sink.SinkRecord;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CamelSinkTaskTest {

    private static final String SEDA_URI = "seda:test";
    private static final String TOPIC_NAME = "my-topic";
    private static final long RECEIVE_TIMEOUT = 1_000;
    private static final String TOPIC_CONF = "topics";

    @Test
    public void testOnlyBody() {
        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertEquals(LoggingLevel.OFF.toString(), sinkTask.getCamelSinkConnectorConfig(props)
            .getString(CamelSinkConnectorConfig.CAMEL_SINK_CONTENT_LOG_LEVEL_CONF));

        sinkTask.stop();
    }

    @Test
    public void testStructBody() {
        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        Schema keySchema = SchemaBuilder.struct()
                .name("keySchema")
                .field("id", Schema.INT32_SCHEMA)
                .build();

        Schema detailsSchema = SchemaBuilder.struct().field("age", SchemaBuilder.INT32_SCHEMA).build();

        Schema valueSchema = SchemaBuilder.struct()
                .name("valueSchema")
                .field("id", SchemaBuilder.INT32_SCHEMA)
                .field("name", SchemaBuilder.STRING_SCHEMA)
                .field("isAdult", SchemaBuilder.BOOLEAN_SCHEMA)
                .field("details", detailsSchema)
                .build();

        Struct key = new Struct(keySchema).put("id", 12);
        Struct value = new Struct(valueSchema)
                .put("id", 12)
                .put("name", "jane doe")
                .put("isAdult", true)
                .put("details", new Struct(detailsSchema).put("age", 30));

        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, keySchema, key, valueSchema, value, 42);
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);

        assertEquals("jane doe", exchange.getMessage().getBody(Map.class).get("name"));
        assertEquals(12, exchange.getMessage().getBody(Map.class).get("id"));
        assertTrue((Boolean) exchange.getMessage().getBody(Map.class).get("isAdult"));
        assertEquals(30, ((Map) exchange.getMessage().getBody(Map.class).get("details")).get("age"));

        assertEquals(12, ((Map) exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER)).get("id"));
        assertEquals(LoggingLevel.OFF.toString(), sinkTask.getCamelSinkConnectorConfig(props)
                .getString(CamelSinkConnectorConfig.CAMEL_SINK_CONTENT_LOG_LEVEL_CONF));

        sinkTask.stop();
    }

    @Test
    public void testTopicsRegex() {
        Map<String, String> props = new HashMap<>();
        props.put("topics.regex", "topic1*");
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord("topic1", 1, null, "test", null, "camel", 42);
        SinkRecord record1 = new SinkRecord("topic12", 1, null, "test", null, "cameltopicregex", 42);
        records.add(record);
        records.add(record1);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertEquals(LoggingLevel.OFF.toString(), sinkTask.getCamelSinkConnectorConfig(props)
            .getString(CamelSinkConnectorConfig.CAMEL_SINK_CONTENT_LOG_LEVEL_CONF));
        Exchange exchange1 = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("cameltopicregex", exchange1.getMessage().getBody());
        assertEquals("test", exchange1.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));

        sinkTask.stop();
    }

    @Test
    public void testBodyAndHeaders() {
        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        Byte myByte = new Byte("100");
        Float myFloat = new Float("100");
        Short myShort = new Short("100");
        Double myDouble = new Double("100");
        int myInteger = 100;
        Long myLong = new Long("100");
        BigDecimal myBigDecimal = new BigDecimal(1234567890);
        Schema schema = Decimal.schema(myBigDecimal.scale());

        Schema headerStruct = SchemaBuilder.struct()
                .field("myHeader", Schema.STRING_SCHEMA)
                .build();

        Struct headerStructValue = new Struct(headerStruct).put("myHeader", "structHeader");

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        record.headers().addBoolean(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyBoolean", true);
        record.headers().addByte(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyByte", myByte);
        record.headers().addFloat(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyFloat", myFloat);
        record.headers().addShort(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyShort", myShort);
        record.headers().addDouble(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyDouble", myDouble);
        record.headers().addInt(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyInteger", myInteger);
        record.headers().addLong(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyLong", myLong);
        record.headers().add(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyBigDecimal", Decimal.fromLogical(schema, myBigDecimal), schema);
        record.headers().addStruct(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyStruct", headerStructValue);
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
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
        assertEquals(myBigDecimal, exchange.getIn().getHeader("MyBigDecimal", BigDecimal.class));
        assertEquals("structHeader", exchange.getIn().getHeader("MyStruct", Map.class).get("myHeader"));

        sinkTask.stop();
    }

    @Test
    public void testBodyAndHeadersExclusions() {
        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_REMOVE_HEADERS_PATTERN_CONF, "MyBoolean" + "|" + "MyShort");

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        Byte myByte = new Byte("100");
        Float myFloat = new Float("100");
        Short myShort = new Short("100");
        Double myDouble = new Double("100");
        int myInteger = 100;
        Long myLong = new Long("100");
        BigDecimal myBigDecimal = new BigDecimal(1234567890);
        Schema schema = Decimal.schema(myBigDecimal.scale());

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        record.headers().addBoolean(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyBoolean", true);
        record.headers().addByte(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyByte", myByte);
        record.headers().addFloat(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyFloat", myFloat);
        record.headers().addShort(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyShort", myShort);
        record.headers().addDouble(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyDouble", myDouble);
        record.headers().addInt(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyInteger", myInteger);
        record.headers().addLong(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyLong", myLong);
        record.headers().add(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyBigDecimal", Decimal.fromLogical(schema, myBigDecimal), schema);
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertNull(exchange.getIn().getHeader("MyBoolean", Boolean.class));
        assertEquals(myByte, exchange.getIn().getHeader("MyByte", Byte.class));
        assertEquals(myFloat, exchange.getIn().getHeader("MyFloat", Float.class));
        assertNull(exchange.getIn().getHeader("MyShort", Short.class));
        assertEquals(myDouble, exchange.getIn().getHeader("MyDouble", Double.class));
        assertEquals(myInteger, exchange.getIn().getHeader("MyInteger"));
        assertEquals(myLong, exchange.getIn().getHeader("MyLong", Long.class));
        assertEquals(myBigDecimal, exchange.getIn().getHeader("MyBigDecimal", BigDecimal.class));

        sinkTask.stop();
    }

    @Test
    public void testBodyAndHeadersExclusionsRegex() {
        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_REMOVE_HEADERS_PATTERN_CONF, "My*");

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        Byte myByte = new Byte("100");
        Float myFloat = new Float("100");
        Short myShort = new Short("100");
        Double myDouble = new Double("100");
        int myInteger = 100;
        Long myLong = new Long("100");
        BigDecimal myBigDecimal = new BigDecimal(1234567890);
        Schema schema = Decimal.schema(myBigDecimal.scale());

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        record.headers().addBoolean(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyBoolean", true);
        record.headers().addByte(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyByte", myByte);
        record.headers().addFloat(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyFloat", myFloat);
        record.headers().addShort(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyShort", myShort);
        record.headers().addDouble(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyDouble", myDouble);
        record.headers().addInt(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyInteger", myInteger);
        record.headers().addLong(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyLong", myLong);
        record.headers().add(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyBigDecimal", Decimal.fromLogical(schema, myBigDecimal), schema);
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertNull(exchange.getIn().getHeader("MyBoolean", Boolean.class));
        assertNull(exchange.getIn().getHeader("MyByte", Byte.class));
        assertNull(exchange.getIn().getHeader("MyFloat", Float.class));
        assertNull(exchange.getIn().getHeader("MyShort", Short.class));
        assertNull(exchange.getIn().getHeader("MyDouble", Double.class));
        assertNull(exchange.getIn().getHeader("MyInteger"));
        assertNull(exchange.getIn().getHeader("MyLong", Long.class));
        assertNull(exchange.getIn().getHeader("MyBigDecimal", BigDecimal.class));

        sinkTask.stop();
    }

    @Test
    public void testBodyAndProperties() {
        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
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
        record.headers().addBoolean(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyBoolean", true);
        record.headers().addByte(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyByte", myByte);
        record.headers().addFloat(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyFloat", myFloat);
        record.headers().addShort(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyShort", myShort);
        record.headers().addDouble(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyDouble", myDouble);
        record.headers().addInt(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyInteger", myInteger);
        record.headers().addLong(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyLong", myLong);
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
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
        props.put(TOPIC_CONF, TOPIC_NAME);
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
        record.headers().addBoolean(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyBoolean", true);
        record.headers().addByte(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyByte", myByte);
        record.headers().addFloat(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyFloat", myFloat);
        record.headers().addShort(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyShort", myShort);
        record.headers().addDouble(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyDouble", myDouble);
        record.headers().addInt(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyInteger", myInteger);
        record.headers().addLong(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyLong", myLong);
        record.headers().addBoolean(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyBoolean", true);
        record.headers().addByte(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyByte", myByte);
        record.headers().addFloat(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyFloat", myFloat);
        record.headers().addShort(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyShort", myShort);
        record.headers().addDouble(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyDouble", myDouble);
        record.headers().addInt(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyInteger", myInteger);
        record.headers().addLong(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyLong", myLong);
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
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
        props.put(TOPIC_CONF, TOPIC_NAME);
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
        record.headers().addBoolean(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyBoolean", true);
        record.headers().addByte(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyByte", myByte);
        record.headers().addFloat(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyFloat", myFloat);
        record.headers().addShort(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyShort", myShort);
        record.headers().addDouble(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyDouble", myDouble);
        record.headers().addInt(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyInteger", myInteger);
        record.headers().addLong(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyLong", myLong);
        record.headers().addMap(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyMap", map, SchemaBuilder.map(Schema.STRING_SCHEMA, Schema.STRING_SCHEMA));
        record.headers().addMap(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyMap1", map1, SchemaBuilder.map(Schema.INT64_SCHEMA, Schema.STRING_SCHEMA));
        record.headers().addMap(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyMap2", map2, SchemaBuilder.map(Schema.INT64_SCHEMA, Schema.INT64_SCHEMA));
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
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
        props.put(TOPIC_CONF, TOPIC_NAME);
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
        record.headers().addBoolean(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyBoolean", true);
        record.headers().addByte(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyByte", myByte);
        record.headers().addFloat(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyFloat", myFloat);
        record.headers().addShort(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyShort", myShort);
        record.headers().addDouble(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyDouble", myDouble);
        record.headers().addInt(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyInteger", myInteger);
        record.headers().addLong(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyLong", myLong);
        record.headers().addMap(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyMap", map, SchemaBuilder.map(Schema.STRING_SCHEMA, Schema.STRING_SCHEMA));
        record.headers().addMap(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyMap1", map1, SchemaBuilder.map(Schema.INT64_SCHEMA, Schema.STRING_SCHEMA));
        record.headers().addMap(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyMap2", map2, SchemaBuilder.map(Schema.INT64_SCHEMA, Schema.INT64_SCHEMA));
        record.headers().addBoolean(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyBoolean", true);
        record.headers().addByte(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyByte", myByte);
        record.headers().addFloat(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyFloat", myFloat);
        record.headers().addShort(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyShort", myShort);
        record.headers().addDouble(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyDouble", myDouble);
        record.headers().addInt(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyInteger", myInteger);
        record.headers().addLong(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyLong", myLong);
        record.headers().addMap(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyMap", map, SchemaBuilder.map(Schema.STRING_SCHEMA, Schema.STRING_SCHEMA));
        record.headers().addMap(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyMap1", map1, SchemaBuilder.map(Schema.INT64_SCHEMA, Schema.STRING_SCHEMA));
        record.headers().addMap(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyMap2", map2, SchemaBuilder.map(Schema.INT64_SCHEMA, Schema.INT64_SCHEMA));
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
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
        props.put(TOPIC_CONF, TOPIC_NAME);
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
        record.headers().addBoolean(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyBoolean", true);
        record.headers().addByte(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyByte", myByte);
        record.headers().addFloat(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyFloat", myFloat);
        record.headers().addShort(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyShort", myShort);
        record.headers().addDouble(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyDouble", myDouble);
        record.headers().addInt(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyInteger", myInteger);
        record.headers().addLong(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyLong", myLong);
        record.headers().addList(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyList", list, SchemaBuilder.array(Schema.STRING_SCHEMA));
        record.headers().addList(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyList1", list1, SchemaBuilder.array(Schema.INT64_SCHEMA));
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
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
        props.put(TOPIC_CONF, TOPIC_NAME);
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
        record.headers().addBoolean(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyBoolean", true);
        record.headers().addByte(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyByte", myByte);
        record.headers().addFloat(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyFloat", myFloat);
        record.headers().addShort(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyShort", myShort);
        record.headers().addDouble(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyDouble", myDouble);
        record.headers().addInt(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyInteger", myInteger);
        record.headers().addLong(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyLong", myLong);
        record.headers().addBoolean(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyBoolean", true);
        record.headers().addByte(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyByte", myByte);
        record.headers().addFloat(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyFloat", myFloat);
        record.headers().addShort(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyShort", myShort);
        record.headers().addDouble(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyDouble", myDouble);
        record.headers().addInt(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyInteger", myInteger);
        record.headers().addLong(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyLong", myLong);
        record.headers().addList(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyList", list, SchemaBuilder.array(Schema.STRING_SCHEMA));
        record.headers().addList(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyList1", list1, SchemaBuilder.array(Schema.INT64_SCHEMA));
        record.headers().addList(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyList", list, SchemaBuilder.array(Schema.STRING_SCHEMA));
        record.headers().addList(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyList1", list1, SchemaBuilder.array(Schema.INT64_SCHEMA));
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
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
        props.put(TOPIC_CONF, TOPIC_NAME);
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

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));

        sinkTask.stop();
    }

    @Test
    public void testOnlyBodyUsingComponentProperty() {
        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_COMPONENT_CONF, "seda");
        props.put(CamelSinkTask.getCamelSinkEndpointConfigPrefix() + "bridgeErrorHandler", "true");
        props.put(CamelSinkTask.getCamelSinkPathConfigPrefix() + "name", "test");

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertEquals(1, sinkTask.getCms().getCamelContext().getEndpoints()
            .stream().filter(e -> e.getEndpointUri().equals("seda://test?bridgeErrorHandler=true")).count());

        sinkTask.stop();
    }

    @Test
    public void testOnlyBodyUsingMultipleComponentProperties() {
        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
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

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));

        assertEquals(1, sinkTask.getCms().getCamelContext().getEndpoints()
            .stream().filter(e -> e.getEndpointUri().equals("seda://test?bridgeErrorHandler=true&size=50")).count());

        sinkTask.stop();
    }

    @Test
    public void testBodyAndPropertiesHeadersMixedWithoutPropertiesAndHeadersMapping() {
        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_MAP_PROPERTIES_CONF, "false");
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_MAP_HEADERS_CONF, "false");

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
        record.headers().addBoolean(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyBoolean", true);
        record.headers().addByte(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyByte", myByte);
        record.headers().addFloat(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyFloat", myFloat);
        record.headers().addShort(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyShort", myShort);
        record.headers().addDouble(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyDouble", myDouble);
        record.headers().addInt(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyInteger", myInteger);
        record.headers().addLong(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyLong", myLong);
        record.headers().addBoolean(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyBoolean", true);
        record.headers().addByte(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyByte", myByte);
        record.headers().addFloat(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyFloat", myFloat);
        record.headers().addShort(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyShort", myShort);
        record.headers().addDouble(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyDouble", myDouble);
        record.headers().addInt(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyInteger", myInteger);
        record.headers().addLong(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyLong", myLong);
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertFalse(exchange.getProperties().containsKey("MyBoolean"));
        assertFalse(exchange.getProperties().containsKey("MyByte"));
        assertFalse(exchange.getProperties().containsKey("MyFloat"));
        assertFalse(exchange.getProperties().containsKey("MyShort"));
        assertFalse(exchange.getProperties().containsKey("MyDouble"));
        assertFalse(exchange.getProperties().containsKey("MyInteger"));
        assertFalse(exchange.getProperties().containsKey("MyLong"));
        assertFalse(exchange.getMessage().getHeaders().containsKey("MyBoolean"));
        assertFalse(exchange.getMessage().getHeaders().containsKey("MyByte"));
        assertFalse(exchange.getMessage().getHeaders().containsKey("MyFloat"));
        assertFalse(exchange.getMessage().getHeaders().containsKey("MyShort"));
        assertFalse(exchange.getMessage().getHeaders().containsKey("MyDouble"));
        assertFalse(exchange.getMessage().getHeaders().containsKey("MyInteger"));
        assertFalse(exchange.getMessage().getHeaders().containsKey("MyLong"));

        sinkTask.stop();
    }

    @Test
    public void testBodyAndPropertiesHeadersMixedWithoutPropertiesMapping() {
        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_MAP_PROPERTIES_CONF, "false");

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
        record.headers().addBoolean(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyBoolean", true);
        record.headers().addByte(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyByte", myByte);
        record.headers().addFloat(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyFloat", myFloat);
        record.headers().addShort(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyShort", myShort);
        record.headers().addDouble(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyDouble", myDouble);
        record.headers().addInt(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyInteger", myInteger);
        record.headers().addLong(CamelSinkTask.PROPERTY_CAMEL_PREFIX + "MyLong", myLong);
        record.headers().addBoolean(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyBoolean", true);
        record.headers().addByte(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyByte", myByte);
        record.headers().addFloat(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyFloat", myFloat);
        record.headers().addShort(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyShort", myShort);
        record.headers().addDouble(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyDouble", myDouble);
        record.headers().addInt(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyInteger", myInteger);
        record.headers().addLong(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyLong", myLong);
        records.add(record);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertFalse(exchange.getProperties().containsKey("MyBoolean"));
        assertFalse(exchange.getProperties().containsKey("MyByte"));
        assertFalse(exchange.getProperties().containsKey("MyFloat"));
        assertFalse(exchange.getProperties().containsKey("MyShort"));
        assertFalse(exchange.getProperties().containsKey("MyDouble"));
        assertFalse(exchange.getProperties().containsKey("MyInteger"));
        assertFalse(exchange.getProperties().containsKey("MyLong"));
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
    public void testIfExchangeFailsShouldThrowConnectException() {
        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
        // we use a dummy component sink in order fail the exchange delivery
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_COMPONENT_CONF, "direct");
        props.put(CamelSinkTask.getCamelSinkPathConfigPrefix() + "name", "test");

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        records.add(record);

        assertThrows(ConnectException.class, () -> sinkTask.put(records));

        sinkTask.stop();
    }

    @Test
    public void testAggregationBody() {
        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_CONF, "#class:org.apache.camel.kafkaconnector.utils.SampleAggregator");
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_SIZE_CONF, "5");
        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        SinkRecord record1 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel1", 42);
        SinkRecord record2 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel2", 42);
        SinkRecord record3 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel3", 42);
        SinkRecord record4 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel4", 42);
        SinkRecord record5 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel5", 42);
        records.add(record);
        records.add(record1);
        records.add(record2);
        records.add(record3);
        records.add(record4);
        records.add(record5);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel camel1 camel2 camel3 camel4", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertEquals(LoggingLevel.OFF.toString(), sinkTask.getCamelSinkConnectorConfig(props)
            .getString(CamelSinkConnectorConfig.CAMEL_SINK_CONTENT_LOG_LEVEL_CONF));

        sinkTask.stop();
    }

    @Test
    public void testAggregationBodyAndTimeout() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_CONF, "#class:org.apache.camel.kafkaconnector.utils.SampleAggregator");
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_SIZE_CONF, "5");
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_TIMEOUT_CONF, "100");
        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        SinkRecord record1 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel1", 42);
        SinkRecord record2 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel2", 42);
        SinkRecord record3 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel3", 42);
        SinkRecord record4 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel4", 42);
        SinkRecord record5 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel5", 42);
        records.add(record);
        records.add(record1);
        records.add(record2);
        records.add(record3);
        records.add(record4);
        records.add(record5);
        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel camel1 camel2 camel3 camel4", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertEquals(LoggingLevel.OFF.toString(), sinkTask.getCamelSinkConnectorConfig(props)
            .getString(CamelSinkConnectorConfig.CAMEL_SINK_CONTENT_LOG_LEVEL_CONF));

        sinkTask.stop();
    }

    @Test
    public void testAggregationWithIdempotencyBodyAndTimeout() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_CONF, "#class:org.apache.camel.kafkaconnector.utils.SampleAggregator");
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_SIZE_CONF, "5");
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_TIMEOUT_CONF, "100");
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_ENABLED_CONF, "true");
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_TYPE_CONF, "body");
        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        SinkRecord record1 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel1", 42);
        SinkRecord record2 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel2", 42);
        SinkRecord record3 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel3", 42);
        SinkRecord record4 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel4", 42);
        SinkRecord record5 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        SinkRecord record6 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel1", 42);
        SinkRecord record7 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel2", 42);
        SinkRecord record8 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel3", 42);
        SinkRecord record9 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel4", 42);
        SinkRecord record10 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel5", 42);
        SinkRecord record11 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel6", 42);
        SinkRecord record12 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel7", 42);
        SinkRecord record13 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel8", 42);
        SinkRecord record14 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel9", 42);
        records.add(record);
        records.add(record1);
        records.add(record2);
        records.add(record3);
        records.add(record4);
        records.add(record5);
        records.add(record6);
        records.add(record7);
        records.add(record8);
        records.add(record9);
        records.add(record10);
        records.add(record11);
        records.add(record12);
        records.add(record13);
        records.add(record14);

        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel camel1 camel2 camel3 camel4", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertEquals(LoggingLevel.OFF.toString(), sinkTask.getCamelSinkConnectorConfig(props)
            .getString(CamelSinkConnectorConfig.CAMEL_SINK_CONTENT_LOG_LEVEL_CONF));

        exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel5 camel6 camel7 camel8 camel9", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertEquals(LoggingLevel.OFF.toString(), sinkTask.getCamelSinkConnectorConfig(props)
            .getString(CamelSinkConnectorConfig.CAMEL_SINK_CONTENT_LOG_LEVEL_CONF));

        sinkTask.stop();
    }

    @Test
    public void testWithIdempotency() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_ENABLED_CONF, "true");
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_TYPE_CONF, "body");
        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        SinkRecord record1 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        SinkRecord record2 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        SinkRecord record3 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        SinkRecord record4 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel1", 42);
        SinkRecord record5 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        SinkRecord record6 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        SinkRecord record7 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        SinkRecord record8 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        SinkRecord record9 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel2", 42);
        SinkRecord record10 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        SinkRecord record11 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        SinkRecord record12 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        SinkRecord record13 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        SinkRecord record14 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        records.add(record);
        records.add(record1);
        records.add(record2);
        records.add(record3);
        records.add(record4);
        records.add(record5);
        records.add(record6);
        records.add(record7);
        records.add(record8);
        records.add(record9);
        records.add(record10);
        records.add(record11);
        records.add(record12);
        records.add(record13);
        records.add(record14);

        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertEquals(LoggingLevel.OFF.toString(), sinkTask.getCamelSinkConnectorConfig(props)
            .getString(CamelSinkConnectorConfig.CAMEL_SINK_CONTENT_LOG_LEVEL_CONF));

        exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel1", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertEquals(LoggingLevel.OFF.toString(), sinkTask.getCamelSinkConnectorConfig(props)
            .getString(CamelSinkConnectorConfig.CAMEL_SINK_CONTENT_LOG_LEVEL_CONF));

        exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel2", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertEquals(LoggingLevel.OFF.toString(), sinkTask.getCamelSinkConnectorConfig(props)
            .getString(CamelSinkConnectorConfig.CAMEL_SINK_CONTENT_LOG_LEVEL_CONF));

        sinkTask.stop();
    }

    @Test
    public void testWithIdempotencyAndHeader() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_ENABLED_CONF, "true");
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_TYPE_CONF, "header");
        props.put(CamelSinkConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_HEADER_CONF, "headerIdempotency");
        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        record.headers().add("CamelHeader.headerIdempotency", new SchemaAndValue(Schema.STRING_SCHEMA, "Test"));
        SinkRecord record1 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
        record1.headers().add("CamelHeader.headerIdempotency", new SchemaAndValue(Schema.STRING_SCHEMA, "Test"));
        SinkRecord record2 = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel1", 42);
        record2.headers().add("CamelHeader.headerIdempotency", new SchemaAndValue(Schema.STRING_SCHEMA, "Test1"));

        records.add(record);
        records.add(record1);
        records.add(record2);

        sinkTask.put(records);

        ConsumerTemplate consumer = sinkTask.getCms().getConsumerTemplate();
        Exchange exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertEquals(LoggingLevel.OFF.toString(), sinkTask.getCamelSinkConnectorConfig(props)
            .getString(CamelSinkConnectorConfig.CAMEL_SINK_CONTENT_LOG_LEVEL_CONF));

        exchange = consumer.receive(SEDA_URI, RECEIVE_TIMEOUT);
        assertEquals("camel1", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertEquals(LoggingLevel.OFF.toString(), sinkTask.getCamelSinkConnectorConfig(props)
            .getString(CamelSinkConnectorConfig.CAMEL_SINK_CONTENT_LOG_LEVEL_CONF));

        sinkTask.stop();
    }

    @Test
    public void testSecretRaw() {
        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
        props.put("camel.sink.endpoint.secretKey", "se+ret");
        props.put("camel.sink.endpoint.accessKey", "MoreSe+ret$");
        props.put("camel.sink.endpoint.queueNameOrArn", "test");
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_COMPONENT_CONF, "aws2-sqs");

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        sinkTask.stop();
    }

    @Test
    public void testSecretRawReference() {
        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
        props.put("camel.sink.endpoint.secretKey", "#bean:mySecretKey");
        props.put("camel.sink.endpoint.accessKey", "#property:myAccessKey");
        props.put("camel.sink.endpoint.queueNameOrArn", "test");
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_COMPONENT_CONF, "aws2-sqs");
        props.put("myAccessKey", "MoreSe+ret$");

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        sinkTask.stop();
    }

    @Test
    public void testBodyAndDateHeader() {
        final Date now = new Date();

        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);

        try {
            List<SinkRecord> records = new ArrayList<>();

            SinkRecord record = new SinkRecord(TOPIC_NAME, 1, null, "test", null, "camel", 42);
            record.headers().addTimestamp(CamelSinkTask.HEADER_CAMEL_PREFIX + "MyDate", now);
            records.add(record);

            sinkTask.put(records);

            Exchange exchange = sinkTask.getCms().getConsumerTemplate().receive(SEDA_URI, RECEIVE_TIMEOUT);

            assertThat(exchange.getIn().getHeader("MyDate")).isInstanceOfSatisfying(Date.class, value -> {
                assertThat(value).isEqualTo(now);
            });
        } finally {
            sinkTask.stop();
        }
    }

    @Test
    public void testContentLogLevelConfiguration() {
        Map<String, String> props = new HashMap<>();
        props.put(TOPIC_CONF, TOPIC_NAME);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF, SEDA_URI);
        props.put(CamelSinkConnectorConfig.CAMEL_SINK_CONTENT_LOG_LEVEL_CONF, "INFO");

        CamelSinkTask sinkTask = new CamelSinkTask();
        sinkTask.start(props);
        assertEquals(LoggingLevel.INFO, sinkTask.getLoggingLevel());

        sinkTask.stop();
    }
}
