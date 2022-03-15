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
package org.apache.camel.kafkaconnector.mongodbsource;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelMongodbsourceSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_HOSTS_CONF = "camel.kamelet.mongodb-source.hosts";
    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_HOSTS_DOC = "Comma separated list of MongoDB Host Addresses in host:port format.";
    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_HOSTS_DEFAULT = null;
    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_COLLECTION_CONF = "camel.kamelet.mongodb-source.collection";
    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_COLLECTION_DOC = "Sets the name of the MongoDB collection to bind to this endpoint.";
    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_COLLECTION_DEFAULT = null;
    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_PASSWORD_CONF = "camel.kamelet.mongodb-source.password";
    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_PASSWORD_DOC = "User password for accessing MongoDB.";
    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_USERNAME_CONF = "camel.kamelet.mongodb-source.username";
    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_USERNAME_DOC = "Username for accessing MongoDB. The username must be present in the MongoDB's authentication database (authenticationDatabase). By default, the MongoDB authenticationDatabase is 'admin'.";
    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_USERNAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_DATABASE_CONF = "camel.kamelet.mongodb-source.database";
    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_DATABASE_DOC = "Sets the name of the MongoDB database to target.";
    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_DATABASE_DEFAULT = null;
    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_PERSISTENT_TAIL_TRACKING_CONF = "camel.kamelet.mongodb-source.persistentTailTracking";
    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_PERSISTENT_TAIL_TRACKING_DOC = "Enable persistent tail tracking, which is a mechanism to keep track of the last consumed message across system restarts. The next time the system is up, the endpoint will recover the cursor from the point where it last stopped slurping records.";
    public static final Boolean CAMEL_SOURCE_MONGODBSOURCE_KAMELET_PERSISTENT_TAIL_TRACKING_DEFAULT = false;
    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_TAIL_TRACK_INCREASING_FIELD_CONF = "camel.kamelet.mongodb-source.tailTrackIncreasingField";
    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_TAIL_TRACK_INCREASING_FIELD_DOC = "Correlation field in the incoming record which is of increasing nature and will be used to position the tailing cursor every time it is generated.";
    public static final String CAMEL_SOURCE_MONGODBSOURCE_KAMELET_TAIL_TRACK_INCREASING_FIELD_DEFAULT = null;

    public CamelMongodbsourceSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelMongodbsourceSourceConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_MONGODBSOURCE_KAMELET_HOSTS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MONGODBSOURCE_KAMELET_HOSTS_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_MONGODBSOURCE_KAMELET_HOSTS_DOC);
        conf.define(CAMEL_SOURCE_MONGODBSOURCE_KAMELET_COLLECTION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MONGODBSOURCE_KAMELET_COLLECTION_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_MONGODBSOURCE_KAMELET_COLLECTION_DOC);
        conf.define(CAMEL_SOURCE_MONGODBSOURCE_KAMELET_PASSWORD_CONF, ConfigDef.Type.PASSWORD, CAMEL_SOURCE_MONGODBSOURCE_KAMELET_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MONGODBSOURCE_KAMELET_PASSWORD_DOC);
        conf.define(CAMEL_SOURCE_MONGODBSOURCE_KAMELET_USERNAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MONGODBSOURCE_KAMELET_USERNAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MONGODBSOURCE_KAMELET_USERNAME_DOC);
        conf.define(CAMEL_SOURCE_MONGODBSOURCE_KAMELET_DATABASE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MONGODBSOURCE_KAMELET_DATABASE_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_MONGODBSOURCE_KAMELET_DATABASE_DOC);
        conf.define(CAMEL_SOURCE_MONGODBSOURCE_KAMELET_PERSISTENT_TAIL_TRACKING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_MONGODBSOURCE_KAMELET_PERSISTENT_TAIL_TRACKING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MONGODBSOURCE_KAMELET_PERSISTENT_TAIL_TRACKING_DOC);
        conf.define(CAMEL_SOURCE_MONGODBSOURCE_KAMELET_TAIL_TRACK_INCREASING_FIELD_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_MONGODBSOURCE_KAMELET_TAIL_TRACK_INCREASING_FIELD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_MONGODBSOURCE_KAMELET_TAIL_TRACK_INCREASING_FIELD_DOC);
        return conf;
    }
}