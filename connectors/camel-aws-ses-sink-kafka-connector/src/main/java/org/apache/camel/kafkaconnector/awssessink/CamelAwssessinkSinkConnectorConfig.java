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
 */package org.apache.camel.kafkaconnector.awssessink;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelAwssessinkSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_AWSSESSINK_KAMELET_FROM_CONF = "camel.kamelet.aws-ses-sink.from";
    public static final String CAMEL_SINK_AWSSESSINK_KAMELET_FROM_DOC = "From address Example: user@example.com";
    public static final String CAMEL_SINK_AWSSESSINK_KAMELET_FROM_DEFAULT = null;
    public static final String CAMEL_SINK_AWSSESSINK_KAMELET_ACCESS_KEY_CONF = "camel.kamelet.aws-ses-sink.accessKey";
    public static final String CAMEL_SINK_AWSSESSINK_KAMELET_ACCESS_KEY_DOC = "The access key obtained from AWS.";
    public static final String CAMEL_SINK_AWSSESSINK_KAMELET_ACCESS_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_AWSSESSINK_KAMELET_SECRET_KEY_CONF = "camel.kamelet.aws-ses-sink.secretKey";
    public static final String CAMEL_SINK_AWSSESSINK_KAMELET_SECRET_KEY_DOC = "The secret key obtained from AWS.";
    public static final String CAMEL_SINK_AWSSESSINK_KAMELET_SECRET_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_AWSSESSINK_KAMELET_REGION_CONF = "camel.kamelet.aws-ses-sink.region";
    public static final String CAMEL_SINK_AWSSESSINK_KAMELET_REGION_DOC = "The AWS region to access.";
    public static final String CAMEL_SINK_AWSSESSINK_KAMELET_REGION_DEFAULT = null;
    public static final String CAMEL_SINK_AWSSESSINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_CONF = "camel.kamelet.aws-ses-sink.useDefaultCredentialsProvider";
    public static final String CAMEL_SINK_AWSSESSINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DOC = "If true, the SES client loads credentials through a default credentials provider. If false, it uses the basic authentication method (access key and secret key).";
    public static final Boolean CAMEL_SINK_AWSSESSINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DEFAULT = false;

    public CamelAwssessinkSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelAwssessinkSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_AWSSESSINK_KAMELET_FROM_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWSSESSINK_KAMELET_FROM_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AWSSESSINK_KAMELET_FROM_DOC);
        conf.define(CAMEL_SINK_AWSSESSINK_KAMELET_ACCESS_KEY_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_AWSSESSINK_KAMELET_ACCESS_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSSESSINK_KAMELET_ACCESS_KEY_DOC);
        conf.define(CAMEL_SINK_AWSSESSINK_KAMELET_SECRET_KEY_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_AWSSESSINK_KAMELET_SECRET_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSSESSINK_KAMELET_SECRET_KEY_DOC);
        conf.define(CAMEL_SINK_AWSSESSINK_KAMELET_REGION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWSSESSINK_KAMELET_REGION_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AWSSESSINK_KAMELET_REGION_DOC);
        conf.define(CAMEL_SINK_AWSSESSINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWSSESSINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSSESSINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DOC);
        return conf;
    }
}