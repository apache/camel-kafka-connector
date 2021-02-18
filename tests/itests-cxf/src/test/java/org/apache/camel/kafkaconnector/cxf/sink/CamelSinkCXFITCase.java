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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.cxf.services.CXFEmbeddedServerService;
import org.apache.camel.kafkaconnector.cxf.services.CXFService;
import org.apache.camel.kafkaconnector.cxf.services.JaxWsServiceConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CamelSinkCXFITCase extends AbstractKafkaTest {
    protected static final String TEST_MESSAGE = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
            + "<soap:Body><ns1:echo xmlns:ns1=\"http://source.cxf.kafkaconnector.camel.apache.org/\">"
            + "<arg0 xmlns=\"http://source.cxf.kafkaconnector.camel.apache.org/\">hello world</arg0>"
            + "</ns1:echo></soap:Body></soap:Envelope>";

    protected static final String JAXWS_TEST_MESSAGE = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\"\n"
            + "        + \"<soap:Body><ns1:greetMe xmlns:ns1=\"http://apache.org/hello_world_soap_http/types\">\"\n"
            + "        + \"<requestType xmlns=\"http://apache.org/hello_world_soap_http/types\">hello world!</requestType>\"\n"
            + "        + \"</ns1:greetMe></soap:Body></soap:Envelope>";

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

    private void putRecords(String message, int count) {
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        for (int i = 0; i < count; i++) {
            try {
                kafkaClient.produce(topicName, message);
            } catch (ExecutionException e) {
                LOG.error("Unable to produce messages: {}", e.getMessage(), e);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public String getJaxwsEndpointUri() {
        return service.getJaxWsServerAddress() + "?serviceClass=org.apache.hello_world_soap_http.Greeter";
    }

    public void runTest(ConnectorPropertyFactory connectorPropertyFactory, String message, int count)
            throws ExecutionException, InterruptedException, TimeoutException {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnectorBlocking(connectorPropertyFactory, 1);
        ExecutorService service = Executors.newCachedThreadPool();
        Runnable r = () -> this.putRecords(message, count);
        service.submit(r);
        Thread.sleep(5000);
        LOG.debug("Created the consumer ... About to receive messages");
    }

    @Test
    public void testBasicSendReceiveUsingUrl() {
        try {
            ConnectorPropertyFactory connectorPropertyFactory = CamelSinkCXFPropertyFactory
                    .basic()
                    .withName("CamelCXFSinkConnector")
                    .withTopics(topicName)
                    .withAddress(service.getSimpleServerAddress())
                    .withServiceClass("org.apache.camel.kafkaconnector.cxf.source.HelloService")
                    .withDataFormat("RAW");

            runTest(connectorPropertyFactory, TEST_MESSAGE, expect);

            assertEquals(expect, serverFactoryBeanConfigurator.getInvocationCount());
        } catch (Exception e) {
            LOG.error("CXF Sink test failed: {} {}", e.getMessage(), e);
            fail(e.getMessage(), e);
        }
    }

    @Test
    @Timeout(90)
    public void testJaxWsBasicSendReceiveUsingUrl() {
        try {
            ConnectorPropertyFactory connectorPropertyFactory = CamelSinkCXFPropertyFactory
                    .basic()
                    .withName("CamelCXFSinkConnectorUrl")
                    .withTopics(topicName)
                    .withAddress(getJaxwsEndpointUri())
                    .withDataFormat("RAW");

            runTest(connectorPropertyFactory, JAXWS_TEST_MESSAGE, 1);

            assertTrue(GreeterImpl.outputFile().exists(), "The test output file was not created");
        } catch (Exception e) {
            LOG.error("CXF Sink test failed: {} {}", e.getMessage(), e);
            fail(e.getMessage(), e);
        }
    }
}