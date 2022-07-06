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
 */package org.apache.camel.kafkaconnector.awssqsfifosink;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelAwssqsfifosinkSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_QUEUE_NAME_OR_ARN_CONF = "camel.kamelet.aws-sqs-fifo-sink.queueNameOrArn";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_QUEUE_NAME_OR_ARN_DOC = "The SQS Queue name or ARN";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_QUEUE_NAME_OR_ARN_DEFAULT = null;
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_ACCESS_KEY_CONF = "camel.kamelet.aws-sqs-fifo-sink.accessKey";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_ACCESS_KEY_DOC = "The access key obtained from AWS";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_ACCESS_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_SECRET_KEY_CONF = "camel.kamelet.aws-sqs-fifo-sink.secretKey";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_SECRET_KEY_DOC = "The secret key obtained from AWS";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_SECRET_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_REGION_CONF = "camel.kamelet.aws-sqs-fifo-sink.region";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_REGION_DOC = "The AWS region to connect to Example: eu-west-1";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_REGION_DEFAULT = null;
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_CONTENT_BASED_DEDUPLICATION_CONF = "camel.kamelet.aws-sqs-fifo-sink.contentBasedDeduplication";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_CONTENT_BASED_DEDUPLICATION_DOC = "Use content-based deduplication (should be enabled in the SQS FIFO queue first)";
    public static final Boolean CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_CONTENT_BASED_DEDUPLICATION_DEFAULT = false;
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_AUTO_CREATE_QUEUE_CONF = "camel.kamelet.aws-sqs-fifo-sink.autoCreateQueue";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_AUTO_CREATE_QUEUE_DOC = "Setting the autocreation of the SQS queue.";
    public static final Boolean CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_AUTO_CREATE_QUEUE_DEFAULT = false;
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_AMAZON_AWSHOST_CONF = "camel.kamelet.aws-sqs-fifo-sink.amazonAWSHost";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_AMAZON_AWSHOST_DOC = "The hostname of the Amazon AWS cloud.";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_AMAZON_AWSHOST_DEFAULT = "amazonaws.com";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_PROTOCOL_CONF = "camel.kamelet.aws-sqs-fifo-sink.protocol";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_PROTOCOL_DOC = "The underlying protocol used to communicate with SQS Example: http or https";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_PROTOCOL_DEFAULT = "https";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_CONF = "camel.kamelet.aws-sqs-fifo-sink.useDefaultCredentialsProvider";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DOC = "Set whether the SQS client should expect to load credentials through a default credentials provider or to expect static credentials to be passed in.";
    public static final Boolean CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DEFAULT = false;
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_URI_ENDPOINT_OVERRIDE_CONF = "camel.kamelet.aws-sqs-fifo-sink.uriEndpointOverride";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_URI_ENDPOINT_OVERRIDE_DOC = "Set the overriding endpoint URI. This option needs to be used in combination with overrideEndpoint option.";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_URI_ENDPOINT_OVERRIDE_DEFAULT = null;
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_OVERRIDE_ENDPOINT_CONF = "camel.kamelet.aws-sqs-fifo-sink.overrideEndpoint";
    public static final String CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_OVERRIDE_ENDPOINT_DOC = "Set the need for overiding the endpoint URI. This option needs to be used in combination with uriEndpointOverride setting.";
    public static final Boolean CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_OVERRIDE_ENDPOINT_DEFAULT = false;

    public CamelAwssqsfifosinkSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelAwssqsfifosinkSinkConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_QUEUE_NAME_OR_ARN_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_QUEUE_NAME_OR_ARN_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_QUEUE_NAME_OR_ARN_DOC);
        conf.define(CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_ACCESS_KEY_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_ACCESS_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_ACCESS_KEY_DOC);
        conf.define(CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_SECRET_KEY_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_SECRET_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_SECRET_KEY_DOC);
        conf.define(CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_REGION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_REGION_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_REGION_DOC);
        conf.define(CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_CONTENT_BASED_DEDUPLICATION_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_CONTENT_BASED_DEDUPLICATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_CONTENT_BASED_DEDUPLICATION_DOC);
        conf.define(CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_AUTO_CREATE_QUEUE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_AUTO_CREATE_QUEUE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_AUTO_CREATE_QUEUE_DOC);
        conf.define(CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_AMAZON_AWSHOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_AMAZON_AWSHOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_AMAZON_AWSHOST_DOC);
        conf.define(CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_PROTOCOL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_PROTOCOL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_PROTOCOL_DOC);
        conf.define(CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_USE_DEFAULT_CREDENTIALS_PROVIDER_DOC);
        conf.define(CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_URI_ENDPOINT_OVERRIDE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_URI_ENDPOINT_OVERRIDE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_URI_ENDPOINT_OVERRIDE_DOC);
        conf.define(CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_OVERRIDE_ENDPOINT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_OVERRIDE_ENDPOINT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWSSQSFIFOSINK_KAMELET_OVERRIDE_ENDPOINT_DOC);
        return conf;
    }
}