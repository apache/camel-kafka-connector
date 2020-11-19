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

package org.apache.camel.kafkaconnector.aws.v1.s3.source;

import java.io.File;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListVersionsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.S3VersionSummary;
import com.amazonaws.services.s3.model.VersionListing;
import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.test.infra.aws.clients.AWSClientUtils;
import org.apache.camel.test.infra.aws.common.AWSCommon;
import org.apache.camel.test.infra.aws.common.AWSConfigs;
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
public class CamelSourceAWSS3ITCase extends AbstractKafkaTest {

    @RegisterExtension
    public static AWSService service = AWSServiceFactory.createS3Service();
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceAWSS3ITCase.class);

    private AmazonS3 awsS3Client;

    private volatile int received;
    private final int expect = 10;

    /**
     * Delete an S3 bucket using the provided client. Coming from AWS documentation:
     * https://docs.aws.amazon.com/AmazonS3/latest/dev/delete-or-empty-bucket.html#delete-bucket-sdk-java
     *
     * @param s3Client
     *            the AmazonS3 client instance used to delete the bucket
     * @param bucketName
     *            a String containing the bucket name
     */
    public static void deleteBucket(AmazonS3 s3Client, String bucketName) {
        // Delete all objects from the bucket. This is sufficient
        // for non versioned buckets. For versioned buckets, when you attempt to delete objects, Amazon S3 inserts
        // delete markers for all objects, but doesn't delete the object versions.
        // To delete objects from versioned buckets, delete all of the object versions before deleting
        // the bucket (see below for an example).
        ObjectListing objectListing = s3Client.listObjects(bucketName);
        while (true) {
            Iterator<S3ObjectSummary> objIter = objectListing.getObjectSummaries().iterator();
            while (objIter.hasNext()) {
                s3Client.deleteObject(bucketName, objIter.next().getKey());
            }

            // If the bucket contains many objects, the listObjects() call
            // might not return all of the objects in the first listing. Check to
            // see whether the listing was truncated. If so, retrieve the next page of objects
            // and delete them.
            if (objectListing.isTruncated()) {
                objectListing = s3Client.listNextBatchOfObjects(objectListing);
            } else {
                break;
            }
        }

        // Delete all object versions (required for versioned buckets).
        VersionListing versionList = s3Client.listVersions(new ListVersionsRequest().withBucketName(bucketName));
        while (true) {
            Iterator<S3VersionSummary> versionIter = versionList.getVersionSummaries().iterator();
            while (versionIter.hasNext()) {
                S3VersionSummary vs = versionIter.next();
                s3Client.deleteVersion(bucketName, vs.getKey(), vs.getVersionId());
            }

            if (versionList.isTruncated()) {
                versionList = s3Client.listNextBatchOfVersions(versionList);
            } else {
                break;
            }
        }

        // After all objects and object versions are deleted, delete the bucket.
        s3Client.deleteBucket(bucketName);
    }

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-aws-s3-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        awsS3Client = AWSClientUtils.newS3Client();
        received = 0;

        try {
            awsS3Client.createBucket(AWSCommon.DEFAULT_S3_BUCKET);
        } catch (Exception e) {
            LOG.error("Unable to create bucket: {}", e.getMessage(), e);
            fail("Unable to create bucket");
        }
    }

    @AfterEach
    public void tearDown() {
        try {
            deleteBucket(awsS3Client, AWSCommon.DEFAULT_S3_BUCKET);
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

    public void runTest(ConnectorPropertyFactory connectorPropertyFactory)
            throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnector(connectorPropertyFactory);

        LOG.debug("Putting S3 objects");
        for (int i = 0; i < expect; i++) {
            String name = "file" + i + ".test";
            String file = this.getClass().getResource(name).getFile();

            LOG.trace("Putting file {}", file);
            awsS3Client.putObject(AWSCommon.DEFAULT_S3_BUCKET, name, new File(file));
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
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSS3PropertyFactory.basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withConfiguration(TestS3Configuration.class.getName()).withBucketNameOrArn(AWSCommon.DEFAULT_S3_BUCKET)
                .withAmazonConfig(service.getConnectionProperties());

        runTest(connectorPropertyFactory);
    }

    @Test
    @Timeout(180)
    public void testBasicSendReceiveWithMaxMessagesPerPoll() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSS3PropertyFactory.basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withConfiguration(TestS3Configuration.class.getName()).withMaxMessagesPerPoll(5)
                .withBucketNameOrArn(AWSCommon.DEFAULT_S3_BUCKET).withAmazonConfig(service.getConnectionProperties());

        runTest(connectorPropertyFactory);
    }

    @Test
    @Timeout(180)
    public void testBasicSendReceiveWithKafkaStyle() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSS3PropertyFactory.basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withConfiguration(TestS3Configuration.class.getName()).withBucketNameOrArn(AWSCommon.DEFAULT_S3_BUCKET)
                .withAmazonConfig(service.getConnectionProperties(), CamelAWSS3PropertyFactory.KAFKA_STYLE);

        runTest(connectorPropertyFactory);
    }

    @Test
    @Timeout(180)
    public void testBasicSendReceiveUsingUrl() throws ExecutionException, InterruptedException {
        Properties amazonProperties = service.getConnectionProperties();

        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSS3PropertyFactory.basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withConfiguration(TestS3Configuration.class.getName()).withUrl(AWSCommon.DEFAULT_S3_BUCKET)
                .append("accessKey", amazonProperties.getProperty(AWSConfigs.ACCESS_KEY))
                .append("secretKey", amazonProperties.getProperty(AWSConfigs.SECRET_KEY))
                .appendIfAvailable("proxyProtocol", amazonProperties.getProperty(AWSConfigs.PROTOCOL))
                .append("region", amazonProperties.getProperty(AWSConfigs.REGION, Regions.US_EAST_1.name())).buildUrl();

        runTest(connectorPropertyFactory);
    }

}
