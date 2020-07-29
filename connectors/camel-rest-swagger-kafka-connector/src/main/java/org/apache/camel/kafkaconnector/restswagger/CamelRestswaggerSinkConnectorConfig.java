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
package org.apache.camel.kafkaconnector.restswagger;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelRestswaggerSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_RESTSWAGGER_PATH_SPECIFICATION_URI_CONF = "camel.sink.path.specificationUri";
    private static final String CAMEL_SINK_RESTSWAGGER_PATH_SPECIFICATION_URI_DOC = "Path to the Swagger specification file. The scheme, host base path are taken from this specification, but these can be overridden with properties on the component or endpoint level. If not given the component tries to load swagger.json resource from the classpath. Note that the host defined on the component and endpoint of this Component should contain the scheme, hostname and optionally the port in the URI syntax (i.e. http://api.example.com:8080). Overrides component configuration. The Swagger specification can be loaded from different sources by prefixing with file: classpath: http: https:. Support for https is limited to using the JDK installed UrlHandler, and as such it can be cumbersome to setup TLS/SSL certificates for https (such as setting a number of javax.net.ssl JVM system properties). How to do that consult the JDK documentation for UrlHandler. Default value notice: By default loads swagger.json file";
    private static final String CAMEL_SINK_RESTSWAGGER_PATH_SPECIFICATION_URI_DEFAULT = "swagger.json";
    public static final String CAMEL_SINK_RESTSWAGGER_PATH_OPERATION_ID_CONF = "camel.sink.path.operationId";
    private static final String CAMEL_SINK_RESTSWAGGER_PATH_OPERATION_ID_DOC = "ID of the operation from the Swagger specification.";
    private static final String CAMEL_SINK_RESTSWAGGER_PATH_OPERATION_ID_DEFAULT = null;
    public static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_BASE_PATH_CONF = "camel.sink.endpoint.basePath";
    private static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_BASE_PATH_DOC = "API basePath, for example /v2. Default is unset, if set overrides the value present in Swagger specification and in the component configuration.";
    private static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_BASE_PATH_DEFAULT = null;
    public static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_COMPONENT_NAME_CONF = "camel.sink.endpoint.componentName";
    private static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_COMPONENT_NAME_DOC = "Name of the Camel component that will perform the requests. The component must be present in Camel registry and it must implement RestProducerFactory service provider interface. If not set CLASSPATH is searched for single component that implements RestProducerFactory SPI. Overrides component configuration.";
    private static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_COMPONENT_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_CONSUMES_CONF = "camel.sink.endpoint.consumes";
    private static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_CONSUMES_DOC = "What payload type this component capable of consuming. Could be one type, like application/json or multiple types as application/json, application/xml; q=0.5 according to the RFC7231. This equates to the value of Accept HTTP header. If set overrides any value found in the Swagger specification and. in the component configuration";
    private static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_CONSUMES_DEFAULT = null;
    public static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_HOST_CONF = "camel.sink.endpoint.host";
    private static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_HOST_DOC = "Scheme hostname and port to direct the HTTP requests to in the form of https://hostname:port. Can be configured at the endpoint, component or in the corresponding REST configuration in the Camel Context. If you give this component a name (e.g. petstore) that REST configuration is consulted first, rest-swagger next, and global configuration last. If set overrides any value found in the Swagger specification, RestConfiguration. Overrides all other configuration.";
    private static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_HOST_DEFAULT = null;
    public static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    private static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_RESTSWAGGER_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_PRODUCES_CONF = "camel.sink.endpoint.produces";
    private static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_PRODUCES_DOC = "What payload type this component is producing. For example application/json according to the RFC7231. This equates to the value of Content-Type HTTP header. If set overrides any value present in the Swagger specification. Overrides all other configuration.";
    private static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_PRODUCES_DEFAULT = null;
    public static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    private static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_RESTSWAGGER_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    private static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SINK_RESTSWAGGER_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF = "camel.sink.endpoint.sslContextParameters";
    private static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC = "To configure security using SSLContextParameters.";
    private static final String CAMEL_SINK_RESTSWAGGER_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_BASE_PATH_CONF = "camel.component.rest-swagger.basePath";
    private static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_BASE_PATH_DOC = "API basePath, for example /v2. Default is unset, if set overrides the value present in Swagger specification.";
    private static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_BASE_PATH_DEFAULT = null;
    public static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_COMPONENT_NAME_CONF = "camel.component.rest-swagger.componentName";
    private static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_COMPONENT_NAME_DOC = "Name of the Camel component that will perform the requests. The component must be present in Camel registry and it must implement RestProducerFactory service provider interface. If not set CLASSPATH is searched for single component that implements RestProducerFactory SPI. Can be overridden in endpoint configuration.";
    private static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_COMPONENT_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_CONSUMES_CONF = "camel.component.rest-swagger.consumes";
    private static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_CONSUMES_DOC = "What payload type this component capable of consuming. Could be one type, like application/json or multiple types as application/json, application/xml; q=0.5 according to the RFC7231. This equates to the value of Accept HTTP header. If set overrides any value found in the Swagger specification. Can be overridden in endpoint configuration";
    private static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_CONSUMES_DEFAULT = null;
    public static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_HOST_CONF = "camel.component.rest-swagger.host";
    private static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_HOST_DOC = "Scheme hostname and port to direct the HTTP requests to in the form of https://hostname:port. Can be configured at the endpoint, component or in the corresponding REST configuration in the Camel Context. If you give this component a name (e.g. petstore) that REST configuration is consulted first, rest-swagger next, and global configuration last. If set overrides any value found in the Swagger specification, RestConfiguration. Can be overridden in endpoint configuration.";
    private static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_HOST_DEFAULT = null;
    public static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.rest-swagger.lazyStartProducer";
    private static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_RESTSWAGGER_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_PRODUCES_CONF = "camel.component.rest-swagger.produces";
    private static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_PRODUCES_DOC = "What payload type this component is producing. For example application/json according to the RFC7231. This equates to the value of Content-Type HTTP header. If set overrides any value present in the Swagger specification. Can be overridden in endpoint configuration.";
    private static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_PRODUCES_DEFAULT = null;
    public static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_SPECIFICATION_URI_CONF = "camel.component.rest-swagger.specificationUri";
    private static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_SPECIFICATION_URI_DOC = "Path to the Swagger specification file. The scheme, host base path are taken from this specification, but these can be overridden with properties on the component or endpoint level. If not given the component tries to load swagger.json resource. Note that the host defined on the component and endpoint of this Component should contain the scheme, hostname and optionally the port in the URI syntax (i.e. https://api.example.com:8080). Can be overridden in endpoint configuration.";
    private static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_SPECIFICATION_URI_DEFAULT = "swagger.json";
    public static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.rest-swagger.basicPropertyBinding";
    private static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_RESTSWAGGER_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.rest-swagger.sslContextParameters";
    private static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_SSL_CONTEXT_PARAMETERS_DOC = "Customize TLS parameters used by the component. If not set defaults to the TLS parameters set in the Camel context";
    private static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_SSL_CONTEXT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.rest-swagger.useGlobalSslContextParameters";
    private static final String CAMEL_SINK_RESTSWAGGER_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC = "Enable usage of global SSL context parameters.";
    private static final Boolean CAMEL_SINK_RESTSWAGGER_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT = false;

    public CamelRestswaggerSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelRestswaggerSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_RESTSWAGGER_PATH_SPECIFICATION_URI_CONF, ConfigDef.Type.STRING, CAMEL_SINK_RESTSWAGGER_PATH_SPECIFICATION_URI_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_PATH_SPECIFICATION_URI_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_PATH_OPERATION_ID_CONF, ConfigDef.Type.STRING, CAMEL_SINK_RESTSWAGGER_PATH_OPERATION_ID_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_RESTSWAGGER_PATH_OPERATION_ID_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_ENDPOINT_BASE_PATH_CONF, ConfigDef.Type.STRING, CAMEL_SINK_RESTSWAGGER_ENDPOINT_BASE_PATH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_ENDPOINT_BASE_PATH_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_ENDPOINT_COMPONENT_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_RESTSWAGGER_ENDPOINT_COMPONENT_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_ENDPOINT_COMPONENT_NAME_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_ENDPOINT_CONSUMES_CONF, ConfigDef.Type.STRING, CAMEL_SINK_RESTSWAGGER_ENDPOINT_CONSUMES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_ENDPOINT_CONSUMES_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_ENDPOINT_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_RESTSWAGGER_ENDPOINT_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_ENDPOINT_HOST_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_RESTSWAGGER_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_ENDPOINT_PRODUCES_CONF, ConfigDef.Type.STRING, CAMEL_SINK_RESTSWAGGER_ENDPOINT_PRODUCES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_ENDPOINT_PRODUCES_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_RESTSWAGGER_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_RESTSWAGGER_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_RESTSWAGGER_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_COMPONENT_BASE_PATH_CONF, ConfigDef.Type.STRING, CAMEL_SINK_RESTSWAGGER_COMPONENT_BASE_PATH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_COMPONENT_BASE_PATH_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_COMPONENT_COMPONENT_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_RESTSWAGGER_COMPONENT_COMPONENT_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_COMPONENT_COMPONENT_NAME_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_COMPONENT_CONSUMES_CONF, ConfigDef.Type.STRING, CAMEL_SINK_RESTSWAGGER_COMPONENT_CONSUMES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_COMPONENT_CONSUMES_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_COMPONENT_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_RESTSWAGGER_COMPONENT_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_COMPONENT_HOST_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_RESTSWAGGER_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_COMPONENT_PRODUCES_CONF, ConfigDef.Type.STRING, CAMEL_SINK_RESTSWAGGER_COMPONENT_PRODUCES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_COMPONENT_PRODUCES_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_COMPONENT_SPECIFICATION_URI_CONF, ConfigDef.Type.STRING, CAMEL_SINK_RESTSWAGGER_COMPONENT_SPECIFICATION_URI_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_COMPONENT_SPECIFICATION_URI_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_RESTSWAGGER_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_COMPONENT_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_RESTSWAGGER_COMPONENT_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_COMPONENT_SSL_CONTEXT_PARAMETERS_DOC);
        conf.define(CAMEL_SINK_RESTSWAGGER_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_RESTSWAGGER_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_RESTSWAGGER_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC);
        return conf;
    }
}