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
package org.apache.camel.kafkaconnector.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.apache.http.client.methods.HttpDelete;
import org.apache.kafka.connect.data.Decimal;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SchemaHelperTest {

    @Test
    public void testBuildSchemaBuilderForAllKnownTypes() {
        assertEquals(SchemaBuilder.STRING_SCHEMA, SchemaHelper.buildSchemaBuilderForType("test").build());
        assertEquals(SchemaBuilder.BOOLEAN_SCHEMA, SchemaHelper.buildSchemaBuilderForType(false).build());
        assertEquals(SchemaBuilder.INT8_SCHEMA, SchemaHelper.buildSchemaBuilderForType(new Byte("1")).build());
        assertEquals(SchemaBuilder.INT16_SCHEMA, SchemaHelper.buildSchemaBuilderForType(new Short("1")).build());
        assertEquals(SchemaBuilder.INT32_SCHEMA, SchemaHelper.buildSchemaBuilderForType(1).build());
        assertEquals(SchemaBuilder.INT64_SCHEMA, SchemaHelper.buildSchemaBuilderForType(1L).build());
        assertEquals(SchemaBuilder.FLOAT32_SCHEMA, SchemaHelper.buildSchemaBuilderForType(new Float("1")).build());
        assertEquals(SchemaBuilder.FLOAT64_SCHEMA, SchemaHelper.buildSchemaBuilderForType(new Double("1")).build());
        assertEquals(SchemaBuilder.BYTES_SCHEMA, SchemaHelper.buildSchemaBuilderForType("test".getBytes()).build());
    }

    @Test
    public void testBuildSchemaBuilderForAllSpecialTypes() {
        // test map
        final Map<String, Object> inputMap = new LinkedHashMap<>();
        inputMap.put("test", "value being tested");

        assertEquals(Schema.Type.MAP, SchemaHelper.buildSchemaBuilderForType(inputMap).type());

        // test list
        final List<String> inputList = Arrays.asList("test1", "test2");
        assertEquals(Schema.Type.ARRAY, SchemaHelper.buildSchemaBuilderForType(inputList).type());

        // test date/time
        final Date date = new Date();
        assertEquals(org.apache.kafka.connect.data.Date.SCHEMA, SchemaHelper.buildSchemaBuilderForType(date).build());

        // test big decimal
        final BigDecimal bigDecimal = new BigDecimal("1");
        assertEquals(Decimal.schema(bigDecimal.scale()), SchemaHelper.buildSchemaBuilderForType(bigDecimal).build());

        // test struct
        final Schema schema = SchemaBuilder.struct()
                .field("id", Schema.INT32_SCHEMA)
                .build();
        final Struct structValue = new Struct(schema);
        structValue.put("id", 1);
        assertEquals(Schema.Type.STRUCT, SchemaHelper.buildSchemaBuilderForType(structValue).type());

        // finally how to handle if we have no idea about the value
        final S3ObjectInputStream s3ObjectInputStream = new S3ObjectInputStream(System.in, new HttpDelete());
        assertEquals(Schema.Type.BYTES, SchemaHelper.buildSchemaBuilderForType(s3ObjectInputStream).type());
    }
}