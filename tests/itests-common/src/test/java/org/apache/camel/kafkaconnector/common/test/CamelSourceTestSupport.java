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

package org.apache.camel.kafkaconnector.common.test;

import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CamelSourceTestSupport extends AbstractKafkaTest {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceTestSupport.class);

    protected abstract void produceTestData();

    protected abstract void verifyMessages(TestMessageConsumer<?> consumer);

    /**
     * A simple blocking test runner that follows the steps: initialize, start producer, consume messages, verify results
     *
     * @param connectorPropertyFactory A factory for connector properties
     * @param topic the topic to send the messages to
     * @param count the number of messages to send
     * @throws Exception For test-specific exceptions
     */
    public void runTest(ConnectorPropertyFactory connectorPropertyFactory, String topic, int count) throws ExecutionException, InterruptedException {
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        StringMessageConsumer consumer = new StringMessageConsumer(kafkaClient, topic, count);

        runTest(connectorPropertyFactory, consumer);
    }


    /**
     * A simple blocking test runner that follows the steps: initialize, start producer, consume messages, verify results
     *
     * @param connectorPropertyFactory A factory for connector properties
     * @param consumer A Kafka consumer consumer for the test messages
     * @throws Exception For test-specific exceptions
     */
    public void runTest(ConnectorPropertyFactory connectorPropertyFactory, TestMessageConsumer<?> consumer) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();
        LOG.debug("Initialized the connector and put the data for the test execution");
        getKafkaConnectService().initializeConnectorBlocking(connectorPropertyFactory, 1);

        LOG.debug("Producing test data to be collected by the connector and sent to Kafka");
        produceTestData();

        LOG.debug("Creating the Kafka consumer ...");
        consumer.consumeMessages();
        LOG.debug("Ran the Kafka consumer ...");

        LOG.debug("Verifying messages");
        verifyMessages(consumer);
        LOG.debug("Verified messages");
    }

}
