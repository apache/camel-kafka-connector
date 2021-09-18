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

import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSourceTestSupport;
import org.apache.camel.kafkaconnector.common.test.TestMessageConsumer;
import org.apache.camel.kafkaconnector.ssh.services.SshService;
import org.apache.camel.kafkaconnector.ssh.services.SshServiceFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisabledIfSystemProperty(named = "kafka.instance.type", matches = "local-(kafka|strimzi)-container",
        disabledReason = "Hangs when running with the embedded Kafka Connect instance")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSourceSshITCase extends CamelSourceTestSupport {
    @RegisterExtension
    public static SshService sshService = SshServiceFactory.createService();

    private final int expect = 1;
    private String oldUserHome = System.getProperty("user.home");

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-ssh-source-kafka-connector"};
    }

    @BeforeAll
    public void setupKeyHome() {
        System.setProperty("user.home", "target/user-home");
    }

    @AfterAll
    public void tearDownKeyHome() {
        System.setProperty("user.home", oldUserHome);
    }

    @Override
    protected void produceTestData() {

    }

    @Override
    protected void verifyMessages(TestMessageConsumer<?> consumer) {
        int received = consumer.consumedMessages().size();
        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }


    @Timeout(90)
    @Test
    public void testRetrieveFromSsh() throws ExecutionException, InterruptedException {
        String topic = getTopicForTest(this);

        ConnectorPropertyFactory connectorPropertyFactory = CamelSshPropertyFactory
                .basic()
                .withKafkaTopic(topic)
                .withHost(sshService.getSshHost())
                .withPort(Integer.toString(sshService.getSshPort()))
                .withUsername("root")
                .withPassword("root")
                .withPollcommand("date")
                .withTransformsConfig("SshTransforms")
                    .withEntry("type", "org.apache.camel.kafkaconnector.transforms.CamelTypeConverterTransform$Value")
                    .withEntry("target.type", "java.lang.String")
                .end();

        runTest(connectorPropertyFactory, topic, expect);
    }
}
