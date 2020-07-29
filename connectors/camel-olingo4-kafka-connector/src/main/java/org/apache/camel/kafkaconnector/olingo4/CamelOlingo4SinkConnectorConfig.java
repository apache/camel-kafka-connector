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
package org.apache.camel.kafkaconnector.olingo4;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelOlingo4SinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_OLINGO4_PATH_API_NAME_CONF = "camel.sink.path.apiName";
    private static final String CAMEL_SINK_OLINGO4_PATH_API_NAME_DOC = "What kind of operation to perform One of: [DEFAULT]";
    private static final String CAMEL_SINK_OLINGO4_PATH_API_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_OLINGO4_PATH_METHOD_NAME_CONF = "camel.sink.path.methodName";
    private static final String CAMEL_SINK_OLINGO4_PATH_METHOD_NAME_DOC = "What sub operation to use for the selected operation";
    private static final String CAMEL_SINK_OLINGO4_PATH_METHOD_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_OLINGO4_ENDPOINT_CONNECT_TIMEOUT_CONF = "camel.sink.endpoint.connectTimeout";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_CONNECT_TIMEOUT_DOC = "HTTP connection creation timeout in milliseconds, defaults to 30,000 (30 seconds)";
    private static final Integer CAMEL_SINK_OLINGO4_ENDPOINT_CONNECT_TIMEOUT_DEFAULT = 30000;
    public static final String CAMEL_SINK_OLINGO4_ENDPOINT_CONTENT_TYPE_CONF = "camel.sink.endpoint.contentType";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_CONTENT_TYPE_DOC = "Content-Type header value can be used to specify JSON or XML message format, defaults to application/json;charset=utf-8";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_CONTENT_TYPE_DEFAULT = "application/json;charset=utf-8";
    public static final String CAMEL_SINK_OLINGO4_ENDPOINT_FILTER_ALREADY_SEEN_CONF = "camel.sink.endpoint.filterAlreadySeen";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_FILTER_ALREADY_SEEN_DOC = "Set this to true to filter out results that have already been communicated by this component.";
    private static final Boolean CAMEL_SINK_OLINGO4_ENDPOINT_FILTER_ALREADY_SEEN_DEFAULT = false;
    public static final String CAMEL_SINK_OLINGO4_ENDPOINT_HTTP_HEADERS_CONF = "camel.sink.endpoint.httpHeaders";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_HTTP_HEADERS_DOC = "Custom HTTP headers to inject into every request, this could include OAuth tokens, etc.";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_HTTP_HEADERS_DEFAULT = null;
    public static final String CAMEL_SINK_OLINGO4_ENDPOINT_IN_BODY_CONF = "camel.sink.endpoint.inBody";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_IN_BODY_DOC = "Sets the name of a parameter to be passed in the exchange In Body";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_IN_BODY_DEFAULT = null;
    public static final String CAMEL_SINK_OLINGO4_ENDPOINT_PROXY_CONF = "camel.sink.endpoint.proxy";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_PROXY_DOC = "HTTP proxy server configuration";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_PROXY_DEFAULT = null;
    public static final String CAMEL_SINK_OLINGO4_ENDPOINT_SERVICE_URI_CONF = "camel.sink.endpoint.serviceUri";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_SERVICE_URI_DOC = "Target OData service base URI, e.g. http://services.odata.org/OData/OData.svc";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_SERVICE_URI_DEFAULT = null;
    public static final String CAMEL_SINK_OLINGO4_ENDPOINT_SOCKET_TIMEOUT_CONF = "camel.sink.endpoint.socketTimeout";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_SOCKET_TIMEOUT_DOC = "HTTP request timeout in milliseconds, defaults to 30,000 (30 seconds)";
    private static final Integer CAMEL_SINK_OLINGO4_ENDPOINT_SOCKET_TIMEOUT_DEFAULT = 30000;
    public static final String CAMEL_SINK_OLINGO4_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_OLINGO4_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_OLINGO4_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_OLINGO4_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_OLINGO4_ENDPOINT_HTTP_ASYNC_CLIENT_BUILDER_CONF = "camel.sink.endpoint.httpAsyncClientBuilder";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_HTTP_ASYNC_CLIENT_BUILDER_DOC = "Custom HTTP async client builder for more complex HTTP client configuration, overrides connectionTimeout, socketTimeout, proxy and sslContext. Note that a socketTimeout MUST be specified in the builder, otherwise OData requests could block indefinitely";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_HTTP_ASYNC_CLIENT_BUILDER_DEFAULT = null;
    public static final String CAMEL_SINK_OLINGO4_ENDPOINT_HTTP_CLIENT_BUILDER_CONF = "camel.sink.endpoint.httpClientBuilder";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_HTTP_CLIENT_BUILDER_DOC = "Custom HTTP client builder for more complex HTTP client configuration, overrides connectionTimeout, socketTimeout, proxy and sslContext. Note that a socketTimeout MUST be specified in the builder, otherwise OData requests could block indefinitely";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_HTTP_CLIENT_BUILDER_DEFAULT = null;
    public static final String CAMEL_SINK_OLINGO4_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SINK_OLINGO4_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_OLINGO4_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF = "camel.sink.endpoint.sslContextParameters";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC = "To configure security using SSLContextParameters";
    private static final String CAMEL_SINK_OLINGO4_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SINK_OLINGO4_COMPONENT_CONFIGURATION_CONF = "camel.component.olingo4.configuration";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_CONFIGURATION_DOC = "To use the shared configuration";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SINK_OLINGO4_COMPONENT_CONNECT_TIMEOUT_CONF = "camel.component.olingo4.connectTimeout";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_CONNECT_TIMEOUT_DOC = "HTTP connection creation timeout in milliseconds, defaults to 30,000 (30 seconds)";
    private static final Integer CAMEL_SINK_OLINGO4_COMPONENT_CONNECT_TIMEOUT_DEFAULT = 30000;
    public static final String CAMEL_SINK_OLINGO4_COMPONENT_CONTENT_TYPE_CONF = "camel.component.olingo4.contentType";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_CONTENT_TYPE_DOC = "Content-Type header value can be used to specify JSON or XML message format, defaults to application/json;charset=utf-8";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_CONTENT_TYPE_DEFAULT = "application/json;charset=utf-8";
    public static final String CAMEL_SINK_OLINGO4_COMPONENT_FILTER_ALREADY_SEEN_CONF = "camel.component.olingo4.filterAlreadySeen";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_FILTER_ALREADY_SEEN_DOC = "Set this to true to filter out results that have already been communicated by this component.";
    private static final Boolean CAMEL_SINK_OLINGO4_COMPONENT_FILTER_ALREADY_SEEN_DEFAULT = false;
    public static final String CAMEL_SINK_OLINGO4_COMPONENT_HTTP_HEADERS_CONF = "camel.component.olingo4.httpHeaders";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_HTTP_HEADERS_DOC = "Custom HTTP headers to inject into every request, this could include OAuth tokens, etc.";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_HTTP_HEADERS_DEFAULT = null;
    public static final String CAMEL_SINK_OLINGO4_COMPONENT_PROXY_CONF = "camel.component.olingo4.proxy";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_PROXY_DOC = "HTTP proxy server configuration";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_PROXY_DEFAULT = null;
    public static final String CAMEL_SINK_OLINGO4_COMPONENT_SERVICE_URI_CONF = "camel.component.olingo4.serviceUri";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_SERVICE_URI_DOC = "Target OData service base URI, e.g. http://services.odata.org/OData/OData.svc";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_SERVICE_URI_DEFAULT = null;
    public static final String CAMEL_SINK_OLINGO4_COMPONENT_SOCKET_TIMEOUT_CONF = "camel.component.olingo4.socketTimeout";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_SOCKET_TIMEOUT_DOC = "HTTP request timeout in milliseconds, defaults to 30,000 (30 seconds)";
    private static final Integer CAMEL_SINK_OLINGO4_COMPONENT_SOCKET_TIMEOUT_DEFAULT = 30000;
    public static final String CAMEL_SINK_OLINGO4_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.olingo4.lazyStartProducer";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_OLINGO4_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_OLINGO4_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.olingo4.basicPropertyBinding";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_OLINGO4_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_OLINGO4_COMPONENT_HTTP_ASYNC_CLIENT_BUILDER_CONF = "camel.component.olingo4.httpAsyncClientBuilder";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_HTTP_ASYNC_CLIENT_BUILDER_DOC = "Custom HTTP async client builder for more complex HTTP client configuration, overrides connectionTimeout, socketTimeout, proxy and sslContext. Note that a socketTimeout MUST be specified in the builder, otherwise OData requests could block indefinitely";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_HTTP_ASYNC_CLIENT_BUILDER_DEFAULT = null;
    public static final String CAMEL_SINK_OLINGO4_COMPONENT_HTTP_CLIENT_BUILDER_CONF = "camel.component.olingo4.httpClientBuilder";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_HTTP_CLIENT_BUILDER_DOC = "Custom HTTP client builder for more complex HTTP client configuration, overrides connectionTimeout, socketTimeout, proxy and sslContext. Note that a socketTimeout MUST be specified in the builder, otherwise OData requests could block indefinitely";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_HTTP_CLIENT_BUILDER_DEFAULT = null;
    public static final String CAMEL_SINK_OLINGO4_COMPONENT_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.olingo4.sslContextParameters";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_SSL_CONTEXT_PARAMETERS_DOC = "To configure security using SSLContextParameters";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_SSL_CONTEXT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SINK_OLINGO4_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.olingo4.useGlobalSslContextParameters";
    private static final String CAMEL_SINK_OLINGO4_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC = "Enable usage of global SSL context parameters.";
    private static final Boolean CAMEL_SINK_OLINGO4_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT = false;

    public CamelOlingo4SinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelOlingo4SinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_OLINGO4_PATH_API_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OLINGO4_PATH_API_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_OLINGO4_PATH_API_NAME_DOC);
        conf.define(CAMEL_SINK_OLINGO4_PATH_METHOD_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OLINGO4_PATH_METHOD_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_OLINGO4_PATH_METHOD_NAME_DOC);
        conf.define(CAMEL_SINK_OLINGO4_ENDPOINT_CONNECT_TIMEOUT_CONF, ConfigDef.Type.INT, CAMEL_SINK_OLINGO4_ENDPOINT_CONNECT_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_ENDPOINT_CONNECT_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_OLINGO4_ENDPOINT_CONTENT_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OLINGO4_ENDPOINT_CONTENT_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_ENDPOINT_CONTENT_TYPE_DOC);
        conf.define(CAMEL_SINK_OLINGO4_ENDPOINT_FILTER_ALREADY_SEEN_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_OLINGO4_ENDPOINT_FILTER_ALREADY_SEEN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_ENDPOINT_FILTER_ALREADY_SEEN_DOC);
        conf.define(CAMEL_SINK_OLINGO4_ENDPOINT_HTTP_HEADERS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OLINGO4_ENDPOINT_HTTP_HEADERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_ENDPOINT_HTTP_HEADERS_DOC);
        conf.define(CAMEL_SINK_OLINGO4_ENDPOINT_IN_BODY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OLINGO4_ENDPOINT_IN_BODY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_ENDPOINT_IN_BODY_DOC);
        conf.define(CAMEL_SINK_OLINGO4_ENDPOINT_PROXY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OLINGO4_ENDPOINT_PROXY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_ENDPOINT_PROXY_DOC);
        conf.define(CAMEL_SINK_OLINGO4_ENDPOINT_SERVICE_URI_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OLINGO4_ENDPOINT_SERVICE_URI_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_ENDPOINT_SERVICE_URI_DOC);
        conf.define(CAMEL_SINK_OLINGO4_ENDPOINT_SOCKET_TIMEOUT_CONF, ConfigDef.Type.INT, CAMEL_SINK_OLINGO4_ENDPOINT_SOCKET_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_ENDPOINT_SOCKET_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_OLINGO4_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_OLINGO4_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_OLINGO4_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_OLINGO4_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_OLINGO4_ENDPOINT_HTTP_ASYNC_CLIENT_BUILDER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OLINGO4_ENDPOINT_HTTP_ASYNC_CLIENT_BUILDER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_ENDPOINT_HTTP_ASYNC_CLIENT_BUILDER_DOC);
        conf.define(CAMEL_SINK_OLINGO4_ENDPOINT_HTTP_CLIENT_BUILDER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OLINGO4_ENDPOINT_HTTP_CLIENT_BUILDER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_ENDPOINT_HTTP_CLIENT_BUILDER_DOC);
        conf.define(CAMEL_SINK_OLINGO4_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_OLINGO4_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_OLINGO4_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OLINGO4_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC);
        conf.define(CAMEL_SINK_OLINGO4_COMPONENT_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OLINGO4_COMPONENT_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_COMPONENT_CONFIGURATION_DOC);
        conf.define(CAMEL_SINK_OLINGO4_COMPONENT_CONNECT_TIMEOUT_CONF, ConfigDef.Type.INT, CAMEL_SINK_OLINGO4_COMPONENT_CONNECT_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_COMPONENT_CONNECT_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_OLINGO4_COMPONENT_CONTENT_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OLINGO4_COMPONENT_CONTENT_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_COMPONENT_CONTENT_TYPE_DOC);
        conf.define(CAMEL_SINK_OLINGO4_COMPONENT_FILTER_ALREADY_SEEN_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_OLINGO4_COMPONENT_FILTER_ALREADY_SEEN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_COMPONENT_FILTER_ALREADY_SEEN_DOC);
        conf.define(CAMEL_SINK_OLINGO4_COMPONENT_HTTP_HEADERS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OLINGO4_COMPONENT_HTTP_HEADERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_COMPONENT_HTTP_HEADERS_DOC);
        conf.define(CAMEL_SINK_OLINGO4_COMPONENT_PROXY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OLINGO4_COMPONENT_PROXY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_COMPONENT_PROXY_DOC);
        conf.define(CAMEL_SINK_OLINGO4_COMPONENT_SERVICE_URI_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OLINGO4_COMPONENT_SERVICE_URI_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_COMPONENT_SERVICE_URI_DOC);
        conf.define(CAMEL_SINK_OLINGO4_COMPONENT_SOCKET_TIMEOUT_CONF, ConfigDef.Type.INT, CAMEL_SINK_OLINGO4_COMPONENT_SOCKET_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_COMPONENT_SOCKET_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_OLINGO4_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_OLINGO4_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_OLINGO4_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_OLINGO4_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_OLINGO4_COMPONENT_HTTP_ASYNC_CLIENT_BUILDER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OLINGO4_COMPONENT_HTTP_ASYNC_CLIENT_BUILDER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_COMPONENT_HTTP_ASYNC_CLIENT_BUILDER_DOC);
        conf.define(CAMEL_SINK_OLINGO4_COMPONENT_HTTP_CLIENT_BUILDER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OLINGO4_COMPONENT_HTTP_CLIENT_BUILDER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_COMPONENT_HTTP_CLIENT_BUILDER_DOC);
        conf.define(CAMEL_SINK_OLINGO4_COMPONENT_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OLINGO4_COMPONENT_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_COMPONENT_SSL_CONTEXT_PARAMETERS_DOC);
        conf.define(CAMEL_SINK_OLINGO4_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_OLINGO4_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OLINGO4_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC);
        return conf;
    }
}