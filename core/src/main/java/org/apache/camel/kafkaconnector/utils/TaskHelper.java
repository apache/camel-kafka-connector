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
package org.apache.camel.kafkaconnector.utils;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.camel.CamelContext;
import org.apache.camel.LoggingLevel;
import org.apache.camel.kafkaconnector.CamelConnectorConfig;
import org.apache.camel.spi.EndpointUriFactory;
import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.slf4j.Logger;

public final class TaskHelper {

    private TaskHelper() {
    }

    /**
     * Try to build a url of a Camel {@link org.apache.camel.Endpoint}.
     *
     * @param camelContext the {@link CamelContext} used to retrieve an instance of a EndpointUriFactory for the given component schema.
     * @param props properties used to build the url in the form of a key -> value {@link Map}.
     * @param componentSchema the schema name of the Camel {@link org.apache.camel.Component} used to build the Camel {@link org.apache.camel.Endpoint} url.
     * @param endpointPropertiesPrefix prefix of all the Camel {@link org.apache.camel.Endpoint} properties.
     * @param pathPropertiesPrefix prefix of all the properties used in the Camel {@link org.apache.camel.Endpoint} path.
     *
     * @return A String representing the built url.
     * @throws {@link URISyntaxException} in case of uri build failure.
     */
    public static String buildUrl(CamelContext camelContext, Map<String, String> props, String componentSchema, String endpointPropertiesPrefix, String pathPropertiesPrefix) throws URISyntaxException {
        Map<String, Object> filteredProps = props.entrySet().stream()
            .filter(e -> e.getKey().startsWith(endpointPropertiesPrefix) || e.getKey().startsWith(pathPropertiesPrefix))
            .collect(Collectors.toMap(
                e -> e.getKey().replace(endpointPropertiesPrefix, "").replace(pathPropertiesPrefix, ""),
                Map.Entry::getValue
            ));

        EndpointUriFactory factory = camelContext.getCamelContextExtension().getEndpointUriFactory(componentSchema);
        if (factory == null) {
            throw new IllegalStateException("Unable to compute endpoint uri. Reason: uri factory for schema `" + componentSchema + "` not found");
        }
        if (!factory.isEnabled(componentSchema)) {
            throw new IllegalStateException("Unable to compute endpoint uri. Reason: uri factory for schema `" + componentSchema + "` not enabled");
        }



        return factory.buildUri(componentSchema, filteredProps, false);
    }

    /**
     * Combines default properties with loaded properties with the following logic:
     * 1) loaded properties overwrite default properties.
     * 2) default properties that starts with a loaded property are removed.
     *
     * Example:
     * default properties:
     *   defaultProperty=defaultValue
     *   overwrittenDefaultProperty=defaultValue
     *   camel.component.x.objectProperty=#class:my.package.MyClass
     *   camel.component.x.objectProperty.removedDefaultField=defaultValue
     *   camel.component.x.defaultProperty=defaultValue
     *
     * loaded properties:
     *   overwrittenDefaultProperty=loadedValue
     *   camel.component.x.objectProperty=#class:my.package.MyOtherClass
     *   camel.component.x.objectProperty.loadedField=loadedValue
     *   camel.component.x.loadedProperty=loadedValue
     *
     * will result in:
     *   defaultProperty=defaultValue
     *   overwrittenDefaultProperty=loadedValue
     *   camel.component.x.objectProperty=#class:my.package.MyOtherClass
     *   camel.component.x.objectProperty.anotherField=loadedValue
     *   camel.component.x.loadedProperty=loadedValue
     *   camel.component.x.defaultProperty=defaultValue
     *
     * See org.apache.camel.kafkaconnector.utils.TaskHelperTests for some examples.
     *
     * @param defaultProps RuntimeCamelCatalog used to build the url.
     * @param loadedProps properties used to build the url in the form of a key -> value {@link Map}.
     */
    public static Map<String, String> combineDefaultAndLoadedProperties(Map<String, String> defaultProps, Map<String, String> loadedProps) {
        if (loadedProps == null && defaultProps == null) {
            return Collections.emptyMap();
        } else if (loadedProps == null) {
            return new HashMap<>(defaultProps);
        } else if (defaultProps == null) {
            return new HashMap<>(loadedProps);
        } else {
            Map<String, String> result = new HashMap<>(loadedProps);
            defaultProps.keySet().stream()
                    .filter(k -> !stringStartWithOneOfPrefixes(k, loadedProps.keySet()))
                    .filter(k -> !result.containsKey(k))
                    .forEach(k -> result.put(k, defaultProps.get(k)));
            return result;
        }
    }

    /**
     * The names of the given properties that the definition declares with the type {@link ConfigDef.Type#PASSWORD},
     * as the comma separated list a connector passes to its tasks under
     * {@link CamelConnectorConfig#CAMEL_CONNECTOR_PASSWORD_PROPERTIES_CONF}.
     *
     * @param definition the configuration definition of the connector.
     * @param props the properties of the connector.
     * @return the comma separated names, empty when there is none.
     */
    public static String passwordPropertyNames(ConfigDef definition, Map<String, String> props) {
        Map<String, ConfigDef.ConfigKey> keys = definition.configKeys();
        return props.keySet().stream()
            .filter(k -> keys.containsKey(k) && keys.get(k).type == ConfigDef.Type.PASSWORD)
            .sorted()
            .collect(Collectors.joining(","));
    }

    /**
     * The names of the task properties whose values are passwords: the ones the connector listed under
     * {@link CamelConnectorConfig#CAMEL_CONNECTOR_PASSWORD_PROPERTIES_CONF}, and the ones the configuration of the task
     * declares with the type {@link ConfigDef.Type#PASSWORD}. The list of the connector is removed from the properties,
     * as it is not a Camel property.
     *
     * @param config the configuration of the task.
     * @param props the properties of the task, the list of the connector is removed from them.
     * @return the names of the password properties.
     */
    public static Set<String> takePasswordPropertyNames(AbstractConfig config, Map<String, String> props) {
        Set<String> names = new HashSet<>();
        String listed = props.remove(CamelConnectorConfig.CAMEL_CONNECTOR_PASSWORD_PROPERTIES_CONF);
        if (listed != null) {
            for (String name : listed.split(",")) {
                if (!name.isBlank()) {
                    names.add(name.trim());
                }
            }
        }
        for (String name : props.keySet()) {
            if (config.typeOf(name) == ConfigDef.Type.PASSWORD) {
                names.add(name);
            }
        }
        return names;
    }

    private static Boolean stringStartWithOneOfPrefixes(String s, Set<String> prefixes) {
        if (s == null || prefixes == null) {
            return false;
        }
        for (String p : prefixes) {
            if (s.startsWith(p)) {
                return true;
            }
        }
        return false;
    }

    public static void logRecordContent(Logger logger, LoggingLevel level, ConnectRecord<?> record) {
        if (level == LoggingLevel.OFF) {
            return;
        }

        switch (level) {
            case TRACE:
                logger.trace(record.toString());
                break;
            case DEBUG:
                logger.debug(record.toString());
                break;
            case INFO:
                logger.info(record.toString());
                break;
            case WARN:
                logger.warn(record.toString());
                break;
            case ERROR:
                logger.error(record.toString());
                break;
            default:
                break;
        }
    }
}
