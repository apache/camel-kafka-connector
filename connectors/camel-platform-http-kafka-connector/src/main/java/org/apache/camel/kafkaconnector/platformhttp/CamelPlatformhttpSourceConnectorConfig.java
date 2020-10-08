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
package org.apache.camel.kafkaconnector.platformhttp;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelPlatformhttpSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_PLATFORMHTTP_PATH_PATH_CONF = "camel.source.path.path";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_PATH_PATH_DOC = "The path under which this endpoint serves the HTTP requests";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_PATH_PATH_DEFAULT = null;
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF = "camel.source.endpoint.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_CONSUMES_CONF = "camel.source.endpoint.consumes";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_CONSUMES_DOC = "The content type this endpoint accepts as an input, such as application/xml or application/json. null or &#42;/&#42; mean no restriction.";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_CONSUMES_DEFAULT = null;
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_HTTP_METHOD_RESTRICT_CONF = "camel.source.endpoint.httpMethodRestrict";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_HTTP_METHOD_RESTRICT_DOC = "A comma separated list of HTTP methods to serve, e.g. GET,POST . If no methods are specified, all methods will be served.";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_HTTP_METHOD_RESTRICT_DEFAULT = null;
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_MATCH_ON_URI_PREFIX_CONF = "camel.source.endpoint.matchOnUriPrefix";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_MATCH_ON_URI_PREFIX_DOC = "Whether or not the consumer should try to find a target consumer by matching the URI prefix if no exact match is found.";
    public static final Boolean CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_MATCH_ON_URI_PREFIX_DEFAULT = false;
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_PRODUCES_CONF = "camel.source.endpoint.produces";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_PRODUCES_DOC = "The content type this endpoint produces, such as application/xml or application/json.";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_PRODUCES_DEFAULT = null;
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_EXCEPTION_HANDLER_CONF = "camel.source.endpoint.exceptionHandler";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_EXCEPTION_HANDLER_DOC = "To let the consumer use a custom ExceptionHandler. Notice if the option bridgeErrorHandler is enabled then this option is not in use. By default the consumer will deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_EXCEPTION_HANDLER_DEFAULT = null;
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_EXCHANGE_PATTERN_CONF = "camel.source.endpoint.exchangePattern";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_EXCHANGE_PATTERN_DOC = "Sets the exchange pattern when the consumer creates an exchange. One of: [InOnly] [InOut] [InOptionalOut]";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_EXCHANGE_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_FILE_NAME_EXT_WHITELIST_CONF = "camel.source.endpoint.fileNameExtWhitelist";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_FILE_NAME_EXT_WHITELIST_DOC = "A comma or whitespace separated list of file extensions. Uploads having these extensions will be stored locally. Null value or asterisk () will allow all files.";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_FILE_NAME_EXT_WHITELIST_DEFAULT = null;
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.source.endpoint.basicPropertyBinding";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_HEADER_FILTER_STRATEGY_CONF = "camel.source.endpoint.headerFilterStrategy";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_HEADER_FILTER_STRATEGY_DOC = "To use a custom HeaderFilterStrategy to filter headers to and from Camel message.";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_HEADER_FILTER_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_PLATFORM_HTTP_ENGINE_CONF = "camel.source.endpoint.platformHttpEngine";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_PLATFORM_HTTP_ENGINE_DOC = "An HTTP Server engine implementation to serve the requests of this endpoint.";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_PLATFORM_HTTP_ENGINE_DEFAULT = null;
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_SYNCHRONOUS_CONF = "camel.source.endpoint.synchronous";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SOURCE_PLATFORMHTTP_COMPONENT_BRIDGE_ERROR_HANDLER_CONF = "camel.component.platform-http.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_COMPONENT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_PLATFORMHTTP_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_PLATFORMHTTP_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.platform-http.basicPropertyBinding";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SOURCE_PLATFORMHTTP_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_PLATFORMHTTP_COMPONENT_ENGINE_CONF = "camel.component.platform-http.engine";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_COMPONENT_ENGINE_DOC = "An HTTP Server engine implementation to serve the requests";
    public static final String CAMEL_SOURCE_PLATFORMHTTP_COMPONENT_ENGINE_DEFAULT = null;

    public CamelPlatformhttpSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelPlatformhttpSourceConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_PLATFORMHTTP_PATH_PATH_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PLATFORMHTTP_PATH_PATH_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_PLATFORMHTTP_PATH_PATH_DOC);
        conf.define(CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_CONSUMES_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_CONSUMES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_CONSUMES_DOC);
        conf.define(CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_HTTP_METHOD_RESTRICT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_HTTP_METHOD_RESTRICT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_HTTP_METHOD_RESTRICT_DOC);
        conf.define(CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_MATCH_ON_URI_PREFIX_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_MATCH_ON_URI_PREFIX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_MATCH_ON_URI_PREFIX_DOC);
        conf.define(CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_PRODUCES_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_PRODUCES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_PRODUCES_DOC);
        conf.define(CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_EXCEPTION_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_EXCEPTION_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_EXCEPTION_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_EXCHANGE_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_EXCHANGE_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_EXCHANGE_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_FILE_NAME_EXT_WHITELIST_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_FILE_NAME_EXT_WHITELIST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_FILE_NAME_EXT_WHITELIST_DOC);
        conf.define(CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_HEADER_FILTER_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_HEADER_FILTER_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_HEADER_FILTER_STRATEGY_DOC);
        conf.define(CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_PLATFORM_HTTP_ENGINE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_PLATFORM_HTTP_ENGINE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_PLATFORM_HTTP_ENGINE_DOC);
        conf.define(CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PLATFORMHTTP_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SOURCE_PLATFORMHTTP_COMPONENT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PLATFORMHTTP_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PLATFORMHTTP_COMPONENT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_PLATFORMHTTP_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PLATFORMHTTP_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.LOW, CAMEL_SOURCE_PLATFORMHTTP_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_PLATFORMHTTP_COMPONENT_ENGINE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PLATFORMHTTP_COMPONENT_ENGINE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PLATFORMHTTP_COMPONENT_ENGINE_DOC);
        return conf;
    }
}