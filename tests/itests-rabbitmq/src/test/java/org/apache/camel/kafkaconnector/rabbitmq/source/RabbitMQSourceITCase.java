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

import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSourceTestSupport;
import org.apache.camel.kafkaconnector.common.test.TestMessageConsumer;
import org.apache.camel.kafkaconnector.rabbitmq.clients.RabbitMQClient;
import org.apache.camel.test.infra.rabbitmq.services.RabbitMQService;
import org.apache.camel.test.infra.rabbitmq.services.RabbitMQServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled("Until https://github.com/apache/camel-kamelets/pull/502 is merged and published")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RabbitMQSourceITCase extends CamelSourceTestSupport {
    @RegisterExtension
    public static RabbitMQService rabbitmqService = RabbitMQServiceFactory.createService();

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQSourceITCase.class);
    private static final String DEFAULT_RABBITMQ_QUEUE = "Q.test.kafka.import";

    private RabbitMQClient rabbitMQClient;
    private String topicName;
    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-rabbitmq-source-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);
        rabbitMQClient =  new RabbitMQClient(rabbitmqService.getAmqpUrl());

        rabbitMQClient.createQueue(DEFAULT_RABBITMQ_QUEUE);
    }

    @Override
    protected void produceTestData() {
        for (int i = 0; i < expect; i++) {
            rabbitMQClient.send(DEFAULT_RABBITMQ_QUEUE, "Test string message");
        }
    }

    @Override
    protected void verifyMessages(TestMessageConsumer<?> consumer) {
        int received = consumer.consumedMessages().size();
        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }

    @Test
    @Timeout(90)
    public void testSource() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory factory = CamelRabbitMQPropertyFactory
                .basic()
                .withKafkaTopic(topicName)
                .withAddresses(rabbitmqService.connectionProperties().hostname() + ":" + rabbitmqService.connectionProperties().port())
                .withPassword(rabbitmqService.connectionProperties().password())
                .withUsername(rabbitmqService.connectionProperties().username())
                .withExchangeName("default");

        runTest(factory, topicName, expect);
    }
}
