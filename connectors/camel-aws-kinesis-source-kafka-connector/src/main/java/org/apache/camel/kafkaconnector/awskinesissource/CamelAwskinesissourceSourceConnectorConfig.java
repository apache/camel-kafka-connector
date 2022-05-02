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
package org.apache.camel.kafkaconnector.awskinesissource;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelAwskinesissourceSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_STREAM_CONF = "camel.kamelet.aws-kinesis-source.stream";
    public static final String CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_STREAM_DOC = "The Kinesis stream that you want to access (needs to be created in advance)";
    public static final String CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_STREAM_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_ACCESS_KEY_CONF = "camel.kamelet.aws-kinesis-source.accessKey";
    public static final String CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_ACCESS_KEY_DOC = "The access key obtained from AWS";
    public static final String CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_ACCESS_KEY_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_SECRET_KEY_CONF = "camel.kamelet.aws-kinesis-source.secretKey";
    public static final String CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_SECRET_KEY_DOC = "The secret key obtained from AWS";
    public static final String CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_SECRET_KEY_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_REGION_CONF = "camel.kamelet.aws-kinesis-source.region";
    public static final String CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_REGION_DOC = "The AWS region to connect to Example: eu-west-1";
    public static final String CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_REGION_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_CONF = "camel.kamelet.aws-kinesis-source.useDefaultCredentialsProvider";
    public static final String CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DOC = "Set whether the Kinesis client should expect to load credentials through a default credentials provider or to expect static credentials to be passed in.";
    public static final Boolean CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DEFAULT = false;
    public static final String CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_URI_ENDPOINT_OVERRIDE_CONF = "camel.kamelet.aws-kinesis-source.uriEndpointOverride";
    public static final String CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_URI_ENDPOINT_OVERRIDE_DOC = "Set the overriding endpoint URI. This option needs to be used in combination with overrideEndpoint option.";
    public static final String CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_URI_ENDPOINT_OVERRIDE_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_OVERRIDE_ENDPOINT_CONF = "camel.kamelet.aws-kinesis-source.overrideEndpoint";
    public static final String CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_OVERRIDE_ENDPOINT_DOC = "Set the need for overiding the endpoint URI. This option needs to be used in combination with uriEndpointOverride setting.";
    public static final Boolean CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_OVERRIDE_ENDPOINT_DEFAULT = false;

    public CamelAwskinesissourceSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelAwskinesissourceSourceConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_STREAM_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_STREAM_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_STREAM_DOC);
        conf.define(CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_ACCESS_KEY_CONF, ConfigDef.Type.PASSWORD, CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_ACCESS_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_ACCESS_KEY_DOC);
        conf.define(CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_SECRET_KEY_CONF, ConfigDef.Type.PASSWORD, CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_SECRET_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_SECRET_KEY_DOC);
        conf.define(CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_REGION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_REGION_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_REGION_DOC);
        conf.define(CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DOC);
        conf.define(CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_URI_ENDPOINT_OVERRIDE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_URI_ENDPOINT_OVERRIDE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_URI_ENDPOINT_OVERRIDE_DOC);
        conf.define(CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_OVERRIDE_ENDPOINT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_OVERRIDE_ENDPOINT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWSKINESISSOURCE_KAMELET_OVERRIDE_ENDPOINT_DOC);
        return conf;
    }
}