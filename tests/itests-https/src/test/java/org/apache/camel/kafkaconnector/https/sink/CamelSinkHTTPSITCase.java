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

package org.apache.camel.kafkaconnector.https.sink;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.services.mockweb.MockWebService;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CamelSinkHTTPSITCase extends CamelSinkTestSupport {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkHTTPSITCase.class);

    @RegisterExtension
    public final MockWebService mockWebService = MockWebService.builder()
            .useHttps()
            .withKeystore("/server-keystore.jks", "secret")
            .build();
    private MockWebServer mockServer;

    private String topicName;

    private final int expect = 10;
    private List<RecordedRequest> received;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-https-kafka-connector"};
    }

    @BeforeEach
    public void setUp() throws Exception {
        topicName = getTopicForTest(this);

        mockServer = mockWebService.getServer();
        received = Collections.emptyList();
    }

    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try {
            received = IntStream.range(0, expect).mapToObj(i -> {
                try {
                    return mockServer.takeRequest(10, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    LOG.error("Unable to receive messages: {}", e.getMessage(), e);
                    return null;
                }
            }).collect(Collectors.toList());
        } finally {
            latch.countDown();
        }
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        String expected = "Sink test message ";
        if (latch.await(30, TimeUnit.SECONDS)) {
            assertEquals(expect, received.size(), "Did not receive the same amount of messages that were sent");


            for (RecordedRequest request : received) {
                String actual = request.getBody().readUtf8();
                LOG.debug("Received: {} ", actual);

                assertEquals("/ckc", request.getPath(), "Received path differed");
                assertTrue(actual.startsWith(expected), "Received message content differed");
            }

            assertEquals(expect, received.size(), "Did not receive the same amount of messages that were sent");
        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }

    @Test
    @Timeout(60)
    public void testBasicSendReceive() throws Exception {
        mockWebService.enqueueResponses(expect);

        String uri = mockServer.getHostName() + ":" + mockServer.getPort() + "/ckc";
        ConnectorPropertyFactory connectorPropertyFactory = CamelHTTPSPropertyFactory.basic()
                .withTopics(topicName)
                .withHttpUri(uri)
                .withSslContextParameters("scp", toPath("client-truststore.jks"), "secret")
                // let's skip host verification as hostname may vary depending on test env
                .withX509HostnameVerifier("x509HostnameVerifier", NoopHostnameVerifier.class);

        runTest(connectorPropertyFactory, topicName, expect);
    }

    @Test
    @Timeout(60)
    @Disabled("HTTPS-sink-connector duplicates protocol #1077")
    public void testBasicSendReceiveHttpUriWithQueryString() throws Exception {
        mockWebService.enqueueResponses(expect);

        String uri = mockServer.getHostName() + ":" + mockServer.getPort() + "/ckc?aaa=xxx&bbb=yyy&ccc=zzz";
        ConnectorPropertyFactory connectorPropertyFactory = CamelHTTPSPropertyFactory.basic()
                .withTopics(topicName)
                .withHttpUri(uri)
                .withHttpMethod("POST")
                .withSslContextParameters("scp", toPath("client-truststore.jks"), "secret")
                // let's skip host verification as hostname may vary depending on test env
                .withX509HostnameVerifier("x509HostnameVerifier", NoopHostnameVerifier.class);

        runTest(connectorPropertyFactory, topicName, expect);
    }

    @Test
    @Timeout(60)
    public void testBasicSendReceiveUsingUrl() throws Exception {
        mockWebService.enqueueResponses(expect);

        ConnectorPropertyFactory connectorPropertyFactory = CamelHTTPSPropertyFactory.basic()
                .withTopics(topicName)
                .withSslContextParameters("scp", toPath("client-truststore.jks"), "secret")
                // let's skip host verification as hostname may vary depending on test env
                .withX509HostnameVerifier("x509HostnameVerifier", NoopHostnameVerifier.class)
                .withUrl(mockServer.getHostName(), mockServer.getPort(), "ckc")
                .append("sslContextParameters", "#bean:scp")
                .append("x509HostnameVerifier", "#bean:x509HostnameVerifier")
                .buildUrl();

        runTest(connectorPropertyFactory, topicName, expect);
    }

    private String toPath(String resource) {
        URL url = Objects.requireNonNull(getClass().getClassLoader().getResource(resource));
        return url.getPath();
    }
}
