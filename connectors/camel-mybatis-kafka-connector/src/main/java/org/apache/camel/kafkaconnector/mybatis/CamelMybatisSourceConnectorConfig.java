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
package org.apache.camel.kafkaconnector.mybatis;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelMybatisSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_MYBATIS_PATH_STATEMENT_CONF = "camel.source.path.statement";
    private static final String CAMEL_SOURCE_MYBATIS_PATH_STATEMENT_DOC = "The statement name in the MyBatis XML mapping file which maps to the query, insert, update or delete operation you wish to evaluate.";
    private static final String CAMEL_SOURCE_MYBATIS_PATH_STATEMENT_DEFAULT = null;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF = "camel.source.endpoint.bridgeErrorHandler";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final Boolean CAMEL_SOURCE_MYBATIS_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_MAX_MESSAGES_PER_POLL_CONF = "camel.source.endpoint.maxMessagesPerPoll";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_MAX_MESSAGES_PER_POLL_DOC = "This option is intended to split results returned by the database pool into the batches and deliver them in multiple exchanges. This integer defines the maximum messages to deliver in single exchange. By default, no maximum is set. Can be used to set a limit of e.g. 1000 to avoid when starting up the server that there are thousands of files. Set a value of 0 or negative to disable it.";
    private static final Integer CAMEL_SOURCE_MYBATIS_ENDPOINT_MAX_MESSAGES_PER_POLL_DEFAULT = 0;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_ON_CONSUME_CONF = "camel.source.endpoint.onConsume";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_ON_CONSUME_DOC = "Statement to run after data has been processed in the route";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_ON_CONSUME_DEFAULT = null;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_ROUTE_EMPTY_RESULT_SET_CONF = "camel.source.endpoint.routeEmptyResultSet";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_ROUTE_EMPTY_RESULT_SET_DOC = "Whether allow empty resultset to be routed to the next hop";
    private static final Boolean CAMEL_SOURCE_MYBATIS_ENDPOINT_ROUTE_EMPTY_RESULT_SET_DEFAULT = false;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_SEND_EMPTY_MESSAGE_WHEN_IDLE_CONF = "camel.source.endpoint.sendEmptyMessageWhenIdle";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_SEND_EMPTY_MESSAGE_WHEN_IDLE_DOC = "If the polling consumer did not poll any files, you can enable this option to send an empty message (no body) instead.";
    private static final Boolean CAMEL_SOURCE_MYBATIS_ENDPOINT_SEND_EMPTY_MESSAGE_WHEN_IDLE_DEFAULT = false;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_TRANSACTED_CONF = "camel.source.endpoint.transacted";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_TRANSACTED_DOC = "Enables or disables transaction. If enabled then if processing an exchange failed then the consumer breaks out processing any further exchanges to cause a rollback eager.";
    private static final Boolean CAMEL_SOURCE_MYBATIS_ENDPOINT_TRANSACTED_DEFAULT = false;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_USE_ITERATOR_CONF = "camel.source.endpoint.useIterator";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_USE_ITERATOR_DOC = "Process resultset individually or as a list";
    private static final Boolean CAMEL_SOURCE_MYBATIS_ENDPOINT_USE_ITERATOR_DEFAULT = true;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_EXCEPTION_HANDLER_CONF = "camel.source.endpoint.exceptionHandler";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_EXCEPTION_HANDLER_DOC = "To let the consumer use a custom ExceptionHandler. Notice if the option bridgeErrorHandler is enabled then this option is not in use. By default the consumer will deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_EXCEPTION_HANDLER_DEFAULT = null;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_EXCHANGE_PATTERN_CONF = "camel.source.endpoint.exchangePattern";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_EXCHANGE_PATTERN_DOC = "Sets the exchange pattern when the consumer creates an exchange. One of: [InOnly] [InOut] [InOptionalOut]";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_EXCHANGE_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_POLL_STRATEGY_CONF = "camel.source.endpoint.pollStrategy";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_POLL_STRATEGY_DOC = "A pluggable org.apache.camel.PollingConsumerPollingStrategy allowing you to provide your custom implementation to control error handling usually occurred during the poll operation before an Exchange have been created and being routed in Camel.";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_POLL_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_PROCESSING_STRATEGY_CONF = "camel.source.endpoint.processingStrategy";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_PROCESSING_STRATEGY_DOC = "To use a custom MyBatisProcessingStrategy";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_PROCESSING_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.source.endpoint.basicPropertyBinding";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SOURCE_MYBATIS_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_SYNCHRONOUS_CONF = "camel.source.endpoint.synchronous";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SOURCE_MYBATIS_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_BACKOFF_ERROR_THRESHOLD_CONF = "camel.source.endpoint.backoffErrorThreshold";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_BACKOFF_ERROR_THRESHOLD_DOC = "The number of subsequent error polls (failed due some error) that should happen before the backoffMultipler should kick-in.";
    private static final Integer CAMEL_SOURCE_MYBATIS_ENDPOINT_BACKOFF_ERROR_THRESHOLD_DEFAULT = null;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_BACKOFF_IDLE_THRESHOLD_CONF = "camel.source.endpoint.backoffIdleThreshold";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_BACKOFF_IDLE_THRESHOLD_DOC = "The number of subsequent idle polls that should happen before the backoffMultipler should kick-in.";
    private static final Integer CAMEL_SOURCE_MYBATIS_ENDPOINT_BACKOFF_IDLE_THRESHOLD_DEFAULT = null;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_BACKOFF_MULTIPLIER_CONF = "camel.source.endpoint.backoffMultiplier";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_BACKOFF_MULTIPLIER_DOC = "To let the scheduled polling consumer backoff if there has been a number of subsequent idles/errors in a row. The multiplier is then the number of polls that will be skipped before the next actual attempt is happening again. When this option is in use then backoffIdleThreshold and/or backoffErrorThreshold must also be configured.";
    private static final Integer CAMEL_SOURCE_MYBATIS_ENDPOINT_BACKOFF_MULTIPLIER_DEFAULT = null;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_DELAY_CONF = "camel.source.endpoint.delay";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_DELAY_DOC = "Milliseconds before the next poll.";
    private static final Long CAMEL_SOURCE_MYBATIS_ENDPOINT_DELAY_DEFAULT = 500L;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_GREEDY_CONF = "camel.source.endpoint.greedy";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_GREEDY_DOC = "If greedy is enabled, then the ScheduledPollConsumer will run immediately again, if the previous run polled 1 or more messages.";
    private static final Boolean CAMEL_SOURCE_MYBATIS_ENDPOINT_GREEDY_DEFAULT = false;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_INITIAL_DELAY_CONF = "camel.source.endpoint.initialDelay";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_INITIAL_DELAY_DOC = "Milliseconds before the first poll starts.";
    private static final Long CAMEL_SOURCE_MYBATIS_ENDPOINT_INITIAL_DELAY_DEFAULT = 1000L;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_REPEAT_COUNT_CONF = "camel.source.endpoint.repeatCount";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_REPEAT_COUNT_DOC = "Specifies a maximum limit of number of fires. So if you set it to 1, the scheduler will only fire once. If you set it to 5, it will only fire five times. A value of zero or negative means fire forever.";
    private static final Long CAMEL_SOURCE_MYBATIS_ENDPOINT_REPEAT_COUNT_DEFAULT = 0L;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_RUN_LOGGING_LEVEL_CONF = "camel.source.endpoint.runLoggingLevel";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_RUN_LOGGING_LEVEL_DOC = "The consumer logs a start/complete log line when it polls. This option allows you to configure the logging level for that. One of: [TRACE] [DEBUG] [INFO] [WARN] [ERROR] [OFF]";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_RUN_LOGGING_LEVEL_DEFAULT = "TRACE";
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_SCHEDULED_EXECUTOR_SERVICE_CONF = "camel.source.endpoint.scheduledExecutorService";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_SCHEDULED_EXECUTOR_SERVICE_DOC = "Allows for configuring a custom/shared thread pool to use for the consumer. By default each consumer has its own single threaded thread pool.";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_SCHEDULED_EXECUTOR_SERVICE_DEFAULT = null;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_SCHEDULER_CONF = "camel.source.endpoint.scheduler";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_SCHEDULER_DOC = "To use a cron scheduler from either camel-spring or camel-quartz component One of: [none] [spring] [quartz]";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_SCHEDULER_DEFAULT = "none";
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_SCHEDULER_PROPERTIES_CONF = "camel.source.endpoint.schedulerProperties";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_SCHEDULER_PROPERTIES_DOC = "To configure additional properties when using a custom scheduler or any of the Quartz, Spring based scheduler.";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_SCHEDULER_PROPERTIES_DEFAULT = null;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_START_SCHEDULER_CONF = "camel.source.endpoint.startScheduler";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_START_SCHEDULER_DOC = "Whether the scheduler should be auto started.";
    private static final Boolean CAMEL_SOURCE_MYBATIS_ENDPOINT_START_SCHEDULER_DEFAULT = true;
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_TIME_UNIT_CONF = "camel.source.endpoint.timeUnit";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_TIME_UNIT_DOC = "Time unit for initialDelay and delay options. One of: [NANOSECONDS] [MICROSECONDS] [MILLISECONDS] [SECONDS] [MINUTES] [HOURS] [DAYS]";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_TIME_UNIT_DEFAULT = "MILLISECONDS";
    public static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_USE_FIXED_DELAY_CONF = "camel.source.endpoint.useFixedDelay";
    private static final String CAMEL_SOURCE_MYBATIS_ENDPOINT_USE_FIXED_DELAY_DOC = "Controls if fixed delay or fixed rate is used. See ScheduledExecutorService in JDK for details.";
    private static final Boolean CAMEL_SOURCE_MYBATIS_ENDPOINT_USE_FIXED_DELAY_DEFAULT = true;
    public static final String CAMEL_SOURCE_MYBATIS_COMPONENT_CONFIGURATION_URI_CONF = "camel.component.mybatis.configurationUri";
    private static final String CAMEL_SOURCE_MYBATIS_COMPONENT_CONFIGURATION_URI_DOC = "Location of MyBatis xml configuration file. The default value is: SqlMapConfig.xml loaded from the classpath";
    private static final String CAMEL_SOURCE_MYBATIS_COMPONENT_CONFIGURATION_URI_DEFAULT = "SqlMapConfig.xml";
    public static final String CAMEL_SOURCE_MYBATIS_COMPONENT_BRIDGE_ERROR_HANDLER_CONF = "camel.component.mybatis.bridgeErrorHandler";
    private static final String CAMEL_SOURCE_MYBATIS_COMPONENT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final Boolean CAMEL_SOURCE_MYBATIS_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_MYBATIS_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.mybatis.basicPropertyBinding";
    private static final String CAMEL_SOURCE_MYBATIS_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SOURCE_MYBATIS_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_MYBATIS_COMPONENT_SQL_SESSION_FACTORY_CONF = "camel.component.mybatis.sqlSessionFactory";
    private static final String CAMEL_SOURCE_MYBATIS_COMPONENT_SQL_SESSION_FACTORY_DOC = "To use the SqlSessionFactory";
    private static final String CAMEL_SOURCE_MYBATIS_COMPONENT_SQL_SESSION_FACTORY_DEFAULT = null;

    public CamelMybatisSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelMybatisSourceConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_MYBATIS_PATH_STATEMENT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MYBATIS_PATH_STATEMENT_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_MYBATIS_PATH_STATEMENT_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MYBATIS_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_MAX_MESSAGES_PER_POLL_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_MYBATIS_ENDPOINT_MAX_MESSAGES_PER_POLL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_MAX_MESSAGES_PER_POLL_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_ON_CONSUME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MYBATIS_ENDPOINT_ON_CONSUME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_ON_CONSUME_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_ROUTE_EMPTY_RESULT_SET_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MYBATIS_ENDPOINT_ROUTE_EMPTY_RESULT_SET_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_ROUTE_EMPTY_RESULT_SET_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_SEND_EMPTY_MESSAGE_WHEN_IDLE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MYBATIS_ENDPOINT_SEND_EMPTY_MESSAGE_WHEN_IDLE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_SEND_EMPTY_MESSAGE_WHEN_IDLE_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_TRANSACTED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MYBATIS_ENDPOINT_TRANSACTED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_TRANSACTED_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_USE_ITERATOR_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MYBATIS_ENDPOINT_USE_ITERATOR_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_USE_ITERATOR_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_EXCEPTION_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MYBATIS_ENDPOINT_EXCEPTION_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_EXCEPTION_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_EXCHANGE_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MYBATIS_ENDPOINT_EXCHANGE_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_EXCHANGE_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_POLL_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MYBATIS_ENDPOINT_POLL_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_POLL_STRATEGY_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_PROCESSING_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MYBATIS_ENDPOINT_PROCESSING_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_PROCESSING_STRATEGY_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MYBATIS_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MYBATIS_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_BACKOFF_ERROR_THRESHOLD_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_MYBATIS_ENDPOINT_BACKOFF_ERROR_THRESHOLD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_BACKOFF_ERROR_THRESHOLD_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_BACKOFF_IDLE_THRESHOLD_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_MYBATIS_ENDPOINT_BACKOFF_IDLE_THRESHOLD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_BACKOFF_IDLE_THRESHOLD_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_BACKOFF_MULTIPLIER_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_MYBATIS_ENDPOINT_BACKOFF_MULTIPLIER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_BACKOFF_MULTIPLIER_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_DELAY_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_MYBATIS_ENDPOINT_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_DELAY_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_GREEDY_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MYBATIS_ENDPOINT_GREEDY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_GREEDY_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_INITIAL_DELAY_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_MYBATIS_ENDPOINT_INITIAL_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_INITIAL_DELAY_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_REPEAT_COUNT_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_MYBATIS_ENDPOINT_REPEAT_COUNT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_REPEAT_COUNT_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_RUN_LOGGING_LEVEL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MYBATIS_ENDPOINT_RUN_LOGGING_LEVEL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_RUN_LOGGING_LEVEL_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_SCHEDULED_EXECUTOR_SERVICE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MYBATIS_ENDPOINT_SCHEDULED_EXECUTOR_SERVICE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_SCHEDULED_EXECUTOR_SERVICE_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_SCHEDULER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MYBATIS_ENDPOINT_SCHEDULER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_SCHEDULER_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_SCHEDULER_PROPERTIES_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MYBATIS_ENDPOINT_SCHEDULER_PROPERTIES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_SCHEDULER_PROPERTIES_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_START_SCHEDULER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MYBATIS_ENDPOINT_START_SCHEDULER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_START_SCHEDULER_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_TIME_UNIT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MYBATIS_ENDPOINT_TIME_UNIT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_TIME_UNIT_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_ENDPOINT_USE_FIXED_DELAY_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MYBATIS_ENDPOINT_USE_FIXED_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_ENDPOINT_USE_FIXED_DELAY_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_COMPONENT_CONFIGURATION_URI_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MYBATIS_COMPONENT_CONFIGURATION_URI_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_COMPONENT_CONFIGURATION_URI_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_COMPONENT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MYBATIS_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_COMPONENT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MYBATIS_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_MYBATIS_COMPONENT_SQL_SESSION_FACTORY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MYBATIS_COMPONENT_SQL_SESSION_FACTORY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MYBATIS_COMPONENT_SQL_SESSION_FACTORY_DOC);
        return conf;
    }
}