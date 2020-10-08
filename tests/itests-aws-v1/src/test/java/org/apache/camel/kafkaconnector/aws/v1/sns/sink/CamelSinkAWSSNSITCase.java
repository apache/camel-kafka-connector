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

package org.apache.camel.kafkaconnector.aws.v1.sns.sink;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import org.apache.camel.kafkaconnector.aws.common.AWSCommon;
import org.apache.camel.kafkaconnector.aws.common.AWSConfigs;
import org.apache.camel.kafkaconnector.aws.common.services.AWSService;
import org.apache.camel.kafkaconnector.aws.v1.clients.AWSSQSClient;
import org.apache.camel.kafkaconnector.aws.v1.services.AWSServiceFactory;
import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Testcontainers
public class CamelSinkAWSSNSITCase extends AbstractKafkaTest  {
    @RegisterExtension
    public static AWSService<AmazonSQS> service = AWSServiceFactory.createSNSService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkAWSSNSITCase.class);

    private AWSSQSClient awsSqsClient;
    private String sqsQueueUrl;
    private String queueName;

    private volatile int received;
    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-aws-sns-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        awsSqsClient = new AWSSQSClient(service.getClient());

        queueName = AWSCommon.DEFAULT_SQS_QUEUE_FOR_SNS + "-" + TestUtils.randomWithRange(0, 1000);
        sqsQueueUrl = awsSqsClient.getQueue(queueName);

        LOG.info("Created SQS queue {}", sqsQueueUrl);

        received = 0;
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


    private void consumeMessages(CountDownLatch latch) {
        try {
            awsSqsClient.receiveFrom(sqsQueueUrl, this::checkMessages);
        } catch (Throwable t) {
            LOG.error("Failed to consume messages: {}", t.getMessage(), t);
            fail(t.getMessage());
        } finally {
            latch.countDown();
        }
    }

    public void runTest(ConnectorPropertyFactory connectorPropertyFactory) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();

        getKafkaConnectService().initializeConnector(connectorPropertyFactory);

        ExecutorService service = Executors.newCachedThreadPool();

        LOG.debug("Creating the consumer ...");
        CountDownLatch latch = new CountDownLatch(1);
        service.submit(() -> consumeMessages(latch));

        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        for (int i = 0; i < expect; i++) {
            kafkaClient.produce(TestUtils.getDefaultTestTopic(this.getClass()), "Sink test message " + i);
        }

        LOG.debug("Created the consumer ... About to receive messages");

        if (latch.await(120, TimeUnit.SECONDS)) {
            assertEquals(expect, received, "Didn't process the expected amount of messages: " + received + " != " + expect);
        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }


    @Test
    @Timeout(value = 90)
    public void testBasicSendReceive() {
        try {
            Properties amazonProperties = service.getConnectionProperties();

            ConnectorPropertyFactory connectorPropertyFactory = CamelAWSSNSPropertyFactory.basic()
                    .withName("CamelAWSSNSSinkConnectorDefault")
                    .withTopics(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withTopicOrArn(queueName)
                    .withSubscribeSNStoSQS(sqsQueueUrl)
                    .withConfiguration(TestSNSConfiguration.class.getName())
                    .withAmazonConfig(amazonProperties);

            runTest(connectorPropertyFactory);
        } catch (Exception e) {
            LOG.error("Amazon SNS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    @Test
    @Timeout(value = 90)
    public void testBasicSendReceiveUsingKafkaStyle() {
        try {
            Properties amazonProperties = service.getConnectionProperties();

            ConnectorPropertyFactory connectorPropertyFactory = CamelAWSSNSPropertyFactory.basic()
                    .withName("CamelAWSSNSSinkKafkaStyleConnector")
                    .withTopics(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withTopicOrArn(queueName)
                    .withSubscribeSNStoSQS(sqsQueueUrl)
                    .withConfiguration(TestSNSConfiguration.class.getName())
                    .withAmazonConfig(amazonProperties, CamelAWSSNSPropertyFactory.KAFKA_STYLE);

            runTest(connectorPropertyFactory);
        } catch (Exception e) {
            LOG.error("Amazon SNS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    @Disabled("AWS SNS component is failing to parse the sink URL for this one")
    @Test
    @Timeout(value = 90)
    public void testBasicSendReceiveUsingUrl() {
        try {
            Properties amazonProperties = service.getConnectionProperties();

            ConnectorPropertyFactory connectorPropertyFactory = CamelAWSSNSPropertyFactory.basic()
                    .withName("CamelAWSSNSSinkKafkaStyleConnector")
                    .withTopics(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withUrl(queueName)
                        .append("queueUrl", sqsQueueUrl)
                        .append("subscribeSNStoSQS", "true")
                        .append("accessKey", amazonProperties.getProperty(AWSConfigs.ACCESS_KEY))
                        .append("secretKey", amazonProperties.getProperty(AWSConfigs.SECRET_KEY))
                        .append("region", amazonProperties.getProperty(AWSConfigs.REGION, Regions.US_EAST_1.name()))
                        .append("configuration", "#class:" + TestSNSConfiguration.class.getName())
                        .buildUrl();

            runTest(connectorPropertyFactory);
        } catch (Exception e) {
            LOG.error("Amazon SNS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }
}
