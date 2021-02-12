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
package org.apache.camel.kafkaconnector.syslog.sink;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.common.test.StringMessageProducer;
import org.apache.camel.kafkaconnector.common.utils.NetworkUtils;
import org.apache.camel.kafkaconnector.syslog.services.SyslogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * A simple test case that checks whether the syslog send the expected number of
 * messages
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSinkSyslogITCase extends CamelSinkTestSupport {
    private static final String HOST = "localhost";
    private static final String PROTOCOL = "udp";
    private static final int FREE_PORT = NetworkUtils.getFreePort(HOST, NetworkUtils.Protocol.UDP);
    private static final String TEST_TXT = "<13>1 2020-05-14T14:47:01.198+02:00 nathannever myapp - - [timeQuality tzKnown=\"1\" isSynced=\"1\" syncAccuracy=\"11266\"] FOO BAR!";

    @RegisterExtension
    public static SyslogService service = SyslogService.sinkSyslogServiceFactory(PROTOCOL, HOST, FREE_PORT);

    private String topicName;
    private final int expect = 1;

    private static class CustomProducer extends StringMessageProducer {
        public CustomProducer(String bootstrapServer, String topicName, int count) {
            super(bootstrapServer, topicName, count);
        }

        @Override
        public String testMessageContent(int current) {
            return TEST_TXT;
        }
    }

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-syslog-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);
    }


    @Override
    protected void consumeMessages(CountDownLatch latch) {
        latch.countDown();
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (latch.await(30, TimeUnit.SECONDS)) {
            Exchange exchange = service.getFirstExchangeToBeReceived();
            assertNotNull(exchange, "There should have been an exchange received");
            Message message = exchange.getIn();
            assertNotNull(message, "There should have been a message in the exchange");

            String body = message.getBody(String.class);
            assertNotNull(body, "The message body should not be null");
            assertEquals(TEST_TXT, message.getBody(String.class),
                    "The received message body does not match the expected message");
        } else {
            fail("Timed out wait for data to be added to the Kafka cluster");
        }
    }


    @Test
    @Timeout(90)
    public void testBasicReceive() throws Exception {
        ConnectorPropertyFactory connectorPropertyFactory = CamelSyslogPropertyFactory
                .basic()
                .withTopics(topicName)
                .withHost("localhost")
                .withPort(FREE_PORT)
                .withProtocol("udp");

        runTest(connectorPropertyFactory, new CustomProducer(getKafkaService().getBootstrapServers(), topicName, expect));
    }
}
