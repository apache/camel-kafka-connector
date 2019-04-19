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
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CamelSinkConnector extends SinkConnector {
   private static Logger log = LoggerFactory.getLogger(CamelSinkConnector.class);

   public static final String NAME_CONFIG = "name";
   public static final String COMPONENT_CONFIG = "component";
   public static final String ADDRESS_CONFIG = "address";
   public static final String OPTIONS_CONFIG = "options";

   private static final ConfigDef CONFIG_DEF = new ConfigDef()
           .define(COMPONENT_CONFIG, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, "Name of the Apache Camel component which will be used to route messages. (component://address?option1=value1&options2=value2)")
           .define(ADDRESS_CONFIG, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, "The address which will be used to configure the component. (component://address?option1=value1&options2=value2)")
           .define(OPTIONS_CONFIG, ConfigDef.Type.LIST, Collections.EMPTY_LIST, ConfigDef.Importance.HIGH, "List of options for configuring the component. The list should contain key-value pairs. E.g. `[\"option1=key1\", \"option2=key2\"]`.");

   private String connectorName;
   private String component;
   private String address;
   private List<String> options;

   @Override
   public String version() {
      return VersionUtil.getVersion();
   }

   @Override
   public void start(Map<String, String> props) {
      log.info("Connector config keys: {}", String.join(", ", props.keySet()));

      connectorName = props.get(NAME_CONFIG);
      log.info("Starting connector {}", connectorName);

      AbstractConfig parsedConfig = new AbstractConfig(CONFIG_DEF, props);
      component = parsedConfig.getString(COMPONENT_CONFIG);
      address = parsedConfig.getString(ADDRESS_CONFIG);
      options = parsedConfig.getList(OPTIONS_CONFIG);
   }

   @Override
   public Class<? extends Task> taskClass() {
      return CamelSinkTask.class;
   }

   @Override
   public List<Map<String, String>> taskConfigs(int maxTasks) {
      log.info("Setting task configurations for {} workers.", maxTasks);

      ArrayList<Map<String, String>> configs = new ArrayList<>();

      Map<String, String> config = new HashMap<>(4);
      config.put(COMPONENT_CONFIG, component);
      config.put(ADDRESS_CONFIG, address);
      config.put(OPTIONS_CONFIG, String.join("&", options));

      for (int i = 0; i < maxTasks; i++) {
         Map<String, String> taskConfig = new HashMap<>(config);
         taskConfig.put(NAME_CONFIG, connectorName + "-" + i);

         configs.add(taskConfig);
      }
      return configs;
   }

   @Override
   public void stop() {
      //nothing to do
   }

   @Override
   public ConfigDef config() {
      return CamelSinkConnectorConfig.conf();
   }
}
