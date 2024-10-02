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
package org.apache.camel.kafkaconnector.sjms2.common;

import org.apache.camel.test.infra.messaging.services.MessagingContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

//TODO: remove after https://issues.apache.org/jira/browse/CAMEL-21297 is fixed and released
public class FixedDispatchRouterContainer extends GenericContainer<FixedDispatchRouterContainer> implements MessagingContainer {
    private static final int DEFAULT_AMQP_PORT = 5672;
    private static final String IMAGE_NAME = "quay.io/interconnectedcloud/qdrouterd:latest";

    public FixedDispatchRouterContainer() {
        super(IMAGE_NAME);
        (this.withExposedPorts(new Integer[]{DEFAULT_AMQP_PORT})).waitingFor(Wait.forListeningPort());
    }

    public int getAMQPPort() {
        return this.getMappedPort(DEFAULT_AMQP_PORT);
    }

    public String getAMQPEndpoint() {
        return String.format("amqp://%s:%d", this.getHost(), this.getAMQPPort());
    }

    public String defaultEndpoint() {
        return this.getAMQPEndpoint();
    }
}