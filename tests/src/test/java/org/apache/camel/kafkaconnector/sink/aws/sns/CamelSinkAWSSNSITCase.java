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

package org.apache.camel.kafkaconnector.sink.aws.sns;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.amazonaws.services.sqs.model.Message;
import org.apache.camel.kafkaconnector.AWSConfigs;
import org.apache.camel.kafkaconnector.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.ContainerUtil;
import org.apache.camel.kafkaconnector.KafkaConnectRunner;
import org.apache.camel.kafkaconnector.TestCommon;
import org.apache.camel.kafkaconnector.clients.aws.sqs.AWSSQSClient;
import org.apache.camel.kafkaconnector.clients.kafka.KafkaClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.localstack.LocalStackContainer;

import static org.junit.Assert.fail;

public class CamelSinkAWSSNSITCase extends AbstractKafkaTest  {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkAWSSNSITCase.class);
    private static final int SQS_PORT = 4576;
    private static final int SNS_PORT = 4575;

    @Rule
    public LocalStackContainer localStackContainer = new LocalStackContainer()
            .withServices(LocalStackContainer.Service.SQS, LocalStackContainer.Service.SNS);

    private KafkaConnectRunner kafkaConnectRunner;
    private AWSSQSClient awsSqsClient;

    private volatile int received;
    private final int expect = 10;

    @Before
    public void setUp() {
        LOG.info("Waiting for SQS+SNS initialization");
        ContainerUtil.waitForHttpInitialization(localStackContainer, localStackContainer.getMappedPort(SQS_PORT));
        LOG.info("SQS+SNS Initialized");

        final String sqsInstance = localStackContainer
                .getEndpointConfiguration(LocalStackContainer.Service.SQS)
                .getServiceEndpoint();

        LOG.info("SQS instance running at {}", sqsInstance);

        awsSqsClient = new AWSSQSClient(localStackContainer);
        final String sqsQueue = awsSqsClient.getQueue(TestCommon.DEFAULT_SQS_QUEUE);
        LOG.info("Created SQS queue {}", sqsQueue);

        final String snsInstance = localStackContainer
                .getEndpointConfiguration(LocalStackContainer.Service.SNS)
                .getServiceEndpoint();

        LOG.info("SNS instance running at {}", snsInstance);

        Properties properties = ContainerUtil.setupAWSConfigs(localStackContainer, SNS_PORT);
        properties.put(AWSConfigs.AMAZON_AWS_SNS_2_SQS_QUEUE_URL, sqsQueue);

        ConnectorPropertyFactory testProperties = new CamelAWSSNSPropertyFactory(1,
            TestCommon.getDefaultTestTopic(this.getClass()), TestCommon.DEFAULT_SNS_QUEUE, properties);

        kafkaConnectRunner = getKafkaConnectRunner();
        kafkaConnectRunner.getConnectorPropertyProducers().add(testProperties);
    }

    private boolean checkMessages(List<Message> messages) {
        for (Message message : messages) {
            LOG.info("Received: {}", message.getBody());

            received++;
        }

        if (received == expect) {
            return false;
        }

        return true;
    }


    private void consumeMessages(CountDownLatch latch) {
        try {
            awsSqsClient.receive(TestCommon.DEFAULT_SQS_QUEUE, this::checkMessages);
        } catch (Throwable t) {
            LOG.error("Failed to consume messages: {}", t.getMessage(), t);
            fail(t.getMessage());
        } finally {
            latch.countDown();
        }
    }


    @Test(timeout = 90000)
    public void testBasicSendReceive() {
        try {
            CountDownLatch latch = new CountDownLatch(1);

            ExecutorService service = Executors.newFixedThreadPool(2);
            service.submit(() -> kafkaConnectRunner.run());

            LOG.debug("Creating the consumer ...");
            service.submit(() -> consumeMessages(latch));

            KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

            for (int i = 0; i < expect; i++) {
                kafkaClient.produce(TestCommon.getDefaultTestTopic(this.getClass()), "Sink test message " + i);
            }

            LOG.debug("Created the consumer ... About to receive messages");

            if (latch.await(120, TimeUnit.SECONDS)) {
                Assert.assertTrue("Didn't process the expected amount of messages: " + received + " != " + expect,
                        received == expect);
            } else {
                fail("Failed to receive the messages within the specified time");
            }

            kafkaConnectRunner.stop();
        } catch (Exception e) {
            LOG.error("Amazon SQS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }

    }
}
