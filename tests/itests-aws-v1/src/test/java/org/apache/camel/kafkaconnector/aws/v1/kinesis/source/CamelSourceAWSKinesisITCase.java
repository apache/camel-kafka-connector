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

package org.apache.camel.kafkaconnector.aws.v1.kinesis.source;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.CreateStreamResult;
import com.amazonaws.services.kinesis.model.DeleteStreamResult;
import com.amazonaws.services.kinesis.model.DescribeStreamResult;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.amazonaws.services.kinesis.model.PutRecordsResult;
import com.amazonaws.services.kinesis.model.ResourceInUseException;
import com.amazonaws.services.kinesis.model.ResourceNotFoundException;
import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.test.infra.aws.clients.AWSClientUtils;
import org.apache.camel.test.infra.aws.common.AWSCommon;
import org.apache.camel.test.infra.aws.common.services.AWSService;
import org.apache.camel.test.infra.aws.services.AWSServiceFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Testcontainers
public class CamelSourceAWSKinesisITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static AWSService service = AWSServiceFactory.createKinesisService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceAWSKinesisITCase.class);

    private String streamName;

    private AmazonKinesis awsKinesisClient;

    private volatile int received;
    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-aws-kinesis-kafka-connector"};
    }

    private void doCreateStream() {
        CreateStreamResult result = awsKinesisClient.createStream(streamName, 1);
        if (result.getSdkHttpMetadata().getHttpStatusCode() != 200) {
            fail("Failed to create the stream");
        } else {
            LOG.info("Stream created successfully");
        }
    }

    private void createStream() {
        try {
            LOG.info("Checking whether the stream exists already");
            DescribeStreamResult describeStreamResult = awsKinesisClient.describeStream(streamName);

            int status = describeStreamResult.getSdkHttpMetadata().getHttpStatusCode();
            LOG.info("Kinesis stream check result: {}", status);
        } catch (ResourceNotFoundException e) {
            LOG.info("The stream does not exist, auto creating it ...");
            doCreateStream();
        }
    }

    private void doDeleteStream() {
        DeleteStreamResult result = awsKinesisClient.deleteStream(streamName);

        if (result.getSdkHttpMetadata().getHttpStatusCode() != 200) {
            fail("Failed to delete the stream");
        } else {
            LOG.info("Stream deleted successfully");
        }
    }

    private void deleteStream() {
        try {
            LOG.info("Checking whether the stream exists already");
            DescribeStreamResult describeStreamResult = awsKinesisClient.describeStream(streamName);

            int status = describeStreamResult.getSdkHttpMetadata().getHttpStatusCode();
            LOG.info("Kinesis stream check result: {}", status);
            doDeleteStream();
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

        awsKinesisClient = AWSClientUtils.newKinesisClient();
        received = 0;

        createStream();
    }

    @AfterEach
    public void tearDown() {
        deleteStream();

        awsKinesisClient.shutdown();
    }

    private boolean checkRecord(ConsumerRecord<String, String> record) {
        LOG.debug("Received: {}", record.value());
        received++;

        if (received == expect) {
            return false;
        }

        return true;
    }

    public void runtTest(ConnectorPropertyFactory connectorPropertyFactory)
            throws ExecutionException, InterruptedException {
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
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSKinesisPropertyFactory.basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withAmazonConfig(service.getConnectionProperties())
                .withConfiguration(TestKinesisConfiguration.class.getName()).withStreamName(streamName);

        runtTest(connectorPropertyFactory);
    }

    @Test
    @Timeout(120)
    public void testBasicSendReceiveWithKafkaStyle() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSKinesisPropertyFactory.basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withAmazonConfig(service.getConnectionProperties(), CamelAWSKinesisPropertyFactory.KAFKA_STYLE)
                .withConfiguration(TestKinesisConfiguration.class.getName()).withStreamName(streamName);

        runtTest(connectorPropertyFactory);
    }

    @Test
    @Timeout(120)
    public void testBasicSendReceiveUsingUrl() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSKinesisPropertyFactory.basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withAmazonConfig(service.getConnectionProperties())
                .withConfiguration(TestKinesisConfiguration.class.getName()).withUrl(streamName).buildUrl();

        runtTest(connectorPropertyFactory);
    }

    private void putRecords() {
        PutRecordsRequest putRecordsRequest = new PutRecordsRequest();
        putRecordsRequest.setStreamName(streamName);

        List<PutRecordsRequestEntry> putRecordsRequestEntryList = new ArrayList<>();

        LOG.debug("Adding data to the Kinesis stream");
        for (int i = 0; i < expect; i++) {
            PutRecordsRequestEntry putRecordsRequestEntry = new PutRecordsRequestEntry();
            putRecordsRequestEntry.setData(ByteBuffer.wrap(String.valueOf(i).getBytes()));

            String partition = String.format("partitionKey-%d", i);
            putRecordsRequestEntry.setPartitionKey(partition);

            LOG.debug("Added data {} (as bytes) to partition {}", i, partition);

            putRecordsRequestEntryList.add(putRecordsRequestEntry);
        }

        LOG.debug("Done creating the data records");

        int retries = 5;
        do {
            try {
                putRecordsRequest.setRecords(putRecordsRequestEntryList);
                PutRecordsResult putRecordsResult = awsKinesisClient.putRecords(putRecordsRequest);

                if (putRecordsResult.getFailedRecordCount() == 0) {
                    LOG.debug("Done putting the data records into the stream");
                } else {
                    fail("Unable to put all the records into the stream");
                }

                break;
            } catch (AmazonServiceException e) {
                retries--;

                /*
                 * This works around the "... Cannot deserialize instance of `...AmazonKinesisException` out of
                 * NOT_AVAILABLE token
                 * 
                 * It may take some time for the local Kinesis backend to be fully up - even though the container is
                 * reportedly up and running. Therefore, it tries a few more times
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
}
