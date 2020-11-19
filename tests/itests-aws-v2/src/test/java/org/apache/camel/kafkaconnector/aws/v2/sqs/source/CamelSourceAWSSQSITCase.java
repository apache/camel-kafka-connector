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

package org.apache.camel.kafkaconnector.aws.v2.sqs.source;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.aws.v2.clients.AWSSQSClient;
import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.test.infra.aws.common.AWSCommon;
import org.apache.camel.test.infra.aws.common.AWSConfigs;
import org.apache.camel.test.infra.aws.common.services.AWSService;
import org.apache.camel.test.infra.aws2.clients.AWSSDKClientUtils;
import org.apache.camel.test.infra.aws2.services.AWSServiceFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CamelSourceAWSSQSITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static AWSService service = AWSServiceFactory.createSQSService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceAWSSQSITCase.class);

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
        awssqsClient = new AWSSQSClient(AWSSDKClientUtils.newSQSClient());
        queueName = AWSCommon.BASE_SQS_QUEUE_NAME + "-" + TestUtils.randomWithRange(0, 1000);

        // TODO: this is a work-around for CAMEL-15833
        awssqsClient.createQueue(queueName);
        received = 0;
    }

    @AfterEach
    public void tearDown() {
        if (!awssqsClient.deleteQueue(queueName)) {
            fail("Failed to delete queue");
        }
    }

    private boolean checkRecord(ConsumerRecord<String, String> record) {
        LOG.debug("Received: {}", record.value());
        received++;

        if (received == expect) {
            return false;
        }

        return true;
    }

    public void runTest(ConnectorPropertyFactory connectorPropertyFactory) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnectorBlocking(connectorPropertyFactory, 1);

        LOG.debug("Sending SQS messages");
        for (int i = 0; i < expect; i++) {
            awssqsClient.send(queueName, "Source test message " + i);
        }
        LOG.debug("Done sending SQS messages");

        LOG.debug("Creating the consumer ...");
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        kafkaClient.consume(TestUtils.getDefaultTestTopic(this.getClass()), this::checkRecord);
        LOG.debug("Created the consumer ...");

        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceive() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSSQSPropertyFactory
                .basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withQueueOrArn(queueName)
                .withAmazonConfig(service.getConnectionProperties());

        runTest(connectorPropertyFactory);
    }

    // This test does not run remotely because SQS has a cool down period for
    // creating and removing the SQS queue
    @DisabledIfSystemProperty(named = "aws-service.instance.type", matches = "remote")
    @Test
    @Timeout(90)
    public void testBasicSendReceiveWithKafkaStyle() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSSQSPropertyFactory
                .basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withQueueOrArn(queueName)
                .withAmazonConfig(service.getConnectionProperties(), CamelAWSSQSPropertyFactory.KAFKA_STYLE);

        runTest(connectorPropertyFactory);
    }

    // This test does not run remotely because SQS has a cool down period for
    // creating and removing the SQS queue
    @DisabledIfSystemProperty(named = "aws-service.instance.type", matches = "remote")
    @Test
    @Timeout(90)
    public void testBasicSendReceiveUsingUrl() throws ExecutionException, InterruptedException {
        Properties amazonProperties = service.getConnectionProperties();

        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSSQSPropertyFactory
                .basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withUrl(queueName)
                .append("accessKey", amazonProperties.getProperty(AWSConfigs.ACCESS_KEY))
                .append("secretKey", amazonProperties.getProperty(AWSConfigs.SECRET_KEY))
                .append("protocol", amazonProperties.getProperty(AWSConfigs.PROTOCOL))
                .appendIfAvailable("amazonAWSHost", amazonProperties.getProperty(AWSConfigs.AMAZON_AWS_HOST))
                .append("region", amazonProperties.getProperty(AWSConfigs.REGION, Region.US_EAST_1.toString()))
                .buildUrl();

        runTest(connectorPropertyFactory);
    }
}
