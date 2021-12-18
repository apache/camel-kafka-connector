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

package org.apache.camel.kafkaconnector.cassandra.source;

import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.cassandra.clients.CassandraClient;
import org.apache.camel.kafkaconnector.cassandra.clients.dao.TestDataDao;
import org.apache.camel.kafkaconnector.cassandra.clients.dao.TestResultSetConversionStrategy;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSourceTestSupport;
import org.apache.camel.kafkaconnector.common.test.TestMessageConsumer;
import org.apache.camel.test.infra.cassandra.services.CassandraService;
import org.apache.camel.test.infra.cassandra.services.CassandraServiceFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.camel.kafkaconnector.common.BasicConnectorPropertyFactory.classRef;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Disabled("TODO: Enable and convert once https://github.com/apache/camel-kamelets/pull/636 is published in kamelet-catalog")
public class CamelSourceCassandraITCase extends CamelSourceTestSupport {
    @RegisterExtension
    public static CassandraService cassandraService = CassandraServiceFactory.createService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceCassandraITCase.class);

    private CassandraClient cassandraClient;
    private TestDataDao testDataDao;
    private String topicName;

    private final int expect = 1;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-cql-kafka-connector"};
    }

    @BeforeAll
    public void setUpTestData() {
        cassandraClient = new CassandraClient(cassandraService.getCassandraHost(), cassandraService.getCQL3Port());

        testDataDao = cassandraClient.newTestDataDao();

        testDataDao.createKeySpace();
        testDataDao.useKeySpace();
        testDataDao.createTable();

        for (int i = 0; i < expect; i++) {
            testDataDao.insert("Test data " + i);
        }
    }

    @BeforeEach
    public void setUpTest() {
        topicName = getTopicForTest(this);
    }

    @AfterAll
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
    protected void produceTestData() {
        // NO-OP (done at the testSetup)
    }

    @Override
    protected void verifyMessages(TestMessageConsumer<?> consumer) {
        int received = consumer.consumedMessages().size();

        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }

    @Timeout(90)
    @Test
    public void testRetrieveFromCassandra() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelCassandraPropertyFactory
                .basic()
                .withKafkaTopic(topicName)
                .withHosts(cassandraService.getCassandraHost())
                .withPort(cassandraService.getCQL3Port())
                .withKeySpace(TestDataDao.KEY_SPACE)
                .withResultSetConversionStrategy("ONE")
                .withCql(testDataDao.getSelectStatement());

        runTest(connectorPropertyFactory, topicName, expect);
    }

    @Timeout(90)
    @Test
    public void testRetrieveFromCassandraWithCustomStrategy() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelCassandraPropertyFactory
                .basic()
                .withKafkaTopic(topicName)
                .withHosts(cassandraService.getCassandraHost())
                .withPort(cassandraService.getCQL3Port())
                .withKeySpace(TestDataDao.KEY_SPACE)
                .withResultSetConversionStrategy(classRef(TestResultSetConversionStrategy.class.getName()))
                .withCql(testDataDao.getSelectStatement());

        runTest(connectorPropertyFactory, topicName, expect);
    }
}
