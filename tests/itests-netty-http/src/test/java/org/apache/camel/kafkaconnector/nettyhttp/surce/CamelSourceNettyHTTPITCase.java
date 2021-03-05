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

package org.apache.camel.kafkaconnector.nettyhttp.surce;

import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSourceTestSupport;
import org.apache.camel.kafkaconnector.common.test.TestMessageConsumer;
import org.apache.camel.kafkaconnector.common.utils.NetworkUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSourceNettyHTTPITCase extends CamelSourceTestSupport {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceNettyHTTPITCase.class);
    private static final int HTTP_PORT = NetworkUtils.getFreePort("localhost");
    private static final String TEST_MESSAGE = "testMessage";

    private String topicName;

    private final int expect = 1;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-netty-http-kafka-connector"};
    }

    @BeforeEach
    public void setUp() throws IOException {
        topicName = getTopicForTest(this);
    }

    @AfterEach
    public void tearDown() {}

    @Test
    @Timeout(90)
    public void testBasicSendReceive() throws Exception {

        ConnectorPropertyFactory connectorPropertyFactory = CamelNettyHTTPPropertyFactory.basic()
                .withKafkaTopic(topicName)
                .withReceiveBufferSize(10)
                .withHost("0.0.0.0")
                .withPort(HTTP_PORT)
                .withProtocol("http")
                .withCamelTypeConverterTransformTo("java.lang.String");

        runTestBlocking(connectorPropertyFactory, topicName, expect);
    }

    @Override
    protected void produceTestData() {
        int retriesLeft = 10;
        boolean success = false;
        while(retriesLeft > 0 && !success) {
            try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {

                byte[] ipAddr = new byte[]{127, 0, 0, 1};
                InetAddress localhost = InetAddress.getByAddress(ipAddr);
                final HttpPost httpPost = new HttpPost("http://" + localhost.getHostAddress() + ":" + HTTP_PORT);

                LOG.info("Executing request {} {}", httpPost.getMethod(), httpPost.getURI());

                httpPost.setEntity(new StringEntity(TEST_MESSAGE));

                CloseableHttpResponse response = httpclient.execute(httpPost);
                assertEquals(200, response.getStatusLine().getStatusCode());
                response.close();
                httpPost.releaseConnection();
                success = true;
                LOG.info("Request success at {} attempt.", retriesLeft);
            } catch (IOException e) {
                if(retriesLeft == 1) {
                    e.printStackTrace();
                    fail("There should be no exceptions in sending the http test message.");
                } else {
                    retriesLeft--;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            }
        }
    }

    protected void verifyMessages(TestMessageConsumer<?> consumer) {
        int received = consumer.consumedMessages().size();
        assertEquals(expect, received, "Didn't process the expected amount of messages");
        assertEquals(TEST_MESSAGE, consumer.consumedMessages().get(0).value().toString());
    }
}
