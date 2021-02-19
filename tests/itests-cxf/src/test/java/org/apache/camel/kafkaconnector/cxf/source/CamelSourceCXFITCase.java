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

package org.apache.camel.kafkaconnector.cxf.source;

import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.NetworkUtils;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.kafkaconnector.cxf.client.CXFServiceUtil;
import org.apache.camel.kafkaconnector.cxf.common.HelloService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * A simple test case that checks whether the CXF Consumer Endpoint produces the expected number of messages
 */
public class CamelSourceCXFITCase extends AbstractKafkaTest {

    protected static final int PORT = NetworkUtils.getFreePort("localhost");
    protected static final String SIMPLE_ENDPOINT_ADDRESS = "http://localhost:" + PORT + "/CxfConsumerTest/test";
    protected static final String SIMPLE_ENDPOINT_URI = SIMPLE_ENDPOINT_ADDRESS
            + "?serviceClass=org.apache.camel.kafkaconnector.cxf.common.HelloService"
            + "&publishedEndpointUrl=http://www.simple.com/services/test";

    private static final String TEST_MESSAGE = "Hello World!";

    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceCXFITCase.class);

    private int received;
    private final int expect = 1;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-cxf-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        received = 0;
    }

    private <T> boolean checkRecord(ConsumerRecord<String, T> record) {
        LOG.debug("Received: {}", record.value());

        received++;

        if (received == expect) {
            return false;
        }

        return true;
    }

    public void runBasicStringTest(ConnectorPropertyFactory connectorPropertyFactory)
            throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnector(connectorPropertyFactory);

        // ensure cxf source connector is up
        Thread.sleep(5000);
        HelloService client = CXFServiceUtil.getService(SIMPLE_ENDPOINT_ADDRESS, HelloService.class);
        try {
            String result = client.echo(TEST_MESSAGE);
            assertEquals(result, TEST_MESSAGE);
        } catch (Exception e) {
            LOG.info("Test Invocation Failure", e);
        }

        LOG.debug("Creating the consumer ...");
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        kafkaClient.consume(TestUtils.getDefaultTestTopic(this.getClass()), this::checkRecord);
        LOG.debug("Created the consumer ...");

        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }

    @Test
    @Timeout(20)
    public void testBasicSendReceive() {
        try {
            ConnectorPropertyFactory connectorPropertyFactory = CamelSourceCXFPropertyFactory.basic()
                    .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass())).withAddress(SIMPLE_ENDPOINT_ADDRESS)
                    .withServiceClass("org.apache.camel.kafkaconnector.cxf.common.HelloService");

            runBasicStringTest(connectorPropertyFactory);
        } catch (Exception e) {
            LOG.error("CXF test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    @Test
    @Timeout(20)
    public void testBasicSendReceiveUsingUrl() {
        try {
            ConnectorPropertyFactory connectorPropertyFactory = CamelSourceCXFPropertyFactory.basic()
                    .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass())).withUrl(SIMPLE_ENDPOINT_URI)
                    .buildUrl();

            runBasicStringTest(connectorPropertyFactory);
        } catch (Exception e) {
            LOG.error("CXF test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    @Test
    @Timeout(20)
    public void testBasicSendReceiveUsingDataFormat() {
        try {
            ConnectorPropertyFactory connectorPropertyFactory = CamelSourceCXFPropertyFactory.basic()
                    .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass())).withAddress(SIMPLE_ENDPOINT_ADDRESS)
                    .withServiceClass("org.apache.camel.kafkaconnector.cxf.common.HelloService").withDataFormat("POJO");

            runBasicStringTest(connectorPropertyFactory);
        } catch (Exception e) {
            LOG.error("CXF test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }

}
