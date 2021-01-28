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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.transforms.Transformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.kafka.connect.transforms.util.Requirements.requireMapOrNull;
import static org.apache.kafka.connect.transforms.util.Requirements.requireStructOrNull;

public abstract class FieldsToHeadersTransform<R extends ConnectRecord<R>> implements Transformation<R> {

    private static final Logger LOG = LoggerFactory.getLogger(FieldsToHeadersTransform.class);

    private static final String PURPOSE = "fields extraction to headers";
    private static final String FIELDS_CONFIG = "fields";
    private static final String HEADERS_CONFIG = "headers";

    public static final ConfigDef CONFIG_DEF = new ConfigDef()
            .define(FIELDS_CONFIG, ConfigDef.Type.LIST, ConfigDef.NO_DEFAULT_VALUE, ConfigDef.Importance.MEDIUM, "Fields names to extract and set to headers")
            .define(HEADERS_CONFIG, ConfigDef.Type.LIST, ConfigDef.NO_DEFAULT_VALUE, ConfigDef.Importance.MEDIUM, "Headers names to set with extracted fields");


    private List<String> fields;

    private List<String> headers;



    protected abstract Schema operatingSchema(R record);

    protected abstract Object operatingValue(R record);


    @Override
    public R apply(R r) {
        RecordValue value = createRecordValue(r);
        Schema currentSchema;
        Object currentValue;
        for (int i = 0; i < fields.size(); i++) {
            currentSchema = value.getFieldSchema(fields.get(i));
            currentValue = value.getFieldValue(fields.get(i));
            r.headers().add(headers.get(i), currentValue, currentSchema);
        }
        return r;
    }

    @Override
    public ConfigDef config() {
        return CONFIG_DEF;
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> map) {
        Map<String, Object> parsedConfig = CONFIG_DEF.parse(map);
        fields =  (List<String>) parsedConfig.getOrDefault(FIELDS_CONFIG, new ArrayList<>());
        headers = (List<String>) parsedConfig.getOrDefault(HEADERS_CONFIG, new ArrayList<>());
        validateConfig();
    }

    private void validateConfig() {

        boolean validFields  = fields.stream().allMatch(nef -> nef != null && !nef.trim().isEmpty());
        boolean validHeaders = headers.stream().allMatch(nef -> nef != null && !nef.trim().isEmpty());

        if (!(validFields && validHeaders)) {
            throw new IllegalArgumentException("headers and fields configuration properties cannot be null or contain empty elements.");
        }
        if (fields.size() > headers.size()) {
            String fieldsWithoutCorrespondingHeaders = fields.subList(headers.size(), fields.size()).stream().collect(Collectors.joining(","));
            throw new IllegalArgumentException("There is no corresponding header(s) configured for the following field(s): " + fieldsWithoutCorrespondingHeaders);
        }
        if (headers.size() > fields.size()) {
            String headersWithoutCorrespondingFields = headers.subList(fields.size(), headers.size()).stream().collect(Collectors.joining(","));
            LOG.warn("There is no corresponding header(s) for the following field(s): {} ", headersWithoutCorrespondingFields);
        }

    }


    private RecordValue createRecordValue(R r) {
        final Schema schema = operatingSchema(r);
        if (schema == null) {
            return new MapRecordValue(requireMapOrNull(operatingValue(r), PURPOSE));
        }
        return new StructRecordValue(requireStructOrNull(operatingValue(r), PURPOSE), schema);
    }

    public static class Key<R extends ConnectRecord<R>> extends FieldsToHeadersTransform<R> {

        @Override
        protected Schema operatingSchema(R record) {
            return record.keySchema();
        }

        @Override
        protected Object operatingValue(R record) {
            return record.key();
        }
    }

    public static class Value<R extends ConnectRecord<R>> extends FieldsToHeadersTransform<R> {

        @Override
        protected Schema operatingSchema(R record) {
            return record.valueSchema();
        }

        @Override
        protected Object operatingValue(R record) {
            return record.value();
        }
    }

    public interface RecordValue {

        Object getFieldValue(String fieldName);

        Schema getFieldSchema(String fieldName);
    }

    public class MapRecordValue implements RecordValue {

        private Map<String, Object> map;

        public MapRecordValue(Map<String, Object> map) {
            this.map = map;
        }

        public Object getFieldValue(String fieldName) {
            return map == null ? null : map.get(fieldName);
        }

        public Schema getFieldSchema(String fieldName) {
            return null;
        }
    }

    public class StructRecordValue implements RecordValue {

        private Struct struct;

        private Schema schema;

        public StructRecordValue(Struct struct, Schema schema) {
            this.struct = struct;
            this.schema = schema;
        }

        public Object getFieldValue(String fieldName) {
            return struct.get(fieldName);
        }

        public Schema getFieldSchema(String fieldName) {
            Field field = schema.field(fieldName);
            if (field == null) {
                throw new IllegalArgumentException("Unknown field: " + fieldName);
            }
            return field.schema();
        }
    }
}
