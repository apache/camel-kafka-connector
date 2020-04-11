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

package org.apache.camel.kafkaconnector;

import org.apache.camel.kafkaconnector.services.kafka.KafkaService;
import org.apache.camel.kafkaconnector.services.kafka.KafkaServiceFactory;
import org.apache.camel.kafkaconnector.services.kafkaconnect.KafkaConnectRunnerFactory;
import org.apache.camel.kafkaconnector.services.kafkaconnect.KafkaConnectService;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class AbstractKafkaTest {

    @RegisterExtension
    public static final KafkaService KAFKA_SERVICE;

    @RegisterExtension
    public static final KafkaConnectService KAFKA_CONNECT_RUNNER_SERVICE;

    static {
        PropertyUtils.load();

        KAFKA_SERVICE = KafkaServiceFactory.createService();

        KAFKA_SERVICE.initialize();

        KAFKA_CONNECT_RUNNER_SERVICE = KafkaConnectRunnerFactory.createService(KAFKA_SERVICE);
    }

    public AbstractKafkaTest() {

    }


    public KafkaService getKafkaService() {
        return KAFKA_SERVICE;
    }


    public KafkaConnectService getKafkaConnectService() {
        return KAFKA_CONNECT_RUNNER_SERVICE;
    }
}
