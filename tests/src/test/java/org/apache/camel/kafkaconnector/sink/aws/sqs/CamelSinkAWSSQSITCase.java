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
import org.apache.camel.kafkaconnector.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.ContainerUtil;
import org.apache.camel.kafkaconnector.TestCommon;
import org.apache.camel.kafkaconnector.clients.aws.sqs.AWSSQSClient;
import org.apache.camel.kafkaconnector.clients.kafka.KafkaClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class CamelSinkAWSSQSITCase extends AbstractKafkaTest {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkAWSSQSITCase.class);
    private static final int SQS_PORT = 4576;

    @Container
    public LocalStackContainer localStackContainer = new LocalStackContainer()
            .withServices(LocalStackContainer.Service.SQS);

    private AWSSQSClient awssqsClient;

    private volatile int received;
    private final int expect = 10;

    @BeforeEach
    public void setUp() {
        final String sqsInstance = localStackContainer
                .getEndpointConfiguration(LocalStackContainer.Service.SQS)
                .getServiceEndpoint();

        LOG.info("SQS instance running at {}", sqsInstance);

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
        } finally {
            latch.countDown();
        }
    }

    private void produceMessages()  {
        try {
            KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

            for (int i = 0; i < expect; i++) {
                kafkaClient.produce(TestCommon.getDefaultTestTopic(this.getClass()), "Sink test message " + i);
            }
        } catch (Throwable t) {
            LOG.error("Unable to publish messages to the broker: {}", t.getMessage(), t);
            fail(String.format("Unable to publish messages to the broker: %s", t.getMessage()));
        }
    }


    @Test
    @Timeout(value = 120)
    public void testBasicSendReceive() {
        try {
            Properties properties = ContainerUtil.setupAWSConfigs(localStackContainer, SQS_PORT);

            ConnectorPropertyFactory testProperties = new CamelAWSSQSPropertyFactory(1,
                    TestCommon.getDefaultTestTopic(this.getClass()), TestCommon.DEFAULT_SQS_QUEUE, properties);

            getKafkaConnectService().initializeConnectorBlocking(testProperties);

            LOG.debug("Creating the consumer ...");
            ExecutorService service = Executors.newCachedThreadPool();

            CountDownLatch latch = new CountDownLatch(1);
            service.submit(() -> consumeMessages(latch));

            LOG.debug("Creating the producer and sending messages ...");
            produceMessages();

            LOG.debug("Waiting for the test to complete");
            if (latch.await(110, TimeUnit.SECONDS)) {
                assertTrue(received == expect,
                        "Didn't process the expected amount of messages: " + received + " != " + expect);
            } else {
                fail(String.format("Failed to receive the messages within the specified time: received %d of %d",
                        received, expect));
            }
        } catch (Exception e) {
            LOG.error("Amazon SQS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }

}
