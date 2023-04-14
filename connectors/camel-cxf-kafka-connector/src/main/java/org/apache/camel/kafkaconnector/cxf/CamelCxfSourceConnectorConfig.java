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
package org.apache.camel.kafkaconnector.cxf;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelCxfSourceConnectorConfig extends CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_CXF_PATH_BEAN_ID_CONF = "camel.source.path.beanId";
    public static final String CAMEL_SOURCE_CXF_PATH_BEAN_ID_DOC = "To lookup an existing configured CxfEndpoint. Must used bean: as prefix.";
    public static final String CAMEL_SOURCE_CXF_PATH_BEAN_ID_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_PATH_ADDRESS_CONF = "camel.source.path.address";
    public static final String CAMEL_SOURCE_CXF_PATH_ADDRESS_DOC = "The service publish address.";
    public static final String CAMEL_SOURCE_CXF_PATH_ADDRESS_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_DATA_FORMAT_CONF = "camel.source.endpoint.dataFormat";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_DATA_FORMAT_DOC = "The data type messages supported by the CXF endpoint. One of: [PAYLOAD] [RAW] [MESSAGE] [CXF_MESSAGE] [POJO]";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_DATA_FORMAT_DEFAULT = "POJO";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_WRAPPED_STYLE_CONF = "camel.source.endpoint.wrappedStyle";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_WRAPPED_STYLE_DOC = "The WSDL style that describes how parameters are represented in the SOAP body. If the value is false, CXF will chose the document-literal unwrapped style, If the value is true, CXF will chose the document-literal wrapped style";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_WRAPPED_STYLE_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF = "camel.source.endpoint.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_CXF_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_EXCEPTION_HANDLER_CONF = "camel.source.endpoint.exceptionHandler";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_EXCEPTION_HANDLER_DOC = "To let the consumer use a custom ExceptionHandler. Notice if the option bridgeErrorHandler is enabled then this option is not in use. By default the consumer will deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_EXCEPTION_HANDLER_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_EXCHANGE_PATTERN_CONF = "camel.source.endpoint.exchangePattern";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_EXCHANGE_PATTERN_DOC = "Sets the exchange pattern when the consumer creates an exchange. One of: [InOnly] [InOut] [InOptionalOut]";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_EXCHANGE_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_ALLOW_STREAMING_CONF = "camel.source.endpoint.allowStreaming";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_ALLOW_STREAMING_DOC = "This option controls whether the CXF component, when running in PAYLOAD mode, will DOM parse the incoming messages into DOM Elements or keep the payload as a javax.xml.transform.Source object that would allow streaming in some cases.";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_ALLOW_STREAMING_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_BUS_CONF = "camel.source.endpoint.bus";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_BUS_DOC = "To use a custom configured CXF Bus.";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_BUS_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_CONTINUATION_TIMEOUT_CONF = "camel.source.endpoint.continuationTimeout";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_CONTINUATION_TIMEOUT_DOC = "This option is used to set the CXF continuation timeout which could be used in CxfConsumer by default when the CXF server is using Jetty or Servlet transport.";
    public static final Long CAMEL_SOURCE_CXF_ENDPOINT_CONTINUATION_TIMEOUT_DEFAULT = 30000L;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_CXF_BINDING_CONF = "camel.source.endpoint.cxfBinding";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_CXF_BINDING_DOC = "To use a custom CxfBinding to control the binding between Camel Message and CXF Message.";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_CXF_BINDING_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_CXF_CONFIGURER_CONF = "camel.source.endpoint.cxfConfigurer";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_CXF_CONFIGURER_DOC = "This option could apply the implementation of org.apache.camel.component.cxf.CxfEndpointConfigurer which supports to configure the CXF endpoint in programmatic way. User can configure the CXF server and client by implementing configure{ServerClient} method of CxfEndpointConfigurer.";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_CXF_CONFIGURER_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_DEFAULT_BUS_CONF = "camel.source.endpoint.defaultBus";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_DEFAULT_BUS_DOC = "Will set the default bus when CXF endpoint create a bus by itself";
    public static final Boolean CAMEL_SOURCE_CXF_ENDPOINT_DEFAULT_BUS_DEFAULT = false;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_HEADER_FILTER_STRATEGY_CONF = "camel.source.endpoint.headerFilterStrategy";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_HEADER_FILTER_STRATEGY_DOC = "To use a custom HeaderFilterStrategy to filter header to and from Camel message.";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_HEADER_FILTER_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_MERGE_PROTOCOL_HEADERS_CONF = "camel.source.endpoint.mergeProtocolHeaders";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_MERGE_PROTOCOL_HEADERS_DOC = "Whether to merge protocol headers. If enabled then propagating headers between Camel and CXF becomes more consistent and similar. For more details see CAMEL-6393.";
    public static final Boolean CAMEL_SOURCE_CXF_ENDPOINT_MERGE_PROTOCOL_HEADERS_DEFAULT = false;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_MTOM_ENABLED_CONF = "camel.source.endpoint.mtomEnabled";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_MTOM_ENABLED_DOC = "To enable MTOM (attachments). This requires to use POJO or PAYLOAD data format mode.";
    public static final Boolean CAMEL_SOURCE_CXF_ENDPOINT_MTOM_ENABLED_DEFAULT = false;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_PROPERTIES_CONF = "camel.source.endpoint.properties";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_PROPERTIES_DOC = "To set additional CXF options using the key/value pairs from the Map. For example to turn on stacktraces in SOAP faults, properties.faultStackTraceEnabled=true";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_PROPERTIES_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_SCHEMA_VALIDATION_ENABLED_CONF = "camel.source.endpoint.schemaValidationEnabled";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_SCHEMA_VALIDATION_ENABLED_DOC = "Enable schema validation for request and response. Disabled by default for performance reason";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_SCHEMA_VALIDATION_ENABLED_DEFAULT = "false";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_SKIP_PAYLOAD_MESSAGE_PART_CHECK_CONF = "camel.source.endpoint.skipPayloadMessagePartCheck";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_SKIP_PAYLOAD_MESSAGE_PART_CHECK_DOC = "Sets whether SOAP message validation should be disabled.";
    public static final Boolean CAMEL_SOURCE_CXF_ENDPOINT_SKIP_PAYLOAD_MESSAGE_PART_CHECK_DEFAULT = false;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_LOGGING_FEATURE_ENABLED_CONF = "camel.source.endpoint.loggingFeatureEnabled";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_LOGGING_FEATURE_ENABLED_DOC = "This option enables CXF Logging Feature which writes inbound and outbound SOAP messages to log.";
    public static final Boolean CAMEL_SOURCE_CXF_ENDPOINT_LOGGING_FEATURE_ENABLED_DEFAULT = false;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_LOGGING_SIZE_LIMIT_CONF = "camel.source.endpoint.loggingSizeLimit";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_LOGGING_SIZE_LIMIT_DOC = "To limit the total size of number of bytes the logger will output when logging feature has been enabled and -1 for no limit.";
    public static final Integer CAMEL_SOURCE_CXF_ENDPOINT_LOGGING_SIZE_LIMIT_DEFAULT = 49152;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_SKIP_FAULT_LOGGING_CONF = "camel.source.endpoint.skipFaultLogging";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_SKIP_FAULT_LOGGING_DOC = "This option controls whether the PhaseInterceptorChain skips logging the Fault that it catches.";
    public static final Boolean CAMEL_SOURCE_CXF_ENDPOINT_SKIP_FAULT_LOGGING_DEFAULT = false;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_PASSWORD_CONF = "camel.source.endpoint.password";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_PASSWORD_DOC = "This option is used to set the basic authentication information of password for the CXF client.";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_USERNAME_CONF = "camel.source.endpoint.username";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_USERNAME_DOC = "This option is used to set the basic authentication information of username for the CXF client.";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_USERNAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_BINDING_ID_CONF = "camel.source.endpoint.bindingId";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_BINDING_ID_DOC = "The bindingId for the service model to use.";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_BINDING_ID_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_PORT_NAME_CONF = "camel.source.endpoint.portName";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_PORT_NAME_DOC = "The endpoint name this service is implementing, it maps to the wsdl:portname. In the format of ns:PORT_NAME where ns is a namespace prefix valid at this scope.";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_PORT_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_PUBLISHED_ENDPOINT_URL_CONF = "camel.source.endpoint.publishedEndpointUrl";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_PUBLISHED_ENDPOINT_URL_DOC = "This option can override the endpointUrl that published from the WSDL which can be accessed with service address url plus wsd";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_PUBLISHED_ENDPOINT_URL_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_SERVICE_CLASS_CONF = "camel.source.endpoint.serviceClass";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_SERVICE_CLASS_DOC = "The class name of the SEI (Service Endpoint Interface) class which could have JSR181 annotation or not.";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_SERVICE_CLASS_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_SERVICE_NAME_CONF = "camel.source.endpoint.serviceName";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_SERVICE_NAME_DOC = "The service name this service is implementing, it maps to the wsdl:servicename.";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_SERVICE_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_WSDL_URLCONF = "camel.source.endpoint.wsdlURL";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_WSDL_URLDOC = "The location of the WSDL. Can be on the classpath, file system, or be hosted remotely.";
    public static final String CAMEL_SOURCE_CXF_ENDPOINT_WSDL_URLDEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_COMPONENT_BRIDGE_ERROR_HANDLER_CONF = "camel.component.cxf.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_CXF_COMPONENT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_CXF_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_CXF_COMPONENT_ALLOW_STREAMING_CONF = "camel.component.cxf.allowStreaming";
    public static final String CAMEL_SOURCE_CXF_COMPONENT_ALLOW_STREAMING_DOC = "This option controls whether the CXF component, when running in PAYLOAD mode, will DOM parse the incoming messages into DOM Elements or keep the payload as a javax.xml.transform.Source object that would allow streaming in some cases.";
    public static final String CAMEL_SOURCE_CXF_COMPONENT_ALLOW_STREAMING_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.cxf.autowiredEnabled";
    public static final String CAMEL_SOURCE_CXF_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SOURCE_CXF_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;
    public static final String CAMEL_SOURCE_CXF_COMPONENT_HEADER_FILTER_STRATEGY_CONF = "camel.component.cxf.headerFilterStrategy";
    public static final String CAMEL_SOURCE_CXF_COMPONENT_HEADER_FILTER_STRATEGY_DOC = "To use a custom org.apache.camel.spi.HeaderFilterStrategy to filter header to and from Camel message.";
    public static final String CAMEL_SOURCE_CXF_COMPONENT_HEADER_FILTER_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SOURCE_CXF_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.cxf.useGlobalSslContextParameters";
    public static final String CAMEL_SOURCE_CXF_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC = "Enable usage of global SSL context parameters.";
    public static final Boolean CAMEL_SOURCE_CXF_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT = false;

    public CamelCxfSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelCxfSourceConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_CXF_PATH_BEAN_ID_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_PATH_BEAN_ID_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_PATH_BEAN_ID_DOC);
        conf.define(CAMEL_SOURCE_CXF_PATH_ADDRESS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_PATH_ADDRESS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_PATH_ADDRESS_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_DATA_FORMAT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_ENDPOINT_DATA_FORMAT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_DATA_FORMAT_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_WRAPPED_STYLE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_ENDPOINT_WRAPPED_STYLE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_WRAPPED_STYLE_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_CXF_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_EXCEPTION_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_ENDPOINT_EXCEPTION_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_EXCEPTION_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_EXCHANGE_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_ENDPOINT_EXCHANGE_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_EXCHANGE_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_ALLOW_STREAMING_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_ENDPOINT_ALLOW_STREAMING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_ALLOW_STREAMING_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_BUS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_ENDPOINT_BUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_BUS_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_CONTINUATION_TIMEOUT_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_CXF_ENDPOINT_CONTINUATION_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_CONTINUATION_TIMEOUT_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_CXF_BINDING_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_ENDPOINT_CXF_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_CXF_BINDING_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_CXF_CONFIGURER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_ENDPOINT_CXF_CONFIGURER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_CXF_CONFIGURER_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_DEFAULT_BUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_CXF_ENDPOINT_DEFAULT_BUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_DEFAULT_BUS_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_HEADER_FILTER_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_ENDPOINT_HEADER_FILTER_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_HEADER_FILTER_STRATEGY_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_MERGE_PROTOCOL_HEADERS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_CXF_ENDPOINT_MERGE_PROTOCOL_HEADERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_MERGE_PROTOCOL_HEADERS_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_MTOM_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_CXF_ENDPOINT_MTOM_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_MTOM_ENABLED_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_PROPERTIES_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_ENDPOINT_PROPERTIES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_PROPERTIES_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_SCHEMA_VALIDATION_ENABLED_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_ENDPOINT_SCHEMA_VALIDATION_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_SCHEMA_VALIDATION_ENABLED_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_SKIP_PAYLOAD_MESSAGE_PART_CHECK_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_CXF_ENDPOINT_SKIP_PAYLOAD_MESSAGE_PART_CHECK_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_SKIP_PAYLOAD_MESSAGE_PART_CHECK_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_LOGGING_FEATURE_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_CXF_ENDPOINT_LOGGING_FEATURE_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_LOGGING_FEATURE_ENABLED_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_LOGGING_SIZE_LIMIT_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_CXF_ENDPOINT_LOGGING_SIZE_LIMIT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_LOGGING_SIZE_LIMIT_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_SKIP_FAULT_LOGGING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_CXF_ENDPOINT_SKIP_FAULT_LOGGING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_SKIP_FAULT_LOGGING_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_PASSWORD_CONF, ConfigDef.Type.PASSWORD, CAMEL_SOURCE_CXF_ENDPOINT_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_PASSWORD_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_USERNAME_CONF, ConfigDef.Type.PASSWORD, CAMEL_SOURCE_CXF_ENDPOINT_USERNAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_USERNAME_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_BINDING_ID_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_ENDPOINT_BINDING_ID_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_BINDING_ID_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_PORT_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_ENDPOINT_PORT_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_PORT_NAME_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_PUBLISHED_ENDPOINT_URL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_ENDPOINT_PUBLISHED_ENDPOINT_URL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_PUBLISHED_ENDPOINT_URL_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_SERVICE_CLASS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_ENDPOINT_SERVICE_CLASS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_SERVICE_CLASS_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_SERVICE_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_ENDPOINT_SERVICE_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_SERVICE_NAME_DOC);
        conf.define(CAMEL_SOURCE_CXF_ENDPOINT_WSDL_URLCONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_ENDPOINT_WSDL_URLDEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_ENDPOINT_WSDL_URLDOC);
        conf.define(CAMEL_SOURCE_CXF_COMPONENT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_CXF_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_COMPONENT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_CXF_COMPONENT_ALLOW_STREAMING_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_COMPONENT_ALLOW_STREAMING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_COMPONENT_ALLOW_STREAMING_DOC);
        conf.define(CAMEL_SOURCE_CXF_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_CXF_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_COMPONENT_AUTOWIRED_ENABLED_DOC);
        conf.define(CAMEL_SOURCE_CXF_COMPONENT_HEADER_FILTER_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_CXF_COMPONENT_HEADER_FILTER_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_COMPONENT_HEADER_FILTER_STRATEGY_DOC);
        conf.define(CAMEL_SOURCE_CXF_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_CXF_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_CXF_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC);
        return conf;
    }
}