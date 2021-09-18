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

package org.apache.camel.kafkaconnector.azure.storage.blob.sink;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobItem;
import org.apache.camel.kafkaconnector.CamelSinkTask;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.common.test.StringMessageProducer;
import org.apache.camel.test.infra.azure.common.AzureCredentialsHolder;
import org.apache.camel.test.infra.azure.common.services.AzureService;
import org.apache.camel.test.infra.azure.storage.blob.clients.AzureStorageBlobClientUtils;
import org.apache.camel.test.infra.azure.storage.blob.services.AzureStorageBlobServiceFactory;
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
public class CamelSinkAzureStorageBlobITCase extends CamelSinkTestSupport {
    @RegisterExtension
    public static AzureService service = AzureStorageBlobServiceFactory.createService();
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkAzureStorageBlobITCase.class);

    private BlobServiceClient client;
    private BlobContainerClient blobContainerClient;
    private String blobContainerName;
    private Map<String, String> sentData = new HashMap<>();
    private String topicName;

    private int expect = 10;
    private int received;

    private class CustomProducer extends StringMessageProducer {
        public CustomProducer(String bootstrapServer, String topicName, int count) {
            super(bootstrapServer, topicName, count);
        }

        @Override
        public String testMessageContent(int current) {
            return "test " + current + " data";
        }

        @Override
        public Map<String, String> messageHeaders(String text, int current) {
            Map<String, String> messageParameters = new HashMap<>();

            String sentFile = "test " + current;

            sentData.put(sentFile, testMessageContent(current));

            messageParameters.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "file", sentFile);

            return messageParameters;
        }
    }

    @Override
    protected String[] getConnectorsInTest() {
        return new String[]{"camel-azure-storage-blob-sink-kafka-connector"};
    }

    @BeforeEach
    public void setUpBlob() {
        topicName = getTopicForTest(this);
        client = AzureStorageBlobClientUtils.getClient();

        blobContainerName = "test-" +  TestUtils.randomWithRange(1, 100);
        blobContainerClient = client.createBlobContainer(blobContainerName);
    }

    @AfterEach
    public void tearDown() {
        if (client != null) {
            client.deleteBlobContainer(blobContainerName);
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
        if (latch.await(240, TimeUnit.SECONDS)) {
            assertEquals(expect, received,
                    "Didn't process the expected amount of messages: " + received + " != " + expect);
        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }

    private boolean canConsume() {
        return blobContainerClient.exists() && blobContainerClient.listBlobs().stream().count() >= expect;
    }


    private void consume() {
        LOG.debug("Created the consumer ...");
        TestUtils.waitFor(this::canConsume);

        int retries = 10;
        do {
            received = 0;
            for (BlobItem blobContainerItem : blobContainerClient.listBlobs()) {
                String receivedFile = blobContainerItem.getName();
                BlobClient blobClient = blobContainerClient.getBlobClient(receivedFile);

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                blobClient.download(outputStream);
                String contentFile = outputStream.toString();

                LOG.info("Received: '{}' with content: '{}' expected content: '{}'", receivedFile, contentFile, sentData.get(receivedFile));
                assertEquals(sentData.get(receivedFile), contentFile, "Did not receive the same message that was sent");

                received++;
            }
            retries--;
        } while (received != 10 && retries > 0);
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceive() throws Exception {
        AzureCredentialsHolder azureCredentialsHolder = service.azureCredentials();

        ConnectorPropertyFactory factory = CamelSinkAzureStorageBlobPropertyFactory
                .basic()
                .withTopics(topicName)
                .withConfiguration(TestBlobConfiguration.class.getName())
                .withAccessKey(azureCredentialsHolder.accountKey())
                .withAccountName(azureCredentialsHolder.accountName())
                .withContainerName(blobContainerName)
                .withOperation("uploadBlockBlob");

        runTest(factory, new CustomProducer(getKafkaService().getBootstrapServers(), topicName, expect));
    }

}
