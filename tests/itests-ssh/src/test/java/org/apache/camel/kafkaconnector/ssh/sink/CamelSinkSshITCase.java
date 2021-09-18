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
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.common.test.StringMessageProducer;
import org.apache.camel.kafkaconnector.ssh.services.SshService;
import org.apache.camel.kafkaconnector.ssh.services.SshServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.fail;

@Disabled("Sink ssh kamelet needs to be implemented see: https://github.com/apache/camel-kamelets/issues/504")
//@DisabledIfSystemProperty(named = "kafka.instance.type", matches = "local-(kafka|strimzi)-container",
//        disabledReason = "Hangs when running with the embedded Kafka Connect instance")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSinkSshITCase extends CamelSinkTestSupport {
    @RegisterExtension
    public static SshService sshService = SshServiceFactory.createService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkSshITCase.class);

    private final int expect = 3;
    private String topic;

    private static class CustomProducer extends StringMessageProducer {
        public CustomProducer(String bootstrapServer, String topicName, int count) {
            super(bootstrapServer, topicName, count);
        }

        @Override
        public String testMessageContent(int current) {
            return "date";
        }
    }

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-ssh-sink-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        topic = getTopicForTest(this);
    }


    @Override
    protected void consumeMessages(CountDownLatch latch) {
        latch.countDown();
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (!latch.await(30, TimeUnit.SECONDS)) {
            fail("Timed out wait for data to be added to the Kafka cluster");
        }
    }

    @Timeout(90)
    @Test
    public void testSshCommand() throws Exception {
        ConnectorPropertyFactory connectorPropertyFactory = CamelSshPropertyFactory
                .basic()
                .withTopics(topic)
                .withHost(sshService.getSshHost())
                .withPort(Integer.toString(sshService.getSshPort()))
                .withUsername("root")
                .withPassword("root");

        runTestNonBlocking(connectorPropertyFactory, new CustomProducer(getKafkaService().getBootstrapServers(), topic, expect));
    }
}
