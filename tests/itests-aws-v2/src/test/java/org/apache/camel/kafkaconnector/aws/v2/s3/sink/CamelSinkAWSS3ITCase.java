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

package org.apache.camel.kafkaconnector.aws.v2.s3.sink;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.CamelSinkTask;
import org.apache.camel.kafkaconnector.aws.v2.s3.common.S3Utils;
import org.apache.camel.kafkaconnector.aws.v2.s3.common.TestS3Configuration;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.common.test.StringMessageProducer;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.test.infra.aws.common.AWSCommon;
import org.apache.camel.test.infra.aws.common.services.AWSService;
import org.apache.camel.test.infra.aws2.clients.AWSSDKClientUtils;
import org.apache.camel.test.infra.aws2.services.AWSServiceFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.S3Object;

import static org.apache.camel.kafkaconnector.aws.v2.s3.common.S3Utils.createBucket;
import static org.apache.camel.kafkaconnector.aws.v2.s3.common.S3Utils.deleteBucket;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@EnabledIfSystemProperty(named = "enable.slow.tests", matches = "true")
public class CamelSinkAWSS3ITCase extends CamelSinkTestSupport {
    @RegisterExtension
    public static AWSService service = AWSServiceFactory.createS3Service();
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkAWSS3ITCase.class);

    private S3Client awsS3Client;
    private String bucketName;

    private volatile int received;
    private int expect = 10;

    private class CustomProducer extends StringMessageProducer {
        public CustomProducer(String bootstrapServer, String topicName, int count) {
            super(bootstrapServer, topicName, count);
        }

        @Override
        public Map<String, String> messageHeaders(String text, int current) {
            Map<String, String> headers = new HashMap<>();

            headers.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "CamelAwsS3Key",
                    "file" + current + ".txt");
            headers.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "CamelAwsS3BucketName",
                    bucketName);

            return headers;
        }
    }


    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try {
            while (true) {
                List<S3Object> objectList = S3Utils.listObjects(awsS3Client, bucketName);

                for (S3Object object : objectList) {
                    LOG.info("Object key: {}", object.key());
                }

                received = objectList.size();
                if (received >= expect) {
                    return;
                }

                if (!waitForData()) {
                    return;
                }
            }
        } finally {
            latch.countDown();
        }
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (latch.await(110, TimeUnit.SECONDS)) {
            assertEquals(expect, received, "Didn't process the expected amount of messages: " + received + " != " + expect);
        } else {
            fail(String.format("Failed to receive the messages within the specified time: received %d of %d",
                    received, expect));
        }
    }

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-aws2-s3-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        awsS3Client = AWSSDKClientUtils.newS3Client();
        received = 0;
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

    @Test
    @Timeout(180)
    public void testBasicSendReceive() throws Exception {
        Properties amazonProperties = service.getConnectionProperties();
        String topicName = TestUtils.getDefaultTestTopic(this.getClass());

        ConnectorPropertyFactory testProperties = CamelAWSS3PropertyFactory
                .basic()
                .withTopics(topicName)
                .withConfiguration(TestS3Configuration.class.getName())
                .withAmazonConfig(amazonProperties)
                .withBucketNameOrArn(bucketName)
                .withAutoCreateBucket(true);

        runTest(testProperties, new CustomProducer(getKafkaService().getBootstrapServers(), topicName, expect));
    }
}
