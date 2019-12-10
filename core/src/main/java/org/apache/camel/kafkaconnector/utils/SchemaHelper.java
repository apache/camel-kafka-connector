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
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.kafka.connect.data.Decimal;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Values;
import org.apache.kafka.connect.transforms.util.SchemaUtil;

public final class SchemaHelper {

    /**
     * Try to build a {@link SchemaBuilder} for a value of type {@link Object}
     * However, this will only build the schema only for known types, in case it can not return the precise SchemaBuilder type
     * it will return an optional {@link SchemaBuilder.BYTE}
     * @param value to return the SchemaBuilder for
     *
     * @return {@link SchemaBuilder} instance
     */
    public static SchemaBuilder buildSchemaBuilderForType(final Object value) {
        // gracefully try to infer the schema
        final Schema knownSchema = Values.inferSchema(value);

        if (knownSchema == null) {
            // let's now check for other types
            if (value instanceof Date) {
                return org.apache.kafka.connect.data.Date.builder();
            }
            if (value instanceof BigDecimal) {
                return Decimal.builder(((BigDecimal) value).scale());
            }
            // we re-check map and list since inferSchema function is not tolerant against map and list
            // for now we rely on inferSchema, however it makes some point to build a specific inferSchema method only for this connector
            if (value instanceof Map) {
                return new SchemaBuilder(Schema.Type.MAP);
            }
            if (value instanceof List) {
                return new SchemaBuilder(Schema.Type.ARRAY);
            }
            // if we do not fine any of schema out of the above, we just return an an optional byte schema
            return SchemaBuilder.bytes().optional();
        }
        return SchemaUtil.copySchemaBasics(knownSchema);
    }
}
