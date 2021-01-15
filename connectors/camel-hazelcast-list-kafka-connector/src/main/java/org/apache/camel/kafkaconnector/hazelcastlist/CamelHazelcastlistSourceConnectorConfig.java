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
package org.apache.camel.kafkaconnector.hazelcastlist;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelHazelcastlistSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_HAZELCASTLIST_PATH_CACHE_NAME_CONF = "camel.source.path.cacheName";
    public static final String CAMEL_SOURCE_HAZELCASTLIST_PATH_CACHE_NAME_DOC = "The name of the cache";
    public static final String CAMEL_SOURCE_HAZELCASTLIST_PATH_CACHE_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_DEFAULT_OPERATION_CONF = "camel.source.endpoint.defaultOperation";
    public static final String CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_DEFAULT_OPERATION_DOC = "To specify a default operation to use, if no operation header has been provided. One of: [put] [delete] [get] [update] [query] [getAll] [clear] [putIfAbsent] [allAll] [removeAll] [retainAll] [evict] [evictAll] [valueCount] [containsKey] [containsValue] [keySet] [removevalue] [increment] [decrement] [setvalue] [destroy] [compareAndSet] [getAndAdd] [add] [offer] [peek] [poll] [remainingCapacity] [drainTo] [removeIf] [take] [publish] [readOnceHeal] [readOnceTail] [capacity]";
    public static final String CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_DEFAULT_OPERATION_DEFAULT = null;
    public static final String CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_HAZELCAST_INSTANCE_CONF = "camel.source.endpoint.hazelcastInstance";
    public static final String CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_HAZELCAST_INSTANCE_DOC = "The hazelcast instance reference which can be used for hazelcast endpoint.";
    public static final String CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_HAZELCAST_INSTANCE_DEFAULT = null;
    public static final String CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_HAZELCAST_INSTANCE_NAME_CONF = "camel.source.endpoint.hazelcastInstanceName";
    public static final String CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_HAZELCAST_INSTANCE_NAME_DOC = "The hazelcast instance reference name which can be used for hazelcast endpoint. If you don't specify the instance reference, camel use the default hazelcast instance from the camel-hazelcast instance.";
    public static final String CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_HAZELCAST_INSTANCE_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF = "camel.source.endpoint.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_EXCEPTION_HANDLER_CONF = "camel.source.endpoint.exceptionHandler";
    public static final String CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_EXCEPTION_HANDLER_DOC = "To let the consumer use a custom ExceptionHandler. Notice if the option bridgeErrorHandler is enabled then this option is not in use. By default the consumer will deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final String CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_EXCEPTION_HANDLER_DEFAULT = null;
    public static final String CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_EXCHANGE_PATTERN_CONF = "camel.source.endpoint.exchangePattern";
    public static final String CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_EXCHANGE_PATTERN_DOC = "Sets the exchange pattern when the consumer creates an exchange. One of: [InOnly] [InOut] [InOptionalOut]";
    public static final String CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_EXCHANGE_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_BRIDGE_ERROR_HANDLER_CONF = "camel.component.hazelcast-list.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.hazelcast-list.autowiredEnabled";
    public static final String CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;
    public static final String CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_HAZELCAST_INSTANCE_CONF = "camel.component.hazelcast-list.hazelcastInstance";
    public static final String CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_HAZELCAST_INSTANCE_DOC = "The hazelcast instance reference which can be used for hazelcast endpoint. If you don't specify the instance reference, camel use the default hazelcast instance from the camel-hazelcast instance.";
    public static final String CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_HAZELCAST_INSTANCE_DEFAULT = null;
    public static final String CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_HAZELCAST_MODE_CONF = "camel.component.hazelcast-list.hazelcastMode";
    public static final String CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_HAZELCAST_MODE_DOC = "The hazelcast mode reference which kind of instance should be used. If you don't specify the mode, then the node mode will be the default.";
    public static final String CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_HAZELCAST_MODE_DEFAULT = "node";

    public CamelHazelcastlistSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelHazelcastlistSourceConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_HAZELCASTLIST_PATH_CACHE_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_HAZELCASTLIST_PATH_CACHE_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_HAZELCASTLIST_PATH_CACHE_NAME_DOC);
        conf.define(CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_DEFAULT_OPERATION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_DEFAULT_OPERATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_DEFAULT_OPERATION_DOC);
        conf.define(CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_HAZELCAST_INSTANCE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_HAZELCAST_INSTANCE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_HAZELCAST_INSTANCE_DOC);
        conf.define(CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_HAZELCAST_INSTANCE_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_HAZELCAST_INSTANCE_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_HAZELCAST_INSTANCE_NAME_DOC);
        conf.define(CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_EXCEPTION_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_EXCEPTION_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_EXCEPTION_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_EXCHANGE_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_EXCHANGE_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_HAZELCASTLIST_ENDPOINT_EXCHANGE_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_AUTOWIRED_ENABLED_DOC);
        conf.define(CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_HAZELCAST_INSTANCE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_HAZELCAST_INSTANCE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_HAZELCAST_INSTANCE_DOC);
        conf.define(CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_HAZELCAST_MODE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_HAZELCAST_MODE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_HAZELCASTLIST_COMPONENT_HAZELCAST_MODE_DOC);
        return conf;
    }
}