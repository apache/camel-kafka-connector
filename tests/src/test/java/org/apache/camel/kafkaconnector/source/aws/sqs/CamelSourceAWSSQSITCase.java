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
import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.TestCommon;
import org.apache.camel.kafkaconnector.clients.aws.AWSConfigs;
import org.apache.camel.kafkaconnector.clients.aws.sqs.AWSSQSClient;
import org.apache.camel.kafkaconnector.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.services.aws.AWSService;
import org.apache.camel.kafkaconnector.services.aws.AWSServiceFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class CamelSourceAWSSQSITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static AWSService<AWSSQSClient> service = AWSServiceFactory.createSQSService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceAWSSQSITCase.class);

    private AWSSQSClient awssqsClient;

    private volatile int received;
    private final int expect = 10;

    @BeforeEach
    public void setUp() {
        awssqsClient = service.getClient();
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
    @Timeout(90)
    public void testBasicSendReceive() throws ExecutionException, InterruptedException {
        Properties properties = service.getConnectionProperties();

        ConnectorPropertyFactory testProperties = new CamelAWSSQSPropertyFactory(1,
                TestCommon.getDefaultTestTopic(this.getClass()), TestCommon.DEFAULT_SQS_QUEUE,
                properties);

        getKafkaConnectService().initializeConnector(testProperties);

        LOG.debug("Sending SQS messages");
        for (int i = 0; i < expect; i++) {
            awssqsClient.send(TestCommon.DEFAULT_SQS_QUEUE, "Source test message " + i);
        }
        LOG.debug("Done sending SQS messages");

        LOG.debug("Creating the consumer ...");
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        kafkaClient.consume(TestCommon.getDefaultTestTopic(this.getClass()), this::checkRecord);
        LOG.debug("Created the consumer ...");

        assertEquals(received, expect, "Didn't process the expected amount of messages");
    }
}
