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

package org.apache.camel.kafkaconnector.rabbitmq.source;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.test.infra.rabbitmq.services.RabbitMQService;
import org.apache.camel.test.infra.rabbitmq.services.RabbitMQServiceFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;

@EnabledIfSystemProperty(named = "it.test.perf.enabled", matches = "true")
public class RabbitMQSourcePerformanceITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static RabbitMQService service = RabbitMQServiceFactory.createService();

    private static int duration;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-rabbitmq-kafka-connector"};
    }

    @BeforeAll
    public static void setUpAll() {
        duration = Integer.parseInt(System.getProperty("rabbitmq.test.duration", "5"));
    }

    @Test
    public void testMemory() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory factory = CamelRabbitMQPropertyFactory
                .basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withUrl(service.connectionProperties().hostname(), service.connectionProperties().port(),
                        "X.test")
                .append("username", service.connectionProperties().username())
                .append("password", service.connectionProperties().password())
                .append("autoDelete", "true")
                .append("queue", "Q.test.kafka.import")
                .append("routingKey", "events")
                .buildUrl();

        factory.log();
        getKafkaConnectService().initializeConnector(factory);

        Thread.sleep(Duration.ofMinutes(duration).toMillis());
    }

}
