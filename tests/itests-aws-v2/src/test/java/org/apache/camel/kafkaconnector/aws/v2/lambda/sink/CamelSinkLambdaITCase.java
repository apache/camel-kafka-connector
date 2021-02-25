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

package org.apache.camel.kafkaconnector.aws.v2.lambda.sink;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.camel.kafkaconnector.CamelSinkTask;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.ByteProducerPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.ConsumerPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.DefaultConsumerPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.clients.kafka.ProducerPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.AbstractTestMessageProducer;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.test.infra.aws.common.services.AWSService;
import org.apache.camel.test.infra.aws2.clients.AWSSDKClientUtils;
import org.apache.camel.test.infra.aws2.services.AWSServiceFactory;
import org.apache.kafka.common.utils.Bytes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.FunctionConfiguration;
import software.amazon.awssdk.services.lambda.model.ListFunctionsResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@EnabledIfSystemProperty(named = "enable.slow.tests", matches = "true")
public class CamelSinkLambdaITCase extends CamelSinkTestSupport {
    @RegisterExtension
    public static AWSService awsService = AWSServiceFactory.createLambdaService();
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkLambdaITCase.class);

    private LambdaClient client;
    private String function;

    private volatile int received;
    private final int expect = 1;


    private static class CustomProducer extends AbstractTestMessageProducer<Bytes> {
        public CustomProducer(String bootstrapServer, String topicName, int count) {
            super(bootstrapServer, topicName, count);
        }

        @Override
        protected KafkaClient<String, Bytes> createKafkaClient(String bootstrapServer) {
            ConsumerPropertyFactory consumerPropertyFactory = new DefaultConsumerPropertyFactory(bootstrapServer);
            ProducerPropertyFactory producerPropertyFactory = new ByteProducerPropertyFactory(bootstrapServer);

            return new KafkaClient<>(consumerPropertyFactory, producerPropertyFactory);
        }

        @Override
        public Bytes testMessageContent(int current) {

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                ZipOutputStream zip = new ZipOutputStream(out);

                ZipEntry entry = new ZipEntry("test");
                zip.putNextEntry(entry);
                zip.write("hello test".getBytes());
                zip.closeEntry();
                zip.finish();

                return Bytes.wrap(out.toByteArray());
            } catch (IOException e) {
                LOG.error("I/O error writing zip entry: {}", e.getMessage(), e);
                fail("I/O error writing zip entry");
            }

            return null;
        }

        @Override
        public Map<String, String> messageHeaders(Bytes text, int current) {
            Map<String, String> headers = new HashMap<>();

            headers.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "CamelAwsLambdaOperation",
                    "createFunction");

            headers.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "CamelAwsLambdaRole",
                    "admin");
            headers.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "CamelAwsLambdaRuntime",
                    "java8");
            headers.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "CamelAwsLambdaHandler",
                    "org.apache.camel.kafkaconnector.SomeHandler");

            return headers;
        }
    }

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-aws2-lambda-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        client = AWSSDKClientUtils.newLambdaClient();

        function = "function-" + TestUtils.randomWithRange(0, 100);
        LOG.debug("Using function {} for the test", function);
    }

    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try {
            while (true) {
                ListFunctionsResponse response = client.listFunctions();

                for (FunctionConfiguration functionConfiguration : response.functions()) {
                    LOG.info("Retrieved function {}", functionConfiguration.functionName());

                    if (functionConfiguration.functionName().equals(function)) {
                        received = 1;
                        return;
                    }
                }

                if (!waitForData()) {
                    break;
                }
            }
        } finally {
            latch.countDown();
        }
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (latch.await(110, TimeUnit.SECONDS)) {
            assertEquals(expect, received, "Didn't process the expected amount of messages: " + received + " != " + expect);
        } else {
            fail(String.format("Failed to receive the messages within the specified time: received %d of %d",
                    received, expect));
        }
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceive() throws Exception {
        Properties amazonProperties = awsService.getConnectionProperties();
        String topicName = TestUtils.getDefaultTestTopic(this.getClass());

        ConnectorPropertyFactory testProperties = CamelAWSLambdaPropertyFactory
                .basic()
                .withTopics(topicName)
                .withConfiguration(TestLambda2Configuration.class.getName())
                .withAmazonConfig(amazonProperties)
                .withSinkPathFunction(function)
                .withSinkEndpointOperation("createFunction");

        runTest(testProperties, new CustomProducer(getKafkaService().getBootstrapServers(), topicName, expect));
    }

}
