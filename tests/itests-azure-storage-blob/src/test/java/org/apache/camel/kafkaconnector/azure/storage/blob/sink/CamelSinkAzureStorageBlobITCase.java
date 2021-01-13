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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobItem;
import org.apache.camel.kafkaconnector.CamelSinkTask;
import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.test.infra.azure.common.AzureCredentialsHolder;
import org.apache.camel.test.infra.azure.common.services.AzureService;
import org.apache.camel.test.infra.azure.storage.blob.clients.AzureStorageBlobClientUtils;
import org.apache.camel.test.infra.azure.storage.blob.services.AzureStorageBlobServiceFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSinkAzureStorageBlobITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static AzureService service = AzureStorageBlobServiceFactory.createAzureService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkAzureStorageBlobITCase.class);

    private BlobServiceClient client;
    private BlobContainerClient blobContainerClient;
    private String blobContainerName;
    private Map<String, String> sentData = new HashMap<>();

    private int expect = 10;
    private int received;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[]{"camel-azure-storage-blob-kafka-connector"};
    }

    @BeforeEach
    public void setUpBlob() {
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

    private boolean canConsume() {
        return blobContainerClient.exists() && blobContainerClient.listBlobs().stream().count() > 0;
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

                LOG.info("Received: \'{}\' with content: \'{}\'", receivedFile, contentFile);
                assertEquals(sentData.get(receivedFile), contentFile, "Did not receive the same message that was sent");

                received++;
            }
            retries--;
        } while (received != 10 && retries > 0);
    }


    private void putRecords() {
        Map<String, String> messageParameters = new HashMap<>();
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        for (int i = 0; i < expect; i++) {
            try {
                String sentFile = "test " + i;
                String sentText = "test " + i + " data";

                sentData.put(sentFile, sentText);

                messageParameters.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "CamelAzureStorageBlobBlobName", sentFile);

                kafkaClient.produce(TestUtils.getDefaultTestTopic(this.getClass()), sentText, messageParameters);
            } catch (ExecutionException e) {
                LOG.error("Unable to produce messages: {}", e.getMessage(), e);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void runTest(ConnectorPropertyFactory connectorPropertyFactory) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnectorBlocking(connectorPropertyFactory, 1);

        putRecords();

        consume();

        assertEquals(expect, received, "Did not receive the same amount of messages that were sent");
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceive() throws InterruptedException, ExecutionException, IOException {
        AzureCredentialsHolder azureCredentialsHolder = service.azureCredentials();

        ConnectorPropertyFactory factory = CamelSinkAzureStorageBlobPropertyFactory
                .basic()
                .withTopics(TestUtils.getDefaultTestTopic(this.getClass()))
                .withConfiguration(TestBlobConfiguration.class.getName())
                .withAccessKey(azureCredentialsHolder.accountKey())
                .withAccountName(azureCredentialsHolder.accountName())
                .withContainerName(blobContainerName)
                .withOperation("uploadBlockBlob");

        runTest(factory);
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceiveUrl() throws InterruptedException, ExecutionException, IOException {
        AzureCredentialsHolder azureCredentialsHolder = service.azureCredentials();

        ConnectorPropertyFactory factory = CamelSinkAzureStorageBlobPropertyFactory
                .basic()
                .withTopics(TestUtils.getDefaultTestTopic(this.getClass()))
                .withConfiguration(TestBlobConfiguration.class.getName())
                .withUrl(azureCredentialsHolder.accountName() + "/" + blobContainerName)
                    .append("accessKey", azureCredentialsHolder.accountKey())
                    .append("operation", "uploadBlockBlob")
                    .buildUrl();

        runTest(factory);
    }
}
