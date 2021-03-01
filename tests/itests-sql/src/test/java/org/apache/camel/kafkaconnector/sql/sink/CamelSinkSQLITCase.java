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

package org.apache.camel.kafkaconnector.sql.sink;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.CamelSinkTask;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.common.test.StringMessageProducer;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.kafkaconnector.sql.client.DatabaseClient;
import org.apache.camel.kafkaconnector.sql.services.TestDataSource;
import org.apache.camel.test.infra.jdbc.services.JDBCService;
import org.apache.camel.test.infra.jdbc.services.JDBCServiceBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CamelSinkSQLITCase extends CamelSinkTestSupport {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkSQLITCase.class);

    @RegisterExtension
    public JDBCService sqlService;
    private DatabaseClient client;

    private String topicName;
    private final int expect = 1;
    private int received;

    private static class CustomProducer extends StringMessageProducer {
        public CustomProducer(String bootstrapServer, String topicName, int count) {
            super(bootstrapServer, topicName, count);
        }

        @Override
        public String testMessageContent(int current) {
            return "test";
        }

        @Override
        public Map<String, String> messageHeaders(String text, int current) {
            Map<String, String> sqlParameters = new HashMap<>();

            // The prefix 'CamelHeader' is removed by the SinkTask
            sqlParameters.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "TestName", "SomeName" + TestUtils.randomWithRange(0, 100));
            sqlParameters.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "TestData", "test data " + current);

            return sqlParameters;
        }
    }

    public CamelSinkSQLITCase() {
        JdbcDatabaseContainer<?> container = new PostgreSQLContainer<>("postgres:9.6.2")
                .withDatabaseName("camel")
                .withUsername("ckc")
                .withPassword("ckcDevel123")
                .withInitScript("schema.sql")
                .withStartupTimeoutSeconds(60);

        sqlService = JDBCServiceBuilder.newBuilder()
                .withContainer(container)
                .build();

        sqlService.initialize();
    }

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-sql-kafka-connector"};
    }

    @BeforeEach
    public void setUp() throws SQLException {
        topicName = getTopicForTest(this);
        client = new DatabaseClient(sqlService.jdbcUrl());
    }


    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try {
            TestUtils.waitFor(() -> {
                try {
                    return client.hasAtLeastRecords("test", expect);
                } catch (SQLException e) {
                    LOG.warn("Failed to read the test table: {}", e.getMessage(), e);
                    return false;
                }
            });
        } finally {
            latch.countDown();
        }
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (latch.await(25, TimeUnit.SECONDS)) {
            try {
                client.runQuery("select * from test", this::verifyData);
                assertEquals(expect, received, "Did not receive as much data as expected");
            } catch (SQLException e) {
                fail(e.getMessage());
            }
        } else {
            fail("Failed to receive the messages within the specified time");
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

    @Timeout(60)
    @Test
    public void testDBFetch() throws Exception {
        CamelSqlPropertyFactory factory = CamelSqlPropertyFactory
                .basic()
                .withDataSource(CamelSqlPropertyFactory.classRef(TestDataSource.class.getName()))
                .withQuery("insert into test(test_name, test_data) values(:#TestName,:#TestData)")
                .withTopics(topicName);

        runTest(factory, new CustomProducer(getKafkaService().getBootstrapServers(), topicName, expect));
    }
}
