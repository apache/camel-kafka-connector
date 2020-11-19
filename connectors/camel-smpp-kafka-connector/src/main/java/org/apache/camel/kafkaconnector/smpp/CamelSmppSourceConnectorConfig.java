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
package org.apache.camel.kafkaconnector.smpp;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelSmppSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_SMPP_PATH_HOST_CONF = "camel.source.path.host";
    public static final String CAMEL_SOURCE_SMPP_PATH_HOST_DOC = "Hostname for the SMSC server to use.";
    public static final String CAMEL_SOURCE_SMPP_PATH_HOST_DEFAULT = "localhost";
    public static final String CAMEL_SOURCE_SMPP_PATH_PORT_CONF = "camel.source.path.port";
    public static final String CAMEL_SOURCE_SMPP_PATH_PORT_DOC = "Port number for the SMSC server to use.";
    public static final String CAMEL_SOURCE_SMPP_PATH_PORT_DEFAULT = "2775";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_INITIAL_RECONNECT_DELAY_CONF = "camel.source.endpoint.initialReconnectDelay";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_INITIAL_RECONNECT_DELAY_DOC = "Defines the initial delay in milliseconds after the consumer/producer tries to reconnect to the SMSC, after the connection was lost.";
    public static final Long CAMEL_SOURCE_SMPP_ENDPOINT_INITIAL_RECONNECT_DELAY_DEFAULT = 5000L;
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_MAX_RECONNECT_CONF = "camel.source.endpoint.maxReconnect";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_MAX_RECONNECT_DOC = "Defines the maximum number of attempts to reconnect to the SMSC, if SMSC returns a negative bind response";
    public static final Integer CAMEL_SOURCE_SMPP_ENDPOINT_MAX_RECONNECT_DEFAULT = 2147483647;
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_RECONNECT_DELAY_CONF = "camel.source.endpoint.reconnectDelay";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_RECONNECT_DELAY_DOC = "Defines the interval in milliseconds between the reconnect attempts, if the connection to the SMSC was lost and the previous was not succeed.";
    public static final Long CAMEL_SOURCE_SMPP_ENDPOINT_RECONNECT_DELAY_DEFAULT = 5000L;
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_SPLITTING_POLICY_CONF = "camel.source.endpoint.splittingPolicy";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_SPLITTING_POLICY_DOC = "You can specify a policy for handling long messages: ALLOW - the default, long messages are split to 140 bytes per message TRUNCATE - long messages are split and only the first fragment will be sent to the SMSC. Some carriers drop subsequent fragments so this reduces load on the SMPP connection sending parts of a message that will never be delivered. REJECT - if a message would need to be split, it is rejected with an SMPP NegativeResponseException and the reason code signifying the message is too long. One of: [ALLOW] [REJECT] [TRUNCATE]";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_SPLITTING_POLICY_DEFAULT = "ALLOW";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_SYSTEM_TYPE_CONF = "camel.source.endpoint.systemType";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_SYSTEM_TYPE_DOC = "This parameter is used to categorize the type of ESME (External Short Message Entity) that is binding to the SMSC (max. 13 characters).";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_SYSTEM_TYPE_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_ADDRESS_RANGE_CONF = "camel.source.endpoint.addressRange";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_ADDRESS_RANGE_DOC = "You can specify the address range for the SmppConsumer as defined in section 5.2.7 of the SMPP 3.4 specification. The SmppConsumer will receive messages only from SMSC's which target an address (MSISDN or IP address) within this range.";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_ADDRESS_RANGE_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF = "camel.source.endpoint.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_SMPP_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_EXCEPTION_HANDLER_CONF = "camel.source.endpoint.exceptionHandler";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_EXCEPTION_HANDLER_DOC = "To let the consumer use a custom ExceptionHandler. Notice if the option bridgeErrorHandler is enabled then this option is not in use. By default the consumer will deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_EXCEPTION_HANDLER_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_EXCHANGE_PATTERN_CONF = "camel.source.endpoint.exchangePattern";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_EXCHANGE_PATTERN_DOC = "Sets the exchange pattern when the consumer creates an exchange. One of: [InOnly] [InOut] [InOptionalOut]";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_EXCHANGE_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_ENQUIRE_LINK_TIMER_CONF = "camel.source.endpoint.enquireLinkTimer";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_ENQUIRE_LINK_TIMER_DOC = "Defines the interval in milliseconds between the confidence checks. The confidence check is used to test the communication path between an ESME and an SMSC.";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_ENQUIRE_LINK_TIMER_DEFAULT = "5000";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_SESSION_STATE_LISTENER_CONF = "camel.source.endpoint.sessionStateListener";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_SESSION_STATE_LISTENER_DOC = "You can refer to a org.jsmpp.session.SessionStateListener in the Registry to receive callbacks when the session state changed.";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_SESSION_STATE_LISTENER_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_SYNCHRONOUS_CONF = "camel.source.endpoint.synchronous";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SOURCE_SMPP_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_TRANSACTION_TIMER_CONF = "camel.source.endpoint.transactionTimer";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_TRANSACTION_TIMER_DOC = "Defines the maximum period of inactivity allowed after a transaction, after which an SMPP entity may assume that the session is no longer active. This timer may be active on either communicating SMPP entity (i.e. SMSC or ESME).";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_TRANSACTION_TIMER_DEFAULT = "10000";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_ALPHABET_CONF = "camel.source.endpoint.alphabet";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_ALPHABET_DOC = "Defines encoding of data according the SMPP 3.4 specification, section 5.2.19. 0: SMSC Default Alphabet 4: 8 bit Alphabet 8: UCS2 Alphabet One of: [0] [4] [8]";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_ALPHABET_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_DATA_CODING_CONF = "camel.source.endpoint.dataCoding";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_DATA_CODING_DOC = "Defines the data coding according the SMPP 3.4 specification, section 5.2.19. Example data encodings are: 0: SMSC Default Alphabet 3: Latin 1 (ISO-8859-1) 4: Octet unspecified (8-bit binary) 8: UCS2 (ISO/IEC-10646) 13: Extended Kanji JIS(X 0212-1990)";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_DATA_CODING_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_ENCODING_CONF = "camel.source.endpoint.encoding";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_ENCODING_DOC = "Defines the encoding scheme of the short message user data. Only for SubmitSm, ReplaceSm and SubmitMulti.";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_ENCODING_DEFAULT = "ISO-8859-1";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_HOST_CONF = "camel.source.endpoint.httpProxyHost";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_HOST_DOC = "If you need to tunnel SMPP through a HTTP proxy, set this attribute to the hostname or ip address of your HTTP proxy.";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_HOST_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_PASSWORD_CONF = "camel.source.endpoint.httpProxyPassword";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_PASSWORD_DOC = "If your HTTP proxy requires basic authentication, set this attribute to the password required for your HTTP proxy.";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_PORT_CONF = "camel.source.endpoint.httpProxyPort";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_PORT_DOC = "If you need to tunnel SMPP through a HTTP proxy, set this attribute to the port of your HTTP proxy.";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_PORT_DEFAULT = "3128";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_USERNAME_CONF = "camel.source.endpoint.httpProxyUsername";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_USERNAME_DOC = "If your HTTP proxy requires basic authentication, set this attribute to the username required for your HTTP proxy.";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_USERNAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_PROXY_HEADERS_CONF = "camel.source.endpoint.proxyHeaders";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_PROXY_HEADERS_DOC = "These headers will be passed to the proxy server while establishing the connection.";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_PROXY_HEADERS_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_PASSWORD_CONF = "camel.source.endpoint.password";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_PASSWORD_DOC = "The password for connecting to SMSC server.";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_SYSTEM_ID_CONF = "camel.source.endpoint.systemId";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_SYSTEM_ID_DOC = "The system id (username) for connecting to SMSC server.";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_SYSTEM_ID_DEFAULT = "smppclient";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_USING_SSLCONF = "camel.source.endpoint.usingSSL";
    public static final String CAMEL_SOURCE_SMPP_ENDPOINT_USING_SSLDOC = "Whether using SSL with the smpps protocol";
    public static final Boolean CAMEL_SOURCE_SMPP_ENDPOINT_USING_SSLDEFAULT = false;
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_INITIAL_RECONNECT_DELAY_CONF = "camel.component.smpp.initialReconnectDelay";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_INITIAL_RECONNECT_DELAY_DOC = "Defines the initial delay in milliseconds after the consumer/producer tries to reconnect to the SMSC, after the connection was lost.";
    public static final Long CAMEL_SOURCE_SMPP_COMPONENT_INITIAL_RECONNECT_DELAY_DEFAULT = 5000L;
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_MAX_RECONNECT_CONF = "camel.component.smpp.maxReconnect";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_MAX_RECONNECT_DOC = "Defines the maximum number of attempts to reconnect to the SMSC, if SMSC returns a negative bind response";
    public static final Integer CAMEL_SOURCE_SMPP_COMPONENT_MAX_RECONNECT_DEFAULT = 2147483647;
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_RECONNECT_DELAY_CONF = "camel.component.smpp.reconnectDelay";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_RECONNECT_DELAY_DOC = "Defines the interval in milliseconds between the reconnect attempts, if the connection to the SMSC was lost and the previous was not succeed.";
    public static final Long CAMEL_SOURCE_SMPP_COMPONENT_RECONNECT_DELAY_DEFAULT = 5000L;
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_SPLITTING_POLICY_CONF = "camel.component.smpp.splittingPolicy";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_SPLITTING_POLICY_DOC = "You can specify a policy for handling long messages: ALLOW - the default, long messages are split to 140 bytes per message TRUNCATE - long messages are split and only the first fragment will be sent to the SMSC. Some carriers drop subsequent fragments so this reduces load on the SMPP connection sending parts of a message that will never be delivered. REJECT - if a message would need to be split, it is rejected with an SMPP NegativeResponseException and the reason code signifying the message is too long. One of: [ALLOW] [REJECT] [TRUNCATE]";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_SPLITTING_POLICY_DEFAULT = "ALLOW";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_SYSTEM_TYPE_CONF = "camel.component.smpp.systemType";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_SYSTEM_TYPE_DOC = "This parameter is used to categorize the type of ESME (External Short Message Entity) that is binding to the SMSC (max. 13 characters).";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_SYSTEM_TYPE_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_ADDRESS_RANGE_CONF = "camel.component.smpp.addressRange";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_ADDRESS_RANGE_DOC = "You can specify the address range for the SmppConsumer as defined in section 5.2.7 of the SMPP 3.4 specification. The SmppConsumer will receive messages only from SMSC's which target an address (MSISDN or IP address) within this range.";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_ADDRESS_RANGE_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_BRIDGE_ERROR_HANDLER_CONF = "camel.component.smpp.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_SMPP_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.smpp.autowiredEnabled";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SOURCE_SMPP_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_CONFIGURATION_CONF = "camel.component.smpp.configuration";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_CONFIGURATION_DOC = "To use the shared SmppConfiguration as configuration.";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_ENQUIRE_LINK_TIMER_CONF = "camel.component.smpp.enquireLinkTimer";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_ENQUIRE_LINK_TIMER_DOC = "Defines the interval in milliseconds between the confidence checks. The confidence check is used to test the communication path between an ESME and an SMSC.";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_ENQUIRE_LINK_TIMER_DEFAULT = "5000";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_SESSION_STATE_LISTENER_CONF = "camel.component.smpp.sessionStateListener";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_SESSION_STATE_LISTENER_DOC = "You can refer to a org.jsmpp.session.SessionStateListener in the Registry to receive callbacks when the session state changed.";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_SESSION_STATE_LISTENER_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_TRANSACTION_TIMER_CONF = "camel.component.smpp.transactionTimer";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_TRANSACTION_TIMER_DOC = "Defines the maximum period of inactivity allowed after a transaction, after which an SMPP entity may assume that the session is no longer active. This timer may be active on either communicating SMPP entity (i.e. SMSC or ESME).";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_TRANSACTION_TIMER_DEFAULT = "10000";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_ALPHABET_CONF = "camel.component.smpp.alphabet";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_ALPHABET_DOC = "Defines encoding of data according the SMPP 3.4 specification, section 5.2.19. 0: SMSC Default Alphabet 4: 8 bit Alphabet 8: UCS2 Alphabet One of: [0] [4] [8]";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_ALPHABET_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_DATA_CODING_CONF = "camel.component.smpp.dataCoding";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_DATA_CODING_DOC = "Defines the data coding according the SMPP 3.4 specification, section 5.2.19. Example data encodings are: 0: SMSC Default Alphabet 3: Latin 1 (ISO-8859-1) 4: Octet unspecified (8-bit binary) 8: UCS2 (ISO/IEC-10646) 13: Extended Kanji JIS(X 0212-1990)";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_DATA_CODING_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_ENCODING_CONF = "camel.component.smpp.encoding";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_ENCODING_DOC = "Defines the encoding scheme of the short message user data. Only for SubmitSm, ReplaceSm and SubmitMulti.";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_ENCODING_DEFAULT = "ISO-8859-1";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_HOST_CONF = "camel.component.smpp.httpProxyHost";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_HOST_DOC = "If you need to tunnel SMPP through a HTTP proxy, set this attribute to the hostname or ip address of your HTTP proxy.";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_HOST_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_PASSWORD_CONF = "camel.component.smpp.httpProxyPassword";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_PASSWORD_DOC = "If your HTTP proxy requires basic authentication, set this attribute to the password required for your HTTP proxy.";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_PORT_CONF = "camel.component.smpp.httpProxyPort";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_PORT_DOC = "If you need to tunnel SMPP through a HTTP proxy, set this attribute to the port of your HTTP proxy.";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_PORT_DEFAULT = "3128";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_USERNAME_CONF = "camel.component.smpp.httpProxyUsername";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_USERNAME_DOC = "If your HTTP proxy requires basic authentication, set this attribute to the username required for your HTTP proxy.";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_USERNAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_PROXY_HEADERS_CONF = "camel.component.smpp.proxyHeaders";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_PROXY_HEADERS_DOC = "These headers will be passed to the proxy server while establishing the connection.";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_PROXY_HEADERS_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_PASSWORD_CONF = "camel.component.smpp.password";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_PASSWORD_DOC = "The password for connecting to SMSC server.";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_SYSTEM_ID_CONF = "camel.component.smpp.systemId";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_SYSTEM_ID_DOC = "The system id (username) for connecting to SMSC server.";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_SYSTEM_ID_DEFAULT = "smppclient";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_USING_SSLCONF = "camel.component.smpp.usingSSL";
    public static final String CAMEL_SOURCE_SMPP_COMPONENT_USING_SSLDOC = "Whether using SSL with the smpps protocol";
    public static final Boolean CAMEL_SOURCE_SMPP_COMPONENT_USING_SSLDEFAULT = false;

    public CamelSmppSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelSmppSourceConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_SMPP_PATH_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_PATH_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_PATH_HOST_DOC);
        conf.define(CAMEL_SOURCE_SMPP_PATH_PORT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_PATH_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_PATH_PORT_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_INITIAL_RECONNECT_DELAY_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_SMPP_ENDPOINT_INITIAL_RECONNECT_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_INITIAL_RECONNECT_DELAY_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_MAX_RECONNECT_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_SMPP_ENDPOINT_MAX_RECONNECT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_MAX_RECONNECT_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_RECONNECT_DELAY_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_SMPP_ENDPOINT_RECONNECT_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_RECONNECT_DELAY_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_SPLITTING_POLICY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_ENDPOINT_SPLITTING_POLICY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_SPLITTING_POLICY_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_SYSTEM_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_ENDPOINT_SYSTEM_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_SYSTEM_TYPE_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_ADDRESS_RANGE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_ENDPOINT_ADDRESS_RANGE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_ADDRESS_RANGE_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_SMPP_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_EXCEPTION_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_ENDPOINT_EXCEPTION_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_EXCEPTION_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_EXCHANGE_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_ENDPOINT_EXCHANGE_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_EXCHANGE_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_ENQUIRE_LINK_TIMER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_ENDPOINT_ENQUIRE_LINK_TIMER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_ENQUIRE_LINK_TIMER_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_SESSION_STATE_LISTENER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_ENDPOINT_SESSION_STATE_LISTENER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_SESSION_STATE_LISTENER_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_SMPP_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_TRANSACTION_TIMER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_ENDPOINT_TRANSACTION_TIMER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_TRANSACTION_TIMER_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_ALPHABET_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_ENDPOINT_ALPHABET_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_ALPHABET_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_DATA_CODING_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_ENDPOINT_DATA_CODING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_DATA_CODING_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_ENCODING_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_ENDPOINT_ENCODING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_ENCODING_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_HOST_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_PASSWORD_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_PASSWORD_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_PORT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_PORT_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_USERNAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_USERNAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_HTTP_PROXY_USERNAME_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_PROXY_HEADERS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_ENDPOINT_PROXY_HEADERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_PROXY_HEADERS_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_PASSWORD_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_ENDPOINT_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_PASSWORD_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_SYSTEM_ID_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_ENDPOINT_SYSTEM_ID_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_SYSTEM_ID_DOC);
        conf.define(CAMEL_SOURCE_SMPP_ENDPOINT_USING_SSLCONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_SMPP_ENDPOINT_USING_SSLDEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_ENDPOINT_USING_SSLDOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_INITIAL_RECONNECT_DELAY_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_SMPP_COMPONENT_INITIAL_RECONNECT_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_INITIAL_RECONNECT_DELAY_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_MAX_RECONNECT_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_SMPP_COMPONENT_MAX_RECONNECT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_MAX_RECONNECT_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_RECONNECT_DELAY_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_SMPP_COMPONENT_RECONNECT_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_RECONNECT_DELAY_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_SPLITTING_POLICY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_COMPONENT_SPLITTING_POLICY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_SPLITTING_POLICY_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_SYSTEM_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_COMPONENT_SYSTEM_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_SYSTEM_TYPE_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_ADDRESS_RANGE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_COMPONENT_ADDRESS_RANGE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_ADDRESS_RANGE_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_SMPP_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_SMPP_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_AUTOWIRED_ENABLED_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_COMPONENT_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_CONFIGURATION_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_ENQUIRE_LINK_TIMER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_COMPONENT_ENQUIRE_LINK_TIMER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_ENQUIRE_LINK_TIMER_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_SESSION_STATE_LISTENER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_COMPONENT_SESSION_STATE_LISTENER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_SESSION_STATE_LISTENER_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_TRANSACTION_TIMER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_COMPONENT_TRANSACTION_TIMER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_TRANSACTION_TIMER_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_ALPHABET_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_COMPONENT_ALPHABET_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_ALPHABET_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_DATA_CODING_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_COMPONENT_DATA_CODING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_DATA_CODING_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_ENCODING_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_COMPONENT_ENCODING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_ENCODING_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_HOST_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_PASSWORD_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_PASSWORD_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_PORT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_PORT_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_USERNAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_USERNAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_HTTP_PROXY_USERNAME_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_PROXY_HEADERS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_COMPONENT_PROXY_HEADERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_PROXY_HEADERS_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_PASSWORD_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_COMPONENT_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_PASSWORD_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_SYSTEM_ID_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_SMPP_COMPONENT_SYSTEM_ID_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_SYSTEM_ID_DOC);
        conf.define(CAMEL_SOURCE_SMPP_COMPONENT_USING_SSLCONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_SMPP_COMPONENT_USING_SSLDEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_SMPP_COMPONENT_USING_SSLDOC);
        return conf;
    }
}