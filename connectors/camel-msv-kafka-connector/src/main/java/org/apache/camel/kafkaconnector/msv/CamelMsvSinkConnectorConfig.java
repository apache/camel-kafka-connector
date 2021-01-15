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
package org.apache.camel.kafkaconnector.msv;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelMsvSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_MSV_PATH_RESOURCE_URI_CONF = "camel.sink.path.resourceUri";
    public static final String CAMEL_SINK_MSV_PATH_RESOURCE_URI_DOC = "URL to a local resource on the classpath, or a reference to lookup a bean in the Registry, or a full URL to a remote resource or resource on the file system which contains the XSD to validate against.";
    public static final String CAMEL_SINK_MSV_PATH_RESOURCE_URI_DEFAULT = null;
    public static final String CAMEL_SINK_MSV_ENDPOINT_FAIL_ON_NULL_BODY_CONF = "camel.sink.endpoint.failOnNullBody";
    public static final String CAMEL_SINK_MSV_ENDPOINT_FAIL_ON_NULL_BODY_DOC = "Whether to fail if no body exists.";
    public static final Boolean CAMEL_SINK_MSV_ENDPOINT_FAIL_ON_NULL_BODY_DEFAULT = true;
    public static final String CAMEL_SINK_MSV_ENDPOINT_FAIL_ON_NULL_HEADER_CONF = "camel.sink.endpoint.failOnNullHeader";
    public static final String CAMEL_SINK_MSV_ENDPOINT_FAIL_ON_NULL_HEADER_DOC = "Whether to fail if no header exists when validating against a header.";
    public static final Boolean CAMEL_SINK_MSV_ENDPOINT_FAIL_ON_NULL_HEADER_DEFAULT = true;
    public static final String CAMEL_SINK_MSV_ENDPOINT_HEADER_NAME_CONF = "camel.sink.endpoint.headerName";
    public static final String CAMEL_SINK_MSV_ENDPOINT_HEADER_NAME_DOC = "To validate against a header instead of the message body.";
    public static final String CAMEL_SINK_MSV_ENDPOINT_HEADER_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_MSV_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_MSV_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_MSV_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_MSV_ENDPOINT_ERROR_HANDLER_CONF = "camel.sink.endpoint.errorHandler";
    public static final String CAMEL_SINK_MSV_ENDPOINT_ERROR_HANDLER_DOC = "To use a custom org.apache.camel.processor.validation.ValidatorErrorHandler. The default error handler captures the errors and throws an exception.";
    public static final String CAMEL_SINK_MSV_ENDPOINT_ERROR_HANDLER_DEFAULT = null;
    public static final String CAMEL_SINK_MSV_ENDPOINT_RESOURCE_RESOLVER_CONF = "camel.sink.endpoint.resourceResolver";
    public static final String CAMEL_SINK_MSV_ENDPOINT_RESOURCE_RESOLVER_DOC = "To use a custom LSResourceResolver. Do not use together with resourceResolverFactory";
    public static final String CAMEL_SINK_MSV_ENDPOINT_RESOURCE_RESOLVER_DEFAULT = null;
    public static final String CAMEL_SINK_MSV_ENDPOINT_RESOURCE_RESOLVER_FACTORY_CONF = "camel.sink.endpoint.resourceResolverFactory";
    public static final String CAMEL_SINK_MSV_ENDPOINT_RESOURCE_RESOLVER_FACTORY_DOC = "To use a custom LSResourceResolver which depends on a dynamic endpoint resource URI. The default resource resolver factory resturns a resource resolver which can read files from the class path and file system. Do not use together with resourceResolver.";
    public static final String CAMEL_SINK_MSV_ENDPOINT_RESOURCE_RESOLVER_FACTORY_DEFAULT = null;
    public static final String CAMEL_SINK_MSV_ENDPOINT_SCHEMA_FACTORY_CONF = "camel.sink.endpoint.schemaFactory";
    public static final String CAMEL_SINK_MSV_ENDPOINT_SCHEMA_FACTORY_DOC = "To use a custom javax.xml.validation.SchemaFactory";
    public static final String CAMEL_SINK_MSV_ENDPOINT_SCHEMA_FACTORY_DEFAULT = null;
    public static final String CAMEL_SINK_MSV_ENDPOINT_SCHEMA_LANGUAGE_CONF = "camel.sink.endpoint.schemaLanguage";
    public static final String CAMEL_SINK_MSV_ENDPOINT_SCHEMA_LANGUAGE_DOC = "Configures the W3C XML Schema Namespace URI.";
    public static final String CAMEL_SINK_MSV_ENDPOINT_SCHEMA_LANGUAGE_DEFAULT = "http://www.w3.org/2001/XMLSchema";
    public static final String CAMEL_SINK_MSV_ENDPOINT_USE_SHARED_SCHEMA_CONF = "camel.sink.endpoint.useSharedSchema";
    public static final String CAMEL_SINK_MSV_ENDPOINT_USE_SHARED_SCHEMA_DOC = "Whether the Schema instance should be shared or not. This option is introduced to work around a JDK 1.6.x bug. Xerces should not have this issue.";
    public static final Boolean CAMEL_SINK_MSV_ENDPOINT_USE_SHARED_SCHEMA_DEFAULT = true;
    public static final String CAMEL_SINK_MSV_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.msv.lazyStartProducer";
    public static final String CAMEL_SINK_MSV_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_MSV_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_MSV_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.msv.autowiredEnabled";
    public static final String CAMEL_SINK_MSV_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SINK_MSV_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;
    public static final String CAMEL_SINK_MSV_COMPONENT_RESOURCE_RESOLVER_FACTORY_CONF = "camel.component.msv.resourceResolverFactory";
    public static final String CAMEL_SINK_MSV_COMPONENT_RESOURCE_RESOLVER_FACTORY_DOC = "To use a custom LSResourceResolver which depends on a dynamic endpoint resource URI";
    public static final String CAMEL_SINK_MSV_COMPONENT_RESOURCE_RESOLVER_FACTORY_DEFAULT = null;
    public static final String CAMEL_SINK_MSV_COMPONENT_SCHEMA_FACTORY_CONF = "camel.component.msv.schemaFactory";
    public static final String CAMEL_SINK_MSV_COMPONENT_SCHEMA_FACTORY_DOC = "To use the javax.xml.validation.SchemaFactory.";
    public static final String CAMEL_SINK_MSV_COMPONENT_SCHEMA_FACTORY_DEFAULT = null;

    public CamelMsvSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelMsvSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_MSV_PATH_RESOURCE_URI_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MSV_PATH_RESOURCE_URI_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_MSV_PATH_RESOURCE_URI_DOC);
        conf.define(CAMEL_SINK_MSV_ENDPOINT_FAIL_ON_NULL_BODY_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MSV_ENDPOINT_FAIL_ON_NULL_BODY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MSV_ENDPOINT_FAIL_ON_NULL_BODY_DOC);
        conf.define(CAMEL_SINK_MSV_ENDPOINT_FAIL_ON_NULL_HEADER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MSV_ENDPOINT_FAIL_ON_NULL_HEADER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MSV_ENDPOINT_FAIL_ON_NULL_HEADER_DOC);
        conf.define(CAMEL_SINK_MSV_ENDPOINT_HEADER_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MSV_ENDPOINT_HEADER_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MSV_ENDPOINT_HEADER_NAME_DOC);
        conf.define(CAMEL_SINK_MSV_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MSV_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MSV_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_MSV_ENDPOINT_ERROR_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MSV_ENDPOINT_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MSV_ENDPOINT_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SINK_MSV_ENDPOINT_RESOURCE_RESOLVER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MSV_ENDPOINT_RESOURCE_RESOLVER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MSV_ENDPOINT_RESOURCE_RESOLVER_DOC);
        conf.define(CAMEL_SINK_MSV_ENDPOINT_RESOURCE_RESOLVER_FACTORY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MSV_ENDPOINT_RESOURCE_RESOLVER_FACTORY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MSV_ENDPOINT_RESOURCE_RESOLVER_FACTORY_DOC);
        conf.define(CAMEL_SINK_MSV_ENDPOINT_SCHEMA_FACTORY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MSV_ENDPOINT_SCHEMA_FACTORY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MSV_ENDPOINT_SCHEMA_FACTORY_DOC);
        conf.define(CAMEL_SINK_MSV_ENDPOINT_SCHEMA_LANGUAGE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MSV_ENDPOINT_SCHEMA_LANGUAGE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MSV_ENDPOINT_SCHEMA_LANGUAGE_DOC);
        conf.define(CAMEL_SINK_MSV_ENDPOINT_USE_SHARED_SCHEMA_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MSV_ENDPOINT_USE_SHARED_SCHEMA_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MSV_ENDPOINT_USE_SHARED_SCHEMA_DOC);
        conf.define(CAMEL_SINK_MSV_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MSV_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MSV_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_MSV_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MSV_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MSV_COMPONENT_AUTOWIRED_ENABLED_DOC);
        conf.define(CAMEL_SINK_MSV_COMPONENT_RESOURCE_RESOLVER_FACTORY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MSV_COMPONENT_RESOURCE_RESOLVER_FACTORY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MSV_COMPONENT_RESOURCE_RESOLVER_FACTORY_DOC);
        conf.define(CAMEL_SINK_MSV_COMPONENT_SCHEMA_FACTORY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MSV_COMPONENT_SCHEMA_FACTORY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MSV_COMPONENT_SCHEMA_FACTORY_DOC);
        return conf;
    }
}