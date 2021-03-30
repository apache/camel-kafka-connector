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

package org.apache.camel.kafkaconnector.netty.sink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.common.utils.NetworkUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CamelSinkNettyITCase extends CamelSinkTestSupport {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkNettyITCase.class);
    private final int port = NetworkUtils.getFreePort();

    private String topicName;

    private final int expect = 1;
    private volatile String received;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-netty-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);
        received = null;
    }

    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket socket = serverSocket.accept();
             InputStream is = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            received = reader.readLine();
            LOG.debug("Received: {}", received);
        } catch (IOException e) {
            LOG.error("Unable to receive messages: {}", e.getMessage(), e);
        } finally {
            latch.countDown();
        }
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        String expected = "Sink test message 0";
        if (latch.await(30, TimeUnit.SECONDS)) {
            assertEquals(expected, received, "Received message content differed");
        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }

    @Test
    @Timeout(30)
    public void testBasicSendReceive() throws Exception {
        ConnectorPropertyFactory connectorPropertyFactory = CamelNettyPropertyFactory.basic()
                .withTopics(topicName)
                .withProtocol("tcp")
                .withHost(NetworkUtils.getHostname())
                .withPort(port)
                // disconnect so that it won't keep mock server socket forever
                .withDisconnect(true)
                // one-way as mock server doesn't send replies
                .withSync(false);

        runTest(connectorPropertyFactory, topicName, expect);
    }

    @Test
    @Timeout(30)
    public void testBasicSendReceiveUsingUrl() throws Exception {
        ConnectorPropertyFactory connectorPropertyFactory = CamelNettyPropertyFactory.basic()
                .withTopics(topicName)
                .withUrl("tcp", NetworkUtils.getHostname(), port)
                // disconnect so that it won't keep mock server socket forever
                .append("disconnect", "true")
                // one-way as mock server doesn't send replies
                .append("sync", "false")
                .buildUrl();

        runTest(connectorPropertyFactory, topicName, expect);
    }
}
