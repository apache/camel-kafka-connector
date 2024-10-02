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
package org.apache.camel.kafkaconnector.awslambdasink;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelAwslambdasinkSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_FUNCTION_CONF = "camel.kamelet.aws-lambda-sink.function";
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_FUNCTION_DOC = "The Lambda Function name.";
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_FUNCTION_DEFAULT = null;
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_ACCESS_KEY_CONF = "camel.kamelet.aws-lambda-sink.accessKey";
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_ACCESS_KEY_DOC = "The access key obtained from AWS.";
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_ACCESS_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_SECRET_KEY_CONF = "camel.kamelet.aws-lambda-sink.secretKey";
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_SECRET_KEY_DOC = "The secret key obtained from AWS.";
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_SECRET_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_REGION_CONF = "camel.kamelet.aws-lambda-sink.region";
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_REGION_DOC = "The AWS region to access.";
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_REGION_DEFAULT = null;
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_CONF = "camel.kamelet.aws-lambda-sink.useDefaultCredentialsProvider";
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DOC = "If true, the Lambda client loads credentials through a default credentials provider. If false, it uses the basic authentication method (access key and secret key).";
    public static final Boolean CAMEL_SINK_AWSLAMBDASINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DEFAULT = false;
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_USE_PROFILE_CREDENTIALS_PROVIDER_CONF = "camel.kamelet.aws-lambda-sink.useProfileCredentialsProvider";
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_USE_PROFILE_CREDENTIALS_PROVIDER_DOC = "Set whether the Lambda client should expect to load credentials through a profile credentials provider.";
    public static final Boolean CAMEL_SINK_AWSLAMBDASINK_KAMELET_USE_PROFILE_CREDENTIALS_PROVIDER_DEFAULT = false;
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_USE_SESSION_CREDENTIALS_CONF = "camel.kamelet.aws-lambda-sink.useSessionCredentials";
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_USE_SESSION_CREDENTIALS_DOC = "Set whether the Lambda client should expect to use Session Credentials. This is useful in situation in which the user needs to assume a IAM role for doing operations in Lambda.";
    public static final Boolean CAMEL_SINK_AWSLAMBDASINK_KAMELET_USE_SESSION_CREDENTIALS_DEFAULT = false;
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_PROFILE_CREDENTIALS_NAME_CONF = "camel.kamelet.aws-lambda-sink.profileCredentialsName";
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_PROFILE_CREDENTIALS_NAME_DOC = "If using a profile credentials provider this parameter will set the profile name.";
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_PROFILE_CREDENTIALS_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_SESSION_TOKEN_CONF = "camel.kamelet.aws-lambda-sink.sessionToken";
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_SESSION_TOKEN_DOC = "Amazon AWS Session Token used when the user needs to assume a IAM role.";
    public static final String CAMEL_SINK_AWSLAMBDASINK_KAMELET_SESSION_TOKEN_DEFAULT = null;

    public CamelAwslambdasinkSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelAwslambdasinkSinkConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_AWSLAMBDASINK_KAMELET_FUNCTION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWSLAMBDASINK_KAMELET_FUNCTION_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AWSLAMBDASINK_KAMELET_FUNCTION_DOC);
        conf.define(CAMEL_SINK_AWSLAMBDASINK_KAMELET_ACCESS_KEY_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_AWSLAMBDASINK_KAMELET_ACCESS_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSLAMBDASINK_KAMELET_ACCESS_KEY_DOC);
        conf.define(CAMEL_SINK_AWSLAMBDASINK_KAMELET_SECRET_KEY_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_AWSLAMBDASINK_KAMELET_SECRET_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSLAMBDASINK_KAMELET_SECRET_KEY_DOC);
        conf.define(CAMEL_SINK_AWSLAMBDASINK_KAMELET_REGION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWSLAMBDASINK_KAMELET_REGION_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AWSLAMBDASINK_KAMELET_REGION_DOC);
        conf.define(CAMEL_SINK_AWSLAMBDASINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWSLAMBDASINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSLAMBDASINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DOC);
        conf.define(CAMEL_SINK_AWSLAMBDASINK_KAMELET_USE_PROFILE_CREDENTIALS_PROVIDER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWSLAMBDASINK_KAMELET_USE_PROFILE_CREDENTIALS_PROVIDER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSLAMBDASINK_KAMELET_USE_PROFILE_CREDENTIALS_PROVIDER_DOC);
        conf.define(CAMEL_SINK_AWSLAMBDASINK_KAMELET_USE_SESSION_CREDENTIALS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWSLAMBDASINK_KAMELET_USE_SESSION_CREDENTIALS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSLAMBDASINK_KAMELET_USE_SESSION_CREDENTIALS_DOC);
        conf.define(CAMEL_SINK_AWSLAMBDASINK_KAMELET_PROFILE_CREDENTIALS_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWSLAMBDASINK_KAMELET_PROFILE_CREDENTIALS_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSLAMBDASINK_KAMELET_PROFILE_CREDENTIALS_NAME_DOC);
        conf.define(CAMEL_SINK_AWSLAMBDASINK_KAMELET_SESSION_TOKEN_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_AWSLAMBDASINK_KAMELET_SESSION_TOKEN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSLAMBDASINK_KAMELET_SESSION_TOKEN_DOC);
        return conf;
    }
}