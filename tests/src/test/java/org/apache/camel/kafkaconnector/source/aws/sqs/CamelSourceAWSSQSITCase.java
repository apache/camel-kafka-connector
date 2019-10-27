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

package org.apache.camel.kafkaconnector.source.aws.sqs;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Regions;
import org.apache.camel.kafkaconnector.AWSConfigs;
import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.ContainerUtil;
import org.apache.camel.kafkaconnector.KafkaConnectRunner;
import org.apache.camel.kafkaconnector.TestCommon;
import org.apache.camel.kafkaconnector.clients.aws.sqs.AWSSQSClient;
import org.apache.camel.kafkaconnector.clients.kafka.KafkaClient;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;

public class CamelSourceAWSSQSITCase {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceAWSSQSITCase.class);
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

    private boolean checkRecord(ConsumerRecord<String, String> record) {
        LOG.debug("Received: {}", record.value());
        received++;

        if (received == expect) {
            return false;
        }

        return true;
    }

    @Test
    public void testBasicSendReceive() {
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> kafkaConnectRunner.run());

        LOG.debug("Sending SQS messages");
        for (int i = 0; i < expect; i++) {
            awssqsClient.send(TestCommon.DEFAULT_SQS_QUEUE, "Test message " + i);
        }
        LOG.debug("Done sending SQS messages");

        LOG.debug("Creating the consumer ...");
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(kafka.getBootstrapServers());
        kafkaClient.consume(TestCommon.DEFAULT_TEST_TOPIC, this::checkRecord);
        LOG.debug("Created the consumer ...");

        kafkaConnectRunner.stop();
        Assert.assertTrue("Didn't process the expected amount of messages", received == expect);
    }
}
