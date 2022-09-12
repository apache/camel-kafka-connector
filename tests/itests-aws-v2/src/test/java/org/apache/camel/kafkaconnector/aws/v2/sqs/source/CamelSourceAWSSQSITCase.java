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

package org.apache.camel.kafkaconnector.aws.v2.sqs.source;

import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.aws.v2.clients.AWSSQSClient;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSourceTestSupport;
import org.apache.camel.kafkaconnector.common.test.TestMessageConsumer;
import org.apache.camel.test.infra.aws.common.AWSCommon;
import org.apache.camel.test.infra.aws.common.services.AWSService;
import org.apache.camel.test.infra.aws2.clients.AWSSDKClientUtils;
import org.apache.camel.test.infra.aws2.services.AWSServiceFactory;
import org.apache.camel.test.infra.common.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnabledIfSystemProperty(named = "enable.slow.tests", matches = "true")
public class CamelSourceAWSSQSITCase extends CamelSourceTestSupport {
    @RegisterExtension
    public static AWSService service = AWSServiceFactory.createSQSService();
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceAWSSQSITCase.class);

    private AWSSQSClient awssqsClient;
    private String queueName;
    private String topicName;

    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-aws-sqs-source-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);

        awssqsClient = new AWSSQSClient(AWSSDKClientUtils.newSQSClient());
        queueName = AWSCommon.BASE_SQS_QUEUE_NAME + "-" + TestUtils.randomWithRange(0, 1000);

        // TODO: this is a work-around for CAMEL-15833
        awssqsClient.createQueue(queueName);
    }

    @AfterEach
    public void tearDown() {
        if (!awssqsClient.deleteQueue(queueName)) {
            fail("Failed to delete queue");
        }
    }

    @Override
    protected void produceTestData() {
        LOG.debug("Sending SQS messages");
        for (int i = 0; i < expect; i++) {
            awssqsClient.send(queueName, "Source test message " + i);
        }
        LOG.debug("Done sending SQS messages");
    }

    @Override
    protected void verifyMessages(TestMessageConsumer<?> consumer) {
        int received = consumer.consumedMessages().size();
        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceive() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory connectorPropertyFactory = CamelAWSSQSPropertyFactory
                .basic()
                .withKafkaTopic(topicName)
                .withQueueOrArn(queueName)
                .withAmazonConfig(service.getConnectionProperties());

        runTest(connectorPropertyFactory, topicName, expect);
    }
}
