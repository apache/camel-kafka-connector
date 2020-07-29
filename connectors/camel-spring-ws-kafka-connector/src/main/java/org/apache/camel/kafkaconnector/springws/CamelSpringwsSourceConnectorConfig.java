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
package org.apache.camel.kafkaconnector.springws;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelSpringwsSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_SPRINGWS_PATH_TYPE_CONF = "camel.source.path.type";
    private static final String CAMEL_SOURCE_SPRINGWS_PATH_TYPE_DOC = "Endpoint mapping type if endpoint mapping is used. rootqname - Offers the option to map web service requests based on the qualified name of the root element contained in the message. soapaction - Used to map web service requests based on the SOAP action specified in the header of the message. uri - In order to map web service requests that target a specific URI. xpathresult - Used to map web service requests based on the evaluation of an XPath expression against the incoming message. The result of the evaluation should match the XPath result specified in the endpoint URI. beanname - Allows you to reference an org.apache.camel.component.spring.ws.bean.CamelEndpointDispatcher object in order to integrate with existing (legacy) endpoint mappings like PayloadRootQNameEndpointMapping, SoapActionEndpointMapping, etc One of: [ROOT_QNAME] [ACTION] [TO] [SOAP_ACTION] [XPATHRESULT] [URI] [URI_PATH] [BEANNAME]";
    private static final String CAMEL_SOURCE_SPRINGWS_PATH_TYPE_DEFAULT = null;
    public static final String CAMEL_SOURCE_SPRINGWS_PATH_LOOKUP_KEY_CONF = "camel.source.path.lookupKey";
    private static final String CAMEL_SOURCE_SPRINGWS_PATH_LOOKUP_KEY_DOC = "Endpoint mapping key if endpoint mapping is used";
    private static final String CAMEL_SOURCE_SPRINGWS_PATH_LOOKUP_KEY_DEFAULT = null;
    public static final String CAMEL_SOURCE_SPRINGWS_PATH_EXPRESSION_CONF = "camel.source.path.expression";
    private static final String CAMEL_SOURCE_SPRINGWS_PATH_EXPRESSION_DOC = "The XPath expression to use when option type=xpathresult. Then this option is required to be configured.";
    private static final String CAMEL_SOURCE_SPRINGWS_PATH_EXPRESSION_DEFAULT = null;
    public static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_MESSAGE_FILTER_CONF = "camel.source.endpoint.messageFilter";
    private static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_MESSAGE_FILTER_DOC = "Option to provide a custom MessageFilter. For example when you want to process your headers or attachments by your own.";
    private static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_MESSAGE_FILTER_DEFAULT = null;
    public static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF = "camel.source.endpoint.bridgeErrorHandler";
    private static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final Boolean CAMEL_SOURCE_SPRINGWS_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_ENDPOINT_DISPATCHER_CONF = "camel.source.endpoint.endpointDispatcher";
    private static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_ENDPOINT_DISPATCHER_DOC = "Spring org.springframework.ws.server.endpoint.MessageEndpoint for dispatching messages received by Spring-WS to a Camel endpoint, to integrate with existing (legacy) endpoint mappings like PayloadRootQNameEndpointMapping, SoapActionEndpointMapping, etc.";
    private static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_ENDPOINT_DISPATCHER_DEFAULT = null;
    public static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_ENDPOINT_MAPPING_CONF = "camel.source.endpoint.endpointMapping";
    private static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_ENDPOINT_MAPPING_DOC = "Reference to an instance of org.apache.camel.component.spring.ws.bean.CamelEndpointMapping in the Registry/ApplicationContext. Only one bean is required in the registry to serve all Camel/Spring-WS endpoints. This bean is auto-discovered by the MessageDispatcher and used to map requests to Camel endpoints based on characteristics specified on the endpoint (like root QName, SOAP action, etc)";
    private static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_ENDPOINT_MAPPING_DEFAULT = null;
    public static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_EXCEPTION_HANDLER_CONF = "camel.source.endpoint.exceptionHandler";
    private static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_EXCEPTION_HANDLER_DOC = "To let the consumer use a custom ExceptionHandler. Notice if the option bridgeErrorHandler is enabled then this option is not in use. By default the consumer will deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_EXCEPTION_HANDLER_DEFAULT = null;
    public static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_EXCHANGE_PATTERN_CONF = "camel.source.endpoint.exchangePattern";
    private static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_EXCHANGE_PATTERN_DOC = "Sets the exchange pattern when the consumer creates an exchange. One of: [InOnly] [InOut] [InOptionalOut]";
    private static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_EXCHANGE_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.source.endpoint.basicPropertyBinding";
    private static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SOURCE_SPRINGWS_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_SYNCHRONOUS_CONF = "camel.source.endpoint.synchronous";
    private static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SOURCE_SPRINGWS_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF = "camel.source.endpoint.sslContextParameters";
    private static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC = "To configure security using SSLContextParameters";
    private static final String CAMEL_SOURCE_SPRINGWS_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SOURCE_SPRINGWS_COMPONENT_BRIDGE_ERROR_HANDLER_CONF = "camel.component.spring-ws.bridgeErrorHandler";
    private static final String CAMEL_SOURCE_SPRINGWS_COMPONENT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final Boolean CAMEL_SOURCE_SPRINGWS_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_SPRINGWS_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.spring-ws.basicPropertyBinding";
    private static final String CAMEL_SOURCE_SPRINGWS_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SOURCE_SPRINGWS_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_SPRINGWS_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.spring-ws.useGlobalSslContextParameters";
    private static final String CAMEL_SOURCE_SPRINGWS_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC = "Enable usage of global SSL context parameters.";
    private static final Boolean CAMEL_SOURCE_SPRINGWS_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT = false;

    public CamelSpringwsSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelSpringwsSourceConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_SPRINGWS_PATH_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SPRINGWS_PATH_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SPRINGWS_PATH_TYPE_DOC);
        conf.define(CAMEL_SOURCE_SPRINGWS_PATH_LOOKUP_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SPRINGWS_PATH_LOOKUP_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SPRINGWS_PATH_LOOKUP_KEY_DOC);
        conf.define(CAMEL_SOURCE_SPRINGWS_PATH_EXPRESSION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SPRINGWS_PATH_EXPRESSION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SPRINGWS_PATH_EXPRESSION_DOC);
        conf.define(CAMEL_SOURCE_SPRINGWS_ENDPOINT_MESSAGE_FILTER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SPRINGWS_ENDPOINT_MESSAGE_FILTER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SPRINGWS_ENDPOINT_MESSAGE_FILTER_DOC);
        conf.define(CAMEL_SOURCE_SPRINGWS_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_SPRINGWS_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SPRINGWS_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_SPRINGWS_ENDPOINT_ENDPOINT_DISPATCHER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SPRINGWS_ENDPOINT_ENDPOINT_DISPATCHER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SPRINGWS_ENDPOINT_ENDPOINT_DISPATCHER_DOC);
        conf.define(CAMEL_SOURCE_SPRINGWS_ENDPOINT_ENDPOINT_MAPPING_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SPRINGWS_ENDPOINT_ENDPOINT_MAPPING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SPRINGWS_ENDPOINT_ENDPOINT_MAPPING_DOC);
        conf.define(CAMEL_SOURCE_SPRINGWS_ENDPOINT_EXCEPTION_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SPRINGWS_ENDPOINT_EXCEPTION_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SPRINGWS_ENDPOINT_EXCEPTION_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_SPRINGWS_ENDPOINT_EXCHANGE_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SPRINGWS_ENDPOINT_EXCHANGE_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SPRINGWS_ENDPOINT_EXCHANGE_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_SPRINGWS_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_SPRINGWS_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SPRINGWS_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_SPRINGWS_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_SPRINGWS_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SPRINGWS_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SOURCE_SPRINGWS_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SPRINGWS_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SPRINGWS_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC);
        conf.define(CAMEL_SOURCE_SPRINGWS_COMPONENT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_SPRINGWS_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SPRINGWS_COMPONENT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_SPRINGWS_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_SPRINGWS_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SPRINGWS_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_SPRINGWS_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_SPRINGWS_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SPRINGWS_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC);
        return conf;
    }
}