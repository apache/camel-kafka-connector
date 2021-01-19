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

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.kafkaconnector.sjms2.clients.JMSClient;
import org.apache.camel.kafkaconnector.sjms2.common.SJMS2Common;
import org.apache.camel.test.infra.dispatch.router.services.DispatchRouterContainer;
import org.apache.camel.test.infra.messaging.services.MessagingService;
import org.apache.camel.test.infra.messaging.services.MessagingServiceBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * A simple test case that checks whether the timer produces the expected number of
 * messages
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSourceJMSITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static MessagingService jmsService = MessagingServiceBuilder
            .newBuilder(DispatchRouterContainer::new)
            .withEndpointProvider(DispatchRouterContainer::defaultEndpoint)
            .build();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceJMSITCase.class);

    private int received;
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
        received = 0;
        jmsClient = JMSClient.newClient(jmsService.defaultEndpoint());
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
        getKafkaConnectService().initializeConnector(connectorPropertyFactory);

        JMSClient.produceMessages(jmsClient, SJMS2Common.DEFAULT_JMS_QUEUE, expect, "Test string message");

        LOG.debug("Creating the consumer ...");
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        kafkaClient.consume(TestUtils.getDefaultTestTopic(this.getClass()), this::checkRecord);
        LOG.debug("Created the consumer ...");

        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceive() {
        try {
            ConnectorPropertyFactory connectorPropertyFactory = CamelJMSPropertyFactory
                    .basic()
                    .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withDestinationName(SJMS2Common.DEFAULT_JMS_QUEUE)
                    .withConnectionProperties(connectionProperties());

            runBasicStringTest(connectorPropertyFactory);
        } catch (Exception e) {
            LOG.error("JMS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceiveUsingUrl() {
        try {
            ConnectorPropertyFactory connectorPropertyFactory = CamelJMSPropertyFactory
                    .basic()
                    .withConnectionProperties(connectionProperties())
                    .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withUrl(SJMS2Common.DEFAULT_JMS_QUEUE)
                        .buildUrl();

            runBasicStringTest(connectorPropertyFactory);
        } catch (Exception e) {
            LOG.error("JMS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }


    @Test
    @Timeout(90)
    public void testIntSendReceive() {
        try {
            final String jmsQueueName = "testIntSendReceive";

            ConnectorPropertyFactory connectorPropertyFactory = CamelJMSPropertyFactory
                    .basic()
                    .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()) + jmsQueueName)
                    .withDestinationName(jmsQueueName)
                    .withConnectionProperties(connectionProperties());

            connectorPropertyFactory.log();
            getKafkaConnectService().initializeConnector(connectorPropertyFactory);

            JMSClient.produceMessages(jmsClient, jmsQueueName, expect);

            LOG.debug("Creating the consumer ...");
            KafkaClient<String, Integer> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
            kafkaClient.consume(TestUtils.getDefaultTestTopic(this.getClass()) + "testIntSendReceive", this::checkRecord);
            LOG.debug("Created the consumer ...");

            assertEquals(received, expect, "Didn't process the expected amount of messages");
        } catch (Exception e) {
            LOG.error("JMS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }

    }


}
