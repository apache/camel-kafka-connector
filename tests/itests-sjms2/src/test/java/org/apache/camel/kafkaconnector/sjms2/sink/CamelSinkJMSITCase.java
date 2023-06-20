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

package org.apache.camel.kafkaconnector.sjms2.sink;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageConsumer;
import jakarta.jms.TextMessage;

import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.sjms2.clients.JMSClient;
import org.apache.camel.kafkaconnector.sjms2.common.SJMS2Common;
import org.apache.camel.test.infra.messaging.services.MessagingService;
import org.apache.camel.test.infra.messaging.services.MessagingServiceFactory;
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
 * Integration tests for the JMS sink
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSinkJMSITCase extends CamelSinkTestSupport {
    @RegisterExtension
    public static MessagingService jmsService = MessagingServiceFactory
            .builder()
            .addLocalMapping(SJMS2Common::createLocalService)
            .build();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkJMSITCase.class);

    private String topicName;
    private int received;
    private final int expect = 10;

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
        LOG.info("JMS service running at {}", jmsService.defaultEndpoint());
        received = 0;

        topicName = getTopicForTest(this);
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (latch.await(35, TimeUnit.SECONDS)) {
            assertEquals(received, expect, "Didn't process the expected amount of messages: " + received + " != " + expect);
        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }

    private boolean checkRecord(Message jmsMessage) {
        if (jmsMessage instanceof TextMessage) {
            try {
                LOG.debug("Received: {}", ((TextMessage) jmsMessage).getText());

                received++;

                if (received == expect) {
                    LOG.debug("All messages were received");
                    return false;
                }

                return true;
            } catch (JMSException e) {
                LOG.error("Failed to read message: {}", e.getMessage(), e);
                fail("Failed to read message: " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    protected void consumeMessages(CountDownLatch latch) {
        JMSClient jmsClient = null;

        try {
            jmsClient = JMSClient.newClient(jmsService.defaultEndpoint());

            jmsClient.start();
            try (MessageConsumer consumer = jmsClient.createConsumer(SJMS2Common.DEFAULT_JMS_QUEUE)) {
                jmsClient.receive(consumer, this::checkRecord);
            }

        } catch (Exception e) {
            LOG.error("JMS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        } finally {
            latch.countDown();

            if (jmsClient != null) {
                jmsClient.stop();
            }
        }
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceive() throws Exception {
        ConnectorPropertyFactory connectorPropertyFactory = CamelJMSPropertyFactory
                .basic()
                .withTopics(topicName)
                .withConnectionProperties(connectionProperties())
                .withDestinationName(SJMS2Common.DEFAULT_JMS_QUEUE);

        runTest(connectorPropertyFactory, topicName, expect);
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceiveUsingUrl() throws Exception {
        ConnectorPropertyFactory connectorPropertyFactory = CamelJMSPropertyFactory
                .basic()
                .withTopics(topicName)
                .withConnectionProperties(connectionProperties())
                    .withUrl(SJMS2Common.DEFAULT_JMS_QUEUE)
                    .buildUrl();

        runTest(connectorPropertyFactory, topicName, expect);
    }
}
