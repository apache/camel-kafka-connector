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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.kafkaconnector.sjms2.clients.JMSClient;
import org.apache.camel.kafkaconnector.sjms2.common.SJMS2Common;
import org.apache.camel.kafkaconnector.sjms2.services.JMSService;
import org.apache.camel.kafkaconnector.sjms2.services.JMSServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration tests for the JMS sink
 */
@Testcontainers
public class CamelSinkJMSITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static JMSService jmsService = JMSServiceFactory.createService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkJMSITCase.class);

    private int received;
    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-sjms2-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        LOG.info("JMS service running at {}", jmsService.getDefaultEndpoint());
        received = 0;
    }

    private boolean checkRecord(Message jmsMessage) {
        if (jmsMessage instanceof TextMessage) {
            try {
                LOG.debug("Received: {}", ((TextMessage) jmsMessage).getText());

                received++;

                if (received == expect) {
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

    private void runTest(ConnectorPropertyFactory connectorPropertyFactory) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnector(connectorPropertyFactory);

        CountDownLatch latch = new CountDownLatch(1);

        ExecutorService service = Executors.newCachedThreadPool();

        LOG.debug("Creating the consumer ...");
        service.submit(() -> consumeJMSMessages(latch));

        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        for (int i = 0; i < expect; i++) {
            kafkaClient.produce(TestUtils.getDefaultTestTopic(this.getClass()), "Sink test message " + i);
        }

        LOG.debug("Created the consumer ... About to receive messages");

        if (latch.await(35, TimeUnit.SECONDS)) {
            assertEquals(received, expect, "Didn't process the expected amount of messages: " + received + " != " + expect);
        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceive() {
        try {
            ConnectorPropertyFactory connectorPropertyFactory = CamelJMSPropertyFactory
                    .basic()
                    .withTopics(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withConnectionProperties(jmsService.getConnectionProperties())
                    .withDestinationName(SJMS2Common.DEFAULT_JMS_QUEUE);

            runTest(connectorPropertyFactory);

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
                    .withTopics(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withConnectionProperties(jmsService.getConnectionProperties())
                        .withUrl(SJMS2Common.DEFAULT_JMS_QUEUE)
                        .buildUrl();

            runTest(connectorPropertyFactory);

        } catch (Exception e) {
            LOG.error("JMS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    private void consumeJMSMessages(CountDownLatch latch) {
        JMSClient jmsClient = null;

        try {
            jmsClient = jmsService.getClient();

            jmsClient.start();

            for (int i = 0; i < expect; i++) {
                jmsClient.receive(SJMS2Common.DEFAULT_JMS_QUEUE, this::checkRecord);
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
}
