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
package org.apache.camel.kafkaconnector.googlestoragesource;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelGooglestoragesourceSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_BUCKET_NAME_OR_ARN_CONF = "camel.kamelet.google-storage-source.bucketNameOrArn";
    public static final String CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_BUCKET_NAME_OR_ARN_DOC = "The Google Cloud Storage bucket name or Bucket Amazon Resource Name (ARN).";
    public static final String CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_BUCKET_NAME_OR_ARN_DEFAULT = null;
    public static final String CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_SERVICE_ACCOUNT_KEY_CONF = "camel.kamelet.google-storage-source.serviceAccountKey";
    public static final String CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_SERVICE_ACCOUNT_KEY_DOC = "The service account key to use as credentials for Google Cloud Storage access. You must encode this value in base64.";
    public static final String CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_SERVICE_ACCOUNT_KEY_DEFAULT = null;
    public static final String CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_DELETE_AFTER_READ_CONF = "camel.kamelet.google-storage-source.deleteAfterRead";
    public static final String CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_DELETE_AFTER_READ_DOC = "Specifies to delete objects after consuming them.";
    public static final Boolean CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_DELETE_AFTER_READ_DEFAULT = true;
    public static final String CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_AUTO_CREATE_BUCKET_CONF = "camel.kamelet.google-storage-source.autoCreateBucket";
    public static final String CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_AUTO_CREATE_BUCKET_DOC = "Specifies to automatically create the Google Cloud Storage bucket.";
    public static final Boolean CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_AUTO_CREATE_BUCKET_DEFAULT = false;

    public CamelGooglestoragesourceSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelGooglestoragesourceSourceConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_BUCKET_NAME_OR_ARN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_BUCKET_NAME_OR_ARN_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_BUCKET_NAME_OR_ARN_DOC);
        conf.define(CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_SERVICE_ACCOUNT_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_SERVICE_ACCOUNT_KEY_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_SERVICE_ACCOUNT_KEY_DOC);
        conf.define(CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_DELETE_AFTER_READ_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_DELETE_AFTER_READ_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_DELETE_AFTER_READ_DOC);
        conf.define(CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_AUTO_CREATE_BUCKET_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_AUTO_CREATE_BUCKET_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_GOOGLESTORAGESOURCE_KAMELET_AUTO_CREATE_BUCKET_DOC);
        return conf;
    }
}