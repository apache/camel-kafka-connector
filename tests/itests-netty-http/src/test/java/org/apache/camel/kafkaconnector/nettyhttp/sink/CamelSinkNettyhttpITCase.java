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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CamelSinkNettyhttpITCase extends AbstractKafkaTest {
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
        topicName = TestUtils.getDefaultTestTopic(this.getClass());
        mockServer = new MockWebServer();
        received = null;
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (mockServer != null) {
            mockServer.shutdown();
        }
    }

    protected void verifyMessages() throws InterruptedException {
        String expected = "test 0";
        try {
            received = mockServer.takeRequest(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOG.error("Unable to receive http requests: {}", e.getMessage(), e);
            fail("Failed to receive the messages within the specified time");
        }
        assertEquals("/test", received.getPath(), "Received path differed");
        assertEquals(expected, received.getBody().readUtf8(), "Received message content differed");
    }

    private void putRecords() {
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        for (int i = 0; i < expect; i++) {
            try {
                kafkaClient.produce(topicName, "test " + i);
            } catch (ExecutionException e) {
                LOG.error("Unable to produce messages: {}", e.getMessage(), e);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void runTest(ConnectorPropertyFactory connectorPropertyFactory) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();

        getKafkaConnectService().initializeConnector(connectorPropertyFactory);

        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> putRecords());

        verifyMessages();
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
        mockServer.enqueue(new MockResponse().setResponseCode(200));
        runTest(connectorPropertyFactory);
    }

    @Test
    @Timeout(30)
    public void testBasicSendReceiveUsingUrl() throws Exception {
        ConnectorPropertyFactory connectorPropertyFactory = CamelNettyhttpPropertyFactory.basic()
                .withTopics(topicName)
                .withUrl("http", mockServer.getHostName(), mockServer.getPort(), "test")
                .buildUrl();
        mockServer.enqueue(new MockResponse().setResponseCode(200));
        runTest(connectorPropertyFactory);
    }
}
