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

import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.kafka.connect.runtime.ConnectorConfig;

public abstract class BasicConnectorPropertyFactory<T extends BasicConnectorPropertyFactory<T>> implements ConnectorPropertyFactory {
    private Properties connectorProps = new Properties();

    public T withName(String name) {
        connectorProps.put(ConnectorConfig.NAME_CONFIG, name);

        return (T) this;
    }

    public T withTasksMax(int tasksMax) {
        connectorProps.put("tasks.max", String.valueOf(tasksMax));

        return (T) this;
    }

    public T withConnectorClass(String connectorClass) {
        connectorProps.put(ConnectorConfig.CONNECTOR_CLASS_CONFIG, connectorClass);

        return (T) this;
    }

    public T withKeyConverterClass(String converterClass) {
        connectorProps.put(ConnectorConfig.KEY_CONVERTER_CLASS_CONFIG, converterClass);

        return (T) this;
    }

    public T withValueConverterClass(String converterClass) {
        connectorProps.put(ConnectorConfig.VALUE_CONVERTER_CLASS_CONFIG, converterClass);

        return (T) this;
    }

    public TransformsConfigBuilder<T> withTransformsConfig(String name) {
        return new TransformsConfigBuilder<>((T) this, getProperties(), name);
    }

    protected T setProperty(String name, Object value) {
        connectorProps.put(name, value);

        return (T) this;
    }

    public static String classRef(String className) {
        return "#class:" + className;
    }

    public T merge(Properties properties) {
        Set<Map.Entry<Object, Object>> set = properties.entrySet();
        connectorProps.putAll(set.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b)->b)));

        return (T) this;
    }

    @Override
    public Properties getProperties() {
        return connectorProps;
    }
}
