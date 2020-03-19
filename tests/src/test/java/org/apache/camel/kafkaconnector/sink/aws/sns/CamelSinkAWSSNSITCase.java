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
import org.apache.camel.kafkaconnector.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.TestCommon;
import org.apache.camel.kafkaconnector.clients.aws.AWSConfigs;
import org.apache.camel.kafkaconnector.clients.aws.sqs.AWSSQSClient;
import org.apache.camel.kafkaconnector.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.services.aws.AWSService;
import org.apache.camel.kafkaconnector.services.aws.AWSServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@Testcontainers
public class CamelSinkAWSSNSITCase extends AbstractKafkaTest  {
    @RegisterExtension
    public static AWSService<AWSSQSClient> service = AWSServiceFactory.createSNSService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkAWSSNSITCase.class);

    private AWSSQSClient awsSqsClient;

    private volatile int received;
    private final int expect = 10;

    @BeforeEach
    public void setUp() {
        awsSqsClient = service.getClient();
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
            awsSqsClient.receive(TestCommon.DEFAULT_SQS_QUEUE_FOR_SNS, this::checkMessages);
        } catch (Throwable t) {
            LOG.error("Failed to consume messages: {}", t.getMessage(), t);
            fail(t.getMessage());
        } finally {
            latch.countDown();
        }
    }


    @Test
    @Timeout(value = 90)
    public void testBasicSendReceive() {
        try {
            final String sqsQueue = awsSqsClient.getQueue(TestCommon.DEFAULT_SQS_QUEUE_FOR_SNS);
            LOG.info("Created SQS queue {}", sqsQueue);

            Properties properties = service.getConnectionProperties();
            properties.put(AWSConfigs.AMAZON_AWS_SNS_2_SQS_QUEUE_URL, sqsQueue);

            ConnectorPropertyFactory testProperties = new CamelAWSSNSPropertyFactory(1,
                    TestCommon.getDefaultTestTopic(this.getClass()), TestCommon.DEFAULT_SQS_QUEUE_FOR_SNS, properties);

            getKafkaConnectService().initializeConnector(testProperties);

            ExecutorService service = Executors.newCachedThreadPool();

            LOG.debug("Creating the consumer ...");
            CountDownLatch latch = new CountDownLatch(1);
            service.submit(() -> consumeMessages(latch));

            KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

            for (int i = 0; i < expect; i++) {
                kafkaClient.produce(TestCommon.getDefaultTestTopic(this.getClass()), "Sink test message " + i);
            }

            LOG.debug("Created the consumer ... About to receive messages");

            if (latch.await(120, TimeUnit.SECONDS)) {
                assertTrue(received == expect,
                        "Didn't process the expected amount of messages: " + received + " != " + expect);
            } else {
                fail("Failed to receive the messages within the specified time");
            }
        } catch (Exception e) {
            LOG.error("Amazon SNS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }

    }
}
