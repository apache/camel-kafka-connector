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

import org.apache.camel.kafkaconnector.common.services.kafka.EmbeddedKafkaService;
import org.apache.camel.kafkaconnector.common.services.kafkaconnect.KafkaConnectRunnerFactory;
import org.apache.camel.kafkaconnector.common.services.kafkaconnect.KafkaConnectService;
import org.apache.camel.kafkaconnector.common.utils.PropertyUtils;
import org.apache.camel.test.infra.common.TestUtils;
import org.apache.camel.test.infra.kafka.services.KafkaService;
import org.apache.camel.test.infra.kafka.services.KafkaServiceFactory;
import org.apache.camel.test.infra.kafka.services.KafkaServiceFactory.ContainerLocalKafkaService;
import org.apache.camel.test.infra.kafka.services.KafkaServiceFactory.RemoteKafkaService;
import org.apache.camel.test.infra.kafka.services.KafkaServiceFactory.StrimziService;
import org.junit.jupiter.api.extension.RegisterExtension;

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

        kafkaService = KafkaServiceFactory
                .builder()
                .addLocalMapping(EmbeddedKafkaService::new)
                .addRemoteMapping(RemoteKafkaService::new)
                .addMapping("embedded", EmbeddedKafkaService::new)
                .addMapping("local-strimzi-container", StrimziService::new)
                .addMapping("local-cp-kafka-container", ContainerLocalKafkaService::kafka3Container)
                .build();

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

    /**
     * Gets a topic name for the test class
     * @param clazz
     * @return
     */
    protected String getDefaultTestTopic(Class<?> clazz) {
        return clazz.getName();
    }

    protected String getTopicForTest(Object testObject) {
        return getDefaultTestTopic(testObject.getClass()) + "." + TestUtils.randomWithRange(0, 1000);
    }
}
