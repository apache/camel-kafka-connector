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
package org.apache.camel.kafkaconnector.mybatisbean;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelMybatisbeanSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_MYBATISBEAN_PATH_BEAN_NAME_CONF = "camel.sink.path.beanName";
    public static final String CAMEL_SINK_MYBATISBEAN_PATH_BEAN_NAME_DOC = "Name of the bean with the MyBatis annotations. This can either by a type alias or a FQN class name.";
    public static final String CAMEL_SINK_MYBATISBEAN_PATH_BEAN_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_MYBATISBEAN_PATH_METHOD_NAME_CONF = "camel.sink.path.methodName";
    public static final String CAMEL_SINK_MYBATISBEAN_PATH_METHOD_NAME_DOC = "Name of the method on the bean that has the SQL query to be executed.";
    public static final String CAMEL_SINK_MYBATISBEAN_PATH_METHOD_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_MYBATISBEAN_ENDPOINT_EXECUTOR_TYPE_CONF = "camel.sink.endpoint.executorType";
    public static final String CAMEL_SINK_MYBATISBEAN_ENDPOINT_EXECUTOR_TYPE_DOC = "The executor type to be used while executing statements. simple - executor does nothing special. reuse - executor reuses prepared statements. batch - executor reuses statements and batches updates. One of: [SIMPLE] [REUSE] [BATCH]";
    public static final String CAMEL_SINK_MYBATISBEAN_ENDPOINT_EXECUTOR_TYPE_DEFAULT = "SIMPLE";
    public static final String CAMEL_SINK_MYBATISBEAN_ENDPOINT_INPUT_HEADER_CONF = "camel.sink.endpoint.inputHeader";
    public static final String CAMEL_SINK_MYBATISBEAN_ENDPOINT_INPUT_HEADER_DOC = "User the header value for input parameters instead of the message body. By default, inputHeader == null and the input parameters are taken from the message body. If outputHeader is set, the value is used and query parameters will be taken from the header instead of the body.";
    public static final String CAMEL_SINK_MYBATISBEAN_ENDPOINT_INPUT_HEADER_DEFAULT = null;
    public static final String CAMEL_SINK_MYBATISBEAN_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_MYBATISBEAN_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_MYBATISBEAN_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_MYBATISBEAN_ENDPOINT_OUTPUT_HEADER_CONF = "camel.sink.endpoint.outputHeader";
    public static final String CAMEL_SINK_MYBATISBEAN_ENDPOINT_OUTPUT_HEADER_DOC = "Store the query result in a header instead of the message body. By default, outputHeader == null and the query result is stored in the message body, any existing content in the message body is discarded. If outputHeader is set, the value is used as the name of the header to store the query result and the original message body is preserved. Setting outputHeader will also omit populating the default CamelMyBatisResult header since it would be the same as outputHeader all the time.";
    public static final String CAMEL_SINK_MYBATISBEAN_ENDPOINT_OUTPUT_HEADER_DEFAULT = null;
    public static final String CAMEL_SINK_MYBATISBEAN_COMPONENT_CONFIGURATION_URI_CONF = "camel.component.mybatis-bean.configurationUri";
    public static final String CAMEL_SINK_MYBATISBEAN_COMPONENT_CONFIGURATION_URI_DOC = "Location of MyBatis xml configuration file. The default value is: SqlMapConfig.xml loaded from the classpath";
    public static final String CAMEL_SINK_MYBATISBEAN_COMPONENT_CONFIGURATION_URI_DEFAULT = "SqlMapConfig.xml";
    public static final String CAMEL_SINK_MYBATISBEAN_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.mybatis-bean.lazyStartProducer";
    public static final String CAMEL_SINK_MYBATISBEAN_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_MYBATISBEAN_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_MYBATISBEAN_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.mybatis-bean.autowiredEnabled";
    public static final String CAMEL_SINK_MYBATISBEAN_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SINK_MYBATISBEAN_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;
    public static final String CAMEL_SINK_MYBATISBEAN_COMPONENT_SQL_SESSION_FACTORY_CONF = "camel.component.mybatis-bean.sqlSessionFactory";
    public static final String CAMEL_SINK_MYBATISBEAN_COMPONENT_SQL_SESSION_FACTORY_DOC = "To use the SqlSessionFactory";
    public static final String CAMEL_SINK_MYBATISBEAN_COMPONENT_SQL_SESSION_FACTORY_DEFAULT = null;

    public CamelMybatisbeanSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelMybatisbeanSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_MYBATISBEAN_PATH_BEAN_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MYBATISBEAN_PATH_BEAN_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_MYBATISBEAN_PATH_BEAN_NAME_DOC);
        conf.define(CAMEL_SINK_MYBATISBEAN_PATH_METHOD_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MYBATISBEAN_PATH_METHOD_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_MYBATISBEAN_PATH_METHOD_NAME_DOC);
        conf.define(CAMEL_SINK_MYBATISBEAN_ENDPOINT_EXECUTOR_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MYBATISBEAN_ENDPOINT_EXECUTOR_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MYBATISBEAN_ENDPOINT_EXECUTOR_TYPE_DOC);
        conf.define(CAMEL_SINK_MYBATISBEAN_ENDPOINT_INPUT_HEADER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MYBATISBEAN_ENDPOINT_INPUT_HEADER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MYBATISBEAN_ENDPOINT_INPUT_HEADER_DOC);
        conf.define(CAMEL_SINK_MYBATISBEAN_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MYBATISBEAN_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MYBATISBEAN_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_MYBATISBEAN_ENDPOINT_OUTPUT_HEADER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MYBATISBEAN_ENDPOINT_OUTPUT_HEADER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MYBATISBEAN_ENDPOINT_OUTPUT_HEADER_DOC);
        conf.define(CAMEL_SINK_MYBATISBEAN_COMPONENT_CONFIGURATION_URI_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MYBATISBEAN_COMPONENT_CONFIGURATION_URI_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MYBATISBEAN_COMPONENT_CONFIGURATION_URI_DOC);
        conf.define(CAMEL_SINK_MYBATISBEAN_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MYBATISBEAN_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MYBATISBEAN_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_MYBATISBEAN_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MYBATISBEAN_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MYBATISBEAN_COMPONENT_AUTOWIRED_ENABLED_DOC);
        conf.define(CAMEL_SINK_MYBATISBEAN_COMPONENT_SQL_SESSION_FACTORY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MYBATISBEAN_COMPONENT_SQL_SESSION_FACTORY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MYBATISBEAN_COMPONENT_SQL_SESSION_FACTORY_DOC);
        return conf;
    }
}