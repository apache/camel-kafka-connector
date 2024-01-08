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
 */package org.apache.camel.kafkaconnector.jmsibmmqsource;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelJmsibmmqsourceSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_SERVER_NAME_CONF = "camel.kamelet.jms-ibm-mq-source.serverName";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_SERVER_NAME_DOC = "IBM MQ Server name or address";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_SERVER_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_SERVER_PORT_CONF = "camel.kamelet.jms-ibm-mq-source.serverPort";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_SERVER_PORT_DOC = "IBM MQ Server port";
    public static final Integer CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_SERVER_PORT_DEFAULT = 1414;
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_DESTINATION_TYPE_CONF = "camel.kamelet.jms-ibm-mq-source.destinationType";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_DESTINATION_TYPE_DOC = "The JMS destination type (queue or topic)";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_DESTINATION_TYPE_DEFAULT = "queue";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_DESTINATION_NAME_CONF = "camel.kamelet.jms-ibm-mq-source.destinationName";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_DESTINATION_NAME_DOC = "The destination name";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_DESTINATION_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_QUEUE_MANAGER_CONF = "camel.kamelet.jms-ibm-mq-source.queueManager";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_QUEUE_MANAGER_DOC = "Name of the IBM MQ Queue Manager";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_QUEUE_MANAGER_DEFAULT = null;
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_CHANNEL_CONF = "camel.kamelet.jms-ibm-mq-source.channel";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_CHANNEL_DOC = "Name of the IBM MQ Channel";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_CHANNEL_DEFAULT = null;
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_CLIENT_ID_CONF = "camel.kamelet.jms-ibm-mq-source.clientId";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_CLIENT_ID_DOC = "Name of the IBM MQ Client ID";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_CLIENT_ID_DEFAULT = null;
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_USERNAME_CONF = "camel.kamelet.jms-ibm-mq-source.username";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_USERNAME_DOC = "Username to authenticate to IBM MQ server";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_USERNAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_PASSWORD_CONF = "camel.kamelet.jms-ibm-mq-source.password";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_PASSWORD_DOC = "Password to authenticate to IBM MQ server";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_SSL_CIPHER_SUITE_CONF = "camel.kamelet.jms-ibm-mq-source.sslCipherSuite";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_SSL_CIPHER_SUITE_DOC = "CipherSuite to use for enabling TLS";
    public static final String CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_SSL_CIPHER_SUITE_DEFAULT = null;

    public CamelJmsibmmqsourceSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelJmsibmmqsourceSourceConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_SERVER_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_SERVER_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_SERVER_NAME_DOC);
        conf.define(CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_SERVER_PORT_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_SERVER_PORT_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_SERVER_PORT_DOC);
        conf.define(CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_DESTINATION_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_DESTINATION_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_DESTINATION_TYPE_DOC);
        conf.define(CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_DESTINATION_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_DESTINATION_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_DESTINATION_NAME_DOC);
        conf.define(CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_QUEUE_MANAGER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_QUEUE_MANAGER_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_QUEUE_MANAGER_DOC);
        conf.define(CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_CHANNEL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_CHANNEL_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_CHANNEL_DOC);
        conf.define(CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_CLIENT_ID_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_CLIENT_ID_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_CLIENT_ID_DOC);
        conf.define(CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_USERNAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_USERNAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_USERNAME_DOC);
        conf.define(CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_PASSWORD_CONF, ConfigDef.Type.PASSWORD, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_PASSWORD_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_PASSWORD_DOC);
        conf.define(CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_SSL_CIPHER_SUITE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_SSL_CIPHER_SUITE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_JMSIBMMQSOURCE_KAMELET_SSL_CIPHER_SUITE_DOC);
        return conf;
    }
}