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
package org.apache.camel.kafkaconnector.aws2kinesis;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelAws2kinesisSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_AWS2KINESIS_PATH_STREAM_NAME_CONF = "camel.source.path.streamName";
    private static final String CAMEL_SOURCE_AWS2KINESIS_PATH_STREAM_NAME_DOC = "Name of the stream";
    private static final String CAMEL_SOURCE_AWS2KINESIS_PATH_STREAM_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_AMAZON_KINESIS_CLIENT_CONF = "camel.source.endpoint.amazonKinesisClient";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_AMAZON_KINESIS_CLIENT_DOC = "Amazon Kinesis client to use for all requests for this endpoint";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_AMAZON_KINESIS_CLIENT_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_PROXY_HOST_CONF = "camel.source.endpoint.proxyHost";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_PROXY_HOST_DOC = "To define a proxy host when instantiating the Kinesis client";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_PROXY_HOST_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_PROXY_PORT_CONF = "camel.source.endpoint.proxyPort";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_PROXY_PORT_DOC = "To define a proxy port when instantiating the Kinesis client";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_PROXY_PORT_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_PROXY_PROTOCOL_CONF = "camel.source.endpoint.proxyProtocol";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_PROXY_PROTOCOL_DOC = "To define a proxy protocol when instantiating the Kinesis client One of: [HTTP] [HTTPS]";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_PROXY_PROTOCOL_DEFAULT = "HTTPS";
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_REGION_CONF = "camel.source.endpoint.region";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_REGION_DOC = "The region in which Kinesis Firehose client needs to work. When using this parameter, the configuration will expect the lowercase name of the region (for example ap-east-1) You'll need to use the name Region.EU_WEST_1.id()";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_REGION_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF = "camel.source.endpoint.bridgeErrorHandler";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final Boolean CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_ITERATOR_TYPE_CONF = "camel.source.endpoint.iteratorType";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_ITERATOR_TYPE_DOC = "Defines where in the Kinesis stream to start getting records One of: [AT_SEQUENCE_NUMBER] [AFTER_SEQUENCE_NUMBER] [TRIM_HORIZON] [LATEST] [AT_TIMESTAMP] [null]";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_ITERATOR_TYPE_DEFAULT = "TRIM_HORIZON";
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_MAX_RESULTS_PER_REQUEST_CONF = "camel.source.endpoint.maxResultsPerRequest";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_MAX_RESULTS_PER_REQUEST_DOC = "Maximum number of records that will be fetched in each poll";
    private static final Integer CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_MAX_RESULTS_PER_REQUEST_DEFAULT = 1;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SEND_EMPTY_MESSAGE_WHEN_IDLE_CONF = "camel.source.endpoint.sendEmptyMessageWhenIdle";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SEND_EMPTY_MESSAGE_WHEN_IDLE_DOC = "If the polling consumer did not poll any files, you can enable this option to send an empty message (no body) instead.";
    private static final Boolean CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SEND_EMPTY_MESSAGE_WHEN_IDLE_DEFAULT = false;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SEQUENCE_NUMBER_CONF = "camel.source.endpoint.sequenceNumber";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SEQUENCE_NUMBER_DOC = "The sequence number to start polling from. Required if iteratorType is set to AFTER_SEQUENCE_NUMBER or AT_SEQUENCE_NUMBER";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SEQUENCE_NUMBER_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SHARD_CLOSED_CONF = "camel.source.endpoint.shardClosed";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SHARD_CLOSED_DOC = "Define what will be the behavior in case of shard closed. Possible value are ignore, silent and fail. In case of ignore a message will be logged and the consumer will restart from the beginning,in case of silent there will be no logging and the consumer will start from the beginning,in case of fail a ReachedClosedStateException will be raised One of: [ignore] [fail] [silent]";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SHARD_CLOSED_DEFAULT = "ignore";
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SHARD_ID_CONF = "camel.source.endpoint.shardId";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SHARD_ID_DOC = "Defines which shardId in the Kinesis stream to get records from";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SHARD_ID_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_EXCEPTION_HANDLER_CONF = "camel.source.endpoint.exceptionHandler";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_EXCEPTION_HANDLER_DOC = "To let the consumer use a custom ExceptionHandler. Notice if the option bridgeErrorHandler is enabled then this option is not in use. By default the consumer will deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_EXCEPTION_HANDLER_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_EXCHANGE_PATTERN_CONF = "camel.source.endpoint.exchangePattern";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_EXCHANGE_PATTERN_DOC = "Sets the exchange pattern when the consumer creates an exchange. One of: [InOnly] [InOut] [InOptionalOut]";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_EXCHANGE_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_POLL_STRATEGY_CONF = "camel.source.endpoint.pollStrategy";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_POLL_STRATEGY_DOC = "A pluggable org.apache.camel.PollingConsumerPollingStrategy allowing you to provide your custom implementation to control error handling usually occurred during the poll operation before an Exchange have been created and being routed in Camel.";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_POLL_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.source.endpoint.basicPropertyBinding";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SYNCHRONOUS_CONF = "camel.source.endpoint.synchronous";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BACKOFF_ERROR_THRESHOLD_CONF = "camel.source.endpoint.backoffErrorThreshold";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BACKOFF_ERROR_THRESHOLD_DOC = "The number of subsequent error polls (failed due some error) that should happen before the backoffMultipler should kick-in.";
    private static final Integer CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BACKOFF_ERROR_THRESHOLD_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BACKOFF_IDLE_THRESHOLD_CONF = "camel.source.endpoint.backoffIdleThreshold";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BACKOFF_IDLE_THRESHOLD_DOC = "The number of subsequent idle polls that should happen before the backoffMultipler should kick-in.";
    private static final Integer CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BACKOFF_IDLE_THRESHOLD_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BACKOFF_MULTIPLIER_CONF = "camel.source.endpoint.backoffMultiplier";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BACKOFF_MULTIPLIER_DOC = "To let the scheduled polling consumer backoff if there has been a number of subsequent idles/errors in a row. The multiplier is then the number of polls that will be skipped before the next actual attempt is happening again. When this option is in use then backoffIdleThreshold and/or backoffErrorThreshold must also be configured.";
    private static final Integer CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BACKOFF_MULTIPLIER_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_DELAY_CONF = "camel.source.endpoint.delay";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_DELAY_DOC = "Milliseconds before the next poll.";
    private static final Long CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_DELAY_DEFAULT = 500L;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_GREEDY_CONF = "camel.source.endpoint.greedy";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_GREEDY_DOC = "If greedy is enabled, then the ScheduledPollConsumer will run immediately again, if the previous run polled 1 or more messages.";
    private static final Boolean CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_GREEDY_DEFAULT = false;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_INITIAL_DELAY_CONF = "camel.source.endpoint.initialDelay";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_INITIAL_DELAY_DOC = "Milliseconds before the first poll starts.";
    private static final Long CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_INITIAL_DELAY_DEFAULT = 1000L;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_REPEAT_COUNT_CONF = "camel.source.endpoint.repeatCount";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_REPEAT_COUNT_DOC = "Specifies a maximum limit of number of fires. So if you set it to 1, the scheduler will only fire once. If you set it to 5, it will only fire five times. A value of zero or negative means fire forever.";
    private static final Long CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_REPEAT_COUNT_DEFAULT = 0L;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_RUN_LOGGING_LEVEL_CONF = "camel.source.endpoint.runLoggingLevel";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_RUN_LOGGING_LEVEL_DOC = "The consumer logs a start/complete log line when it polls. This option allows you to configure the logging level for that. One of: [TRACE] [DEBUG] [INFO] [WARN] [ERROR] [OFF]";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_RUN_LOGGING_LEVEL_DEFAULT = "TRACE";
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SCHEDULED_EXECUTOR_SERVICE_CONF = "camel.source.endpoint.scheduledExecutorService";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SCHEDULED_EXECUTOR_SERVICE_DOC = "Allows for configuring a custom/shared thread pool to use for the consumer. By default each consumer has its own single threaded thread pool.";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SCHEDULED_EXECUTOR_SERVICE_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SCHEDULER_CONF = "camel.source.endpoint.scheduler";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SCHEDULER_DOC = "To use a cron scheduler from either camel-spring or camel-quartz component One of: [none] [spring] [quartz]";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SCHEDULER_DEFAULT = "none";
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SCHEDULER_PROPERTIES_CONF = "camel.source.endpoint.schedulerProperties";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SCHEDULER_PROPERTIES_DOC = "To configure additional properties when using a custom scheduler or any of the Quartz, Spring based scheduler.";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SCHEDULER_PROPERTIES_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_START_SCHEDULER_CONF = "camel.source.endpoint.startScheduler";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_START_SCHEDULER_DOC = "Whether the scheduler should be auto started.";
    private static final Boolean CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_START_SCHEDULER_DEFAULT = true;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_TIME_UNIT_CONF = "camel.source.endpoint.timeUnit";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_TIME_UNIT_DOC = "Time unit for initialDelay and delay options. One of: [NANOSECONDS] [MICROSECONDS] [MILLISECONDS] [SECONDS] [MINUTES] [HOURS] [DAYS]";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_TIME_UNIT_DEFAULT = "MILLISECONDS";
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_USE_FIXED_DELAY_CONF = "camel.source.endpoint.useFixedDelay";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_USE_FIXED_DELAY_DOC = "Controls if fixed delay or fixed rate is used. See ScheduledExecutorService in JDK for details.";
    private static final Boolean CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_USE_FIXED_DELAY_DEFAULT = true;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_ACCESS_KEY_CONF = "camel.source.endpoint.accessKey";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_ACCESS_KEY_DOC = "Amazon AWS Access Key";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_ACCESS_KEY_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SECRET_KEY_CONF = "camel.source.endpoint.secretKey";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SECRET_KEY_DOC = "Amazon AWS Secret Key";
    private static final String CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SECRET_KEY_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_AMAZON_KINESIS_CLIENT_CONF = "camel.component.aws2-kinesis.amazonKinesisClient";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_AMAZON_KINESIS_CLIENT_DOC = "Amazon Kinesis client to use for all requests for this endpoint";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_AMAZON_KINESIS_CLIENT_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_CONFIGURATION_CONF = "camel.component.aws2-kinesis.configuration";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_CONFIGURATION_DOC = "Component configuration";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_PROXY_HOST_CONF = "camel.component.aws2-kinesis.proxyHost";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_PROXY_HOST_DOC = "To define a proxy host when instantiating the Kinesis client";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_PROXY_HOST_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_PROXY_PORT_CONF = "camel.component.aws2-kinesis.proxyPort";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_PROXY_PORT_DOC = "To define a proxy port when instantiating the Kinesis client";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_PROXY_PORT_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_PROXY_PROTOCOL_CONF = "camel.component.aws2-kinesis.proxyProtocol";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_PROXY_PROTOCOL_DOC = "To define a proxy protocol when instantiating the Kinesis client One of: [HTTP] [HTTPS]";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_PROXY_PROTOCOL_DEFAULT = "HTTPS";
    public static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_REGION_CONF = "camel.component.aws2-kinesis.region";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_REGION_DOC = "The region in which Kinesis Firehose client needs to work. When using this parameter, the configuration will expect the lowercase name of the region (for example ap-east-1) You'll need to use the name Region.EU_WEST_1.id()";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_REGION_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_BRIDGE_ERROR_HANDLER_CONF = "camel.component.aws2-kinesis.bridgeErrorHandler";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final Boolean CAMEL_SOURCE_AWS2KINESIS_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_ITERATOR_TYPE_CONF = "camel.component.aws2-kinesis.iteratorType";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_ITERATOR_TYPE_DOC = "Defines where in the Kinesis stream to start getting records One of: [AT_SEQUENCE_NUMBER] [AFTER_SEQUENCE_NUMBER] [TRIM_HORIZON] [LATEST] [AT_TIMESTAMP] [null]";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_ITERATOR_TYPE_DEFAULT = "TRIM_HORIZON";
    public static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_MAX_RESULTS_PER_REQUEST_CONF = "camel.component.aws2-kinesis.maxResultsPerRequest";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_MAX_RESULTS_PER_REQUEST_DOC = "Maximum number of records that will be fetched in each poll";
    private static final Integer CAMEL_SOURCE_AWS2KINESIS_COMPONENT_MAX_RESULTS_PER_REQUEST_DEFAULT = 1;
    public static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SEQUENCE_NUMBER_CONF = "camel.component.aws2-kinesis.sequenceNumber";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SEQUENCE_NUMBER_DOC = "The sequence number to start polling from. Required if iteratorType is set to AFTER_SEQUENCE_NUMBER or AT_SEQUENCE_NUMBER";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SEQUENCE_NUMBER_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SHARD_CLOSED_CONF = "camel.component.aws2-kinesis.shardClosed";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SHARD_CLOSED_DOC = "Define what will be the behavior in case of shard closed. Possible value are ignore, silent and fail. In case of ignore a message will be logged and the consumer will restart from the beginning,in case of silent there will be no logging and the consumer will start from the beginning,in case of fail a ReachedClosedStateException will be raised One of: [ignore] [fail] [silent]";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SHARD_CLOSED_DEFAULT = "ignore";
    public static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SHARD_ID_CONF = "camel.component.aws2-kinesis.shardId";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SHARD_ID_DOC = "Defines which shardId in the Kinesis stream to get records from";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SHARD_ID_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.aws2-kinesis.basicPropertyBinding";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SOURCE_AWS2KINESIS_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_ACCESS_KEY_CONF = "camel.component.aws2-kinesis.accessKey";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_ACCESS_KEY_DOC = "Amazon AWS Access Key";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_ACCESS_KEY_DEFAULT = null;
    public static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SECRET_KEY_CONF = "camel.component.aws2-kinesis.secretKey";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SECRET_KEY_DOC = "Amazon AWS Secret Key";
    private static final String CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SECRET_KEY_DEFAULT = null;

    public CamelAws2kinesisSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelAws2kinesisSourceConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_AWS2KINESIS_PATH_STREAM_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_PATH_STREAM_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_AWS2KINESIS_PATH_STREAM_NAME_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_AMAZON_KINESIS_CLIENT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_AMAZON_KINESIS_CLIENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_AMAZON_KINESIS_CLIENT_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_PROXY_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_PROXY_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_PROXY_HOST_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_PROXY_PORT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_PROXY_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_PROXY_PORT_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_PROXY_PROTOCOL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_PROXY_PROTOCOL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_PROXY_PROTOCOL_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_REGION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_REGION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_REGION_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_ITERATOR_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_ITERATOR_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_ITERATOR_TYPE_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_MAX_RESULTS_PER_REQUEST_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_MAX_RESULTS_PER_REQUEST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_MAX_RESULTS_PER_REQUEST_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SEND_EMPTY_MESSAGE_WHEN_IDLE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SEND_EMPTY_MESSAGE_WHEN_IDLE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SEND_EMPTY_MESSAGE_WHEN_IDLE_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SEQUENCE_NUMBER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SEQUENCE_NUMBER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SEQUENCE_NUMBER_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SHARD_CLOSED_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SHARD_CLOSED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SHARD_CLOSED_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SHARD_ID_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SHARD_ID_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SHARD_ID_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_EXCEPTION_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_EXCEPTION_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_EXCEPTION_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_EXCHANGE_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_EXCHANGE_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_EXCHANGE_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_POLL_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_POLL_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_POLL_STRATEGY_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BACKOFF_ERROR_THRESHOLD_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BACKOFF_ERROR_THRESHOLD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BACKOFF_ERROR_THRESHOLD_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BACKOFF_IDLE_THRESHOLD_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BACKOFF_IDLE_THRESHOLD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BACKOFF_IDLE_THRESHOLD_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BACKOFF_MULTIPLIER_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BACKOFF_MULTIPLIER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_BACKOFF_MULTIPLIER_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_DELAY_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_DELAY_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_GREEDY_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_GREEDY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_GREEDY_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_INITIAL_DELAY_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_INITIAL_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_INITIAL_DELAY_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_REPEAT_COUNT_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_REPEAT_COUNT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_REPEAT_COUNT_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_RUN_LOGGING_LEVEL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_RUN_LOGGING_LEVEL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_RUN_LOGGING_LEVEL_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SCHEDULED_EXECUTOR_SERVICE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SCHEDULED_EXECUTOR_SERVICE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SCHEDULED_EXECUTOR_SERVICE_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SCHEDULER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SCHEDULER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SCHEDULER_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SCHEDULER_PROPERTIES_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SCHEDULER_PROPERTIES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SCHEDULER_PROPERTIES_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_START_SCHEDULER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_START_SCHEDULER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_START_SCHEDULER_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_TIME_UNIT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_TIME_UNIT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_TIME_UNIT_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_USE_FIXED_DELAY_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_USE_FIXED_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_USE_FIXED_DELAY_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_ACCESS_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_ACCESS_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_ACCESS_KEY_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SECRET_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SECRET_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_ENDPOINT_SECRET_KEY_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_COMPONENT_AMAZON_KINESIS_CLIENT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_AMAZON_KINESIS_CLIENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_AMAZON_KINESIS_CLIENT_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_COMPONENT_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_CONFIGURATION_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_COMPONENT_PROXY_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_PROXY_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_PROXY_HOST_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_COMPONENT_PROXY_PORT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_PROXY_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_PROXY_PORT_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_COMPONENT_PROXY_PROTOCOL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_PROXY_PROTOCOL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_PROXY_PROTOCOL_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_COMPONENT_REGION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_REGION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_REGION_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_COMPONENT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_COMPONENT_ITERATOR_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_ITERATOR_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_ITERATOR_TYPE_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_COMPONENT_MAX_RESULTS_PER_REQUEST_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_MAX_RESULTS_PER_REQUEST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_MAX_RESULTS_PER_REQUEST_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SEQUENCE_NUMBER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SEQUENCE_NUMBER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SEQUENCE_NUMBER_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SHARD_CLOSED_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SHARD_CLOSED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SHARD_CLOSED_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SHARD_ID_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SHARD_ID_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SHARD_ID_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_COMPONENT_ACCESS_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_ACCESS_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_ACCESS_KEY_DOC);
        conf.define(CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SECRET_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SECRET_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AWS2KINESIS_COMPONENT_SECRET_KEY_DOC);
        return conf;
    }
}