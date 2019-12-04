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

import org.apache.kafka.connect.runtime.ConnectorConfig;

public class CamelElasticSearchIndexPropertyFactory extends CamelElasticSearchPropertyFactory {
    private final String index;
    private final String transformerKey;


    CamelElasticSearchIndexPropertyFactory(int tasksMax, String topic, String clusterName, String hostAddress,
                                           String index, String transformerKey) {
        super(tasksMax, topic, clusterName, hostAddress, index);
        this.index = index;
        this.transformerKey = transformerKey;
    }


    @Override
    public Properties getProperties() {
        Properties connectorProps = super.getProperties();

        connectorProps.put(ConnectorConfig.TRANSFORMS_CONFIG, "ElasticSearchTransformer");
        connectorProps.put(ConnectorConfig.TRANSFORMS_CONFIG + ".ElasticSearchTransformer.type",
                "org.apache.camel.kafkaconnector.sink.elasticsearch.transforms.ConnectRecordValueToMapTransformer");
        connectorProps.put(ConnectorConfig.TRANSFORMS_CONFIG + ".ElasticSearchTransformer.key",
                transformerKey);

        String queueUrl = "elasticsearch-rest://" + getClusterName() + "?hostAddresses=" + getHostAddress()
                + "&operation=Index"
                + "&indexName=" + index;

        connectorProps.put("camel.sink.url", queueUrl);


        return connectorProps;
    }
}
