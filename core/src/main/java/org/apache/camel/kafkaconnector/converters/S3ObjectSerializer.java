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
package org.apache.camel.kafkaconnector.converters;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.apache.kafka.common.serialization.Serializer;

public class S3ObjectSerializer implements Serializer<S3ObjectInputStream> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, S3ObjectInputStream data) {
        InputStream is = data.getDelegateStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] byteArray = new byte[16384];

        try {
            while ((nRead = is.read(byteArray, 0, byteArray.length)) != -1) {
                buffer.write(byteArray, 0, nRead);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return buffer.toByteArray();
    }

    @Override
    public void close() {
    }

}
