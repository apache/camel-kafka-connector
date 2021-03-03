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

package org.apache.camel.kafkaconnector.netty.source;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.common.test.CamelSourceTestSupport;
import org.apache.camel.kafkaconnector.common.test.TestMessageConsumer;
import org.apache.camel.kafkaconnector.common.utils.NetworkUtils;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CamelSourceNettyITCase extends CamelSourceTestSupport {
    private final int port = NetworkUtils.getFreePort();

    private final int expect = 1;
    private String topicName;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-netty-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);
    }

    @Override
    protected void produceTestData() {
        TestUtils.waitFor(() -> NetworkUtils.portIsOpen(NetworkUtils.getHostname(), port));

        sendMessage();
    }

    void sendMessage() {
        try (Socket s = new Socket(NetworkUtils.getHostname(), port);
             PrintWriter out = new PrintWriter(s.getOutputStream())) {
            out.print("Hello CKC!");
            out.flush();
        } catch (Exception e) {
            fail(e.getMessage(), e);
        }
    }

    @Override
    protected void verifyMessages(TestMessageConsumer<?> consumer) {
        int received = consumer.consumedMessages().size();
        Object receivedObject = consumer.consumedMessages().get(0).value();
        assertEquals(expect, received, "Did not receive as many messages as expected");
        assertEquals("Hello CKC!", receivedObject, "Received message content differed");
    }

    @Test
    @Timeout(35)
    public void testLaunchConnector() throws ExecutionException, InterruptedException {
        CamelNettyPropertyFactory connectorPropertyFactory = CamelNettyPropertyFactory
                .basic()
                .withKafkaTopic(topicName)
                .withProtocol("tcp")
                // TODO https://github.com/apache/camel-kafka-connector/issues/924
                .withHost("//" + NetworkUtils.getHostname())
                .withPort(port)
                // one-way as test client doesn't receive response
                .withSync(false);

        runTestBlocking(connectorPropertyFactory, topicName, expect);
    }

    @Test
    @Timeout(35)
    public void testLaunchConnectorUsingUrl() throws ExecutionException, InterruptedException {
        CamelNettyPropertyFactory connectorPropertyFactory = CamelNettyPropertyFactory
                .basic()
                .withKafkaTopic(topicName)
                .withUrl("tcp", NetworkUtils.getHostname(), port)
                // one-way as test client doesn't receive response
                .append("sync", "false")
                .buildUrl();

        runTestBlocking(connectorPropertyFactory, topicName, expect);
    }
}
