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

package org.apache.camel.kafkaconnector.sink.jms;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.camel.kafkaconnector.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.TestCommon;
import org.apache.camel.kafkaconnector.clients.jms.JMSClient;
import org.apache.camel.kafkaconnector.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.services.jms.JMSService;
import org.apache.camel.kafkaconnector.services.jms.JMSServiceFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.fail;

/**
 * A simple test case that checks whether the timer produces the expected number of
 * messages
 */
public class CamelSinkJMSITCase extends AbstractKafkaTest {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkJMSITCase.class);

    @Rule
    public JMSService jmsService = JMSServiceFactory.createService();

    private int received;
    private final int expect = 10;

    @Before
    public void setUp() {
        LOG.info("JMS service running at {}", jmsService.getDefaultEndpoint());
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

    @Test(timeout = 90000)
    public void testBasicSendReceive() {
        try {
            Properties connectionProperties = JMSClient.getConnectionProperties(jmsService.getDefaultEndpoint());

            ConnectorPropertyFactory testProperties = new CamelJMSPropertyFactory(1,
                    TestCommon.getDefaultTestTopic(this.getClass()),
                    TestCommon.DEFAULT_JMS_QUEUE, connectionProperties);

            getKafkaConnectService().initializeConnector(testProperties);

            CountDownLatch latch = new CountDownLatch(1);

            ExecutorService service = Executors.newCachedThreadPool();

            LOG.debug("Creating the consumer ...");
            service.submit(() -> consumeJMSMessages(latch));

            KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

            for (int i = 0; i < expect; i++) {
                kafkaClient.produce(TestCommon.getDefaultTestTopic(this.getClass()), "Sink test message " + i);
            }

            LOG.debug("Created the consumer ... About to receive messages");

            if (latch.await(35, TimeUnit.SECONDS)) {
                Assert.assertEquals("Didn't process the expected amount of messages: " + received + " != " + expect,
                        received, expect);
            } else {
                fail("Failed to receive the messages within the specified time");
            }
        } catch (Exception e) {
            LOG.error("JMS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }

    }

    private void consumeJMSMessages(CountDownLatch latch) {
        JMSClient jmsClient = null;

        try {
            jmsClient = JMSClient.createClient(jmsService.getDefaultEndpoint());

            jmsClient.start();

            for (int i = 0; i < expect; i++) {
                jmsClient.receive(TestCommon.DEFAULT_JMS_QUEUE, this::checkRecord);
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
