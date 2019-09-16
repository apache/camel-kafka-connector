/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.camel.kafkaconnector.sink.aws.sqs;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import org.apache.camel.kafkaconnector.AWSConfigs;
import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.ContainerUtil;
import org.apache.camel.kafkaconnector.KafkaConnectRunner;
import org.apache.camel.kafkaconnector.TestCommon;
import org.apache.camel.kafkaconnector.clients.kafka.KafkaClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

@Ignore("This test requires camel > 3.0.0-M4")
public class CamelSinkAWSSQSITCase {
    private static final Logger log = LoggerFactory.getLogger(CamelSinkAWSSQSITCase.class);
    private static final int SQS_PORT = 4576;

    @Rule
    public KafkaContainer kafka = new KafkaContainer().withEmbeddedZookeeper();

    @Rule
    public LocalStackContainer localStackContainer = new LocalStackContainer()
            .withServices(LocalStackContainer.Service.SQS);

    private KafkaConnectRunner kafkaConnectRunner;
    private AmazonSQS sqs;

    private volatile int received = 0;
    private final int expect = 10;

    @Before
    public void setUp() {
        ContainerUtil.waitForInitialization(kafka);
        log.info("Kafka bootstrap server running at address {}", kafka.getBootstrapServers());

        log.info("Waiting for SQS initialization");
        ContainerUtil.waitForHttpInitialization(localStackContainer, localStackContainer.getMappedPort(SQS_PORT));
        log.info("SQS Initialized");

        final String sqsInstance = localStackContainer
                .getEndpointConfiguration(LocalStackContainer.Service.SQS)
                .getServiceEndpoint();

        log.info("SQS instance running at {}", sqsInstance);

        Properties properties = new Properties();

        properties.put(AWSConfigs.AMAZON_AWS_HOST, "localhost:" + localStackContainer.getMappedPort(SQS_PORT));

        AWSCredentials credentials = localStackContainer.getDefaultCredentialsProvider().getCredentials();

        properties.put(AWSConfigs.ACCESS_KEY, credentials.getAWSAccessKeyId());
        properties.put(AWSConfigs.SECRET_KEY, credentials.getAWSSecretKey());
        properties.put(AWSConfigs.REGION, Regions.US_EAST_1.name());

        ConnectorPropertyFactory testProperties = new CamelAWSSQSPropertyFactory(1,
            TestCommon.DEFAULT_TEST_TOPIC, TestCommon.DEFAULT_SQS_QUEUE, properties);

        kafkaConnectRunner =  new KafkaConnectRunner(kafka.getBootstrapServers());
        kafkaConnectRunner.getConnectorPropertyProducers().add(testProperties);

        sqs = AmazonSQSClientBuilder
                .standard()
                .withEndpointConfiguration(localStackContainer
                        .getEndpointConfiguration(LocalStackContainer.Service.SQS))
                .withCredentials(localStackContainer.getDefaultCredentialsProvider())
                .build();
    }



    private void consumeMessages(CountDownLatch latch) {
        try {
            Map<String, String> queueAttributes = new HashMap<>();

            CreateQueueRequest createFifoQueueRequest = new CreateQueueRequest(
                    TestCommon.DEFAULT_SQS_QUEUE).withAttributes(queueAttributes);

            String queueUrl = sqs.createQueue(createFifoQueueRequest)
                    .getQueueUrl();

            log.debug("Consuming messages from {}", queueUrl);

            ReceiveMessageRequest request = new ReceiveMessageRequest(queueUrl)
                    .withWaitTimeSeconds(10)
                    .withMaxNumberOfMessages(1);

            for (int i = 0; i < expect; i++) {
                ReceiveMessageResult result = sqs.receiveMessage(request);

                List<Message> messages = result.getMessages();
                for (Message message : messages) {
                    log.info("Received: {}", message.getBody());
                    received++;
                }

            }
        }
        catch (Throwable t) {
            log.error("Failed to consume messages: {}", t.getMessage(), t);
            fail(t.getMessage());
        }
        finally {
            latch.countDown();
        }
    }


    @Test
    public void testBasicSendReceive() {
        try {
            CountDownLatch latch = new CountDownLatch(1);

            ExecutorService service = Executors.newFixedThreadPool(2);
            service.submit(() -> kafkaConnectRunner.run());

            log.debug("Creating the consumer ...");
            service.submit(() -> consumeMessages(latch));

            KafkaClient<String,String> kafkaClient = new KafkaClient<>(kafka.getBootstrapServers());

            for (int i = 0; i < expect; i++) {
                kafkaClient.produce(TestCommon.DEFAULT_TEST_TOPIC, "Sink test message " + i);
            }

            log.debug("Created the consumer ... About to receive messages");

            if (latch.await(120, TimeUnit.SECONDS)) {
                Assert.assertTrue("Didn't process the expected amount of messages: " + received + " != " + expect,
                        received == expect);
            }
            else {
                fail("Failed to receive the messages within the specified time");
            }

            kafkaConnectRunner.stop();
        } catch (Exception e) {
            log.error("Amazon SQS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }

    }
}
