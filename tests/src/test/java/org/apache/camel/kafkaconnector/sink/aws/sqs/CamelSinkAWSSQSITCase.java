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

package org.apache.camel.kafkaconnector.sink.aws.sqs;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import com.amazonaws.services.sqs.model.Message;
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
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;

import static org.junit.Assert.fail;

public class CamelSinkAWSSQSITCase {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkAWSSQSITCase.class);
    private static final int SQS_PORT = 4576;

    @Rule
    public KafkaContainer kafka = new KafkaContainer().withEmbeddedZookeeper();

    @Rule
    public LocalStackContainer localStackContainer = new LocalStackContainer()
            .withServices(LocalStackContainer.Service.SQS);

    private KafkaConnectRunner kafkaConnectRunner;
    private AWSSQSClient awssqsClient;

    private volatile int received;
    private final int expect = 10;

    @Before
    public void setUp() {
        ContainerUtil.waitForInitialization(kafka);
        LOG.info("Kafka bootstrap server running at address {}", kafka.getBootstrapServers());

        LOG.info("Waiting for SQS initialization");
        ContainerUtil.waitForHttpInitialization(localStackContainer, localStackContainer.getMappedPort(SQS_PORT));
        LOG.info("SQS Initialized");

        final String sqsInstance = localStackContainer
                .getEndpointConfiguration(LocalStackContainer.Service.SQS)
                .getServiceEndpoint();

        LOG.info("SQS instance running at {}", sqsInstance);

        Properties properties = ContainerUtil.setupAWSConfigs(localStackContainer, SQS_PORT);

        ConnectorPropertyFactory testProperties = new CamelAWSSQSPropertyFactory(1,
            TestCommon.DEFAULT_TEST_TOPIC, TestCommon.DEFAULT_SQS_QUEUE, properties);

        kafkaConnectRunner =  new KafkaConnectRunner(kafka.getBootstrapServers());
        kafkaConnectRunner.getConnectorPropertyProducers().add(testProperties);

        awssqsClient = new AWSSQSClient(localStackContainer);
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
            awssqsClient.receive(TestCommon.DEFAULT_SQS_QUEUE, this::checkMessages);
        } catch (Throwable t) {
            LOG.error("Failed to consume messages: {}", t.getMessage(), t);
            fail(t.getMessage());
        } finally {
            latch.countDown();
        }
    }

    private void produceMessages()  {
        try {
            KafkaClient<String, String> kafkaClient = new KafkaClient<>(kafka.getBootstrapServers());

            for (int i = 0; i < expect; i++) {
                kafkaClient.produce(TestCommon.DEFAULT_TEST_TOPIC, "Sink test message " + i);
            }
        } catch (Throwable t) {
            LOG.error("Unable to publish messages to the broker: {}", t.getMessage(), t);
            fail(String.format("Unable to publish messages to the broker: {}", t.getMessage()));
        }
    }


    @Test(timeout = 90000)
    public void testBasicSendReceive() {
        try {
            CountDownLatch latch = new CountDownLatch(2);

            ExecutorService service = Executors.newCachedThreadPool();
            service.submit(() -> kafkaConnectRunner.run(latch));

            LOG.debug("Creating the consumer ...");
            service.submit(() -> consumeMessages(latch));

            LOG.debug("Creating the producer and sending messages ...");
            produceMessages();

            LOG.debug("Waiting for the test to complete");
            if (latch.await(80, TimeUnit.SECONDS)) {
                Assert.assertTrue("Didn't process the expected amount of messages: " + received + " != " + expect,
                        received == expect);
            } else {
                fail("Failed to receive the messages within the specified time");
            }
        } catch (Exception e) {
            LOG.error("Amazon SQS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        } finally {
            kafkaConnectRunner.stop();
        }
    }


}
