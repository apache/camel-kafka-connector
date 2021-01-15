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

package org.apache.camel.kafkaconnector.sjms2.sink;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.kafkaconnector.sjms2.common.SJMS2Common;
import org.apache.kafka.connect.runtime.rest.entities.ConnectorStateInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * A simple test to make sure we are not losing or hiding exception data on errors
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSinkJMSStartupITCase extends AbstractKafkaTest {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkJMSStartupITCase.class);

    private boolean running;
    private String trace;


    private Properties connectionProperties() {
        Properties properties = new Properties();

        properties.put("camel.component.sjms2.connection-factory", "#class:org.apache.qpid.jms.JmsConnectionFactory");
        properties.put("camel.component.sjms2.connection-factory.remoteURI", "tcp://invalid");

        return properties;
    }

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-sjms2-kafka-connector"};
    }

    private void connectorStateCheck(ConnectorStateInfo connectorStateInfo) {
        LOG.debug("Checking state for {}", connectorStateInfo.name());
        running = connectorStateInfo.tasks().stream().allMatch(t -> isRunning(t));

    }

    private boolean isRunning(ConnectorStateInfo.TaskState t) {
        boolean isRunningState =  t.state().equals("RUNNING");
        if (!isRunningState) {
            trace = t.trace();
        }

        return isRunningState;
    }

    private void runTest(ConnectorPropertyFactory connectorPropertyFactory) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnector(connectorPropertyFactory);

        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        kafkaClient.produce(TestUtils.getDefaultTestTopic(this.getClass()), "Sink test message ");
    }

    private void checkThatFailed() throws InterruptedException {
        int i = 25;
        do {
            kafkaConnectService.connectorStateCheck(this::connectorStateCheck);
            i--;

            if (i > 0 && running) {
                Thread.sleep(Duration.ofSeconds(1).toMillis());
            }
        } while (i > 0 && running);

        assertFalse(running, "The connector should be in a failed state");

        LOG.trace(trace);
        assertTrue(trace.contains("Failed to resolve endpoint"),
                "Trace should contain a Camel error message");
    }


    @Test
    @Timeout(30)
    public void testStartup() {
        try {
            Properties brokenProp = connectionProperties();

            ConnectorPropertyFactory connectorPropertyFactory = CamelJMSPropertyFactory
                    .basic()
                    .withTopics(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withConnectionProperties(brokenProp)
                    .withDestinationName(SJMS2Common.DEFAULT_JMS_QUEUE)
                    .withDeadLetterQueueTopicName("dlq-sink-topic");

            // Inject an invalid configuration and check that fails
            runTest(connectorPropertyFactory);

            checkThatFailed();
        } catch (Exception e) {
            LOG.error("JMS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }
}
