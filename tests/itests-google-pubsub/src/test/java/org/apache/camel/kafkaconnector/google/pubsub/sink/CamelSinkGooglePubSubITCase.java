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

package org.apache.camel.kafkaconnector.google.pubsub.sink;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.google.pubsub.clients.GooglePubEasy;
import org.apache.camel.test.infra.common.TestUtils;
import org.apache.camel.test.infra.google.pubsub.services.GooglePubSubService;
import org.apache.camel.test.infra.google.pubsub.services.GooglePubSubServiceFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CamelSinkGooglePubSubITCase extends CamelSinkTestSupport {
    @RegisterExtension
    public static GooglePubSubService service = GooglePubSubServiceFactory.createService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkGooglePubSubITCase.class);
    private String project = "ckc";
    private GooglePubEasy easyClient;

    private String googlePubSubTopic;
    private String testSubscription;

    private final int expected = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[]{"camel-google-pubsub-kafka-connector"};
    }


    @BeforeEach
    public void setUp() {
        googlePubSubTopic = "ckctopic" + TestUtils.randomWithRange(0, 100);
        testSubscription = "test-subscription" + TestUtils.randomWithRange(0, 100);
        LOG.info("Requesting topic {} for the pub/sub client", googlePubSubTopic);

        easyClient = new GooglePubEasy(service.getServiceAddress(), project);

        try {
            easyClient.createTopic(googlePubSubTopic);
            easyClient.createSubscription(testSubscription, googlePubSubTopic);
        } catch (InterruptedException | IOException e) {
            fail(e.getMessage());
        }

    }

    @AfterEach
    public void tearDown() {
        easyClient.shutdown();
    }

    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try {
            easyClient.receive();
        } finally {
            latch.countDown();
        }
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        List<String> receivedMessages = easyClient.getReceivedMessages();

        if (latch.await(60, TimeUnit.SECONDS)) {
            assertEquals(expected, receivedMessages.size(), "Did not receive as many messages as was sent");
        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }

    @Test
    public void testBasicSendReceive() throws Exception {
        String topicName = getTopicForTest(this);

        ConnectorPropertyFactory connectorPropertyFactory = CamelGooglePubSubPropertyFactory
                .basic()
                .withTopics(topicName)
                .withProjectId(project)
                .withDestinationName(googlePubSubTopic)
                .withEndpoint(service.getServiceAddress());

        runTest(connectorPropertyFactory, topicName, expected);
    }

    @Test
    public void testBasicSendReceiveUrl() throws Exception {
        String topicName = getTopicForTest(this);

        ConnectorPropertyFactory connectorPropertyFactory = CamelGooglePubSubPropertyFactory
                .basic()
                .withTopics(topicName)
                .withEndpoint(service.getServiceAddress())
                .withUrl(project, googlePubSubTopic)
                .buildUrl();

        runTest(connectorPropertyFactory, topicName, expected);
    }

}
