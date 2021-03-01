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

package org.apache.camel.kafkaconnector.cxf.sink;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.cxf.services.CXFEmbeddedServerService;
import org.apache.camel.kafkaconnector.cxf.services.CXFService;
import org.apache.camel.kafkaconnector.cxf.services.JaxWsServiceConfigurator;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CamelSinkCXFITCase extends CamelSinkTestSupport {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkCXFITCase.class);

    private SinkServerFactoryBeanConfigurator serverFactoryBeanConfigurator = new SinkServerFactoryBeanConfigurator();
    private JaxWsServiceConfigurator jaxWsServiceConfigurator = new SinkJaxWsServiceConfigurator();

    @RegisterExtension
    public CXFService service = new CXFEmbeddedServerService(serverFactoryBeanConfigurator, jaxWsServiceConfigurator);

    private final int expect = 10;
    private String topicName;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[]{"camel-cxf-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);
        GreeterImpl.outputFile().delete();
    }

    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try {
            // NO-OP (the messages are consumed on each service implementation)
            Thread.sleep(5000);
        } catch (Exception e) {
            LOG.warn("Interrupted");
        } finally {
            latch.countDown();
        }
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        // NO-OP (specific for each)
        if (!latch.await(30, TimeUnit.SECONDS)) {
            fail("Failed to receive the messages within the specified time: received %d of %d");
        }
    }

    private void putRecords(String message, int count) {
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        try {
            for (int i = 0; i < count; i++) {
                kafkaClient.produce(topicName, message);
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public String getJaxwsEndpointUri() {
        return service.getJaxWsServerAddress() + "?serviceClass=org.apache.hello_world_soap_http.Greeter";
    }

    @Timeout(20)
    @Test
    public void testBasicSendReceiveUsingUrl() throws Exception {
        InputStream stream = this.getClass().getResource("/hello-service-test.xml").openStream();
        String testMessage = IOUtils.toString(stream, Charset.defaultCharset());

        ConnectorPropertyFactory connectorPropertyFactory = CamelSinkCXFPropertyFactory
                .basic()
                .withName("CamelCXFSinkConnector")
                .withTopics(topicName)
                .withAddress(service.getSimpleServerAddress())
                .withServiceClass("org.apache.camel.kafkaconnector.cxf.common.HelloService")
                .withDataFormat("RAW");

        runTest(connectorPropertyFactory, () -> putRecords(testMessage, expect));

        assertEquals(expect, serverFactoryBeanConfigurator.getInvocationCount());
    }

    @Test
    @Timeout(90)
    public void testJaxWsBasicSendReceiveUsingUrl() throws Exception {
        InputStream stream = this.getClass().getResource("/jaxws-test.xml").openStream();
        String testMessage = IOUtils.toString(stream, Charset.defaultCharset());

        ConnectorPropertyFactory connectorPropertyFactory = CamelSinkCXFPropertyFactory
                .basic()
                .withName("CamelCXFSinkConnectorUrl")
                .withTopics(topicName)
                .withAddress(getJaxwsEndpointUri())
                .withDataFormat("RAW");

        runTest(connectorPropertyFactory, () -> putRecords(testMessage, 1));

        assertTrue(GreeterImpl.outputFile().exists(), "The test output file was not created");
    }
}