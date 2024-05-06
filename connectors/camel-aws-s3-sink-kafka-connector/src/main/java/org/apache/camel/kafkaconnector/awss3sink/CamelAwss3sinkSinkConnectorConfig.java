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
 */package org.apache.camel.kafkaconnector.awss3sink;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelAwss3sinkSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_BUCKET_NAME_OR_ARN_CONF = "camel.kamelet.aws-s3-sink.bucketNameOrArn";
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_BUCKET_NAME_OR_ARN_DOC = "The S3 Bucket name or Amazon Resource Name (ARN).";
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_BUCKET_NAME_OR_ARN_DEFAULT = null;
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_ACCESS_KEY_CONF = "camel.kamelet.aws-s3-sink.accessKey";
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_ACCESS_KEY_DOC = "The access key obtained from AWS.";
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_ACCESS_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_SECRET_KEY_CONF = "camel.kamelet.aws-s3-sink.secretKey";
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_SECRET_KEY_DOC = "The secret key obtained from AWS.";
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_SECRET_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_REGION_CONF = "camel.kamelet.aws-s3-sink.region";
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_REGION_DOC = "The AWS region to access.";
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_REGION_DEFAULT = null;
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_AUTO_CREATE_BUCKET_CONF = "camel.kamelet.aws-s3-sink.autoCreateBucket";
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_AUTO_CREATE_BUCKET_DOC = "Specifies to automatically create the S3 bucket.";
    public static final Boolean CAMEL_SINK_AWSS3SINK_KAMELET_AUTO_CREATE_BUCKET_DEFAULT = false;
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_CONF = "camel.kamelet.aws-s3-sink.useDefaultCredentialsProvider";
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DOC = "If true, the S3 client loads credentials through a default credentials provider. If false, it uses the basic authentication method (access key and secret key).";
    public static final Boolean CAMEL_SINK_AWSS3SINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DEFAULT = false;
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_URI_ENDPOINT_OVERRIDE_CONF = "camel.kamelet.aws-s3-sink.uriEndpointOverride";
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_URI_ENDPOINT_OVERRIDE_DOC = "The overriding endpoint URI. To use this option, you must also select the `overrideEndpoint` option.";
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_URI_ENDPOINT_OVERRIDE_DEFAULT = null;
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_OVERRIDE_ENDPOINT_CONF = "camel.kamelet.aws-s3-sink.overrideEndpoint";
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_OVERRIDE_ENDPOINT_DOC = "Select this option to override the endpoint URI. To use this option, you must also provide a URI for the `uriEndpointOverride` option.";
    public static final Boolean CAMEL_SINK_AWSS3SINK_KAMELET_OVERRIDE_ENDPOINT_DEFAULT = false;
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_FORCE_PATH_STYLE_CONF = "camel.kamelet.aws-s3-sink.forcePathStyle";
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_FORCE_PATH_STYLE_DOC = "Forces path style when accessing AWS S3 buckets.";
    public static final Boolean CAMEL_SINK_AWSS3SINK_KAMELET_FORCE_PATH_STYLE_DEFAULT = false;
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_KEY_NAME_CONF = "camel.kamelet.aws-s3-sink.keyName";
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_KEY_NAME_DOC = "The key name for saving an element in the bucket.";
    public static final String CAMEL_SINK_AWSS3SINK_KAMELET_KEY_NAME_DEFAULT = null;

    public CamelAwss3sinkSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelAwss3sinkSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_AWSS3SINK_KAMELET_BUCKET_NAME_OR_ARN_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWSS3SINK_KAMELET_BUCKET_NAME_OR_ARN_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AWSS3SINK_KAMELET_BUCKET_NAME_OR_ARN_DOC);
        conf.define(CAMEL_SINK_AWSS3SINK_KAMELET_ACCESS_KEY_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_AWSS3SINK_KAMELET_ACCESS_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSS3SINK_KAMELET_ACCESS_KEY_DOC);
        conf.define(CAMEL_SINK_AWSS3SINK_KAMELET_SECRET_KEY_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_AWSS3SINK_KAMELET_SECRET_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSS3SINK_KAMELET_SECRET_KEY_DOC);
        conf.define(CAMEL_SINK_AWSS3SINK_KAMELET_REGION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWSS3SINK_KAMELET_REGION_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AWSS3SINK_KAMELET_REGION_DOC);
        conf.define(CAMEL_SINK_AWSS3SINK_KAMELET_AUTO_CREATE_BUCKET_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWSS3SINK_KAMELET_AUTO_CREATE_BUCKET_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSS3SINK_KAMELET_AUTO_CREATE_BUCKET_DOC);
        conf.define(CAMEL_SINK_AWSS3SINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWSS3SINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSS3SINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DOC);
        conf.define(CAMEL_SINK_AWSS3SINK_KAMELET_URI_ENDPOINT_OVERRIDE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWSS3SINK_KAMELET_URI_ENDPOINT_OVERRIDE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSS3SINK_KAMELET_URI_ENDPOINT_OVERRIDE_DOC);
        conf.define(CAMEL_SINK_AWSS3SINK_KAMELET_OVERRIDE_ENDPOINT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWSS3SINK_KAMELET_OVERRIDE_ENDPOINT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSS3SINK_KAMELET_OVERRIDE_ENDPOINT_DOC);
        conf.define(CAMEL_SINK_AWSS3SINK_KAMELET_FORCE_PATH_STYLE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWSS3SINK_KAMELET_FORCE_PATH_STYLE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSS3SINK_KAMELET_FORCE_PATH_STYLE_DOC);
        conf.define(CAMEL_SINK_AWSS3SINK_KAMELET_KEY_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWSS3SINK_KAMELET_KEY_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSS3SINK_KAMELET_KEY_NAME_DOC);
        return conf;
    }
}