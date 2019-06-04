/**
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
package org.apache.camel.kafkaconnector;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

import java.util.Map;

public class CamelSourceConnectorConfig extends AbstractConfig {

   public static final String CAMEL_SOURCE_URL_DEFAULT = "timer:kafkaconnector";
   public static final String CAMEL_SOURCE_URL_CONF = "camel.source.url";
   private static final String CAMEL_SOURCE_URL_DOC = "The camel url to configure the source";

   public static final String TOPIC_DEFAULT = "test";
   public static final String TOPIC_CONF = "camel.source.kafka.topic";
   private static final String TOPIC_DOC = "The topic to publish data to";

   public CamelSourceConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
      super(config, parsedConfig);
   }

   public CamelSourceConnectorConfig(Map<String, String> parsedConfig) {
      this(conf(), parsedConfig);
   }

   public static ConfigDef conf() {
      return new ConfigDef()
            .define(CAMEL_SOURCE_URL_CONF, Type.STRING, CAMEL_SOURCE_URL_DEFAULT, Importance.HIGH, CAMEL_SOURCE_URL_DOC)
            .define(TOPIC_CONF, ConfigDef.Type.STRING, TOPIC_DEFAULT, ConfigDef.Importance.HIGH, TOPIC_DOC);
   }
}
