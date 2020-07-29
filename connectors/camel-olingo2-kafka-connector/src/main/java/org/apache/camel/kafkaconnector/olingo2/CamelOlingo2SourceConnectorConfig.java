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
package org.apache.camel.kafkaconnector.olingo2;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelOlingo2SourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_OLINGO2_PATH_API_NAME_CONF = "camel.source.path.apiName";
    private static final String CAMEL_SOURCE_OLINGO2_PATH_API_NAME_DOC = "What kind of operation to perform One of: [DEFAULT]";
    private static final String CAMEL_SOURCE_OLINGO2_PATH_API_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_PATH_METHOD_NAME_CONF = "camel.source.path.methodName";
    private static final String CAMEL_SOURCE_OLINGO2_PATH_METHOD_NAME_DOC = "What sub operation to use for the selected operation";
    private static final String CAMEL_SOURCE_OLINGO2_PATH_METHOD_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_CONNECT_TIMEOUT_CONF = "camel.source.endpoint.connectTimeout";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_CONNECT_TIMEOUT_DOC = "HTTP connection creation timeout in milliseconds, defaults to 30,000 (30 seconds)";
    private static final Integer CAMEL_SOURCE_OLINGO2_ENDPOINT_CONNECT_TIMEOUT_DEFAULT = 30000;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_CONTENT_TYPE_CONF = "camel.source.endpoint.contentType";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_CONTENT_TYPE_DOC = "Content-Type header value can be used to specify JSON or XML message format, defaults to application/json;charset=utf-8";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_CONTENT_TYPE_DEFAULT = "application/json;charset=utf-8";
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_FILTER_ALREADY_SEEN_CONF = "camel.source.endpoint.filterAlreadySeen";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_FILTER_ALREADY_SEEN_DOC = "Set this to true to filter out results that have already been communicated by this component.";
    private static final Boolean CAMEL_SOURCE_OLINGO2_ENDPOINT_FILTER_ALREADY_SEEN_DEFAULT = false;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_HTTP_HEADERS_CONF = "camel.source.endpoint.httpHeaders";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_HTTP_HEADERS_DOC = "Custom HTTP headers to inject into every request, this could include OAuth tokens, etc.";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_HTTP_HEADERS_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_IN_BODY_CONF = "camel.source.endpoint.inBody";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_IN_BODY_DOC = "Sets the name of a parameter to be passed in the exchange In Body";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_IN_BODY_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_PROXY_CONF = "camel.source.endpoint.proxy";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_PROXY_DOC = "HTTP proxy server configuration";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_PROXY_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SERVICE_URI_CONF = "camel.source.endpoint.serviceUri";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SERVICE_URI_DOC = "Target OData service base URI, e.g. http://services.odata.org/OData/OData.svc";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SERVICE_URI_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SOCKET_TIMEOUT_CONF = "camel.source.endpoint.socketTimeout";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SOCKET_TIMEOUT_DOC = "HTTP request timeout in milliseconds, defaults to 30,000 (30 seconds)";
    private static final Integer CAMEL_SOURCE_OLINGO2_ENDPOINT_SOCKET_TIMEOUT_DEFAULT = 30000;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF = "camel.source.endpoint.bridgeErrorHandler";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final Boolean CAMEL_SOURCE_OLINGO2_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SEND_EMPTY_MESSAGE_WHEN_IDLE_CONF = "camel.source.endpoint.sendEmptyMessageWhenIdle";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SEND_EMPTY_MESSAGE_WHEN_IDLE_DOC = "If the polling consumer did not poll any files, you can enable this option to send an empty message (no body) instead.";
    private static final Boolean CAMEL_SOURCE_OLINGO2_ENDPOINT_SEND_EMPTY_MESSAGE_WHEN_IDLE_DEFAULT = false;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SPLIT_RESULT_CONF = "camel.source.endpoint.splitResult";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SPLIT_RESULT_DOC = "For endpoints that return an array or collection, a consumer endpoint will map every element to distinct messages, unless splitResult is set to false.";
    private static final Boolean CAMEL_SOURCE_OLINGO2_ENDPOINT_SPLIT_RESULT_DEFAULT = true;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_EXCEPTION_HANDLER_CONF = "camel.source.endpoint.exceptionHandler";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_EXCEPTION_HANDLER_DOC = "To let the consumer use a custom ExceptionHandler. Notice if the option bridgeErrorHandler is enabled then this option is not in use. By default the consumer will deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_EXCEPTION_HANDLER_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_EXCHANGE_PATTERN_CONF = "camel.source.endpoint.exchangePattern";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_EXCHANGE_PATTERN_DOC = "Sets the exchange pattern when the consumer creates an exchange. One of: [InOnly] [InOut] [InOptionalOut]";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_EXCHANGE_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_POLL_STRATEGY_CONF = "camel.source.endpoint.pollStrategy";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_POLL_STRATEGY_DOC = "A pluggable org.apache.camel.PollingConsumerPollingStrategy allowing you to provide your custom implementation to control error handling usually occurred during the poll operation before an Exchange have been created and being routed in Camel.";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_POLL_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.source.endpoint.basicPropertyBinding";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SOURCE_OLINGO2_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_HTTP_ASYNC_CLIENT_BUILDER_CONF = "camel.source.endpoint.httpAsyncClientBuilder";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_HTTP_ASYNC_CLIENT_BUILDER_DOC = "Custom HTTP async client builder for more complex HTTP client configuration, overrides connectionTimeout, socketTimeout, proxy and sslContext. Note that a socketTimeout MUST be specified in the builder, otherwise OData requests could block indefinitely";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_HTTP_ASYNC_CLIENT_BUILDER_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_HTTP_CLIENT_BUILDER_CONF = "camel.source.endpoint.httpClientBuilder";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_HTTP_CLIENT_BUILDER_DOC = "Custom HTTP client builder for more complex HTTP client configuration, overrides connectionTimeout, socketTimeout, proxy and sslContext. Note that a socketTimeout MUST be specified in the builder, otherwise OData requests could block indefinitely";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_HTTP_CLIENT_BUILDER_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SYNCHRONOUS_CONF = "camel.source.endpoint.synchronous";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SOURCE_OLINGO2_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_BACKOFF_ERROR_THRESHOLD_CONF = "camel.source.endpoint.backoffErrorThreshold";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_BACKOFF_ERROR_THRESHOLD_DOC = "The number of subsequent error polls (failed due some error) that should happen before the backoffMultipler should kick-in.";
    private static final Integer CAMEL_SOURCE_OLINGO2_ENDPOINT_BACKOFF_ERROR_THRESHOLD_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_BACKOFF_IDLE_THRESHOLD_CONF = "camel.source.endpoint.backoffIdleThreshold";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_BACKOFF_IDLE_THRESHOLD_DOC = "The number of subsequent idle polls that should happen before the backoffMultipler should kick-in.";
    private static final Integer CAMEL_SOURCE_OLINGO2_ENDPOINT_BACKOFF_IDLE_THRESHOLD_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_BACKOFF_MULTIPLIER_CONF = "camel.source.endpoint.backoffMultiplier";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_BACKOFF_MULTIPLIER_DOC = "To let the scheduled polling consumer backoff if there has been a number of subsequent idles/errors in a row. The multiplier is then the number of polls that will be skipped before the next actual attempt is happening again. When this option is in use then backoffIdleThreshold and/or backoffErrorThreshold must also be configured.";
    private static final Integer CAMEL_SOURCE_OLINGO2_ENDPOINT_BACKOFF_MULTIPLIER_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_DELAY_CONF = "camel.source.endpoint.delay";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_DELAY_DOC = "Milliseconds before the next poll.";
    private static final Long CAMEL_SOURCE_OLINGO2_ENDPOINT_DELAY_DEFAULT = 500L;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_GREEDY_CONF = "camel.source.endpoint.greedy";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_GREEDY_DOC = "If greedy is enabled, then the ScheduledPollConsumer will run immediately again, if the previous run polled 1 or more messages.";
    private static final Boolean CAMEL_SOURCE_OLINGO2_ENDPOINT_GREEDY_DEFAULT = false;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_INITIAL_DELAY_CONF = "camel.source.endpoint.initialDelay";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_INITIAL_DELAY_DOC = "Milliseconds before the first poll starts.";
    private static final Long CAMEL_SOURCE_OLINGO2_ENDPOINT_INITIAL_DELAY_DEFAULT = 1000L;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_REPEAT_COUNT_CONF = "camel.source.endpoint.repeatCount";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_REPEAT_COUNT_DOC = "Specifies a maximum limit of number of fires. So if you set it to 1, the scheduler will only fire once. If you set it to 5, it will only fire five times. A value of zero or negative means fire forever.";
    private static final Long CAMEL_SOURCE_OLINGO2_ENDPOINT_REPEAT_COUNT_DEFAULT = 0L;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_RUN_LOGGING_LEVEL_CONF = "camel.source.endpoint.runLoggingLevel";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_RUN_LOGGING_LEVEL_DOC = "The consumer logs a start/complete log line when it polls. This option allows you to configure the logging level for that. One of: [TRACE] [DEBUG] [INFO] [WARN] [ERROR] [OFF]";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_RUN_LOGGING_LEVEL_DEFAULT = "TRACE";
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SCHEDULED_EXECUTOR_SERVICE_CONF = "camel.source.endpoint.scheduledExecutorService";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SCHEDULED_EXECUTOR_SERVICE_DOC = "Allows for configuring a custom/shared thread pool to use for the consumer. By default each consumer has its own single threaded thread pool.";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SCHEDULED_EXECUTOR_SERVICE_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SCHEDULER_CONF = "camel.source.endpoint.scheduler";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SCHEDULER_DOC = "To use a cron scheduler from either camel-spring or camel-quartz component One of: [none] [spring] [quartz]";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SCHEDULER_DEFAULT = "none";
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SCHEDULER_PROPERTIES_CONF = "camel.source.endpoint.schedulerProperties";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SCHEDULER_PROPERTIES_DOC = "To configure additional properties when using a custom scheduler or any of the Quartz, Spring based scheduler.";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SCHEDULER_PROPERTIES_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_START_SCHEDULER_CONF = "camel.source.endpoint.startScheduler";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_START_SCHEDULER_DOC = "Whether the scheduler should be auto started.";
    private static final Boolean CAMEL_SOURCE_OLINGO2_ENDPOINT_START_SCHEDULER_DEFAULT = true;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_TIME_UNIT_CONF = "camel.source.endpoint.timeUnit";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_TIME_UNIT_DOC = "Time unit for initialDelay and delay options. One of: [NANOSECONDS] [MICROSECONDS] [MILLISECONDS] [SECONDS] [MINUTES] [HOURS] [DAYS]";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_TIME_UNIT_DEFAULT = "MILLISECONDS";
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_USE_FIXED_DELAY_CONF = "camel.source.endpoint.useFixedDelay";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_USE_FIXED_DELAY_DOC = "Controls if fixed delay or fixed rate is used. See ScheduledExecutorService in JDK for details.";
    private static final Boolean CAMEL_SOURCE_OLINGO2_ENDPOINT_USE_FIXED_DELAY_DEFAULT = true;
    public static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF = "camel.source.endpoint.sslContextParameters";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC = "To configure security using SSLContextParameters";
    private static final String CAMEL_SOURCE_OLINGO2_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_COMPONENT_CONFIGURATION_CONF = "camel.component.olingo2.configuration";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_CONFIGURATION_DOC = "To use the shared configuration";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_COMPONENT_CONNECT_TIMEOUT_CONF = "camel.component.olingo2.connectTimeout";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_CONNECT_TIMEOUT_DOC = "HTTP connection creation timeout in milliseconds, defaults to 30,000 (30 seconds)";
    private static final Integer CAMEL_SOURCE_OLINGO2_COMPONENT_CONNECT_TIMEOUT_DEFAULT = 30000;
    public static final String CAMEL_SOURCE_OLINGO2_COMPONENT_CONTENT_TYPE_CONF = "camel.component.olingo2.contentType";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_CONTENT_TYPE_DOC = "Content-Type header value can be used to specify JSON or XML message format, defaults to application/json;charset=utf-8";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_CONTENT_TYPE_DEFAULT = "application/json;charset=utf-8";
    public static final String CAMEL_SOURCE_OLINGO2_COMPONENT_FILTER_ALREADY_SEEN_CONF = "camel.component.olingo2.filterAlreadySeen";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_FILTER_ALREADY_SEEN_DOC = "Set this to true to filter out results that have already been communicated by this component.";
    private static final Boolean CAMEL_SOURCE_OLINGO2_COMPONENT_FILTER_ALREADY_SEEN_DEFAULT = false;
    public static final String CAMEL_SOURCE_OLINGO2_COMPONENT_HTTP_HEADERS_CONF = "camel.component.olingo2.httpHeaders";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_HTTP_HEADERS_DOC = "Custom HTTP headers to inject into every request, this could include OAuth tokens, etc.";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_HTTP_HEADERS_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_COMPONENT_PROXY_CONF = "camel.component.olingo2.proxy";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_PROXY_DOC = "HTTP proxy server configuration";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_PROXY_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_COMPONENT_SERVICE_URI_CONF = "camel.component.olingo2.serviceUri";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_SERVICE_URI_DOC = "Target OData service base URI, e.g. http://services.odata.org/OData/OData.svc";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_SERVICE_URI_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_COMPONENT_SOCKET_TIMEOUT_CONF = "camel.component.olingo2.socketTimeout";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_SOCKET_TIMEOUT_DOC = "HTTP request timeout in milliseconds, defaults to 30,000 (30 seconds)";
    private static final Integer CAMEL_SOURCE_OLINGO2_COMPONENT_SOCKET_TIMEOUT_DEFAULT = 30000;
    public static final String CAMEL_SOURCE_OLINGO2_COMPONENT_BRIDGE_ERROR_HANDLER_CONF = "camel.component.olingo2.bridgeErrorHandler";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final Boolean CAMEL_SOURCE_OLINGO2_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_OLINGO2_COMPONENT_SPLIT_RESULT_CONF = "camel.component.olingo2.splitResult";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_SPLIT_RESULT_DOC = "For endpoints that return an array or collection, a consumer endpoint will map every element to distinct messages, unless splitResult is set to false.";
    private static final Boolean CAMEL_SOURCE_OLINGO2_COMPONENT_SPLIT_RESULT_DEFAULT = true;
    public static final String CAMEL_SOURCE_OLINGO2_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.olingo2.basicPropertyBinding";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SOURCE_OLINGO2_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_OLINGO2_COMPONENT_HTTP_ASYNC_CLIENT_BUILDER_CONF = "camel.component.olingo2.httpAsyncClientBuilder";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_HTTP_ASYNC_CLIENT_BUILDER_DOC = "Custom HTTP async client builder for more complex HTTP client configuration, overrides connectionTimeout, socketTimeout, proxy and sslContext. Note that a socketTimeout MUST be specified in the builder, otherwise OData requests could block indefinitely";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_HTTP_ASYNC_CLIENT_BUILDER_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_COMPONENT_HTTP_CLIENT_BUILDER_CONF = "camel.component.olingo2.httpClientBuilder";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_HTTP_CLIENT_BUILDER_DOC = "Custom HTTP client builder for more complex HTTP client configuration, overrides connectionTimeout, socketTimeout, proxy and sslContext. Note that a socketTimeout MUST be specified in the builder, otherwise OData requests could block indefinitely";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_HTTP_CLIENT_BUILDER_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_COMPONENT_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.olingo2.sslContextParameters";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_SSL_CONTEXT_PARAMETERS_DOC = "To configure security using SSLContextParameters";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_SSL_CONTEXT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SOURCE_OLINGO2_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.olingo2.useGlobalSslContextParameters";
    private static final String CAMEL_SOURCE_OLINGO2_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC = "Enable usage of global SSL context parameters.";
    private static final Boolean CAMEL_SOURCE_OLINGO2_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT = false;

    public CamelOlingo2SourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelOlingo2SourceConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_OLINGO2_PATH_API_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_PATH_API_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_OLINGO2_PATH_API_NAME_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_PATH_METHOD_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_PATH_METHOD_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_OLINGO2_PATH_METHOD_NAME_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_CONNECT_TIMEOUT_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_OLINGO2_ENDPOINT_CONNECT_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_CONNECT_TIMEOUT_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_CONTENT_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_ENDPOINT_CONTENT_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_CONTENT_TYPE_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_FILTER_ALREADY_SEEN_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_OLINGO2_ENDPOINT_FILTER_ALREADY_SEEN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_FILTER_ALREADY_SEEN_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_HTTP_HEADERS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_ENDPOINT_HTTP_HEADERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_HTTP_HEADERS_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_IN_BODY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_ENDPOINT_IN_BODY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_IN_BODY_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_PROXY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_ENDPOINT_PROXY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_PROXY_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_SERVICE_URI_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_ENDPOINT_SERVICE_URI_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_SERVICE_URI_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_SOCKET_TIMEOUT_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_OLINGO2_ENDPOINT_SOCKET_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_SOCKET_TIMEOUT_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_OLINGO2_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_SEND_EMPTY_MESSAGE_WHEN_IDLE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_OLINGO2_ENDPOINT_SEND_EMPTY_MESSAGE_WHEN_IDLE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_SEND_EMPTY_MESSAGE_WHEN_IDLE_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_SPLIT_RESULT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_OLINGO2_ENDPOINT_SPLIT_RESULT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_SPLIT_RESULT_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_EXCEPTION_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_ENDPOINT_EXCEPTION_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_EXCEPTION_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_EXCHANGE_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_ENDPOINT_EXCHANGE_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_EXCHANGE_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_POLL_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_ENDPOINT_POLL_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_POLL_STRATEGY_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_OLINGO2_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_HTTP_ASYNC_CLIENT_BUILDER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_ENDPOINT_HTTP_ASYNC_CLIENT_BUILDER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_HTTP_ASYNC_CLIENT_BUILDER_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_HTTP_CLIENT_BUILDER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_ENDPOINT_HTTP_CLIENT_BUILDER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_HTTP_CLIENT_BUILDER_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_OLINGO2_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_BACKOFF_ERROR_THRESHOLD_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_OLINGO2_ENDPOINT_BACKOFF_ERROR_THRESHOLD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_BACKOFF_ERROR_THRESHOLD_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_BACKOFF_IDLE_THRESHOLD_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_OLINGO2_ENDPOINT_BACKOFF_IDLE_THRESHOLD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_BACKOFF_IDLE_THRESHOLD_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_BACKOFF_MULTIPLIER_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_OLINGO2_ENDPOINT_BACKOFF_MULTIPLIER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_BACKOFF_MULTIPLIER_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_DELAY_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_OLINGO2_ENDPOINT_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_DELAY_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_GREEDY_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_OLINGO2_ENDPOINT_GREEDY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_GREEDY_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_INITIAL_DELAY_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_OLINGO2_ENDPOINT_INITIAL_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_INITIAL_DELAY_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_REPEAT_COUNT_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_OLINGO2_ENDPOINT_REPEAT_COUNT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_REPEAT_COUNT_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_RUN_LOGGING_LEVEL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_ENDPOINT_RUN_LOGGING_LEVEL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_RUN_LOGGING_LEVEL_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_SCHEDULED_EXECUTOR_SERVICE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_ENDPOINT_SCHEDULED_EXECUTOR_SERVICE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_SCHEDULED_EXECUTOR_SERVICE_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_SCHEDULER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_ENDPOINT_SCHEDULER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_SCHEDULER_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_SCHEDULER_PROPERTIES_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_ENDPOINT_SCHEDULER_PROPERTIES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_SCHEDULER_PROPERTIES_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_START_SCHEDULER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_OLINGO2_ENDPOINT_START_SCHEDULER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_START_SCHEDULER_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_TIME_UNIT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_ENDPOINT_TIME_UNIT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_TIME_UNIT_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_USE_FIXED_DELAY_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_OLINGO2_ENDPOINT_USE_FIXED_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_USE_FIXED_DELAY_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_COMPONENT_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_COMPONENT_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_COMPONENT_CONFIGURATION_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_COMPONENT_CONNECT_TIMEOUT_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_OLINGO2_COMPONENT_CONNECT_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_COMPONENT_CONNECT_TIMEOUT_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_COMPONENT_CONTENT_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_COMPONENT_CONTENT_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_COMPONENT_CONTENT_TYPE_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_COMPONENT_FILTER_ALREADY_SEEN_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_OLINGO2_COMPONENT_FILTER_ALREADY_SEEN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_COMPONENT_FILTER_ALREADY_SEEN_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_COMPONENT_HTTP_HEADERS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_COMPONENT_HTTP_HEADERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_COMPONENT_HTTP_HEADERS_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_COMPONENT_PROXY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_COMPONENT_PROXY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_COMPONENT_PROXY_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_COMPONENT_SERVICE_URI_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_COMPONENT_SERVICE_URI_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_COMPONENT_SERVICE_URI_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_COMPONENT_SOCKET_TIMEOUT_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_OLINGO2_COMPONENT_SOCKET_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_COMPONENT_SOCKET_TIMEOUT_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_COMPONENT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_OLINGO2_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_COMPONENT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_COMPONENT_SPLIT_RESULT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_OLINGO2_COMPONENT_SPLIT_RESULT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_COMPONENT_SPLIT_RESULT_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_OLINGO2_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_COMPONENT_HTTP_ASYNC_CLIENT_BUILDER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_COMPONENT_HTTP_ASYNC_CLIENT_BUILDER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_COMPONENT_HTTP_ASYNC_CLIENT_BUILDER_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_COMPONENT_HTTP_CLIENT_BUILDER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_COMPONENT_HTTP_CLIENT_BUILDER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_COMPONENT_HTTP_CLIENT_BUILDER_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_COMPONENT_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_OLINGO2_COMPONENT_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_COMPONENT_SSL_CONTEXT_PARAMETERS_DOC);
        conf.define(CAMEL_SOURCE_OLINGO2_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_OLINGO2_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_OLINGO2_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC);
        return conf;
    }
}