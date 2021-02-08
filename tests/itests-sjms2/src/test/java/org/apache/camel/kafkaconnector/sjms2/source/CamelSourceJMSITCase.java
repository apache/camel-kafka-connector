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

package org.apache.camel.kafkaconnector.sjms2.source;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.test.CamelSourceTestSupport;
import org.apache.camel.kafkaconnector.common.test.IntegerMessageConsumer;
import org.apache.camel.kafkaconnector.common.test.TestMessageConsumer;
import org.apache.camel.kafkaconnector.sjms2.clients.JMSClient;
import org.apache.camel.kafkaconnector.sjms2.common.SJMS2Common;
import org.apache.camel.test.infra.dispatch.router.services.DispatchRouterContainer;
import org.apache.camel.test.infra.messaging.services.MessagingService;
import org.apache.camel.test.infra.messaging.services.MessagingServiceBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * A simple test case that checks whether the timer produces the expected number of
 * messages
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSourceJMSITCase extends CamelSourceTestSupport {
    @RegisterExtension
    public static MessagingService jmsService = MessagingServiceBuilder
            .newBuilder(DispatchRouterContainer::new)
            .withEndpointProvider(DispatchRouterContainer::defaultEndpoint)
            .build();

    private String topicName;
    private final int expect = 10;
    private JMSClient jmsClient;

    private Properties connectionProperties() {
        Properties properties = new Properties();

        properties.put("camel.component.sjms2.connection-factory", "#class:org.apache.qpid.jms.JmsConnectionFactory");
        properties.put("camel.component.sjms2.connection-factory.remoteURI", jmsService.defaultEndpoint());

        return properties;
    }

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-sjms2-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);

    }

    @BeforeAll
    public void setupClient() {
        jmsClient = JMSClient.newClient(jmsService.defaultEndpoint());
    }

    @Override
    protected void produceTestData() {
        JMSClient.produceMessages(jmsClient, SJMS2Common.DEFAULT_JMS_QUEUE, expect, "Test string message");
    }

    @Override
    protected void verifyMessages(TestMessageConsumer<?> consumer) {
        int received = consumer.consumedMessages().size();
        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }


    @Test
    @Timeout(90)
    public void testBasicSendReceive() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelJMSPropertyFactory
                .basic()
                .withKafkaTopic(topicName)
                .withDestinationName(SJMS2Common.DEFAULT_JMS_QUEUE)
                .withConnectionProperties(connectionProperties());

        runTest(connectorPropertyFactory, topicName, expect);
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceiveUsingUrl() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelJMSPropertyFactory
                .basic()
                .withConnectionProperties(connectionProperties())
                .withKafkaTopic(topicName)
                .withUrl(SJMS2Common.DEFAULT_JMS_QUEUE)
                    .buildUrl();

        runTest(connectorPropertyFactory, topicName, expect);
    }


    @Test
    @Timeout(90)
    public void testIntSendReceive() throws ExecutionException, InterruptedException {
        final String jmsQueueName = "testIntSendReceive";

        ConnectorPropertyFactory connectorPropertyFactory = CamelJMSPropertyFactory
                .basic()
                .withKafkaTopic(topicName)
                .withDestinationName(jmsQueueName)
                .withConnectionProperties(connectionProperties());

        KafkaClient<String, Integer> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        IntegerMessageConsumer consumer = new IntegerMessageConsumer(kafkaClient, topicName, expect);

        runTest(connectorPropertyFactory, consumer, () -> JMSClient.produceMessages(jmsClient, jmsQueueName, expect));
    }


}
