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

package org.apache.camel.kafkaconnector.services.kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.PluginPathHelper;
import org.apache.kafka.connect.runtime.WorkerConfig;
import org.apache.kafka.connect.util.clusters.EmbeddedConnectCluster;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmbeddedKafkaService implements KafkaService {
    private static final Logger LOG = LoggerFactory.getLogger(EmbeddedKafkaService.class);
    private static final long OFFSET_COMMIT_INTERVAL_MS = TimeUnit.SECONDS.toMillis(30);

    private EmbeddedConnectCluster cluster;

    public EmbeddedKafkaService() {
        LOG.info("Creating the embedded Kafka connect instance");
        EmbeddedConnectCluster.Builder builder = new EmbeddedConnectCluster.Builder();

        Properties brokerProps = new Properties();
        brokerProps.put("auto.create.topics.enable", String.valueOf(true));

        Map<String, String> workerProps = new HashMap<>();
        workerProps.put(WorkerConfig.OFFSET_COMMIT_INTERVAL_MS_CONFIG, String.valueOf(OFFSET_COMMIT_INTERVAL_MS));

        String pluginPaths = PluginPathHelper.pluginPaths();

        LOG.info("Adding the returned directories to the plugin path. This may take A VERY long time to complete");
        workerProps.put(WorkerConfig.PLUGIN_PATH_CONFIG, pluginPaths);

        LOG.info("Building the embedded Kafka connect instance");
        this.cluster = builder
                .name("connect-cluster")
                .numWorkers(1)
                .numBrokers(1)
                .brokerProps(brokerProps)
                .workerProps(workerProps)
                .maskExitProcedures(true)
                .build();

        LOG.info("Built the embedded Kafka connect instance");
    }



    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {

    }

    @Override
    public String getBootstrapServers() {
        return cluster.kafka().bootstrapServers();
    }

    @Override
    public void initialize() {
        cluster.start();
        LOG.info("Kafka bootstrap server running at address {}", getBootstrapServers());
    }

    public EmbeddedConnectCluster getCluster() {
        return cluster;
    }
}
