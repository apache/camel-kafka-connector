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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.common.test.StringMessageProducer;
import org.apache.camel.kafkaconnector.mongodb.common.MongoDBEnvVarServiceFactory;
import org.apache.camel.test.infra.common.TestUtils;
import org.apache.camel.test.infra.mongodb.services.MongoDBService;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Disabled("Waiting for https://github.com/apache/camel-kamelets/pull/485 to be merged and published.")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSinkMongoDBITCase extends CamelSinkTestSupport {
    @RegisterExtension
    public static MongoDBService mongoDBService = MongoDBEnvVarServiceFactory.createService("root", "password");

    private static final Logger LOG = LoggerFactory.getLogger(CamelMongoDBPropertyFactory.class);

    private MongoClient mongoClient;
    private String topicName;
    private final String databaseName = "testDB";
    private final String collectionName = "testRecords";

    private final int expect = 10;

    private static class CustomProducer extends StringMessageProducer {
        public CustomProducer(String bootstrapServer, String topicName, int count) {
            super(bootstrapServer, topicName, count);
        }

        @Override
        public String testMessageContent(int current) {
            return String.format("{\"test\": \"value %d\"}", current);
        }
    }

    @Override
    protected String[] getConnectorsInTest() {
        return new String[]{"camel-mongodb-sink-kafka-connector"};
    }


    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);
        mongoClient = MongoClients.create(mongoDBService.getReplicaSetUrl());
    }

    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try {
            MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);

            LOG.info("Waiting for data on the MongoDB instance");
            TestUtils.waitFor(() -> hasAllRecords(collection));
        } finally {
            latch.countDown();
        }
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (latch.await(15, TimeUnit.SECONDS)) {
            String databaseName = "testDB";
            String collectionName = "testRecords";

            verifyDocuments(databaseName, collectionName);
        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }
    private boolean hasAllRecords(MongoCollection<Document> collection) {
        return collection.countDocuments() >= expect;
    }

    private void verifyDocuments(String database, String collectionName) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);

        assertEquals(expect, collection.countDocuments());
    }

    @Test
    @Timeout(30)
    public void testBasicSendReceive() throws Exception {
        String connectionBeanRef = String.format("com.mongodb.client.MongoClients#create('%s')",
                mongoDBService.getReplicaSetUrl());

        CamelMongoDBPropertyFactory factory = CamelMongoDBPropertyFactory.basic()
                .withTopics(topicName)
                .withDatabase("testDB")
                .withCollection("testRecords")
                .withUsername("root")
                .withPassword("password")
                .withHosts(mongoDBService.getConnectionAddress());

        runTest(factory, new CustomProducer(getKafkaService().getBootstrapServers(), topicName, expect));
    }
}
