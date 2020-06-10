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

package org.apache.camel.kafkaconnector.sjms2.services;

import java.util.Properties;

import org.apache.camel.kafkaconnector.sjms2.clients.JMSClient;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public interface JMSService extends BeforeAllCallback, AfterAllCallback {

    /**
     * Gets the connection properties for accessing the service
     * @return
     */
    Properties getConnectionProperties();

    /**
     * Get the appropriate client for the service
     * @return
     */
    JMSClient getClient();

    /**
     * Gets the default endpoint for the JMS service (ie.: amqp://host:port, or tcp://host:port, etc)
     * @return the endpoint URL as a string in the specific format used by the service
     */
    String getDefaultEndpoint();

    /**
     * Perform any initialization necessary
     */
    void initialize();

    /**
     * Shuts down the service after the test has completed
     */
    void shutdown();


    @Override
    default void beforeAll(ExtensionContext extensionContext) throws Exception {
        initialize();
    }

    @Override
    default void afterAll(ExtensionContext extensionContext) throws Exception {
        shutdown();
    }
}
