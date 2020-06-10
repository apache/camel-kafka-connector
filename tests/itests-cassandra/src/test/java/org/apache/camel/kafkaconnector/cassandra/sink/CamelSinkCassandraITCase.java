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

package org.apache.camel.kafkaconnector.cassandra.sink;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.cassandra.clients.CassandraClient;
import org.apache.camel.kafkaconnector.cassandra.clients.dao.TestDataDao;
import org.apache.camel.kafkaconnector.cassandra.services.CassandraService;
import org.apache.camel.kafkaconnector.cassandra.services.CassandraServiceFactory;
import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@Testcontainers
public class CamelSinkCassandraITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static CassandraService cassandraService = CassandraServiceFactory.createService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkCassandraITCase.class);

    private CassandraClient cassandraClient;
    private TestDataDao testDataDao;

    private final int expect = 10;
    private int received;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-cql-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        cassandraClient = cassandraService.getClient();

        testDataDao = cassandraClient.newTestDataDao();

        testDataDao.createKeySpace();
        testDataDao.useKeySpace();
        testDataDao.createTable();
    }

    @AfterEach
    public void tearDown() {
        cassandraClient = cassandraService.getClient();

        if (testDataDao != null) {
            testDataDao.dropTable();
        }
    }

    private void putRecords(CountDownLatch latch) {
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        try {
            for (int i = 0; i < expect; i++) {
                try {
                    kafkaClient.produce(TestUtils.getDefaultTestTopic(this.getClass()), "test " + i);
                } catch (ExecutionException e) {
                    LOG.error("Unable to produce messages: {}", e.getMessage(), e);
                } catch (InterruptedException e) {
                    break;
                }
            }
        } finally {
            latch.countDown();
        }
    }

    private void checkRetrievedData(String data) {
        if (data != null) {
            received++;
        }
    }

    public void runTest(ConnectorPropertyFactory connectorPropertyFactory) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();

        getKafkaConnectService().initializeConnector(connectorPropertyFactory);

        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> putRecords(latch));

        if (!latch.await(30, TimeUnit.SECONDS)) {
            fail("Timed out wait for data to be added to the Kafka cluster");
        }

        TestUtils.waitFor(testDataDao::hasEnoughData, (long) expect);
        testDataDao.getData(this::checkRetrievedData);
        assertTrue(received >= expect,
                String.format("Did not receive as much data as expected: %d < %d", received, expect));

    }

    @Test
    public void testFetchFromCassandra() throws ExecutionException, InterruptedException {
        String topic = TestUtils.getDefaultTestTopic(this.getClass());

        ConnectorPropertyFactory connectorPropertyFactory = CamelCassandraPropertyFactory
                .basic()
                .withTopics(topic)
                .withHosts(cassandraService.getCassandraHost())
                .withPort(cassandraService.getCQL3Port())
                .withKeySpace(TestDataDao.KEY_SPACE)
                .withCql(testDataDao.getInsertStatement());

        runTest(connectorPropertyFactory);
    }


    @Test
    public void testFetchFromCassandraWithUrl() throws ExecutionException, InterruptedException {
        String topic = TestUtils.getDefaultTestTopic(this.getClass());

        ConnectorPropertyFactory connectorPropertyFactory = CamelCassandraPropertyFactory
                .basic()
                    .withTopics(topic)
                    .withUrl(cassandraService.getCQL3Endpoint(), TestDataDao.KEY_SPACE)
                    .append("cql", testDataDao.getInsertStatement())
                    .buildUrl();

        runTest(connectorPropertyFactory);

    }
}
