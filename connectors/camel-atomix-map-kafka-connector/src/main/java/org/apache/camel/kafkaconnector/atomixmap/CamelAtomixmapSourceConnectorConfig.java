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
package org.apache.camel.kafkaconnector.atomixmap;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelAtomixmapSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_ATOMIXMAP_PATH_RESOURCE_NAME_CONF = "camel.source.path.resourceName";
    private static final String CAMEL_SOURCE_ATOMIXMAP_PATH_RESOURCE_NAME_DOC = "The distributed resource name";
    private static final String CAMEL_SOURCE_ATOMIXMAP_PATH_RESOURCE_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_ATOMIX_CONF = "camel.source.endpoint.atomix";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_ATOMIX_DOC = "The Atomix instance to use";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_ATOMIX_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_CONFIGURATION_URI_CONF = "camel.source.endpoint.configurationUri";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_CONFIGURATION_URI_DOC = "The Atomix configuration uri.";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_CONFIGURATION_URI_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_DEFAULT_ACTION_CONF = "camel.source.endpoint.defaultAction";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_DEFAULT_ACTION_DOC = "The default action. One of: [PUT] [PUT_IF_ABSENT] [GET] [CLEAR] [SIZE] [CONTAINS_KEY] [CONTAINS_VALUE] [IS_EMPTY] [ENTRY_SET] [REMOVE] [REPLACE] [VALUES]";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_DEFAULT_ACTION_DEFAULT = "PUT";
    public static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_KEY_CONF = "camel.source.endpoint.key";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_KEY_DOC = "The key to use if none is set in the header or to listen for events for a specific key.";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_KEY_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_NODES_CONF = "camel.source.endpoint.nodes";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_NODES_DOC = "The address of the nodes composing the cluster.";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_NODES_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_RESULT_HEADER_CONF = "camel.source.endpoint.resultHeader";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_RESULT_HEADER_DOC = "The header that wil carry the result.";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_RESULT_HEADER_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_TRANSPORT_CLASS_NAME_CONF = "camel.source.endpoint.transportClassName";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_TRANSPORT_CLASS_NAME_DOC = "The class name (fqn) of the Atomix transport";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_TRANSPORT_CLASS_NAME_DEFAULT = "io.atomix.catalyst.transport.netty.NettyTransport";
    public static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_TTL_CONF = "camel.source.endpoint.ttl";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_TTL_DOC = "The resource ttl.";
    private static final Long CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_TTL_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF = "camel.source.endpoint.bridgeErrorHandler";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final Boolean CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_EXCEPTION_HANDLER_CONF = "camel.source.endpoint.exceptionHandler";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_EXCEPTION_HANDLER_DOC = "To let the consumer use a custom ExceptionHandler. Notice if the option bridgeErrorHandler is enabled then this option is not in use. By default the consumer will deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_EXCEPTION_HANDLER_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_EXCHANGE_PATTERN_CONF = "camel.source.endpoint.exchangePattern";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_EXCHANGE_PATTERN_DOC = "Sets the exchange pattern when the consumer creates an exchange. One of: [InOnly] [InOut] [InOptionalOut]";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_EXCHANGE_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.source.endpoint.basicPropertyBinding";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_DEFAULT_RESOURCE_CONFIG_CONF = "camel.source.endpoint.defaultResourceConfig";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_DEFAULT_RESOURCE_CONFIG_DOC = "The cluster wide default resource configuration.";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_DEFAULT_RESOURCE_CONFIG_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_DEFAULT_RESOURCE_OPTIONS_CONF = "camel.source.endpoint.defaultResourceOptions";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_DEFAULT_RESOURCE_OPTIONS_DOC = "The local default resource options.";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_DEFAULT_RESOURCE_OPTIONS_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_EPHEMERAL_CONF = "camel.source.endpoint.ephemeral";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_EPHEMERAL_DOC = "Sets if the local member should join groups as PersistentMember or not. If set to ephemeral the local member will receive an auto generated ID thus the local one is ignored.";
    private static final Boolean CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_EPHEMERAL_DEFAULT = false;
    public static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_READ_CONSISTENCY_CONF = "camel.source.endpoint.readConsistency";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_READ_CONSISTENCY_DOC = "The read consistency level. One of: [ATOMIC] [ATOMIC_LEASE] [SEQUENTIAL] [LOCAL]";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_READ_CONSISTENCY_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_RESOURCE_CONFIGS_CONF = "camel.source.endpoint.resourceConfigs";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_RESOURCE_CONFIGS_DOC = "Cluster wide resources configuration.";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_RESOURCE_CONFIGS_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_RESOURCE_OPTIONS_CONF = "camel.source.endpoint.resourceOptions";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_RESOURCE_OPTIONS_DOC = "Local resources configurations";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_RESOURCE_OPTIONS_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_SYNCHRONOUS_CONF = "camel.source.endpoint.synchronous";
    private static final String CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_ATOMIX_CONF = "camel.component.atomix-map.atomix";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_ATOMIX_DOC = "The Atomix instance to use";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_ATOMIX_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_CONFIGURATION_CONF = "camel.component.atomix-map.configuration";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_CONFIGURATION_DOC = "The shared component configuration";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_CONFIGURATION_URI_CONF = "camel.component.atomix-map.configurationUri";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_CONFIGURATION_URI_DOC = "The path to the AtomixClient configuration";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_CONFIGURATION_URI_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_DEFAULT_ACTION_CONF = "camel.component.atomix-map.defaultAction";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_DEFAULT_ACTION_DOC = "The default action. One of: [PUT] [PUT_IF_ABSENT] [GET] [CLEAR] [SIZE] [CONTAINS_KEY] [CONTAINS_VALUE] [IS_EMPTY] [ENTRY_SET] [REMOVE] [REPLACE] [VALUES]";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_DEFAULT_ACTION_DEFAULT = "PUT";
    public static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_KEY_CONF = "camel.component.atomix-map.key";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_KEY_DOC = "The key to use if none is set in the header or to listen for events for a specific key.";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_KEY_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_NODES_CONF = "camel.component.atomix-map.nodes";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_NODES_DOC = "The nodes the AtomixClient should connect to";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_NODES_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_RESULT_HEADER_CONF = "camel.component.atomix-map.resultHeader";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_RESULT_HEADER_DOC = "The header that wil carry the result.";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_RESULT_HEADER_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_TRANSPORT_CLASS_NAME_CONF = "camel.component.atomix-map.transportClassName";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_TRANSPORT_CLASS_NAME_DOC = "The class name (fqn) of the Atomix transport";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_TRANSPORT_CLASS_NAME_DEFAULT = "io.atomix.catalyst.transport.netty.NettyTransport";
    public static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_TTL_CONF = "camel.component.atomix-map.ttl";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_TTL_DOC = "The resource ttl.";
    private static final Long CAMEL_SOURCE_ATOMIXMAP_COMPONENT_TTL_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_BRIDGE_ERROR_HANDLER_CONF = "camel.component.atomix-map.bridgeErrorHandler";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final Boolean CAMEL_SOURCE_ATOMIXMAP_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.atomix-map.basicPropertyBinding";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SOURCE_ATOMIXMAP_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_DEFAULT_RESOURCE_CONFIG_CONF = "camel.component.atomix-map.defaultResourceConfig";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_DEFAULT_RESOURCE_CONFIG_DOC = "The cluster wide default resource configuration.";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_DEFAULT_RESOURCE_CONFIG_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_DEFAULT_RESOURCE_OPTIONS_CONF = "camel.component.atomix-map.defaultResourceOptions";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_DEFAULT_RESOURCE_OPTIONS_DOC = "The local default resource options.";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_DEFAULT_RESOURCE_OPTIONS_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_EPHEMERAL_CONF = "camel.component.atomix-map.ephemeral";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_EPHEMERAL_DOC = "Sets if the local member should join groups as PersistentMember or not. If set to ephemeral the local member will receive an auto generated ID thus the local one is ignored.";
    private static final Boolean CAMEL_SOURCE_ATOMIXMAP_COMPONENT_EPHEMERAL_DEFAULT = false;
    public static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_READ_CONSISTENCY_CONF = "camel.component.atomix-map.readConsistency";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_READ_CONSISTENCY_DOC = "The read consistency level. One of: [ATOMIC] [ATOMIC_LEASE] [SEQUENTIAL] [LOCAL]";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_READ_CONSISTENCY_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_RESOURCE_CONFIGS_CONF = "camel.component.atomix-map.resourceConfigs";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_RESOURCE_CONFIGS_DOC = "Cluster wide resources configuration.";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_RESOURCE_CONFIGS_DEFAULT = null;
    public static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_RESOURCE_OPTIONS_CONF = "camel.component.atomix-map.resourceOptions";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_RESOURCE_OPTIONS_DOC = "Local resources configurations";
    private static final String CAMEL_SOURCE_ATOMIXMAP_COMPONENT_RESOURCE_OPTIONS_DEFAULT = null;

    public CamelAtomixmapSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelAtomixmapSourceConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_ATOMIXMAP_PATH_RESOURCE_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_PATH_RESOURCE_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_ATOMIXMAP_PATH_RESOURCE_NAME_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_ATOMIX_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_ATOMIX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_ATOMIX_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_CONFIGURATION_URI_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_CONFIGURATION_URI_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_CONFIGURATION_URI_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_DEFAULT_ACTION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_DEFAULT_ACTION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_DEFAULT_ACTION_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_KEY_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_NODES_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_NODES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_NODES_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_RESULT_HEADER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_RESULT_HEADER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_RESULT_HEADER_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_TRANSPORT_CLASS_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_TRANSPORT_CLASS_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_TRANSPORT_CLASS_NAME_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_TTL_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_TTL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_TTL_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_EXCEPTION_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_EXCEPTION_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_EXCEPTION_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_EXCHANGE_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_EXCHANGE_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_EXCHANGE_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_DEFAULT_RESOURCE_CONFIG_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_DEFAULT_RESOURCE_CONFIG_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_DEFAULT_RESOURCE_CONFIG_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_DEFAULT_RESOURCE_OPTIONS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_DEFAULT_RESOURCE_OPTIONS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_DEFAULT_RESOURCE_OPTIONS_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_EPHEMERAL_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_EPHEMERAL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_EPHEMERAL_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_READ_CONSISTENCY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_READ_CONSISTENCY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_READ_CONSISTENCY_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_RESOURCE_CONFIGS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_RESOURCE_CONFIGS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_RESOURCE_CONFIGS_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_RESOURCE_OPTIONS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_RESOURCE_OPTIONS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_RESOURCE_OPTIONS_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_COMPONENT_ATOMIX_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_ATOMIX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_ATOMIX_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_COMPONENT_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_CONFIGURATION_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_COMPONENT_CONFIGURATION_URI_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_CONFIGURATION_URI_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_CONFIGURATION_URI_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_COMPONENT_DEFAULT_ACTION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_DEFAULT_ACTION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_DEFAULT_ACTION_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_COMPONENT_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_KEY_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_COMPONENT_NODES_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_NODES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_NODES_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_COMPONENT_RESULT_HEADER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_RESULT_HEADER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_RESULT_HEADER_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_COMPONENT_TRANSPORT_CLASS_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_TRANSPORT_CLASS_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_TRANSPORT_CLASS_NAME_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_COMPONENT_TTL_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_TTL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_TTL_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_COMPONENT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_COMPONENT_DEFAULT_RESOURCE_CONFIG_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_DEFAULT_RESOURCE_CONFIG_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_DEFAULT_RESOURCE_CONFIG_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_COMPONENT_DEFAULT_RESOURCE_OPTIONS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_DEFAULT_RESOURCE_OPTIONS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_DEFAULT_RESOURCE_OPTIONS_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_COMPONENT_EPHEMERAL_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_EPHEMERAL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_EPHEMERAL_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_COMPONENT_READ_CONSISTENCY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_READ_CONSISTENCY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_READ_CONSISTENCY_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_COMPONENT_RESOURCE_CONFIGS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_RESOURCE_CONFIGS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_RESOURCE_CONFIGS_DOC);
        conf.define(CAMEL_SOURCE_ATOMIXMAP_COMPONENT_RESOURCE_OPTIONS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_RESOURCE_OPTIONS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_ATOMIXMAP_COMPONENT_RESOURCE_OPTIONS_DOC);
        return conf;
    }
}