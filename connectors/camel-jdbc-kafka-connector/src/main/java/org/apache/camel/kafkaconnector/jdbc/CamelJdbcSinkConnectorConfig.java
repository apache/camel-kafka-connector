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
 */package org.apache.camel.kafkaconnector.jdbc;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelJdbcSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_JDBC_PATH_DATA_SOURCE_NAME_CONF = "camel.sink.path.dataSourceName";
    public static final String CAMEL_SINK_JDBC_PATH_DATA_SOURCE_NAME_DOC = "Name of DataSource to lookup in the Registry. If the name is dataSource or default, then Camel will attempt to lookup a default DataSource from the registry, meaning if there is a only one instance of DataSource found, then this DataSource will be used.";
    public static final String CAMEL_SINK_JDBC_PATH_DATA_SOURCE_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_JDBC_ENDPOINT_ALLOW_NAMED_PARAMETERS_CONF = "camel.sink.endpoint.allowNamedParameters";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_ALLOW_NAMED_PARAMETERS_DOC = "Whether to allow using named parameters in the queries.";
    public static final Boolean CAMEL_SINK_JDBC_ENDPOINT_ALLOW_NAMED_PARAMETERS_DEFAULT = true;
    public static final String CAMEL_SINK_JDBC_ENDPOINT_OUTPUT_CLASS_CONF = "camel.sink.endpoint.outputClass";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_OUTPUT_CLASS_DOC = "Specify the full package and class name to use as conversion when outputType=SelectOne or SelectList.";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_OUTPUT_CLASS_DEFAULT = null;
    public static final String CAMEL_SINK_JDBC_ENDPOINT_OUTPUT_TYPE_CONF = "camel.sink.endpoint.outputType";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_OUTPUT_TYPE_DOC = "Determines the output the producer should use. One of: [SelectOne] [SelectList] [StreamList]";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_OUTPUT_TYPE_DEFAULT = "SelectList";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_PARAMETERS_CONF = "camel.sink.endpoint.parameters";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_PARAMETERS_DOC = "Optional parameters to the java.sql.Statement. For example to set maxRows, fetchSize etc.";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SINK_JDBC_ENDPOINT_READ_SIZE_CONF = "camel.sink.endpoint.readSize";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_READ_SIZE_DOC = "The default maximum number of rows that can be read by a polling query. The default value is 0.";
    public static final Integer CAMEL_SINK_JDBC_ENDPOINT_READ_SIZE_DEFAULT = null;
    public static final String CAMEL_SINK_JDBC_ENDPOINT_RESET_AUTO_COMMIT_CONF = "camel.sink.endpoint.resetAutoCommit";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_RESET_AUTO_COMMIT_DOC = "Camel will set the autoCommit on the JDBC connection to be false, commit the change after executed the statement and reset the autoCommit flag of the connection at the end, if the resetAutoCommit is true. If the JDBC connection doesn't support to reset the autoCommit flag, you can set the resetAutoCommit flag to be false, and Camel will not try to reset the autoCommit flag. When used with XA transactions you most likely need to set it to false so that the transaction manager is in charge of committing this tx.";
    public static final Boolean CAMEL_SINK_JDBC_ENDPOINT_RESET_AUTO_COMMIT_DEFAULT = true;
    public static final String CAMEL_SINK_JDBC_ENDPOINT_TRANSACTED_CONF = "camel.sink.endpoint.transacted";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_TRANSACTED_DOC = "Whether transactions are in use.";
    public static final Boolean CAMEL_SINK_JDBC_ENDPOINT_TRANSACTED_DEFAULT = false;
    public static final String CAMEL_SINK_JDBC_ENDPOINT_USE_GET_BYTES_FOR_BLOB_CONF = "camel.sink.endpoint.useGetBytesForBlob";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_USE_GET_BYTES_FOR_BLOB_DOC = "To read BLOB columns as bytes instead of string data. This may be needed for certain databases such as Oracle where you must read BLOB columns as bytes.";
    public static final Boolean CAMEL_SINK_JDBC_ENDPOINT_USE_GET_BYTES_FOR_BLOB_DEFAULT = false;
    public static final String CAMEL_SINK_JDBC_ENDPOINT_USE_HEADERS_AS_PARAMETERS_CONF = "camel.sink.endpoint.useHeadersAsParameters";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_USE_HEADERS_AS_PARAMETERS_DOC = "Set this option to true to use the prepareStatementStrategy with named parameters. This allows to define queries with named placeholders, and use headers with the dynamic values for the query placeholders.";
    public static final Boolean CAMEL_SINK_JDBC_ENDPOINT_USE_HEADERS_AS_PARAMETERS_DEFAULT = false;
    public static final String CAMEL_SINK_JDBC_ENDPOINT_USE_JDBC4COLUMN_NAME_AND_LABEL_SEMANTICS_CONF = "camel.sink.endpoint.useJDBC4ColumnNameAndLabelSemantics";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_USE_JDBC4COLUMN_NAME_AND_LABEL_SEMANTICS_DOC = "Sets whether to use JDBC 4 or JDBC 3.0 or older semantic when retrieving column name. JDBC 4.0 uses columnLabel to get the column name where as JDBC 3.0 uses both columnName or columnLabel. Unfortunately JDBC drivers behave differently so you can use this option to work out issues around your JDBC driver if you get problem using this component This option is default true.";
    public static final Boolean CAMEL_SINK_JDBC_ENDPOINT_USE_JDBC4COLUMN_NAME_AND_LABEL_SEMANTICS_DEFAULT = true;
    public static final String CAMEL_SINK_JDBC_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_JDBC_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_JDBC_ENDPOINT_BEAN_ROW_MAPPER_CONF = "camel.sink.endpoint.beanRowMapper";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_BEAN_ROW_MAPPER_DOC = "To use a custom org.apache.camel.component.jdbc.BeanRowMapper when using outputClass. The default implementation will lower case the row names and skip underscores, and dashes. For example CUST_ID is mapped as custId.";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_BEAN_ROW_MAPPER_DEFAULT = null;
    public static final String CAMEL_SINK_JDBC_ENDPOINT_CONNECTION_STRATEGY_CONF = "camel.sink.endpoint.connectionStrategy";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_CONNECTION_STRATEGY_DOC = "To use a custom strategy for working with connections. Do not use a custom strategy when using the spring-jdbc component because a special Spring ConnectionStrategy is used by default to support Spring Transactions.";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_CONNECTION_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SINK_JDBC_ENDPOINT_PREPARE_STATEMENT_STRATEGY_CONF = "camel.sink.endpoint.prepareStatementStrategy";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_PREPARE_STATEMENT_STRATEGY_DOC = "Allows the plugin to use a custom org.apache.camel.component.jdbc.JdbcPrepareStatementStrategy to control preparation of the query and prepared statement.";
    public static final String CAMEL_SINK_JDBC_ENDPOINT_PREPARE_STATEMENT_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SINK_JDBC_COMPONENT_DATA_SOURCE_CONF = "camel.component.jdbc.dataSource";
    public static final String CAMEL_SINK_JDBC_COMPONENT_DATA_SOURCE_DOC = "To use the DataSource instance instead of looking up the data source by name from the registry.";
    public static final String CAMEL_SINK_JDBC_COMPONENT_DATA_SOURCE_DEFAULT = null;
    public static final String CAMEL_SINK_JDBC_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.jdbc.lazyStartProducer";
    public static final String CAMEL_SINK_JDBC_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_JDBC_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_JDBC_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.jdbc.autowiredEnabled";
    public static final String CAMEL_SINK_JDBC_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SINK_JDBC_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;
    public static final String CAMEL_SINK_JDBC_COMPONENT_CONNECTION_STRATEGY_CONF = "camel.component.jdbc.connectionStrategy";
    public static final String CAMEL_SINK_JDBC_COMPONENT_CONNECTION_STRATEGY_DOC = "To use a custom strategy for working with connections. Do not use a custom strategy when using the spring-jdbc component because a special Spring ConnectionStrategy is used by default to support Spring Transactions.";
    public static final String CAMEL_SINK_JDBC_COMPONENT_CONNECTION_STRATEGY_DEFAULT = null;

    public CamelJdbcSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelJdbcSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_JDBC_PATH_DATA_SOURCE_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JDBC_PATH_DATA_SOURCE_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_JDBC_PATH_DATA_SOURCE_NAME_DOC);
        conf.define(CAMEL_SINK_JDBC_ENDPOINT_ALLOW_NAMED_PARAMETERS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JDBC_ENDPOINT_ALLOW_NAMED_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JDBC_ENDPOINT_ALLOW_NAMED_PARAMETERS_DOC);
        conf.define(CAMEL_SINK_JDBC_ENDPOINT_OUTPUT_CLASS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JDBC_ENDPOINT_OUTPUT_CLASS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JDBC_ENDPOINT_OUTPUT_CLASS_DOC);
        conf.define(CAMEL_SINK_JDBC_ENDPOINT_OUTPUT_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JDBC_ENDPOINT_OUTPUT_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JDBC_ENDPOINT_OUTPUT_TYPE_DOC);
        conf.define(CAMEL_SINK_JDBC_ENDPOINT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JDBC_ENDPOINT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JDBC_ENDPOINT_PARAMETERS_DOC);
        conf.define(CAMEL_SINK_JDBC_ENDPOINT_READ_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SINK_JDBC_ENDPOINT_READ_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JDBC_ENDPOINT_READ_SIZE_DOC);
        conf.define(CAMEL_SINK_JDBC_ENDPOINT_RESET_AUTO_COMMIT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JDBC_ENDPOINT_RESET_AUTO_COMMIT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JDBC_ENDPOINT_RESET_AUTO_COMMIT_DOC);
        conf.define(CAMEL_SINK_JDBC_ENDPOINT_TRANSACTED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JDBC_ENDPOINT_TRANSACTED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JDBC_ENDPOINT_TRANSACTED_DOC);
        conf.define(CAMEL_SINK_JDBC_ENDPOINT_USE_GET_BYTES_FOR_BLOB_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JDBC_ENDPOINT_USE_GET_BYTES_FOR_BLOB_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JDBC_ENDPOINT_USE_GET_BYTES_FOR_BLOB_DOC);
        conf.define(CAMEL_SINK_JDBC_ENDPOINT_USE_HEADERS_AS_PARAMETERS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JDBC_ENDPOINT_USE_HEADERS_AS_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JDBC_ENDPOINT_USE_HEADERS_AS_PARAMETERS_DOC);
        conf.define(CAMEL_SINK_JDBC_ENDPOINT_USE_JDBC4COLUMN_NAME_AND_LABEL_SEMANTICS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JDBC_ENDPOINT_USE_JDBC4COLUMN_NAME_AND_LABEL_SEMANTICS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JDBC_ENDPOINT_USE_JDBC4COLUMN_NAME_AND_LABEL_SEMANTICS_DOC);
        conf.define(CAMEL_SINK_JDBC_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JDBC_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JDBC_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_JDBC_ENDPOINT_BEAN_ROW_MAPPER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JDBC_ENDPOINT_BEAN_ROW_MAPPER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JDBC_ENDPOINT_BEAN_ROW_MAPPER_DOC);
        conf.define(CAMEL_SINK_JDBC_ENDPOINT_CONNECTION_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JDBC_ENDPOINT_CONNECTION_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JDBC_ENDPOINT_CONNECTION_STRATEGY_DOC);
        conf.define(CAMEL_SINK_JDBC_ENDPOINT_PREPARE_STATEMENT_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JDBC_ENDPOINT_PREPARE_STATEMENT_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JDBC_ENDPOINT_PREPARE_STATEMENT_STRATEGY_DOC);
        conf.define(CAMEL_SINK_JDBC_COMPONENT_DATA_SOURCE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JDBC_COMPONENT_DATA_SOURCE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JDBC_COMPONENT_DATA_SOURCE_DOC);
        conf.define(CAMEL_SINK_JDBC_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JDBC_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JDBC_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_JDBC_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JDBC_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JDBC_COMPONENT_AUTOWIRED_ENABLED_DOC);
        conf.define(CAMEL_SINK_JDBC_COMPONENT_CONNECTION_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JDBC_COMPONENT_CONNECTION_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JDBC_COMPONENT_CONNECTION_STRATEGY_DOC);
        return conf;
    }
}