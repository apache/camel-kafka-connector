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

package org.apache.camel.kafkaconnector.aws.v2.sqs.sink;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.aws.common.AWSCommon;
import org.apache.camel.kafkaconnector.aws.common.AWSConfigs;
import org.apache.camel.kafkaconnector.aws.common.services.AWSService;
import org.apache.camel.kafkaconnector.aws.v2.clients.AWSSQSClient;
import org.apache.camel.kafkaconnector.aws.v2.services.AWSServiceFactory;
import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.model.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Disabled("Needs the fix for CAMEL-15391 which will be available on camel 3.4.4")
@Testcontainers
public class CamelSinkAWSSQSITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static AWSService<AWSSQSClient> awsService = AWSServiceFactory.createSQSService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkAWSSQSITCase.class);

    private AWSSQSClient awssqsClient;
    private String queueName;

    private volatile int received;
    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-aws2-sqs-kafka-connector"};
    }


    @BeforeEach
    public void setUp() {
        awssqsClient = awsService.getClient();

        queueName = AWSCommon.BASE_SQS_QUEUE_NAME + "-" + TestUtils.randomWithRange(0, 1000);
        String queueUrl = awssqsClient.getOrCreateQueue(queueName);

        LOG.debug("Using queue {} for the test", queueUrl);

        received = 0;
    }

    @AfterEach
    public void tearDown() {
        if (!awssqsClient.deleteQueue(queueName)) {
            fail("Failed to delete queue");
        }
    }

    private boolean checkMessages(List<Message> messages) {
        for (Message message : messages) {
            LOG.info("Received: {}", message.body());

            received++;
        }

        if (received == expect) {
            return false;
        }

        return true;
    }


    private void consumeMessages(CountDownLatch latch) {
        try {
            awssqsClient.receive(queueName, this::checkMessages);
        } catch (Throwable t) {
            LOG.error("Failed to consume messages: {}", t.getMessage(), t);
        } finally {
            latch.countDown();
        }
    }

    private void produceMessages()  {
        try {
            KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

            for (int i = 0; i < expect; i++) {
                kafkaClient.produce(TestUtils.getDefaultTestTopic(this.getClass()), "Sink test message " + i);
            }
        } catch (Throwable t) {
            LOG.error("Unable to publish messages to the broker: {}", t.getMessage(), t);
            fail(String.format("Unable to publish messages to the broker: %s", t.getMessage()));
        }
    }

    public void runTest(ConnectorPropertyFactory connectorPropertyFactory) throws Exception {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnectorBlocking(connectorPropertyFactory, 1);

        LOG.debug("Creating the consumer ...");
        ExecutorService service = Executors.newCachedThreadPool();

        CountDownLatch latch = new CountDownLatch(1);
        service.submit(() -> consumeMessages(latch));

        LOG.debug("Creating the producer and sending messages ...");
        produceMessages();

        LOG.debug("Waiting for the test to complete");
        if (latch.await(110, TimeUnit.SECONDS)) {
            assertEquals(expect, received, "Didn't process the expected amount of messages: " + received + " != " + expect);
        } else {
            fail(String.format("Failed to receive the messages within the specified time: received %d of %d",
                    received, expect));
        }
    }


    @Test
    @Timeout(value = 120)
    public void testBasicSendReceive() {
        try {
            Properties amazonProperties = awsService.getConnectionProperties();

            ConnectorPropertyFactory testProperties = CamelAWSSQSPropertyFactory
                    .basic()
                    .withName("CamelAwssqsSinkConnectorSpringBootStyle")
                    .withTopics(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withAmazonConfig(amazonProperties)
                    .withQueueNameOrArn(queueName);

            runTest(testProperties);
        } catch (Exception e) {
            LOG.error("Amazon SQS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    @DisabledIfSystemProperty(named = "aws-service.instance.type", matches = "remote")
    @Timeout(value = 120)
    @RepeatedTest(3)
    public void testBasicSendReceiveUsingKafkaStyle() {
        try {
            Properties amazonProperties = awsService.getConnectionProperties();

            ConnectorPropertyFactory testProperties = CamelAWSSQSPropertyFactory
                    .basic()
                    .withName("CamelAwssqsSinkConnectorKafkaStyle")
                    .withTopics(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withAmazonConfig(amazonProperties, CamelAWSSQSPropertyFactory.KAFKA_STYLE)
                    .withQueueNameOrArn(queueName);

            runTest(testProperties);

        } catch (Exception e) {
            LOG.error("Amazon SQS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    @DisabledIfSystemProperty(named = "aws-service.instance.type", matches = "remote")
    @Timeout(value = 120)
    @RepeatedTest(3)
    public void testBasicSendReceiveUsingUrl() {
        try {
            Properties amazonProperties = awsService.getConnectionProperties();

            ConnectorPropertyFactory testProperties = CamelAWSSQSPropertyFactory
                    .basic()
                    .withName("CamelAwssqsSinkConnectorUsingUrl")
                    .withTopics(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withUrl(queueName)
                        .append("autoCreateQueue", "true")
                        .append("accessKey", amazonProperties.getProperty(AWSConfigs.ACCESS_KEY))
                        .append("secretKey", amazonProperties.getProperty(AWSConfigs.SECRET_KEY))
                        .append("protocol", amazonProperties.getProperty(AWSConfigs.PROTOCOL))
                        .append("region", amazonProperties.getProperty(AWSConfigs.REGION, Region.US_EAST_1.toString()))
                        .append("amazonAWSHost", amazonProperties.getProperty(AWSConfigs.AMAZON_AWS_HOST))
                        .buildUrl();

            runTest(testProperties);

        } catch (Exception e) {
            LOG.error("Amazon SQS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }



}
