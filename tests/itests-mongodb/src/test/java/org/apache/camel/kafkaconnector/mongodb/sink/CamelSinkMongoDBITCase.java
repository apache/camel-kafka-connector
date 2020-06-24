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

package org.apache.camel.kafkaconnector.mongodb.sink;

import java.util.concurrent.ExecutionException;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.BasicConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.kafkaconnector.mongodb.services.MongoDBService;
import org.apache.camel.kafkaconnector.mongodb.services.MongoDBServiceFactory;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Testcontainers
public class CamelSinkMongoDBITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static MongoDBService mongoDBService = MongoDBServiceFactory.createService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelMongoDBPropertyFactory.class);

    private MongoClient mongoClient;

    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[]{"camel-mongodb-kafka-connector"};
    }


    @BeforeEach
    public void setUp() {
        mongoClient = mongoDBService.getClient();
    }

    private void putRecords() {
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        try {
            for (int i = 0; i < expect; i++) {
                String data = String.format("{\"test\": \"value %d\"}", i);

                kafkaClient.produce(TestUtils.getDefaultTestTopic(this.getClass()), data);
            }

        } catch (ExecutionException e) {
            LOG.error("Unable to produce messages: {}", e.getMessage(), e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOG.warn("The thread putting records to Kafka was interrupted");
            fail("The thread putting records to Kafka was interrupted");
        }
    }

    private boolean hasAllRecords(MongoCollection<Document> collection) {
        return collection.countDocuments() >= expect;
    }

    private void verifyDocuments(String database, String collectionName) throws InterruptedException {
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);

        TestUtils.waitFor(() -> hasAllRecords(collection));

        assertEquals(expect, collection.countDocuments());
    }

    public void runTest(ConnectorPropertyFactory propertyFactory) throws ExecutionException, InterruptedException {
        propertyFactory.log();
        getKafkaConnectService().initializeConnectorBlocking(propertyFactory, 1);

        putRecords();
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceive() throws ExecutionException, InterruptedException {
        String connectionBeanRef = String.format("com.mongodb.client.MongoClients#create('mongodb://%s:%d')",
                mongoDBService.getHost(),
                mongoDBService.getPort());

        CamelMongoDBPropertyFactory factory = CamelMongoDBPropertyFactory.basic()
                .withTopics(TestUtils.getDefaultTestTopic(this.getClass()))
                .withConnectionBean("mongo",
                        BasicConnectorPropertyFactory.classRef(connectionBeanRef))
                .withDatabase("testDB")
                .withCollection("testRecords")
                .withOperation("insert");

        runTest(factory);

        verifyDocuments("testDB", "testRecords");
    }
}
