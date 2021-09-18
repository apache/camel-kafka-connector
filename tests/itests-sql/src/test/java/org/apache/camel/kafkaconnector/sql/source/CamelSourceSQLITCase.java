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

package org.apache.camel.kafkaconnector.sql.source;

import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.common.test.CamelSourceTestSupport;
import org.apache.camel.kafkaconnector.common.test.TestMessageConsumer;
import org.apache.camel.test.infra.jdbc.services.JDBCService;
import org.apache.camel.test.infra.jdbc.services.JDBCServiceBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@DisabledIfSystemProperty(named = "kafka.instance.type", matches = "local-(kafka|strimzi)-container",
//        disabledReason = "Database connection fails when running with the embedded Kafka Connect instance")
public class CamelSourceSQLITCase extends CamelSourceTestSupport {
    private static final String DATABASE_NAME = "camel";
    private static final String USERNAME = "ckc";
    private static final String PASSWORD = "ckcDevel123";

    @RegisterExtension
    public JDBCService sqlService;

    private final int expect = 1;

    private String hostname;
    private String port;

    public CamelSourceSQLITCase() {
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

        hostname = container.getContainerIpAddress();
        port = String.valueOf(container.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT));
    }

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-postgresql-source-kafka-connector"};
    }

    @Override
    protected void produceTestData() {
        // NO-OP, already done via init script in the service initialization
    }

    @Override
    protected void verifyMessages(TestMessageConsumer<?> consumer) {
        int received = consumer.consumedMessages().size();
        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }

    @Timeout(30)
    @Test
    public void testDBFetch() throws ExecutionException, InterruptedException {
        String topicName = getTopicForTest(this);

        CamelSqlPropertyFactory factory = CamelSqlPropertyFactory
                .basic()
                .withDatabaseName(DATABASE_NAME)
                .withServerName(hostname)
                .withPort(port)
                .withUsername(USERNAME)
                .withPassword(PASSWORD)
                .withQuery("select * from test")
                .withTopics(topicName);

        runTest(factory, topicName, expect);

    }
}
