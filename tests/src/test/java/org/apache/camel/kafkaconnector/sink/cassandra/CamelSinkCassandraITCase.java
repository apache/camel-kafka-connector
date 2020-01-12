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

package org.apache.camel.kafkaconnector.sink.cassandra;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.TestCommon;
import org.apache.camel.kafkaconnector.clients.cassandra.CassandraClient;
import org.apache.camel.kafkaconnector.clients.cassandra.dao.TestDataDao;
import org.apache.camel.kafkaconnector.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.services.cassandra.CassandraService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CamelSinkCassandraITCase extends AbstractKafkaTest {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkCassandraITCase.class);

    @Rule
    public CassandraService cassandraService = new CassandraService();

    private CassandraClient cassandraClient;
    private TestDataDao testDataDao;


    private final int expect = 10;
    private int received;

    @Before
    public void setUp() {
        String host = cassandraService.getCassandraHost();
        int port = cassandraService.getCQL3Port();

        cassandraClient = new CassandraClient(host, port);

        testDataDao = cassandraClient.newTestDataDao();

        testDataDao.createKeySpace();
        testDataDao.useKeySpace();
        testDataDao.createTable();
    }

    private void putRecords(CountDownLatch latch) {
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        try {
            for (int i = 0; i < expect; i++) {
                try {
                    kafkaClient.produce(TestCommon.getDefaultTestTopic(this.getClass()), "test " + i);
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


    @Test
    public void testFetchFromCassandra() throws ExecutionException, InterruptedException {
        String topic = TestCommon.getDefaultTestTopic(this.getClass());

        ConnectorPropertyFactory testProperties = new CamelCassandraPropertyFactory(1, topic,
                cassandraService.getCQL3Endpoint(), TestDataDao.KEY_SPACE,
                testDataDao.getInsertStatement());

        getKafkaConnectService().initializeConnector(testProperties);

        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> putRecords(latch));

        if (!latch.await(30, TimeUnit.SECONDS)) {
            fail("Timed out wait for data to be added to the Kafka cluster");
        }

        TestCommon.waitFor(testDataDao::hasEnoughData, (long) expect);
        testDataDao.getData(this::checkRetrievedData);
        assertTrue(String.format("Did not receive as much data as expected: %d < %d", received, expect),
                received >= expect);

    }
}
