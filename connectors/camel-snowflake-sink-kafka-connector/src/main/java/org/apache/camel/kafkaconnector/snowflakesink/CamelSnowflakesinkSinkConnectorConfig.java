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
 */package org.apache.camel.kafkaconnector.snowflakesink;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelSnowflakesinkSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_SNOWFLAKESINK_KAMELET_INSTANCE_URL_CONF = "camel.kamelet.snowflake-sink.instanceUrl";
    public static final String CAMEL_SINK_SNOWFLAKESINK_KAMELET_INSTANCE_URL_DOC = "The Instance url Example: instance.snowflakecomputing.com";
    public static final String CAMEL_SINK_SNOWFLAKESINK_KAMELET_INSTANCE_URL_DEFAULT = null;
    public static final String CAMEL_SINK_SNOWFLAKESINK_KAMELET_USERNAME_CONF = "camel.kamelet.snowflake-sink.username";
    public static final String CAMEL_SINK_SNOWFLAKESINK_KAMELET_USERNAME_DOC = "The username to access a secured Snowflake Database.";
    public static final String CAMEL_SINK_SNOWFLAKESINK_KAMELET_USERNAME_DEFAULT = null;
    public static final String CAMEL_SINK_SNOWFLAKESINK_KAMELET_PASSWORD_CONF = "camel.kamelet.snowflake-sink.password";
    public static final String CAMEL_SINK_SNOWFLAKESINK_KAMELET_PASSWORD_DOC = "The password to access a secured Snowflake Database.";
    public static final String CAMEL_SINK_SNOWFLAKESINK_KAMELET_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SINK_SNOWFLAKESINK_KAMELET_QUERY_CONF = "camel.kamelet.snowflake-sink.query";
    public static final String CAMEL_SINK_SNOWFLAKESINK_KAMELET_QUERY_DOC = "The query to execute against the Snowflake Database. Example: INSERT INTO accounts (username,city) VALUES (:#username,:#city)";
    public static final String CAMEL_SINK_SNOWFLAKESINK_KAMELET_QUERY_DEFAULT = null;
    public static final String CAMEL_SINK_SNOWFLAKESINK_KAMELET_DATABASE_NAME_CONF = "camel.kamelet.snowflake-sink.databaseName";
    public static final String CAMEL_SINK_SNOWFLAKESINK_KAMELET_DATABASE_NAME_DOC = "The name of the Snowflake Database.";
    public static final String CAMEL_SINK_SNOWFLAKESINK_KAMELET_DATABASE_NAME_DEFAULT = null;

    public CamelSnowflakesinkSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelSnowflakesinkSinkConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_SNOWFLAKESINK_KAMELET_INSTANCE_URL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SNOWFLAKESINK_KAMELET_INSTANCE_URL_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_SNOWFLAKESINK_KAMELET_INSTANCE_URL_DOC);
        conf.define(CAMEL_SINK_SNOWFLAKESINK_KAMELET_USERNAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SNOWFLAKESINK_KAMELET_USERNAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_SNOWFLAKESINK_KAMELET_USERNAME_DOC);
        conf.define(CAMEL_SINK_SNOWFLAKESINK_KAMELET_PASSWORD_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_SNOWFLAKESINK_KAMELET_PASSWORD_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_SNOWFLAKESINK_KAMELET_PASSWORD_DOC);
        conf.define(CAMEL_SINK_SNOWFLAKESINK_KAMELET_QUERY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SNOWFLAKESINK_KAMELET_QUERY_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_SNOWFLAKESINK_KAMELET_QUERY_DOC);
        conf.define(CAMEL_SINK_SNOWFLAKESINK_KAMELET_DATABASE_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SNOWFLAKESINK_KAMELET_DATABASE_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNOWFLAKESINK_KAMELET_DATABASE_NAME_DOC);
        return conf;
    }
}