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

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An interface for producing different types of connector properties that match
 * an specific type of connector in test.
 */
public interface ConnectorPropertyFactory {


    /**
     * Gets the properties used to configure the connector
     * @return a Properties object containing the set of properties for the connector
     */
    Properties getProperties();

    default void log() {
        Properties properties = getProperties();

        Logger log = LoggerFactory.getLogger(ConnectorPropertyFactory.class);

        log.info("Using the following properties for the test: ");
        properties.entrySet().forEach(entry -> log.info("{}={}", entry.getKey(), entry.getValue()));
    }
}
