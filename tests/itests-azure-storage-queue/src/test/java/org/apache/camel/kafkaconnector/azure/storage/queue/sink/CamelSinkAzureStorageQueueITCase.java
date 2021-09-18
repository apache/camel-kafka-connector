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

package org.apache.camel.kafkaconnector.azure.storage.queue.sink;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.QueueServiceClient;
import com.azure.storage.queue.models.PeekedMessageItem;
import org.apache.camel.kafkaconnector.azure.storage.queue.common.TestQueueConfiguration;
import org.apache.camel.kafkaconnector.azure.storage.services.AzureStorageClientUtils;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.test.infra.azure.common.AzureCredentialsHolder;
import org.apache.camel.test.infra.azure.common.services.AzureService;
import org.apache.camel.test.infra.azure.storage.queue.services.AzureStorageQueueServiceFactory;
import org.apache.camel.test.infra.common.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSinkAzureStorageQueueITCase extends CamelSinkTestSupport {
    @RegisterExtension
    public static AzureService service = AzureStorageQueueServiceFactory.createService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkAzureStorageQueueITCase.class);

    private QueueServiceClient client;
    private QueueClient queueClient;
    private String queueName;
    private String topicName;
    private int expect = 10;
    private int received;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[]{"camel-azure-storage-queue-sink-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);

        client = AzureStorageClientUtils.getClient();
        queueName = "test-queue" + TestUtils.randomWithRange(0, 100);

        queueClient = client.createQueue(queueName);
        received = 0;
    }

    @AfterEach
    public void tearDown() {
        if (client != null) {
            client.deleteQueue(queueName);
        }
    }

    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try {
            consume();
        } finally {
            latch.countDown();
        }
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (latch.await(120, TimeUnit.SECONDS)) {
            assertEquals(expect, received,
                    "Didn't process the expected amount of messages: " + received + " != " + expect);
        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }

    private void acknowledgeReceived(PeekedMessageItem peekedMessageItem) {
        received++;
        LOG.info("Received: {}", peekedMessageItem.getMessageText());
    }

    private boolean canConsume() {
        return queueClient.getProperties().getApproximateMessagesCount() >= expect;
    }

    private void consume() {
        LOG.debug("Created the consumer ...");
        TestUtils.waitFor(this::canConsume);

        LOG.debug("About to receive messages");
        int count = queueClient.getProperties().getApproximateMessagesCount();

        queueClient.peekMessages(count, null, null).forEach(this::acknowledgeReceived);
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceive() throws Exception {
        AzureCredentialsHolder azureCredentialsHolder = service.azureCredentials();

        ConnectorPropertyFactory connectorPropertyFactory = CamelSinkAzureStorageQueuePropertyFactory
                .basic()
                .withConfiguration(TestQueueConfiguration.class.getName())
                .withTopics(topicName)
                .withAccessKey(azureCredentialsHolder.accountKey())
                .withAccountName(azureCredentialsHolder.accountName())
                .withQueueName(queueName);

        runTest(connectorPropertyFactory, topicName, expect);
    }
}
