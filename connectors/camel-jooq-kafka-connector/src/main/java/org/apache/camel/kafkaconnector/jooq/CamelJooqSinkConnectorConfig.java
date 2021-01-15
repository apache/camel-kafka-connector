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
package org.apache.camel.kafkaconnector.jooq;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelJooqSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_JOOQ_PATH_ENTITY_TYPE_CONF = "camel.sink.path.entityType";
    public static final String CAMEL_SINK_JOOQ_PATH_ENTITY_TYPE_DOC = "JOOQ entity class";
    public static final String CAMEL_SINK_JOOQ_PATH_ENTITY_TYPE_DEFAULT = null;
    public static final String CAMEL_SINK_JOOQ_ENDPOINT_DATABASE_CONFIGURATION_CONF = "camel.sink.endpoint.databaseConfiguration";
    public static final String CAMEL_SINK_JOOQ_ENDPOINT_DATABASE_CONFIGURATION_DOC = "To use a specific database configuration";
    public static final String CAMEL_SINK_JOOQ_ENDPOINT_DATABASE_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SINK_JOOQ_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_JOOQ_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_JOOQ_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_JOOQ_ENDPOINT_OPERATION_CONF = "camel.sink.endpoint.operation";
    public static final String CAMEL_SINK_JOOQ_ENDPOINT_OPERATION_DOC = "Type of operation to execute on query One of: [EXECUTE] [FETCH] [NONE]";
    public static final String CAMEL_SINK_JOOQ_ENDPOINT_OPERATION_DEFAULT = "NONE";
    public static final String CAMEL_SINK_JOOQ_ENDPOINT_QUERY_CONF = "camel.sink.endpoint.query";
    public static final String CAMEL_SINK_JOOQ_ENDPOINT_QUERY_DOC = "To execute plain SQL query";
    public static final String CAMEL_SINK_JOOQ_ENDPOINT_QUERY_DEFAULT = null;
    public static final String CAMEL_SINK_JOOQ_COMPONENT_CONFIGURATION_CONF = "camel.component.jooq.configuration";
    public static final String CAMEL_SINK_JOOQ_COMPONENT_CONFIGURATION_DOC = "Component configuration (database connection, database entity type, etc.)";
    public static final String CAMEL_SINK_JOOQ_COMPONENT_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SINK_JOOQ_COMPONENT_DATABASE_CONFIGURATION_CONF = "camel.component.jooq.databaseConfiguration";
    public static final String CAMEL_SINK_JOOQ_COMPONENT_DATABASE_CONFIGURATION_DOC = "To use a specific database configuration";
    public static final String CAMEL_SINK_JOOQ_COMPONENT_DATABASE_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SINK_JOOQ_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.jooq.lazyStartProducer";
    public static final String CAMEL_SINK_JOOQ_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_JOOQ_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_JOOQ_COMPONENT_OPERATION_CONF = "camel.component.jooq.operation";
    public static final String CAMEL_SINK_JOOQ_COMPONENT_OPERATION_DOC = "Type of operation to execute on query One of: [EXECUTE] [FETCH] [NONE]";
    public static final String CAMEL_SINK_JOOQ_COMPONENT_OPERATION_DEFAULT = "NONE";
    public static final String CAMEL_SINK_JOOQ_COMPONENT_QUERY_CONF = "camel.component.jooq.query";
    public static final String CAMEL_SINK_JOOQ_COMPONENT_QUERY_DOC = "To execute plain SQL query";
    public static final String CAMEL_SINK_JOOQ_COMPONENT_QUERY_DEFAULT = null;
    public static final String CAMEL_SINK_JOOQ_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.jooq.autowiredEnabled";
    public static final String CAMEL_SINK_JOOQ_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SINK_JOOQ_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;

    public CamelJooqSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelJooqSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_JOOQ_PATH_ENTITY_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JOOQ_PATH_ENTITY_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JOOQ_PATH_ENTITY_TYPE_DOC);
        conf.define(CAMEL_SINK_JOOQ_ENDPOINT_DATABASE_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JOOQ_ENDPOINT_DATABASE_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JOOQ_ENDPOINT_DATABASE_CONFIGURATION_DOC);
        conf.define(CAMEL_SINK_JOOQ_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JOOQ_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JOOQ_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_JOOQ_ENDPOINT_OPERATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JOOQ_ENDPOINT_OPERATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JOOQ_ENDPOINT_OPERATION_DOC);
        conf.define(CAMEL_SINK_JOOQ_ENDPOINT_QUERY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JOOQ_ENDPOINT_QUERY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JOOQ_ENDPOINT_QUERY_DOC);
        conf.define(CAMEL_SINK_JOOQ_COMPONENT_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JOOQ_COMPONENT_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JOOQ_COMPONENT_CONFIGURATION_DOC);
        conf.define(CAMEL_SINK_JOOQ_COMPONENT_DATABASE_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JOOQ_COMPONENT_DATABASE_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JOOQ_COMPONENT_DATABASE_CONFIGURATION_DOC);
        conf.define(CAMEL_SINK_JOOQ_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JOOQ_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JOOQ_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_JOOQ_COMPONENT_OPERATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JOOQ_COMPONENT_OPERATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JOOQ_COMPONENT_OPERATION_DOC);
        conf.define(CAMEL_SINK_JOOQ_COMPONENT_QUERY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JOOQ_COMPONENT_QUERY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JOOQ_COMPONENT_QUERY_DOC);
        conf.define(CAMEL_SINK_JOOQ_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JOOQ_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JOOQ_COMPONENT_AUTOWIRED_ENABLED_DOC);
        return conf;
    }
}