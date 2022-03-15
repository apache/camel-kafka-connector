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
package org.apache.camel.kafkaconnector.awss3streaminguploadsink;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelAwss3streaminguploadsinkSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_BUCKET_NAME_OR_ARN_CONF = "camel.kamelet.aws-s3-streaming-upload-sink.bucketNameOrArn";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_BUCKET_NAME_OR_ARN_DOC = "The S3 Bucket name or ARN.";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_BUCKET_NAME_OR_ARN_DEFAULT = null;
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_ACCESS_KEY_CONF = "camel.kamelet.aws-s3-streaming-upload-sink.accessKey";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_ACCESS_KEY_DOC = "The access key obtained from AWS.";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_ACCESS_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_SECRET_KEY_CONF = "camel.kamelet.aws-s3-streaming-upload-sink.secretKey";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_SECRET_KEY_DOC = "The secret key obtained from AWS.";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_SECRET_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_REGION_CONF = "camel.kamelet.aws-s3-streaming-upload-sink.region";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_REGION_DOC = "The AWS region to connect to. Example: eu-west-1";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_REGION_DEFAULT = null;
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_AUTO_CREATE_BUCKET_CONF = "camel.kamelet.aws-s3-streaming-upload-sink.autoCreateBucket";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_AUTO_CREATE_BUCKET_DOC = "Setting the autocreation of the S3 bucket bucketName.";
    public static final Boolean CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_AUTO_CREATE_BUCKET_DEFAULT = false;
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_STREAMING_UPLOAD_MODE_CONF = "camel.kamelet.aws-s3-streaming-upload-sink.streamingUploadMode";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_STREAMING_UPLOAD_MODE_DOC = "Setting the Streaming Upload Mode";
    public static final Boolean CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_STREAMING_UPLOAD_MODE_DEFAULT = true;
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_RESTARTING_POLICY_CONF = "camel.kamelet.aws-s3-streaming-upload-sink.restartingPolicy";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_RESTARTING_POLICY_DOC = "The restarting policy to use in streaming upload mode. There are 2 enums and the value can be one of override, lastPart";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_RESTARTING_POLICY_DEFAULT = "lastPart";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_BATCH_MESSAGE_NUMBER_CONF = "camel.kamelet.aws-s3-streaming-upload-sink.batchMessageNumber";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_BATCH_MESSAGE_NUMBER_DOC = "The number of messages composing a batch in streaming upload mode";
    public static final Integer CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_BATCH_MESSAGE_NUMBER_DEFAULT = 10;
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_BATCH_SIZE_CONF = "camel.kamelet.aws-s3-streaming-upload-sink.batchSize";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_BATCH_SIZE_DOC = "The batch size (in bytes) in streaming upload mode";
    public static final Integer CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_BATCH_SIZE_DEFAULT = 1000000;
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_STREAMING_UPLOAD_TIMEOUT_CONF = "camel.kamelet.aws-s3-streaming-upload-sink.streamingUploadTimeout";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_STREAMING_UPLOAD_TIMEOUT_DOC = "While streaming upload mode is true, this option set the timeout to complete upload";
    public static final Long CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_STREAMING_UPLOAD_TIMEOUT_DEFAULT = null;
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_NAMING_STRATEGY_CONF = "camel.kamelet.aws-s3-streaming-upload-sink.namingStrategy";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_NAMING_STRATEGY_DOC = "The naming strategy to use in streaming upload mode. There are 2 enums and the value can be one of progressive, random";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_NAMING_STRATEGY_DEFAULT = "progressive";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_KEY_NAME_CONF = "camel.kamelet.aws-s3-streaming-upload-sink.keyName";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_KEY_NAME_DOC = "Setting the key name for an element in the bucket through endpoint parameter. In Streaming Upload, with the default configuration, this will be the base for the progressive creation of files.";
    public static final String CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_KEY_NAME_DEFAULT = null;

    public CamelAwss3streaminguploadsinkSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelAwss3streaminguploadsinkSinkConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_BUCKET_NAME_OR_ARN_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_BUCKET_NAME_OR_ARN_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_BUCKET_NAME_OR_ARN_DOC);
        conf.define(CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_ACCESS_KEY_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_ACCESS_KEY_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_ACCESS_KEY_DOC);
        conf.define(CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_SECRET_KEY_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_SECRET_KEY_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_SECRET_KEY_DOC);
        conf.define(CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_REGION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_REGION_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_REGION_DOC);
        conf.define(CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_AUTO_CREATE_BUCKET_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_AUTO_CREATE_BUCKET_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_AUTO_CREATE_BUCKET_DOC);
        conf.define(CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_STREAMING_UPLOAD_MODE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_STREAMING_UPLOAD_MODE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_STREAMING_UPLOAD_MODE_DOC);
        conf.define(CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_RESTARTING_POLICY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_RESTARTING_POLICY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_RESTARTING_POLICY_DOC);
        conf.define(CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_BATCH_MESSAGE_NUMBER_CONF, ConfigDef.Type.INT, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_BATCH_MESSAGE_NUMBER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_BATCH_MESSAGE_NUMBER_DOC);
        conf.define(CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_BATCH_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_BATCH_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_BATCH_SIZE_DOC);
        conf.define(CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_STREAMING_UPLOAD_TIMEOUT_CONF, ConfigDef.Type.LONG, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_STREAMING_UPLOAD_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_STREAMING_UPLOAD_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_NAMING_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_NAMING_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_NAMING_STRATEGY_DOC);
        conf.define(CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_KEY_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_KEY_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AWSS3STREAMINGUPLOADSINK_KAMELET_KEY_NAME_DOC);
        return conf;
    }
}