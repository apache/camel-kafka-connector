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
package org.apache.camel.kafkaconnector.websocket;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelWebsocketSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_WEBSOCKET_PATH_HOST_CONF = "camel.source.path.host";
    public static final String CAMEL_SOURCE_WEBSOCKET_PATH_HOST_DOC = "The hostname. The default value is 0.0.0.0. Setting this option on the component will use the component configured value as default.";
    public static final String CAMEL_SOURCE_WEBSOCKET_PATH_HOST_DEFAULT = "0.0.0.0";
    public static final String CAMEL_SOURCE_WEBSOCKET_PATH_PORT_CONF = "camel.source.path.port";
    public static final String CAMEL_SOURCE_WEBSOCKET_PATH_PORT_DOC = "The port number. The default value is 9292. Setting this option on the component will use the component configured value as default.";
    public static final String CAMEL_SOURCE_WEBSOCKET_PATH_PORT_DEFAULT = "9292";
    public static final String CAMEL_SOURCE_WEBSOCKET_PATH_RESOURCE_URI_CONF = "camel.source.path.resourceUri";
    public static final String CAMEL_SOURCE_WEBSOCKET_PATH_RESOURCE_URI_DOC = "Name of the websocket channel to use";
    public static final String CAMEL_SOURCE_WEBSOCKET_PATH_RESOURCE_URI_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MAX_BINARY_MESSAGE_SIZE_CONF = "camel.source.endpoint.maxBinaryMessageSize";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MAX_BINARY_MESSAGE_SIZE_DOC = "Can be used to set the size in bytes that the websocket created by the websocketServlet may be accept before closing. (Default is -1 - or unlimited)";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MAX_BINARY_MESSAGE_SIZE_DEFAULT = "-1";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF = "camel.source.endpoint.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_WEBSOCKET_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_SESSION_SUPPORT_CONF = "camel.source.endpoint.sessionSupport";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_SESSION_SUPPORT_DOC = "Whether to enable session support which enables HttpSession for each http request.";
    public static final Boolean CAMEL_SOURCE_WEBSOCKET_ENDPOINT_SESSION_SUPPORT_DEFAULT = false;
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_STATIC_RESOURCES_CONF = "camel.source.endpoint.staticResources";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_STATIC_RESOURCES_DOC = "Set a resource path for static resources (such as .html files etc). The resources can be loaded from classpath, if you prefix with classpath:, otherwise the resources is loaded from file system or from JAR files. For example to load from root classpath use classpath:., or classpath:WEB-INF/static If not configured (eg null) then no static resource is in use.";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_STATIC_RESOURCES_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_EXCEPTION_HANDLER_CONF = "camel.source.endpoint.exceptionHandler";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_EXCEPTION_HANDLER_DOC = "To let the consumer use a custom ExceptionHandler. Notice if the option bridgeErrorHandler is enabled then this option is not in use. By default the consumer will deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_EXCEPTION_HANDLER_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_EXCHANGE_PATTERN_CONF = "camel.source.endpoint.exchangePattern";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_EXCHANGE_PATTERN_DOC = "Sets the exchange pattern when the consumer creates an exchange. One of: [InOnly] [InOut] [InOptionalOut]";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_EXCHANGE_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_BUFFER_SIZE_CONF = "camel.source.endpoint.bufferSize";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_BUFFER_SIZE_DOC = "Set the buffer size of the websocketServlet, which is also the max frame byte size (default 8192)";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_BUFFER_SIZE_DEFAULT = "8192";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MAX_IDLE_TIME_CONF = "camel.source.endpoint.maxIdleTime";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MAX_IDLE_TIME_DOC = "Set the time in ms that the websocket created by the websocketServlet may be idle before closing. (default is 300000)";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MAX_IDLE_TIME_DEFAULT = "300000";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MAX_TEXT_MESSAGE_SIZE_CONF = "camel.source.endpoint.maxTextMessageSize";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MAX_TEXT_MESSAGE_SIZE_DOC = "Can be used to set the size in characters that the websocket created by the websocketServlet may be accept before closing.";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MAX_TEXT_MESSAGE_SIZE_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MIN_VERSION_CONF = "camel.source.endpoint.minVersion";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MIN_VERSION_DOC = "Can be used to set the minimum protocol version accepted for the websocketServlet. (Default 13 - the RFC6455 version)";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MIN_VERSION_DEFAULT = "13";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_SYNCHRONOUS_CONF = "camel.source.endpoint.synchronous";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SOURCE_WEBSOCKET_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_ALLOWED_ORIGINS_CONF = "camel.source.endpoint.allowedOrigins";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_ALLOWED_ORIGINS_DOC = "The CORS allowed origins. Use to allow all.";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_ALLOWED_ORIGINS_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_CROSS_ORIGIN_FILTER_ON_CONF = "camel.source.endpoint.crossOriginFilterOn";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_CROSS_ORIGIN_FILTER_ON_DOC = "Whether to enable CORS";
    public static final Boolean CAMEL_SOURCE_WEBSOCKET_ENDPOINT_CROSS_ORIGIN_FILTER_ON_DEFAULT = false;
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_FILTER_PATH_CONF = "camel.source.endpoint.filterPath";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_FILTER_PATH_DOC = "Context path for filtering CORS";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_FILTER_PATH_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_ENABLE_JMX_CONF = "camel.source.endpoint.enableJmx";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_ENABLE_JMX_DOC = "If this option is true, Jetty JMX support will be enabled for this endpoint. See Jetty JMX support for more details.";
    public static final Boolean CAMEL_SOURCE_WEBSOCKET_ENDPOINT_ENABLE_JMX_DEFAULT = false;
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF = "camel.source.endpoint.sslContextParameters";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC = "To configure security using SSLContextParameters";
    public static final String CAMEL_SOURCE_WEBSOCKET_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_HOST_CONF = "camel.component.websocket.host";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_HOST_DOC = "The hostname. The default value is 0.0.0.0";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_HOST_DEFAULT = "0.0.0.0";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_PORT_CONF = "camel.component.websocket.port";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_PORT_DOC = "The port number. The default value is 9292";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_PORT_DEFAULT = "9292";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_BRIDGE_ERROR_HANDLER_CONF = "camel.component.websocket.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_WEBSOCKET_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_STATIC_RESOURCES_CONF = "camel.component.websocket.staticResources";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_STATIC_RESOURCES_DOC = "Set a resource path for static resources (such as .html files etc). The resources can be loaded from classpath, if you prefix with classpath:, otherwise the resources is loaded from file system or from JAR files. For example to load from root classpath use classpath:., or classpath:WEB-INF/static If not configured (eg null) then no static resource is in use.";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_STATIC_RESOURCES_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.websocket.autowiredEnabled";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SOURCE_WEBSOCKET_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_ENABLE_JMX_CONF = "camel.component.websocket.enableJmx";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_ENABLE_JMX_DOC = "If this option is true, Jetty JMX support will be enabled for this endpoint. See Jetty JMX support for more details.";
    public static final Boolean CAMEL_SOURCE_WEBSOCKET_COMPONENT_ENABLE_JMX_DEFAULT = false;
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_MAX_THREADS_CONF = "camel.component.websocket.maxThreads";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_MAX_THREADS_DOC = "To set a value for maximum number of threads in server thread pool. MaxThreads/minThreads or threadPool fields are required due to switch to Jetty9. The default values for maxThreads is 1 2 noCores.";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_MAX_THREADS_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_MIN_THREADS_CONF = "camel.component.websocket.minThreads";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_MIN_THREADS_DOC = "To set a value for minimum number of threads in server thread pool. MaxThreads/minThreads or threadPool fields are required due to switch to Jetty9. The default values for minThreads is 1.";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_MIN_THREADS_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_THREAD_POOL_CONF = "camel.component.websocket.threadPool";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_THREAD_POOL_DOC = "To use a custom thread pool for the server. MaxThreads/minThreads or threadPool fields are required due to switch to Jetty9.";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_THREAD_POOL_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.websocket.sslContextParameters";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_CONTEXT_PARAMETERS_DOC = "To configure security using SSLContextParameters";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_CONTEXT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_KEY_PASSWORD_CONF = "camel.component.websocket.sslKeyPassword";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_KEY_PASSWORD_DOC = "The password for the keystore when using SSL.";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_KEY_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_KEYSTORE_CONF = "camel.component.websocket.sslKeystore";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_KEYSTORE_DOC = "The path to the keystore.";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_KEYSTORE_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_PASSWORD_CONF = "camel.component.websocket.sslPassword";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_PASSWORD_DOC = "The password when using SSL.";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.websocket.useGlobalSslContextParameters";
    public static final String CAMEL_SOURCE_WEBSOCKET_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC = "Enable usage of global SSL context parameters.";
    public static final Boolean CAMEL_SOURCE_WEBSOCKET_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT = false;

    public CamelWebsocketSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelWebsocketSourceConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_WEBSOCKET_PATH_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_PATH_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_PATH_HOST_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_PATH_PORT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_PATH_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_PATH_PORT_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_PATH_RESOURCE_URI_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_PATH_RESOURCE_URI_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_WEBSOCKET_PATH_RESOURCE_URI_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MAX_BINARY_MESSAGE_SIZE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MAX_BINARY_MESSAGE_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MAX_BINARY_MESSAGE_SIZE_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_ENDPOINT_SESSION_SUPPORT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_SESSION_SUPPORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_SESSION_SUPPORT_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_ENDPOINT_STATIC_RESOURCES_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_STATIC_RESOURCES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_STATIC_RESOURCES_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_ENDPOINT_EXCEPTION_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_EXCEPTION_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_EXCEPTION_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_ENDPOINT_EXCHANGE_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_EXCHANGE_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_EXCHANGE_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_ENDPOINT_BUFFER_SIZE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_BUFFER_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_BUFFER_SIZE_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MAX_IDLE_TIME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MAX_IDLE_TIME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MAX_IDLE_TIME_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MAX_TEXT_MESSAGE_SIZE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MAX_TEXT_MESSAGE_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MAX_TEXT_MESSAGE_SIZE_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MIN_VERSION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MIN_VERSION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_MIN_VERSION_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_ENDPOINT_ALLOWED_ORIGINS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_ALLOWED_ORIGINS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_ALLOWED_ORIGINS_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_ENDPOINT_CROSS_ORIGIN_FILTER_ON_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_CROSS_ORIGIN_FILTER_ON_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_CROSS_ORIGIN_FILTER_ON_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_ENDPOINT_FILTER_PATH_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_FILTER_PATH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_FILTER_PATH_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_ENDPOINT_ENABLE_JMX_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_ENABLE_JMX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_ENABLE_JMX_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_COMPONENT_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_COMPONENT_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_COMPONENT_HOST_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_COMPONENT_PORT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_COMPONENT_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_COMPONENT_PORT_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_COMPONENT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_WEBSOCKET_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_COMPONENT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_COMPONENT_STATIC_RESOURCES_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_COMPONENT_STATIC_RESOURCES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_COMPONENT_STATIC_RESOURCES_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_WEBSOCKET_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_COMPONENT_AUTOWIRED_ENABLED_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_COMPONENT_ENABLE_JMX_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_WEBSOCKET_COMPONENT_ENABLE_JMX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_COMPONENT_ENABLE_JMX_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_COMPONENT_MAX_THREADS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_COMPONENT_MAX_THREADS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_COMPONENT_MAX_THREADS_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_COMPONENT_MIN_THREADS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_COMPONENT_MIN_THREADS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_COMPONENT_MIN_THREADS_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_COMPONENT_THREAD_POOL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_COMPONENT_THREAD_POOL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_COMPONENT_THREAD_POOL_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_CONTEXT_PARAMETERS_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_KEY_PASSWORD_CONF, ConfigDef.Type.PASSWORD, CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_KEY_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_KEY_PASSWORD_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_KEYSTORE_CONF, ConfigDef.Type.PASSWORD, CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_KEYSTORE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_KEYSTORE_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_PASSWORD_CONF, ConfigDef.Type.PASSWORD, CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_COMPONENT_SSL_PASSWORD_DOC);
        conf.define(CAMEL_SOURCE_WEBSOCKET_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_WEBSOCKET_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBSOCKET_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC);
        return conf;
    }
}