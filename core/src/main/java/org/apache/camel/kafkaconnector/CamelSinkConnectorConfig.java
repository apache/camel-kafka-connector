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

public class CamelSinkConnectorConfig extends AbstractConfig {
   public static final String CAMEL_SINK_URL_DEFAULT = "log:kafkaconnector?showAll=true";
   public static final String CAMEL_SINK_URL_CONF = "camel.sink.url";
   private static final String CAMEL_SINK_URL_DOC = "The camel url to configure the destination";

   public CamelSinkConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
      super(config, parsedConfig);
   }

   public CamelSinkConnectorConfig(Map<String, String> parsedConfig) {
      this(conf(), parsedConfig);
   }

   public static ConfigDef conf() {
      return new ConfigDef()
            .define(CAMEL_SINK_URL_CONF, Type.STRING, CAMEL_SINK_URL_DEFAULT, Importance.HIGH, CAMEL_SINK_URL_DOC);
   }
}
