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

package org.apache.camel.kafkaconnector.cxfrs.source;

import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSourceTestSupport;
import org.apache.camel.kafkaconnector.common.test.TestMessageConsumer;
import org.apache.camel.kafkaconnector.common.utils.NetworkUtils;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * A simple test case that checks whether the CXF RS Consumer Endpoint produces the expected number of
 * messages
 */
public class CamelSourceCXFRSITCase extends CamelSourceTestSupport {
    
    protected static final String LOCALHOST = NetworkUtils.getHostname();
    protected static final int PORT = NetworkUtils.getFreePort(LOCALHOST);
    protected static final String CXT = PORT + "/CxfRsConsumerTest";
    protected static final String CXF_RS_ENDPOINT_ADDRESS = "http://" + LOCALHOST + ":" + CXT + "/rest";
    protected static final String CXF_RS_ENDPOINT_URI =  CXF_RS_ENDPOINT_ADDRESS
        + "?resourceClasses=org.apache.camel.kafkaconnector.cxfrs.CustomerServiceResource";

    
            
    private static String[] receivedValue = {"[126]", "[123]", "[400]"};

    
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceCXFRSITCase.class);

    private int received;
    private final int expect = 3;
    

    
    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-cxfrs-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        received = 0;
        
    }

    

    @Override
    protected void produceTestData() {
        TestUtils.waitFor(() -> NetworkUtils.portIsOpen(LOCALHOST, PORT));

        try {
            Bus bus = BusFactory.newInstance().createBus();
            
            bus.getInInterceptors().add(new LoggingInInterceptor());
            bus.getOutInterceptors().add(new LoggingOutInterceptor());
            try {
                doTestGetCustomer("rest");
            } catch (Exception e) {
                LOG.info("Test Invocation Failure", e);
            }
            

        } catch (Exception e) {
            LOG.info("Unable to invoke service: {}", e.getMessage(), e);
            fail("Unable to invoke service");
        }
    }

    @Override
    protected void verifyMessages(TestMessageConsumer<?> consumer) {
        LOG.info("Consumed messages: {}", consumer.consumedMessages());
        
        for (ConsumerRecord<String, ?> record : consumer.consumedMessages()) {
            Object receivedObject = consumer.consumedMessages().get(received).value();
            if (!(receivedObject instanceof String)) {
                fail("Unexpected message type");
            }

            String result = (String) receivedObject;
            assertEquals(receivedValue[received++], result);
            
            
        }
    }

        

    @Test
    @Timeout(20)
    public void testBasicSendReceive() {
        try {
            String topicName = getTopicForTest(this);
            ConnectorPropertyFactory connectorPropertyFactory = CamelSourceCXFRSPropertyFactory
                    .basic()
                    .withKafkaTopic(topicName)
                    .withAddress(CXF_RS_ENDPOINT_ADDRESS)
                    .withResourceClass("org.apache.camel.kafkaconnector.cxfrs.CustomerServiceResource");
                  
                                        

            runTestBlocking(connectorPropertyFactory, topicName, expect);
        } catch (Exception e) {
            LOG.error("CXF test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }
    
    @Test
    @Timeout(20)
    public void testBasicSendReceiveWithoutProcessor() {
        try {
            
            String topicName = getTopicForTest(this);
            ConnectorPropertyFactory connectorPropertyFactory = CamelSourceCXFRSPropertyFactory
                    .basic()
                    .withKafkaTopic(topicName)
                    .withAddress(CXF_RS_ENDPOINT_ADDRESS)
                    .withResourceClass("org.apache.camel.kafkaconnector.cxfrs.CustomerServiceResource");
                    
                                        

            runTestBlocking(connectorPropertyFactory, topicName, expect);
        } catch (Exception e) {
            LOG.error("CXF test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }
    
    @Test
    @Timeout(20)
    public void testBasicSendReceiveUsingUrl() {
        try {
            String topicName = getTopicForTest(this);
            ConnectorPropertyFactory connectorPropertyFactory = CamelSourceCXFRSPropertyFactory
                    .basic()
                    .withKafkaTopic(topicName)
                    .withUrl(CXF_RS_ENDPOINT_URI).buildUrl();
                    
                    

            runTestBlocking(connectorPropertyFactory, topicName, expect);
        } catch (Exception e) {
            LOG.error("CXF test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    
    
    private void invokeGetCustomer(String uri, String expect) throws Exception {
        HttpGet get = new HttpGet(uri);
        get.addHeader("Accept", "application/json");
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();

        try {
            HttpResponse response = httpclient.execute(get);
            
        } finally {
            httpclient.close();
        }
    }

    private void doTestGetCustomer(String contextUri) throws Exception {
        invokeGetCustomer("http://" + LOCALHOST + ":" + CXT + "/" + contextUri + "/customerservice/customers/126",
                          "{\"Customer\":{\"id\":126,\"name\":\"CKC\"}}");
        invokeGetCustomer("http://" + LOCALHOST + ":" + CXT + "/" + contextUri + "/customerservice/customers/123",
                          "customer response back!");
        invokeGetCustomer("http://" + LOCALHOST + ":" + CXT + "/" + contextUri + "/customerservice/customers/400",
            "The remoteAddress is 127.0.0.1");

    }
    
}
