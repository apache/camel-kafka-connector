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

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.NetworkUtils;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.kafkaconnector.syslog.services.SyslogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * A simple test case that checks whether the syslog send the expected number of
 * messages
 */
@Testcontainers
public class CamelSinkSyslogITCase extends AbstractKafkaTest {
    private static final int FREE_PORT = NetworkUtils.getFreePort("localhost", NetworkUtils.Protocol.UDP);

    @RegisterExtension
    public static SyslogService syslogService = new SyslogService("udp", "//localhost", FREE_PORT);

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkSyslogITCase.class);

    private int received;
    private final int expect = 1;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-syslog-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        received = 0;
    }

    private void runBasicProduceTest(ConnectorPropertyFactory connectorPropertyFactory) throws Exception {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnectorBlocking(connectorPropertyFactory, 1);

        LOG.debug("Creating the producer ...");
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        kafkaClient.produce(TestUtils.getDefaultTestTopic(this.getClass()), "<13>1 2020-05-14T14:47:01.198+02:00 nathannever myapp - - [timeQuality tzKnown=\"1\" isSynced=\"1\" syncAccuracy=\"11266\"] FOO BAR!");
        LOG.debug("Created the producer ...");

        assertEquals("<13>1 2020-05-14T14:47:01.198+02:00 nathannever myapp - - [timeQuality tzKnown=\"1\" isSynced=\"1\" syncAccuracy=\"11266\"] FOO BAR!", syslogService.getFirstExchangeToBeReceived().getIn().getBody(String.class));
    }

    @Test
    @Timeout(90)
    public void testBasicReceive() {
        try {
            ConnectorPropertyFactory connectorPropertyFactory = CamelSyslogPropertyFactory
                    .basic()
                    .withTopics(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withHost("localhost")
                    .withPort(FREE_PORT)
                    .withProtocol("udp");

            runBasicProduceTest(connectorPropertyFactory);
        } catch (Exception e) {
            LOG.error("Syslog test failed: {} {}", e.getMessage(), e);
            fail(e.getMessage(), e);
        }
    }
}
