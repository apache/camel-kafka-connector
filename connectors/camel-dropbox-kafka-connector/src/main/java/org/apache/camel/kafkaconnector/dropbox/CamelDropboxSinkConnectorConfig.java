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
package org.apache.camel.kafkaconnector.dropbox;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelDropboxSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_DROPBOX_PATH_OPERATION_CONF = "camel.sink.path.operation";
    private static final String CAMEL_SINK_DROPBOX_PATH_OPERATION_DOC = "The specific action (typically is a CRUD action) to perform on Dropbox remote folder. One of: [put] [del] [search] [get] [move]";
    private static final String CAMEL_SINK_DROPBOX_PATH_OPERATION_DEFAULT = null;
    public static final String CAMEL_SINK_DROPBOX_ENDPOINT_ACCESS_TOKEN_CONF = "camel.sink.endpoint.accessToken";
    private static final String CAMEL_SINK_DROPBOX_ENDPOINT_ACCESS_TOKEN_DOC = "The access token to make API requests for a specific Dropbox user";
    private static final String CAMEL_SINK_DROPBOX_ENDPOINT_ACCESS_TOKEN_DEFAULT = null;
    public static final String CAMEL_SINK_DROPBOX_ENDPOINT_CLIENT_CONF = "camel.sink.endpoint.client";
    private static final String CAMEL_SINK_DROPBOX_ENDPOINT_CLIENT_DOC = "To use an existing DbxClient instance as DropBox client.";
    private static final String CAMEL_SINK_DROPBOX_ENDPOINT_CLIENT_DEFAULT = null;
    public static final String CAMEL_SINK_DROPBOX_ENDPOINT_CLIENT_IDENTIFIER_CONF = "camel.sink.endpoint.clientIdentifier";
    private static final String CAMEL_SINK_DROPBOX_ENDPOINT_CLIENT_IDENTIFIER_DOC = "Name of the app registered to make API requests";
    private static final String CAMEL_SINK_DROPBOX_ENDPOINT_CLIENT_IDENTIFIER_DEFAULT = null;
    public static final String CAMEL_SINK_DROPBOX_ENDPOINT_LOCAL_PATH_CONF = "camel.sink.endpoint.localPath";
    private static final String CAMEL_SINK_DROPBOX_ENDPOINT_LOCAL_PATH_DOC = "Optional folder or file to upload on Dropbox from the local filesystem. If this option has not been configured then the message body is used as the content to upload.";
    private static final String CAMEL_SINK_DROPBOX_ENDPOINT_LOCAL_PATH_DEFAULT = null;
    public static final String CAMEL_SINK_DROPBOX_ENDPOINT_NEW_REMOTE_PATH_CONF = "camel.sink.endpoint.newRemotePath";
    private static final String CAMEL_SINK_DROPBOX_ENDPOINT_NEW_REMOTE_PATH_DOC = "Destination file or folder";
    private static final String CAMEL_SINK_DROPBOX_ENDPOINT_NEW_REMOTE_PATH_DEFAULT = null;
    public static final String CAMEL_SINK_DROPBOX_ENDPOINT_QUERY_CONF = "camel.sink.endpoint.query";
    private static final String CAMEL_SINK_DROPBOX_ENDPOINT_QUERY_DOC = "A space-separated list of sub-strings to search for. A file matches only if it contains all the sub-strings. If this option is not set, all files will be matched.";
    private static final String CAMEL_SINK_DROPBOX_ENDPOINT_QUERY_DEFAULT = null;
    public static final String CAMEL_SINK_DROPBOX_ENDPOINT_REMOTE_PATH_CONF = "camel.sink.endpoint.remotePath";
    private static final String CAMEL_SINK_DROPBOX_ENDPOINT_REMOTE_PATH_DOC = "Original file or folder to move";
    private static final String CAMEL_SINK_DROPBOX_ENDPOINT_REMOTE_PATH_DEFAULT = null;
    public static final String CAMEL_SINK_DROPBOX_ENDPOINT_UPLOAD_MODE_CONF = "camel.sink.endpoint.uploadMode";
    private static final String CAMEL_SINK_DROPBOX_ENDPOINT_UPLOAD_MODE_DOC = "Which mode to upload. in case of add the new file will be renamed if a file with the same name already exists on dropbox. in case of force if a file with the same name already exists on dropbox, this will be overwritten. One of: [add] [force]";
    private static final String CAMEL_SINK_DROPBOX_ENDPOINT_UPLOAD_MODE_DEFAULT = null;
    public static final String CAMEL_SINK_DROPBOX_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    private static final String CAMEL_SINK_DROPBOX_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_DROPBOX_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_DROPBOX_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    private static final String CAMEL_SINK_DROPBOX_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_DROPBOX_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_DROPBOX_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    private static final String CAMEL_SINK_DROPBOX_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SINK_DROPBOX_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_DROPBOX_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.dropbox.lazyStartProducer";
    private static final String CAMEL_SINK_DROPBOX_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_DROPBOX_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_DROPBOX_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.dropbox.basicPropertyBinding";
    private static final String CAMEL_SINK_DROPBOX_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_DROPBOX_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;

    public CamelDropboxSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelDropboxSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_DROPBOX_PATH_OPERATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_DROPBOX_PATH_OPERATION_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_DROPBOX_PATH_OPERATION_DOC);
        conf.define(CAMEL_SINK_DROPBOX_ENDPOINT_ACCESS_TOKEN_CONF, ConfigDef.Type.STRING, CAMEL_SINK_DROPBOX_ENDPOINT_ACCESS_TOKEN_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_DROPBOX_ENDPOINT_ACCESS_TOKEN_DOC);
        conf.define(CAMEL_SINK_DROPBOX_ENDPOINT_CLIENT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_DROPBOX_ENDPOINT_CLIENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DROPBOX_ENDPOINT_CLIENT_DOC);
        conf.define(CAMEL_SINK_DROPBOX_ENDPOINT_CLIENT_IDENTIFIER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_DROPBOX_ENDPOINT_CLIENT_IDENTIFIER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DROPBOX_ENDPOINT_CLIENT_IDENTIFIER_DOC);
        conf.define(CAMEL_SINK_DROPBOX_ENDPOINT_LOCAL_PATH_CONF, ConfigDef.Type.STRING, CAMEL_SINK_DROPBOX_ENDPOINT_LOCAL_PATH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DROPBOX_ENDPOINT_LOCAL_PATH_DOC);
        conf.define(CAMEL_SINK_DROPBOX_ENDPOINT_NEW_REMOTE_PATH_CONF, ConfigDef.Type.STRING, CAMEL_SINK_DROPBOX_ENDPOINT_NEW_REMOTE_PATH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DROPBOX_ENDPOINT_NEW_REMOTE_PATH_DOC);
        conf.define(CAMEL_SINK_DROPBOX_ENDPOINT_QUERY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_DROPBOX_ENDPOINT_QUERY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DROPBOX_ENDPOINT_QUERY_DOC);
        conf.define(CAMEL_SINK_DROPBOX_ENDPOINT_REMOTE_PATH_CONF, ConfigDef.Type.STRING, CAMEL_SINK_DROPBOX_ENDPOINT_REMOTE_PATH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DROPBOX_ENDPOINT_REMOTE_PATH_DOC);
        conf.define(CAMEL_SINK_DROPBOX_ENDPOINT_UPLOAD_MODE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_DROPBOX_ENDPOINT_UPLOAD_MODE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DROPBOX_ENDPOINT_UPLOAD_MODE_DOC);
        conf.define(CAMEL_SINK_DROPBOX_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_DROPBOX_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DROPBOX_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_DROPBOX_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_DROPBOX_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DROPBOX_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_DROPBOX_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_DROPBOX_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DROPBOX_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_DROPBOX_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_DROPBOX_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DROPBOX_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_DROPBOX_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_DROPBOX_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DROPBOX_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        return conf;
    }
}