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
package org.apache.camel.kafkaconnector.rabbitmq.source;

import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.kafkaconnector.rabbitmq.clients.RabbitMQClient;
import org.apache.camel.kafkaconnector.rabbitmq.services.RabbitMQService;
import org.apache.camel.kafkaconnector.rabbitmq.services.RabbitMQServiceFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class RabbitMQSourceITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static RabbitMQService rabbitmqService = RabbitMQServiceFactory.createService();

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQSourceITCase.class);
    private static final String DEFAULT_RABBITMQ_QUEUE = "Q.test.kafka.import";

    private RabbitMQClient rabbitMQClient;
    private int received;
    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-rabbitmq-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        received = 0;
        rabbitMQClient =  new RabbitMQClient(rabbitmqService.getAmqpUrl());
    }

    private <T> boolean checkRecord(ConsumerRecord<String, T> record) {
        LOG.debug("Received: {}", record.value());
        received++;

        if (received == expect) {
            return false;
        }

        return true;
    }

    public void runBasicStringTest(ConnectorPropertyFactory connectorPropertyFactory) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnectorBlocking(connectorPropertyFactory, 1);
        rabbitMQClient.createQueue(DEFAULT_RABBITMQ_QUEUE);

        for (int i = 0; i < expect; i++) {
            rabbitMQClient.send(DEFAULT_RABBITMQ_QUEUE, "Test string message");
        }

        LOG.debug("Creating the kafka consumer ...");
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        kafkaClient.consume(TestUtils.getDefaultTestTopic(this.getClass()), this::checkRecord);
        LOG.debug("Created the kafka consumer ...");

        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }

    @Test
    @Timeout(90)
    public void testSource() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory factory = CamelRabbitMQPropertyFactory
                .basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withUrl("")
                .append("username", rabbitmqService.connectionProperties().username())
                .append("password", rabbitmqService.connectionProperties().password())
                .append("autoDelete", "false")
                .append("queue", DEFAULT_RABBITMQ_QUEUE)
                .append("skipExchangeDeclare", "true")
                .append("skipQueueBind", "true")
                .append("hostname", rabbitmqService.connectionProperties().hostname())
                .append("portNumber", rabbitmqService.connectionProperties().port())
                .buildUrl();

        runBasicStringTest(factory);
    }
}
