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

package org.apache.camel.kafkaconnector.ssh.sink;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.kafkaconnector.ssh.services.SshService;
import org.apache.camel.kafkaconnector.ssh.services.SshServiceFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.fail;

@DisabledIfSystemProperty(named = "kafka.instance.type", matches = "local-(kafka|strimzi)-container",
        disabledReason = "Hangs when running with the embedded Kafka Connect instance")
public class CamelSinkSshITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static SshService sshService = SshServiceFactory.createService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkSshITCase.class);

    private final int expect = 3;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-ssh-kafka-connector"};
    }

    private void putRecords(CountDownLatch latch) {
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        try {
            for (int i = 0; i < expect; i++) {
                try {
                    kafkaClient.produce(TestUtils.getDefaultTestTopic(this.getClass()), "date");
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

    public void runTest(ConnectorPropertyFactory connectorPropertyFactory) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();

        getKafkaConnectService().initializeConnector(connectorPropertyFactory);

        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> putRecords(latch));

        if (!latch.await(30, TimeUnit.SECONDS)) {
            fail("Timed out wait for data to be added to the Kafka cluster");
        }
    }

    @Timeout(90)
    @Test
    public void testSshCommand() throws ExecutionException, InterruptedException {
        String topic = TestUtils.getDefaultTestTopic(this.getClass());

        ConnectorPropertyFactory connectorPropertyFactory = CamelSshPropertyFactory.basic().withTopics(topic).withHost(sshService.getSshHost())
            .withPort(Integer.toString(sshService.getSshPort())).withUsername("root").withPassword("root");

        runTest(connectorPropertyFactory);
    }
}
