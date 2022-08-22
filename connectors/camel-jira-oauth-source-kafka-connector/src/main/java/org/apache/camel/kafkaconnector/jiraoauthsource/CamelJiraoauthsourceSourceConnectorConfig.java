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
package org.apache.camel.kafkaconnector.jiraoauthsource;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelJiraoauthsourceSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_JIRA_URL_CONF = "camel.kamelet.jira-oauth-source.jiraUrl";
    public static final String CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_JIRA_URL_DOC = "The URL of your instance of Jira Example: http://my_jira.com:8081";
    public static final String CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_JIRA_URL_DEFAULT = null;
    public static final String CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_ACCESS_TOKEN_CONF = "camel.kamelet.jira-oauth-source.accessToken";
    public static final String CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_ACCESS_TOKEN_DOC = "The access token generated by the Jira server.";
    public static final String CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_ACCESS_TOKEN_DEFAULT = null;
    public static final String CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_VERIFICATION_CODE_CONF = "camel.kamelet.jira-oauth-source.verificationCode";
    public static final String CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_VERIFICATION_CODE_DOC = "The verification code from Jira generated in the first step of the authorization proccess.";
    public static final String CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_VERIFICATION_CODE_DEFAULT = null;
    public static final String CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_CONSUMER_KEY_CONF = "camel.kamelet.jira-oauth-source.consumerKey";
    public static final String CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_CONSUMER_KEY_DOC = "The consumer key from Jira settings.";
    public static final String CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_CONSUMER_KEY_DEFAULT = null;
    public static final String CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_PRIVATE_KEY_CONF = "camel.kamelet.jira-oauth-source.privateKey";
    public static final String CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_PRIVATE_KEY_DOC = "The private key generated by the client to encrypt the conversation to the server.";
    public static final String CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_PRIVATE_KEY_DEFAULT = null;
    public static final String CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_JQL_CONF = "camel.kamelet.jira-oauth-source.jql";
    public static final String CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_JQL_DOC = "A query to filter issues Example: project=MyProject";
    public static final String CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_JQL_DEFAULT = null;

    public CamelJiraoauthsourceSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelJiraoauthsourceSourceConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_JIRA_URL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_JIRA_URL_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_JIRA_URL_DOC);
        conf.define(CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_ACCESS_TOKEN_CONF, ConfigDef.Type.PASSWORD, CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_ACCESS_TOKEN_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_ACCESS_TOKEN_DOC);
        conf.define(CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_VERIFICATION_CODE_CONF, ConfigDef.Type.PASSWORD, CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_VERIFICATION_CODE_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_VERIFICATION_CODE_DOC);
        conf.define(CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_CONSUMER_KEY_CONF, ConfigDef.Type.PASSWORD, CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_CONSUMER_KEY_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_CONSUMER_KEY_DOC);
        conf.define(CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_PRIVATE_KEY_CONF, ConfigDef.Type.PASSWORD, CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_PRIVATE_KEY_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_PRIVATE_KEY_DOC);
        conf.define(CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_JQL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_JQL_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_JIRAOAUTHSOURCE_KAMELET_JQL_DOC);
        return conf;
    }
}