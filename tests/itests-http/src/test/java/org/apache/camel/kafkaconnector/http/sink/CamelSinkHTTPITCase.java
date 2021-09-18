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

package org.apache.camel.kafkaconnector.http.sink;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.common.utils.NetworkUtils;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSinkHTTPITCase extends CamelSinkTestSupport {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkHTTPITCase.class);

    private HttpServer localServer;

    private HTTPTestValidationHandler validationHandler;
    private List<String> replies;
    private String topicName;

    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-http-sink-kafka-connector"};
    }

    @BeforeEach
    public void setUp() throws IOException {
        topicName = getTopicForTest(this);

        validationHandler = new HTTPTestValidationHandler(10);
        byte[] ipAddr = new byte[]{127, 0, 0, 1};
        InetAddress localhost = InetAddress.getByAddress(ipAddr);
        localServer = ServerBootstrap.bootstrap()
                .setLocalAddress(localhost)
                .setListenerPort(NetworkUtils.getFreePort())
                .registerHandler("/ckc", validationHandler)
                .create();

        localServer.start();
    }

    @AfterEach
    public void tearDown() {
        try {
            localServer.stop();
        } finally {
            localServer.shutdown(2, TimeUnit.SECONDS);
        }
    }


    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try {
            replies = validationHandler
                    .getReplies()
                    .get(30, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            LOG.error("Unable to ret replies: {}", e.getMessage(), e);
        } finally {
            latch.countDown();
        }
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (latch.await(30, TimeUnit.SECONDS)) {
            if (replies == null) {
                fail("Some messages should have been exchanged, but none seems to have gone through");
            }

            for (String reply : replies) {
                LOG.debug("Received: {} ", reply);
            }

            assertEquals(replies.size(), expect, "Did not receive the same amount of messages that were sent");
        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }


    @Test
    @Timeout(90)
    public void testBasicSendReceive() throws Exception {
        String url = "http://" + localServer.getInetAddress().getHostName() + ":" + localServer.getLocalPort() + "/ckc";

        ConnectorPropertyFactory connectorPropertyFactory = CamelHTTPPropertyFactory.basic()
                .withTopics(topicName)
                .withHttpUrl(url);

        runTest(connectorPropertyFactory, topicName, expect);
    }
}
