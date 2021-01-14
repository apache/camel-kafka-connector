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

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.json.JsonConverter;
import org.apache.kafka.connect.json.JsonConverterConfig;
import org.apache.kafka.connect.storage.ConverterConfig;
import org.apache.kafka.connect.storage.ConverterType;
import org.apache.kafka.connect.transforms.Transformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchemaAndStructToJsonTransform<R extends ConnectRecord<R>> implements Transformation<R> {
    private static final Logger LOG = LoggerFactory.getLogger(SchemaAndStructToJsonTransform.class);

    private JsonConverter jsonConverter;

    @Override
    public R apply(R r) {
        LOG.debug("Incoming record: {}", r);

        if (r.value() != null && r.valueSchema() != null) {
            byte[] json = jsonConverter.fromConnectData(r.topic(), r.valueSchema(), r.value());

            if (json == null) {
                LOG.warn("No record was converted as part of this transformation, resulting json byte[] was null.");
                return r;
            }

            LOG.debug("Json created: {}", new String(json));

            return r.newRecord(r.topic(), r.kafkaPartition(), r.keySchema(), r.key(),
                    Schema.BYTES_SCHEMA, json, r.timestamp());
        } else {
            LOG.debug("Incoming record with a null value or a null schema, nothing to be done.");
            return r;
        }
    }

    @Override
    public ConfigDef config() {
        return JsonConverterConfig.configDef();
    }

    @Override
    public void close() {
        //NOOP
    }

    @Override
    public void configure(Map<String, ?> configs) {
        jsonConverter = new JsonConverter();
        Map<String, Object> conf = new HashMap<>(configs);
        conf.put(ConverterConfig.TYPE_CONFIG, ConverterType.VALUE.getName());
        jsonConverter.configure(conf);
    }
}