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

package org.apache.camel.kafkaconnector.aws.v2.kms.sink;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.CamelSinkTask;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.common.test.StringMessageProducer;
import org.apache.camel.test.infra.aws.common.services.AWSService;
import org.apache.camel.test.infra.aws2.clients.AWSSDKClientUtils;
import org.apache.camel.test.infra.aws2.services.AWSServiceFactory;
import org.apache.camel.test.infra.common.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.DescribeKeyRequest;
import software.amazon.awssdk.services.kms.model.DescribeKeyResponse;
import software.amazon.awssdk.services.kms.model.KeyListEntry;
import software.amazon.awssdk.services.kms.model.ListKeysResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnabledIfSystemProperty(named = "enable.slow.tests", matches = "true")
public class CamelSinkAWSKMSITCase extends CamelSinkTestSupport {

    @RegisterExtension
    public static AWSService awsService = AWSServiceFactory.createKMSService();
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkAWSKMSITCase.class);

    private String logicalName;
    private KmsClient client;

    private volatile int received;
    private final int expect = 10;

    private static class CustomProducer extends StringMessageProducer {
        public CustomProducer(String bootstrapServer, String topicName, int count) {
            super(bootstrapServer, topicName, count);
        }

        @Override
        public Map<String, String> messageHeaders(String text, int current) {
            Map<String, String> headers = new HashMap<>();

            headers.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "CamelAwsKMSKeyId",
                    String.valueOf(current));

            headers.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "CamelAwsKMSDescription",
                    "test key " + current);


            return headers;
        }
    }

    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try {
            while (true) {
                ListKeysResponse response = client.listKeys();
                List<KeyListEntry> keys = response.keys();

                received = keys.size();
                for (KeyListEntry entry : keys) {
                    DescribeKeyRequest describeKeyRequest = DescribeKeyRequest.builder().keyId(entry.keyId()).build();

                    DescribeKeyResponse describeKeyResponse = client.describeKey(describeKeyRequest);

                    LOG.info("Received key: {} / {}: {}", entry.keyId(), entry.keyArn(),
                            describeKeyResponse.keyMetadata().description());
                }

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
            // There is 1 default key from localstack, so the check here is different.
            assertTrue(received >= expect, "Should have processed at least : " + expect
                    + " keys, but processed only " + received);
        } else {
            fail(String.format("Failed to receive the messages within the specified time: received %d of %d",
                    received, expect));
        }
    }

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-aws2-kms-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        logicalName = "kms-" + TestUtils.randomWithRange(0, 100);

        client = AWSSDKClientUtils.newKMSClient();
        received = 0;
    }


    @Test
    @Timeout(120)
    public void testBasicSendReceive() throws Exception {
        Properties amazonProperties = awsService.getConnectionProperties();
        String topicName = getTopicForTest(this);

        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSKMSPropertyFactory
                .basic()
                .withTopics(topicName)
                .withAmazonConfig(amazonProperties)
                .withConfiguration(TestKMS2Configuration.class.getName())
                .withSinkEndpointOperation("createKey")
                .withSinkPathLabel(logicalName);

        runTest(connectorPropertyFactory, new CustomProducer(getKafkaService().getBootstrapServers(), topicName, expect));
    }
}
