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

import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.test.CamelSourceTestSupport;
import org.apache.camel.kafkaconnector.common.test.StringMessageConsumer;
import org.apache.camel.kafkaconnector.common.test.TestMessageConsumer;
import org.apache.camel.kafkaconnector.common.utils.NetworkUtils;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.kafkaconnector.syslog.services.SyslogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * A simple test case that checks whether the timer produces the expected number of
 * messages
 */
@EnabledIfSystemProperty(named = "enable.flaky.tests", matches = "true",
        disabledReason = "Quickly spawning multiple Jetty Servers doesn't work well on Github Actions")
public class CamelSourceSyslogITCase extends CamelSourceTestSupport {
    private static final String HOST = NetworkUtils.getHostname();
    private static final String PROTOCOL = "udp";
    private static final int FREE_PORT = NetworkUtils.getFreePort(HOST, NetworkUtils.Protocol.UDP);

    @RegisterExtension
    public static SyslogService service = SyslogService.sourceSyslogServiceFactory(PROTOCOL, HOST, FREE_PORT);

    private final int expect = 1;
    private String topicName;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-syslog-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);
    }


    @Override
    protected void produceTestData() {
        String message = "<13>1 2020-05-14T14:47:01.198+02:00 nathannever myapp - - [timeQuality tzKnown=\"1\" isSynced=\"1\" syncAccuracy=\"11266\"] FOO BAR!";

        service.getCamelContext().createProducerTemplate().sendBody("direct:test", message);
    }

    @Override
    protected void verifyMessages(TestMessageConsumer<?> consumer) {
        int received = consumer.consumedMessages().size();

        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }


    @RepeatedTest(3)
    @Test
    @Timeout(180)
    public void testBasicSend() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelSyslogPropertyFactory
                .basic()
                .withName("CamelSyslogSourceConnector" + TestUtils.randomWithRange(0, 1000))
                .withKafkaTopic(topicName)
                .withHost(HOST)
                .withPort(FREE_PORT)
                .withProtocol(PROTOCOL);

        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        StringMessageConsumer stringMessageConsumer = new StringMessageConsumer(kafkaClient, topicName, expect);

        runTestBlocking(connectorPropertyFactory, stringMessageConsumer);
    }
}
