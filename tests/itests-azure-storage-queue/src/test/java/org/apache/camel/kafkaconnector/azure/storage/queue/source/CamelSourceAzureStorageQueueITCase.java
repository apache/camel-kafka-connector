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

package org.apache.camel.kafkaconnector.azure.storage.queue.source;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.QueueServiceClient;
import org.apache.camel.kafkaconnector.azure.storage.queue.common.TestQueueConfiguration;
import org.apache.camel.kafkaconnector.azure.storage.services.AzureStorageClientUtils;
import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.test.infra.azure.common.AzureCredentialsHolder;
import org.apache.camel.test.infra.azure.common.services.AzureService;
import org.apache.camel.test.infra.azure.storage.queue.services.AzureStorageQueueServiceFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled(value = "Disabled due to issue #976")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSourceAzureStorageQueueITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static AzureService service = AzureStorageQueueServiceFactory.createAzureService();
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceAzureStorageQueueITCase.class);

    private QueueServiceClient client;
    private QueueClient queueClient;
    private String queueName;
    private int expect = 10;
    private int received;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[]{"camel-azure-storage-queue-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
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

    private void sendMessages() {
        for (int i = 0; i < expect; i++) {
            queueClient.sendMessage("Test message " + i);
        }
    }

    private boolean checkRecord(ConsumerRecord<String, String> record) {
        LOG.debug("Received: {}", record.value());
        received++;

        if (received == expect) {
            return false;
        }

        return true;
    }

    public void runtTest(ConnectorPropertyFactory connectorPropertyFactory) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnector(connectorPropertyFactory);

        sendMessages();

        LOG.debug("Initialized the connector and put the data for the test execution");

        LOG.debug("Creating the consumer ...");
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        kafkaClient.consume(TestUtils.getDefaultTestTopic(this.getClass()), this::checkRecord);
        LOG.debug("Created the consumer ...");

        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }


    @Test
    @Timeout(90)
    public void testBasicSendReceive() throws InterruptedException, ExecutionException, IOException {
        AzureCredentialsHolder azureCredentialsHolder = service.azureCredentials();

        ConnectorPropertyFactory connectorPropertyFactory = CamelSourceAzureStorageQueuePropertyFactory
                .basic()
                .withConfiguration(TestQueueConfiguration.class.getName())
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withAccessKey(azureCredentialsHolder.accountKey())
                .withAccountName(azureCredentialsHolder.accountName())
                .withQueueName(queueName);

        runtTest(connectorPropertyFactory);
    }

}
