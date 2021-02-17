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

import java.util.concurrent.ExecutionException;

import javax.servlet.ServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.NetworkUtils;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.kafkaconnector.cxfrs.Customer;
import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.impl.ResponseImpl;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.Assert.assertTrue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * A simple test case that checks whether the CXF RS Consumer Endpoint produces the expected number of
 * messages
 */
public class CamelSourceCXFRSITCase extends AbstractKafkaTest {
    
    protected static final int PORT = NetworkUtils.getFreePort("localhost");
    protected static final String CXT = PORT + "/CxfRsConsumerTest";
    protected static final String CXF_RS_ENDPOINT_ADDRESS = "http://localhost:" + CXT + "/rest";
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

    private <T> boolean checkRecord(ConsumerRecord<String, Object> record) {
        LOG.debug("Received: {}", record.value());
        assertEquals(receivedValue[received], record.value());
        received++;

        if (received == expect) {
            return false;
        }

        return true;
    }

    
    private <T> boolean checkRecordWithProcessor(ConsumerRecord<String, Object> record) {
        LOG.debug("Received: {}", record.value());
        
        switch (received) {
            case 0:
                assertTrue(((String)record.value()).startsWith("org.apache.camel.kafkaconnector.cxfrs.Customer"));
                break;
            case 1:
            case 2:    
                assertTrue(((String)record.value()).startsWith("org.apache.cxf.jaxrs.impl.ResponseImpl"));
                break;
            
        }
        received++;

        if (received == expect) {
            return false;
        }

        return true;
    }



    public void runBasicStringTest(ConnectorPropertyFactory connectorPropertyFactory, boolean withProcessor) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();
Bus bus = BusFactory.newInstance().createBus();
        
        bus.getInInterceptors().add(new LoggingInInterceptor());
        bus.getOutInterceptors().add(new LoggingOutInterceptor());
        getKafkaConnectService().initializeConnector(connectorPropertyFactory);
        getKafkaConnectService().initializeConnectorBlocking(connectorPropertyFactory, 1);
        
        
        Thread.sleep(5000);
        try {
            doTestGetCustomer("rest", withProcessor);
        } catch (Exception e) {
            LOG.info("Test Invocation Failure", e);
        }
        
        
        LOG.debug("Creating the consumer ...");
        KafkaClient<String, Object> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        if (!withProcessor) {
            kafkaClient.consume(TestUtils.getDefaultTestTopic(this.getClass()), this::checkRecord);
        } else {
            kafkaClient.consume(TestUtils.getDefaultTestTopic(this.getClass()), this::checkRecordWithProcessor);
        }
        LOG.debug("Created the consumer ...");

        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }

    

    @Test
    @Timeout(2000)
    public void testBasicSendReceive() {
        try {
            ConnectorPropertyFactory connectorPropertyFactory = CamelSourceCXFRSPropertyFactory
                    .basic()
                    .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withAddress(CXF_RS_ENDPOINT_ADDRESS)
                    .withResourceClass("org.apache.camel.kafkaconnector.cxfrs.CustomerServiceResource");
                  
                                        

            runBasicStringTest(connectorPropertyFactory, false);
        } catch (Exception e) {
            LOG.error("CXF test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }
    
    @Test
    @Timeout(2000)
    public void testBasicSendReceiveWithoutProcessor() {
        try {
            ConnectorPropertyFactory connectorPropertyFactory = CamelSourceCXFRSPropertyFactory
                    .basic()
                    .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withAddress(CXF_RS_ENDPOINT_ADDRESS)
                    .withResourceClass("org.apache.camel.kafkaconnector.cxfrs.CustomerServiceResource");
                    
                                        

            runBasicStringTest(connectorPropertyFactory, false);
        } catch (Exception e) {
            LOG.error("CXF test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }
    
    @Test
    @Timeout(20)
    public void testBasicSendReceiveUsingUrl() {
        try {
            ConnectorPropertyFactory connectorPropertyFactory = CamelSourceCXFRSPropertyFactory
                    .basic()
                    .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withUrl(CXF_RS_ENDPOINT_URI).buildUrl();
                    
                    

            runBasicStringTest(connectorPropertyFactory, false);
        } catch (Exception e) {
            LOG.error("CXF test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    
    
    private void invokeGetCustomer(String uri, String expect, boolean withProcessor) throws Exception {
        HttpGet get = new HttpGet(uri);
        get.addHeader("Accept", "application/json");
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();

        try {
            HttpResponse response = httpclient.execute(get);
            if (withProcessor) {
                assertEquals(200, response.getStatusLine().getStatusCode());
                assertEquals(expect,
                         EntityUtils.toString(response.getEntity()));
            }
        } finally {
            httpclient.close();
        }
    }

    private void doTestGetCustomer(String contextUri, boolean withProcessor) throws Exception {
        invokeGetCustomer("http://localhost:" + CXT + "/" + contextUri + "/customerservice/customers/126",
                          "{\"Customer\":{\"id\":126,\"name\":\"CKC\"}}",
                          withProcessor);
        invokeGetCustomer("http://localhost:" + CXT + "/" + contextUri + "/customerservice/customers/123",
                          "customer response back!",
                          withProcessor);
        invokeGetCustomer("http://localhost:" + CXT + "/" + contextUri + "/customerservice/customers/400",
            "The remoteAddress is 127.0.0.1",
            withProcessor);

    }


    public abstract static class AbstractTestProcessor implements Processor {
        public void processGetCustomer(Exchange exchange) throws Exception {
            Message inMessage = exchange.getIn();
            String httpMethod = inMessage.getHeader(Exchange.HTTP_METHOD, String.class);
            assertEquals("GET", httpMethod, "Get a wrong http method");
            String path = inMessage.getHeader(Exchange.HTTP_PATH, String.class);
            // The parameter of the invocation is stored in the body of in message
            String id = inMessage.getBody(String.class);
            if ("/customerservice/customers/126".equals(path)) {
                Customer customer = new Customer();
                customer.setId(Long.parseLong(id));
                customer.setName("CKC");
                // We just put the response Object into the out message body
                exchange.getMessage().setBody(customer);
            } else {
                if ("/customerservice/customers/400".equals(path)) {
                    // We return the remote client IP address this time
                    org.apache.cxf.message.Message cxfMessage
                            = inMessage.getHeader(CxfConstants.CAMEL_CXF_MESSAGE, org.apache.cxf.message.Message.class);
                    ServletRequest request = (ServletRequest) cxfMessage.get("HTTP.REQUEST");
                    // Just make sure the request object is not null
                    assertNotNull(request, "The request object should not be null");
                    Response r = Response.status(200).entity("The remoteAddress is 127.0.0.1").build();
                    exchange.getMessage().setBody(r);
                    return;
                }
                if ("/customerservice/customers/123".equals(path)) {
                    // send a customer response back
                    Response r = Response.status(200).entity("customer response back!").build();
                    exchange.getMessage().setBody(r);
                    return;
                }
                if ("/customerservice/customers/456".equals(path)) {
                    Response r = Response.status(404).entity("Can't found the customer with uri " + path)
                            .header("Content-Type", "text/plain").build();
                    throw new WebApplicationException(r);
                } else if ("/customerservice/customers/234".equals(path)) {
                    Response r = Response.status(404).entity("Can't found the customer with uri " + path)
                            .header("Content-Type", "text/plain").build();
                    exchange.getMessage().setBody(r);
                } else if ("/customerservice/customers/789".equals(path)) {
                    exchange.getMessage().setBody("Can't found the customer with uri " + path);
                    exchange.getMessage().setHeader(Exchange.CONTENT_TYPE, "text/plain");
                    exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, "404");
                } else {
                    throw new RuntimeCamelException("Can't found the customer with uri " + path);
                }
            }
        }

    }

    public static class TestProcessor extends AbstractTestProcessor {
        @Override
        public void process(Exchange exchange) throws Exception {
            Message inMessage = exchange.getIn();
            // Get the operation name from in message
            String operationName = inMessage.getHeader(CxfConstants.OPERATION_NAME, String.class);
            if ("getCustomer".equals(operationName)) {
                processGetCustomer(exchange);
            } else if ("updateCustomer".equals(operationName)) {
                assertEquals("header1;header2", inMessage.getHeader("test"), "Get a wrong customer message header");
                String httpMethod = inMessage.getHeader(Exchange.HTTP_METHOD, String.class);
                assertEquals("PUT", httpMethod, "Get a wrong http method");
                Customer customer = inMessage.getBody(Customer.class);
                assertNotNull(customer, "The customer should not be null.");
                // Now you can do what you want on the customer object
                assertEquals("Mary", customer.getName(), "Get a wrong customer name.");
                // set the response back
                exchange.getMessage().setBody(Response.ok().build());
            }

        }

    }
    
}
