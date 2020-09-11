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

import org.apache.camel.kafkaconnector.aws2s3.serializers.S3ObjectSerializer;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.transforms.Transformation;
import software.amazon.awssdk.core.ResponseInputStream;

public class S3ObjectTransformers<R extends ConnectRecord<R>> implements Transformation<R> {

    public static final ConfigDef CONFIG_DEF = new ConfigDef().define("test", ConfigDef.Type.STRING, "test", ConfigDef.Importance.MEDIUM,
                                                                      "Transform the content of a bucket into a string ");

    private final S3ObjectSerializer serializer = new S3ObjectSerializer();

    @Override
    public void configure(Map<String, ?> configs) {
    }

    @Override
    public R apply(R record) {
        byte[] v = serializer.serialize(record.topic(), (ResponseInputStream)record.value());
        String finalValue = new String(v);
        return record.newRecord(record.topic(), record.kafkaPartition(), null, record.key(), Schema.STRING_SCHEMA, finalValue, record.timestamp());
    }

    @Override
    public void close() {
    }

    @Override
    public ConfigDef config() {
        return CONFIG_DEF;
    }

}
