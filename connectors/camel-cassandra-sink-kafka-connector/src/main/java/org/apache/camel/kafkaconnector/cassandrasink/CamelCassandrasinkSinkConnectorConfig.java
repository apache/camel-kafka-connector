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
package org.apache.camel.kafkaconnector.cassandrasink;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelCassandrasinkSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_CONNECTION_HOST_CONF = "camel.kamelet.cassandra-sink.connectionHost";
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_CONNECTION_HOST_DOC = "Hostname(s) cassandra server(s). Multiple hosts can be separated by comma. Example: localhost";
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_CONNECTION_HOST_DEFAULT = null;
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_CONNECTION_PORT_CONF = "camel.kamelet.cassandra-sink.connectionPort";
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_CONNECTION_PORT_DOC = "Port number of cassandra server(s) Example: 9042";
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_CONNECTION_PORT_DEFAULT = null;
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_KEYSPACE_CONF = "camel.kamelet.cassandra-sink.keyspace";
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_KEYSPACE_DOC = "Keyspace to use Example: customers";
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_KEYSPACE_DEFAULT = null;
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_USERNAME_CONF = "camel.kamelet.cassandra-sink.username";
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_USERNAME_DOC = "The username to use for accessing a secured Cassandra Cluster";
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_USERNAME_DEFAULT = null;
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_PASSWORD_CONF = "camel.kamelet.cassandra-sink.password";
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_PASSWORD_DOC = "The password to use for accessing a secured Cassandra Cluster";
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_CONSISTENCY_LEVEL_CONF = "camel.kamelet.cassandra-sink.consistencyLevel";
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_CONSISTENCY_LEVEL_DOC = "Consistency level to use. The value can be one of ANY, ONE, TWO, THREE, QUORUM, ALL, LOCAL_QUORUM, EACH_QUORUM, SERIAL, LOCAL_SERIAL, LOCAL_ONE";
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_CONSISTENCY_LEVEL_DEFAULT = "ANY";
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_PREPARED_STATEMENT_CONF = "camel.kamelet.cassandra-sink.preparedStatement";
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_PREPARED_STATEMENT_DOC = "The Prepared statement to execute against the Cassandra cluster table";
    public static final String CAMEL_SINK_CASSANDRASINK_KAMELET_PREPARED_STATEMENT_DEFAULT = null;

    public CamelCassandrasinkSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelCassandrasinkSinkConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_CASSANDRASINK_KAMELET_CONNECTION_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_CASSANDRASINK_KAMELET_CONNECTION_HOST_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_CASSANDRASINK_KAMELET_CONNECTION_HOST_DOC);
        conf.define(CAMEL_SINK_CASSANDRASINK_KAMELET_CONNECTION_PORT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_CASSANDRASINK_KAMELET_CONNECTION_PORT_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_CASSANDRASINK_KAMELET_CONNECTION_PORT_DOC);
        conf.define(CAMEL_SINK_CASSANDRASINK_KAMELET_KEYSPACE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_CASSANDRASINK_KAMELET_KEYSPACE_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_CASSANDRASINK_KAMELET_KEYSPACE_DOC);
        conf.define(CAMEL_SINK_CASSANDRASINK_KAMELET_USERNAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_CASSANDRASINK_KAMELET_USERNAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_CASSANDRASINK_KAMELET_USERNAME_DOC);
        conf.define(CAMEL_SINK_CASSANDRASINK_KAMELET_PASSWORD_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_CASSANDRASINK_KAMELET_PASSWORD_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_CASSANDRASINK_KAMELET_PASSWORD_DOC);
        conf.define(CAMEL_SINK_CASSANDRASINK_KAMELET_CONSISTENCY_LEVEL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_CASSANDRASINK_KAMELET_CONSISTENCY_LEVEL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_CASSANDRASINK_KAMELET_CONSISTENCY_LEVEL_DOC);
        conf.define(CAMEL_SINK_CASSANDRASINK_KAMELET_PREPARED_STATEMENT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_CASSANDRASINK_KAMELET_PREPARED_STATEMENT_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_CASSANDRASINK_KAMELET_PREPARED_STATEMENT_DOC);
        return conf;
    }
}