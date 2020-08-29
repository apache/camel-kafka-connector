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
package org.apache.camel.kafkaconnector.jira;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelJiraSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_JIRA_PATH_TYPE_CONF = "camel.sink.path.type";
    public static final String CAMEL_SINK_JIRA_PATH_TYPE_DOC = "Operation to perform. Consumers: NewIssues, NewComments. Producers: AddIssue, AttachFile, DeleteIssue, TransitionIssue, UpdateIssue, Watchers. See this class javadoc description for more information. One of: [ADDCOMMENT] [ADDISSUE] [ATTACH] [DELETEISSUE] [NEWISSUES] [NEWCOMMENTS] [WATCHUPDATES] [UPDATEISSUE] [TRANSITIONISSUE] [WATCHERS] [ADDISSUELINK] [ADDWORKLOG] [FETCHISSUE] [FETCHCOMMENTS]";
    public static final String CAMEL_SINK_JIRA_PATH_TYPE_DEFAULT = null;
    public static final String CAMEL_SINK_JIRA_ENDPOINT_DELAY_CONF = "camel.sink.endpoint.delay";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_DELAY_DOC = "Time in milliseconds to elapse for the next poll.";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_DELAY_DEFAULT = "6000";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_JIRA_URL_CONF = "camel.sink.endpoint.jiraUrl";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_JIRA_URL_DOC = "The Jira server url, example: http://my_jira.com:8081";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_JIRA_URL_DEFAULT = null;
    public static final String CAMEL_SINK_JIRA_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_JIRA_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_JIRA_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SINK_JIRA_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_JIRA_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SINK_JIRA_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_JIRA_ENDPOINT_ACCESS_TOKEN_CONF = "camel.sink.endpoint.accessToken";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_ACCESS_TOKEN_DOC = "(OAuth only) The access token generated by the Jira server.";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_ACCESS_TOKEN_DEFAULT = null;
    public static final String CAMEL_SINK_JIRA_ENDPOINT_CONSUMER_KEY_CONF = "camel.sink.endpoint.consumerKey";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_CONSUMER_KEY_DOC = "(OAuth only) The consumer key from Jira settings.";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_CONSUMER_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_JIRA_ENDPOINT_PASSWORD_CONF = "camel.sink.endpoint.password";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_PASSWORD_DOC = "(Basic authentication only) The password to authenticate to the Jira server. Use only if username basic authentication is used.";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SINK_JIRA_ENDPOINT_PRIVATE_KEY_CONF = "camel.sink.endpoint.privateKey";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_PRIVATE_KEY_DOC = "(OAuth only) The private key generated by the client to encrypt the conversation to the server.";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_PRIVATE_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_JIRA_ENDPOINT_USERNAME_CONF = "camel.sink.endpoint.username";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_USERNAME_DOC = "(Basic authentication only) The username to authenticate to the Jira server. Use only if OAuth is not enabled on the Jira server. Do not set the username and OAuth token parameter, if they are both set, the username basic authentication takes precedence.";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_USERNAME_DEFAULT = null;
    public static final String CAMEL_SINK_JIRA_ENDPOINT_VERIFICATION_CODE_CONF = "camel.sink.endpoint.verificationCode";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_VERIFICATION_CODE_DOC = "(OAuth only) The verification code from Jira generated in the first step of the authorization proccess.";
    public static final String CAMEL_SINK_JIRA_ENDPOINT_VERIFICATION_CODE_DEFAULT = null;
    public static final String CAMEL_SINK_JIRA_COMPONENT_DELAY_CONF = "camel.component.jira.delay";
    public static final String CAMEL_SINK_JIRA_COMPONENT_DELAY_DOC = "Time in milliseconds to elapse for the next poll.";
    public static final String CAMEL_SINK_JIRA_COMPONENT_DELAY_DEFAULT = "6000";
    public static final String CAMEL_SINK_JIRA_COMPONENT_JIRA_URL_CONF = "camel.component.jira.jiraUrl";
    public static final String CAMEL_SINK_JIRA_COMPONENT_JIRA_URL_DOC = "The Jira server url, example: http://my_jira.com:8081";
    public static final String CAMEL_SINK_JIRA_COMPONENT_JIRA_URL_DEFAULT = null;
    public static final String CAMEL_SINK_JIRA_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.jira.lazyStartProducer";
    public static final String CAMEL_SINK_JIRA_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_JIRA_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_JIRA_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.jira.basicPropertyBinding";
    public static final String CAMEL_SINK_JIRA_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SINK_JIRA_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_JIRA_COMPONENT_CONFIGURATION_CONF = "camel.component.jira.configuration";
    public static final String CAMEL_SINK_JIRA_COMPONENT_CONFIGURATION_DOC = "To use a shared base jira configuration.";
    public static final String CAMEL_SINK_JIRA_COMPONENT_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SINK_JIRA_COMPONENT_ACCESS_TOKEN_CONF = "camel.component.jira.accessToken";
    public static final String CAMEL_SINK_JIRA_COMPONENT_ACCESS_TOKEN_DOC = "(OAuth only) The access token generated by the Jira server.";
    public static final String CAMEL_SINK_JIRA_COMPONENT_ACCESS_TOKEN_DEFAULT = null;
    public static final String CAMEL_SINK_JIRA_COMPONENT_CONSUMER_KEY_CONF = "camel.component.jira.consumerKey";
    public static final String CAMEL_SINK_JIRA_COMPONENT_CONSUMER_KEY_DOC = "(OAuth only) The consumer key from Jira settings.";
    public static final String CAMEL_SINK_JIRA_COMPONENT_CONSUMER_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_JIRA_COMPONENT_PASSWORD_CONF = "camel.component.jira.password";
    public static final String CAMEL_SINK_JIRA_COMPONENT_PASSWORD_DOC = "(Basic authentication only) The password to authenticate to the Jira server. Use only if username basic authentication is used.";
    public static final String CAMEL_SINK_JIRA_COMPONENT_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SINK_JIRA_COMPONENT_PRIVATE_KEY_CONF = "camel.component.jira.privateKey";
    public static final String CAMEL_SINK_JIRA_COMPONENT_PRIVATE_KEY_DOC = "(OAuth only) The private key generated by the client to encrypt the conversation to the server.";
    public static final String CAMEL_SINK_JIRA_COMPONENT_PRIVATE_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_JIRA_COMPONENT_USERNAME_CONF = "camel.component.jira.username";
    public static final String CAMEL_SINK_JIRA_COMPONENT_USERNAME_DOC = "(Basic authentication only) The username to authenticate to the Jira server. Use only if OAuth is not enabled on the Jira server. Do not set the username and OAuth token parameter, if they are both set, the username basic authentication takes precedence.";
    public static final String CAMEL_SINK_JIRA_COMPONENT_USERNAME_DEFAULT = null;
    public static final String CAMEL_SINK_JIRA_COMPONENT_VERIFICATION_CODE_CONF = "camel.component.jira.verificationCode";
    public static final String CAMEL_SINK_JIRA_COMPONENT_VERIFICATION_CODE_DOC = "(OAuth only) The verification code from Jira generated in the first step of the authorization proccess.";
    public static final String CAMEL_SINK_JIRA_COMPONENT_VERIFICATION_CODE_DEFAULT = null;

    public CamelJiraSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelJiraSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_JIRA_PATH_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JIRA_PATH_TYPE_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_JIRA_PATH_TYPE_DOC);
        conf.define(CAMEL_SINK_JIRA_ENDPOINT_DELAY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JIRA_ENDPOINT_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_ENDPOINT_DELAY_DOC);
        conf.define(CAMEL_SINK_JIRA_ENDPOINT_JIRA_URL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JIRA_ENDPOINT_JIRA_URL_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_JIRA_ENDPOINT_JIRA_URL_DOC);
        conf.define(CAMEL_SINK_JIRA_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JIRA_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_JIRA_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JIRA_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_JIRA_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JIRA_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_JIRA_ENDPOINT_ACCESS_TOKEN_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JIRA_ENDPOINT_ACCESS_TOKEN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_ENDPOINT_ACCESS_TOKEN_DOC);
        conf.define(CAMEL_SINK_JIRA_ENDPOINT_CONSUMER_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JIRA_ENDPOINT_CONSUMER_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_ENDPOINT_CONSUMER_KEY_DOC);
        conf.define(CAMEL_SINK_JIRA_ENDPOINT_PASSWORD_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JIRA_ENDPOINT_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_ENDPOINT_PASSWORD_DOC);
        conf.define(CAMEL_SINK_JIRA_ENDPOINT_PRIVATE_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JIRA_ENDPOINT_PRIVATE_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_ENDPOINT_PRIVATE_KEY_DOC);
        conf.define(CAMEL_SINK_JIRA_ENDPOINT_USERNAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JIRA_ENDPOINT_USERNAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_ENDPOINT_USERNAME_DOC);
        conf.define(CAMEL_SINK_JIRA_ENDPOINT_VERIFICATION_CODE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JIRA_ENDPOINT_VERIFICATION_CODE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_ENDPOINT_VERIFICATION_CODE_DOC);
        conf.define(CAMEL_SINK_JIRA_COMPONENT_DELAY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JIRA_COMPONENT_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_COMPONENT_DELAY_DOC);
        conf.define(CAMEL_SINK_JIRA_COMPONENT_JIRA_URL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JIRA_COMPONENT_JIRA_URL_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_JIRA_COMPONENT_JIRA_URL_DOC);
        conf.define(CAMEL_SINK_JIRA_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JIRA_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_JIRA_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JIRA_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_JIRA_COMPONENT_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JIRA_COMPONENT_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_COMPONENT_CONFIGURATION_DOC);
        conf.define(CAMEL_SINK_JIRA_COMPONENT_ACCESS_TOKEN_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JIRA_COMPONENT_ACCESS_TOKEN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_COMPONENT_ACCESS_TOKEN_DOC);
        conf.define(CAMEL_SINK_JIRA_COMPONENT_CONSUMER_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JIRA_COMPONENT_CONSUMER_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_COMPONENT_CONSUMER_KEY_DOC);
        conf.define(CAMEL_SINK_JIRA_COMPONENT_PASSWORD_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JIRA_COMPONENT_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_COMPONENT_PASSWORD_DOC);
        conf.define(CAMEL_SINK_JIRA_COMPONENT_PRIVATE_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JIRA_COMPONENT_PRIVATE_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_COMPONENT_PRIVATE_KEY_DOC);
        conf.define(CAMEL_SINK_JIRA_COMPONENT_USERNAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JIRA_COMPONENT_USERNAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_COMPONENT_USERNAME_DOC);
        conf.define(CAMEL_SINK_JIRA_COMPONENT_VERIFICATION_CODE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JIRA_COMPONENT_VERIFICATION_CODE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JIRA_COMPONENT_VERIFICATION_CODE_DOC);
        return conf;
    }
}