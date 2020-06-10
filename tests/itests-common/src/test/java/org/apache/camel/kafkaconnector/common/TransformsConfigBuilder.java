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

import org.apache.kafka.connect.runtime.ConnectorConfig;


public class TransformsConfigBuilder<T extends ConnectorPropertyFactory> {
    private T handle;
    private Properties properties;
    private String name;

    public TransformsConfigBuilder(T handle, Properties properties, String name) {
        this.handle = handle;
        this.properties = properties;
        this.name = name;

        properties.put(ConnectorConfig.TRANSFORMS_CONFIG, name);
    }

    public TransformsConfigBuilder<T> withEntry(String key, String value) {
        properties.put(ConnectorConfig.TRANSFORMS_CONFIG + "." + name + "." + key, value);

        return this;
    }

    public T end() {
        return handle;
    }
}
