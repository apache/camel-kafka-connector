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
 */package org.apache.camel.kafkaconnector.azurestorageblobsource;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelAzurestorageblobsourceSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_PERIOD_CONF = "camel.kamelet.azure-storage-blob-source.period";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_PERIOD_DOC = "The interval (in milliseconds) between fetches to the Azure Storage Container.";
    public static final Integer CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_PERIOD_DEFAULT = 10000;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_ACCOUNT_NAME_CONF = "camel.kamelet.azure-storage-blob-source.accountName";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_ACCOUNT_NAME_DOC = "The Azure Storage Blob account name.";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_ACCOUNT_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_CONTAINER_NAME_CONF = "camel.kamelet.azure-storage-blob-source.containerName";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_CONTAINER_NAME_DOC = "The Azure Storage Blob container name.";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_CONTAINER_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_ACCESS_KEY_CONF = "camel.kamelet.azure-storage-blob-source.accessKey";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_ACCESS_KEY_DOC = "The Azure Storage Blob access key.";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_ACCESS_KEY_DEFAULT = null;

    public CamelAzurestorageblobsourceSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelAzurestorageblobsourceSourceConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_PERIOD_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_PERIOD_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_PERIOD_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_ACCOUNT_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_ACCOUNT_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_ACCOUNT_NAME_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_CONTAINER_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_CONTAINER_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_CONTAINER_NAME_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_ACCESS_KEY_CONF, ConfigDef.Type.PASSWORD, CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_ACCESS_KEY_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_AZURESTORAGEBLOBSOURCE_KAMELET_ACCESS_KEY_DOC);
        return conf;
    }
}