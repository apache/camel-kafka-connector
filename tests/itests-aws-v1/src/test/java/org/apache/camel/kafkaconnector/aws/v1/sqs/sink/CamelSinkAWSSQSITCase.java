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

package org.apache.camel.kafkaconnector.aws.v1.sqs.sink;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.model.Message;
import org.apache.camel.kafkaconnector.aws.v1.clients.AWSSQSClient;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.test.infra.aws.clients.AWSClientUtils;
import org.apache.camel.test.infra.aws.common.AWSCommon;
import org.apache.camel.test.infra.aws.common.AWSConfigs;
import org.apache.camel.test.infra.aws.common.services.AWSService;
import org.apache.camel.test.infra.aws.services.AWSServiceFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnabledIfSystemProperty(named = "enable.slow.tests", matches = "true")
public class CamelSinkAWSSQSITCase extends CamelSinkTestSupport {

    @RegisterExtension
    public static AWSService awsService = AWSServiceFactory.createSQSService();
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkAWSSQSITCase.class);

    private AWSSQSClient awssqsClient;
    private String queueName;
    private String queueUrl;
    private String topicName;

    private volatile int received;
    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-aws-sqs-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);
        awssqsClient = new AWSSQSClient(AWSClientUtils.newSQSClient());

        queueName = AWSCommon.BASE_SQS_QUEUE_NAME + "-" + TestUtils.randomWithRange(0, 1000);
        queueUrl = awssqsClient.getQueue(queueName);

        LOG.debug("Using queue {} for the test", queueUrl);

        received = 0;
    }

    @AfterEach
    public void tearDown() {
        if (!awssqsClient.deleteQueue(queueName)) {
            fail("Failed to delete queue");
        }
    }

    @Override
    protected Map<String, String> messageHeaders(String text, int current) {
        return null;
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (latch.await(110, TimeUnit.SECONDS)) {
            assertEquals(expect, received,
                    "Didn't process the expected amount of messages: " + received + " != " + expect);
        } else {
            fail(String.format("Failed to receive the messages within the specified time: received %d of %d", received,
                    expect));
        }
    }

    private boolean checkMessages(List<Message> messages) {
        for (Message message : messages) {
            LOG.info("Received: {}", message.getBody());

            received++;
        }

        if (received == expect) {
            return false;
        }

        return true;
    }

    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try {
            awssqsClient.receiveFrom(queueUrl, this::checkMessages);
        } catch (Throwable t) {
            LOG.error("Failed to consume messages: {}", t.getMessage(), t);
        } finally {
            latch.countDown();
        }
    }

    @Test
    @Timeout(value = 120)
    public void testBasicSendReceive() throws Exception {
        Properties amazonProperties = awsService.getConnectionProperties();

        ConnectorPropertyFactory testProperties = CamelAWSSQSPropertyFactory.basic()
                .withName("CamelAwssqsSinkConnectorSpringBootStyle")
                .withTopics(topicName)
                .withAmazonConfig(amazonProperties)
                .withQueueNameOrArn(queueName);

        runTest(testProperties, topicName, expect);

    }

    @DisabledIfSystemProperty(named = "aws-service.instance.type", matches = "remote")
    @Timeout(value = 120)
    @RepeatedTest(3)
    public void testBasicSendReceiveUsingKafkaStyle() throws Exception {
        Properties amazonProperties = awsService.getConnectionProperties();

        ConnectorPropertyFactory testProperties = CamelAWSSQSPropertyFactory.basic()
                .withName("CamelAwssqsSinkConnectorKafkaStyle")
                .withTopics(topicName)
                .withAmazonConfig(amazonProperties, CamelAWSSQSPropertyFactory.KAFKA_STYLE)
                .withQueueNameOrArn(queueName);

        runTest(testProperties, topicName, expect);
    }

    @DisabledIfSystemProperty(named = "aws-service.instance.type", matches = "remote")
    @Timeout(value = 120)
    @RepeatedTest(3)
    public void testBasicSendReceiveUsingUrl() throws Exception {
        Properties amazonProperties = awsService.getConnectionProperties();

        ConnectorPropertyFactory testProperties = CamelAWSSQSPropertyFactory.basic()
                .withName("CamelAwssqsSinkConnectorUsingUrl")
                .withTopics(topicName)
                .withUrl(queueName)
                    .append("autoCreateQueue", "true")
                    .append("accessKey", amazonProperties.getProperty(AWSConfigs.ACCESS_KEY))
                    .append("secretKey", amazonProperties.getProperty(AWSConfigs.SECRET_KEY))
                    .append("protocol", amazonProperties.getProperty(AWSConfigs.PROTOCOL))
                    .append("region", amazonProperties.getProperty(AWSConfigs.REGION, Regions.US_EAST_1.name()))
                    .append("amazonAWSHost", amazonProperties.getProperty(AWSConfigs.AMAZON_AWS_HOST))
                .buildUrl();

        runTest(testProperties, topicName, expect);
    }

}
