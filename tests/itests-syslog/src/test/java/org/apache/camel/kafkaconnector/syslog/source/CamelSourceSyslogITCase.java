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

package org.apache.camel.kafkaconnector.syslog.source;

import java.util.concurrent.ExecutionException;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.syslog.SyslogDataFormat;
import org.apache.camel.component.syslog.netty.Rfc5425Encoder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.test.CamelSourceTestSupport;
import org.apache.camel.kafkaconnector.common.test.StringMessageConsumer;
import org.apache.camel.kafkaconnector.common.test.TestMessageConsumer;
import org.apache.camel.kafkaconnector.common.utils.NetworkUtils;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * A simple test case that checks whether the timer produces the expected number of
 * messages
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSourceSyslogITCase extends CamelSourceTestSupport {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceSyslogITCase.class);
    private static final String HOST = "localhost";
    private static final String PROTOCOL = "udp";
    private static final int FREE_PORT = NetworkUtils.getFreePort(HOST, NetworkUtils.Protocol.UDP);

    private final int expect = 1;
    private ConnectorPropertyFactory connectorPropertyFactory;
    private String topicName;

    private CamelContext camelContext;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-syslog-kafka-connector"};
    }

    @BeforeAll
    public void setupCamelContext() throws Exception {
        LOG.debug("Creating the Camel context");
        camelContext = new DefaultCamelContext();
        camelContext.getRegistry().bind("encoder", new Rfc5425Encoder());

        LOG.debug("Adding routes");
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:test")
                        .marshal(new SyslogDataFormat())
                        .toF("netty:%s://%s:%d?sync=false&encoders=#encoder&useByteBuf=true", PROTOCOL, HOST, FREE_PORT);
            }
        });
    }

    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);

        camelContext.start();
        TestUtils.waitFor(camelContext::isStarted);
    }

    @AfterEach
    public void tearDown() {
        camelContext.stop();
    }

    @Override
    protected void produceTestData() {
        String message = "<13>1 2020-05-14T14:47:01.198+02:00 nathannever myapp - - [timeQuality tzKnown=\"1\" isSynced=\"1\" syncAccuracy=\"11266\"] FOO BAR!";

        camelContext.createProducerTemplate().sendBody("direct:test", message);
    }

    @Override
    protected void verifyMessages(TestMessageConsumer<?> consumer) {
        int received = consumer.consumedMessages().size();

        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }


    @RepeatedTest(3)
    @Timeout(90)
    public void testBasicSend() throws ExecutionException, InterruptedException {
        connectorPropertyFactory = CamelSyslogPropertyFactory
                .basic()
                .withKafkaTopic(topicName)
                .withHost(HOST)
                .withPort(FREE_PORT)
                .withProtocol(PROTOCOL);

        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        StringMessageConsumer stringMessageConsumer = new StringMessageConsumer(kafkaClient, topicName, expect);

        runTestBlocking(connectorPropertyFactory, stringMessageConsumer);
    }
}
