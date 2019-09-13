/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.apache.camel.kafkaconnector.sink.jms;

import org.apache.camel.kafkaconnector.ArtemisContainer;
import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.ContainerUtil;
import org.apache.camel.kafkaconnector.KafkaConnectRunner;
import org.apache.camel.kafkaconnector.TestCommon;
import org.apache.camel.kafkaconnector.clients.jms.JMSClient;
import org.apache.camel.kafkaconnector.clients.kafka.KafkaClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.KafkaContainer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

/**
 * A simple test case that checks whether the timer produces the expected number of
 * messages
 */
public class CamelSinkJMSITCase {
    private static final Logger log = LoggerFactory.getLogger(CamelSinkJMSITCase.class);

    @Rule
    public KafkaContainer kafka = new KafkaContainer().withEmbeddedZookeeper();

    @Rule
    public ArtemisContainer artemis = new ArtemisContainer();

    private int received = 0;
    private final int expect = 10;
    private KafkaConnectRunner kafkaConnectRunner;

    @Before
    public void setUp() {
        ContainerUtil.waitForInitialization(kafka);
        log.info("Kafka bootstrap server running at address {}", kafka.getBootstrapServers());

        ContainerUtil.waitForInitialization(artemis);
        log.info("Artemis broker running at {}", artemis.getAdminURL());

        ConnectorPropertyFactory testProperties = new CamelJMSPropertyFactory(1,
                TestCommon.DEFAULT_TEST_TOPIC, TestCommon.DEFAULT_JMS_QUEUE, artemis.getDefaultAcceptorEndpoint());

        kafkaConnectRunner =  new KafkaConnectRunner(kafka.getBootstrapServers());
        kafkaConnectRunner.getConnectorPropertyProducers().add(testProperties);
    }

    private boolean checkRecord(Message jmsMessage) {
        if (jmsMessage instanceof TextMessage) {
            try {
                log.debug("Received: {}", ((TextMessage) jmsMessage).getText());

                received++;

                if (received == expect) {
                    return false;
                }

                return true;
            } catch (JMSException e) {
                log.error("Failed to read message: {}", e.getMessage(), e);
                fail("Failed to read message: " + e.getMessage());
            }
        }

        return false;
    }

    @Test
    public void testBasicSendReceive() {
        try {
            CountDownLatch latch = new CountDownLatch(1);

            ExecutorService service = Executors.newFixedThreadPool(2);
            service.submit(() -> kafkaConnectRunner.run());

            log.debug("Creating the consumer ...");
            service.submit(() -> consumeJMSMessages(latch));

            KafkaClient<String,String> kafkaClient = new KafkaClient<>(kafka.getBootstrapServers());

            for (int i = 0; i < expect; i++) {
                kafkaClient.produce(TestCommon.DEFAULT_TEST_TOPIC, "Sink test message " + i);
            }

            log.debug("Created the consumer ... About to receive messages");

            if (latch.await(35, TimeUnit.SECONDS)) {
                Assert.assertTrue("Didn't process the expected amount of messages: " + received + " != " + expect,
                        received == expect);
            }
            else {
                fail("Failed to receive the messages within the specified time");
            }

            kafkaConnectRunner.stop();
        } catch (Exception e) {
            log.error("JMS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }

    }

    private void consumeJMSMessages(CountDownLatch latch) {
        JMSClient jmsClient = null;

        try {
            jmsClient = new JMSClient(org.apache.activemq.ActiveMQConnectionFactory::new,
                    org.apache.activemq.command.ActiveMQQueue::new,
                    artemis.getOpenwireEndpoint());

            jmsClient.start();

            for (int i = 0; i < expect; i++) {
                jmsClient.receive(TestCommon.DEFAULT_JMS_QUEUE, this::checkRecord);
            }

        } catch (Exception e) {
            log.error("JMS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        } finally {
            latch.countDown();

            if (jmsClient != null) {
                jmsClient.stop();
            }
        }
    }
}
