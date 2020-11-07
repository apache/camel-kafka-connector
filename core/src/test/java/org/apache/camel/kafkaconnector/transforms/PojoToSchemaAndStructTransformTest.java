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
package org.apache.camel.kafkaconnector.transforms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.component.slack.helper.SlackMessage;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PojoToSchemaAndStructTransformTest {

    @Test
    public void testRecordValueConversion() {
        PojoToSchemaAndStructTransform pojoToSchemaAndStructTransform = new PojoToSchemaAndStructTransform();
        pojoToSchemaAndStructTransform.configure(Collections.emptyMap());

        SlackMessage sm = new SlackMessage();

        SlackMessage.Attachment at1 = sm.new Attachment();
        SlackMessage.Attachment.Field at1f1 = at1.new Field();
        at1f1.setTitle("ciao");
        at1f1.setShortValue(true);
        at1.setFields(new ArrayList<SlackMessage.Attachment.Field>(Collections.singleton(at1f1)));
        at1.setAuthorName("Andrea");

        SlackMessage.Attachment at2 = sm.new Attachment();
        at2.setColor("green");

        ArrayList<SlackMessage.Attachment> attachments = new ArrayList<>();
        attachments.add(at1);
        attachments.add(at2);

        sm.setAttachments(attachments);

        ConnectRecord cr = new SourceRecord(null, null, "testTopic",
                Schema.STRING_SCHEMA, "testKeyValue",
                Schema.BYTES_SCHEMA, sm);

        ConnectRecord transformedCr = pojoToSchemaAndStructTransform.apply(cr);

        assertEquals("testTopic", transformedCr.topic());
        assertEquals(Schema.STRING_SCHEMA, transformedCr.keySchema());
        assertEquals("testKeyValue", transformedCr.key());
        Schema transformedSchema = transformedCr.valueSchema();
        assertEquals(Schema.Type.STRUCT, transformedSchema.type());
        assertEquals(Schema.Type.ARRAY, transformedSchema.field("attachments").schema().type());
        assertEquals(Schema.STRING_SCHEMA.type(), transformedSchema.field("attachments").schema().valueSchema().field("title").schema().type());
        assertEquals(Struct.class, transformedCr.value().getClass());
        Struct transformedValue = (Struct)transformedCr.value();
        assertTrue(ArrayList.class.isAssignableFrom(transformedValue.get("attachments").getClass()));
    }

    @Test
    public void testMapValueConversion() {
        PojoToSchemaAndStructTransform pojoToSchemaAndStructTransform = new PojoToSchemaAndStructTransform();
        pojoToSchemaAndStructTransform.configure(Collections.emptyMap());

        PojoWithMap pwm = new PojoWithMap();
        pwm.addToMap("ciao", 9);

        ConnectRecord cr = new SourceRecord(null, null, "testTopic",
                Schema.STRING_SCHEMA, "testKeyValue",
                Schema.BYTES_SCHEMA, pwm);

        ConnectRecord transformedCr = pojoToSchemaAndStructTransform.apply(cr);

        assertEquals("testTopic", transformedCr.topic());
        assertEquals(Schema.STRING_SCHEMA, transformedCr.keySchema());
        assertEquals("testKeyValue", transformedCr.key());
        Schema transformedSchema = transformedCr.valueSchema();
        assertEquals(Schema.Type.STRUCT, transformedSchema.type());
        assertEquals(Schema.Type.MAP, transformedSchema.field("map").schema().type());
        assertEquals(Struct.class, transformedCr.value().getClass());
        Struct transformedValue = (Struct)transformedCr.value();
        assertTrue(Map.class.isAssignableFrom(transformedValue.get("map").getClass()));
    }

    public class PojoWithMap {
        private Map<String, Integer> map = new HashMap<>();

        public Map<String, Integer> getMap() {
            return map;
        }

        public void setMap(Map<String, Integer> map) {
            this.map = map;
        }

        public void addToMap(String key, Integer value) {
            map.put(key, value);
        }
    }
}
