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

package org.apache.camel.kafkaconnector.source.aws.kinesis;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.amazonaws.services.kinesis.model.PutRecordsResult;
import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.ContainerUtil;
import org.apache.camel.kafkaconnector.KafkaConnectRunner;
import org.apache.camel.kafkaconnector.TestCommon;
import org.apache.camel.kafkaconnector.clients.kafka.KafkaClient;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;

public class CamelSourceAWSKinesisITCase {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceAWSKinesisITCase.class);
    private static final int KINESIS_PORT = 4568;

    @Rule
    public KafkaContainer kafka = new KafkaContainer().withEmbeddedZookeeper();

    @Rule
    public LocalStackContainer localStackContainer = new LocalStackContainer()
            .withServices(LocalStackContainer.Service.KINESIS);

    private KafkaConnectRunner kafkaConnectRunner;
    private AmazonKinesis awsKinesisClient;

    private volatile int received;
    private final int expect = 10;


    @Before
    public void setUp() {
        ContainerUtil.waitForInitialization(kafka);
        LOG.info("Kafka bootstrap server running at address {}", kafka.getBootstrapServers());

        LOG.info("Waiting for Kinesis initialization");
        ContainerUtil.waitForHttpInitialization(localStackContainer, localStackContainer.getMappedPort(KINESIS_PORT));
        LOG.info("Kinesis Initialized");

        final String kinesisInstance = localStackContainer
                .getEndpointConfiguration(LocalStackContainer.Service.KINESIS)
                .getServiceEndpoint();

        LOG.info("Kinesis instance running at {}", kinesisInstance);

        Properties properties = ContainerUtil.setupAWSConfigs(localStackContainer, KINESIS_PORT);

        ConnectorPropertyFactory testProperties = new CamelAWSKinesisPropertyFactory(1,
                TestCommon.DEFAULT_TEST_TOPIC, TestCommon.DEFAULT_KINESIS_STREAM, properties);

        kafkaConnectRunner =  new KafkaConnectRunner(kafka.getBootstrapServers());
        kafkaConnectRunner.getConnectorPropertyProducers().add(testProperties);

        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setProtocol(Protocol.HTTP);

        awsKinesisClient = AmazonKinesisClientBuilder
                .standard()
                .withEndpointConfiguration(localStackContainer.getEndpointConfiguration(LocalStackContainer.Service.KINESIS))
                .withCredentials(localStackContainer.getDefaultCredentialsProvider())
                .withClientConfiguration(clientConfiguration)
                .build();

        awsKinesisClient.createStream(TestCommon.DEFAULT_KINESIS_STREAM, 1);

    }

    private boolean checkRecord(ConsumerRecord<String, String> record) {
        LOG.debug("Received: {}", record.value());
        received++;

        if (received == expect) {
            return false;
        }

        return true;
    }

    @Test(timeout = 120000)
    public void testBasicSendReceive() {
        CountDownLatch latch = new CountDownLatch(1);

        ExecutorService service = Executors.newCachedThreadPool();

        service.submit(() -> kafkaConnectRunner.run(latch));

        try {
            /*
             * These tests need the embedded Kafka connect runner to be completely
             * initialized before they can start running. Therefore we force it to
             * wait until the connect instance signals it's done initializing.
             */
            int seconds = 30;

            if (!latch.await(seconds, TimeUnit.SECONDS)) {
            }

            service.submit(() -> putRecords());
            LOG.debug("Initialized the connector and put the data for the test execution");
        } catch (InterruptedException e) {
            Assert.fail("Test interrupted");
        }

        LOG.debug("Creating the consumer ...");
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(kafka.getBootstrapServers());
        kafkaClient.consume(TestCommon.DEFAULT_TEST_TOPIC, this::checkRecord);
        LOG.debug("Created the consumer ...");

        kafkaConnectRunner.stop();
        Assert.assertTrue("Didn't process the expected amount of messages", received == expect);
    }

    private void putRecords() {
        PutRecordsRequest putRecordsRequest = new PutRecordsRequest();
        putRecordsRequest.setStreamName(TestCommon.DEFAULT_KINESIS_STREAM);

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
        putRecordsRequest.setRecords(putRecordsRequestEntryList);
        PutRecordsResult putRecordsResult = awsKinesisClient.putRecords(putRecordsRequest);

        if (putRecordsResult.getFailedRecordCount() == 0) {
            LOG.debug("Done putting the data records into the stream");
        } else {
            Assert.fail("Unable to put all the records into the stream");
        }


    }
}
