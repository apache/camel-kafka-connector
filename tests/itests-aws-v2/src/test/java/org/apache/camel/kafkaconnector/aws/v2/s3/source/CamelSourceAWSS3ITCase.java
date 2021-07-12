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
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.aws.v2.s3.common.S3Utils;
import org.apache.camel.kafkaconnector.aws.v2.s3.common.TestS3Configuration;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSourceTestSupport;
import org.apache.camel.kafkaconnector.common.test.TestMessageConsumer;
import org.apache.camel.test.infra.aws.common.AWSCommon;
import org.apache.camel.test.infra.aws.common.AWSConfigs;
import org.apache.camel.test.infra.aws.common.services.AWSService;
import org.apache.camel.test.infra.aws2.clients.AWSSDKClientUtils;
import org.apache.camel.test.infra.aws2.services.AWSServiceFactory;
import org.apache.camel.test.infra.common.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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

import static org.apache.camel.kafkaconnector.aws.v2.s3.common.S3Utils.createBucket;
import static org.apache.camel.kafkaconnector.aws.v2.s3.common.S3Utils.deleteBucket;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnabledIfSystemProperty(named = "enable.slow.tests", matches = "true")
public class CamelSourceAWSS3ITCase extends CamelSourceTestSupport {
    @RegisterExtension
    public static AWSService service = AWSServiceFactory.createS3Service();
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceAWSS3ITCase.class);

    private S3Client awsS3Client;
    private String bucketName;
    private String topicName;

    private int expect;
    private File[] files;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-aws2-s3-kafka-connector"};
    }

    @BeforeAll
    public void setupTestFiles() throws IOException {
        final URL resourceDir = this.getClass().getResource(".");
        final File baseTestDir = new File(resourceDir.getFile());

        files = S3Utils.getFilesToSend(baseTestDir);

        expect = files.length;
    }


    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);

        awsS3Client = AWSSDKClientUtils.newS3Client();
        bucketName = AWSCommon.DEFAULT_S3_BUCKET + TestUtils.randomWithRange(0, 100);

        try {
            createBucket(awsS3Client, bucketName);
        } catch (Exception e) {
            LOG.error("Unable to create bucket: {}", e.getMessage(), e);
            fail("Unable to create bucket");
        }
    }

    @AfterEach
    public void tearDown() {
        try {
            deleteBucket(awsS3Client, bucketName);
        } catch (Exception e) {
            LOG.warn("Unable to delete bucked: {}", e.getMessage(), e);
        }
    }

    @Override
    protected void produceTestData() {
        S3Utils.sendFilesFromPath(awsS3Client, bucketName, files);
    }

    @Override
    protected void verifyMessages(TestMessageConsumer<?> consumer) {
        int received = consumer.consumedMessages().size();

        assertEquals(expect, received,  "Didn't process the expected amount of messages");
    }


    @Test
    @Timeout(180)
    public void testBasicSendReceive() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSS3PropertyFactory
                .basic()
                .withKafkaTopic(topicName)
                .withConfiguration(TestS3Configuration.class.getName())
                .withBucketNameOrArn(bucketName)
                .withAmazonConfig(service.getConnectionProperties());

        runTest(connectorPropertyFactory, topicName, expect);
    }

    @Test
    @Timeout(180)
    public void testBasicSendReceiveWithMaxMessagesPerPoll() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSS3PropertyFactory
                .basic()
                .withKafkaTopic(topicName)
                .withConfiguration(TestS3Configuration.class.getName())
                .withMaxMessagesPerPoll(5)
                .withBucketNameOrArn(bucketName)
                .withAmazonConfig(service.getConnectionProperties());

        runTest(connectorPropertyFactory, topicName, expect);
    }

    @Test
    @Timeout(180)
    public void testBasicSendReceiveWithKafkaStyle() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSS3PropertyFactory
                .basic()
                .withKafkaTopic(topicName)
                .withConfiguration(TestS3Configuration.class.getName())
                .withBucketNameOrArn(bucketName)
                .withAmazonConfig(service.getConnectionProperties(), CamelAWSS3PropertyFactory.KAFKA_STYLE);

        runTest(connectorPropertyFactory, topicName, expect);
    }

    @Test
    @Timeout(180)
    public void testBasicSendReceiveUsingUrl() throws ExecutionException, InterruptedException {
        Properties amazonProperties = service.getConnectionProperties();

        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSS3PropertyFactory
                .basic()
                .withKafkaTopic(topicName)
                .withConfiguration(TestS3Configuration.class.getName())
                .withUrl(bucketName)
                    .append("accessKey", amazonProperties.getProperty(AWSConfigs.ACCESS_KEY))
                    .append("secretKey", amazonProperties.getProperty(AWSConfigs.SECRET_KEY))
                    .appendIfAvailable("proxyProtocol", amazonProperties.getProperty(AWSConfigs.PROTOCOL))
                    .append("region", amazonProperties.getProperty(AWSConfigs.REGION, Region.US_EAST_1.id()))
                .buildUrl();

        runTest(connectorPropertyFactory, topicName, expect);
    }
}
