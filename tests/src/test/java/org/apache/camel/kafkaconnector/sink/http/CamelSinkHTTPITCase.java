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

package org.apache.camel.kafkaconnector.sink.http;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.KafkaConnectRunner;
import org.apache.camel.kafkaconnector.TestCommon;
import org.apache.camel.kafkaconnector.clients.kafka.KafkaClient;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.fail;

public class CamelSinkHTTPITCase extends AbstractKafkaTest {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkHTTPITCase.class);
    private static final int HTTP_PORT = 18080;

    private HttpServer localServer;

    private KafkaConnectRunner kafkaConnectRunner;
    private HTTPTestValidationHandler validationHandler;

    private final int expect = 10;

    @Before
    public void setUp() throws IOException {
        validationHandler = new HTTPTestValidationHandler(10);
        localServer = ServerBootstrap.bootstrap()
                .setListenerPort(HTTP_PORT)
                .registerHandler("/ckc", validationHandler)
                .create();

        localServer.start();

        String url = "http://localhost:" + HTTP_PORT + "/ckc";
        ConnectorPropertyFactory testProperties = new CamelHTTPPropertyFactory(1,
                TestCommon.getDefaultTestTopic(this.getClass()), url);

        kafkaConnectRunner = getKafkaConnectRunner();
        kafkaConnectRunner.getConnectorPropertyProducers().add(testProperties);
    }

    @After
    public void tearDown() {
        localServer.stop();
    }


    private void putRecords() {
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        for (int i = 0; i < expect; i++) {
            try {
                kafkaClient.produce(TestCommon.getDefaultTestTopic(this.getClass()), "test");
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Test(timeout = 90000)
    public void testBasicSendReceive() {
        try {
            ExecutorService service = Executors.newCachedThreadPool();
            service.submit(() -> kafkaConnectRunner.run());
            service.submit(this::putRecords);

            LOG.debug("Created the consumer ... About to receive messages");

            List<String> replies = validationHandler.getReplies().get(30, TimeUnit.SECONDS);
            if (replies == null) {
                fail("Some messages should have been exchanged, but none seems to have gone through");
            }

            for (String reply : replies) {
                LOG.debug("Received: {} ", reply);
            }

            Assert.assertEquals("Did not receive the same amount of messages that were sent", replies.size(),
                    expect);

            kafkaConnectRunner.stop();
        } catch (Exception e) {
            LOG.error("HTTP test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }
}
