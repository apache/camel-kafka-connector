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

package org.apache.camel.kafkaconnector.rabbitmq.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.RabbitMQContainer;

public class RabbitMQLocalContainerService implements RabbitMQService {
    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQLocalContainerService.class);

    private final RabbitMQContainer container = new RabbitMQContainer("rabbitmq:3.8-management");

    public RabbitMQLocalContainerService() {
        container.start();
    }

    @Override
    public ConnectionProperties connectionProperties() {
        return new ConnectionProperties() {
            @Override
            public String username() {
                return container.getAdminUsername();
            }

            @Override
            public String password() {
                return container.getAdminPassword();
            }

            @Override
            public String hostname() {
                return container.getHost();
            }

            @Override
            public int port() {
                return container.getAmqpPort();
            }
        };
    }

    @Override
    public void initialize() {
        LOG.info("RabbitMQ container running on {}", container.getAmqpUrl());
    }

    @Override
    public void shutdown() {
        container.stop();
    }
}
