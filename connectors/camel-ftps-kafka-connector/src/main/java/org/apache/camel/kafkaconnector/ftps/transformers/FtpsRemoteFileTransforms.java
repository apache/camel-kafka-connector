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
package org.apache.camel.kafkaconnector.ftps.transformers;

import java.util.Map;

import org.apache.camel.component.file.remote.RemoteFile;
import org.apache.camel.kafkaconnector.utils.SchemaHelper;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.transforms.Transformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FtpsRemoteFileTransforms <R extends ConnectRecord<R>> implements Transformation<R> {
    public static final String FIELD_KEY_CONFIG = "key";
    public static final ConfigDef CONFIG_DEF = new ConfigDef()
            .define(FIELD_KEY_CONFIG, ConfigDef.Type.STRING, null, ConfigDef.Importance.MEDIUM,
                    "Transforms Remote File to String");

    private static final Logger LOG = LoggerFactory.getLogger(FtpsRemoteFileTransforms.class);

    @Override
    public R apply(R r) {
        Object value = r.value();

        if (r.value() instanceof RemoteFile) {
            LOG.debug("Converting record from RemoteFile to text");
            RemoteFile message = (RemoteFile) r.value();

            LOG.debug("Received text: {}", message.getBody());

            return r.newRecord(r.topic(), r.kafkaPartition(), null, r.key(),
                    SchemaHelper.buildSchemaBuilderForType(message.getBody()), message.getBody(), r.timestamp());

        } else {
            LOG.debug("Unexpected message type: {}", r.value().getClass());

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
