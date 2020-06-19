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

import com.datastax.driver.core.Row;
import org.apache.camel.kafkaconnector.cassandra.clients.CassandraClient;
import org.apache.camel.kafkaconnector.cassandra.clients.dao.TestDataDao;
import org.apache.camel.kafkaconnector.cassandra.clients.dao.TestResultSetConversionStrategy;
import org.apache.camel.kafkaconnector.cassandra.services.CassandraService;
import org.apache.camel.kafkaconnector.cassandra.services.CassandraServiceFactory;
import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CamelSourceCassandraITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static CassandraService cassandraService = CassandraServiceFactory.createService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceCassandraITCase.class);

    private CassandraClient cassandraClient;
    private TestDataDao testDataDao;

    private final int expect = 1;
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

        for (int i = 0; i < expect; i++) {
            testDataDao.insert("Test data " + i);
        }
    }

    private <T> boolean checkRecord(ConsumerRecord<String, T> record) {

        LOG.debug("Received: {}", record.value());
        received++;

        return false;
    }

    public void runTest(ConnectorPropertyFactory connectorPropertyFactory) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();

        getKafkaConnectService().initializeConnector(connectorPropertyFactory);

        LOG.debug("Creating the consumer ...");
        KafkaClient<String, Row> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        kafkaClient.consume(TestUtils.getDefaultTestTopic(this.getClass()), this::checkRecord);
        LOG.debug("Created the consumer ...");

        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }


    @Timeout(90)
    @Test
    public void testRetrieveFromCassandra() throws ExecutionException, InterruptedException {
        String topic = TestUtils.getDefaultTestTopic(this.getClass());

        ConnectorPropertyFactory connectorPropertyFactory = CamelCassandraPropertyFactory
                .basic()
                .withKafkaTopic(topic)
                .withHosts(cassandraService.getCassandraHost())
                .withPort(cassandraService.getCQL3Port())
                .withKeySpace(TestDataDao.KEY_SPACE)
                .withResultSetConversionStrategy("ONE")
                .withCql(testDataDao.getSelectStatement());

        runTest(connectorPropertyFactory);
    }

    @Disabled("Disabled due to CAMEL-15219")
    @Timeout(90)
    @Test
    public void testRetrieveFromCassandraWithCustomStrategy() throws ExecutionException, InterruptedException {
        String topic = TestUtils.getDefaultTestTopic(this.getClass());

        ConnectorPropertyFactory connectorPropertyFactory = CamelCassandraPropertyFactory
                .basic()
                .withKafkaTopic(topic)
                .withHosts(cassandraService.getCassandraHost())
                .withPort(cassandraService.getCQL3Port())
                .withKeySpace(TestDataDao.KEY_SPACE)
                .withResultSetConversionStrategy("#:" + TestResultSetConversionStrategy.class.getName())
                .withCql(testDataDao.getSelectStatement());

        runTest(connectorPropertyFactory);
    }
}
