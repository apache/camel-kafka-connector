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

package org.apache.camel.kakfaconnector.source.timer;

import org.apache.camel.kakfaconnector.KafkaConnectRunner;
import org.apache.camel.kakfaconnector.TestCommon;
import org.apache.camel.kakfaconnector.TestMessageConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.KafkaContainer;

import static org.junit.Assert.fail;

/**
 * A simple test case that checks whether the timer produces the expected number of
 * messages
 */
public class CamelSourceTimerITCase {
    private static final Logger log = LoggerFactory.getLogger(CamelSourceTimerITCase.class);

    @Rule
    public KafkaContainer kafka = new KafkaContainer().withEmbeddedZookeeper();

    private int received = 0;
    private final int expect = 10;
    private KafkaConnectRunner kafkaConnectRunner;

    @Before
    public void setUp() {
        kafkaConnectRunner =  new KafkaConnectRunner(kafka.getBootstrapServers());

        CamelTimerPropertyProducer testProperties = new CamelTimerPropertyProducer(1,
                TestCommon.DEFAULT_TEST_TOPIC, expect);

        kafkaConnectRunner.getConnectorPropertyProducers().add(testProperties);

        log.info("Kafka bootstrap server running at address " + kafka.getBootstrapServers());
    }

    private boolean checkRecord(ConsumerRecord<String, String> record) {
        received++;

        if (received == expect) {
            return false;
        }

        return true;
    }

    @Test
    public void testLaunchConnector() {
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> kafkaConnectRunner.run());

        log.debug("Creating the consumer ...");
        TestMessageConsumer<String,String> testMessageConsumer = new TestMessageConsumer<>(kafka.getBootstrapServers());
        testMessageConsumer.consume(TestCommon.DEFAULT_TEST_TOPIC, this::checkRecord);
        log.debug("Created the consumer ...");

        kafkaConnectRunner.stop();
        Assert.assertTrue(received == expect);
    }
}
