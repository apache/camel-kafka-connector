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

package org.apache.camel.kafkaconnector.sink.elasticsearch;

import java.util.Properties;

import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.kafka.connect.runtime.ConnectorConfig;

public class CamelElasticSearchPropertyFactory implements ConnectorPropertyFactory {
    private final int tasksMax;
    private final String topic;
    private final String clusterName;
    private final String hostAddress;
    private final String index;


    CamelElasticSearchPropertyFactory(int tasksMax, String topic, String clusterName, String hostAddress, String index) {
        this.tasksMax = tasksMax;
        this.topic = topic;
        this.clusterName = clusterName;
        this.hostAddress = hostAddress;
        this.index = index;
    }

    protected int getTasksMax() {
        return tasksMax;
    }

    protected String getTopic() {
        return topic;
    }

    protected String getClusterName() {
        return clusterName;
    }

    protected String getHostAddress() {
        return hostAddress;
    }

    protected String getIndex() {
        return index;
    }

    @Override
    public Properties getProperties() {
        Properties connectorProps = new Properties();
        connectorProps.put(ConnectorConfig.NAME_CONFIG, "CamelElasticSearchSinkConnector");
        connectorProps.put("tasks.max", String.valueOf(tasksMax));

        connectorProps.put(ConnectorConfig.CONNECTOR_CLASS_CONFIG, "org.apache.camel.kafkaconnector.elasticsearchrest.CamelElasticsearchrestSinkConnector");
        connectorProps.put(ConnectorConfig.KEY_CONVERTER_CLASS_CONFIG, "org.apache.kafka.connect.storage.StringConverter");
        connectorProps.put(ConnectorConfig.VALUE_CONVERTER_CLASS_CONFIG, "org.apache.kafka.connect.storage.StringConverter");

        connectorProps.put("topics", topic);

        return connectorProps;
    }
}
