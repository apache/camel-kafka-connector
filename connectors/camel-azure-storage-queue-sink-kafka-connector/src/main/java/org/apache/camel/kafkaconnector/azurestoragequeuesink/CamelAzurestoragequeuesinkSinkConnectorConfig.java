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
package org.apache.camel.kafkaconnector.azurestoragequeuesink;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelAzurestoragequeuesinkSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_AZURESTORAGEQUEUESINK_KAMELET_ACCOUNT_NAME_CONF = "camel.kamelet.azure-storage-queue-sink.accountName";
    public static final String CAMEL_SINK_AZURESTORAGEQUEUESINK_KAMELET_ACCOUNT_NAME_DOC = "The Azure Storage Queue account name.";
    public static final String CAMEL_SINK_AZURESTORAGEQUEUESINK_KAMELET_ACCOUNT_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_AZURESTORAGEQUEUESINK_KAMELET_QUEUE_NAME_CONF = "camel.kamelet.azure-storage-queue-sink.queueName";
    public static final String CAMEL_SINK_AZURESTORAGEQUEUESINK_KAMELET_QUEUE_NAME_DOC = "The Azure Storage Queue container name.";
    public static final String CAMEL_SINK_AZURESTORAGEQUEUESINK_KAMELET_QUEUE_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_AZURESTORAGEQUEUESINK_KAMELET_ACCESS_KEY_CONF = "camel.kamelet.azure-storage-queue-sink.accessKey";
    public static final String CAMEL_SINK_AZURESTORAGEQUEUESINK_KAMELET_ACCESS_KEY_DOC = "The Azure Storage Queue access Key.";
    public static final String CAMEL_SINK_AZURESTORAGEQUEUESINK_KAMELET_ACCESS_KEY_DEFAULT = null;

    public CamelAzurestoragequeuesinkSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelAzurestoragequeuesinkSinkConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_AZURESTORAGEQUEUESINK_KAMELET_ACCOUNT_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZURESTORAGEQUEUESINK_KAMELET_ACCOUNT_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AZURESTORAGEQUEUESINK_KAMELET_ACCOUNT_NAME_DOC);
        conf.define(CAMEL_SINK_AZURESTORAGEQUEUESINK_KAMELET_QUEUE_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZURESTORAGEQUEUESINK_KAMELET_QUEUE_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AZURESTORAGEQUEUESINK_KAMELET_QUEUE_NAME_DOC);
        conf.define(CAMEL_SINK_AZURESTORAGEQUEUESINK_KAMELET_ACCESS_KEY_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_AZURESTORAGEQUEUESINK_KAMELET_ACCESS_KEY_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AZURESTORAGEQUEUESINK_KAMELET_ACCESS_KEY_DOC);
        return conf;
    }
}