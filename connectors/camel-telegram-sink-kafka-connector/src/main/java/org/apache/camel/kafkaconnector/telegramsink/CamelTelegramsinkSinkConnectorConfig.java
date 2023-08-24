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
 */package org.apache.camel.kafkaconnector.telegramsink;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelTelegramsinkSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_TELEGRAMSINK_KAMELET_AUTHORIZATION_TOKEN_CONF = "camel.kamelet.telegram-sink.authorizationToken";
    public static final String CAMEL_SINK_TELEGRAMSINK_KAMELET_AUTHORIZATION_TOKEN_DOC = "The token to access your bot on Telegram. You you can obtain it from the Telegram @botfather.";
    public static final String CAMEL_SINK_TELEGRAMSINK_KAMELET_AUTHORIZATION_TOKEN_DEFAULT = null;
    public static final String CAMEL_SINK_TELEGRAMSINK_KAMELET_CHAT_ID_CONF = "camel.kamelet.telegram-sink.chatId";
    public static final String CAMEL_SINK_TELEGRAMSINK_KAMELET_CHAT_ID_DOC = "The Chat ID to where you want to send messages by default.   Whilst the Chat ID is not a required Configuration Option it must be provided for every message; either as a Configuration Option or a `chat-id` / `ce-chatid` header.";
    public static final String CAMEL_SINK_TELEGRAMSINK_KAMELET_CHAT_ID_DEFAULT = null;

    public CamelTelegramsinkSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelTelegramsinkSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_TELEGRAMSINK_KAMELET_AUTHORIZATION_TOKEN_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_TELEGRAMSINK_KAMELET_AUTHORIZATION_TOKEN_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_TELEGRAMSINK_KAMELET_AUTHORIZATION_TOKEN_DOC);
        conf.define(CAMEL_SINK_TELEGRAMSINK_KAMELET_CHAT_ID_CONF, ConfigDef.Type.STRING, CAMEL_SINK_TELEGRAMSINK_KAMELET_CHAT_ID_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_TELEGRAMSINK_KAMELET_CHAT_ID_DOC);
        return conf;
    }
}