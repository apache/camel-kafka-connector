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

package org.apache.camel.kafkaconnector.aws.v2.ec2.sink;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.CamelSinkTask;
import org.apache.camel.kafkaconnector.aws.v2.cw.sink.TestCloudWatchConfiguration;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.test.infra.aws.common.services.AWSService;
import org.apache.camel.test.infra.aws2.clients.AWSSDKClientUtils;
import org.apache.camel.test.infra.aws2.services.AWSServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstanceStatusRequest;
import software.amazon.awssdk.services.ec2.model.InstanceStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CamelSinkAWSEC2ITCase extends CamelSinkTestSupport {
    @RegisterExtension
    public static AWSService awsService = AWSServiceFactory.createEC2Service();
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkAWSEC2ITCase.class);

    private Ec2Client client;
    private String logicalName;

    private volatile int received;
    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-aws2-ec2-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        client = AWSSDKClientUtils.newEC2Client();
        logicalName = "ec2-" + TestUtils.randomWithRange(1, 100);

        received = 0;
    }

    @Override
    protected Map<String, String> messageHeaders(String text, int current) {
        Map<String, String> headers = new HashMap<>();

        headers.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "CamelAwsEC2ImageId",
                "image-id-" + current);
        headers.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "CamelAwsEC2InstanceType", "T1_MICRO");
        headers.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "CamelAwsEC2InstanceMinCount", "1");
        headers.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "CamelAwsEC2InstanceMaxCount", "1");
        headers.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "CamelAwsEC2InstanceSecurityGroups", "default");

        return headers;
    }

    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try {
            while (true) {
                DescribeInstanceStatusRequest request = DescribeInstanceStatusRequest.builder()
                        .includeAllInstances(true)
                        .build();
                List<InstanceStatus> statusList = client.describeInstanceStatus(request).instanceStatuses();

                for (InstanceStatus status : statusList) {
                    LOG.info("Instance {} has status: {}", status.instanceId(), status);
                    received++;


                    if (received >= expect) {
                        return;
                    }
                }

                if (!waitForData()) {
                    break;
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

    @Test
    @Timeout(90)
    public void testBasicSendReceive() {
        try {
            Properties amazonProperties = awsService.getConnectionProperties();
            String topicName = TestUtils.getDefaultTestTopic(this.getClass());

            ConnectorPropertyFactory testProperties = CamelAWSEC2PropertyFactory
                    .basic()
                    .withTopics(topicName)
                    .withConfiguration(TestCloudWatchConfiguration.class.getName())
                    .withAmazonConfig(amazonProperties)
                    .withSinkPathLabel(logicalName)
                    .withConfiguration(TestEC2Configuration.class.getName())
                    .withSinkEndpointOperation("createAndRunInstances");

            runTest(testProperties, topicName, expect);
        } catch (Exception e) {
            LOG.error("Amazon EC2 test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }


}
