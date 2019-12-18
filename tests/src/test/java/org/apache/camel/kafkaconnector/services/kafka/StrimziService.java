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

package org.apache.camel.kafkaconnector.services.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StrimziService implements KafkaService {
    private static final Logger LOG = LoggerFactory.getLogger(StrimziService.class);
    private StrimziContainer container = new StrimziContainer();

    private Integer getKafkaPort() {
        return container.getServicePort("kafka", 9092);
    }

    @Override
    public String getBootstrapServers() {
        return "localhost:" + getKafkaPort();
    }

    @Override
    public void initialize() {
        container.start();

        LOG.info("Kafka bootstrap server running at address {}", getBootstrapServers());
    }
}
