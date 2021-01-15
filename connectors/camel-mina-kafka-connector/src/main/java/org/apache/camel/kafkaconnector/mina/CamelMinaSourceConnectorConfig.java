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
package org.apache.camel.kafkaconnector.mina;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelMinaSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_MINA_PATH_PROTOCOL_CONF = "camel.source.path.protocol";
    public static final String CAMEL_SOURCE_MINA_PATH_PROTOCOL_DOC = "Protocol to use";
    public static final String CAMEL_SOURCE_MINA_PATH_PROTOCOL_DEFAULT = null;
    public static final String CAMEL_SOURCE_MINA_PATH_HOST_CONF = "camel.source.path.host";
    public static final String CAMEL_SOURCE_MINA_PATH_HOST_DOC = "Hostname to use. Use localhost or 0.0.0.0 for local server as consumer. For producer use the hostname or ip address of the remote server.";
    public static final String CAMEL_SOURCE_MINA_PATH_HOST_DEFAULT = null;
    public static final String CAMEL_SOURCE_MINA_PATH_PORT_CONF = "camel.source.path.port";
    public static final String CAMEL_SOURCE_MINA_PATH_PORT_DOC = "Port number";
    public static final Integer CAMEL_SOURCE_MINA_PATH_PORT_DEFAULT = null;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_DISCONNECT_CONF = "camel.source.endpoint.disconnect";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_DISCONNECT_DOC = "Whether or not to disconnect(close) from Mina session right after use. Can be used for both consumer and producer.";
    public static final Boolean CAMEL_SOURCE_MINA_ENDPOINT_DISCONNECT_DEFAULT = false;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_MINA_LOGGER_CONF = "camel.source.endpoint.minaLogger";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_MINA_LOGGER_DOC = "You can enable the Apache MINA logging filter. Apache MINA uses slf4j logging at INFO level to log all input and output.";
    public static final Boolean CAMEL_SOURCE_MINA_ENDPOINT_MINA_LOGGER_DEFAULT = false;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_SYNC_CONF = "camel.source.endpoint.sync";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_SYNC_DOC = "Setting to set endpoint as one-way or request-response.";
    public static final Boolean CAMEL_SOURCE_MINA_ENDPOINT_SYNC_DEFAULT = true;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_TIMEOUT_CONF = "camel.source.endpoint.timeout";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_TIMEOUT_DOC = "You can configure the timeout that specifies how long to wait for a response from a remote server. The timeout unit is in milliseconds, so 60000 is 60 seconds.";
    public static final Long CAMEL_SOURCE_MINA_ENDPOINT_TIMEOUT_DEFAULT = 30000L;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_WRITE_TIMEOUT_CONF = "camel.source.endpoint.writeTimeout";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_WRITE_TIMEOUT_DOC = "Maximum amount of time it should take to send data to the MINA session. Default is 10000 milliseconds.";
    public static final Long CAMEL_SOURCE_MINA_ENDPOINT_WRITE_TIMEOUT_DEFAULT = 10000L;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF = "camel.source.endpoint.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_MINA_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_CLIENT_MODE_CONF = "camel.source.endpoint.clientMode";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_CLIENT_MODE_DOC = "If the clientMode is true, mina consumer will connect the address as a TCP client.";
    public static final Boolean CAMEL_SOURCE_MINA_ENDPOINT_CLIENT_MODE_DEFAULT = false;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_DISCONNECT_ON_NO_REPLY_CONF = "camel.source.endpoint.disconnectOnNoReply";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_DISCONNECT_ON_NO_REPLY_DOC = "If sync is enabled then this option dictates MinaConsumer if it should disconnect where there is no reply to send back.";
    public static final Boolean CAMEL_SOURCE_MINA_ENDPOINT_DISCONNECT_ON_NO_REPLY_DEFAULT = true;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_EXCEPTION_HANDLER_CONF = "camel.source.endpoint.exceptionHandler";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_EXCEPTION_HANDLER_DOC = "To let the consumer use a custom ExceptionHandler. Notice if the option bridgeErrorHandler is enabled then this option is not in use. By default the consumer will deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_EXCEPTION_HANDLER_DEFAULT = null;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_EXCHANGE_PATTERN_CONF = "camel.source.endpoint.exchangePattern";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_EXCHANGE_PATTERN_DOC = "Sets the exchange pattern when the consumer creates an exchange. One of: [InOnly] [InOut] [InOptionalOut]";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_EXCHANGE_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_NO_REPLY_LOG_LEVEL_CONF = "camel.source.endpoint.noReplyLogLevel";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_NO_REPLY_LOG_LEVEL_DOC = "If sync is enabled this option dictates MinaConsumer which logging level to use when logging a there is no reply to send back. One of: [TRACE] [DEBUG] [INFO] [WARN] [ERROR] [OFF]";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_NO_REPLY_LOG_LEVEL_DEFAULT = "WARN";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_MAXIMUM_POOL_SIZE_CONF = "camel.source.endpoint.maximumPoolSize";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_MAXIMUM_POOL_SIZE_DOC = "Number of worker threads in the worker pool for TCP and UDP";
    public static final Integer CAMEL_SOURCE_MINA_ENDPOINT_MAXIMUM_POOL_SIZE_DEFAULT = 16;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_ORDERED_THREAD_POOL_EXECUTOR_CONF = "camel.source.endpoint.orderedThreadPoolExecutor";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_ORDERED_THREAD_POOL_EXECUTOR_DOC = "Whether to use ordered thread pool, to ensure events are processed orderly on the same channel.";
    public static final Boolean CAMEL_SOURCE_MINA_ENDPOINT_ORDERED_THREAD_POOL_EXECUTOR_DEFAULT = true;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_TRANSFER_EXCHANGE_CONF = "camel.source.endpoint.transferExchange";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_TRANSFER_EXCHANGE_DOC = "Only used for TCP. You can transfer the exchange over the wire instead of just the body. The following fields are transferred: In body, Out body, fault body, In headers, Out headers, fault headers, exchange properties, exchange exception. This requires that the objects are serializable. Camel will exclude any non-serializable objects and log it at WARN level.";
    public static final Boolean CAMEL_SOURCE_MINA_ENDPOINT_TRANSFER_EXCHANGE_DEFAULT = false;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_ALLOW_DEFAULT_CODEC_CONF = "camel.source.endpoint.allowDefaultCodec";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_ALLOW_DEFAULT_CODEC_DOC = "The mina component installs a default codec if both, codec is null and textline is false. Setting allowDefaultCodec to false prevents the mina component from installing a default codec as the first element in the filter chain. This is useful in scenarios where another filter must be the first in the filter chain, like the SSL filter.";
    public static final Boolean CAMEL_SOURCE_MINA_ENDPOINT_ALLOW_DEFAULT_CODEC_DEFAULT = true;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_CODEC_CONF = "camel.source.endpoint.codec";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_CODEC_DOC = "To use a custom minda codec implementation.";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_CODEC_DEFAULT = null;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_DECODER_MAX_LINE_LENGTH_CONF = "camel.source.endpoint.decoderMaxLineLength";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_DECODER_MAX_LINE_LENGTH_DOC = "To set the textline protocol decoder max line length. By default the default value of Mina itself is used which are 1024.";
    public static final Integer CAMEL_SOURCE_MINA_ENDPOINT_DECODER_MAX_LINE_LENGTH_DEFAULT = 1024;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_ENCODER_MAX_LINE_LENGTH_CONF = "camel.source.endpoint.encoderMaxLineLength";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_ENCODER_MAX_LINE_LENGTH_DOC = "To set the textline protocol encoder max line length. By default the default value of Mina itself is used which are Integer.MAX_VALUE.";
    public static final Integer CAMEL_SOURCE_MINA_ENDPOINT_ENCODER_MAX_LINE_LENGTH_DEFAULT = -1;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_ENCODING_CONF = "camel.source.endpoint.encoding";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_ENCODING_DOC = "You can configure the encoding (a charset name) to use for the TCP textline codec and the UDP protocol. If not provided, Camel will use the JVM default Charset";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_ENCODING_DEFAULT = null;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_FILTERS_CONF = "camel.source.endpoint.filters";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_FILTERS_DOC = "You can set a list of Mina IoFilters to use.";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_FILTERS_DEFAULT = null;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_TEXTLINE_CONF = "camel.source.endpoint.textline";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_TEXTLINE_DOC = "Only used for TCP. If no codec is specified, you can use this flag to indicate a text line based codec; if not specified or the value is false, then Object Serialization is assumed over TCP.";
    public static final Boolean CAMEL_SOURCE_MINA_ENDPOINT_TEXTLINE_DEFAULT = false;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_TEXTLINE_DELIMITER_CONF = "camel.source.endpoint.textlineDelimiter";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_TEXTLINE_DELIMITER_DOC = "Only used for TCP and if textline=true. Sets the text line delimiter to use. If none provided, Camel will use DEFAULT. This delimiter is used to mark the end of text. One of: [DEFAULT] [AUTO] [UNIX] [WINDOWS] [MAC]";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_TEXTLINE_DELIMITER_DEFAULT = null;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_AUTO_START_TLS_CONF = "camel.source.endpoint.autoStartTls";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_AUTO_START_TLS_DOC = "Whether to auto start SSL handshake.";
    public static final Boolean CAMEL_SOURCE_MINA_ENDPOINT_AUTO_START_TLS_DEFAULT = true;
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF = "camel.source.endpoint.sslContextParameters";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC = "To configure SSL security.";
    public static final String CAMEL_SOURCE_MINA_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_DISCONNECT_CONF = "camel.component.mina.disconnect";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_DISCONNECT_DOC = "Whether or not to disconnect(close) from Mina session right after use. Can be used for both consumer and producer.";
    public static final Boolean CAMEL_SOURCE_MINA_COMPONENT_DISCONNECT_DEFAULT = false;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_MINA_LOGGER_CONF = "camel.component.mina.minaLogger";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_MINA_LOGGER_DOC = "You can enable the Apache MINA logging filter. Apache MINA uses slf4j logging at INFO level to log all input and output.";
    public static final Boolean CAMEL_SOURCE_MINA_COMPONENT_MINA_LOGGER_DEFAULT = false;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_SYNC_CONF = "camel.component.mina.sync";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_SYNC_DOC = "Setting to set endpoint as one-way or request-response.";
    public static final Boolean CAMEL_SOURCE_MINA_COMPONENT_SYNC_DEFAULT = true;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_TIMEOUT_CONF = "camel.component.mina.timeout";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_TIMEOUT_DOC = "You can configure the timeout that specifies how long to wait for a response from a remote server. The timeout unit is in milliseconds, so 60000 is 60 seconds.";
    public static final Long CAMEL_SOURCE_MINA_COMPONENT_TIMEOUT_DEFAULT = 30000L;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_WRITE_TIMEOUT_CONF = "camel.component.mina.writeTimeout";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_WRITE_TIMEOUT_DOC = "Maximum amount of time it should take to send data to the MINA session. Default is 10000 milliseconds.";
    public static final Long CAMEL_SOURCE_MINA_COMPONENT_WRITE_TIMEOUT_DEFAULT = 10000L;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_BRIDGE_ERROR_HANDLER_CONF = "camel.component.mina.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_MINA_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_CLIENT_MODE_CONF = "camel.component.mina.clientMode";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_CLIENT_MODE_DOC = "If the clientMode is true, mina consumer will connect the address as a TCP client.";
    public static final Boolean CAMEL_SOURCE_MINA_COMPONENT_CLIENT_MODE_DEFAULT = false;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_DISCONNECT_ON_NO_REPLY_CONF = "camel.component.mina.disconnectOnNoReply";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_DISCONNECT_ON_NO_REPLY_DOC = "If sync is enabled then this option dictates MinaConsumer if it should disconnect where there is no reply to send back.";
    public static final Boolean CAMEL_SOURCE_MINA_COMPONENT_DISCONNECT_ON_NO_REPLY_DEFAULT = true;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_NO_REPLY_LOG_LEVEL_CONF = "camel.component.mina.noReplyLogLevel";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_NO_REPLY_LOG_LEVEL_DOC = "If sync is enabled this option dictates MinaConsumer which logging level to use when logging a there is no reply to send back. One of: [TRACE] [DEBUG] [INFO] [WARN] [ERROR] [OFF]";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_NO_REPLY_LOG_LEVEL_DEFAULT = "WARN";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.mina.autowiredEnabled";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SOURCE_MINA_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_CONFIGURATION_CONF = "camel.component.mina.configuration";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_CONFIGURATION_DOC = "To use the shared mina configuration.";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_MAXIMUM_POOL_SIZE_CONF = "camel.component.mina.maximumPoolSize";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_MAXIMUM_POOL_SIZE_DOC = "Number of worker threads in the worker pool for TCP and UDP";
    public static final Integer CAMEL_SOURCE_MINA_COMPONENT_MAXIMUM_POOL_SIZE_DEFAULT = 16;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_ORDERED_THREAD_POOL_EXECUTOR_CONF = "camel.component.mina.orderedThreadPoolExecutor";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_ORDERED_THREAD_POOL_EXECUTOR_DOC = "Whether to use ordered thread pool, to ensure events are processed orderly on the same channel.";
    public static final Boolean CAMEL_SOURCE_MINA_COMPONENT_ORDERED_THREAD_POOL_EXECUTOR_DEFAULT = true;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_TRANSFER_EXCHANGE_CONF = "camel.component.mina.transferExchange";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_TRANSFER_EXCHANGE_DOC = "Only used for TCP. You can transfer the exchange over the wire instead of just the body. The following fields are transferred: In body, Out body, fault body, In headers, Out headers, fault headers, exchange properties, exchange exception. This requires that the objects are serializable. Camel will exclude any non-serializable objects and log it at WARN level.";
    public static final Boolean CAMEL_SOURCE_MINA_COMPONENT_TRANSFER_EXCHANGE_DEFAULT = false;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_ALLOW_DEFAULT_CODEC_CONF = "camel.component.mina.allowDefaultCodec";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_ALLOW_DEFAULT_CODEC_DOC = "The mina component installs a default codec if both, codec is null and textline is false. Setting allowDefaultCodec to false prevents the mina component from installing a default codec as the first element in the filter chain. This is useful in scenarios where another filter must be the first in the filter chain, like the SSL filter.";
    public static final Boolean CAMEL_SOURCE_MINA_COMPONENT_ALLOW_DEFAULT_CODEC_DEFAULT = true;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_CODEC_CONF = "camel.component.mina.codec";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_CODEC_DOC = "To use a custom minda codec implementation.";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_CODEC_DEFAULT = null;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_DECODER_MAX_LINE_LENGTH_CONF = "camel.component.mina.decoderMaxLineLength";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_DECODER_MAX_LINE_LENGTH_DOC = "To set the textline protocol decoder max line length. By default the default value of Mina itself is used which are 1024.";
    public static final Integer CAMEL_SOURCE_MINA_COMPONENT_DECODER_MAX_LINE_LENGTH_DEFAULT = 1024;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_ENCODER_MAX_LINE_LENGTH_CONF = "camel.component.mina.encoderMaxLineLength";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_ENCODER_MAX_LINE_LENGTH_DOC = "To set the textline protocol encoder max line length. By default the default value of Mina itself is used which are Integer.MAX_VALUE.";
    public static final Integer CAMEL_SOURCE_MINA_COMPONENT_ENCODER_MAX_LINE_LENGTH_DEFAULT = -1;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_ENCODING_CONF = "camel.component.mina.encoding";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_ENCODING_DOC = "You can configure the encoding (a charset name) to use for the TCP textline codec and the UDP protocol. If not provided, Camel will use the JVM default Charset";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_ENCODING_DEFAULT = null;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_FILTERS_CONF = "camel.component.mina.filters";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_FILTERS_DOC = "You can set a list of Mina IoFilters to use.";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_FILTERS_DEFAULT = null;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_TEXTLINE_CONF = "camel.component.mina.textline";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_TEXTLINE_DOC = "Only used for TCP. If no codec is specified, you can use this flag to indicate a text line based codec; if not specified or the value is false, then Object Serialization is assumed over TCP.";
    public static final Boolean CAMEL_SOURCE_MINA_COMPONENT_TEXTLINE_DEFAULT = false;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_TEXTLINE_DELIMITER_CONF = "camel.component.mina.textlineDelimiter";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_TEXTLINE_DELIMITER_DOC = "Only used for TCP and if textline=true. Sets the text line delimiter to use. If none provided, Camel will use DEFAULT. This delimiter is used to mark the end of text. One of: [DEFAULT] [AUTO] [UNIX] [WINDOWS] [MAC]";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_TEXTLINE_DELIMITER_DEFAULT = null;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_AUTO_START_TLS_CONF = "camel.component.mina.autoStartTls";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_AUTO_START_TLS_DOC = "Whether to auto start SSL handshake.";
    public static final Boolean CAMEL_SOURCE_MINA_COMPONENT_AUTO_START_TLS_DEFAULT = true;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.mina.sslContextParameters";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_SSL_CONTEXT_PARAMETERS_DOC = "To configure SSL security.";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_SSL_CONTEXT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SOURCE_MINA_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.mina.useGlobalSslContextParameters";
    public static final String CAMEL_SOURCE_MINA_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC = "Enable usage of global SSL context parameters.";
    public static final Boolean CAMEL_SOURCE_MINA_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT = false;

    public CamelMinaSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelMinaSourceConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_MINA_PATH_PROTOCOL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MINA_PATH_PROTOCOL_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_MINA_PATH_PROTOCOL_DOC);
        conf.define(CAMEL_SOURCE_MINA_PATH_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MINA_PATH_HOST_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_MINA_PATH_HOST_DOC);
        conf.define(CAMEL_SOURCE_MINA_PATH_PORT_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_MINA_PATH_PORT_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_MINA_PATH_PORT_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_DISCONNECT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_ENDPOINT_DISCONNECT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_DISCONNECT_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_MINA_LOGGER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_ENDPOINT_MINA_LOGGER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_MINA_LOGGER_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_SYNC_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_ENDPOINT_SYNC_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_SYNC_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_TIMEOUT_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_MINA_ENDPOINT_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_TIMEOUT_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_WRITE_TIMEOUT_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_MINA_ENDPOINT_WRITE_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_WRITE_TIMEOUT_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_CLIENT_MODE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_ENDPOINT_CLIENT_MODE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_CLIENT_MODE_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_DISCONNECT_ON_NO_REPLY_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_ENDPOINT_DISCONNECT_ON_NO_REPLY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_DISCONNECT_ON_NO_REPLY_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_EXCEPTION_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MINA_ENDPOINT_EXCEPTION_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_EXCEPTION_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_EXCHANGE_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MINA_ENDPOINT_EXCHANGE_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_EXCHANGE_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_NO_REPLY_LOG_LEVEL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MINA_ENDPOINT_NO_REPLY_LOG_LEVEL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_NO_REPLY_LOG_LEVEL_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_MAXIMUM_POOL_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_MINA_ENDPOINT_MAXIMUM_POOL_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_MAXIMUM_POOL_SIZE_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_ORDERED_THREAD_POOL_EXECUTOR_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_ENDPOINT_ORDERED_THREAD_POOL_EXECUTOR_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_ORDERED_THREAD_POOL_EXECUTOR_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_TRANSFER_EXCHANGE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_ENDPOINT_TRANSFER_EXCHANGE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_TRANSFER_EXCHANGE_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_ALLOW_DEFAULT_CODEC_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_ENDPOINT_ALLOW_DEFAULT_CODEC_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_ALLOW_DEFAULT_CODEC_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_CODEC_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MINA_ENDPOINT_CODEC_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_CODEC_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_DECODER_MAX_LINE_LENGTH_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_MINA_ENDPOINT_DECODER_MAX_LINE_LENGTH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_DECODER_MAX_LINE_LENGTH_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_ENCODER_MAX_LINE_LENGTH_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_MINA_ENDPOINT_ENCODER_MAX_LINE_LENGTH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_ENCODER_MAX_LINE_LENGTH_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_ENCODING_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MINA_ENDPOINT_ENCODING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_ENCODING_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_FILTERS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MINA_ENDPOINT_FILTERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_FILTERS_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_TEXTLINE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_ENDPOINT_TEXTLINE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_TEXTLINE_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_TEXTLINE_DELIMITER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MINA_ENDPOINT_TEXTLINE_DELIMITER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_TEXTLINE_DELIMITER_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_AUTO_START_TLS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_ENDPOINT_AUTO_START_TLS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_AUTO_START_TLS_DOC);
        conf.define(CAMEL_SOURCE_MINA_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MINA_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_DISCONNECT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_COMPONENT_DISCONNECT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_DISCONNECT_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_MINA_LOGGER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_COMPONENT_MINA_LOGGER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_MINA_LOGGER_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_SYNC_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_COMPONENT_SYNC_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_SYNC_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_TIMEOUT_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_MINA_COMPONENT_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_TIMEOUT_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_WRITE_TIMEOUT_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_MINA_COMPONENT_WRITE_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_WRITE_TIMEOUT_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_CLIENT_MODE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_COMPONENT_CLIENT_MODE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_CLIENT_MODE_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_DISCONNECT_ON_NO_REPLY_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_COMPONENT_DISCONNECT_ON_NO_REPLY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_DISCONNECT_ON_NO_REPLY_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_NO_REPLY_LOG_LEVEL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MINA_COMPONENT_NO_REPLY_LOG_LEVEL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_NO_REPLY_LOG_LEVEL_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_AUTOWIRED_ENABLED_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MINA_COMPONENT_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_CONFIGURATION_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_MAXIMUM_POOL_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_MINA_COMPONENT_MAXIMUM_POOL_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_MAXIMUM_POOL_SIZE_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_ORDERED_THREAD_POOL_EXECUTOR_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_COMPONENT_ORDERED_THREAD_POOL_EXECUTOR_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_ORDERED_THREAD_POOL_EXECUTOR_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_TRANSFER_EXCHANGE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_COMPONENT_TRANSFER_EXCHANGE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_TRANSFER_EXCHANGE_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_ALLOW_DEFAULT_CODEC_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_COMPONENT_ALLOW_DEFAULT_CODEC_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_ALLOW_DEFAULT_CODEC_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_CODEC_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MINA_COMPONENT_CODEC_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_CODEC_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_DECODER_MAX_LINE_LENGTH_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_MINA_COMPONENT_DECODER_MAX_LINE_LENGTH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_DECODER_MAX_LINE_LENGTH_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_ENCODER_MAX_LINE_LENGTH_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_MINA_COMPONENT_ENCODER_MAX_LINE_LENGTH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_ENCODER_MAX_LINE_LENGTH_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_ENCODING_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MINA_COMPONENT_ENCODING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_ENCODING_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_FILTERS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MINA_COMPONENT_FILTERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_FILTERS_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_TEXTLINE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_COMPONENT_TEXTLINE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_TEXTLINE_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_TEXTLINE_DELIMITER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MINA_COMPONENT_TEXTLINE_DELIMITER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_TEXTLINE_DELIMITER_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_AUTO_START_TLS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_COMPONENT_AUTO_START_TLS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_AUTO_START_TLS_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MINA_COMPONENT_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_SSL_CONTEXT_PARAMETERS_DOC);
        conf.define(CAMEL_SOURCE_MINA_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MINA_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MINA_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC);
        return conf;
    }
}