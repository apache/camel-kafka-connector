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
package org.apache.camel.kafkaconnector.aws2sqs;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelAws2sqsSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_AWS2SQS_PATH_QUEUE_NAME_OR_ARN_CONF = "camel.sink.path.queueNameOrArn";
    public static final String CAMEL_SINK_AWS2SQS_PATH_QUEUE_NAME_OR_ARN_DOC = "Queue name or ARN";
    public static final String CAMEL_SINK_AWS2SQS_PATH_QUEUE_NAME_OR_ARN_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_AMAZON_AWSHOST_CONF = "camel.sink.endpoint.amazonAWSHost";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_AMAZON_AWSHOST_DOC = "The hostname of the Amazon AWS cloud.";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_AMAZON_AWSHOST_DEFAULT = "amazonaws.com";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_AMAZON_SQSCLIENT_CONF = "camel.sink.endpoint.amazonSQSClient";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_AMAZON_SQSCLIENT_DOC = "To use the AmazonSQS as client";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_AMAZON_SQSCLIENT_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_AUTO_CREATE_QUEUE_CONF = "camel.sink.endpoint.autoCreateQueue";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_AUTO_CREATE_QUEUE_DOC = "Setting the autocreation of the queue";
    public static final Boolean CAMEL_SINK_AWS2SQS_ENDPOINT_AUTO_CREATE_QUEUE_DEFAULT = true;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_HEADER_FILTER_STRATEGY_CONF = "camel.sink.endpoint.headerFilterStrategy";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_HEADER_FILTER_STRATEGY_DOC = "To use a custom HeaderFilterStrategy to map headers to/from Camel.";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_HEADER_FILTER_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_PROTOCOL_CONF = "camel.sink.endpoint.protocol";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_PROTOCOL_DOC = "The underlying protocol used to communicate with SQS";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_PROTOCOL_DEFAULT = "https";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_PROXY_PROTOCOL_CONF = "camel.sink.endpoint.proxyProtocol";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_PROXY_PROTOCOL_DOC = "To define a proxy protocol when instantiating the SQS client One of: [HTTP] [HTTPS]";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_PROXY_PROTOCOL_DEFAULT = "HTTPS";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_QUEUE_OWNER_AWSACCOUNT_ID_CONF = "camel.sink.endpoint.queueOwnerAWSAccountId";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_QUEUE_OWNER_AWSACCOUNT_ID_DOC = "Specify the queue owner aws account id when you need to connect the queue with different account owner.";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_QUEUE_OWNER_AWSACCOUNT_ID_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_REGION_CONF = "camel.sink.endpoint.region";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_REGION_DOC = "The region in which SQS client needs to work. When using this parameter, the configuration will expect the lowercase name of the region (for example ap-east-1) You'll need to use the name Region.EU_WEST_1.id()";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_REGION_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_TRUST_ALL_CERTIFICATES_CONF = "camel.sink.endpoint.trustAllCertificates";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_TRUST_ALL_CERTIFICATES_DOC = "If we want to trust all certificates in case of overriding the endpoint";
    public static final Boolean CAMEL_SINK_AWS2SQS_ENDPOINT_TRUST_ALL_CERTIFICATES_DEFAULT = false;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_USE_IAMCREDENTIALS_CONF = "camel.sink.endpoint.useIAMCredentials";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_USE_IAMCREDENTIALS_DOC = "Set whether the SQS client should expect to load credentials on an AWS infra instance or to expect static credentials to be passed in.";
    public static final Boolean CAMEL_SINK_AWS2SQS_ENDPOINT_USE_IAMCREDENTIALS_DEFAULT = false;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_DELAY_SECONDS_CONF = "camel.sink.endpoint.delaySeconds";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_DELAY_SECONDS_DOC = "Delay sending messages for a number of seconds.";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_DELAY_SECONDS_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_AWS2SQS_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_MESSAGE_DEDUPLICATION_ID_STRATEGY_CONF = "camel.sink.endpoint.messageDeduplicationIdStrategy";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_MESSAGE_DEDUPLICATION_ID_STRATEGY_DOC = "Only for FIFO queues. Strategy for setting the messageDeduplicationId on the message. Can be one of the following options: useExchangeId, useContentBasedDeduplication. For the useContentBasedDeduplication option, no messageDeduplicationId will be set on the message. One of: [useExchangeId] [useContentBasedDeduplication]";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_MESSAGE_DEDUPLICATION_ID_STRATEGY_DEFAULT = "useExchangeId";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_MESSAGE_GROUP_ID_STRATEGY_CONF = "camel.sink.endpoint.messageGroupIdStrategy";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_MESSAGE_GROUP_ID_STRATEGY_DOC = "Only for FIFO queues. Strategy for setting the messageGroupId on the message. Can be one of the following options: useConstant, useExchangeId, usePropertyValue. For the usePropertyValue option, the value of property CamelAwsMessageGroupId will be used. One of: [useConstant] [useExchangeId] [usePropertyValue]";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_MESSAGE_GROUP_ID_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_OPERATION_CONF = "camel.sink.endpoint.operation";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_OPERATION_DOC = "The operation to do in case the user don't want to send only a message One of: [sendBatchMessage] [deleteMessage] [listQueues] [purgeQueue]";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_OPERATION_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_DELAY_QUEUE_CONF = "camel.sink.endpoint.delayQueue";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_DELAY_QUEUE_DOC = "Define if you want to apply delaySeconds option to the queue or on single messages";
    public static final Boolean CAMEL_SINK_AWS2SQS_ENDPOINT_DELAY_QUEUE_DEFAULT = false;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_QUEUE_URL_CONF = "camel.sink.endpoint.queueUrl";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_QUEUE_URL_DOC = "To define the queueUrl explicitly. All other parameters, which would influence the queueUrl, are ignored. This parameter is intended to be used, to connect to a mock implementation of SQS, for testing purposes.";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_QUEUE_URL_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SINK_AWS2SQS_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_PROXY_HOST_CONF = "camel.sink.endpoint.proxyHost";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_PROXY_HOST_DOC = "To define a proxy host when instantiating the SQS client";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_PROXY_HOST_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_PROXY_PORT_CONF = "camel.sink.endpoint.proxyPort";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_PROXY_PORT_DOC = "To define a proxy port when instantiating the SQS client";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_PROXY_PORT_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_MAXIMUM_MESSAGE_SIZE_CONF = "camel.sink.endpoint.maximumMessageSize";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_MAXIMUM_MESSAGE_SIZE_DOC = "The maximumMessageSize (in bytes) an SQS message can contain for this queue.";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_MAXIMUM_MESSAGE_SIZE_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_MESSAGE_RETENTION_PERIOD_CONF = "camel.sink.endpoint.messageRetentionPeriod";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_MESSAGE_RETENTION_PERIOD_DOC = "The messageRetentionPeriod (in seconds) a message will be retained by SQS for this queue.";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_MESSAGE_RETENTION_PERIOD_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_POLICY_CONF = "camel.sink.endpoint.policy";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_POLICY_DOC = "The policy for this queue";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_POLICY_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_RECEIVE_MESSAGE_WAIT_TIME_SECONDS_CONF = "camel.sink.endpoint.receiveMessageWaitTimeSeconds";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_RECEIVE_MESSAGE_WAIT_TIME_SECONDS_DOC = "If you do not specify WaitTimeSeconds in the request, the queue attribute ReceiveMessageWaitTimeSeconds is used to determine how long to wait.";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_RECEIVE_MESSAGE_WAIT_TIME_SECONDS_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_REDRIVE_POLICY_CONF = "camel.sink.endpoint.redrivePolicy";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_REDRIVE_POLICY_DOC = "Specify the policy that send message to DeadLetter queue. See detail at Amazon docs.";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_REDRIVE_POLICY_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_ACCESS_KEY_CONF = "camel.sink.endpoint.accessKey";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_ACCESS_KEY_DOC = "Amazon AWS Access Key";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_ACCESS_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_SECRET_KEY_CONF = "camel.sink.endpoint.secretKey";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_SECRET_KEY_DOC = "Amazon AWS Secret Key";
    public static final String CAMEL_SINK_AWS2SQS_ENDPOINT_SECRET_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_AMAZON_AWSHOST_CONF = "camel.component.aws2-sqs.amazonAWSHost";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_AMAZON_AWSHOST_DOC = "The hostname of the Amazon AWS cloud.";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_AMAZON_AWSHOST_DEFAULT = "amazonaws.com";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_AMAZON_SQSCLIENT_CONF = "camel.component.aws2-sqs.amazonSQSClient";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_AMAZON_SQSCLIENT_DOC = "To use the AmazonSQS as client";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_AMAZON_SQSCLIENT_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_AUTO_CREATE_QUEUE_CONF = "camel.component.aws2-sqs.autoCreateQueue";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_AUTO_CREATE_QUEUE_DOC = "Setting the autocreation of the queue";
    public static final Boolean CAMEL_SINK_AWS2SQS_COMPONENT_AUTO_CREATE_QUEUE_DEFAULT = true;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_CONFIGURATION_CONF = "camel.component.aws2-sqs.configuration";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_CONFIGURATION_DOC = "The AWS SQS default configuration";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_PROTOCOL_CONF = "camel.component.aws2-sqs.protocol";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_PROTOCOL_DOC = "The underlying protocol used to communicate with SQS";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_PROTOCOL_DEFAULT = "https";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_PROXY_PROTOCOL_CONF = "camel.component.aws2-sqs.proxyProtocol";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_PROXY_PROTOCOL_DOC = "To define a proxy protocol when instantiating the SQS client One of: [HTTP] [HTTPS]";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_PROXY_PROTOCOL_DEFAULT = "HTTPS";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_QUEUE_OWNER_AWSACCOUNT_ID_CONF = "camel.component.aws2-sqs.queueOwnerAWSAccountId";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_QUEUE_OWNER_AWSACCOUNT_ID_DOC = "Specify the queue owner aws account id when you need to connect the queue with different account owner.";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_QUEUE_OWNER_AWSACCOUNT_ID_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_REGION_CONF = "camel.component.aws2-sqs.region";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_REGION_DOC = "The region in which SQS client needs to work. When using this parameter, the configuration will expect the lowercase name of the region (for example ap-east-1) You'll need to use the name Region.EU_WEST_1.id()";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_REGION_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_TRUST_ALL_CERTIFICATES_CONF = "camel.component.aws2-sqs.trustAllCertificates";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_TRUST_ALL_CERTIFICATES_DOC = "If we want to trust all certificates in case of overriding the endpoint";
    public static final Boolean CAMEL_SINK_AWS2SQS_COMPONENT_TRUST_ALL_CERTIFICATES_DEFAULT = false;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_USE_IAMCREDENTIALS_CONF = "camel.component.aws2-sqs.useIAMCredentials";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_USE_IAMCREDENTIALS_DOC = "Set whether the SQS client should expect to load credentials on an AWS infra instance or to expect static credentials to be passed in.";
    public static final Boolean CAMEL_SINK_AWS2SQS_COMPONENT_USE_IAMCREDENTIALS_DEFAULT = false;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_DELAY_SECONDS_CONF = "camel.component.aws2-sqs.delaySeconds";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_DELAY_SECONDS_DOC = "Delay sending messages for a number of seconds.";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_DELAY_SECONDS_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.aws2-sqs.lazyStartProducer";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_AWS2SQS_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_MESSAGE_DEDUPLICATION_ID_STRATEGY_CONF = "camel.component.aws2-sqs.messageDeduplicationIdStrategy";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_MESSAGE_DEDUPLICATION_ID_STRATEGY_DOC = "Only for FIFO queues. Strategy for setting the messageDeduplicationId on the message. Can be one of the following options: useExchangeId, useContentBasedDeduplication. For the useContentBasedDeduplication option, no messageDeduplicationId will be set on the message. One of: [useExchangeId] [useContentBasedDeduplication]";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_MESSAGE_DEDUPLICATION_ID_STRATEGY_DEFAULT = "useExchangeId";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_MESSAGE_GROUP_ID_STRATEGY_CONF = "camel.component.aws2-sqs.messageGroupIdStrategy";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_MESSAGE_GROUP_ID_STRATEGY_DOC = "Only for FIFO queues. Strategy for setting the messageGroupId on the message. Can be one of the following options: useConstant, useExchangeId, usePropertyValue. For the usePropertyValue option, the value of property CamelAwsMessageGroupId will be used. One of: [useConstant] [useExchangeId] [usePropertyValue]";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_MESSAGE_GROUP_ID_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_OPERATION_CONF = "camel.component.aws2-sqs.operation";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_OPERATION_DOC = "The operation to do in case the user don't want to send only a message One of: [sendBatchMessage] [deleteMessage] [listQueues] [purgeQueue]";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_OPERATION_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.aws2-sqs.autowiredEnabled";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SINK_AWS2SQS_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_DELAY_QUEUE_CONF = "camel.component.aws2-sqs.delayQueue";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_DELAY_QUEUE_DOC = "Define if you want to apply delaySeconds option to the queue or on single messages";
    public static final Boolean CAMEL_SINK_AWS2SQS_COMPONENT_DELAY_QUEUE_DEFAULT = false;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_QUEUE_URL_CONF = "camel.component.aws2-sqs.queueUrl";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_QUEUE_URL_DOC = "To define the queueUrl explicitly. All other parameters, which would influence the queueUrl, are ignored. This parameter is intended to be used, to connect to a mock implementation of SQS, for testing purposes.";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_QUEUE_URL_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_PROXY_HOST_CONF = "camel.component.aws2-sqs.proxyHost";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_PROXY_HOST_DOC = "To define a proxy host when instantiating the SQS client";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_PROXY_HOST_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_PROXY_PORT_CONF = "camel.component.aws2-sqs.proxyPort";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_PROXY_PORT_DOC = "To define a proxy port when instantiating the SQS client";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_PROXY_PORT_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_MAXIMUM_MESSAGE_SIZE_CONF = "camel.component.aws2-sqs.maximumMessageSize";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_MAXIMUM_MESSAGE_SIZE_DOC = "The maximumMessageSize (in bytes) an SQS message can contain for this queue.";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_MAXIMUM_MESSAGE_SIZE_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_MESSAGE_RETENTION_PERIOD_CONF = "camel.component.aws2-sqs.messageRetentionPeriod";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_MESSAGE_RETENTION_PERIOD_DOC = "The messageRetentionPeriod (in seconds) a message will be retained by SQS for this queue.";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_MESSAGE_RETENTION_PERIOD_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_POLICY_CONF = "camel.component.aws2-sqs.policy";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_POLICY_DOC = "The policy for this queue";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_POLICY_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_RECEIVE_MESSAGE_WAIT_TIME_SECONDS_CONF = "camel.component.aws2-sqs.receiveMessageWaitTimeSeconds";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_RECEIVE_MESSAGE_WAIT_TIME_SECONDS_DOC = "If you do not specify WaitTimeSeconds in the request, the queue attribute ReceiveMessageWaitTimeSeconds is used to determine how long to wait.";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_RECEIVE_MESSAGE_WAIT_TIME_SECONDS_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_REDRIVE_POLICY_CONF = "camel.component.aws2-sqs.redrivePolicy";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_REDRIVE_POLICY_DOC = "Specify the policy that send message to DeadLetter queue. See detail at Amazon docs.";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_REDRIVE_POLICY_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_ACCESS_KEY_CONF = "camel.component.aws2-sqs.accessKey";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_ACCESS_KEY_DOC = "Amazon AWS Access Key";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_ACCESS_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_SECRET_KEY_CONF = "camel.component.aws2-sqs.secretKey";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_SECRET_KEY_DOC = "Amazon AWS Secret Key";
    public static final String CAMEL_SINK_AWS2SQS_COMPONENT_SECRET_KEY_DEFAULT = null;

    public CamelAws2sqsSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelAws2sqsSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_AWS2SQS_PATH_QUEUE_NAME_OR_ARN_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_PATH_QUEUE_NAME_OR_ARN_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AWS2SQS_PATH_QUEUE_NAME_OR_ARN_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_AMAZON_AWSHOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_AMAZON_AWSHOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_AMAZON_AWSHOST_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_AMAZON_SQSCLIENT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_AMAZON_SQSCLIENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_AMAZON_SQSCLIENT_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_AUTO_CREATE_QUEUE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWS2SQS_ENDPOINT_AUTO_CREATE_QUEUE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_AUTO_CREATE_QUEUE_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_HEADER_FILTER_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_HEADER_FILTER_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_HEADER_FILTER_STRATEGY_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_PROTOCOL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_PROTOCOL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_PROTOCOL_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_PROXY_PROTOCOL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_PROXY_PROTOCOL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_PROXY_PROTOCOL_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_QUEUE_OWNER_AWSACCOUNT_ID_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_QUEUE_OWNER_AWSACCOUNT_ID_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_QUEUE_OWNER_AWSACCOUNT_ID_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_REGION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_REGION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_REGION_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_TRUST_ALL_CERTIFICATES_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWS2SQS_ENDPOINT_TRUST_ALL_CERTIFICATES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_TRUST_ALL_CERTIFICATES_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_USE_IAMCREDENTIALS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWS2SQS_ENDPOINT_USE_IAMCREDENTIALS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_USE_IAMCREDENTIALS_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_DELAY_SECONDS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_DELAY_SECONDS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_DELAY_SECONDS_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWS2SQS_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_MESSAGE_DEDUPLICATION_ID_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_MESSAGE_DEDUPLICATION_ID_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_MESSAGE_DEDUPLICATION_ID_STRATEGY_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_MESSAGE_GROUP_ID_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_MESSAGE_GROUP_ID_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_MESSAGE_GROUP_ID_STRATEGY_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_OPERATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_OPERATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_OPERATION_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_DELAY_QUEUE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWS2SQS_ENDPOINT_DELAY_QUEUE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_DELAY_QUEUE_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_QUEUE_URL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_QUEUE_URL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_QUEUE_URL_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWS2SQS_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_PROXY_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_PROXY_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_PROXY_HOST_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_PROXY_PORT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_PROXY_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_PROXY_PORT_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_MAXIMUM_MESSAGE_SIZE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_MAXIMUM_MESSAGE_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_MAXIMUM_MESSAGE_SIZE_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_MESSAGE_RETENTION_PERIOD_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_MESSAGE_RETENTION_PERIOD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_MESSAGE_RETENTION_PERIOD_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_POLICY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_POLICY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_POLICY_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_RECEIVE_MESSAGE_WAIT_TIME_SECONDS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_RECEIVE_MESSAGE_WAIT_TIME_SECONDS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_RECEIVE_MESSAGE_WAIT_TIME_SECONDS_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_REDRIVE_POLICY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_REDRIVE_POLICY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_REDRIVE_POLICY_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_ACCESS_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_ACCESS_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_ACCESS_KEY_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_ENDPOINT_SECRET_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_ENDPOINT_SECRET_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_ENDPOINT_SECRET_KEY_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_AMAZON_AWSHOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_AMAZON_AWSHOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_AMAZON_AWSHOST_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_AMAZON_SQSCLIENT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_AMAZON_SQSCLIENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_AMAZON_SQSCLIENT_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_AUTO_CREATE_QUEUE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWS2SQS_COMPONENT_AUTO_CREATE_QUEUE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_AUTO_CREATE_QUEUE_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_CONFIGURATION_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_PROTOCOL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_PROTOCOL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_PROTOCOL_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_PROXY_PROTOCOL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_PROXY_PROTOCOL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_PROXY_PROTOCOL_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_QUEUE_OWNER_AWSACCOUNT_ID_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_QUEUE_OWNER_AWSACCOUNT_ID_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_QUEUE_OWNER_AWSACCOUNT_ID_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_REGION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_REGION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_REGION_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_TRUST_ALL_CERTIFICATES_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWS2SQS_COMPONENT_TRUST_ALL_CERTIFICATES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_TRUST_ALL_CERTIFICATES_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_USE_IAMCREDENTIALS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWS2SQS_COMPONENT_USE_IAMCREDENTIALS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_USE_IAMCREDENTIALS_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_DELAY_SECONDS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_DELAY_SECONDS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_DELAY_SECONDS_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWS2SQS_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_MESSAGE_DEDUPLICATION_ID_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_MESSAGE_DEDUPLICATION_ID_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_MESSAGE_DEDUPLICATION_ID_STRATEGY_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_MESSAGE_GROUP_ID_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_MESSAGE_GROUP_ID_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_MESSAGE_GROUP_ID_STRATEGY_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_OPERATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_OPERATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_OPERATION_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWS2SQS_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_AUTOWIRED_ENABLED_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_DELAY_QUEUE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWS2SQS_COMPONENT_DELAY_QUEUE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_DELAY_QUEUE_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_QUEUE_URL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_QUEUE_URL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_QUEUE_URL_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_PROXY_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_PROXY_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_PROXY_HOST_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_PROXY_PORT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_PROXY_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_PROXY_PORT_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_MAXIMUM_MESSAGE_SIZE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_MAXIMUM_MESSAGE_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_MAXIMUM_MESSAGE_SIZE_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_MESSAGE_RETENTION_PERIOD_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_MESSAGE_RETENTION_PERIOD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_MESSAGE_RETENTION_PERIOD_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_POLICY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_POLICY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_POLICY_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_RECEIVE_MESSAGE_WAIT_TIME_SECONDS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_RECEIVE_MESSAGE_WAIT_TIME_SECONDS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_RECEIVE_MESSAGE_WAIT_TIME_SECONDS_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_REDRIVE_POLICY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_REDRIVE_POLICY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_REDRIVE_POLICY_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_ACCESS_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_ACCESS_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_ACCESS_KEY_DOC);
        conf.define(CAMEL_SINK_AWS2SQS_COMPONENT_SECRET_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SQS_COMPONENT_SECRET_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SQS_COMPONENT_SECRET_KEY_DOC);
        return conf;
    }
}