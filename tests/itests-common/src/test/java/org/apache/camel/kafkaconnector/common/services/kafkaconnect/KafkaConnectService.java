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

package org.apache.camel.kafkaconnector.common.services.kafkaconnect;

import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.kafka.connect.runtime.rest.entities.ConnectorStateInfo;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public interface KafkaConnectService extends BeforeTestExecutionCallback, AfterTestExecutionCallback {

    void initializeConnector(ConnectorPropertyFactory propertyFactory) throws ExecutionException, InterruptedException;
    void initializeConnectorBlocking(ConnectorPropertyFactory propertyFactory, Integer expectedTasksnumber) throws ExecutionException, InterruptedException;

    void stop();
    void start();

    void connectorStateCheck(Consumer<ConnectorStateInfo> taskStateConsumer);

    @Override
    default void afterTestExecution(ExtensionContext extensionContext) {
        stop();
    }

    @Override
    default void beforeTestExecution(ExtensionContext extensionContext) {
        start();
    }
}
