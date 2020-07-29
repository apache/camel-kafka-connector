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
package org.apache.camel.kafkaconnector.workday;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelWorkdaySinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_WORKDAY_PATH_ENTITY_CONF = "camel.sink.path.entity";
    private static final String CAMEL_SINK_WORKDAY_PATH_ENTITY_DOC = "The entity to be requested or subscribed via API. One of: [report]";
    private static final String CAMEL_SINK_WORKDAY_PATH_ENTITY_DEFAULT = null;
    public static final String CAMEL_SINK_WORKDAY_PATH_PATH_CONF = "camel.sink.path.path";
    private static final String CAMEL_SINK_WORKDAY_PATH_PATH_DOC = "The API path to access an entity structure.";
    private static final String CAMEL_SINK_WORKDAY_PATH_PATH_DEFAULT = null;
    public static final String CAMEL_SINK_WORKDAY_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    private static final String CAMEL_SINK_WORKDAY_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_WORKDAY_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_WORKDAY_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    private static final String CAMEL_SINK_WORKDAY_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_WORKDAY_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_WORKDAY_ENDPOINT_HTTP_CONNECTION_MANAGER_CONF = "camel.sink.endpoint.httpConnectionManager";
    private static final String CAMEL_SINK_WORKDAY_ENDPOINT_HTTP_CONNECTION_MANAGER_DOC = "Pool connection manager for advanced configuration.";
    private static final String CAMEL_SINK_WORKDAY_ENDPOINT_HTTP_CONNECTION_MANAGER_DEFAULT = null;
    public static final String CAMEL_SINK_WORKDAY_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    private static final String CAMEL_SINK_WORKDAY_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SINK_WORKDAY_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_WORKDAY_ENDPOINT_REPORT_FORMAT_CONF = "camel.sink.endpoint.reportFormat";
    private static final String CAMEL_SINK_WORKDAY_ENDPOINT_REPORT_FORMAT_DOC = "Workday Report as a service output format. One of: [json]";
    private static final String CAMEL_SINK_WORKDAY_ENDPOINT_REPORT_FORMAT_DEFAULT = "json";
    public static final String CAMEL_SINK_WORKDAY_ENDPOINT_HOST_CONF = "camel.sink.endpoint.host";
    private static final String CAMEL_SINK_WORKDAY_ENDPOINT_HOST_DOC = "Workday Host name.";
    private static final String CAMEL_SINK_WORKDAY_ENDPOINT_HOST_DEFAULT = null;
    public static final String CAMEL_SINK_WORKDAY_ENDPOINT_CLIENT_ID_CONF = "camel.sink.endpoint.clientId";
    private static final String CAMEL_SINK_WORKDAY_ENDPOINT_CLIENT_ID_DOC = "Workday client Id generated by API client for integrations.";
    private static final String CAMEL_SINK_WORKDAY_ENDPOINT_CLIENT_ID_DEFAULT = null;
    public static final String CAMEL_SINK_WORKDAY_ENDPOINT_CLIENT_SECRET_CONF = "camel.sink.endpoint.clientSecret";
    private static final String CAMEL_SINK_WORKDAY_ENDPOINT_CLIENT_SECRET_DOC = "Workday client Secret generated by API client for integrations.";
    private static final String CAMEL_SINK_WORKDAY_ENDPOINT_CLIENT_SECRET_DEFAULT = null;
    public static final String CAMEL_SINK_WORKDAY_ENDPOINT_TOKEN_REFRESH_CONF = "camel.sink.endpoint.tokenRefresh";
    private static final String CAMEL_SINK_WORKDAY_ENDPOINT_TOKEN_REFRESH_DOC = "Workday token Refresh generated for integrations system user.";
    private static final String CAMEL_SINK_WORKDAY_ENDPOINT_TOKEN_REFRESH_DEFAULT = null;
    public static final String CAMEL_SINK_WORKDAY_ENDPOINT_TENANT_CONF = "camel.sink.endpoint.tenant";
    private static final String CAMEL_SINK_WORKDAY_ENDPOINT_TENANT_DOC = "Workday Tenant name.";
    private static final String CAMEL_SINK_WORKDAY_ENDPOINT_TENANT_DEFAULT = null;
    public static final String CAMEL_SINK_WORKDAY_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.workday.lazyStartProducer";
    private static final String CAMEL_SINK_WORKDAY_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_WORKDAY_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_WORKDAY_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.workday.basicPropertyBinding";
    private static final String CAMEL_SINK_WORKDAY_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_WORKDAY_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;

    public CamelWorkdaySinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelWorkdaySinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_WORKDAY_PATH_ENTITY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_WORKDAY_PATH_ENTITY_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_WORKDAY_PATH_ENTITY_DOC);
        conf.define(CAMEL_SINK_WORKDAY_PATH_PATH_CONF, ConfigDef.Type.STRING, CAMEL_SINK_WORKDAY_PATH_PATH_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_WORKDAY_PATH_PATH_DOC);
        conf.define(CAMEL_SINK_WORKDAY_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_WORKDAY_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_WORKDAY_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_WORKDAY_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_WORKDAY_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_WORKDAY_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_WORKDAY_ENDPOINT_HTTP_CONNECTION_MANAGER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_WORKDAY_ENDPOINT_HTTP_CONNECTION_MANAGER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_WORKDAY_ENDPOINT_HTTP_CONNECTION_MANAGER_DOC);
        conf.define(CAMEL_SINK_WORKDAY_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_WORKDAY_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_WORKDAY_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_WORKDAY_ENDPOINT_REPORT_FORMAT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_WORKDAY_ENDPOINT_REPORT_FORMAT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_WORKDAY_ENDPOINT_REPORT_FORMAT_DOC);
        conf.define(CAMEL_SINK_WORKDAY_ENDPOINT_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_WORKDAY_ENDPOINT_HOST_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_WORKDAY_ENDPOINT_HOST_DOC);
        conf.define(CAMEL_SINK_WORKDAY_ENDPOINT_CLIENT_ID_CONF, ConfigDef.Type.STRING, CAMEL_SINK_WORKDAY_ENDPOINT_CLIENT_ID_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_WORKDAY_ENDPOINT_CLIENT_ID_DOC);
        conf.define(CAMEL_SINK_WORKDAY_ENDPOINT_CLIENT_SECRET_CONF, ConfigDef.Type.STRING, CAMEL_SINK_WORKDAY_ENDPOINT_CLIENT_SECRET_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_WORKDAY_ENDPOINT_CLIENT_SECRET_DOC);
        conf.define(CAMEL_SINK_WORKDAY_ENDPOINT_TOKEN_REFRESH_CONF, ConfigDef.Type.STRING, CAMEL_SINK_WORKDAY_ENDPOINT_TOKEN_REFRESH_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_WORKDAY_ENDPOINT_TOKEN_REFRESH_DOC);
        conf.define(CAMEL_SINK_WORKDAY_ENDPOINT_TENANT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_WORKDAY_ENDPOINT_TENANT_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_WORKDAY_ENDPOINT_TENANT_DOC);
        conf.define(CAMEL_SINK_WORKDAY_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_WORKDAY_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_WORKDAY_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_WORKDAY_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_WORKDAY_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_WORKDAY_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        return conf;
    }
}