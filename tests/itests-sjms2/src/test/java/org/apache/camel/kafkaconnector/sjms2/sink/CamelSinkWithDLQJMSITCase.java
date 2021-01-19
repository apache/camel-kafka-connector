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
import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.kafkaconnector.sjms2.common.SJMS2Common;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Integration tests for the JMS sink with a DLQ configuration. This test forces a failure in the sink connector to
 * ensure that the failed records are added to the DLQ configured in Kafka.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSinkWithDLQJMSITCase extends AbstractKafkaTest {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkWithDLQJMSITCase.class);

    private int received;
    private final int expect = 10;
    private int errors;
    private final int expectedErrors = 1;

    private Properties connectionProperties() {
        Properties properties = new Properties();

        properties.put("camel.component.sjms2.connection-factory", "#class:org.apache.qpid.jms.JmsConnectionFactory");
        properties.put("camel.component.sjms2.connection-factory.remoteURI", "invalid");

        return properties;
    }

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-sjms2-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        received = 0;
        errors = 0;
    }

    private <T> boolean checkDqlRecord(ConsumerRecord<String, T> record) {
        LOG.debug("Received: {}", record.value());
        errors++;

        if (errors >= expectedErrors) {
            return false;
        }

        return true;
    }

    private void runTest(ConnectorPropertyFactory connectorPropertyFactory) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnector(connectorPropertyFactory);

        LOG.debug("Creating the consumer ...");


        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        for (int i = 0; i < expect; i++) {
            kafkaClient.produce(TestUtils.getDefaultTestTopic(this.getClass()), "Sink test message " + i);
        }

        LOG.debug("Created the consumer ... About to receive messages");
    }


    @Test
    @Timeout(10)
    public void testSendReceiveWithError() {
        try {
            Properties brokenProp = connectionProperties();

            brokenProp.put("camel.component.sjms2.connection-factory.remoteURI", "invalid");

            ConnectorPropertyFactory connectorPropertyFactory = CamelJMSPropertyFactory
                    .basic()
                    .withTopics(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withConnectionProperties(brokenProp)
                    .withDestinationName(SJMS2Common.DEFAULT_JMS_QUEUE)
                    .withDeadLetterQueueTopicName("dlq-sink-topic");

            runTest(connectorPropertyFactory);

            KafkaClient<String, Integer> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
            kafkaClient.consume("dlq-sink-topic", this::checkDqlRecord);

            assertEquals(expectedErrors, errors, "Didn't process the expected amount of messages");

        } catch (Exception e) {
            LOG.error("JMS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }
}
