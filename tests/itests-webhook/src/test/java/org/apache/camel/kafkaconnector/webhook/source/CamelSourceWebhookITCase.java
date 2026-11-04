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
package org.apache.camel.kafkaconnector.webhook.source;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSourceTestSupport;
import org.apache.camel.kafkaconnector.common.test.TestMessageConsumer;
import org.apache.camel.kafkaconnector.common.utils.NetworkUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Starts the webhook source connector with the embedded HTTP server enabled, sends a request to the path the Kamelet
 * exposes and checks that its body lands on the Kafka topic.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSourceWebhookITCase extends CamelSourceTestSupport {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceWebhookITCase.class);
    private static final String HTTP_HOST = "127.0.0.1";
    private static final int HTTP_PORT = NetworkUtils.getFreePort(HTTP_HOST);
    private static final String SUBPATH = "ckc-webhook";
    private static final String TEST_MESSAGE = "{\"event\":\"ckc-webhook-test\"}";

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final int expect = 1;
    private String topicName;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-webhook-source-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceive() throws Exception {
        ConnectorPropertyFactory connectorPropertyFactory = CamelWebhookPropertyFactory.basic()
                .withKafkaTopic(topicName)
                .withServerEnabled(true)
                .withServerHost(HTTP_HOST)
                .withServerPort(HTTP_PORT)
                .withSubpath(SUBPATH)
                .withCamelTypeConverterTransformTo("java.lang.String");

        runTestBlocking(connectorPropertyFactory, topicName, expect);
    }

    @Override
    protected void produceTestData() {
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://" + HTTP_HOST + ":" + HTTP_PORT + "/" + SUBPATH))
                .timeout(Duration.ofSeconds(30))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(TEST_MESSAGE))
                .build();

        // The listener only exists once the task has started, so the first attempts may be refused. The response is
        // only sent back once the record has been written to Kafka.
        Awaitility.await()
                .atMost(Duration.ofSeconds(60))
                .pollInterval(Duration.ofSeconds(1))
                .ignoreExceptions()
                .untilAsserted(() -> {
                    LOG.info("Sending {} {}", request.method(), request.uri());
                    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                    assertEquals(200, response.statusCode());
                });
    }

    @Override
    protected void verifyMessages(TestMessageConsumer<?> consumer) {
        int received = consumer.consumedMessages().size();
        assertEquals(expect, received, "Didn't process the expected amount of messages");

        ConsumerRecord<String, ?> record = consumer.consumedMessages().get(0);
        assertEquals(TEST_MESSAGE, record.value().toString());

        // the request metadata is mapped to record headers
        Header path = record.headers().lastHeader("CamelHeader.CamelHttpPath");
        assertNotNull(path, "The record should carry the HTTP path of the request");
        assertEquals("/" + SUBPATH, new String(path.value(), StandardCharsets.UTF_8));
    }
}
