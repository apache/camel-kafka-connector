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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.apache.camel.util.function.TriConsumer;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.header.Header;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.source.SourceRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.apache.kafka.connect.data.Schema.BOOLEAN_SCHEMA;
import static org.apache.kafka.connect.data.Schema.BYTES_SCHEMA;
import static org.apache.kafka.connect.data.Schema.FLOAT32_SCHEMA;
import static org.apache.kafka.connect.data.Schema.INT32_SCHEMA;
import static org.apache.kafka.connect.data.Schema.OPTIONAL_STRING_SCHEMA;
import static org.apache.kafka.connect.data.Schema.STRING_SCHEMA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FieldsToHeadersTransformTest {

    private static List<String> fields = Arrays.asList("FROM", "TO", "CC", "SUBJECT", "BODY", "INT_EXAMPLE", "BYTE_EXAMPLE", "BOOLEAN_EXAMPLE", "FLOAT_EXAMPLE");
    private static List<String> headers = Arrays.asList("from", "to", "cc", "subject", "body", "int_example", "byte_example", "boolean_example", "float_example");

    @Test
    public void testKeyWithSchema() {
        FieldsToHeadersTransform.Key fieldsToHeadersTransform = new FieldsToHeadersTransform.Key();
        ConnectRecord transformedCr = testWithSchema(fieldsToHeadersTransform, (headerSchema, headerValue) -> new SourceRecord(null, null, "testTopic", headerSchema, headerValue, null, null));
        assertNull(transformedCr.value());
        assertNull(transformedCr.valueSchema());
    }

    @Test
    public void testWholeKey() {
        FieldsToHeadersTransform.Key fieldsToHeadersTransform = new FieldsToHeadersTransform.Key();
        Map<String, String> conf = new HashMap<>();
        conf.put("headers", "camel.kafka.KEY");
        fieldsToHeadersTransform.configure(conf);
        ConnectRecord transformedCr =  fieldsToHeadersTransform.apply(new SourceRecord(null, null, "testTopic", null, 100, null, null));
        assertEquals(100, transformedCr.headers().lastWithName("camel.kafka.KEY").value());
    }

    @Test
    public void testWholeKeyWithSchema() {
        FieldsToHeadersTransform.Key fieldsToHeadersTransform = new FieldsToHeadersTransform.Key();
        Map<String, String> conf = new HashMap<>();
        conf.put("headers", "camel.kafka.KEY");
        fieldsToHeadersTransform.configure(conf);
        ConnectRecord transformedCr =  fieldsToHeadersTransform.apply(new SourceRecord(null, null, "testTopic", INT32_SCHEMA, 100, null, null));
        assertEquals(100, transformedCr.headers().lastWithName("camel.kafka.KEY").value());
    }

    @Test
    public void testWholeKeyMultipleHeaders() {
        FieldsToHeadersTransform.Key fieldsToHeadersTransform = new FieldsToHeadersTransform.Key();
        Map<String, String> conf = new HashMap<>();
        conf.put("headers", "camel.kafka.KEY,should.not.be.set");
        fieldsToHeadersTransform.configure(conf);
        ConnectRecord transformedCr =  fieldsToHeadersTransform.apply(new SourceRecord(null, null, "testTopic", null, 100, null, null));
        assertEquals(100, transformedCr.headers().lastWithName("camel.kafka.KEY").value());
    }

    @Test
    public void testValueWithSchema() {
        FieldsToHeadersTransform.Value fieldsToHeadersTransform = new FieldsToHeadersTransform.Value();
        ConnectRecord transformedCr = testWithSchema(fieldsToHeadersTransform, (schema, value) -> new SourceRecord(null, null, "testTopic", schema, value));
        assertNull(transformedCr.key());
        assertNull(transformedCr.keySchema());
    }

    @Test
    public void testWithoutSchema() {

        TriConsumer<ConnectRecord, String, String> assertions = (record, headerName, expectedHeaderValue) -> {
            assertNull(record.keySchema());
            assertEquals("testTopic", record.topic());
            Iterator<Header> headerIterator = record.headers().allWithName(headerName);
            assertTrue(headerIterator.hasNext());
            Header header = headerIterator.next();
            assertEquals(expectedHeaderValue, header.value());
            assertNull(header.schema());
            assertFalse(headerIterator.hasNext());
        };

        Map<String, String> conf = new HashMap<>();
        conf.put("fields", "FROM,TO");
        conf.put("headers", "from,to");

        Map<String, String> message = new HashMap<>();
        message.put("FROM", "bob@example.com");
        message.put("TO", "alice@mail.com");

        // Test KEY
        FieldsToHeadersTransform.Key fieldsToHeadersTransformKey = new FieldsToHeadersTransform.Key();
        fieldsToHeadersTransformKey.configure(conf);

        final SinkRecord recordKey = new SinkRecord("testTopic", 0, null, message, null, null, 0);
        final ConnectRecord transformedRecordKey = fieldsToHeadersTransformKey.apply(recordKey);

        assertions.accept(transformedRecordKey, "from", message.get("FROM"));
        assertions.accept(transformedRecordKey, "to", message.get("TO"));

        // Test VALUE
        FieldsToHeadersTransform.Value fieldsToHeadersTransformValue = new FieldsToHeadersTransform.Value();
        fieldsToHeadersTransformValue.configure(conf);
        final SinkRecord recordValue = new SinkRecord("testTopic", 0, null, null, null, message, 0);
        final ConnectRecord transformedRecordValue = fieldsToHeadersTransformValue.apply(recordValue);

        assertions.accept(transformedRecordValue, "from", message.get("FROM"));
        assertions.accept(transformedRecordValue, "to", message.get("TO"));
    }

    @Test
    public void testWholeValue() {
        FieldsToHeadersTransform.Value fieldsToHeadersTransform = new FieldsToHeadersTransform.Value();
        Map<String, String> conf = new HashMap<>();
        conf.put("headers", "camel.kafka.KEY");
        fieldsToHeadersTransform.configure(conf);
        ConnectRecord transformedCr =  fieldsToHeadersTransform.apply(new SourceRecord(null, null, "testTopic", null, null, null, 100));
        assertEquals(100, transformedCr.headers().lastWithName("camel.kafka.KEY").value());
    }

    @Test
    public void testWholeValueMultipleHeaders() {
        FieldsToHeadersTransform.Value fieldsToHeadersTransform = new FieldsToHeadersTransform.Value();
        Map<String, String> conf = new HashMap<>();
        conf.put("headers", "camel.kafka.KEY,should.not.be.set");
        fieldsToHeadersTransform.configure(conf);
        ConnectRecord transformedCr =  fieldsToHeadersTransform.apply(new SourceRecord(null, null, "testTopic", null, null, null, 100));
        assertEquals(100, transformedCr.headers().lastWithName("camel.kafka.KEY").value());
    }

    @Test
    public void testWholeValueWithSchema() {
        FieldsToHeadersTransform.Value fieldsToHeadersTransform = new FieldsToHeadersTransform.Value();
        Map<String, String> conf = new HashMap<>();
        conf.put("headers", "camel.kafka.KEY");
        fieldsToHeadersTransform.configure(conf);
        ConnectRecord transformedCr =  fieldsToHeadersTransform.apply(new SourceRecord(null, null, "testTopic", null, null, INT32_SCHEMA, 100));
        assertEquals(100, transformedCr.headers().lastWithName("camel.kafka.KEY").value());
    }

    @Test
    public void fieldsWithoutCorrespondingHeadersTest() {
        Map<String, String> conf = new HashMap<>();
        conf.put("fields", "FROM,TO,CC,SUBJECT,BODY");
        conf.put("headers", "from,to");
        // key
        final FieldsToHeadersTransform.Key fieldsToHeadersTransformKey = new FieldsToHeadersTransform.Key();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            fieldsToHeadersTransformKey.configure(conf);
        });
        // value
        final FieldsToHeadersTransform fieldsToHeadersTransformValue = new FieldsToHeadersTransform.Value<>();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            fieldsToHeadersTransformValue.configure(conf);
        });
    }

    @Test
    public void headersWithoutCorrespondingFieldsTest() {
        Map<String, String> conf = new HashMap<>();
        conf.put("fields", "FROM");
        conf.put("headers", "from,to,cc,subject,body");
        // key
        final FieldsToHeadersTransform.Key fieldsToHeadersTransformKey = new FieldsToHeadersTransform.Key();
        fieldsToHeadersTransformKey.configure(conf);

        // value
        final FieldsToHeadersTransform fieldsToHeadersTransformValue = new FieldsToHeadersTransform.Value<>();
        fieldsToHeadersTransformValue.configure(conf);
    }

    @Test
    public void missingFieldInTheSchemaKeyTest() {
        FieldsToHeadersTransform.Key fieldsToHeadersTransform = new FieldsToHeadersTransform.Key();
        Schema keySchema = buildSchemaWithoutCC();
        Struct keyValue = buildValueWithoutCC(keySchema);

        fieldsToHeadersTransform.configure(buildConfig());
        ConnectRecord record = new SourceRecord(null, null, "testTopic", keySchema, keyValue, null, null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            fieldsToHeadersTransform.apply(record);
        });
    }

    @Test
    public void missingFieldInTheSchemaValueTest() {
        FieldsToHeadersTransform.Value fieldsToHeadersTransform = new FieldsToHeadersTransform.Value();
        Schema valueSchema = buildSchemaWithoutCC();
        Struct value = buildValueWithoutCC(valueSchema);

        fieldsToHeadersTransform.configure(buildConfig());
        ConnectRecord record = new SourceRecord(null, null, "testTopic", valueSchema, value);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            fieldsToHeadersTransform.apply(record);
        });
    }

    @Test
    public void missingFieldInTheRecordKeyWithSchemaTest() {
        FieldsToHeadersTransform.Key fieldsToHeadersTransform = new FieldsToHeadersTransform.Key();
        Schema keySchema = buildSchema();
        Struct keyValue = buildValueWithoutCC(keySchema);

        fieldsToHeadersTransform.configure(buildConfig());
        ConnectRecord record = new SourceRecord(null, null, "testTopic", keySchema, keyValue, null, null);

        ConnectRecord transformedRecord = fieldsToHeadersTransform.apply(record);
        Iterator<Header> headerIterator = transformedRecord.headers().allWithName("cc");
        assertTrue(headerIterator.hasNext());
        Header header = headerIterator.next();
        assertNotNull(header);
        assertNull(header.value());
        assertEquals(OPTIONAL_STRING_SCHEMA, header.schema());
        assertFalse(headerIterator.hasNext());
    }

    @Test
    public void missingFieldInTheRecordValueWithSchemaTest() {
        FieldsToHeadersTransform.Value fieldsToHeadersTransform = new FieldsToHeadersTransform.Value();
        Schema valueSchema = buildSchema();
        Struct value = buildValueWithoutCC(valueSchema);

        fieldsToHeadersTransform.configure(buildConfig());
        ConnectRecord record = new SourceRecord(null, null, "testTopic", valueSchema, value);

        ConnectRecord transformedRecord = fieldsToHeadersTransform.apply(record);
        Iterator<Header> headerIterator = transformedRecord.headers().allWithName("cc");
        assertTrue(headerIterator.hasNext());
        Header header = headerIterator.next();
        assertNotNull(header);
        assertNull(header.value());
        assertEquals(OPTIONAL_STRING_SCHEMA, header.schema());
        assertFalse(headerIterator.hasNext());

    }
    @Test
    public void missingFieldWithoutSchema() {

        TriConsumer<ConnectRecord, String, String> assertions = (record, headerName, expectedHeaderValue) -> {
            assertNull(record.keySchema());
            assertEquals("testTopic", record.topic());
            Iterator<Header> headerIterator = record.headers().allWithName(headerName);
            assertTrue(headerIterator.hasNext());
            Header header = headerIterator.next();
            assertEquals(expectedHeaderValue, header.value());
            assertNull(header.schema());
            assertFalse(headerIterator.hasNext());
        };

        Map<String, String> conf = new HashMap<>();
        conf.put("fields", "FROM,TO,CC");
        conf.put("headers", "from,to,cc");

        Map<String, String> message = new HashMap<>();
        message.put("FROM", "bob@example.com");
        message.put("TO", "alice@mail.com");

        // Test KEY
        FieldsToHeadersTransform.Key fieldsToHeadersTransformKey = new FieldsToHeadersTransform.Key();
        fieldsToHeadersTransformKey.configure(conf);

        final SinkRecord recordKey = new SinkRecord("testTopic", 0, null, message, null, null, 0);
        final ConnectRecord transformedRecordKey = fieldsToHeadersTransformKey.apply(recordKey);

        assertions.accept(transformedRecordKey, "from", message.get("FROM"));
        assertions.accept(transformedRecordKey, "to", message.get("TO"));
        Iterator<Header> headerIterator = transformedRecordKey.headers().allWithName("cc");
        assertTrue(headerIterator.hasNext());
        Header header = headerIterator.next();
        assertNotNull(header);
        assertNull(header.value());
        assertFalse(headerIterator.hasNext());

        // Test VALUE
        FieldsToHeadersTransform.Value fieldsToHeadersTransformValue = new FieldsToHeadersTransform.Value();
        fieldsToHeadersTransformValue.configure(conf);
        final SinkRecord recordValue = new SinkRecord("testTopic", 0, null, null, null, message, 0);
        final ConnectRecord transformedRecordValue = fieldsToHeadersTransformValue.apply(recordValue);

        assertions.accept(transformedRecordValue, "from", message.get("FROM"));
        assertions.accept(transformedRecordValue, "to", message.get("TO"));

        headerIterator = transformedRecordKey.headers().allWithName("cc");
        assertTrue(headerIterator.hasNext());
        header = headerIterator.next();
        assertNotNull(header);
        assertNull(header.value());
        assertFalse(headerIterator.hasNext());
    }

    private static Schema buildSchemaWithoutCC() {
        return SchemaBuilder.struct()
                .field("FROM", STRING_SCHEMA)
                .field("TO", STRING_SCHEMA)
                .field("SUBJECT", STRING_SCHEMA)
                .field("BODY", STRING_SCHEMA)
                .field("INT_EXAMPLE", INT32_SCHEMA)
                .field("BYTE_EXAMPLE", BYTES_SCHEMA)
                .field("BOOLEAN_EXAMPLE", BOOLEAN_SCHEMA)
                .field("FLOAT_EXAMPLE", FLOAT32_SCHEMA).build();
    }

    private static Struct buildValueWithoutCC(Schema schema) {
        byte[] attachment = new byte[32];
        new Random().nextBytes(attachment);
        return new Struct(schema)
                .put("FROM", "bob@example.com")
                .put("TO", "alice@mail.com")
                .put("SUBJECT", "Needs Attention")
                .put("BODY", "there is an issue that needs your attention")
                .put("INT_EXAMPLE", 34)
                .put("BYTE_EXAMPLE", attachment)
                .put("BOOLEAN_EXAMPLE", true)
                .put("FLOAT_EXAMPLE", 34.5F);
    }

    private static Schema buildSchema() {
        return SchemaBuilder.struct()
                .field("FROM", STRING_SCHEMA)
                .field("TO", STRING_SCHEMA)
                .field("CC", OPTIONAL_STRING_SCHEMA)
                .field("SUBJECT", STRING_SCHEMA)
                .field("BODY", STRING_SCHEMA)
                .field("INT_EXAMPLE", INT32_SCHEMA)
                .field("BYTE_EXAMPLE", BYTES_SCHEMA)
                .field("BOOLEAN_EXAMPLE", BOOLEAN_SCHEMA)
                .field("FLOAT_EXAMPLE", FLOAT32_SCHEMA).build();
    }

    private static Struct buildValue(Schema schema) {
        byte[] attachment = new byte[32];
        new Random().nextBytes(attachment);
        return new Struct(schema)
                .put("FROM", "bob@example.com")
                .put("TO", "alice@mail.com")
                .put("CC", "managers@enterprise.com")
                .put("SUBJECT", "Needs Attention")
                .put("BODY", "there is an issue that needs your attention")
                .put("INT_EXAMPLE", 34)
                .put("BYTE_EXAMPLE", attachment)
                .put("BOOLEAN_EXAMPLE", true)
                .put("FLOAT_EXAMPLE", 34.5F);
    }

    private static Map<String, String> buildConfig() {
        Map map = new HashMap();
        map.put("fields", fields.stream().collect(Collectors.joining(",")));
        map.put("headers", headers.stream().collect(Collectors.joining(",")));
        return map;
    }

    public ConnectRecord testWithSchema(FieldsToHeadersTransform fieldsToHeadersTransform, BiFunction<Schema, Struct, ConnectRecord> createRecord) {
        Schema valueSchema = buildSchema();

        Struct value = buildValue(valueSchema);
        fieldsToHeadersTransform.configure(buildConfig());
        ConnectRecord record = createRecord.apply(valueSchema, value);
        ConnectRecord transformedCr = fieldsToHeadersTransform.apply(record);

        assertEquals("testTopic", transformedCr.topic());
        Iterator<Header> headerIterator;
        Header header;
        for (int i = 0; i < fields.size(); i++) {
            headerIterator = transformedCr.headers().allWithName(headers.get(i));
            assertTrue(headerIterator.hasNext());
            header = headerIterator.next();
            assertEquals(value.get(fields.get(i)), header.value());
            assertEquals(valueSchema.field(fields.get(i)).schema(), header.schema());
            assertFalse(headerIterator.hasNext());
        }
        return transformedCr;
    }
}
