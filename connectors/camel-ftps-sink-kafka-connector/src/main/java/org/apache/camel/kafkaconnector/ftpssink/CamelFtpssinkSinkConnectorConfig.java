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
package org.apache.camel.kafkaconnector.ftpssink;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelFtpssinkSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_FTPSSINK_KAMELET_CONNECTION_HOST_CONF = "camel.kamelet.ftps-sink.connectionHost";
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_CONNECTION_HOST_DOC = "The hostname of the FTP server.";
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_CONNECTION_HOST_DEFAULT = null;
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_CONNECTION_PORT_CONF = "camel.kamelet.ftps-sink.connectionPort";
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_CONNECTION_PORT_DOC = "The port of the FTP server.";
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_CONNECTION_PORT_DEFAULT = "21";
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_USERNAME_CONF = "camel.kamelet.ftps-sink.username";
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_USERNAME_DOC = "The username to access the FTP server.";
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_USERNAME_DEFAULT = null;
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_PASSWORD_CONF = "camel.kamelet.ftps-sink.password";
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_PASSWORD_DOC = "The password to access the FTP server.";
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_DIRECTORY_NAME_CONF = "camel.kamelet.ftps-sink.directoryName";
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_DIRECTORY_NAME_DOC = "The starting directory.";
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_DIRECTORY_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_PASSIVE_MODE_CONF = "camel.kamelet.ftps-sink.passiveMode";
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_PASSIVE_MODE_DOC = "Set the passive mode connection.";
    public static final Boolean CAMEL_SINK_FTPSSINK_KAMELET_PASSIVE_MODE_DEFAULT = false;
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_FILE_EXIST_CONF = "camel.kamelet.ftps-sink.fileExist";
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_FILE_EXIST_DOC = "Specifies how the Kamelet behaves if the file already exists. Possible values are Override, Append, Fail, or Ignore.";
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_FILE_EXIST_DEFAULT = "Override";
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_BINARY_CONF = "camel.kamelet.ftps-sink.binary";
    public static final String CAMEL_SINK_FTPSSINK_KAMELET_BINARY_DOC = "Specifies the file transfer mode, BINARY or ASCII. Default is ASCII (false).";
    public static final Boolean CAMEL_SINK_FTPSSINK_KAMELET_BINARY_DEFAULT = false;

    public CamelFtpssinkSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelFtpssinkSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_FTPSSINK_KAMELET_CONNECTION_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_FTPSSINK_KAMELET_CONNECTION_HOST_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_FTPSSINK_KAMELET_CONNECTION_HOST_DOC);
        conf.define(CAMEL_SINK_FTPSSINK_KAMELET_CONNECTION_PORT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_FTPSSINK_KAMELET_CONNECTION_PORT_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_FTPSSINK_KAMELET_CONNECTION_PORT_DOC);
        conf.define(CAMEL_SINK_FTPSSINK_KAMELET_USERNAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_FTPSSINK_KAMELET_USERNAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_FTPSSINK_KAMELET_USERNAME_DOC);
        conf.define(CAMEL_SINK_FTPSSINK_KAMELET_PASSWORD_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_FTPSSINK_KAMELET_PASSWORD_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_FTPSSINK_KAMELET_PASSWORD_DOC);
        conf.define(CAMEL_SINK_FTPSSINK_KAMELET_DIRECTORY_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_FTPSSINK_KAMELET_DIRECTORY_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_FTPSSINK_KAMELET_DIRECTORY_NAME_DOC);
        conf.define(CAMEL_SINK_FTPSSINK_KAMELET_PASSIVE_MODE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_FTPSSINK_KAMELET_PASSIVE_MODE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_FTPSSINK_KAMELET_PASSIVE_MODE_DOC);
        conf.define(CAMEL_SINK_FTPSSINK_KAMELET_FILE_EXIST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_FTPSSINK_KAMELET_FILE_EXIST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_FTPSSINK_KAMELET_FILE_EXIST_DOC);
        conf.define(CAMEL_SINK_FTPSSINK_KAMELET_BINARY_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_FTPSSINK_KAMELET_BINARY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_FTPSSINK_KAMELET_BINARY_DOC);
        return conf;
    }
}