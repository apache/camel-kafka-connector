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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.test.infra.aws.common.AWSCommon;
import org.apache.camel.test.infra.aws.common.services.AWSService;
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
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.model.CreateStreamRequest;
import software.amazon.awssdk.services.kinesis.model.CreateStreamResponse;
import software.amazon.awssdk.services.kinesis.model.DeleteStreamRequest;
import software.amazon.awssdk.services.kinesis.model.DeleteStreamResponse;
import software.amazon.awssdk.services.kinesis.model.DescribeStreamRequest;
import software.amazon.awssdk.services.kinesis.model.DescribeStreamResponse;
import software.amazon.awssdk.services.kinesis.model.KinesisException;
import software.amazon.awssdk.services.kinesis.model.PutRecordsRequest;
import software.amazon.awssdk.services.kinesis.model.PutRecordsRequestEntry;
import software.amazon.awssdk.services.kinesis.model.PutRecordsResponse;
import software.amazon.awssdk.services.kinesis.model.ResourceInUseException;
import software.amazon.awssdk.services.kinesis.model.ResourceNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnabledIfSystemProperty(named = "enable.slow.tests", matches = "true")
public class CamelSourceAWSKinesisITCase extends AbstractKafkaTest {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceAWSKinesisITCase.class);

    @RegisterExtension
    AWSService<KinesisClient> awsService = AWSServiceFactory.createKinesisService();

    private String streamName;
    private KinesisClient kinesisClient;

    private volatile int received;
    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-aws2-kinesis-kafka-connector"};
    }

    private void doCreateStream() {
        CreateStreamRequest request = CreateStreamRequest.builder()
                .streamName(streamName)
                .shardCount(1)
                .build();

        try {
            CreateStreamResponse response = kinesisClient.createStream(request);

            if (response.sdkHttpResponse().isSuccessful()) {
                LOG.info("Stream created successfully");
            } else {
                fail("Failed to create the stream");
            }
        } catch (KinesisException e) {
            LOG.error("Unable to create stream: {}", e.getMessage(), e);
            fail("Unable to create stream");
        }
    }

    private void createStream() {
        try {
            LOG.info("Checking whether the stream exists already");
            DescribeStreamRequest request = DescribeStreamRequest.builder()
                    .streamName(streamName)
                    .build();

            DescribeStreamResponse response = kinesisClient.describeStream(request);

            int status = response.sdkHttpResponse().statusCode();
            LOG.info("Kinesis stream check result: {}", status);
        } catch (KinesisException e) {
            LOG.info("The stream does not exist, auto creating it: {}", e.getMessage(), e);
            doCreateStream();
        }
    }

    private void doDeleteStream() {
        DeleteStreamRequest request = DeleteStreamRequest.builder()
                .streamName(streamName)
                .build();

        DeleteStreamResponse response = kinesisClient.deleteStream(request);

        if (response.sdkHttpResponse().isSuccessful()) {
            LOG.info("Stream deleted successfully");
        } else {
            fail("Failed to delete the stream");
        }
    }

    private void deleteStream() {
        try {
            LOG.info("Checking whether the stream exists already");


            DescribeStreamRequest request = DescribeStreamRequest.builder()
                    .streamName(streamName)
                    .build();

            DescribeStreamResponse response = kinesisClient.describeStream(request);

            if (response.sdkHttpResponse().isSuccessful()) {
                LOG.info("Kinesis stream check result");
                doDeleteStream();
            }
        } catch (ResourceNotFoundException e) {
            LOG.info("The stream does not exist, skipping deletion");
        } catch (ResourceInUseException e) {
            LOG.info("The stream exist but cannot be deleted because it's in use");
            doDeleteStream();
        }
    }


    @BeforeEach
    public void setUp() {
        streamName = AWSCommon.KINESIS_STREAM_BASE_NAME + "-" + TestUtils.randomWithRange(0, 100);

        kinesisClient = awsService.getClient();
        received = 0;

        createStream();
    }


    @AfterEach
    public void tearDown() {
        deleteStream();
    }

    private boolean checkRecord(ConsumerRecord<String, String> record) {
        LOG.debug("Received: {}", record.value());
        received++;

        if (received == expect) {
            return false;
        }

        return true;
    }

    private void putRecords() {
        List<PutRecordsRequestEntry> putRecordsRequestEntryList = new ArrayList<>();

        LOG.debug("Adding data to the Kinesis stream");
        for (int i = 0; i < expect; i++) {
            String partition = String.format("partitionKey-%d", i);

            PutRecordsRequestEntry putRecordsRequestEntry = PutRecordsRequestEntry.builder()
                    .data(SdkBytes.fromByteArray(String.valueOf(i).getBytes()))
                    .partitionKey(partition)
                    .build();

            LOG.debug("Added data {} (as bytes) to partition {}", i, partition);
            putRecordsRequestEntryList.add(putRecordsRequestEntry);
        }

        LOG.debug("Done creating the data records");

        PutRecordsRequest putRecordsRequest = PutRecordsRequest
                .builder()
                .streamName(streamName)
                .records(putRecordsRequestEntryList)
                .build();

        int retries = 5;
        do {
            try {
                PutRecordsResponse response = kinesisClient.putRecords(putRecordsRequest);

                if (response.sdkHttpResponse().isSuccessful()) {
                    LOG.debug("Done putting the data records into the stream");
                } else {
                    fail("Unable to put all the records into the stream");
                }

                break;
            } catch (AwsServiceException e) {
                retries--;

                /*
                 This works around the "... Cannot deserialize instance of `...AmazonKinesisException` out of NOT_AVAILABLE token

                 It may take some time for the local Kinesis backend to be fully up - even though the container is
                 reportedly up and running. Therefore, it tries a few more times
                 */
                LOG.trace("Failed to put the records: {}. Retrying in 2 seconds ...", e.getMessage());
                if (retries == 0) {
                    LOG.error("Failed to put the records: {}", e.getMessage(), e);
                    throw e;
                }


                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(2));
                } catch (InterruptedException ex) {
                    break;
                }
            }
        } while (retries > 0);


    }

    public void runtTest(ConnectorPropertyFactory connectorPropertyFactory) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnector(connectorPropertyFactory);

        putRecords();
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
