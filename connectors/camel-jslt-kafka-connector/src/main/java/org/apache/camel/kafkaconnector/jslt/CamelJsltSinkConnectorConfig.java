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
package org.apache.camel.kafkaconnector.jslt;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelJsltSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_JSLT_PATH_RESOURCE_URI_CONF = "camel.sink.path.resourceUri";
    public static final String CAMEL_SINK_JSLT_PATH_RESOURCE_URI_DOC = "Path to the resource. You can prefix with: classpath, file, http, ref, or bean. classpath, file and http loads the resource using these protocols (classpath is default). ref will lookup the resource in the registry. bean will call a method on a bean to be used as the resource. For bean you can specify the method name after dot, eg bean:myBean.myMethod.";
    public static final String CAMEL_SINK_JSLT_PATH_RESOURCE_URI_DEFAULT = null;
    public static final String CAMEL_SINK_JSLT_ENDPOINT_ALLOW_CONTEXT_MAP_ALL_CONF = "camel.sink.endpoint.allowContextMapAll";
    public static final String CAMEL_SINK_JSLT_ENDPOINT_ALLOW_CONTEXT_MAP_ALL_DOC = "Sets whether the context map should allow access to all details. By default only the message body and headers can be accessed. This option can be enabled for full access to the current Exchange and CamelContext. Doing so impose a potential security risk as this opens access to the full power of CamelContext API.";
    public static final Boolean CAMEL_SINK_JSLT_ENDPOINT_ALLOW_CONTEXT_MAP_ALL_DEFAULT = false;
    public static final String CAMEL_SINK_JSLT_ENDPOINT_ALLOW_TEMPLATE_FROM_HEADER_CONF = "camel.sink.endpoint.allowTemplateFromHeader";
    public static final String CAMEL_SINK_JSLT_ENDPOINT_ALLOW_TEMPLATE_FROM_HEADER_DOC = "Whether to allow to use resource template from header or not (default false). Enabling this allows to specify dynamic templates via message header. However this can be seen as a potential security vulnerability if the header is coming from a malicious user, so use this with care.";
    public static final Boolean CAMEL_SINK_JSLT_ENDPOINT_ALLOW_TEMPLATE_FROM_HEADER_DEFAULT = false;
    public static final String CAMEL_SINK_JSLT_ENDPOINT_CONTENT_CACHE_CONF = "camel.sink.endpoint.contentCache";
    public static final String CAMEL_SINK_JSLT_ENDPOINT_CONTENT_CACHE_DOC = "Sets whether to use resource content cache or not";
    public static final Boolean CAMEL_SINK_JSLT_ENDPOINT_CONTENT_CACHE_DEFAULT = false;
    public static final String CAMEL_SINK_JSLT_ENDPOINT_PRETTY_PRINT_CONF = "camel.sink.endpoint.prettyPrint";
    public static final String CAMEL_SINK_JSLT_ENDPOINT_PRETTY_PRINT_DOC = "If true, JSON in output message is pretty printed.";
    public static final Boolean CAMEL_SINK_JSLT_ENDPOINT_PRETTY_PRINT_DEFAULT = false;
    public static final String CAMEL_SINK_JSLT_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_JSLT_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_JSLT_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_JSLT_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    public static final String CAMEL_SINK_JSLT_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SINK_JSLT_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_JSLT_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    public static final String CAMEL_SINK_JSLT_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SINK_JSLT_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_JSLT_COMPONENT_ALLOW_TEMPLATE_FROM_HEADER_CONF = "camel.component.jslt.allowTemplateFromHeader";
    public static final String CAMEL_SINK_JSLT_COMPONENT_ALLOW_TEMPLATE_FROM_HEADER_DOC = "Whether to allow to use resource template from header or not (default false). Enabling this allows to specify dynamic templates via message header. However this can be seen as a potential security vulnerability if the header is coming from a malicious user, so use this with care.";
    public static final Boolean CAMEL_SINK_JSLT_COMPONENT_ALLOW_TEMPLATE_FROM_HEADER_DEFAULT = false;
    public static final String CAMEL_SINK_JSLT_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.jslt.lazyStartProducer";
    public static final String CAMEL_SINK_JSLT_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_JSLT_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_JSLT_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.jslt.basicPropertyBinding";
    public static final String CAMEL_SINK_JSLT_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SINK_JSLT_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_JSLT_COMPONENT_FUNCTIONS_CONF = "camel.component.jslt.functions";
    public static final String CAMEL_SINK_JSLT_COMPONENT_FUNCTIONS_DOC = "JSLT can be extended by plugging in functions written in Java.";
    public static final String CAMEL_SINK_JSLT_COMPONENT_FUNCTIONS_DEFAULT = null;
    public static final String CAMEL_SINK_JSLT_COMPONENT_OBJECT_FILTER_CONF = "camel.component.jslt.objectFilter";
    public static final String CAMEL_SINK_JSLT_COMPONENT_OBJECT_FILTER_DOC = "JSLT can be extended by plugging in a custom jslt object filter";
    public static final String CAMEL_SINK_JSLT_COMPONENT_OBJECT_FILTER_DEFAULT = null;

    public CamelJsltSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelJsltSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_JSLT_PATH_RESOURCE_URI_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JSLT_PATH_RESOURCE_URI_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_JSLT_PATH_RESOURCE_URI_DOC);
        conf.define(CAMEL_SINK_JSLT_ENDPOINT_ALLOW_CONTEXT_MAP_ALL_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JSLT_ENDPOINT_ALLOW_CONTEXT_MAP_ALL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JSLT_ENDPOINT_ALLOW_CONTEXT_MAP_ALL_DOC);
        conf.define(CAMEL_SINK_JSLT_ENDPOINT_ALLOW_TEMPLATE_FROM_HEADER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JSLT_ENDPOINT_ALLOW_TEMPLATE_FROM_HEADER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JSLT_ENDPOINT_ALLOW_TEMPLATE_FROM_HEADER_DOC);
        conf.define(CAMEL_SINK_JSLT_ENDPOINT_CONTENT_CACHE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JSLT_ENDPOINT_CONTENT_CACHE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JSLT_ENDPOINT_CONTENT_CACHE_DOC);
        conf.define(CAMEL_SINK_JSLT_ENDPOINT_PRETTY_PRINT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JSLT_ENDPOINT_PRETTY_PRINT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JSLT_ENDPOINT_PRETTY_PRINT_DOC);
        conf.define(CAMEL_SINK_JSLT_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JSLT_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JSLT_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_JSLT_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JSLT_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JSLT_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_JSLT_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JSLT_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JSLT_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_JSLT_COMPONENT_ALLOW_TEMPLATE_FROM_HEADER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JSLT_COMPONENT_ALLOW_TEMPLATE_FROM_HEADER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JSLT_COMPONENT_ALLOW_TEMPLATE_FROM_HEADER_DOC);
        conf.define(CAMEL_SINK_JSLT_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JSLT_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JSLT_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_JSLT_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JSLT_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.LOW, CAMEL_SINK_JSLT_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_JSLT_COMPONENT_FUNCTIONS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JSLT_COMPONENT_FUNCTIONS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JSLT_COMPONENT_FUNCTIONS_DOC);
        conf.define(CAMEL_SINK_JSLT_COMPONENT_OBJECT_FILTER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JSLT_COMPONENT_OBJECT_FILTER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JSLT_COMPONENT_OBJECT_FILTER_DOC);
        return conf;
    }
}