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

package org.apache.camel.kafkaconnector.aws.kinesis.source;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.CreateStreamResult;
import com.amazonaws.services.kinesis.model.DeleteStreamResult;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.amazonaws.services.kinesis.model.PutRecordsResult;
import org.apache.camel.kafkaconnector.aws.common.AWSCommon;
import org.apache.camel.kafkaconnector.aws.services.AWSService;
import org.apache.camel.kafkaconnector.aws.services.AWSServiceFactory;
import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class CamelSourceAWSKinesisITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static AWSService<AmazonKinesis> service = AWSServiceFactory.createKinesisService();
    
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceAWSKinesisITCase.class);

    private AmazonKinesis awsKinesisClient;

    private volatile int received;
    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-aws-kinesis-kafka-connector"};
    }


    @BeforeEach
    public void setUp() {
        awsKinesisClient = service.getClient();
        received = 0;

        CreateStreamResult result = awsKinesisClient.createStream(AWSCommon.DEFAULT_KINESIS_STREAM, 1);
        if (result.getSdkHttpMetadata().getHttpStatusCode() != 200) {
            fail("Failed to create the stream");
        } else {
            LOG.info("Stream created successfully");
        }
    }

    @AfterEach
    public void tearDown() {
        DeleteStreamResult result = awsKinesisClient.deleteStream(AWSCommon.DEFAULT_KINESIS_STREAM);

        if (result.getSdkHttpMetadata().getHttpStatusCode() != 200) {
            fail("Failed to delete the stream");
        } else {
            try {
                // Because of the latency used to simulate the Kinesis API call (defined by the KINESIS_LATENCY) in
                // the LocalStack configuration, the test needs to wait at least the same amount of time as set there
                // in order to proceed. Otherwise the it fails to create the stream in the setUp phase.
                // Ref.: https://github.com/localstack/localstack/issues/231#issuecomment-319959693
                Thread.sleep(500);
                LOG.info("Stream deleted successfully");
            } catch (InterruptedException e) {
                fail("Test interrupted while waiting for the stream to cool down");
            }
        }

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
                .withAmazonConfig(service.getConnectionProperties())
                .withConfiguration(TestKinesisConfiguration.class.getName())
                .withStreamName(AWSCommon.DEFAULT_KINESIS_STREAM);

        runtTest(connectorPropertyFactory);
    }

    @Test
    @Timeout(120)
    public void testBasicSendReceiveWithKafkaStyle() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSKinesisPropertyFactory
                .basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withAmazonConfig(service.getConnectionProperties(), CamelAWSKinesisPropertyFactory.KAFKA_STYLE)
                .withConfiguration(TestKinesisConfiguration.class.getName())
                .withStreamName(AWSCommon.DEFAULT_KINESIS_STREAM);

        runtTest(connectorPropertyFactory);
    }

    @Test
    @Timeout(120)
    public void testBasicSendReceiveUsingUrl() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSKinesisPropertyFactory
                .basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withAmazonConfig(service.getConnectionProperties())
                .withConfiguration(TestKinesisConfiguration.class.getName())
                .withUrl(AWSCommon.DEFAULT_KINESIS_STREAM)
                    .buildUrl();

        runtTest(connectorPropertyFactory);
    }

    private void putRecords() {
        PutRecordsRequest putRecordsRequest = new PutRecordsRequest();
        putRecordsRequest.setStreamName(AWSCommon.DEFAULT_KINESIS_STREAM);

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
}
