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
import java.util.Map;
import java.util.Set;

import org.apache.camel.LoggingLevel;
import org.apache.camel.catalog.RuntimeCamelCatalog;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.source.SourceRecord;
import org.slf4j.Logger;

public final class TaskHelper {

    private TaskHelper() {
    }

    public static String buildUrl(RuntimeCamelCatalog rcc, Map<String, String> props, String componentSchema, String endpointPropertiesPrefix, String pathPropertiesPrefix) throws URISyntaxException {
        Map<String, String> filteredProps = new HashMap<>();
        props.keySet().stream()
                .filter(k -> k.startsWith(endpointPropertiesPrefix) || k.startsWith(pathPropertiesPrefix))
                .forEach(k -> filteredProps.put(k.replace(endpointPropertiesPrefix, "").replace(pathPropertiesPrefix, ""), props.get(k)));
        return rcc.asEndpointUri(componentSchema, filteredProps, false);
    }

    public static String buildUrl(Map<String, String> props, String componentSchema, String endpointPropertiesPrefix, String pathPropertiesPrefix) {
        final String urlPath = createUrlPathFromProperties(props, pathPropertiesPrefix);
        final String endpointOptions = createEndpointOptionsFromProperties(props, endpointPropertiesPrefix);

        return componentSchema + ":" + urlPath + endpointOptions;
    }

    public static String createEndpointOptionsFromProperties(Map<String, String> props, String prefix) {
        return props.keySet().stream()
                .filter(k -> k.startsWith(prefix))
                .map(k -> k.replace(prefix, "") + "=" + props.get(k))
                .reduce((o1, o2) -> o1 + "&" + o2)
                .map(result -> (result == null || result.isEmpty()) ? "" : "?" + result)
                .orElse("");
    }

    public static String createUrlPathFromProperties(Map<String, String> props, String prefix) {
        return props.keySet().stream()
                .filter(k -> k.startsWith(prefix))
                .map(k -> props.get(k))
                .reduce((p1, p2) -> p1 + ":" + p2)
                .orElse("");
    }

    public static Map<String, String> mergeProperties(Map<String, String> defaultProps, Map<String, String> loadedProps) {
        if (loadedProps == null && defaultProps == null) {
            return Collections.EMPTY_MAP;
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

    private static String getStringPrefix(String s) {
        return s.lastIndexOf(".") > 0 ? s.substring(0, s.lastIndexOf(".")) : "";
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

    public static <CFG extends AbstractConfig> void logRecordContent(Logger logger, ConnectRecord<?> record, CFG config) {
        if (logger != null && record != null && config != null) {
            // do not log record's content by default, as it may contain sensitive information
            LoggingLevel level = LoggingLevel.OFF;
            try {
                final String key = (record instanceof SourceRecord)
                    ? CamelSourceConnectorConfig.CAMEL_SOURCE_CONTENT_LOG_LEVEL_CONF
                    : CamelSinkConnectorConfig.CAMEL_SINK_CONTENT_LOG_LEVEL_CONF;
                level = LoggingLevel.valueOf(config.getString(key).toUpperCase());
            } catch (Exception e) {
                logger.warn("Invalid value for contentLogLevel property");
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

}
