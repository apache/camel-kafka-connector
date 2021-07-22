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
package org.apache.camel.kafkaconnector.aws2s3.transformers;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.camel.kafkaconnector.aws2s3.models.StorageRecord;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.header.ConnectHeaders;
import org.apache.kafka.connect.header.Headers;
import org.apache.kafka.connect.transforms.Transformation;

public class JSONToRecordTransforms<R extends ConnectRecord<R>> implements Transformation<R> {
    public static final String FIELD_KEY_CONFIG = "key";
    public static final ConfigDef CONFIG_DEF =
            new ConfigDef()
                    .define(
                            FIELD_KEY_CONFIG,
                            ConfigDef.Type.STRING,
                            null,
                            ConfigDef.Importance.MEDIUM,
                            "Add the key and the header to the record value");

    @Override
    public void configure(Map<String, ?> configs) {
    }

    @Override
    public R apply(R record) {
        String str = new String((byte[]) record.value());
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        StorageRecord storageRecord = gson.fromJson(str, StorageRecord.class);
        // Header format conversion
        Headers headers = new ConnectHeaders();
        for (int i = 0; i < storageRecord.headers.length; i++) {
            headers.add(storageRecord.headers[i].key, storageRecord.headers[i].value, null);
        }
        headers.forEach(h -> record.headers().add(h));
        return record.newRecord(
                record.topic(),
                record.kafkaPartition(),
                record.keySchema(),
                storageRecord.key,
                record.valueSchema(),
                storageRecord.body,
                record.timestamp(),
                headers);
    }

    @Override
    public void close() {
    }

    @Override
    public ConfigDef config() {
        return CONFIG_DEF;
    }
}
