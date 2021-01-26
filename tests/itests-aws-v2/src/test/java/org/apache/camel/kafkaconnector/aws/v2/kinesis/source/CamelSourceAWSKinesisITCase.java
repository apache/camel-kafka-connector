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

package org.apache.camel.kafkaconnector.aws.v2.kinesis.source;

import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.aws.v2.kinesis.common.TestKinesisConfiguration;
import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.test.infra.aws.common.AWSCommon;
import org.apache.camel.test.infra.aws.common.services.AWSService;
import org.apache.camel.test.infra.aws2.clients.AWSSDKClientUtils;
import org.apache.camel.test.infra.aws2.services.AWSServiceFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.kinesis.KinesisClient;

import static org.apache.camel.kafkaconnector.aws.v2.kinesis.common.KinesisUtils.createStream;
import static org.apache.camel.kafkaconnector.aws.v2.kinesis.common.KinesisUtils.deleteStream;
import static org.apache.camel.kafkaconnector.aws.v2.kinesis.common.KinesisUtils.putRecords;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnabledIfSystemProperty(named = "enable.slow.tests", matches = "true")
public class CamelSourceAWSKinesisITCase extends AbstractKafkaTest {

    @RegisterExtension
    public static AWSService awsService = AWSServiceFactory.createKinesisService();
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceAWSKinesisITCase.class);

    private String streamName;
    private KinesisClient kinesisClient;

    private volatile int received;
    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-aws2-kinesis-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        streamName = AWSCommon.KINESIS_STREAM_BASE_NAME + "-" + TestUtils.randomWithRange(0, 100);

        kinesisClient = AWSSDKClientUtils.newKinesisClient();
        received = 0;

        createStream(kinesisClient, streamName);
    }


    @AfterEach
    public void tearDown() {
        deleteStream(kinesisClient, streamName);
    }

    private boolean checkRecord(ConsumerRecord<String, String> record) {
        LOG.debug("Received: {}", record.value());
        received++;

        if (received == expect) {
            return false;
        }

        return true;
    }



    public void runtTest(ConnectorPropertyFactory connectorPropertyFactory) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnector(connectorPropertyFactory);

        putRecords(kinesisClient, streamName, expect);
        LOG.debug("Initialized the connector and put the data for the test execution");

        LOG.debug("Creating the consumer ...");
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        kafkaClient.consume(TestUtils.getDefaultTestTopic(this.getClass()), this::checkRecord);
        LOG.debug("Created the consumer ...");

        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }

    @Test
    @Timeout(120)
    public void testBasicSendReceive() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSKinesisPropertyFactory
                .basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withAmazonConfig(awsService.getConnectionProperties())
                .withConfiguration(TestKinesisConfiguration.class.getName())
                .withStreamName(streamName);

        runtTest(connectorPropertyFactory);
    }

    @Test
    @Timeout(120)
    public void testBasicSendReceiveWithKafkaStyle() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSKinesisPropertyFactory
                .basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withAmazonConfig(awsService.getConnectionProperties(), CamelAWSKinesisPropertyFactory.KAFKA_STYLE)
                .withConfiguration(TestKinesisConfiguration.class.getName())
                .withStreamName(streamName);

        runtTest(connectorPropertyFactory);
    }

    @Test
    @Timeout(120)
    public void testBasicSendReceiveUsingUrl() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSKinesisPropertyFactory
                .basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withAmazonConfig(awsService.getConnectionProperties())
                .withConfiguration(TestKinesisConfiguration.class.getName())
                .withUrl(streamName)
                .buildUrl();

        runtTest(connectorPropertyFactory);
    }

}
