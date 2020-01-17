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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.config.ConfigException;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.transforms.Transformation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CamelTypeConverterTransformTest {

    @Test
    public void testIfItConvertsConnectRecordCorrectly() {
        final SourceRecord connectRecord = new SourceRecord(Collections.emptyMap(), Collections.emptyMap(), "topic", Schema.STRING_SCHEMA, "1234", Schema.STRING_SCHEMA, "TRUE");

        final Map<String, Object> propsForKeySmt = new HashMap<>();
        propsForKeySmt.put(CamelTypeConverterTransform.FIELD_TARGET_TYPE_CONFIG, Integer.class.getName());

        final Map<String, Object> propsForValueSmt = new HashMap<>();
        propsForValueSmt.put(CamelTypeConverterTransform.FIELD_TARGET_TYPE_CONFIG, "java.lang.Boolean");

        final Transformation<SourceRecord> transformationKey = new CamelTypeConverterTransform.Key<>();
        final Transformation<SourceRecord> transformationValue = new CamelTypeConverterTransform.Value<>();

        transformationKey.configure(propsForKeySmt);
        transformationValue.configure(propsForValueSmt);

        final SourceRecord transformedKeySourceRecord = transformationKey.apply(connectRecord);
        final SourceRecord transformedValueSourceRecord = transformationValue.apply(connectRecord);

        assertEquals(1234, transformedKeySourceRecord.key());
        assertEquals(Schema.INT32_SCHEMA, transformedKeySourceRecord.keySchema());

        assertEquals(true, transformedValueSourceRecord.value());
        assertEquals(Schema.BOOLEAN_SCHEMA, transformedValueSourceRecord.valueSchema());
    }

    @Test
    public void testIfHandlesTypeConvertersFromCamelComponents() {
        // we know we have a type converter from struct to map in dbz component, so we use this for testing
        final Schema schema = SchemaBuilder.struct()
                .field("id", Schema.INT32_SCHEMA)
                .field("name", Schema.STRING_SCHEMA)
                .field("valid", Schema.BOOLEAN_SCHEMA)
                .field("extra", Schema.STRING_SCHEMA)
                .build();

        final Struct value = new Struct(schema);
        value.put("id", 12);
        value.put("name", "test-name");
        value.put("valid", true);

        final SourceRecord connectRecord = new SourceRecord(Collections.emptyMap(), Collections.emptyMap(), "topic", Schema.STRING_SCHEMA, "1234", schema, value);

        final Map<String, Object> props = new HashMap<>();
        props.put(CamelTypeConverterTransform.FIELD_TARGET_TYPE_CONFIG, Map.class.getName());

        final Transformation<SourceRecord> transformationValue = new CamelTypeConverterTransform.Value<>();

        transformationValue.configure(props);

        final SourceRecord transformedValueSourceRecord = transformationValue.apply(connectRecord);

        // assert
        assertNotNull(transformedValueSourceRecord);

        final Map<String, Object> outputValue = (Map<String, Object>) transformedValueSourceRecord.value();

        assertEquals(12, outputValue.get("id"));
        assertEquals("test-name", outputValue.get("name"));
        assertNull(outputValue.get("extra"));
        assertTrue((boolean)outputValue.get("valid"));
        assertEquals(Schema.Type.MAP, transformedValueSourceRecord.valueSchema().type());
    }

    @Test
    public void testIfItCanHandleEmptyKeyProps() {
        final Transformation<SourceRecord> transformationKey = new CamelTypeConverterTransform.Key<>();

        final Map<String, Object> props = new HashMap<>();
        props.put(CamelTypeConverterTransform.FIELD_TARGET_TYPE_CONFIG, Map.class.getName());

        assertThrows(ConfigException.class, () -> transformationKey.configure(Collections.emptyMap()));
    }

}