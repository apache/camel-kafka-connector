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
package org.apache.camel.kafkaconnector.cometd;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelCometdSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_COMETD_PATH_HOST_CONF = "camel.sink.path.host";
    private static final String CAMEL_SINK_COMETD_PATH_HOST_DOC = "Hostname";
    private static final String CAMEL_SINK_COMETD_PATH_HOST_DEFAULT = null;
    public static final String CAMEL_SINK_COMETD_PATH_PORT_CONF = "camel.sink.path.port";
    private static final String CAMEL_SINK_COMETD_PATH_PORT_DOC = "Host port number";
    private static final Integer CAMEL_SINK_COMETD_PATH_PORT_DEFAULT = null;
    public static final String CAMEL_SINK_COMETD_PATH_CHANNEL_NAME_CONF = "camel.sink.path.channelName";
    private static final String CAMEL_SINK_COMETD_PATH_CHANNEL_NAME_DOC = "The channelName represents a topic that can be subscribed to by the Camel endpoints.";
    private static final String CAMEL_SINK_COMETD_PATH_CHANNEL_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_COMETD_ENDPOINT_ALLOWED_ORIGINS_CONF = "camel.sink.endpoint.allowedOrigins";
    private static final String CAMEL_SINK_COMETD_ENDPOINT_ALLOWED_ORIGINS_DOC = "The origins domain that support to cross, if the crosssOriginFilterOn is true";
    private static final String CAMEL_SINK_COMETD_ENDPOINT_ALLOWED_ORIGINS_DEFAULT = "*";
    public static final String CAMEL_SINK_COMETD_ENDPOINT_BASE_RESOURCE_CONF = "camel.sink.endpoint.baseResource";
    private static final String CAMEL_SINK_COMETD_ENDPOINT_BASE_RESOURCE_DOC = "The root directory for the web resources or classpath. Use the protocol file: or classpath: depending if you want that the component loads the resource from file system or classpath. Classpath is required for OSGI deployment where the resources are packaged in the jar";
    private static final String CAMEL_SINK_COMETD_ENDPOINT_BASE_RESOURCE_DEFAULT = null;
    public static final String CAMEL_SINK_COMETD_ENDPOINT_CROSS_ORIGIN_FILTER_ON_CONF = "camel.sink.endpoint.crossOriginFilterOn";
    private static final String CAMEL_SINK_COMETD_ENDPOINT_CROSS_ORIGIN_FILTER_ON_DOC = "If true, the server will support for cross-domain filtering";
    private static final Boolean CAMEL_SINK_COMETD_ENDPOINT_CROSS_ORIGIN_FILTER_ON_DEFAULT = false;
    public static final String CAMEL_SINK_COMETD_ENDPOINT_FILTER_PATH_CONF = "camel.sink.endpoint.filterPath";
    private static final String CAMEL_SINK_COMETD_ENDPOINT_FILTER_PATH_DOC = "The filterPath will be used by the CrossOriginFilter, if the crosssOriginFilterOn is true";
    private static final String CAMEL_SINK_COMETD_ENDPOINT_FILTER_PATH_DEFAULT = null;
    public static final String CAMEL_SINK_COMETD_ENDPOINT_INTERVAL_CONF = "camel.sink.endpoint.interval";
    private static final String CAMEL_SINK_COMETD_ENDPOINT_INTERVAL_DOC = "The client side poll timeout in milliseconds. How long a client will wait between reconnects";
    private static final Integer CAMEL_SINK_COMETD_ENDPOINT_INTERVAL_DEFAULT = null;
    public static final String CAMEL_SINK_COMETD_ENDPOINT_JSON_COMMENTED_CONF = "camel.sink.endpoint.jsonCommented";
    private static final String CAMEL_SINK_COMETD_ENDPOINT_JSON_COMMENTED_DOC = "If true, the server will accept JSON wrapped in a comment and will generate JSON wrapped in a comment. This is a defence against Ajax Hijacking.";
    private static final Boolean CAMEL_SINK_COMETD_ENDPOINT_JSON_COMMENTED_DEFAULT = true;
    public static final String CAMEL_SINK_COMETD_ENDPOINT_LOG_LEVEL_CONF = "camel.sink.endpoint.logLevel";
    private static final String CAMEL_SINK_COMETD_ENDPOINT_LOG_LEVEL_DOC = "Logging level. 0=none, 1=info, 2=debug. One of: [0] [1] [2]";
    private static final Integer CAMEL_SINK_COMETD_ENDPOINT_LOG_LEVEL_DEFAULT = 1;
    public static final String CAMEL_SINK_COMETD_ENDPOINT_MAX_INTERVAL_CONF = "camel.sink.endpoint.maxInterval";
    private static final String CAMEL_SINK_COMETD_ENDPOINT_MAX_INTERVAL_DOC = "The max client side poll timeout in milliseconds. A client will be removed if a connection is not received in this time.";
    private static final Integer CAMEL_SINK_COMETD_ENDPOINT_MAX_INTERVAL_DEFAULT = 30000;
    public static final String CAMEL_SINK_COMETD_ENDPOINT_MULTI_FRAME_INTERVAL_CONF = "camel.sink.endpoint.multiFrameInterval";
    private static final String CAMEL_SINK_COMETD_ENDPOINT_MULTI_FRAME_INTERVAL_DOC = "The client side poll timeout, if multiple connections are detected from the same browser.";
    private static final Integer CAMEL_SINK_COMETD_ENDPOINT_MULTI_FRAME_INTERVAL_DEFAULT = 1500;
    public static final String CAMEL_SINK_COMETD_ENDPOINT_TIMEOUT_CONF = "camel.sink.endpoint.timeout";
    private static final String CAMEL_SINK_COMETD_ENDPOINT_TIMEOUT_DOC = "The server side poll timeout in milliseconds. This is how long the server will hold a reconnect request before responding.";
    private static final Integer CAMEL_SINK_COMETD_ENDPOINT_TIMEOUT_DEFAULT = 240000;
    public static final String CAMEL_SINK_COMETD_ENDPOINT_DISCONNECT_LOCAL_SESSION_CONF = "camel.sink.endpoint.disconnectLocalSession";
    private static final String CAMEL_SINK_COMETD_ENDPOINT_DISCONNECT_LOCAL_SESSION_DOC = "Whether to disconnect local sessions after publishing a message to its channel. Disconnecting local session is needed as they are not swept by default by CometD, and therefore you can run out of memory.";
    private static final Boolean CAMEL_SINK_COMETD_ENDPOINT_DISCONNECT_LOCAL_SESSION_DEFAULT = false;
    public static final String CAMEL_SINK_COMETD_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    private static final String CAMEL_SINK_COMETD_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_COMETD_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_COMETD_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    private static final String CAMEL_SINK_COMETD_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_COMETD_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_COMETD_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    private static final String CAMEL_SINK_COMETD_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SINK_COMETD_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_COMETD_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.cometd.lazyStartProducer";
    private static final String CAMEL_SINK_COMETD_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_COMETD_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_COMETD_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.cometd.basicPropertyBinding";
    private static final String CAMEL_SINK_COMETD_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_COMETD_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_COMETD_COMPONENT_EXTENSIONS_CONF = "camel.component.cometd.extensions";
    private static final String CAMEL_SINK_COMETD_COMPONENT_EXTENSIONS_DOC = "To use a list of custom BayeuxServer.Extension that allows modifying incoming and outgoing requests.";
    private static final String CAMEL_SINK_COMETD_COMPONENT_EXTENSIONS_DEFAULT = null;
    public static final String CAMEL_SINK_COMETD_COMPONENT_SECURITY_POLICY_CONF = "camel.component.cometd.securityPolicy";
    private static final String CAMEL_SINK_COMETD_COMPONENT_SECURITY_POLICY_DOC = "To use a custom configured SecurityPolicy to control authorization";
    private static final String CAMEL_SINK_COMETD_COMPONENT_SECURITY_POLICY_DEFAULT = null;
    public static final String CAMEL_SINK_COMETD_COMPONENT_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.cometd.sslContextParameters";
    private static final String CAMEL_SINK_COMETD_COMPONENT_SSL_CONTEXT_PARAMETERS_DOC = "To configure security using SSLContextParameters";
    private static final String CAMEL_SINK_COMETD_COMPONENT_SSL_CONTEXT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SINK_COMETD_COMPONENT_SSL_KEY_PASSWORD_CONF = "camel.component.cometd.sslKeyPassword";
    private static final String CAMEL_SINK_COMETD_COMPONENT_SSL_KEY_PASSWORD_DOC = "The password for the keystore when using SSL.";
    private static final String CAMEL_SINK_COMETD_COMPONENT_SSL_KEY_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SINK_COMETD_COMPONENT_SSL_KEYSTORE_CONF = "camel.component.cometd.sslKeystore";
    private static final String CAMEL_SINK_COMETD_COMPONENT_SSL_KEYSTORE_DOC = "The path to the keystore.";
    private static final String CAMEL_SINK_COMETD_COMPONENT_SSL_KEYSTORE_DEFAULT = null;
    public static final String CAMEL_SINK_COMETD_COMPONENT_SSL_PASSWORD_CONF = "camel.component.cometd.sslPassword";
    private static final String CAMEL_SINK_COMETD_COMPONENT_SSL_PASSWORD_DOC = "The password when using SSL.";
    private static final String CAMEL_SINK_COMETD_COMPONENT_SSL_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SINK_COMETD_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.cometd.useGlobalSslContextParameters";
    private static final String CAMEL_SINK_COMETD_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC = "Enable usage of global SSL context parameters.";
    private static final Boolean CAMEL_SINK_COMETD_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT = false;

    public CamelCometdSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelCometdSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_COMETD_PATH_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_COMETD_PATH_HOST_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_COMETD_PATH_HOST_DOC);
        conf.define(CAMEL_SINK_COMETD_PATH_PORT_CONF, ConfigDef.Type.INT, CAMEL_SINK_COMETD_PATH_PORT_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_COMETD_PATH_PORT_DOC);
        conf.define(CAMEL_SINK_COMETD_PATH_CHANNEL_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_COMETD_PATH_CHANNEL_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_COMETD_PATH_CHANNEL_NAME_DOC);
        conf.define(CAMEL_SINK_COMETD_ENDPOINT_ALLOWED_ORIGINS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_COMETD_ENDPOINT_ALLOWED_ORIGINS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_ENDPOINT_ALLOWED_ORIGINS_DOC);
        conf.define(CAMEL_SINK_COMETD_ENDPOINT_BASE_RESOURCE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_COMETD_ENDPOINT_BASE_RESOURCE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_ENDPOINT_BASE_RESOURCE_DOC);
        conf.define(CAMEL_SINK_COMETD_ENDPOINT_CROSS_ORIGIN_FILTER_ON_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_COMETD_ENDPOINT_CROSS_ORIGIN_FILTER_ON_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_ENDPOINT_CROSS_ORIGIN_FILTER_ON_DOC);
        conf.define(CAMEL_SINK_COMETD_ENDPOINT_FILTER_PATH_CONF, ConfigDef.Type.STRING, CAMEL_SINK_COMETD_ENDPOINT_FILTER_PATH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_ENDPOINT_FILTER_PATH_DOC);
        conf.define(CAMEL_SINK_COMETD_ENDPOINT_INTERVAL_CONF, ConfigDef.Type.INT, CAMEL_SINK_COMETD_ENDPOINT_INTERVAL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_ENDPOINT_INTERVAL_DOC);
        conf.define(CAMEL_SINK_COMETD_ENDPOINT_JSON_COMMENTED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_COMETD_ENDPOINT_JSON_COMMENTED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_ENDPOINT_JSON_COMMENTED_DOC);
        conf.define(CAMEL_SINK_COMETD_ENDPOINT_LOG_LEVEL_CONF, ConfigDef.Type.INT, CAMEL_SINK_COMETD_ENDPOINT_LOG_LEVEL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_ENDPOINT_LOG_LEVEL_DOC);
        conf.define(CAMEL_SINK_COMETD_ENDPOINT_MAX_INTERVAL_CONF, ConfigDef.Type.INT, CAMEL_SINK_COMETD_ENDPOINT_MAX_INTERVAL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_ENDPOINT_MAX_INTERVAL_DOC);
        conf.define(CAMEL_SINK_COMETD_ENDPOINT_MULTI_FRAME_INTERVAL_CONF, ConfigDef.Type.INT, CAMEL_SINK_COMETD_ENDPOINT_MULTI_FRAME_INTERVAL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_ENDPOINT_MULTI_FRAME_INTERVAL_DOC);
        conf.define(CAMEL_SINK_COMETD_ENDPOINT_TIMEOUT_CONF, ConfigDef.Type.INT, CAMEL_SINK_COMETD_ENDPOINT_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_ENDPOINT_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_COMETD_ENDPOINT_DISCONNECT_LOCAL_SESSION_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_COMETD_ENDPOINT_DISCONNECT_LOCAL_SESSION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_ENDPOINT_DISCONNECT_LOCAL_SESSION_DOC);
        conf.define(CAMEL_SINK_COMETD_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_COMETD_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_COMETD_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_COMETD_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_COMETD_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_COMETD_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_COMETD_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_COMETD_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_COMETD_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_COMETD_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_COMETD_COMPONENT_EXTENSIONS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_COMETD_COMPONENT_EXTENSIONS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_COMPONENT_EXTENSIONS_DOC);
        conf.define(CAMEL_SINK_COMETD_COMPONENT_SECURITY_POLICY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_COMETD_COMPONENT_SECURITY_POLICY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_COMPONENT_SECURITY_POLICY_DOC);
        conf.define(CAMEL_SINK_COMETD_COMPONENT_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_COMETD_COMPONENT_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_COMPONENT_SSL_CONTEXT_PARAMETERS_DOC);
        conf.define(CAMEL_SINK_COMETD_COMPONENT_SSL_KEY_PASSWORD_CONF, ConfigDef.Type.STRING, CAMEL_SINK_COMETD_COMPONENT_SSL_KEY_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_COMPONENT_SSL_KEY_PASSWORD_DOC);
        conf.define(CAMEL_SINK_COMETD_COMPONENT_SSL_KEYSTORE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_COMETD_COMPONENT_SSL_KEYSTORE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_COMPONENT_SSL_KEYSTORE_DOC);
        conf.define(CAMEL_SINK_COMETD_COMPONENT_SSL_PASSWORD_CONF, ConfigDef.Type.STRING, CAMEL_SINK_COMETD_COMPONENT_SSL_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_COMPONENT_SSL_PASSWORD_DOC);
        conf.define(CAMEL_SINK_COMETD_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_COMETD_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COMETD_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC);
        return conf;
    }
}