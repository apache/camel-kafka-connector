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

package org.apache.camel.kafkaconnector.timer.source;

import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.common.test.CamelSourceTestSupport;
import org.apache.camel.kafkaconnector.common.test.TestMessageConsumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A simple test case that checks whether the timer produces the expected number of
 * messages
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSourceTimerITCase extends CamelSourceTestSupport {
    private final int expect = 1;
    private String topicName;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-timer-source-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);
    }

    @Override
    protected void produceTestData() {
        // NO-OP
    }

    @Override
    protected void verifyMessages(TestMessageConsumer<?> consumer) {
        int received = consumer.consumedMessages().size();

        assertEquals(expect, received, "Did not receive as many messages as expected");
    }

    @Test
    @Timeout(30)
    public void testLaunchConnector() throws ExecutionException, InterruptedException {
        CamelTimerPropertyFactory connectorPropertyFactory = CamelTimerPropertyFactory
                .basic()
                .withKafkaTopic(topicName)
                .withPeriod(Integer.MAX_VALUE)
                .withMessage("hello world!");

        runTest(connectorPropertyFactory, topicName, expect);
    }
}
