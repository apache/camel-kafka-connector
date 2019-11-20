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

package org.apache.camel.kafkaconnector.source.aws.s3;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.ContainerUtil;
import org.apache.camel.kafkaconnector.KafkaConnectRunner;
import org.apache.camel.kafkaconnector.TestCommon;
import org.apache.camel.kafkaconnector.clients.kafka.KafkaClient;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;

@Ignore("Requires Camel > 3.0.0-RC3")
public class CamelSourceAWSS3ITCase {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceAWSS3ITCase.class);
    private static final int S3_PORT = 4572;

    @Rule
    public KafkaContainer kafka = new KafkaContainer().withEmbeddedZookeeper();

    @Rule
    public LocalStackContainer localStackContainer = new LocalStackContainer()
            .withServices(LocalStackContainer.Service.S3);

    private KafkaConnectRunner kafkaConnectRunner;
    private AmazonS3 awsS3Client;

    private volatile int received;
    private final int expect = 10;

    @Before
    public void setUp() {
        ContainerUtil.waitForInitialization(kafka);
        LOG.info("Kafka bootstrap server running at address {}", kafka.getBootstrapServers());

        LOG.info("Waiting for S3 initialization");
        ContainerUtil.waitForHttpInitialization(localStackContainer, localStackContainer.getMappedPort(S3_PORT));
        LOG.info("S3 Initialized");

        final String s3Instance = localStackContainer
                .getEndpointConfiguration(LocalStackContainer.Service.S3)
                .getServiceEndpoint();

        LOG.info("S3 instance running at {}", s3Instance);

        Properties properties = ContainerUtil.setupAWSConfigs(localStackContainer, S3_PORT);

        ConnectorPropertyFactory testProperties = new CamelAWSS3PropertyFactory(1,
                TestCommon.DEFAULT_TEST_TOPIC, TestCommon.DEFAULT_S3_BUCKET, properties);

        kafkaConnectRunner =  new KafkaConnectRunner(kafka.getBootstrapServers());
        kafkaConnectRunner.getConnectorPropertyProducers().add(testProperties);

        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setProtocol(Protocol.HTTP);

        awsS3Client = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(localStackContainer.getEndpointConfiguration(LocalStackContainer.Service.S3))
                .withCredentials(localStackContainer.getDefaultCredentialsProvider())
                .withClientConfiguration(clientConfiguration)

                .build();

    }

    private boolean checkRecord(ConsumerRecord<String, String> record) {
        LOG.debug("Received: {}", record.value());
        received++;

        if (received == expect) {
            return false;
        }

        return true;
    }

    @Test(timeout = 180000)
    public void testBasicSendReceive() {
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> kafkaConnectRunner.run());

        awsS3Client.createBucket(TestCommon.DEFAULT_S3_BUCKET);

        LOG.debug("Putting S3 objects");
        for (int i = 0; i < expect; i++) {
            String name = "file" + i + ".test";
            String file = this.getClass().getResource(name).getFile();

            LOG.trace("Putting file " + file);
            awsS3Client.putObject(TestCommon.DEFAULT_S3_BUCKET, name, new File(file));
        }
        LOG.debug("Done putting S3S objects");

        LOG.debug("Creating the consumer ...");
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(kafka.getBootstrapServers());
        kafkaClient.consume(TestCommon.DEFAULT_TEST_TOPIC, this::checkRecord);
        LOG.debug("Created the consumer ...");

        kafkaConnectRunner.stop();
        Assert.assertTrue("Didn't process the expected amount of messages", received == expect);
    }
}
