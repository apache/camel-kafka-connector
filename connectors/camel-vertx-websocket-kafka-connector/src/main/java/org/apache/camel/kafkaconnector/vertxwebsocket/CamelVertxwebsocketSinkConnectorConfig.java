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
package org.apache.camel.kafkaconnector.vertxwebsocket;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelVertxwebsocketSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_VERTXWEBSOCKET_PATH_HOST_CONF = "camel.sink.path.host";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_PATH_HOST_DOC = "The host that the consumer should bind to or the host of the remote websocket destination that the producer should connect to";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_PATH_HOST_DEFAULT = "0.0.0.0";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_PATH_PORT_CONF = "camel.sink.path.port";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_PATH_PORT_DOC = "The port that the consumer should bind to or port of the remote websocket destination that the producer should connect to";
    public static final Integer CAMEL_SINK_VERTXWEBSOCKET_PATH_PORT_DEFAULT = 0;
    public static final String CAMEL_SINK_VERTXWEBSOCKET_PATH_PATH_CONF = "camel.sink.path.path";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_PATH_PATH_DOC = "The path that the consumer should bind to or path of the remote websocket destination that the producer should connect to";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_PATH_PATH_DEFAULT = "/";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_CLIENT_OPTIONS_CONF = "camel.sink.endpoint.clientOptions";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_CLIENT_OPTIONS_DOC = "Sets customized options for configuring the WebSocket client used in the producer";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_CLIENT_OPTIONS_DEFAULT = null;
    public static final String CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_CLIENT_SUB_PROTOCOLS_CONF = "camel.sink.endpoint.clientSubProtocols";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_CLIENT_SUB_PROTOCOLS_DOC = "Comma separated list of WebSocket subprotocols that the client should use for the Sec-WebSocket-Protocol header";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_CLIENT_SUB_PROTOCOLS_DEFAULT = null;
    public static final String CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_SEND_TO_ALL_CONF = "camel.sink.endpoint.sendToAll";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_SEND_TO_ALL_DOC = "To send to all websocket subscribers. Can be used to configure on endpoint level, instead of having to use the VertxWebsocketConstants.SEND_TO_ALL header on the message.";
    public static final Boolean CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_SEND_TO_ALL_DEFAULT = false;
    public static final String CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF = "camel.sink.endpoint.sslContextParameters";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC = "To configure security using SSLContextParameters";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.vertx-websocket.lazyStartProducer";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.vertx-websocket.autowiredEnabled";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;
    public static final String CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_ROUTER_CONF = "camel.component.vertx-websocket.router";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_ROUTER_DOC = "To provide a custom vertx router to use on the WebSocket server";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_ROUTER_DEFAULT = null;
    public static final String CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_VERTX_CONF = "camel.component.vertx-websocket.vertx";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_VERTX_DOC = "To use an existing vertx instead of creating a new instance";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_VERTX_DEFAULT = null;
    public static final String CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_VERTX_OPTIONS_CONF = "camel.component.vertx-websocket.vertxOptions";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_VERTX_OPTIONS_DOC = "To provide a custom set of vertx options for configuring vertx";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_VERTX_OPTIONS_DEFAULT = null;
    public static final String CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.vertx-websocket.useGlobalSslContextParameters";
    public static final String CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC = "Enable usage of global SSL context parameters.";
    public static final Boolean CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT = false;

    public CamelVertxwebsocketSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelVertxwebsocketSinkConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_VERTXWEBSOCKET_PATH_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_VERTXWEBSOCKET_PATH_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_VERTXWEBSOCKET_PATH_HOST_DOC);
        conf.define(CAMEL_SINK_VERTXWEBSOCKET_PATH_PORT_CONF, ConfigDef.Type.INT, CAMEL_SINK_VERTXWEBSOCKET_PATH_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_VERTXWEBSOCKET_PATH_PORT_DOC);
        conf.define(CAMEL_SINK_VERTXWEBSOCKET_PATH_PATH_CONF, ConfigDef.Type.STRING, CAMEL_SINK_VERTXWEBSOCKET_PATH_PATH_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_VERTXWEBSOCKET_PATH_PATH_DOC);
        conf.define(CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_CLIENT_OPTIONS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_CLIENT_OPTIONS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_CLIENT_OPTIONS_DOC);
        conf.define(CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_CLIENT_SUB_PROTOCOLS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_CLIENT_SUB_PROTOCOLS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_CLIENT_SUB_PROTOCOLS_DOC);
        conf.define(CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_SEND_TO_ALL_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_SEND_TO_ALL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_SEND_TO_ALL_DOC);
        conf.define(CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_VERTXWEBSOCKET_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC);
        conf.define(CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_AUTOWIRED_ENABLED_DOC);
        conf.define(CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_ROUTER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_ROUTER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_ROUTER_DOC);
        conf.define(CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_VERTX_CONF, ConfigDef.Type.STRING, CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_VERTX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_VERTX_DOC);
        conf.define(CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_VERTX_OPTIONS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_VERTX_OPTIONS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_VERTX_OPTIONS_DOC);
        conf.define(CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_VERTXWEBSOCKET_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC);
        return conf;
    }
}