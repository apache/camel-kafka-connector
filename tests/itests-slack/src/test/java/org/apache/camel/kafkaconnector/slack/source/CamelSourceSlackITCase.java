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

package org.apache.camel.kafkaconnector.slack.source;

import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


/*
This test is disabled by default because requires manual steps.

You need to set 3 system properties to run this test:
 -Dit.test.slack.enable=true to enable the test
 -Dit.test.slack.channel=#channel to inform the channel to send the message to
 -Dit.test.slack.token=<token> The token is a string that starts with xoxb

Preparing for the test. You need create a bot and give it the following scopes:
channel:history, channels:read and incoming-webhook. The settings for these,
along with the token can be found on the page OAuth & Permissions.
*/

@EnabledIfSystemProperty(named = "it.test.slack.enable", matches = "true")
public class CamelSourceSlackITCase extends AbstractKafkaTest {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceSlackITCase.class);
    private String slackChannel = System.getProperty("it.test.slack.channel");
    private String token = System.getProperty("it.test.slack.token");

    private boolean received;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[]{"camel-slack-kafka-connector"};
    }

    private <T> boolean checkRecord(ConsumerRecord<String, T> record) {
        LOG.debug("Received: {}", record.value());

        if (record.value() instanceof String) {
            LOG.debug("Received text: {}", record.value());
        } else {
            fail(String.format("Unexpected message type: %s", record.value().getClass()));
        }

        received  = true;

        return false;
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceive() throws ExecutionException, InterruptedException {
        String kafkaTopic = getTopicForTest(this);
        ConnectorPropertyFactory factory = CamelSlackPropertyFactory
                .basic()
                .withKafkaTopic(kafkaTopic)
                .withChannel(slackChannel)
                .withMaxResults(1)
                .withToken(token)
                .withTransformsConfig("SlackTransforms")
                    .withEntry("type", "org.apache.camel.kafkaconnector.slack.transformers.SlackTransforms")
                    .end();

        factory.log();
        getKafkaConnectService().initializeConnectorBlocking(factory, 1);

        LOG.debug("Creating the consumer ...");
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        kafkaClient.consume(kafkaTopic, this::checkRecord);
        LOG.debug("Created the consumer ...");

        assertTrue(received, "Didn't receive any messages");
    }
}
