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

package org.apache.camel.kafkaconnector.common;

import org.apache.camel.kafkaconnector.common.services.kafka.KafkaService;
import org.apache.camel.kafkaconnector.common.services.kafka.KafkaServiceFactory;
import org.apache.camel.kafkaconnector.common.services.kafkaconnect.KafkaConnectRunnerFactory;
import org.apache.camel.kafkaconnector.common.services.kafkaconnect.KafkaConnectService;
import org.apache.camel.kafkaconnector.common.utils.PropertyUtils;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class AbstractKafkaTest {

    @RegisterExtension
    public final KafkaService kafkaService;

    @RegisterExtension
    public final KafkaConnectService kafkaConnectService;

    static {
        PropertyUtils.load();
    }

    public AbstractKafkaTest() {
        PluginPathHelper.getInstance().registerConnector(getConnectorsInTest());

        kafkaService = KafkaServiceFactory.createService();

        kafkaService.initialize();

        kafkaConnectService = KafkaConnectRunnerFactory.createService(kafkaService);
    }

    protected abstract String[] getConnectorsInTest();

    public KafkaService getKafkaService() {
        return kafkaService;
    }


    public KafkaConnectService getKafkaConnectService() {
        return kafkaConnectService;
    }
}
