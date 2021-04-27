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

package org.apache.camel.kafkaconnector.aws.v2.sns.sink;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.aws.v2.clients.AWSSQSClient;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.test.infra.aws.common.AWSCommon;
import org.apache.camel.test.infra.aws.common.AWSConfigs;
import org.apache.camel.test.infra.aws.common.services.AWSService;
import org.apache.camel.test.infra.aws2.clients.AWSSDKClientUtils;
import org.apache.camel.test.infra.aws2.services.AWSServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.model.Message;

import static org.apache.camel.kafkaconnector.common.BasicConnectorPropertyFactory.classRef;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnabledIfSystemProperty(named = "enable.slow.tests", matches = "true")
public class CamelSinkAWSSNSITCase extends CamelSinkTestSupport {
    @RegisterExtension
    public static AWSService service = AWSServiceFactory.createSNSService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkAWSSNSITCase.class);

    private AWSSQSClient awsSqsClient;
    private String sqsQueueUrl;
    private String queueName;

    private volatile int received;
    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-aws2-sns-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        awsSqsClient = new AWSSQSClient(AWSSDKClientUtils.newSQSClient());

        queueName = AWSCommon.DEFAULT_SQS_QUEUE_FOR_SNS + "-" + TestUtils.randomWithRange(0, 1000);
        sqsQueueUrl = awsSqsClient.createQueue(queueName);

        LOG.info("Created SQS queue {}", sqsQueueUrl);

        received = 0;
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (latch.await(120, TimeUnit.SECONDS)) {
            assertEquals(expect, received,
                    "Didn't process the expected amount of messages: " + received + " != " + expect);
        } else {
            fail("Failed to receive the messages within the specified time");
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

    protected void consumeMessages(CountDownLatch latch) {
        try {
            awsSqsClient.receive(sqsQueueUrl, this::checkMessages);
        } catch (Throwable t) {
            LOG.error("Failed to consume messages: {}", t.getMessage(), t);
            fail(t.getMessage());
        } finally {
            latch.countDown();
        }
    }

    @Test
    @Timeout(value = 90)
    public void testBasicSendReceive() throws Exception {
        Properties amazonProperties = service.getConnectionProperties();
        String topicName = getTopicForTest(this.getClass());

        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSSNSPropertyFactory
                .basic()
                .withName("CamelAWSSNSSinkConnectorDefault")
                .withTopics(topicName)
                .withTopicOrArn(queueName)
                .withSubscribeSNStoSQS(sqsQueueUrl)
                .withAutoCreateTopic(true)
                .withConfiguration(TestSnsConfiguration.class.getName())
                .withAmazonConfig(amazonProperties);

        runTest(connectorPropertyFactory, topicName, expect);
    }

    @Test
    @Timeout(value = 90)
    public void testBasicSendReceiveUsingKafkaStyle() throws Exception {
        Properties amazonProperties = service.getConnectionProperties();
        String topicName = getTopicForTest(this.getClass());

        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSSNSPropertyFactory
                .basic()
                .withName("CamelAWSSNSSinkKafkaStyleConnector")
                .withTopics(topicName)
                .withTopicOrArn(queueName)
                .withSubscribeSNStoSQS(sqsQueueUrl)
                .withAutoCreateTopic(true)
                .withConfiguration(TestSnsConfiguration.class.getName())
                .withAmazonConfig(amazonProperties, CamelAWSSNSPropertyFactory.KAFKA_STYLE);

        runTest(connectorPropertyFactory, topicName, expect);
    }

    @Test
    @Timeout(value = 90)
    public void testBasicSendReceiveUsingUrl() throws Exception {
        Properties amazonProperties = service.getConnectionProperties();
        String topicName = getTopicForTest(this.getClass());

        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSSNSPropertyFactory
                .basic()
                .withName("CamelAWSSNSSinkKafkaStyleConnectorWithUrl")
                .withTopics(topicName)
                .withUrl(queueName)
                    .append("queueUrl", sqsQueueUrl)
                    .append("subscribeSNStoSQS", "true")
                    .append("region", amazonProperties.getProperty(AWSConfigs.REGION, Region.US_EAST_1.id()))
                    .append("configuration", classRef(TestSnsConfiguration.class.getName()))
                    .append("autoCreateTopic", "true")
                    .buildUrl();

        runTest(connectorPropertyFactory, topicName, expect);

    }
}
