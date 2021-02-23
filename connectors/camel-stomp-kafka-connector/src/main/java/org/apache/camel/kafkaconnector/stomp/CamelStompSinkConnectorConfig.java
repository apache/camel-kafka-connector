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
package org.apache.camel.kafkaconnector.stomp;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelStompSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_STOMP_PATH_DESTINATION_CONF = "camel.sink.path.destination";
    public static final String CAMEL_SINK_STOMP_PATH_DESTINATION_DOC = "Name of the queue";
    public static final String CAMEL_SINK_STOMP_PATH_DESTINATION_DEFAULT = null;
    public static final String CAMEL_SINK_STOMP_ENDPOINT_BROKER_URLCONF = "camel.sink.endpoint.brokerURL";
    public static final String CAMEL_SINK_STOMP_ENDPOINT_BROKER_URLDOC = "The URI of the Stomp broker to connect to";
    public static final String CAMEL_SINK_STOMP_ENDPOINT_BROKER_URLDEFAULT = "tcp://localhost:61613";
    public static final String CAMEL_SINK_STOMP_ENDPOINT_CUSTOM_HEADERS_CONF = "camel.sink.endpoint.customHeaders";
    public static final String CAMEL_SINK_STOMP_ENDPOINT_CUSTOM_HEADERS_DOC = "To set custom headers";
    public static final String CAMEL_SINK_STOMP_ENDPOINT_CUSTOM_HEADERS_DEFAULT = null;
    public static final String CAMEL_SINK_STOMP_ENDPOINT_HOST_CONF = "camel.sink.endpoint.host";
    public static final String CAMEL_SINK_STOMP_ENDPOINT_HOST_DOC = "The virtual host name";
    public static final String CAMEL_SINK_STOMP_ENDPOINT_HOST_DEFAULT = null;
    public static final String CAMEL_SINK_STOMP_ENDPOINT_VERSION_CONF = "camel.sink.endpoint.version";
    public static final String CAMEL_SINK_STOMP_ENDPOINT_VERSION_DOC = "The stomp version (1.1, or 1.2)";
    public static final String CAMEL_SINK_STOMP_ENDPOINT_VERSION_DEFAULT = null;
    public static final String CAMEL_SINK_STOMP_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_STOMP_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_STOMP_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_STOMP_ENDPOINT_HEADER_FILTER_STRATEGY_CONF = "camel.sink.endpoint.headerFilterStrategy";
    public static final String CAMEL_SINK_STOMP_ENDPOINT_HEADER_FILTER_STRATEGY_DOC = "To use a custom HeaderFilterStrategy to filter header to and from Camel message.";
    public static final String CAMEL_SINK_STOMP_ENDPOINT_HEADER_FILTER_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SINK_STOMP_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    public static final String CAMEL_SINK_STOMP_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SINK_STOMP_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_STOMP_ENDPOINT_LOGIN_CONF = "camel.sink.endpoint.login";
    public static final String CAMEL_SINK_STOMP_ENDPOINT_LOGIN_DOC = "The username";
    public static final String CAMEL_SINK_STOMP_ENDPOINT_LOGIN_DEFAULT = null;
    public static final String CAMEL_SINK_STOMP_ENDPOINT_PASSCODE_CONF = "camel.sink.endpoint.passcode";
    public static final String CAMEL_SINK_STOMP_ENDPOINT_PASSCODE_DOC = "The password";
    public static final String CAMEL_SINK_STOMP_ENDPOINT_PASSCODE_DEFAULT = null;
    public static final String CAMEL_SINK_STOMP_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF = "camel.sink.endpoint.sslContextParameters";
    public static final String CAMEL_SINK_STOMP_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC = "To configure security using SSLContextParameters";
    public static final String CAMEL_SINK_STOMP_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SINK_STOMP_COMPONENT_BROKER_URLCONF = "camel.component.stomp.brokerURL";
    public static final String CAMEL_SINK_STOMP_COMPONENT_BROKER_URLDOC = "The URI of the Stomp broker to connect to";
    public static final String CAMEL_SINK_STOMP_COMPONENT_BROKER_URLDEFAULT = "tcp://localhost:61613";
    public static final String CAMEL_SINK_STOMP_COMPONENT_CUSTOM_HEADERS_CONF = "camel.component.stomp.customHeaders";
    public static final String CAMEL_SINK_STOMP_COMPONENT_CUSTOM_HEADERS_DOC = "To set custom headers";
    public static final String CAMEL_SINK_STOMP_COMPONENT_CUSTOM_HEADERS_DEFAULT = null;
    public static final String CAMEL_SINK_STOMP_COMPONENT_HOST_CONF = "camel.component.stomp.host";
    public static final String CAMEL_SINK_STOMP_COMPONENT_HOST_DOC = "The virtual host name";
    public static final String CAMEL_SINK_STOMP_COMPONENT_HOST_DEFAULT = null;
    public static final String CAMEL_SINK_STOMP_COMPONENT_VERSION_CONF = "camel.component.stomp.version";
    public static final String CAMEL_SINK_STOMP_COMPONENT_VERSION_DOC = "The stomp version (1.1, or 1.2)";
    public static final String CAMEL_SINK_STOMP_COMPONENT_VERSION_DEFAULT = null;
    public static final String CAMEL_SINK_STOMP_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.stomp.lazyStartProducer";
    public static final String CAMEL_SINK_STOMP_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_STOMP_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_STOMP_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.stomp.autowiredEnabled";
    public static final String CAMEL_SINK_STOMP_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SINK_STOMP_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;
    public static final String CAMEL_SINK_STOMP_COMPONENT_CONFIGURATION_CONF = "camel.component.stomp.configuration";
    public static final String CAMEL_SINK_STOMP_COMPONENT_CONFIGURATION_DOC = "Component configuration.";
    public static final String CAMEL_SINK_STOMP_COMPONENT_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SINK_STOMP_COMPONENT_HEADER_FILTER_STRATEGY_CONF = "camel.component.stomp.headerFilterStrategy";
    public static final String CAMEL_SINK_STOMP_COMPONENT_HEADER_FILTER_STRATEGY_DOC = "To use a custom org.apache.camel.spi.HeaderFilterStrategy to filter header to and from Camel message.";
    public static final String CAMEL_SINK_STOMP_COMPONENT_HEADER_FILTER_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SINK_STOMP_COMPONENT_LOGIN_CONF = "camel.component.stomp.login";
    public static final String CAMEL_SINK_STOMP_COMPONENT_LOGIN_DOC = "The username";
    public static final String CAMEL_SINK_STOMP_COMPONENT_LOGIN_DEFAULT = null;
    public static final String CAMEL_SINK_STOMP_COMPONENT_PASSCODE_CONF = "camel.component.stomp.passcode";
    public static final String CAMEL_SINK_STOMP_COMPONENT_PASSCODE_DOC = "The password";
    public static final String CAMEL_SINK_STOMP_COMPONENT_PASSCODE_DEFAULT = null;
    public static final String CAMEL_SINK_STOMP_COMPONENT_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.stomp.sslContextParameters";
    public static final String CAMEL_SINK_STOMP_COMPONENT_SSL_CONTEXT_PARAMETERS_DOC = "To configure security using SSLContextParameters";
    public static final String CAMEL_SINK_STOMP_COMPONENT_SSL_CONTEXT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SINK_STOMP_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.stomp.useGlobalSslContextParameters";
    public static final String CAMEL_SINK_STOMP_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC = "Enable usage of global SSL context parameters.";
    public static final Boolean CAMEL_SINK_STOMP_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT = false;

    public CamelStompSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelStompSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_STOMP_PATH_DESTINATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_STOMP_PATH_DESTINATION_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_STOMP_PATH_DESTINATION_DOC);
        conf.define(CAMEL_SINK_STOMP_ENDPOINT_BROKER_URLCONF, ConfigDef.Type.STRING, CAMEL_SINK_STOMP_ENDPOINT_BROKER_URLDEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_STOMP_ENDPOINT_BROKER_URLDOC);
        conf.define(CAMEL_SINK_STOMP_ENDPOINT_CUSTOM_HEADERS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_STOMP_ENDPOINT_CUSTOM_HEADERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_ENDPOINT_CUSTOM_HEADERS_DOC);
        conf.define(CAMEL_SINK_STOMP_ENDPOINT_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_STOMP_ENDPOINT_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_ENDPOINT_HOST_DOC);
        conf.define(CAMEL_SINK_STOMP_ENDPOINT_VERSION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_STOMP_ENDPOINT_VERSION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_ENDPOINT_VERSION_DOC);
        conf.define(CAMEL_SINK_STOMP_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_STOMP_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_STOMP_ENDPOINT_HEADER_FILTER_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_STOMP_ENDPOINT_HEADER_FILTER_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_ENDPOINT_HEADER_FILTER_STRATEGY_DOC);
        conf.define(CAMEL_SINK_STOMP_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_STOMP_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_STOMP_ENDPOINT_LOGIN_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_STOMP_ENDPOINT_LOGIN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_ENDPOINT_LOGIN_DOC);
        conf.define(CAMEL_SINK_STOMP_ENDPOINT_PASSCODE_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_STOMP_ENDPOINT_PASSCODE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_ENDPOINT_PASSCODE_DOC);
        conf.define(CAMEL_SINK_STOMP_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_STOMP_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC);
        conf.define(CAMEL_SINK_STOMP_COMPONENT_BROKER_URLCONF, ConfigDef.Type.STRING, CAMEL_SINK_STOMP_COMPONENT_BROKER_URLDEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_STOMP_COMPONENT_BROKER_URLDOC);
        conf.define(CAMEL_SINK_STOMP_COMPONENT_CUSTOM_HEADERS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_STOMP_COMPONENT_CUSTOM_HEADERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_COMPONENT_CUSTOM_HEADERS_DOC);
        conf.define(CAMEL_SINK_STOMP_COMPONENT_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_STOMP_COMPONENT_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_COMPONENT_HOST_DOC);
        conf.define(CAMEL_SINK_STOMP_COMPONENT_VERSION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_STOMP_COMPONENT_VERSION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_COMPONENT_VERSION_DOC);
        conf.define(CAMEL_SINK_STOMP_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_STOMP_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_STOMP_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_STOMP_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_COMPONENT_AUTOWIRED_ENABLED_DOC);
        conf.define(CAMEL_SINK_STOMP_COMPONENT_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_STOMP_COMPONENT_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_COMPONENT_CONFIGURATION_DOC);
        conf.define(CAMEL_SINK_STOMP_COMPONENT_HEADER_FILTER_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_STOMP_COMPONENT_HEADER_FILTER_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_COMPONENT_HEADER_FILTER_STRATEGY_DOC);
        conf.define(CAMEL_SINK_STOMP_COMPONENT_LOGIN_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_STOMP_COMPONENT_LOGIN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_COMPONENT_LOGIN_DOC);
        conf.define(CAMEL_SINK_STOMP_COMPONENT_PASSCODE_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_STOMP_COMPONENT_PASSCODE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_COMPONENT_PASSCODE_DOC);
        conf.define(CAMEL_SINK_STOMP_COMPONENT_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_STOMP_COMPONENT_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_COMPONENT_SSL_CONTEXT_PARAMETERS_DOC);
        conf.define(CAMEL_SINK_STOMP_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_STOMP_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_STOMP_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC);
        return conf;
    }
}