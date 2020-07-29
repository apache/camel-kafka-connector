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
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelMinaSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_MINA_PATH_PROTOCOL_CONF = "camel.sink.path.protocol";
    private static final String CAMEL_SINK_MINA_PATH_PROTOCOL_DOC = "Protocol to use";
    private static final String CAMEL_SINK_MINA_PATH_PROTOCOL_DEFAULT = null;
    public static final String CAMEL_SINK_MINA_PATH_HOST_CONF = "camel.sink.path.host";
    private static final String CAMEL_SINK_MINA_PATH_HOST_DOC = "Hostname to use. Use localhost or 0.0.0.0 for local server as consumer. For producer use the hostname or ip address of the remote server.";
    private static final String CAMEL_SINK_MINA_PATH_HOST_DEFAULT = null;
    public static final String CAMEL_SINK_MINA_PATH_PORT_CONF = "camel.sink.path.port";
    private static final String CAMEL_SINK_MINA_PATH_PORT_DOC = "Port number";
    private static final Integer CAMEL_SINK_MINA_PATH_PORT_DEFAULT = null;
    public static final String CAMEL_SINK_MINA_ENDPOINT_DISCONNECT_CONF = "camel.sink.endpoint.disconnect";
    private static final String CAMEL_SINK_MINA_ENDPOINT_DISCONNECT_DOC = "Whether or not to disconnect(close) from Mina session right after use. Can be used for both consumer and producer.";
    private static final Boolean CAMEL_SINK_MINA_ENDPOINT_DISCONNECT_DEFAULT = false;
    public static final String CAMEL_SINK_MINA_ENDPOINT_MINA_LOGGER_CONF = "camel.sink.endpoint.minaLogger";
    private static final String CAMEL_SINK_MINA_ENDPOINT_MINA_LOGGER_DOC = "You can enable the Apache MINA logging filter. Apache MINA uses slf4j logging at INFO level to log all input and output.";
    private static final Boolean CAMEL_SINK_MINA_ENDPOINT_MINA_LOGGER_DEFAULT = false;
    public static final String CAMEL_SINK_MINA_ENDPOINT_SYNC_CONF = "camel.sink.endpoint.sync";
    private static final String CAMEL_SINK_MINA_ENDPOINT_SYNC_DOC = "Setting to set endpoint as one-way or request-response.";
    private static final Boolean CAMEL_SINK_MINA_ENDPOINT_SYNC_DEFAULT = true;
    public static final String CAMEL_SINK_MINA_ENDPOINT_TIMEOUT_CONF = "camel.sink.endpoint.timeout";
    private static final String CAMEL_SINK_MINA_ENDPOINT_TIMEOUT_DOC = "You can configure the timeout that specifies how long to wait for a response from a remote server. The timeout unit is in milliseconds, so 60000 is 60 seconds.";
    private static final Long CAMEL_SINK_MINA_ENDPOINT_TIMEOUT_DEFAULT = 30000L;
    public static final String CAMEL_SINK_MINA_ENDPOINT_WRITE_TIMEOUT_CONF = "camel.sink.endpoint.writeTimeout";
    private static final String CAMEL_SINK_MINA_ENDPOINT_WRITE_TIMEOUT_DOC = "Maximum amount of time it should take to send data to the MINA session. Default is 10000 milliseconds.";
    private static final Long CAMEL_SINK_MINA_ENDPOINT_WRITE_TIMEOUT_DEFAULT = 10000L;
    public static final String CAMEL_SINK_MINA_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    private static final String CAMEL_SINK_MINA_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_MINA_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_MINA_ENDPOINT_CACHED_ADDRESS_CONF = "camel.sink.endpoint.cachedAddress";
    private static final String CAMEL_SINK_MINA_ENDPOINT_CACHED_ADDRESS_DOC = "Whether to create the InetAddress once and reuse. Setting this to false allows to pickup DNS changes in the network.";
    private static final Boolean CAMEL_SINK_MINA_ENDPOINT_CACHED_ADDRESS_DEFAULT = true;
    public static final String CAMEL_SINK_MINA_ENDPOINT_LAZY_SESSION_CREATION_CONF = "camel.sink.endpoint.lazySessionCreation";
    private static final String CAMEL_SINK_MINA_ENDPOINT_LAZY_SESSION_CREATION_DOC = "Sessions can be lazily created to avoid exceptions, if the remote server is not up and running when the Camel producer is started.";
    private static final Boolean CAMEL_SINK_MINA_ENDPOINT_LAZY_SESSION_CREATION_DEFAULT = true;
    public static final String CAMEL_SINK_MINA_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    private static final String CAMEL_SINK_MINA_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_MINA_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_MINA_ENDPOINT_MAXIMUM_POOL_SIZE_CONF = "camel.sink.endpoint.maximumPoolSize";
    private static final String CAMEL_SINK_MINA_ENDPOINT_MAXIMUM_POOL_SIZE_DOC = "Number of worker threads in the worker pool for TCP and UDP";
    private static final Integer CAMEL_SINK_MINA_ENDPOINT_MAXIMUM_POOL_SIZE_DEFAULT = 16;
    public static final String CAMEL_SINK_MINA_ENDPOINT_ORDERED_THREAD_POOL_EXECUTOR_CONF = "camel.sink.endpoint.orderedThreadPoolExecutor";
    private static final String CAMEL_SINK_MINA_ENDPOINT_ORDERED_THREAD_POOL_EXECUTOR_DOC = "Whether to use ordered thread pool, to ensure events are processed orderly on the same channel.";
    private static final Boolean CAMEL_SINK_MINA_ENDPOINT_ORDERED_THREAD_POOL_EXECUTOR_DEFAULT = true;
    public static final String CAMEL_SINK_MINA_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    private static final String CAMEL_SINK_MINA_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SINK_MINA_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_MINA_ENDPOINT_TRANSFER_EXCHANGE_CONF = "camel.sink.endpoint.transferExchange";
    private static final String CAMEL_SINK_MINA_ENDPOINT_TRANSFER_EXCHANGE_DOC = "Only used for TCP. You can transfer the exchange over the wire instead of just the body. The following fields are transferred: In body, Out body, fault body, In headers, Out headers, fault headers, exchange properties, exchange exception. This requires that the objects are serializable. Camel will exclude any non-serializable objects and log it at WARN level.";
    private static final Boolean CAMEL_SINK_MINA_ENDPOINT_TRANSFER_EXCHANGE_DEFAULT = false;
    public static final String CAMEL_SINK_MINA_ENDPOINT_ALLOW_DEFAULT_CODEC_CONF = "camel.sink.endpoint.allowDefaultCodec";
    private static final String CAMEL_SINK_MINA_ENDPOINT_ALLOW_DEFAULT_CODEC_DOC = "The mina component installs a default codec if both, codec is null and textline is false. Setting allowDefaultCodec to false prevents the mina component from installing a default codec as the first element in the filter chain. This is useful in scenarios where another filter must be the first in the filter chain, like the SSL filter.";
    private static final Boolean CAMEL_SINK_MINA_ENDPOINT_ALLOW_DEFAULT_CODEC_DEFAULT = true;
    public static final String CAMEL_SINK_MINA_ENDPOINT_CODEC_CONF = "camel.sink.endpoint.codec";
    private static final String CAMEL_SINK_MINA_ENDPOINT_CODEC_DOC = "To use a custom minda codec implementation.";
    private static final String CAMEL_SINK_MINA_ENDPOINT_CODEC_DEFAULT = null;
    public static final String CAMEL_SINK_MINA_ENDPOINT_DECODER_MAX_LINE_LENGTH_CONF = "camel.sink.endpoint.decoderMaxLineLength";
    private static final String CAMEL_SINK_MINA_ENDPOINT_DECODER_MAX_LINE_LENGTH_DOC = "To set the textline protocol decoder max line length. By default the default value of Mina itself is used which are 1024.";
    private static final Integer CAMEL_SINK_MINA_ENDPOINT_DECODER_MAX_LINE_LENGTH_DEFAULT = 1024;
    public static final String CAMEL_SINK_MINA_ENDPOINT_ENCODER_MAX_LINE_LENGTH_CONF = "camel.sink.endpoint.encoderMaxLineLength";
    private static final String CAMEL_SINK_MINA_ENDPOINT_ENCODER_MAX_LINE_LENGTH_DOC = "To set the textline protocol encoder max line length. By default the default value of Mina itself is used which are Integer.MAX_VALUE.";
    private static final Integer CAMEL_SINK_MINA_ENDPOINT_ENCODER_MAX_LINE_LENGTH_DEFAULT = -1;
    public static final String CAMEL_SINK_MINA_ENDPOINT_ENCODING_CONF = "camel.sink.endpoint.encoding";
    private static final String CAMEL_SINK_MINA_ENDPOINT_ENCODING_DOC = "You can configure the encoding (a charset name) to use for the TCP textline codec and the UDP protocol. If not provided, Camel will use the JVM default Charset";
    private static final String CAMEL_SINK_MINA_ENDPOINT_ENCODING_DEFAULT = null;
    public static final String CAMEL_SINK_MINA_ENDPOINT_FILTERS_CONF = "camel.sink.endpoint.filters";
    private static final String CAMEL_SINK_MINA_ENDPOINT_FILTERS_DOC = "You can set a list of Mina IoFilters to use.";
    private static final String CAMEL_SINK_MINA_ENDPOINT_FILTERS_DEFAULT = null;
    public static final String CAMEL_SINK_MINA_ENDPOINT_TEXTLINE_CONF = "camel.sink.endpoint.textline";
    private static final String CAMEL_SINK_MINA_ENDPOINT_TEXTLINE_DOC = "Only used for TCP. If no codec is specified, you can use this flag to indicate a text line based codec; if not specified or the value is false, then Object Serialization is assumed over TCP.";
    private static final Boolean CAMEL_SINK_MINA_ENDPOINT_TEXTLINE_DEFAULT = false;
    public static final String CAMEL_SINK_MINA_ENDPOINT_TEXTLINE_DELIMITER_CONF = "camel.sink.endpoint.textlineDelimiter";
    private static final String CAMEL_SINK_MINA_ENDPOINT_TEXTLINE_DELIMITER_DOC = "Only used for TCP and if textline=true. Sets the text line delimiter to use. If none provided, Camel will use DEFAULT. This delimiter is used to mark the end of text. One of: [DEFAULT] [AUTO] [UNIX] [WINDOWS] [MAC]";
    private static final String CAMEL_SINK_MINA_ENDPOINT_TEXTLINE_DELIMITER_DEFAULT = null;
    public static final String CAMEL_SINK_MINA_ENDPOINT_AUTO_START_TLS_CONF = "camel.sink.endpoint.autoStartTls";
    private static final String CAMEL_SINK_MINA_ENDPOINT_AUTO_START_TLS_DOC = "Whether to auto start SSL handshake.";
    private static final Boolean CAMEL_SINK_MINA_ENDPOINT_AUTO_START_TLS_DEFAULT = true;
    public static final String CAMEL_SINK_MINA_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF = "camel.sink.endpoint.sslContextParameters";
    private static final String CAMEL_SINK_MINA_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC = "To configure SSL security.";
    private static final String CAMEL_SINK_MINA_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SINK_MINA_COMPONENT_DISCONNECT_CONF = "camel.component.mina.disconnect";
    private static final String CAMEL_SINK_MINA_COMPONENT_DISCONNECT_DOC = "Whether or not to disconnect(close) from Mina session right after use. Can be used for both consumer and producer.";
    private static final Boolean CAMEL_SINK_MINA_COMPONENT_DISCONNECT_DEFAULT = false;
    public static final String CAMEL_SINK_MINA_COMPONENT_MINA_LOGGER_CONF = "camel.component.mina.minaLogger";
    private static final String CAMEL_SINK_MINA_COMPONENT_MINA_LOGGER_DOC = "You can enable the Apache MINA logging filter. Apache MINA uses slf4j logging at INFO level to log all input and output.";
    private static final Boolean CAMEL_SINK_MINA_COMPONENT_MINA_LOGGER_DEFAULT = false;
    public static final String CAMEL_SINK_MINA_COMPONENT_SYNC_CONF = "camel.component.mina.sync";
    private static final String CAMEL_SINK_MINA_COMPONENT_SYNC_DOC = "Setting to set endpoint as one-way or request-response.";
    private static final Boolean CAMEL_SINK_MINA_COMPONENT_SYNC_DEFAULT = true;
    public static final String CAMEL_SINK_MINA_COMPONENT_TIMEOUT_CONF = "camel.component.mina.timeout";
    private static final String CAMEL_SINK_MINA_COMPONENT_TIMEOUT_DOC = "You can configure the timeout that specifies how long to wait for a response from a remote server. The timeout unit is in milliseconds, so 60000 is 60 seconds.";
    private static final Long CAMEL_SINK_MINA_COMPONENT_TIMEOUT_DEFAULT = 30000L;
    public static final String CAMEL_SINK_MINA_COMPONENT_WRITE_TIMEOUT_CONF = "camel.component.mina.writeTimeout";
    private static final String CAMEL_SINK_MINA_COMPONENT_WRITE_TIMEOUT_DOC = "Maximum amount of time it should take to send data to the MINA session. Default is 10000 milliseconds.";
    private static final Long CAMEL_SINK_MINA_COMPONENT_WRITE_TIMEOUT_DEFAULT = 10000L;
    public static final String CAMEL_SINK_MINA_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.mina.lazyStartProducer";
    private static final String CAMEL_SINK_MINA_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_MINA_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_MINA_COMPONENT_CACHED_ADDRESS_CONF = "camel.component.mina.cachedAddress";
    private static final String CAMEL_SINK_MINA_COMPONENT_CACHED_ADDRESS_DOC = "Whether to create the InetAddress once and reuse. Setting this to false allows to pickup DNS changes in the network.";
    private static final Boolean CAMEL_SINK_MINA_COMPONENT_CACHED_ADDRESS_DEFAULT = true;
    public static final String CAMEL_SINK_MINA_COMPONENT_LAZY_SESSION_CREATION_CONF = "camel.component.mina.lazySessionCreation";
    private static final String CAMEL_SINK_MINA_COMPONENT_LAZY_SESSION_CREATION_DOC = "Sessions can be lazily created to avoid exceptions, if the remote server is not up and running when the Camel producer is started.";
    private static final Boolean CAMEL_SINK_MINA_COMPONENT_LAZY_SESSION_CREATION_DEFAULT = true;
    public static final String CAMEL_SINK_MINA_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.mina.basicPropertyBinding";
    private static final String CAMEL_SINK_MINA_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_MINA_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_MINA_COMPONENT_CONFIGURATION_CONF = "camel.component.mina.configuration";
    private static final String CAMEL_SINK_MINA_COMPONENT_CONFIGURATION_DOC = "To use the shared mina configuration.";
    private static final String CAMEL_SINK_MINA_COMPONENT_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SINK_MINA_COMPONENT_MAXIMUM_POOL_SIZE_CONF = "camel.component.mina.maximumPoolSize";
    private static final String CAMEL_SINK_MINA_COMPONENT_MAXIMUM_POOL_SIZE_DOC = "Number of worker threads in the worker pool for TCP and UDP";
    private static final Integer CAMEL_SINK_MINA_COMPONENT_MAXIMUM_POOL_SIZE_DEFAULT = 16;
    public static final String CAMEL_SINK_MINA_COMPONENT_ORDERED_THREAD_POOL_EXECUTOR_CONF = "camel.component.mina.orderedThreadPoolExecutor";
    private static final String CAMEL_SINK_MINA_COMPONENT_ORDERED_THREAD_POOL_EXECUTOR_DOC = "Whether to use ordered thread pool, to ensure events are processed orderly on the same channel.";
    private static final Boolean CAMEL_SINK_MINA_COMPONENT_ORDERED_THREAD_POOL_EXECUTOR_DEFAULT = true;
    public static final String CAMEL_SINK_MINA_COMPONENT_TRANSFER_EXCHANGE_CONF = "camel.component.mina.transferExchange";
    private static final String CAMEL_SINK_MINA_COMPONENT_TRANSFER_EXCHANGE_DOC = "Only used for TCP. You can transfer the exchange over the wire instead of just the body. The following fields are transferred: In body, Out body, fault body, In headers, Out headers, fault headers, exchange properties, exchange exception. This requires that the objects are serializable. Camel will exclude any non-serializable objects and log it at WARN level.";
    private static final Boolean CAMEL_SINK_MINA_COMPONENT_TRANSFER_EXCHANGE_DEFAULT = false;
    public static final String CAMEL_SINK_MINA_COMPONENT_ALLOW_DEFAULT_CODEC_CONF = "camel.component.mina.allowDefaultCodec";
    private static final String CAMEL_SINK_MINA_COMPONENT_ALLOW_DEFAULT_CODEC_DOC = "The mina component installs a default codec if both, codec is null and textline is false. Setting allowDefaultCodec to false prevents the mina component from installing a default codec as the first element in the filter chain. This is useful in scenarios where another filter must be the first in the filter chain, like the SSL filter.";
    private static final Boolean CAMEL_SINK_MINA_COMPONENT_ALLOW_DEFAULT_CODEC_DEFAULT = true;
    public static final String CAMEL_SINK_MINA_COMPONENT_CODEC_CONF = "camel.component.mina.codec";
    private static final String CAMEL_SINK_MINA_COMPONENT_CODEC_DOC = "To use a custom minda codec implementation.";
    private static final String CAMEL_SINK_MINA_COMPONENT_CODEC_DEFAULT = null;
    public static final String CAMEL_SINK_MINA_COMPONENT_DECODER_MAX_LINE_LENGTH_CONF = "camel.component.mina.decoderMaxLineLength";
    private static final String CAMEL_SINK_MINA_COMPONENT_DECODER_MAX_LINE_LENGTH_DOC = "To set the textline protocol decoder max line length. By default the default value of Mina itself is used which are 1024.";
    private static final Integer CAMEL_SINK_MINA_COMPONENT_DECODER_MAX_LINE_LENGTH_DEFAULT = 1024;
    public static final String CAMEL_SINK_MINA_COMPONENT_ENCODER_MAX_LINE_LENGTH_CONF = "camel.component.mina.encoderMaxLineLength";
    private static final String CAMEL_SINK_MINA_COMPONENT_ENCODER_MAX_LINE_LENGTH_DOC = "To set the textline protocol encoder max line length. By default the default value of Mina itself is used which are Integer.MAX_VALUE.";
    private static final Integer CAMEL_SINK_MINA_COMPONENT_ENCODER_MAX_LINE_LENGTH_DEFAULT = -1;
    public static final String CAMEL_SINK_MINA_COMPONENT_ENCODING_CONF = "camel.component.mina.encoding";
    private static final String CAMEL_SINK_MINA_COMPONENT_ENCODING_DOC = "You can configure the encoding (a charset name) to use for the TCP textline codec and the UDP protocol. If not provided, Camel will use the JVM default Charset";
    private static final String CAMEL_SINK_MINA_COMPONENT_ENCODING_DEFAULT = null;
    public static final String CAMEL_SINK_MINA_COMPONENT_FILTERS_CONF = "camel.component.mina.filters";
    private static final String CAMEL_SINK_MINA_COMPONENT_FILTERS_DOC = "You can set a list of Mina IoFilters to use.";
    private static final String CAMEL_SINK_MINA_COMPONENT_FILTERS_DEFAULT = null;
    public static final String CAMEL_SINK_MINA_COMPONENT_TEXTLINE_CONF = "camel.component.mina.textline";
    private static final String CAMEL_SINK_MINA_COMPONENT_TEXTLINE_DOC = "Only used for TCP. If no codec is specified, you can use this flag to indicate a text line based codec; if not specified or the value is false, then Object Serialization is assumed over TCP.";
    private static final Boolean CAMEL_SINK_MINA_COMPONENT_TEXTLINE_DEFAULT = false;
    public static final String CAMEL_SINK_MINA_COMPONENT_TEXTLINE_DELIMITER_CONF = "camel.component.mina.textlineDelimiter";
    private static final String CAMEL_SINK_MINA_COMPONENT_TEXTLINE_DELIMITER_DOC = "Only used for TCP and if textline=true. Sets the text line delimiter to use. If none provided, Camel will use DEFAULT. This delimiter is used to mark the end of text. One of: [DEFAULT] [AUTO] [UNIX] [WINDOWS] [MAC]";
    private static final String CAMEL_SINK_MINA_COMPONENT_TEXTLINE_DELIMITER_DEFAULT = null;
    public static final String CAMEL_SINK_MINA_COMPONENT_AUTO_START_TLS_CONF = "camel.component.mina.autoStartTls";
    private static final String CAMEL_SINK_MINA_COMPONENT_AUTO_START_TLS_DOC = "Whether to auto start SSL handshake.";
    private static final Boolean CAMEL_SINK_MINA_COMPONENT_AUTO_START_TLS_DEFAULT = true;
    public static final String CAMEL_SINK_MINA_COMPONENT_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.mina.sslContextParameters";
    private static final String CAMEL_SINK_MINA_COMPONENT_SSL_CONTEXT_PARAMETERS_DOC = "To configure SSL security.";
    private static final String CAMEL_SINK_MINA_COMPONENT_SSL_CONTEXT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SINK_MINA_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.mina.useGlobalSslContextParameters";
    private static final String CAMEL_SINK_MINA_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC = "Enable usage of global SSL context parameters.";
    private static final Boolean CAMEL_SINK_MINA_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT = false;

    public CamelMinaSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelMinaSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_MINA_PATH_PROTOCOL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MINA_PATH_PROTOCOL_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_MINA_PATH_PROTOCOL_DOC);
        conf.define(CAMEL_SINK_MINA_PATH_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MINA_PATH_HOST_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_MINA_PATH_HOST_DOC);
        conf.define(CAMEL_SINK_MINA_PATH_PORT_CONF, ConfigDef.Type.INT, CAMEL_SINK_MINA_PATH_PORT_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_MINA_PATH_PORT_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_DISCONNECT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_ENDPOINT_DISCONNECT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_DISCONNECT_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_MINA_LOGGER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_ENDPOINT_MINA_LOGGER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_MINA_LOGGER_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_SYNC_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_ENDPOINT_SYNC_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_SYNC_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_TIMEOUT_CONF, ConfigDef.Type.LONG, CAMEL_SINK_MINA_ENDPOINT_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_WRITE_TIMEOUT_CONF, ConfigDef.Type.LONG, CAMEL_SINK_MINA_ENDPOINT_WRITE_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_WRITE_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_CACHED_ADDRESS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_ENDPOINT_CACHED_ADDRESS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_CACHED_ADDRESS_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_LAZY_SESSION_CREATION_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_ENDPOINT_LAZY_SESSION_CREATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_LAZY_SESSION_CREATION_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_MAXIMUM_POOL_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SINK_MINA_ENDPOINT_MAXIMUM_POOL_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_MAXIMUM_POOL_SIZE_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_ORDERED_THREAD_POOL_EXECUTOR_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_ENDPOINT_ORDERED_THREAD_POOL_EXECUTOR_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_ORDERED_THREAD_POOL_EXECUTOR_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_TRANSFER_EXCHANGE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_ENDPOINT_TRANSFER_EXCHANGE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_TRANSFER_EXCHANGE_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_ALLOW_DEFAULT_CODEC_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_ENDPOINT_ALLOW_DEFAULT_CODEC_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_ALLOW_DEFAULT_CODEC_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_CODEC_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MINA_ENDPOINT_CODEC_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_CODEC_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_DECODER_MAX_LINE_LENGTH_CONF, ConfigDef.Type.INT, CAMEL_SINK_MINA_ENDPOINT_DECODER_MAX_LINE_LENGTH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_DECODER_MAX_LINE_LENGTH_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_ENCODER_MAX_LINE_LENGTH_CONF, ConfigDef.Type.INT, CAMEL_SINK_MINA_ENDPOINT_ENCODER_MAX_LINE_LENGTH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_ENCODER_MAX_LINE_LENGTH_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_ENCODING_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MINA_ENDPOINT_ENCODING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_ENCODING_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_FILTERS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MINA_ENDPOINT_FILTERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_FILTERS_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_TEXTLINE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_ENDPOINT_TEXTLINE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_TEXTLINE_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_TEXTLINE_DELIMITER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MINA_ENDPOINT_TEXTLINE_DELIMITER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_TEXTLINE_DELIMITER_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_AUTO_START_TLS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_ENDPOINT_AUTO_START_TLS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_AUTO_START_TLS_DOC);
        conf.define(CAMEL_SINK_MINA_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MINA_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_DISCONNECT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_COMPONENT_DISCONNECT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_DISCONNECT_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_MINA_LOGGER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_COMPONENT_MINA_LOGGER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_MINA_LOGGER_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_SYNC_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_COMPONENT_SYNC_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_SYNC_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_TIMEOUT_CONF, ConfigDef.Type.LONG, CAMEL_SINK_MINA_COMPONENT_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_WRITE_TIMEOUT_CONF, ConfigDef.Type.LONG, CAMEL_SINK_MINA_COMPONENT_WRITE_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_WRITE_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_CACHED_ADDRESS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_COMPONENT_CACHED_ADDRESS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_CACHED_ADDRESS_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_LAZY_SESSION_CREATION_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_COMPONENT_LAZY_SESSION_CREATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_LAZY_SESSION_CREATION_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MINA_COMPONENT_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_CONFIGURATION_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_MAXIMUM_POOL_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SINK_MINA_COMPONENT_MAXIMUM_POOL_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_MAXIMUM_POOL_SIZE_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_ORDERED_THREAD_POOL_EXECUTOR_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_COMPONENT_ORDERED_THREAD_POOL_EXECUTOR_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_ORDERED_THREAD_POOL_EXECUTOR_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_TRANSFER_EXCHANGE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_COMPONENT_TRANSFER_EXCHANGE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_TRANSFER_EXCHANGE_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_ALLOW_DEFAULT_CODEC_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_COMPONENT_ALLOW_DEFAULT_CODEC_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_ALLOW_DEFAULT_CODEC_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_CODEC_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MINA_COMPONENT_CODEC_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_CODEC_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_DECODER_MAX_LINE_LENGTH_CONF, ConfigDef.Type.INT, CAMEL_SINK_MINA_COMPONENT_DECODER_MAX_LINE_LENGTH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_DECODER_MAX_LINE_LENGTH_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_ENCODER_MAX_LINE_LENGTH_CONF, ConfigDef.Type.INT, CAMEL_SINK_MINA_COMPONENT_ENCODER_MAX_LINE_LENGTH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_ENCODER_MAX_LINE_LENGTH_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_ENCODING_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MINA_COMPONENT_ENCODING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_ENCODING_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_FILTERS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MINA_COMPONENT_FILTERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_FILTERS_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_TEXTLINE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_COMPONENT_TEXTLINE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_TEXTLINE_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_TEXTLINE_DELIMITER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MINA_COMPONENT_TEXTLINE_DELIMITER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_TEXTLINE_DELIMITER_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_AUTO_START_TLS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_COMPONENT_AUTO_START_TLS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_AUTO_START_TLS_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MINA_COMPONENT_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_SSL_CONTEXT_PARAMETERS_DOC);
        conf.define(CAMEL_SINK_MINA_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MINA_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MINA_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC);
        return conf;
    }
}