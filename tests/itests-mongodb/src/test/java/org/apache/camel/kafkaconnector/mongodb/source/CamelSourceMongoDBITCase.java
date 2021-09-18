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

package org.apache.camel.kafkaconnector.mongodb.source;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSourceTestSupport;
import org.apache.camel.kafkaconnector.common.test.TestMessageConsumer;
import org.apache.camel.kafkaconnector.mongodb.common.MongoDBEnvVarServiceFactory;
import org.apache.camel.test.infra.mongodb.services.MongoDBService;
import org.bson.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled("Waiting for https://github.com/apache/camel-kamelets/pull/486 to be merged and published.")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSourceMongoDBITCase extends CamelSourceTestSupport {
    @RegisterExtension
    public static MongoDBService mongoDBService = MongoDBEnvVarServiceFactory.createService("root", "password");

    private MongoClient mongoClient;
    private String topicName;

    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[]{"camel-mongodb-source-kafka-connector"};
    }

    @BeforeAll
    public void setUpDb() {
        mongoClient = MongoClients.create(mongoDBService.getReplicaSetUrl());

        MongoDatabase database = mongoClient.getDatabase("testDatabase");

        /*
         The consume operation needs taliable cursors which require capped
         collections
         */
        CreateCollectionOptions options = new CreateCollectionOptions();
        options.capped(true);
        options.sizeInBytes(1024 * 1024);

        database.createCollection("testCollection", options);

        MongoCollection<Document> collection = database.getCollection("testCollection");

        List<Document> documents = new ArrayList<>(expect);
        for (int i = 0; i < expect; i++) {
            Document doc = new Document();

            doc.append("name", "test");
            doc.append("value", "value " + i);

            documents.add(doc);
        }

        collection.insertMany(documents);
    }

    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);
    }

    @Override
    protected void produceTestData() {
        // NO-OP: static data already produced on the DB setup method
    }

    @Override
    protected void verifyMessages(TestMessageConsumer<?> consumer) {
        int received = consumer.consumedMessages().size();

        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }

    @Test
    @Timeout(90)
    public void testFindAll() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory factory = CamelMongoDBPropertyFactory.basic()
                .withKafkaTopic(topicName)
                .withDatabase("testDatabase")
                .withCollection("testCollection")
                .withUsername("root")
                .withPassword("password")
                .withHosts(mongoDBService.getConnectionAddress());

        runTest(factory, topicName, expect);
    }
}
