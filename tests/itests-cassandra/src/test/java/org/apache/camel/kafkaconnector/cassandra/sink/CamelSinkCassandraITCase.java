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

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.cassandra.clients.CassandraClient;
import org.apache.camel.kafkaconnector.cassandra.clients.dao.TestDataDao;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.test.infra.cassandra.services.CassandraService;
import org.apache.camel.test.infra.cassandra.services.CassandraServiceFactory;
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
public class CamelSinkCassandraITCase extends CamelSinkTestSupport {
    @RegisterExtension
    public static CassandraService cassandraService = CassandraServiceFactory.createService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkCassandraITCase.class);

    private CassandraClient cassandraClient;
    private TestDataDao testDataDao;
    private String topicName;

    private final int expect = 10;
    private int received;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-cql-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);
        cassandraClient = new CassandraClient(cassandraService.getCassandraHost(), cassandraService.getCQL3Port());

        testDataDao = cassandraClient.newTestDataDao();

        testDataDao.createKeySpace();
        testDataDao.useKeySpace();
        testDataDao.createTable();

        received = 0;
    }

    @AfterEach
    public void tearDown() {
        if (testDataDao != null) {
            try {
                testDataDao.dropTable();
            } catch (Exception e) {
                LOG.warn("Unable to drop the table: {}", e.getMessage(), e);
            }
        }
    }

    @Override
    protected Map<String, String> messageHeaders(String text, int current) {
        return null;
    }

    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try {
            if (!TestUtils.waitFor(testDataDao::hasEnoughData, (long) expect)) {
                fail("Did not receive enough data");
            }
            testDataDao.getData(this::checkRetrievedData);
        } finally {
            latch.countDown();
        }
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (latch.await(30, TimeUnit.SECONDS)) {
            assertEquals(expect, received,
                    "Didn't process the expected amount of messages: " + received + " != " + expect);
        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }

    private void checkRetrievedData(String data) {
        if (data != null) {
            received++;
        }
    }

    @Timeout(90)
    @Test
    public void testFetchFromCassandra() throws Exception {
        ConnectorPropertyFactory connectorPropertyFactory = CamelCassandraPropertyFactory
                .basic()
                .withTopics(topicName)
                .withHosts(cassandraService.getCassandraHost())
                .withPort(cassandraService.getCQL3Port())
                .withKeySpace(TestDataDao.KEY_SPACE)
                .withCql(testDataDao.getInsertStatement());

        runTest(connectorPropertyFactory, topicName, expect);
    }

    @Timeout(90)
    @Test
    public void testFetchFromCassandraWithUrl() throws Exception {
        ConnectorPropertyFactory connectorPropertyFactory = CamelCassandraPropertyFactory
                .basic()
                    .withTopics(topicName)
                    .withUrl(cassandraService.getCQL3Endpoint(), TestDataDao.KEY_SPACE)
                    .append("cql", testDataDao.getInsertStatement())
                    .buildUrl();

        runTest(connectorPropertyFactory, topicName, expect);
    }
}
