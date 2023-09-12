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
import java.util.Map;

import org.apache.camel.component.slack.helper.SlackMessage;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.source.SourceRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinkPojoToSchemaAndStructTransformTest {

    @Test
    public void testRecordValueConversion() {
        SourcePojoToSchemaAndStructTransform sourcePojoToSchemaAndStructTransform = new SourcePojoToSchemaAndStructTransform();
        sourcePojoToSchemaAndStructTransform.configure(Collections.emptyMap());

        SlackMessage sm = new SlackMessage();

        SlackMessage.Attachment at1 = new SlackMessage.Attachment();
        SlackMessage.Attachment.Field at1f1 = new SlackMessage.Attachment.Field();
        at1f1.setTitle("ciao");
        at1f1.setShortValue(true);
        at1.setFields(new ArrayList<SlackMessage.Attachment.Field>(Collections.singleton(at1f1)));
        at1.setAuthorName("Andrea");

        SlackMessage.Attachment at2 = new SlackMessage.Attachment();
        at2.setColor("green");

        ArrayList<SlackMessage.Attachment> attachments = new ArrayList<>();
        attachments.add(at1);
        attachments.add(at2);

        sm.setText("text");
        sm.setAttachments(attachments);

        ConnectRecord cr = sourcePojoToSchemaAndStructTransform.apply(
                new SourceRecord(null, null, "testTopic",
                Schema.STRING_SCHEMA, "testKeyValue",
                Schema.BYTES_SCHEMA, sm));

        SinkPojoToSchemaAndStructTransform sinkPojoToSchemaAndStructTransform = new SinkPojoToSchemaAndStructTransform();
        sinkPojoToSchemaAndStructTransform.configure(Collections.singletonMap(SinkPojoToSchemaAndStructTransform.CAMEL_TRANSFORMER_SINK_POJO_CLASS_PROPERTY, SlackMessage.class.getName()));

        ConnectRecord transformedCr = sinkPojoToSchemaAndStructTransform.apply(cr);

        assertEquals("testTopic", transformedCr.topic());
        assertEquals(Schema.STRING_SCHEMA, transformedCr.keySchema());
        assertEquals("testKeyValue", transformedCr.key());
        assertEquals(SlackMessage.class.getName(), transformedCr.value().getClass().getName());
        SlackMessage transformedSM = (SlackMessage)transformedCr.value();
        assertEquals(sm.getText(), transformedSM.getText());
        assertEquals(sm.getAttachments().size(), transformedSM.getAttachments().size());
    }

    @Test
    public void testMapValueConversion() {
        SourcePojoToSchemaAndStructTransform sourcePojoToSchemaAndStructTransform = new SourcePojoToSchemaAndStructTransform();
        sourcePojoToSchemaAndStructTransform.configure(Collections.emptyMap());

        PojoWithMap pwm = new PojoWithMap();
        pwm.addToMap("ciao", 9);

        ConnectRecord cr = sourcePojoToSchemaAndStructTransform.apply(new SourceRecord(null, null, "testTopic",
                Schema.STRING_SCHEMA, "testKeyValue",
                Schema.BYTES_SCHEMA, pwm));

        SinkPojoToSchemaAndStructTransform sinkPojoToSchemaAndStructTransform = new SinkPojoToSchemaAndStructTransform();
        sinkPojoToSchemaAndStructTransform.configure(Collections.singletonMap(SinkPojoToSchemaAndStructTransform.CAMEL_TRANSFORMER_SINK_POJO_CLASS_PROPERTY, PojoWithMap.class.getName()));

        ConnectRecord transformedCr = sinkPojoToSchemaAndStructTransform.apply(cr);

        assertEquals("testTopic", transformedCr.topic());
        assertEquals(Schema.STRING_SCHEMA, transformedCr.keySchema());
        assertEquals("testKeyValue", transformedCr.key());

        assertEquals(PojoWithMap.class.getName(), transformedCr.value().getClass().getName());
        PojoWithMap transformedPWM = (PojoWithMap)transformedCr.value();
        assertEquals(pwm.getMap().size(), transformedPWM.getMap().size());
        assertEquals(pwm.getMap().keySet(), transformedPWM.getMap().keySet());
    }

    @Test()
    public void testNotStructSchemaConversion() {
        SinkPojoToSchemaAndStructTransform sinkPojoToSchemaAndStructTransform = new SinkPojoToSchemaAndStructTransform();
        sinkPojoToSchemaAndStructTransform.configure(Collections.singletonMap(SinkPojoToSchemaAndStructTransform.CAMEL_TRANSFORMER_SINK_POJO_CLASS_PROPERTY, PojoWithMap.class.getName()));

        Map map = Collections.singletonMap("ciao", 9);

        ConnectRecord cr = new SourceRecord(null, null, "testTopic",
                Schema.STRING_SCHEMA, "testKeyValue",
                null, map);

        ConnectRecord transformedCr = sinkPojoToSchemaAndStructTransform.apply(cr);
        assertEquals(cr, transformedCr);
    }

    @Test()
    public void testNullValueConversion() {
        SinkPojoToSchemaAndStructTransform sinkPojoToSchemaAndStructTransform = new SinkPojoToSchemaAndStructTransform();
        sinkPojoToSchemaAndStructTransform.configure(Collections.singletonMap(SinkPojoToSchemaAndStructTransform.CAMEL_TRANSFORMER_SINK_POJO_CLASS_PROPERTY, PojoWithMap.class.getName()));

        ConnectRecord cr = new SourceRecord(null, null, "testTopic",
                Schema.STRING_SCHEMA, "testKeyValue",
                Schema.BYTES_SCHEMA, null);

        ConnectRecord transformedCr = sinkPojoToSchemaAndStructTransform.apply(cr);
        assertEquals(cr, transformedCr);
    }
}
