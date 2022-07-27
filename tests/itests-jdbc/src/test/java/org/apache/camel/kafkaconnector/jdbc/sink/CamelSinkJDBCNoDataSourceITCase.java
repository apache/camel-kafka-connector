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

package org.apache.camel.kafkaconnector.jdbc.sink;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.CamelSinkTask;
import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.jdbc.client.DatabaseClient;
import org.apache.camel.test.infra.common.TestUtils;
import org.apache.camel.test.infra.jdbc.services.JDBCService;
import org.apache.camel.test.infra.jdbc.services.JDBCServiceBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSinkJDBCNoDataSourceITCase extends AbstractKafkaTest {
    @RegisterExtension
    static JDBCService jdbcService;

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkJDBCNoDataSourceITCase.class);

    private final int expect = 10;
    private int received;
    private String topicName;

    static {
        final String postgresImage = "postgres:13.0";

        JdbcDatabaseContainer<?> container = new PostgreSQLContainer<>(postgresImage)
                .withDatabaseName("camel")
                .withUsername("ckc")
                .withPassword("ckcDevel123")
                .withInitScript("schema.sql")
                .withStartupTimeoutSeconds(60);

        // Let the JDBC Service handle container lifecycle
        jdbcService = JDBCServiceBuilder.newBuilder()
                .withContainer(container)
                .build();
    }

    @BeforeEach
    void setUp() {
        topicName = getTopicForTest(this);
    }

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-jdbc-kafka-connector"};
    }

    private void putRecords(CountDownLatch latch) {
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        String body = "insert into test(test_name, test_data) values(:?TestName, :?TestData)";

        try {
            for (int i = 0; i < expect; i++) {
                Map<String, String> jdbcParameters = new HashMap<>();

                // The prefix 'CamelHeader' is removed by the SinkTask
                jdbcParameters.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "TestName", "SomeName" + TestUtils.randomWithRange(0, 100));
                jdbcParameters.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "TestData", "test data " + i);

                try {
                    kafkaClient.produce(topicName, body, jdbcParameters);
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

    private void verifyData(ResultSet rs) {
        try {
            received++;
            String testName = rs.getString("test_name");
            String testData = rs.getString("test_data");

            assertTrue(testName.startsWith("SomeName"), String.format("Unexpected test name %s", testName));
            assertTrue(testData.startsWith("test data"), String.format("Unexpected test data %s", testData));

        } catch (SQLException e) {
            LOG.error("Unable to fetch record from result set: {}", e.getMessage(), e);
            fail(String.format("Unable to fetch record from result set: %s", e.getMessage()));
        }
    }

    public void runTest(ConnectorPropertyFactory propertyFactory) throws ExecutionException, InterruptedException {
        propertyFactory.log();
        getKafkaConnectService().initializeConnectorBlocking(propertyFactory, 1);

        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> putRecords(latch));

        if (!latch.await(30, TimeUnit.SECONDS)) {
            fail("Timed out wait for data to be added to the Kafka cluster");
        }

        LOG.debug("Waiting for indices");

        try {
            DatabaseClient client = new DatabaseClient(jdbcService.jdbcUrl());

            TestUtils.waitFor(() -> {
                try {
                    return client.hasAtLeastRecords("test", expect);
                } catch (SQLException e) {
                    LOG.warn("Failed to read the test table: {}", e.getMessage(), e);
                    return false;
                }
            });

            client.runQuery("select * from test", this::verifyData);
        } catch (SQLException e) {
            LOG.error("Unable to execute the SQL query: {}", e.getMessage(), e);
            fail(e.getMessage());
        }

        assertEquals(expect, received, "Did not receive the same amount of messages sent");
        LOG.debug("Created the consumer ... About to receive messages");
    }

    @Test
    public void testDBFetch() throws ExecutionException, InterruptedException {
        CamelJDBCPropertyFactory factory = CamelJDBCPropertyFactory.basic()
                .withComponentConfig("camel.component.jdbc.dataSource", "#class:org.postgresql.ds.PGSimpleDataSource")
                    .withEntry("user", "ckc")
                    .withEntry("password", "ckcDevel123")
                    .withEntry("url", jdbcService.jdbcUrl())
                    .end()
                .withDataSourceName("anotherName")
                .withUseHeaderAsParameters(true)
                .withTopics(topicName);

        runTest(factory);

    }
}
