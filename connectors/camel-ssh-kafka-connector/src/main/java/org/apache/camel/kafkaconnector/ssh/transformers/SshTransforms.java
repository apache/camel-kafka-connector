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
package org.apache.camel.kafkaconnector.ssh.transformers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.camel.kafkaconnector.utils.SchemaHelper;
import org.apache.commons.io.IOUtils;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.transforms.Transformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SshTransforms<R extends ConnectRecord<R>> implements Transformation<R> {
    public static final String FIELD_KEY_CONFIG = "key";
    public static final ConfigDef CONFIG_DEF = new ConfigDef().define(FIELD_KEY_CONFIG, ConfigDef.Type.STRING, null, ConfigDef.Importance.MEDIUM,
                                                                      "Transforms String-based content from Kafka into a map");

    private static final Logger LOG = LoggerFactory.getLogger(SshTransforms.class);

    @Override
    public R apply(R r) {
        Object value = r.value();

        if (value instanceof ByteArrayInputStream) {
            LOG.debug("Converting record from Ssh Body Result to text");
            ByteArrayInputStream message = (ByteArrayInputStream) value;
            String m = null;
            try {
                m = IOUtils.toString(message, Charset.defaultCharset());
            } catch (IOException e) {
                LOG.error("Input/output error while transforming the SSH value of type {}: {}", value.getClass(),
                        e.getMessage(), e);
            }

            return r.newRecord(r.topic(), r.kafkaPartition(), null, r.key(), SchemaHelper.buildSchemaBuilderForType(m), m, r.timestamp());

        } else {
            LOG.debug("Unexpected message type: {}", value == null ? "null instance" : value.getClass());

            return r;
        }
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

    }
}
