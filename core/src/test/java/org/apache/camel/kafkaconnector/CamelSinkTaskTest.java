/**
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.kafka.connect.sink.SinkRecord;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

public class CamelSinkTaskTest {

    @Test
    public void testOnlyBody() throws JsonProcessingException, InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.sink.url", "seda:test");
        props.put("topics", "mytopic");

        CamelSinkTask camelsinkTask = new CamelSinkTask();
        camelsinkTask.start(props);

        String topic = "mytopic";

        List<SinkRecord> records = new ArrayList<SinkRecord>();
        SinkRecord record = new SinkRecord(topic, 1, null, "test", null, "camel", 42);
        records.add(record);
        camelsinkTask.put(records);

        ConsumerTemplate c = camelsinkTask.getCms().createConsumerTemplate();
        Exchange exchange = c.receive("seda:test", 1000L);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));

        camelsinkTask.stop();
    }

    @Test
    public void testBodyAndHeaders() throws JsonProcessingException, InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.sink.url", "seda:test");
        props.put("topics", "mytopic");

        CamelSinkTask camelsinkTask = new CamelSinkTask();
        camelsinkTask.start(props);

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
        camelsinkTask.put(records);

        ConsumerTemplate c = camelsinkTask.getCms().createConsumerTemplate();
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

        camelsinkTask.stop();
    }
    
    @Test
    public void testBodyAndProperties() throws JsonProcessingException, InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.sink.url", "seda:test");
        props.put("topics", "mytopic");

        CamelSinkTask camelsinkTask = new CamelSinkTask();
        camelsinkTask.start(props);

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
        camelsinkTask.put(records);

        ConsumerTemplate c = camelsinkTask.getCms().createConsumerTemplate();
        Exchange exchange = c.receive("seda:test", 1000L);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertTrue((boolean) exchange.getProperties().get(("CamelPropertyMyBoolean")));
        assertEquals(myByte, (Byte) exchange.getProperties().get("CamelPropertyMyByte"));
        assertEquals(myFloat, (Float) exchange.getProperties().get("CamelPropertyMyFloat"));
        assertEquals(myShort, (Short) exchange.getProperties().get("CamelPropertyMyShort"));
        assertEquals(myDouble, (Double) exchange.getProperties().get("CamelPropertyMyDouble"));
        assertEquals(myInteger, exchange.getProperties().get("CamelPropertyMyInteger"));
        assertEquals(myLong, (Long) exchange.getProperties().get("CamelPropertyMyLong"));

        camelsinkTask.stop();
    }
    
    @Test
    public void testBodyAndPropertiesHeadersMixed() throws JsonProcessingException, InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.sink.url", "seda:test");
        props.put("topics", "mytopic");

        CamelSinkTask camelsinkTask = new CamelSinkTask();
        camelsinkTask.start(props);

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
        camelsinkTask.put(records);

        ConsumerTemplate c = camelsinkTask.getCms().createConsumerTemplate();
        Exchange exchange = c.receive("seda:test", 1000L);
        assertEquals("camel", exchange.getMessage().getBody());
        assertEquals("test", exchange.getMessage().getHeaders().get(CamelSinkTask.KAFKA_RECORD_KEY_HEADER));
        assertTrue((boolean) exchange.getProperties().get(("CamelPropertyMyBoolean")));
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

        camelsinkTask.stop();
    }
}
