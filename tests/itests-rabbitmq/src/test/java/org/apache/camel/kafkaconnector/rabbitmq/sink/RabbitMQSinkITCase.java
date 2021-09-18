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
package org.apache.camel.kafkaconnector.rabbitmq.sink;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.rabbitmq.clients.RabbitMQClient;
import org.apache.camel.test.infra.rabbitmq.services.RabbitMQService;
import org.apache.camel.test.infra.rabbitmq.services.RabbitMQServiceFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Disabled("Until we have a rabbitmq sink kamelet see: https://github.com/apache/camel-kamelets/issues/45")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RabbitMQSinkITCase extends CamelSinkTestSupport {
    @RegisterExtension
    public static RabbitMQService rabbitmqService = RabbitMQServiceFactory.createService();

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQSinkITCase.class);
    private static final String DEFAULT_RABBITMQ_QUEUE = "Q.test.kafka.import";

    private String topicName;
    private RabbitMQClient rabbitMQClient;
    private int received;
    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-rabbitmq-sink-kafka-connector"};
    }

    @BeforeEach
    public void setUp() throws Exception {
        topicName = getTopicForTest(this);
        received = 0;

        rabbitMQClient =  new RabbitMQClient(rabbitmqService.getAmqpUrl());
        rabbitMQClient.createQueue(DEFAULT_RABBITMQ_QUEUE);
        rabbitMQClient.start();
    }

    @AfterEach
    public void tearDown() {
        rabbitMQClient.stop();
    }

    @Override
    protected void consumeMessages(CountDownLatch latch) {
        DeliverCallback deliveryCallback = (consumerTag, delivery) -> {
            if (!this.checkRecord(delivery)) {
                latch.countDown();
            }
        };

        try {
            rabbitMQClient.receive(DEFAULT_RABBITMQ_QUEUE, deliveryCallback);
        } catch (Exception e) {
            LOG.error("RabbitMQ test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (latch.await(15, TimeUnit.SECONDS)) {
            assertEquals(received, expect, "Didn't process the expected amount of messages: " + received + " != " + expect);
        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }

    private boolean checkRecord(Delivery rabbitMQDelivery) {
        String message = new String(rabbitMQDelivery.getBody(), StandardCharsets.UTF_8);
        LOG.debug("Received: {}", message);

        received++;

        if (received == expect) {
            return false;
        }

        return true;
    }

    @Test
    @Timeout(90)
    public void testSource() throws Exception {
        ConnectorPropertyFactory factory = CamelRabbitMQPropertyFactory
                .basic()
                .withTopics(topicName)
                .withUsername(rabbitmqService.connectionProperties().username())
                .withPassword(rabbitmqService.connectionProperties().password())
                .withAutoDelete(false)
                .withQueue(DEFAULT_RABBITMQ_QUEUE)
                .withRoutingKey(DEFAULT_RABBITMQ_QUEUE)
                .withSkipExchangeDeclare(true)
                .withSkipQueueBind(true)
                .withHostname(rabbitmqService.connectionProperties().hostname())
                .withPortNumber(rabbitmqService.connectionProperties().port());

        runTest(factory, topicName, expect);
    }
}
