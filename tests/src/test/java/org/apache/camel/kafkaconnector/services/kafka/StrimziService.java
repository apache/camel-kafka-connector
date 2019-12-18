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

import org.apache.camel.kafkaconnector.ContainerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.Network;

public class StrimziService implements KafkaService {
    private static final Logger LOG = LoggerFactory.getLogger(StrimziService.class);
    private final ZookeeperContainer zookeeperContainer;
    private final StrimziContainer strimziContainer;

    public StrimziService() {
        Network network = Network.newNetwork();

        zookeeperContainer = new ZookeeperContainer(network, "zookeeper");
        strimziContainer = new StrimziContainer(network, "strimzi");
    }

    private Integer getKafkaPort() {
        return strimziContainer.getKafkaPort();
    }

    @Override
    public String getBootstrapServers() {
        return "localhost:" + getKafkaPort();
    }

    @Override
    public void initialize() {
        zookeeperContainer.start();
        ContainerUtil.waitForInitialization(zookeeperContainer);

        String zookeeperConnect = "zookeeper:" + zookeeperContainer.getZookeeperPort();
        LOG.info("Apache Zookeeper running at address {}", zookeeperConnect);

        strimziContainer.start();
        ContainerUtil.waitForInitialization(strimziContainer);

        LOG.info("Kafka bootstrap server running at address {}", getBootstrapServers());
    }
}
