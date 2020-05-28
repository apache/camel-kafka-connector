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

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.syslog.SyslogDataFormat;
import org.apache.camel.component.syslog.netty.Rfc5425Encoder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.NetworkUtils;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * A simple test case that checks whether the timer produces the expected number of
 * messages
 */
@Testcontainers
public class CamelSourceSyslogITCase extends AbstractKafkaTest {
    private static final int FREE_PORT = NetworkUtils.getFreePort("localhost", NetworkUtils.Protocol.UDP);

    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceSyslogITCase.class);

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

    private void produceLogMessages(String protocol, String host, String port, String message) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();

        try {
            camelContext.getRegistry().bind("encoder", new Rfc5425Encoder());
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("direct:test").marshal(new SyslogDataFormat()).to("netty:" + protocol + ":" + host + ":" + port + "?sync=false&encoders=#encoder&useByteBuf=true");
                }
            });

            camelContext.start();
            camelContext.createProducerTemplate().sendBody("direct:test", message);
        } catch (Exception e) {
            LOG.error("Failed to send log messages {} to : {}", message, "netty:" + protocol + ":" + host + ":" + port);
            fail(e.getMessage());
        } finally {
            camelContext.stop();
        }
    }

    private <T> boolean checkRecord(ConsumerRecord<String, T> record) {
        LOG.debug("Received: {}", record.value());
        received++;

        if (received == expect) {
            return false;
        }

        return true;
    }

    private void runBasicStringTest(ConnectorPropertyFactory connectorPropertyFactory) throws Exception {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnectorBlocking(connectorPropertyFactory, 1);

        produceLogMessages(connectorPropertyFactory.getProperties().get("camel.source.path.protocol").toString(),
                connectorPropertyFactory.getProperties().get("camel.source.path.host").toString(),
                connectorPropertyFactory.getProperties().get("camel.source.path.port").toString(),
                "<13>1 2020-05-14T14:47:01.198+02:00 nathannever myapp - - [timeQuality tzKnown=\"1\" isSynced=\"1\" syncAccuracy=\"11266\"] FOO BAR!");

        LOG.debug("Creating the consumer ...");
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        kafkaClient.consume(TestUtils.getDefaultTestTopic(this.getClass()), this::checkRecord);
        LOG.debug("Created the consumer ...");

        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }

    @Test
    @Timeout(90)
    public void testBasicSend() {
        try {
            ConnectorPropertyFactory connectorPropertyFactory = CamelSyslogPropertyFactory
                    .basic()
                    .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withHost("localhost")
                    .withPort(FREE_PORT)
                    .withProtocol("udp");

            runBasicStringTest(connectorPropertyFactory);
        } catch (Exception e) {
            LOG.error("Syslog test failed: {} {}", e.getMessage(), e);
            fail(e.getMessage(), e);
        }
    }
}
