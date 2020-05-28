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

package org.apache.camel.kafkaconnector.common.services.kafkaconnect;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.services.kafka.EmbeddedKafkaService;
import org.apache.camel.kafkaconnector.common.services.kafka.KafkaService;
import org.apache.kafka.connect.runtime.AbstractStatus;
import org.apache.kafka.connect.runtime.ConnectorConfig;
import org.apache.kafka.connect.runtime.rest.entities.ConnectorStateInfo;
import org.apache.kafka.connect.util.clusters.EmbeddedConnectCluster;
import org.apache.kafka.test.TestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaConnectEmbedded implements KafkaConnectService {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaConnectEmbedded.class);
    private final EmbeddedConnectCluster cluster;
    private String connectorName;

    public KafkaConnectEmbedded(KafkaService kafkaService) {
        if (!(kafkaService instanceof EmbeddedKafkaService)) {
            throw new RuntimeException("Invalid Kafka service type: "
                    + (kafkaService == null ? "null" : kafkaService.getClass()));
        }

        this.cluster = ((EmbeddedKafkaService) kafkaService).getCluster();
    }

    private void convertProperty(Map<String, String> map, Object key, Object value) {
        map.put(String.valueOf(key), String.valueOf(value));
    }

    @Override
    public void initializeConnector(ConnectorPropertyFactory propertyFactory) {
        LOG.trace("Adding the new connector");
        Map<String, String> configuredProperties = new HashMap<>();

        propertyFactory.getProperties().forEach((k, v) -> convertProperty(configuredProperties, k, v));

        connectorName = configuredProperties.get(ConnectorConfig.NAME_CONFIG);
        LOG.info("Initializing connector {}", connectorName);
        cluster.configureConnector(connectorName, configuredProperties);
        LOG.trace("Added the new connector");
    }

    @Override
    public void initializeConnectorBlocking(ConnectorPropertyFactory propertyFactory, Integer expectedTaskNumber) throws InterruptedException {
        initializeConnector(propertyFactory);
        TestUtils.waitForCondition(() -> {
            ConnectorStateInfo connectorStateInfo = null;
            do {
                connectorStateInfo = cluster.connectorStatus(connectorName);
                Thread.sleep(20L);
            } while (connectorStateInfo == null);
            return  connectorStateInfo.tasks().size() >= expectedTaskNumber
                    && connectorStateInfo.connector().state().equals(AbstractStatus.State.RUNNING.toString())
                    && connectorStateInfo.tasks().stream().allMatch(s -> s.state().equals(AbstractStatus.State.RUNNING.toString()));
        }, 30000L, "The connector " + connectorName + " did not start within a reasonable time");
    }

    @Override
    public void stop() {
        if (connectorName != null) {
            try {
                LOG.info("Removing connector {}", connectorName);
                cluster.deleteConnector(connectorName);
            } finally {
                connectorName = null;
            }
        }
    }

    @Override
    public void start() {
        // NO-OP
    }
}
