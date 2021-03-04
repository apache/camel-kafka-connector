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

package org.apache.camel.kafkaconnector.nettyhttp.sink;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CamelSinkNettyhttpITCase extends CamelSinkTestSupport {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkNettyhttpITCase.class);

    private MockWebServer mockServer;

    private String topicName;

    private final int expect = 1;
    private volatile RecordedRequest received;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-netty-http-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);
        mockServer = new MockWebServer();
        received = null;
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (mockServer != null) {
            mockServer.shutdown();
        }
    }

    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try {
            received = mockServer.takeRequest();
        } catch (InterruptedException e) {
            LOG.error("Unable to receive messages: {}", e.getMessage(), e);
        } finally {
            latch.countDown();
        }
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        String expected = "Sink test message 0";
        if (latch.await(30, TimeUnit.SECONDS)) {
            assertEquals("/test", received.getPath(), "Received path differed");
            assertEquals(expected, received.getBody().readUtf8(), "Received message content differed");
        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }

    @Test
    @Timeout(30)
    public void testBasicSendReceive() throws Exception {
        ConnectorPropertyFactory connectorPropertyFactory = CamelNettyhttpPropertyFactory.basic()
                .withTopics(topicName)
                .withProtocol("http")
                .withHost(mockServer.getHostName())
                .withPort(mockServer.getPort())
                .withPath("test");

        runTest(connectorPropertyFactory, topicName, expect);
    }

    @Test
    @Timeout(30)
    public void testBasicSendReceiveUsingUrl() throws Exception {
        ConnectorPropertyFactory connectorPropertyFactory = CamelNettyhttpPropertyFactory.basic()
                .withTopics(topicName)
                .withUrl("http", mockServer.getHostName(), mockServer.getPort(), "test")
                .buildUrl();

        runTest(connectorPropertyFactory, topicName, expect);
    }
}
