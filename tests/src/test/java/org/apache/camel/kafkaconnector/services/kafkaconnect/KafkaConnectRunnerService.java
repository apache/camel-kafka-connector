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

package org.apache.camel.kafkaconnector.services.kafkaconnect;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.services.kafka.KafkaService;
import org.apache.kafka.connect.runtime.ConnectorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.fail;

public class KafkaConnectRunnerService implements KafkaConnectService {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaConnectRunnerService.class);

    private final KafkaConnectRunner kafkaConnectRunner;
    private final ExecutorService service = Executors.newCachedThreadPool();


    public KafkaConnectRunnerService(KafkaService kafkaService) {
        Objects.nonNull(kafkaService);

        LOG.debug("Connecting the Kafka Connect Runner to {}", kafkaService.getBootstrapServers());
        this.kafkaConnectRunner = new KafkaConnectRunner(kafkaService.getBootstrapServers());
    }


    private void checkInitializationState(KafkaConnectRunner.ConnectorInitState initState) {
        Objects.nonNull(initState);

        Throwable error = initState.getError();
        Map<String, String> configs = initState.getConfigs();
        String name = configs.get(ConnectorConfig.NAME_CONFIG);

        if (error != null) {
            LOG.error("Failed to create the connector {}: {}", name, error.getMessage(), error);
            throw new RuntimeException(String.format("Failed to create the connector %s: %s", name,
                    error.getMessage()), error);
        } else {
            if (initState.isCreated()) {
                LOG.debug("Created and initialized the connector {}", name);
            } else {
                LOG.debug("Failed to create connector {}", name);
                throw new RuntimeException(String.format("Failed to create connector %s", name));
            }
        }
    }

    private void checkInitializationState(KafkaConnectRunner.ConnectorInitState initState, CountDownLatch latch) {
        try {
            checkInitializationState(initState);
        } finally {
            latch.countDown();
        }
    }


    @Override
    public void initializeConnector(ConnectorPropertyFactory propertyFactory) throws ExecutionException, InterruptedException {
        kafkaConnectRunner.initializeConnector(propertyFactory, this::checkInitializationState);
    }

    @Override
    public void initializeConnectorBlocking(ConnectorPropertyFactory propertyFactory, Integer expectedTaskNumber) throws ExecutionException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        kafkaConnectRunner.initializeConnector(propertyFactory, this::checkInitializationState, latch);

        if (!latch.await(30, TimeUnit.SECONDS)) {
            fail("The connector did not start within a reasonable time");
        }
    }

    public void stop() {
        kafkaConnectRunner.stop();
        try {
            service.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOG.warn("The test was interrupted while executing");
        }
    }

    public void start() {
        CountDownLatch latch = new CountDownLatch(1);
        service.submit(() -> kafkaConnectRunner.run(latch));

        try {
            if (!latch.await(30, TimeUnit.SECONDS)) {
                LOG.warn("The Kafka Connect Runner timed out while initializing");
                throw new RuntimeException("The Kafka Connect Runner timed out while initializing");
            }
        } catch (InterruptedException e) {
            LOG.error("The test was interrupted while executing");
        }
    }
}
