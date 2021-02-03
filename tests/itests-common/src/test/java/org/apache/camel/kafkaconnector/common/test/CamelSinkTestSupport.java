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

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class CamelSinkTestSupport extends AbstractKafkaTest {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkTestSupport.class);

    protected abstract Map<String, String> messageHeaders(String text, int current);

    protected String testMessageContent(int current) {
        return  "Sink test message " + current;
    }

    protected void produceMessages(String topicName, int count)  {
        try {
            KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

            for (int i = 0; i < count; i++) {
                String message = testMessageContent(i);
                Map<String, String> headers = messageHeaders(message, i);

                if (headers == null) {
                    kafkaClient.produce(topicName, message);
                } else {
                    kafkaClient.produce(topicName, message, headers);
                }
            }
        } catch (Throwable t) {
            LOG.error("Unable to publish messages to the broker: {}", t.getMessage(), t);
            fail(String.format("Unable to publish messages to the broker: %s", t.getMessage()));
        }
    }

    /**
     * A simple test runner that follows the steps: initialize, start consumer, produce messages, verify results
     *
     * @param connectorPropertyFactory A factory for connector properties
     * @param topic the topic to send the messages to
     * @param count the number of messages to send
     * @throws Exception For test-specific exceptions
     */
    protected void runTest(ConnectorPropertyFactory connectorPropertyFactory, String topic, int count) throws Exception {
        runTest(connectorPropertyFactory, () -> produceMessages(topic, count));
    }

    /**
     * A more flexible test runner that can use a custom producer of test messages
     * @param connectorPropertyFactory a factory for connector properties
     * @param producer the test message producer
     * @throws ExecutionException
     * @throws InterruptedException
     */
    protected void runTest(ConnectorPropertyFactory connectorPropertyFactory, TestMessageProducer producer) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnectorBlocking(connectorPropertyFactory, 1);

        LOG.debug("Creating the consumer ...");
        ExecutorService service = Executors.newCachedThreadPool();

        CountDownLatch latch = new CountDownLatch(1);
        service.submit(() -> consumeMessages(latch));

        producer.producerMessages();

        LOG.debug("Waiting for the messages to be processed");
        service.shutdown();

        LOG.debug("Waiting for the test to complete");
        verifyMessages(latch);
    }


    protected boolean waitForData() {
        try {
            Thread.sleep(Duration.ofSeconds(1).toMillis());
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            return false;
        }
    }

    protected abstract void consumeMessages(CountDownLatch latch);

    protected abstract void verifyMessages(CountDownLatch latch) throws InterruptedException;
}
