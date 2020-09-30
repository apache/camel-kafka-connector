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
package org.apache.camel.kafkaconnector.irc;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelIrcSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_IRC_PATH_HOSTNAME_CONF = "camel.sink.path.hostname";
    public static final String CAMEL_SINK_IRC_PATH_HOSTNAME_DOC = "Hostname for the IRC chat server";
    public static final String CAMEL_SINK_IRC_PATH_HOSTNAME_DEFAULT = null;
    public static final String CAMEL_SINK_IRC_PATH_PORT_CONF = "camel.sink.path.port";
    public static final String CAMEL_SINK_IRC_PATH_PORT_DOC = "Port number for the IRC chat server. If no port is configured then a default port of either 6667, 6668 or 6669 is used.";
    public static final Integer CAMEL_SINK_IRC_PATH_PORT_DEFAULT = null;
    public static final String CAMEL_SINK_IRC_ENDPOINT_AUTO_REJOIN_CONF = "camel.sink.endpoint.autoRejoin";
    public static final String CAMEL_SINK_IRC_ENDPOINT_AUTO_REJOIN_DOC = "Whether to auto re-join when being kicked";
    public static final Boolean CAMEL_SINK_IRC_ENDPOINT_AUTO_REJOIN_DEFAULT = true;
    public static final String CAMEL_SINK_IRC_ENDPOINT_CHANNELS_CONF = "camel.sink.endpoint.channels";
    public static final String CAMEL_SINK_IRC_ENDPOINT_CHANNELS_DOC = "Comma separated list of IRC channels.";
    public static final String CAMEL_SINK_IRC_ENDPOINT_CHANNELS_DEFAULT = null;
    public static final String CAMEL_SINK_IRC_ENDPOINT_COMMAND_TIMEOUT_CONF = "camel.sink.endpoint.commandTimeout";
    public static final String CAMEL_SINK_IRC_ENDPOINT_COMMAND_TIMEOUT_DOC = "Delay in milliseconds before sending commands after the connection is established.";
    public static final Long CAMEL_SINK_IRC_ENDPOINT_COMMAND_TIMEOUT_DEFAULT = 5000L;
    public static final String CAMEL_SINK_IRC_ENDPOINT_KEYS_CONF = "camel.sink.endpoint.keys";
    public static final String CAMEL_SINK_IRC_ENDPOINT_KEYS_DOC = "Comma separated list of keys for channels.";
    public static final String CAMEL_SINK_IRC_ENDPOINT_KEYS_DEFAULT = null;
    public static final String CAMEL_SINK_IRC_ENDPOINT_NAMES_ON_JOIN_CONF = "camel.sink.endpoint.namesOnJoin";
    public static final String CAMEL_SINK_IRC_ENDPOINT_NAMES_ON_JOIN_DOC = "Sends NAMES command to channel after joining it. onReply has to be true in order to process the result which will have the header value irc.num = '353'.";
    public static final Boolean CAMEL_SINK_IRC_ENDPOINT_NAMES_ON_JOIN_DEFAULT = false;
    public static final String CAMEL_SINK_IRC_ENDPOINT_NICKNAME_CONF = "camel.sink.endpoint.nickname";
    public static final String CAMEL_SINK_IRC_ENDPOINT_NICKNAME_DOC = "The nickname used in chat.";
    public static final String CAMEL_SINK_IRC_ENDPOINT_NICKNAME_DEFAULT = null;
    public static final String CAMEL_SINK_IRC_ENDPOINT_PERSISTENT_CONF = "camel.sink.endpoint.persistent";
    public static final String CAMEL_SINK_IRC_ENDPOINT_PERSISTENT_DOC = "Use persistent messages.";
    public static final Boolean CAMEL_SINK_IRC_ENDPOINT_PERSISTENT_DEFAULT = true;
    public static final String CAMEL_SINK_IRC_ENDPOINT_REALNAME_CONF = "camel.sink.endpoint.realname";
    public static final String CAMEL_SINK_IRC_ENDPOINT_REALNAME_DOC = "The IRC user's actual name.";
    public static final String CAMEL_SINK_IRC_ENDPOINT_REALNAME_DEFAULT = null;
    public static final String CAMEL_SINK_IRC_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_IRC_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_IRC_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_IRC_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    public static final String CAMEL_SINK_IRC_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SINK_IRC_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_IRC_ENDPOINT_COLORS_CONF = "camel.sink.endpoint.colors";
    public static final String CAMEL_SINK_IRC_ENDPOINT_COLORS_DOC = "Whether or not the server supports color codes.";
    public static final Boolean CAMEL_SINK_IRC_ENDPOINT_COLORS_DEFAULT = true;
    public static final String CAMEL_SINK_IRC_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    public static final String CAMEL_SINK_IRC_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SINK_IRC_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_IRC_ENDPOINT_ON_JOIN_CONF = "camel.sink.endpoint.onJoin";
    public static final String CAMEL_SINK_IRC_ENDPOINT_ON_JOIN_DOC = "Handle user join events.";
    public static final Boolean CAMEL_SINK_IRC_ENDPOINT_ON_JOIN_DEFAULT = true;
    public static final String CAMEL_SINK_IRC_ENDPOINT_ON_KICK_CONF = "camel.sink.endpoint.onKick";
    public static final String CAMEL_SINK_IRC_ENDPOINT_ON_KICK_DOC = "Handle kick events.";
    public static final Boolean CAMEL_SINK_IRC_ENDPOINT_ON_KICK_DEFAULT = true;
    public static final String CAMEL_SINK_IRC_ENDPOINT_ON_MODE_CONF = "camel.sink.endpoint.onMode";
    public static final String CAMEL_SINK_IRC_ENDPOINT_ON_MODE_DOC = "Handle mode change events.";
    public static final Boolean CAMEL_SINK_IRC_ENDPOINT_ON_MODE_DEFAULT = true;
    public static final String CAMEL_SINK_IRC_ENDPOINT_ON_NICK_CONF = "camel.sink.endpoint.onNick";
    public static final String CAMEL_SINK_IRC_ENDPOINT_ON_NICK_DOC = "Handle nickname change events.";
    public static final Boolean CAMEL_SINK_IRC_ENDPOINT_ON_NICK_DEFAULT = true;
    public static final String CAMEL_SINK_IRC_ENDPOINT_ON_PART_CONF = "camel.sink.endpoint.onPart";
    public static final String CAMEL_SINK_IRC_ENDPOINT_ON_PART_DOC = "Handle user part events.";
    public static final Boolean CAMEL_SINK_IRC_ENDPOINT_ON_PART_DEFAULT = true;
    public static final String CAMEL_SINK_IRC_ENDPOINT_ON_PRIVMSG_CONF = "camel.sink.endpoint.onPrivmsg";
    public static final String CAMEL_SINK_IRC_ENDPOINT_ON_PRIVMSG_DOC = "Handle private message events.";
    public static final Boolean CAMEL_SINK_IRC_ENDPOINT_ON_PRIVMSG_DEFAULT = true;
    public static final String CAMEL_SINK_IRC_ENDPOINT_ON_QUIT_CONF = "camel.sink.endpoint.onQuit";
    public static final String CAMEL_SINK_IRC_ENDPOINT_ON_QUIT_DOC = "Handle user quit events.";
    public static final Boolean CAMEL_SINK_IRC_ENDPOINT_ON_QUIT_DEFAULT = true;
    public static final String CAMEL_SINK_IRC_ENDPOINT_ON_REPLY_CONF = "camel.sink.endpoint.onReply";
    public static final String CAMEL_SINK_IRC_ENDPOINT_ON_REPLY_DOC = "Whether or not to handle general responses to commands or informational messages.";
    public static final Boolean CAMEL_SINK_IRC_ENDPOINT_ON_REPLY_DEFAULT = false;
    public static final String CAMEL_SINK_IRC_ENDPOINT_ON_TOPIC_CONF = "camel.sink.endpoint.onTopic";
    public static final String CAMEL_SINK_IRC_ENDPOINT_ON_TOPIC_DOC = "Handle topic change events.";
    public static final Boolean CAMEL_SINK_IRC_ENDPOINT_ON_TOPIC_DEFAULT = true;
    public static final String CAMEL_SINK_IRC_ENDPOINT_NICK_PASSWORD_CONF = "camel.sink.endpoint.nickPassword";
    public static final String CAMEL_SINK_IRC_ENDPOINT_NICK_PASSWORD_DOC = "Your IRC server nickname password.";
    public static final String CAMEL_SINK_IRC_ENDPOINT_NICK_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SINK_IRC_ENDPOINT_PASSWORD_CONF = "camel.sink.endpoint.password";
    public static final String CAMEL_SINK_IRC_ENDPOINT_PASSWORD_DOC = "The IRC server password.";
    public static final String CAMEL_SINK_IRC_ENDPOINT_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SINK_IRC_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF = "camel.sink.endpoint.sslContextParameters";
    public static final String CAMEL_SINK_IRC_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC = "Used for configuring security using SSL. Reference to a org.apache.camel.support.jsse.SSLContextParameters in the Registry. This reference overrides any configured SSLContextParameters at the component level. Note that this setting overrides the trustManager option.";
    public static final String CAMEL_SINK_IRC_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT = null;
    public static final String CAMEL_SINK_IRC_ENDPOINT_TRUST_MANAGER_CONF = "camel.sink.endpoint.trustManager";
    public static final String CAMEL_SINK_IRC_ENDPOINT_TRUST_MANAGER_DOC = "The trust manager used to verify the SSL server's certificate.";
    public static final String CAMEL_SINK_IRC_ENDPOINT_TRUST_MANAGER_DEFAULT = null;
    public static final String CAMEL_SINK_IRC_ENDPOINT_USERNAME_CONF = "camel.sink.endpoint.username";
    public static final String CAMEL_SINK_IRC_ENDPOINT_USERNAME_DOC = "The IRC server user name.";
    public static final String CAMEL_SINK_IRC_ENDPOINT_USERNAME_DEFAULT = null;
    public static final String CAMEL_SINK_IRC_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.irc.lazyStartProducer";
    public static final String CAMEL_SINK_IRC_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_IRC_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_IRC_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.irc.basicPropertyBinding";
    public static final String CAMEL_SINK_IRC_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SINK_IRC_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_IRC_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF = "camel.component.irc.useGlobalSslContextParameters";
    public static final String CAMEL_SINK_IRC_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC = "Enable usage of global SSL context parameters.";
    public static final Boolean CAMEL_SINK_IRC_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT = false;

    public CamelIrcSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelIrcSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_IRC_PATH_HOSTNAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_IRC_PATH_HOSTNAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_IRC_PATH_HOSTNAME_DOC);
        conf.define(CAMEL_SINK_IRC_PATH_PORT_CONF, ConfigDef.Type.INT, CAMEL_SINK_IRC_PATH_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_PATH_PORT_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_AUTO_REJOIN_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IRC_ENDPOINT_AUTO_REJOIN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_AUTO_REJOIN_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_CHANNELS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_IRC_ENDPOINT_CHANNELS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_CHANNELS_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_COMMAND_TIMEOUT_CONF, ConfigDef.Type.LONG, CAMEL_SINK_IRC_ENDPOINT_COMMAND_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_COMMAND_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_KEYS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_IRC_ENDPOINT_KEYS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_KEYS_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_NAMES_ON_JOIN_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IRC_ENDPOINT_NAMES_ON_JOIN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_NAMES_ON_JOIN_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_NICKNAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_IRC_ENDPOINT_NICKNAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_NICKNAME_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_PERSISTENT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IRC_ENDPOINT_PERSISTENT_DEFAULT, ConfigDef.Importance.LOW, CAMEL_SINK_IRC_ENDPOINT_PERSISTENT_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_REALNAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_IRC_ENDPOINT_REALNAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_REALNAME_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IRC_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IRC_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_COLORS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IRC_ENDPOINT_COLORS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_COLORS_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IRC_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_ON_JOIN_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IRC_ENDPOINT_ON_JOIN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_ON_JOIN_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_ON_KICK_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IRC_ENDPOINT_ON_KICK_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_ON_KICK_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_ON_MODE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IRC_ENDPOINT_ON_MODE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_ON_MODE_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_ON_NICK_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IRC_ENDPOINT_ON_NICK_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_ON_NICK_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_ON_PART_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IRC_ENDPOINT_ON_PART_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_ON_PART_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_ON_PRIVMSG_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IRC_ENDPOINT_ON_PRIVMSG_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_ON_PRIVMSG_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_ON_QUIT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IRC_ENDPOINT_ON_QUIT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_ON_QUIT_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_ON_REPLY_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IRC_ENDPOINT_ON_REPLY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_ON_REPLY_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_ON_TOPIC_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IRC_ENDPOINT_ON_TOPIC_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_ON_TOPIC_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_NICK_PASSWORD_CONF, ConfigDef.Type.STRING, CAMEL_SINK_IRC_ENDPOINT_NICK_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_NICK_PASSWORD_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_PASSWORD_CONF, ConfigDef.Type.STRING, CAMEL_SINK_IRC_ENDPOINT_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_PASSWORD_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_IRC_ENDPOINT_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_SSL_CONTEXT_PARAMETERS_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_TRUST_MANAGER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_IRC_ENDPOINT_TRUST_MANAGER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_TRUST_MANAGER_DOC);
        conf.define(CAMEL_SINK_IRC_ENDPOINT_USERNAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_IRC_ENDPOINT_USERNAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_ENDPOINT_USERNAME_DOC);
        conf.define(CAMEL_SINK_IRC_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IRC_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_IRC_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IRC_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.LOW, CAMEL_SINK_IRC_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_IRC_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IRC_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IRC_COMPONENT_USE_GLOBAL_SSL_CONTEXT_PARAMETERS_DOC);
        return conf;
    }
}