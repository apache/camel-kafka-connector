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

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;

import org.apache.camel.kafkaconnector.CamelSinkTask;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.common.utils.CamelKafkaConnectorTestUtils;
import org.apache.camel.kafkaconnector.sjms2.clients.JMSClient;
import org.apache.camel.kafkaconnector.sjms2.common.SJMS2Common;
import org.apache.camel.test.infra.common.TestUtils;
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
 * Integration tests for the JMS sink using idempotent features
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSinkIdempotentJMSITCase extends CamelSinkTestSupport {
    @RegisterExtension
    public static MessagingService jmsService = MessagingServiceFactory
            .builder()
            .addLocalMapping(SJMS2Common::createLocalService)
            .build();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkIdempotentJMSITCase.class);

    private String topic;
    private String destinationName;
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

        topic = CamelKafkaConnectorTestUtils.getDefaultTestTopic(this.getClass()) + TestUtils.randomWithRange(0, 100);
        destinationName = SJMS2Common.DEFAULT_JMS_QUEUE + "-" + TestUtils.randomWithRange(0, 100);
    }


    @Override
    protected void consumeMessages(CountDownLatch latch) {
        JMSClient jmsClient = null;

        try {
            jmsClient = JMSClient.newClient(jmsService.defaultEndpoint());
            jmsClient.start();

            try (MessageConsumer consumer = jmsClient.createConsumer(destinationName)) {
                // number of retries until stale
                int retries = 10;

                while (retries > 0) {
                    LOG.debug("Waiting for JMS messages (received {} of {} / retry {})", received, expect, retries);
                    jmsClient.receive(consumer, this::checkRecord, 1000);

                    // Once staled for 'retries', then it means no more data to receive (hopefully)
                    if (expect == received) {
                        retries--;
                    } else {
                        retries = 10;
                    }
                }
            }
        } catch (InterruptedException e) {
            LOG.warn("Interrupted, stopping ...");
            Thread.currentThread().interrupt();
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

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (latch.await(25, TimeUnit.SECONDS)) {
            assertEquals(received, expect, "Didn't process the expected amount of messages: " + received
                    + " != " + expect);
        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }

    private boolean checkRecord(Message jmsMessage) {
        if (jmsMessage instanceof TextMessage) {
            try {
                LOG.debug("Received: {}", ((TextMessage) jmsMessage).getText());

                received++;

                return true;
            } catch (JMSException e) {
                LOG.error("Failed to read message: {}", e.getMessage(), e);
                fail("Failed to read message: " + e.getMessage());
            }
        }

        return false;
    }

    private void produceMessagesNoProperties() {
        try {
            KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

            for (int i = 0; i < expect; i++) {
                LOG.debug("Sending message 1/2");
                kafkaClient.produce(topic, "Sink test message " + i);
                LOG.debug("Sending message 2/2");
                kafkaClient.produce(topic, "Sink test message " + i);
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    private void produceMessagesWithProperties() {
        try {
            KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

            for (int i = 0; i < expect; i++) {
                Map<String, String> headers = new HashMap<>();
                int randomNumber = TestUtils.randomWithRange(1, 1000);

                headers.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "MessageNumber", String.valueOf(i));

                kafkaClient.produce(topic, "Sink test message " + randomNumber, headers);
                kafkaClient.produce(topic, "Sink test message " + randomNumber + 1, headers);
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    @Timeout(90)
    public void testIdempotentBodySendReceive() {
        try {
            ConnectorPropertyFactory connectorPropertyFactory = CamelJMSPropertyFactory
                    .basic()
                    .withTopics(topic)
                    .withConnectionProperties(connectionProperties())
                    .withDestinationName(destinationName)
                    .withIdempotency()
                        .withRepositoryType("memory")
                        .withExpressionType("body")
                        .end();

            runTest(connectorPropertyFactory, this::produceMessagesNoProperties);

        } catch (Exception e) {
            LOG.error("JMS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    @Test
    @Timeout(90)
    public void testIdempotentHeaderSendReceive() {
        try {
            ConnectorPropertyFactory connectorPropertyFactory = CamelJMSPropertyFactory
                    .basic()
                    .withTopics(topic)
                    .withConnectionProperties(connectionProperties())
                    .withDestinationName(destinationName)
                    .withIdempotency()
                    .withRepositoryType("memory")
                    .withExpressionType("header")
                    .withExpressionHeader("MessageNumber")
                    .end();

            runTest(connectorPropertyFactory, this::produceMessagesWithProperties);

        } catch (Exception e) {
            LOG.error("JMS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }
}
