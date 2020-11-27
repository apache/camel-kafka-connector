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

package org.apache.camel.kafkaconnector.aws.v2.s3.source;

import java.io.File;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.aws.v2.clients.AWSSDKClientUtils;
import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.test.infra.aws.common.AWSCommon;
import org.apache.camel.test.infra.aws.common.AWSConfigs;
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
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnabledIfSystemProperty(named = "enable.slow.tests", matches = "true")
public class CamelSourceAWSS3ITCase extends AbstractKafkaTest {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceAWSS3ITCase.class);

    @RegisterExtension
    AWSService<S3Client> service = AWSServiceFactory.createS3Service();

    private S3Client awsS3Client;
    private String bucketName;

    private volatile int received;
    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-aws2-s3-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        awsS3Client = service.getClient();
        received = 0;
        bucketName = AWSCommon.DEFAULT_S3_BUCKET + TestUtils.randomWithRange(0, 100);

        try {
            CreateBucketRequest request = CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .build();

            awsS3Client.createBucket(request);
        } catch (Exception e) {
            LOG.error("Unable to create bucket: {}", e.getMessage(), e);
            fail("Unable to create bucket");
        }
    }

    @AfterEach
    public void tearDown() {
        try {
            AWSSDKClientUtils.deleteBucket(awsS3Client, bucketName);
        } catch (Exception e) {
            LOG.warn("Unable to delete bucked: {}", e.getMessage(), e);
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
        getKafkaConnectService().initializeConnector(connectorPropertyFactory);

        LOG.debug("Putting S3 objects");
        for (int i = 0; i < expect; i++) {
            String name = "file" + i + ".test";
            LOG.debug("Trying to read file {}", name);
            URL fileResource = this.getClass().getResource(name);
            LOG.debug("Found file at {}", fileResource.getPath());
            String file = fileResource.getFile();
            LOG.debug("Using file {}", file);

            LOG.trace("Putting file {}", file);
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(name)
                    .build();

            awsS3Client.putObject(putObjectRequest, new File(file).toPath());
        }
        LOG.debug("Done putting S3S objects");

        LOG.debug("Creating the consumer ...");
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        kafkaClient.consume(TestUtils.getDefaultTestTopic(this.getClass()), this::checkRecord);
        LOG.debug("Created the consumer ...");

        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }

    @Test
    @Timeout(180)
    public void testBasicSendReceive() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSS3PropertyFactory
                .basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withConfiguration(TestS3Configuration.class.getName())
                .withBucketNameOrArn(bucketName)
                .withAmazonConfig(service.getConnectionProperties());

        runTest(connectorPropertyFactory);
    }

    @Test
    @Timeout(180)
    public void testBasicSendReceiveWithMaxMessagesPerPoll() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSS3PropertyFactory
                .basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withConfiguration(TestS3Configuration.class.getName())
                .withMaxMessagesPerPoll(5)
                .withBucketNameOrArn(bucketName)
                .withAmazonConfig(service.getConnectionProperties());

        runTest(connectorPropertyFactory);
    }

    @Test
    @Timeout(180)
    public void testBasicSendReceiveWithKafkaStyle() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSS3PropertyFactory
                .basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withConfiguration(TestS3Configuration.class.getName())
                .withBucketNameOrArn(bucketName)
                .withAmazonConfig(service.getConnectionProperties(), CamelAWSS3PropertyFactory.KAFKA_STYLE);

        runTest(connectorPropertyFactory);
    }

    @Test
    @Timeout(180)
    public void testBasicSendReceiveUsingUrl() throws ExecutionException, InterruptedException {
        Properties amazonProperties = service.getConnectionProperties();

        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSS3PropertyFactory
                .basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withConfiguration(TestS3Configuration.class.getName())
                .withUrl(bucketName)
                    .append("accessKey", amazonProperties.getProperty(AWSConfigs.ACCESS_KEY))
                    .append("secretKey", amazonProperties.getProperty(AWSConfigs.SECRET_KEY))
                    .appendIfAvailable("proxyProtocol", amazonProperties.getProperty(AWSConfigs.PROTOCOL))
                    .append("region", amazonProperties.getProperty(AWSConfigs.REGION, Region.US_EAST_1.id()))
                .buildUrl();

        runTest(connectorPropertyFactory);
    }

}
