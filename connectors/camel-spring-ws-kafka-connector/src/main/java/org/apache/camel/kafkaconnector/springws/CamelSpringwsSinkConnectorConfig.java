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
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelSpringwsSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_SPRINGWS_PATH_WEB_SERVICE_ENDPOINT_URI_CONF = "camel.sink.path.webServiceEndpointUri";
    public static final String CAMEL_SINK_SPRINGWS_PATH_WEB_SERVICE_ENDPOINT_URI_DOC = "The default Web Service endpoint uri to use for the producer.";
    public static final String CAMEL_SINK_SPRINGWS_PATH_WEB_SERVICE_ENDPOINT_URI_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_FILTER_CONF = "camel.sink.endpoint.messageFilter";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_FILTER_DOC = "Option to provide a custom MessageFilter. For example when you want to process your headers or attachments by your own.";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_FILTER_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_ID_STRATEGY_CONF = "camel.sink.endpoint.messageIdStrategy";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_ID_STRATEGY_DOC = "Option to provide a custom MessageIdStrategy to control generation of WS-Addressing unique message ids.";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_ID_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_ALLOW_RESPONSE_ATTACHMENT_OVERRIDE_CONF = "camel.sink.endpoint.allowResponseAttachmentOverride";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_ALLOW_RESPONSE_ATTACHMENT_OVERRIDE_DOC = "Option to override soap response attachments in in/out exchange with attachments from the actual service layer. If the invoked service appends or rewrites the soap attachments this option when set to true, allows the modified soap attachments to be overwritten in in/out message attachments";
    public static final Boolean CAMEL_SINK_SPRINGWS_ENDPOINT_ALLOW_RESPONSE_ATTACHMENT_OVERRIDE_DEFAULT = false;
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_ALLOW_RESPONSE_HEADER_OVERRIDE_CONF = "camel.sink.endpoint.allowResponseHeaderOverride";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_ALLOW_RESPONSE_HEADER_OVERRIDE_DOC = "Option to override soap response header in in/out exchange with header info from the actual service layer. If the invoked service appends or rewrites the soap header this option when set to true, allows the modified soap header to be overwritten in in/out message headers";
    public static final Boolean CAMEL_SINK_SPRINGWS_ENDPOINT_ALLOW_RESPONSE_HEADER_OVERRIDE_DEFAULT = false;
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_FAULT_ACTION_CONF = "camel.sink.endpoint.faultAction";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_FAULT_ACTION_DOC = "Signifies the value for the faultAction response WS-Addressing Fault Action header that is provided by the method. See org.springframework.ws.soap.addressing.server.annotation.Action annotation for more details.";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_FAULT_ACTION_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_FAULT_TO_CONF = "camel.sink.endpoint.faultTo";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_FAULT_TO_DOC = "Signifies the value for the faultAction response WS-Addressing FaultTo header that is provided by the method. See org.springframework.ws.soap.addressing.server.annotation.Action annotation for more details.";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_FAULT_TO_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_SPRINGWS_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_FACTORY_CONF = "camel.sink.endpoint.messageFactory";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_FACTORY_DOC = "Option to provide a custom WebServiceMessageFactory. For example when you want Apache Axiom to handle web service messages instead of SAAJ.";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_FACTORY_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_SENDER_CONF = "camel.sink.endpoint.messageSender";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_SENDER_DOC = "Option to provide a custom WebServiceMessageSender. For example to perform authentication or use alternative transports";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_SENDER_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_OUTPUT_ACTION_CONF = "camel.sink.endpoint.outputAction";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_OUTPUT_ACTION_DOC = "Signifies the value for the response WS-Addressing Action header that is provided by the method. See org.springframework.ws.soap.addressing.server.annotation.Action annotation for more details.";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_OUTPUT_ACTION_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_REPLY_TO_CONF = "camel.sink.endpoint.replyTo";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_REPLY_TO_DOC = "Signifies the value for the replyTo response WS-Addressing ReplyTo header that is provided by the method. See org.springframework.ws.soap.addressing.server.annotation.Action annotation for more details.";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_REPLY_TO_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_SOAP_ACTION_CONF = "camel.sink.endpoint.soapAction";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_SOAP_ACTION_DOC = "SOAP action to include inside a SOAP request when accessing remote web services";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_SOAP_ACTION_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_TIMEOUT_CONF = "camel.sink.endpoint.timeout";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_TIMEOUT_DOC = "Sets the socket read timeout (in milliseconds) while invoking a webservice using the producer, see URLConnection.setReadTimeout() and CommonsHttpMessageSender.setReadTimeout(). This option works when using the built-in message sender implementations: CommonsHttpMessageSender and HttpUrlConnectionMessageSender. One of these implementations will be used by default for HTTP based services unless you customize the Spring WS configuration options supplied to the component. If you are using a non-standard sender, it is assumed that you will handle your own timeout configuration. The built-in message sender HttpComponentsMessageSender is considered instead of CommonsHttpMessageSender which has been deprecated, see HttpComponentsMessageSender.setReadTimeout().";
    public static final Integer CAMEL_SINK_SPRINGWS_ENDPOINT_TIMEOUT_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_WEB_SERVICE_TEMPLATE_CONF = "camel.sink.endpoint.webServiceTemplate";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_WEB_SERVICE_TEMPLATE_DOC = "Option to provide a custom WebServiceTemplate. This allows for full control over client-side web services handling; like adding a custom interceptor or specifying a fault resolver, message sender or message factory.";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_WEB_SERVICE_TEMPLATE_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_WS_ADDRESSING_ACTION_CONF = "camel.sink.endpoint.wsAddressingAction";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_WS_ADDRESSING_ACTION_DOC = "WS-Addressing 1.0 action header to include when accessing web services. The To header is set to the address of the web service as specified in the endpoint URI (default Spring-WS behavior).";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_WS_ADDRESSING_ACTION_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SINK_SPRINGWS_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SINK_SPRINGWS_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF = "camel.sink.endpoint.sslContextParameters";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC = "To configure security using SSLContextParameters";
    public static final String CAMEL_SINK_SPRINGWS_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGWS_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.spring-ws.lazyStartProducer";
    public static final String CAMEL_SINK_SPRINGWS_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_SPRINGWS_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_SPRINGWS_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.spring-ws.basicPropertyBinding";
    public static final String CAMEL_SINK_SPRINGWS_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SINK_SPRINGWS_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_SPRINGWS_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.spring-ws.useGlobalSslContextParameters";
    public static final String CAMEL_SINK_SPRINGWS_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC = "Enable usage of global SSL context parameters.";
    public static final Boolean CAMEL_SINK_SPRINGWS_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT = false;

    public CamelSpringwsSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelSpringwsSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_SPRINGWS_PATH_WEB_SERVICE_ENDPOINT_URI_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGWS_PATH_WEB_SERVICE_ENDPOINT_URI_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_PATH_WEB_SERVICE_ENDPOINT_URI_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_FILTER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_FILTER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_FILTER_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_ID_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_ID_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_ID_STRATEGY_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_ENDPOINT_ALLOW_RESPONSE_ATTACHMENT_OVERRIDE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPRINGWS_ENDPOINT_ALLOW_RESPONSE_ATTACHMENT_OVERRIDE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_ENDPOINT_ALLOW_RESPONSE_ATTACHMENT_OVERRIDE_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_ENDPOINT_ALLOW_RESPONSE_HEADER_OVERRIDE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPRINGWS_ENDPOINT_ALLOW_RESPONSE_HEADER_OVERRIDE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_ENDPOINT_ALLOW_RESPONSE_HEADER_OVERRIDE_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_ENDPOINT_FAULT_ACTION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGWS_ENDPOINT_FAULT_ACTION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_ENDPOINT_FAULT_ACTION_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_ENDPOINT_FAULT_TO_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGWS_ENDPOINT_FAULT_TO_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_ENDPOINT_FAULT_TO_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPRINGWS_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_FACTORY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_FACTORY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_FACTORY_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_SENDER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_SENDER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_ENDPOINT_MESSAGE_SENDER_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_ENDPOINT_OUTPUT_ACTION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGWS_ENDPOINT_OUTPUT_ACTION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_ENDPOINT_OUTPUT_ACTION_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_ENDPOINT_REPLY_TO_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGWS_ENDPOINT_REPLY_TO_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_ENDPOINT_REPLY_TO_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_ENDPOINT_SOAP_ACTION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGWS_ENDPOINT_SOAP_ACTION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_ENDPOINT_SOAP_ACTION_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_ENDPOINT_TIMEOUT_CONF, ConfigDef.Type.INT, CAMEL_SINK_SPRINGWS_ENDPOINT_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_ENDPOINT_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_ENDPOINT_WEB_SERVICE_TEMPLATE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGWS_ENDPOINT_WEB_SERVICE_TEMPLATE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_ENDPOINT_WEB_SERVICE_TEMPLATE_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_ENDPOINT_WS_ADDRESSING_ACTION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGWS_ENDPOINT_WS_ADDRESSING_ACTION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_ENDPOINT_WS_ADDRESSING_ACTION_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPRINGWS_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPRINGWS_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGWS_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPRINGWS_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPRINGWS_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.LOW, CAMEL_SINK_SPRINGWS_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_SPRINGWS_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPRINGWS_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGWS_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC);
        return conf;
    }
}