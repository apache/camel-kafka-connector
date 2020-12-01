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

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.kafkaconnector.sql.client.DatabaseClient;
import org.apache.camel.kafkaconnector.sql.services.SQLService;
import org.apache.camel.kafkaconnector.sql.services.SQLServiceFactory;
import org.apache.camel.kafkaconnector.sql.services.TestDataSource;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

@Testcontainers
public class CamelSourceSQLITCase extends AbstractKafkaTest {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceSQLITCase.class);

    @RegisterExtension
    public SQLService sqlService = SQLServiceFactory.createService();

    private final int expect = 1;
    private int received;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-sql-kafka-connector"};
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
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        kafkaClient.consume(TestUtils.getDefaultTestTopic(this.getClass()), this::checkRecord);
        LOG.debug("Created the consumer ...");

        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }

    @Timeout(10)
    @Test
    public void testDBFetch() throws ExecutionException, InterruptedException {
        CamelSqlPropertyFactory factory = CamelSqlPropertyFactory.basic()
                .withDataSource(CamelSqlPropertyFactory.classRef(TestDataSource.class.getName()))
                .withQuery("select * from test")
                .withTopics(TestUtils.getDefaultTestTopic(this.getClass()));

        runTest(factory);

    }
}
