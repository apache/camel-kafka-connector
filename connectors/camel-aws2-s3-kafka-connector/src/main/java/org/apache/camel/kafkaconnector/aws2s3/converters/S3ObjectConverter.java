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
package org.apache.camel.kafkaconnector.aws2s3.converters;

import java.util.Map;

import org.apache.camel.kafkaconnector.aws2s3.serializers.S3ObjectSerializer;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaAndValue;
import org.apache.kafka.connect.storage.Converter;

import software.amazon.awssdk.core.ResponseInputStream;

public class S3ObjectConverter implements Converter {

    private final S3ObjectSerializer serializer = new S3ObjectSerializer();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] fromConnectData(String topic, Schema schema, Object value) {
        return serializer.serialize(topic, (ResponseInputStream) value);
    }

    @Override
    public SchemaAndValue toConnectData(String arg0, byte[] arg1) {
        return null;
    }

}
