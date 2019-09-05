/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.camel.kakfaconnector;

import org.apache.kafka.connect.runtime.standalone.StandaloneConfig;

import java.util.Properties;


/**
 * A set of properties for the Kafka connect runtime that match the standard configuration
 * used for the standalone CLI connect runtime.
 */
public class DefaultKafkaConnectPropertyProducer implements KafkaConnectPropertyProducer {
    private final String bootstrapServer;

    /**
     * Constructs the properties using the given bootstrap server
     * @param bootstrapServer the address of the server in the format
     *                       PLAINTEXT://${address}:${port}
     */
    public DefaultKafkaConnectPropertyProducer(String bootstrapServer) {
        this.bootstrapServer = bootstrapServer;
    }

    @Override
    public Properties getProperties() {
        Properties props = new Properties();

        props.put(StandaloneConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(StandaloneConfig.KEY_CONVERTER_CLASS_CONFIG, "org.apache.kafka.connect.json.JsonConverter");
        props.put(StandaloneConfig.VALUE_CONVERTER_CLASS_CONFIG, "org.apache.kafka.connect.json.JsonConverter");
        props.put(StandaloneConfig.OFFSET_STORAGE_FILE_FILENAME_CONFIG, this.getClass().getResource("/").getPath() + "connect.offsets");
        props.put(StandaloneConfig.OFFSET_COMMIT_INTERVAL_MS_CONFIG, "10000");
        props.put(StandaloneConfig.PLUGIN_PATH_CONFIG, "");
        props.put(StandaloneConfig.REST_PORT_CONFIG, "9999");
        return props;
    }
}
