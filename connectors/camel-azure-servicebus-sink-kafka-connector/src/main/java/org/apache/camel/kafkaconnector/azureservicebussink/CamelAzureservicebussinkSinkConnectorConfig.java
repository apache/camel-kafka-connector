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
 */package org.apache.camel.kafkaconnector.azureservicebussink;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelAzureservicebussinkSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_AZURESERVICEBUSSINK_KAMELET_TOPIC_OR_QUEUE_NAME_CONF = "camel.kamelet.azure-servicebus-sink.topicOrQueueName";
    public static final String CAMEL_SINK_AZURESERVICEBUSSINK_KAMELET_TOPIC_OR_QUEUE_NAME_DOC = "Topic Or Queue Name for the Azure Servicebus instance";
    public static final String CAMEL_SINK_AZURESERVICEBUSSINK_KAMELET_TOPIC_OR_QUEUE_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_AZURESERVICEBUSSINK_KAMELET_CONNECTION_STRING_CONF = "camel.kamelet.azure-servicebus-sink.connectionString";
    public static final String CAMEL_SINK_AZURESERVICEBUSSINK_KAMELET_CONNECTION_STRING_DOC = "Connection String for Azure Servicebus instance";
    public static final String CAMEL_SINK_AZURESERVICEBUSSINK_KAMELET_CONNECTION_STRING_DEFAULT = null;

    public CamelAzureservicebussinkSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelAzureservicebussinkSinkConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_AZURESERVICEBUSSINK_KAMELET_TOPIC_OR_QUEUE_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZURESERVICEBUSSINK_KAMELET_TOPIC_OR_QUEUE_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AZURESERVICEBUSSINK_KAMELET_TOPIC_OR_QUEUE_NAME_DOC);
        conf.define(CAMEL_SINK_AZURESERVICEBUSSINK_KAMELET_CONNECTION_STRING_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_AZURESERVICEBUSSINK_KAMELET_CONNECTION_STRING_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AZURESERVICEBUSSINK_KAMELET_CONNECTION_STRING_DOC);
        return conf;
    }
}