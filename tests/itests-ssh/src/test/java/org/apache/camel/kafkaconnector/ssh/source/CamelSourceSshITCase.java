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

package org.apache.camel.kafkaconnector.ssh.source;

import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.kafkaconnector.ssh.services.SshService;
import org.apache.camel.kafkaconnector.ssh.services.SshServiceFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisabledIfSystemProperty(named = "kafka.instance.type", matches = "local-(kafka|strimzi)-container",
        disabledReason = "Hangs when running with the embedded Kafka Connect instance")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSourceSshITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static SshService sshService = SshServiceFactory.createService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceSshITCase.class);

    private final int expect = 1;
    private int received;
    private String oldUserHome = System.getProperty("user.home");

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-ssh-kafka-connector"};
    }

    @BeforeEach
    public void setupKeyHome() {
        System.setProperty("user.home", "target/user-home");
    }

    @AfterEach
    public void tearDownKeyHome() {
        System.setProperty("user.home", oldUserHome);
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

    @Timeout(90)
    @Test
    public void testRetrieveFromSsh() throws ExecutionException, InterruptedException {
        String topic = TestUtils.getDefaultTestTopic(this.getClass());

        ConnectorPropertyFactory connectorPropertyFactory = CamelSshPropertyFactory
                .basic()
                .withKafkaTopic(topic)
                .withHost(sshService.getSshHost())
                .withPort(Integer.toString(sshService.getSshPort()))
                .withDelay(Integer.toString(10000))
                .withUsername("root")
                .withPassword("root")
                .withPollcommand("date")
                .withTransformsConfig("SshTransforms")
                    .withEntry("type", "org.apache.camel.kafkaconnector.ssh.transformers.SshTransforms")
                .end();


        runTest(connectorPropertyFactory);
    }
}
