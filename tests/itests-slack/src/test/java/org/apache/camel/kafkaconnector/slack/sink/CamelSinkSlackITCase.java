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

package org.apache.camel.kafkaconnector.slack.sink;

import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Integration tests for the JMS sink
 */
@Testcontainers
/* This test is disabled by default because requires manual verification on Slack end.

You need to set 3 system properties to run this test:
 -Dit.test.slack.enable=true to enable the test
 -Dit.test.slack.channel=#channel to inform the channel to send the message to
 -Dit.test.slack.webhookUrl=https://host.slack.com/id/of/the/hook to pass the incoming hook URL to the test
 */
@EnabledIfSystemProperty(named = "it.test.slack.enable", matches = "true")
public class CamelSinkSlackITCase extends AbstractKafkaTest {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkSlackITCase.class);
    private String slackChannel = System.getProperty("it.test.slack.channel");
    private String webhookUrl = System.getProperty("it.test.slack.webhookUrl");

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-slack-kafka-connector"};
    }

    private void runTest(ConnectorPropertyFactory connectorPropertyFactory, String message) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnector(connectorPropertyFactory);

        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        kafkaClient.produce(TestUtils.getDefaultTestTopic(this.getClass()), message);


        LOG.debug("Created the consumer ... About to receive messages");
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceive() {
        try {
            ConnectorPropertyFactory connectorPropertyFactory = CamelSlackPropertyFactory
                    .basic()
                    .withTopics(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withChannel(slackChannel)
                    .withWebhookUrl(webhookUrl);

            runTest(connectorPropertyFactory, "Sink test message sent to Slack from testBasicSendReceive");

        } catch (Exception e) {
            LOG.error("Slack test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceiveWithUrl() {
        try {
            ConnectorPropertyFactory connectorPropertyFactory = CamelSlackPropertyFactory
                    .basic()
                    .withTopics(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withUrl(slackChannel)
                        .append("webhookUrl", webhookUrl)
                        .buildUrl();

            runTest(connectorPropertyFactory, "Sink test message sent to Slack from testBasicSendReceiveWithUrl");

        } catch (Exception e) {
            LOG.error("Slack test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }

}
