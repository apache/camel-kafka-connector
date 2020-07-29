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
package org.apache.camel.kafkaconnector.vertx;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelVertxSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_VERTX_PATH_ADDRESS_CONF = "camel.source.path.address";
    private static final String CAMEL_SOURCE_VERTX_PATH_ADDRESS_DOC = "Sets the event bus address used to communicate";
    private static final String CAMEL_SOURCE_VERTX_PATH_ADDRESS_DEFAULT = null;
    public static final String CAMEL_SOURCE_VERTX_ENDPOINT_PUB_SUB_CONF = "camel.source.endpoint.pubSub";
    private static final String CAMEL_SOURCE_VERTX_ENDPOINT_PUB_SUB_DOC = "Whether to use publish/subscribe instead of point to point when sending to a vertx endpoint.";
    private static final String CAMEL_SOURCE_VERTX_ENDPOINT_PUB_SUB_DEFAULT = null;
    public static final String CAMEL_SOURCE_VERTX_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF = "camel.source.endpoint.bridgeErrorHandler";
    private static final String CAMEL_SOURCE_VERTX_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final Boolean CAMEL_SOURCE_VERTX_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_VERTX_ENDPOINT_EXCEPTION_HANDLER_CONF = "camel.source.endpoint.exceptionHandler";
    private static final String CAMEL_SOURCE_VERTX_ENDPOINT_EXCEPTION_HANDLER_DOC = "To let the consumer use a custom ExceptionHandler. Notice if the option bridgeErrorHandler is enabled then this option is not in use. By default the consumer will deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final String CAMEL_SOURCE_VERTX_ENDPOINT_EXCEPTION_HANDLER_DEFAULT = null;
    public static final String CAMEL_SOURCE_VERTX_ENDPOINT_EXCHANGE_PATTERN_CONF = "camel.source.endpoint.exchangePattern";
    private static final String CAMEL_SOURCE_VERTX_ENDPOINT_EXCHANGE_PATTERN_DOC = "Sets the exchange pattern when the consumer creates an exchange. One of: [InOnly] [InOut] [InOptionalOut]";
    private static final String CAMEL_SOURCE_VERTX_ENDPOINT_EXCHANGE_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_VERTX_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.source.endpoint.basicPropertyBinding";
    private static final String CAMEL_SOURCE_VERTX_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SOURCE_VERTX_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_VERTX_ENDPOINT_SYNCHRONOUS_CONF = "camel.source.endpoint.synchronous";
    private static final String CAMEL_SOURCE_VERTX_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SOURCE_VERTX_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SOURCE_VERTX_COMPONENT_HOST_CONF = "camel.component.vertx.host";
    private static final String CAMEL_SOURCE_VERTX_COMPONENT_HOST_DOC = "Hostname for creating an embedded clustered EventBus";
    private static final String CAMEL_SOURCE_VERTX_COMPONENT_HOST_DEFAULT = null;
    public static final String CAMEL_SOURCE_VERTX_COMPONENT_PORT_CONF = "camel.component.vertx.port";
    private static final String CAMEL_SOURCE_VERTX_COMPONENT_PORT_DOC = "Port for creating an embedded clustered EventBus";
    private static final Integer CAMEL_SOURCE_VERTX_COMPONENT_PORT_DEFAULT = null;
    public static final String CAMEL_SOURCE_VERTX_COMPONENT_TIMEOUT_CONF = "camel.component.vertx.timeout";
    private static final String CAMEL_SOURCE_VERTX_COMPONENT_TIMEOUT_DOC = "Timeout in seconds to wait for clustered Vertx EventBus to be ready. The default value is 60.";
    private static final Integer CAMEL_SOURCE_VERTX_COMPONENT_TIMEOUT_DEFAULT = 60;
    public static final String CAMEL_SOURCE_VERTX_COMPONENT_VERTX_CONF = "camel.component.vertx.vertx";
    private static final String CAMEL_SOURCE_VERTX_COMPONENT_VERTX_DOC = "To use the given vertx EventBus instead of creating a new embedded EventBus";
    private static final String CAMEL_SOURCE_VERTX_COMPONENT_VERTX_DEFAULT = null;
    public static final String CAMEL_SOURCE_VERTX_COMPONENT_VERTX_OPTIONS_CONF = "camel.component.vertx.vertxOptions";
    private static final String CAMEL_SOURCE_VERTX_COMPONENT_VERTX_OPTIONS_DOC = "Options to use for creating vertx";
    private static final String CAMEL_SOURCE_VERTX_COMPONENT_VERTX_OPTIONS_DEFAULT = null;
    public static final String CAMEL_SOURCE_VERTX_COMPONENT_BRIDGE_ERROR_HANDLER_CONF = "camel.component.vertx.bridgeErrorHandler";
    private static final String CAMEL_SOURCE_VERTX_COMPONENT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final Boolean CAMEL_SOURCE_VERTX_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_VERTX_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.vertx.basicPropertyBinding";
    private static final String CAMEL_SOURCE_VERTX_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SOURCE_VERTX_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_VERTX_COMPONENT_VERTX_FACTORY_CONF = "camel.component.vertx.vertxFactory";
    private static final String CAMEL_SOURCE_VERTX_COMPONENT_VERTX_FACTORY_DOC = "To use a custom VertxFactory implementation";
    private static final String CAMEL_SOURCE_VERTX_COMPONENT_VERTX_FACTORY_DEFAULT = null;

    public CamelVertxSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelVertxSourceConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_VERTX_PATH_ADDRESS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_VERTX_PATH_ADDRESS_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_VERTX_PATH_ADDRESS_DOC);
        conf.define(CAMEL_SOURCE_VERTX_ENDPOINT_PUB_SUB_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_VERTX_ENDPOINT_PUB_SUB_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_VERTX_ENDPOINT_PUB_SUB_DOC);
        conf.define(CAMEL_SOURCE_VERTX_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_VERTX_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_VERTX_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_VERTX_ENDPOINT_EXCEPTION_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_VERTX_ENDPOINT_EXCEPTION_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_VERTX_ENDPOINT_EXCEPTION_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_VERTX_ENDPOINT_EXCHANGE_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_VERTX_ENDPOINT_EXCHANGE_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_VERTX_ENDPOINT_EXCHANGE_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_VERTX_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_VERTX_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_VERTX_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_VERTX_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_VERTX_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_VERTX_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SOURCE_VERTX_COMPONENT_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_VERTX_COMPONENT_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_VERTX_COMPONENT_HOST_DOC);
        conf.define(CAMEL_SOURCE_VERTX_COMPONENT_PORT_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_VERTX_COMPONENT_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_VERTX_COMPONENT_PORT_DOC);
        conf.define(CAMEL_SOURCE_VERTX_COMPONENT_TIMEOUT_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_VERTX_COMPONENT_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_VERTX_COMPONENT_TIMEOUT_DOC);
        conf.define(CAMEL_SOURCE_VERTX_COMPONENT_VERTX_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_VERTX_COMPONENT_VERTX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_VERTX_COMPONENT_VERTX_DOC);
        conf.define(CAMEL_SOURCE_VERTX_COMPONENT_VERTX_OPTIONS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_VERTX_COMPONENT_VERTX_OPTIONS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_VERTX_COMPONENT_VERTX_OPTIONS_DOC);
        conf.define(CAMEL_SOURCE_VERTX_COMPONENT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_VERTX_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_VERTX_COMPONENT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_VERTX_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_VERTX_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_VERTX_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_VERTX_COMPONENT_VERTX_FACTORY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_VERTX_COMPONENT_VERTX_FACTORY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_VERTX_COMPONENT_VERTX_FACTORY_DOC);
        return conf;
    }
}