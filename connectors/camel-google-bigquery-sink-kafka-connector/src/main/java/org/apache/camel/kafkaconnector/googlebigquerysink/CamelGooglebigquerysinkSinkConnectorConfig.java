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
 */package org.apache.camel.kafkaconnector.googlebigquerysink;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelGooglebigquerysinkSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_PROJECT_ID_CONF = "camel.kamelet.google-bigquery-sink.projectId";
    public static final String CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_PROJECT_ID_DOC = "Google Cloud Project id";
    public static final String CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_PROJECT_ID_DEFAULT = null;
    public static final String CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_DATASET_CONF = "camel.kamelet.google-bigquery-sink.dataset";
    public static final String CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_DATASET_DOC = "The Big Query Dataset Id";
    public static final String CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_DATASET_DEFAULT = null;
    public static final String CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_TABLE_CONF = "camel.kamelet.google-bigquery-sink.table";
    public static final String CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_TABLE_DOC = "The Big Query Table Id";
    public static final String CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_TABLE_DEFAULT = null;
    public static final String CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_CREDENTIALS_FILE_LOCATION_CONF = "camel.kamelet.google-bigquery-sink.credentialsFileLocation";
    public static final String CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_CREDENTIALS_FILE_LOCATION_DOC = "The credential to access Google Cloud Platform api services";
    public static final String CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_CREDENTIALS_FILE_LOCATION_DEFAULT = null;

    public CamelGooglebigquerysinkSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelGooglebigquerysinkSinkConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_PROJECT_ID_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_PROJECT_ID_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_PROJECT_ID_DOC);
        conf.define(CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_DATASET_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_DATASET_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_DATASET_DOC);
        conf.define(CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_TABLE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_TABLE_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_TABLE_DOC);
        conf.define(CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_CREDENTIALS_FILE_LOCATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_CREDENTIALS_FILE_LOCATION_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_GOOGLEBIGQUERYSINK_KAMELET_CREDENTIALS_FILE_LOCATION_DOC);
        return conf;
    }
}