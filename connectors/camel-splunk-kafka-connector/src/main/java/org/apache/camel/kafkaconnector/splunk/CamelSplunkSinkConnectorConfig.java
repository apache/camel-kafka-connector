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
package org.apache.camel.kafkaconnector.splunk;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelSplunkSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_SPLUNK_PATH_NAME_CONF = "camel.sink.path.name";
    public static final String CAMEL_SINK_SPLUNK_PATH_NAME_DOC = "Name has no purpose";
    public static final String CAMEL_SINK_SPLUNK_PATH_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_APP_CONF = "camel.sink.endpoint.app";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_APP_DOC = "Splunk app";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_APP_DEFAULT = null;
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_CONNECTION_TIMEOUT_CONF = "camel.sink.endpoint.connectionTimeout";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_CONNECTION_TIMEOUT_DOC = "Timeout in MS when connecting to Splunk server";
    public static final Integer CAMEL_SINK_SPLUNK_ENDPOINT_CONNECTION_TIMEOUT_DEFAULT = 5000;
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_HOST_CONF = "camel.sink.endpoint.host";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_HOST_DOC = "Splunk host.";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_HOST_DEFAULT = "localhost";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_OWNER_CONF = "camel.sink.endpoint.owner";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_OWNER_DOC = "Splunk owner";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_OWNER_DEFAULT = null;
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_PORT_CONF = "camel.sink.endpoint.port";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_PORT_DOC = "Splunk port";
    public static final Integer CAMEL_SINK_SPLUNK_ENDPOINT_PORT_DEFAULT = 8089;
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_SCHEME_CONF = "camel.sink.endpoint.scheme";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_SCHEME_DOC = "Splunk scheme";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_SCHEME_DEFAULT = "https";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_EVENT_HOST_CONF = "camel.sink.endpoint.eventHost";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_EVENT_HOST_DOC = "Override the default Splunk event host field";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_EVENT_HOST_DEFAULT = null;
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_INDEX_CONF = "camel.sink.endpoint.index";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_INDEX_DOC = "Splunk index to write to";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_INDEX_DEFAULT = null;
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_SPLUNK_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_RAW_CONF = "camel.sink.endpoint.raw";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_RAW_DOC = "Should the payload be inserted raw";
    public static final Boolean CAMEL_SINK_SPLUNK_ENDPOINT_RAW_DEFAULT = false;
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_SOURCE_CONF = "camel.sink.endpoint.source";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_SOURCE_DOC = "Splunk source argument";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_SOURCE_DEFAULT = null;
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_SOURCE_TYPE_CONF = "camel.sink.endpoint.sourceType";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_SOURCE_TYPE_DOC = "Splunk sourcetype argument";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_SOURCE_TYPE_DEFAULT = null;
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_TCP_RECEIVER_LOCAL_PORT_CONF = "camel.sink.endpoint.tcpReceiverLocalPort";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_TCP_RECEIVER_LOCAL_PORT_DOC = "Splunk tcp receiver port defined locally on splunk server. (For example if splunk port 9997 is mapped to 12345, tcpReceiverLocalPort has to be 9997)";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_TCP_RECEIVER_LOCAL_PORT_DEFAULT = null;
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_TCP_RECEIVER_PORT_CONF = "camel.sink.endpoint.tcpReceiverPort";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_TCP_RECEIVER_PORT_DOC = "Splunk tcp receiver port";
    public static final Integer CAMEL_SINK_SPLUNK_ENDPOINT_TCP_RECEIVER_PORT_DEFAULT = null;
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_PASSWORD_CONF = "camel.sink.endpoint.password";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_PASSWORD_DOC = "Password for Splunk";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_SSL_PROTOCOL_CONF = "camel.sink.endpoint.sslProtocol";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_SSL_PROTOCOL_DOC = "Set the ssl protocol to use One of: [TLSv1.2] [TLSv1.1] [TLSv1] [SSLv3]";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_SSL_PROTOCOL_DEFAULT = "TLSv1.2";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_USERNAME_CONF = "camel.sink.endpoint.username";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_USERNAME_DOC = "Username for Splunk";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_USERNAME_DEFAULT = null;
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_USE_SUN_HTTPS_HANDLER_CONF = "camel.sink.endpoint.useSunHttpsHandler";
    public static final String CAMEL_SINK_SPLUNK_ENDPOINT_USE_SUN_HTTPS_HANDLER_DOC = "Use sun.net.www.protocol.https.Handler Https handler to establish the Splunk Connection. Can be useful when running in application servers to avoid app. server https handling.";
    public static final Boolean CAMEL_SINK_SPLUNK_ENDPOINT_USE_SUN_HTTPS_HANDLER_DEFAULT = false;
    public static final String CAMEL_SINK_SPLUNK_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.splunk.lazyStartProducer";
    public static final String CAMEL_SINK_SPLUNK_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_SPLUNK_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_SPLUNK_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.splunk.autowiredEnabled";
    public static final String CAMEL_SINK_SPLUNK_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SINK_SPLUNK_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;
    public static final String CAMEL_SINK_SPLUNK_COMPONENT_SPLUNK_CONFIGURATION_FACTORY_CONF = "camel.component.splunk.splunkConfigurationFactory";
    public static final String CAMEL_SINK_SPLUNK_COMPONENT_SPLUNK_CONFIGURATION_FACTORY_DOC = "To use the SplunkConfigurationFactory";
    public static final String CAMEL_SINK_SPLUNK_COMPONENT_SPLUNK_CONFIGURATION_FACTORY_DEFAULT = null;

    public CamelSplunkSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelSplunkSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_SPLUNK_PATH_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPLUNK_PATH_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_SPLUNK_PATH_NAME_DOC);
        conf.define(CAMEL_SINK_SPLUNK_ENDPOINT_APP_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPLUNK_ENDPOINT_APP_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_ENDPOINT_APP_DOC);
        conf.define(CAMEL_SINK_SPLUNK_ENDPOINT_CONNECTION_TIMEOUT_CONF, ConfigDef.Type.INT, CAMEL_SINK_SPLUNK_ENDPOINT_CONNECTION_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_ENDPOINT_CONNECTION_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_SPLUNK_ENDPOINT_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPLUNK_ENDPOINT_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_ENDPOINT_HOST_DOC);
        conf.define(CAMEL_SINK_SPLUNK_ENDPOINT_OWNER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPLUNK_ENDPOINT_OWNER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_ENDPOINT_OWNER_DOC);
        conf.define(CAMEL_SINK_SPLUNK_ENDPOINT_PORT_CONF, ConfigDef.Type.INT, CAMEL_SINK_SPLUNK_ENDPOINT_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_ENDPOINT_PORT_DOC);
        conf.define(CAMEL_SINK_SPLUNK_ENDPOINT_SCHEME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPLUNK_ENDPOINT_SCHEME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_ENDPOINT_SCHEME_DOC);
        conf.define(CAMEL_SINK_SPLUNK_ENDPOINT_EVENT_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPLUNK_ENDPOINT_EVENT_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_ENDPOINT_EVENT_HOST_DOC);
        conf.define(CAMEL_SINK_SPLUNK_ENDPOINT_INDEX_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPLUNK_ENDPOINT_INDEX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_ENDPOINT_INDEX_DOC);
        conf.define(CAMEL_SINK_SPLUNK_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPLUNK_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_SPLUNK_ENDPOINT_RAW_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPLUNK_ENDPOINT_RAW_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_ENDPOINT_RAW_DOC);
        conf.define(CAMEL_SINK_SPLUNK_ENDPOINT_SOURCE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPLUNK_ENDPOINT_SOURCE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_ENDPOINT_SOURCE_DOC);
        conf.define(CAMEL_SINK_SPLUNK_ENDPOINT_SOURCE_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPLUNK_ENDPOINT_SOURCE_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_ENDPOINT_SOURCE_TYPE_DOC);
        conf.define(CAMEL_SINK_SPLUNK_ENDPOINT_TCP_RECEIVER_LOCAL_PORT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPLUNK_ENDPOINT_TCP_RECEIVER_LOCAL_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_ENDPOINT_TCP_RECEIVER_LOCAL_PORT_DOC);
        conf.define(CAMEL_SINK_SPLUNK_ENDPOINT_TCP_RECEIVER_PORT_CONF, ConfigDef.Type.INT, CAMEL_SINK_SPLUNK_ENDPOINT_TCP_RECEIVER_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_ENDPOINT_TCP_RECEIVER_PORT_DOC);
        conf.define(CAMEL_SINK_SPLUNK_ENDPOINT_PASSWORD_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_SPLUNK_ENDPOINT_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_ENDPOINT_PASSWORD_DOC);
        conf.define(CAMEL_SINK_SPLUNK_ENDPOINT_SSL_PROTOCOL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPLUNK_ENDPOINT_SSL_PROTOCOL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_ENDPOINT_SSL_PROTOCOL_DOC);
        conf.define(CAMEL_SINK_SPLUNK_ENDPOINT_USERNAME_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_SPLUNK_ENDPOINT_USERNAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_ENDPOINT_USERNAME_DOC);
        conf.define(CAMEL_SINK_SPLUNK_ENDPOINT_USE_SUN_HTTPS_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPLUNK_ENDPOINT_USE_SUN_HTTPS_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_ENDPOINT_USE_SUN_HTTPS_HANDLER_DOC);
        conf.define(CAMEL_SINK_SPLUNK_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPLUNK_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_SPLUNK_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPLUNK_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_COMPONENT_AUTOWIRED_ENABLED_DOC);
        conf.define(CAMEL_SINK_SPLUNK_COMPONENT_SPLUNK_CONFIGURATION_FACTORY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPLUNK_COMPONENT_SPLUNK_CONFIGURATION_FACTORY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPLUNK_COMPONENT_SPLUNK_CONFIGURATION_FACTORY_DOC);
        return conf;
    }
}