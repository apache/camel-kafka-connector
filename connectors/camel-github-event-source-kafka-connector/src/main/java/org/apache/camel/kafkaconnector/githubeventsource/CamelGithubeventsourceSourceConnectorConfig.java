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
package org.apache.camel.kafkaconnector.githubeventsource;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelGithubeventsourceSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_GITHUBEVENTSOURCE_KAMELET_REPO_NAME_CONF = "camel.kamelet.github-event-source.repoName";
    public static final String CAMEL_SOURCE_GITHUBEVENTSOURCE_KAMELET_REPO_NAME_DOC = "The Github Repository name";
    public static final String CAMEL_SOURCE_GITHUBEVENTSOURCE_KAMELET_REPO_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_GITHUBEVENTSOURCE_KAMELET_REPO_OWNER_CONF = "camel.kamelet.github-event-source.repoOwner";
    public static final String CAMEL_SOURCE_GITHUBEVENTSOURCE_KAMELET_REPO_OWNER_DOC = "The repository owner";
    public static final String CAMEL_SOURCE_GITHUBEVENTSOURCE_KAMELET_REPO_OWNER_DEFAULT = null;
    public static final String CAMEL_SOURCE_GITHUBEVENTSOURCE_KAMELET_OAUTH_TOKEN_CONF = "camel.kamelet.github-event-source.oauthToken";
    public static final String CAMEL_SOURCE_GITHUBEVENTSOURCE_KAMELET_OAUTH_TOKEN_DOC = "Oauth token";
    public static final String CAMEL_SOURCE_GITHUBEVENTSOURCE_KAMELET_OAUTH_TOKEN_DEFAULT = null;

    public CamelGithubeventsourceSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelGithubeventsourceSourceConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_GITHUBEVENTSOURCE_KAMELET_REPO_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_GITHUBEVENTSOURCE_KAMELET_REPO_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_GITHUBEVENTSOURCE_KAMELET_REPO_NAME_DOC);
        conf.define(CAMEL_SOURCE_GITHUBEVENTSOURCE_KAMELET_REPO_OWNER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_GITHUBEVENTSOURCE_KAMELET_REPO_OWNER_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_GITHUBEVENTSOURCE_KAMELET_REPO_OWNER_DOC);
        conf.define(CAMEL_SOURCE_GITHUBEVENTSOURCE_KAMELET_OAUTH_TOKEN_CONF, ConfigDef.Type.PASSWORD, CAMEL_SOURCE_GITHUBEVENTSOURCE_KAMELET_OAUTH_TOKEN_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_GITHUBEVENTSOURCE_KAMELET_OAUTH_TOKEN_DOC);
        return conf;
    }
}