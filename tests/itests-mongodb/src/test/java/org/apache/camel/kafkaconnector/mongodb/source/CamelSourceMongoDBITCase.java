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
import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.BasicConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.test.infra.mongodb.services.MongoDBService;
import org.apache.camel.test.infra.mongodb.services.MongoDBServiceFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSourceMongoDBITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static MongoDBService mongoDBService = MongoDBServiceFactory.createService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceMongoDBITCase.class);

    private MongoClient mongoClient;

    private final int expect = 10;
    private int received;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[]{"camel-mongodb-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
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

    private <T> boolean checkRecord(ConsumerRecord<String, T> record) {
        LOG.debug("Received: {}", record.value());
        received++;

        if (received == expect) {
            return false;
        }

        return true;
    }

    public void runTest(ConnectorPropertyFactory connectorPropertyFactory) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnectorBlocking(connectorPropertyFactory, 1);

        LOG.debug("Creating the consumer ...");
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        kafkaClient.consume(TestUtils.getDefaultTestTopic(this.getClass()), this::checkRecord);
        LOG.debug("Created the consumer ...");

        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }

    @Test
    @Timeout(90)
    public void testFindAll() throws ExecutionException, InterruptedException {
        String connectionBeanRef = String.format("com.mongodb.client.MongoClients#create('%s')",
                mongoDBService.getReplicaSetUrl());

        ConnectorPropertyFactory factory = CamelMongoDBPropertyFactory.basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withConnectionBean("mongo",
                        BasicConnectorPropertyFactory.classRef(connectionBeanRef))
                .withDatabase("testDatabase")
                .withCollection("testCollection")
                .withCreateCollection(true);

        runTest(factory);
    }
}
