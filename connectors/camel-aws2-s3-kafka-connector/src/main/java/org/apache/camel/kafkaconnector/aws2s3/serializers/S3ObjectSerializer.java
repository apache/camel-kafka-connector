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
package org.apache.camel.kafkaconnector.aws2s3.serializers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.core.ResponseInputStream;

public class S3ObjectSerializer implements Serializer<ResponseInputStream> {
    private static final Logger LOG = LoggerFactory.getLogger(S3ObjectSerializer.class);

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, ResponseInputStream data) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] byteArray = new byte[16384];

        try {
            while ((nRead = data.read(byteArray, 0, byteArray.length)) != -1) {
                buffer.write(byteArray, 0, nRead);
            }
        } catch (IOException e) {
            LOG.warn("I/O error while serializing data from or to topic {}: {} | {}", topic, e.getMessage(), e);
        }

        return buffer.toByteArray();
    }

    @Override
    public void close() {
    }

}
