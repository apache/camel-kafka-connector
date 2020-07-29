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
package org.apache.camel.kafkaconnector.pgevent;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelPgeventSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_PGEVENT_PATH_HOST_CONF = "camel.sink.path.host";
    private static final String CAMEL_SINK_PGEVENT_PATH_HOST_DOC = "To connect using hostname and port to the database.";
    private static final String CAMEL_SINK_PGEVENT_PATH_HOST_DEFAULT = "localhost";
    public static final String CAMEL_SINK_PGEVENT_PATH_PORT_CONF = "camel.sink.path.port";
    private static final String CAMEL_SINK_PGEVENT_PATH_PORT_DOC = "To connect using hostname and port to the database.";
    private static final String CAMEL_SINK_PGEVENT_PATH_PORT_DEFAULT = "5432";
    public static final String CAMEL_SINK_PGEVENT_PATH_DATABASE_CONF = "camel.sink.path.database";
    private static final String CAMEL_SINK_PGEVENT_PATH_DATABASE_DOC = "The database name";
    private static final String CAMEL_SINK_PGEVENT_PATH_DATABASE_DEFAULT = null;
    public static final String CAMEL_SINK_PGEVENT_PATH_CHANNEL_CONF = "camel.sink.path.channel";
    private static final String CAMEL_SINK_PGEVENT_PATH_CHANNEL_DOC = "The channel name";
    private static final String CAMEL_SINK_PGEVENT_PATH_CHANNEL_DEFAULT = null;
    public static final String CAMEL_SINK_PGEVENT_ENDPOINT_DATASOURCE_CONF = "camel.sink.endpoint.datasource";
    private static final String CAMEL_SINK_PGEVENT_ENDPOINT_DATASOURCE_DOC = "To connect using the given javax.sql.DataSource instead of using hostname and port.";
    private static final String CAMEL_SINK_PGEVENT_ENDPOINT_DATASOURCE_DEFAULT = null;
    public static final String CAMEL_SINK_PGEVENT_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    private static final String CAMEL_SINK_PGEVENT_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_PGEVENT_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_PGEVENT_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    private static final String CAMEL_SINK_PGEVENT_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_PGEVENT_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_PGEVENT_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    private static final String CAMEL_SINK_PGEVENT_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SINK_PGEVENT_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_PGEVENT_ENDPOINT_PASS_CONF = "camel.sink.endpoint.pass";
    private static final String CAMEL_SINK_PGEVENT_ENDPOINT_PASS_DOC = "Password for login";
    private static final String CAMEL_SINK_PGEVENT_ENDPOINT_PASS_DEFAULT = null;
    public static final String CAMEL_SINK_PGEVENT_ENDPOINT_USER_CONF = "camel.sink.endpoint.user";
    private static final String CAMEL_SINK_PGEVENT_ENDPOINT_USER_DOC = "Username for login";
    private static final String CAMEL_SINK_PGEVENT_ENDPOINT_USER_DEFAULT = "postgres";
    public static final String CAMEL_SINK_PGEVENT_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.pgevent.lazyStartProducer";
    private static final String CAMEL_SINK_PGEVENT_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_PGEVENT_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_PGEVENT_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.pgevent.basicPropertyBinding";
    private static final String CAMEL_SINK_PGEVENT_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_PGEVENT_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;

    public CamelPgeventSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelPgeventSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_PGEVENT_PATH_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_PGEVENT_PATH_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_PGEVENT_PATH_HOST_DOC);
        conf.define(CAMEL_SINK_PGEVENT_PATH_PORT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_PGEVENT_PATH_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_PGEVENT_PATH_PORT_DOC);
        conf.define(CAMEL_SINK_PGEVENT_PATH_DATABASE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_PGEVENT_PATH_DATABASE_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_PGEVENT_PATH_DATABASE_DOC);
        conf.define(CAMEL_SINK_PGEVENT_PATH_CHANNEL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_PGEVENT_PATH_CHANNEL_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_PGEVENT_PATH_CHANNEL_DOC);
        conf.define(CAMEL_SINK_PGEVENT_ENDPOINT_DATASOURCE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_PGEVENT_ENDPOINT_DATASOURCE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_PGEVENT_ENDPOINT_DATASOURCE_DOC);
        conf.define(CAMEL_SINK_PGEVENT_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_PGEVENT_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_PGEVENT_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_PGEVENT_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_PGEVENT_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_PGEVENT_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_PGEVENT_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_PGEVENT_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_PGEVENT_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_PGEVENT_ENDPOINT_PASS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_PGEVENT_ENDPOINT_PASS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_PGEVENT_ENDPOINT_PASS_DOC);
        conf.define(CAMEL_SINK_PGEVENT_ENDPOINT_USER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_PGEVENT_ENDPOINT_USER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_PGEVENT_ENDPOINT_USER_DOC);
        conf.define(CAMEL_SINK_PGEVENT_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_PGEVENT_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_PGEVENT_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_PGEVENT_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_PGEVENT_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_PGEVENT_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        return conf;
    }
}