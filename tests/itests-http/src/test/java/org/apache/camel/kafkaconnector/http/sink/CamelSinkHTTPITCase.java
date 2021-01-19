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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.NetworkUtils;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
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
public class CamelSinkHTTPITCase extends AbstractKafkaTest {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkHTTPITCase.class);
    private static final int HTTP_PORT = NetworkUtils.getFreePort("localhost");

    private HttpServer localServer;

    private HTTPTestValidationHandler validationHandler;

    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-http-kafka-connector"};
    }

    @BeforeEach
    public void setUp() throws IOException {
        validationHandler = new HTTPTestValidationHandler(10);
        byte[] ipAddr = new byte[]{127, 0, 0, 1};
        InetAddress localhost = InetAddress.getByAddress(ipAddr);
        localServer = ServerBootstrap.bootstrap()
                .setLocalAddress(localhost)
                .setListenerPort(HTTP_PORT)
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


    private void putRecords() {
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        for (int i = 0; i < expect; i++) {
            try {
                kafkaClient.produce(TestUtils.getDefaultTestTopic(this.getClass()), "test");
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void runTest(ConnectorPropertyFactory connectorPropertyFactory) throws ExecutionException, InterruptedException, TimeoutException {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnector(connectorPropertyFactory);

        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(this::putRecords);

        LOG.debug("Created the consumer ... About to receive messages");

        List<String> replies = validationHandler.getReplies().get(30, TimeUnit.SECONDS);
        if (replies == null) {
            fail("Some messages should have been exchanged, but none seems to have gone through");
        }

        for (String reply : replies) {
            LOG.debug("Received: {} ", reply);
        }

        assertEquals(replies.size(), expect, "Did not receive the same amount of messages that were sent");

    }

    @Test
    @Timeout(90)
    public void testBasicSendReceive() {
        try {
            String url = localServer.getInetAddress().getHostName() + ":" + HTTP_PORT + "/ckc";

            ConnectorPropertyFactory connectorPropertyFactory = CamelHTTPPropertyFactory.basic()
                    .withTopics(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withHttpUri(url);

            runTest(connectorPropertyFactory);
        } catch (Exception e) {
            LOG.error("HTTP test failed: {} {}", e.getMessage(), e);
            fail(e.getMessage(), e);
        }
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceiveUsingUrl() {
        try {
            String hostName = localServer.getInetAddress().getHostName() + ":" + HTTP_PORT + "/ckc";

            ConnectorPropertyFactory connectorPropertyFactory = CamelHTTPPropertyFactory.basic()
                    .withTopics(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withUrl(hostName)
                        .buildUrl();


            runTest(connectorPropertyFactory);
        } catch (Exception e) {
            LOG.error("HTTP test failed: {} {}", e.getMessage(), e);
            fail(e.getMessage(), e);
        }
    }
}
