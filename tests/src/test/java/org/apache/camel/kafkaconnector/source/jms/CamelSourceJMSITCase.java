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

package org.apache.camel.kafkaconnector.source.jms;

import java.util.Properties;

import javax.jms.JMSException;

import org.apache.camel.kafkaconnector.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.TestCommon;
import org.apache.camel.kafkaconnector.clients.jms.JMSClient;
import org.apache.camel.kafkaconnector.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.services.jms.JMSService;
import org.apache.camel.kafkaconnector.services.jms.JMSServiceFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * A simple test case that checks whether the timer produces the expected number of
 * messages
 */
@Testcontainers
public class CamelSourceJMSITCase extends AbstractKafkaTest {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceJMSITCase.class);

    @RegisterExtension
    public JMSService jmsService = JMSServiceFactory.createService();

    private int received;
    private final int expect = 10;

    private boolean checkRecord(ConsumerRecord<String, String> record) {
        LOG.debug("Received: {}", record.value());
        received++;

        if (received == expect) {
            return false;
        }

        return true;
    }

    private void produceMessages(String queue, String baseText) {
        JMSClient jmsProducer = null;

        try {
            jmsProducer = jmsService.getClient();

            jmsProducer.start();
            for (int i = 0; i < expect; i++) {
                jmsProducer.send(queue, baseText + " " + i);
            }
        } catch (JMSException e) {
            LOG.error("JMS exception trying to send messages to the queue: {}", e.getMessage(), e);
            fail(e.getMessage());
        } catch (Exception e) {
            LOG.error("Failed to send messages to the queue: {}", e.getMessage(), e);
            fail(e.getMessage());
        } finally {
            jmsProducer.stop();
        }
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceive() {
        try {
            Properties connectionProperties = jmsService.getConnectionProperties();

            ConnectorPropertyFactory testProperties = new CamelJMSPropertyFactory(1,
                    TestCommon.getDefaultTestTopic(this.getClass()), TestCommon.DEFAULT_JMS_QUEUE, connectionProperties);

            getKafkaConnectService().initializeConnector(testProperties);

            produceMessages(TestCommon.DEFAULT_JMS_QUEUE, "Test string message");

            LOG.debug("Creating the consumer ...");
            KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
            kafkaClient.consume(TestCommon.getDefaultTestTopic(this.getClass()), this::checkRecord);
            LOG.debug("Created the consumer ...");

            assertEquals(received, expect, "Didn't process the expected amount of messages");
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

            Properties connectionProperties = jmsService.getConnectionProperties();

            ConnectorPropertyFactory testProperties = new CamelJMSPropertyFactory(1,
                    TestCommon.getDefaultTestTopic(this.getClass()) + jmsQueueName,
                    jmsQueueName, connectionProperties);

            getKafkaConnectService().initializeConnector(testProperties);

            produceMessages(jmsQueueName, "Test string message");

            LOG.debug("Creating the consumer ...");
            KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
            kafkaClient.consume(TestCommon.getDefaultTestTopic(this.getClass()) + "testIntSendReceive", this::checkRecord);
            LOG.debug("Created the consumer ...");

            assertEquals(received, expect, "Didn't process the expected amount of messages");
        } catch (Exception e) {
            LOG.error("JMS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }

    }
}
