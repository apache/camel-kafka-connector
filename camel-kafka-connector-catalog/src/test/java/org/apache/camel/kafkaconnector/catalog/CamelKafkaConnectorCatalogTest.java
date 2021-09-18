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
package org.apache.camel.kafkaconnector.catalog;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.camel.kafkaconnector.model.CamelKafkaConnectorModel;
import org.apache.camel.kafkaconnector.model.CamelKafkaConnectorOptionModel;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.ConfigKey;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CamelKafkaConnectorCatalogTest {

    static CamelKafkaConnectorCatalog catalog;
    
    @BeforeAll
    public static void createCamelCatalog() {
        catalog = new CamelKafkaConnectorCatalog();
    }

    @Test
    void testConnectors() throws Exception {
        List<String> list = catalog.getConnectorsName();
        assertFalse(list.isEmpty());
    }

//    @Test
//    void testAws2S3Options() throws Exception {
//        Map<String, CamelKafkaConnectorModel> p = catalog.getConnectorsModel();
//        CamelKafkaConnectorModel model = p.get("camel-aws2-s3-sink");
//        assertEquals("org.apache.camel.kafkaconnector", model.getGroupId());
//        assertEquals("sink", model.getType());
//        assertEquals("org.apache.camel.kafkaconnector.aws2s3.CamelAws2s3SinkConnector", model.getConnectorClass());
//        assertEquals("camel.sink.path.bucketNameOrArn", model.getOptions().get(0).getName());
//        assertEquals("camel.sink.endpoint.amazonS3Client", model.getOptions().get(1).getName());
//        assertEquals("camel.sink.endpoint.amazonS3Presigner", model.getOptions().get(2).getName());
//        assertEquals(1, model.getConverters().size());
//        assertEquals(3, model.getTransforms().size());
//        assertEquals(1, model.getAggregationStrategies().size());
//    }
    
//    @Test
//    void testAws2SnsOptions() throws Exception {
//        Map<String, CamelKafkaConnectorModel> p = catalog.getConnectorsModel();
//        CamelKafkaConnectorModel model = p.get("camel-aws2-sns-sink");
//        assertEquals("org.apache.camel.kafkaconnector", model.getGroupId());
//        assertEquals("sink", model.getType());
//        assertEquals("org.apache.camel.kafkaconnector.aws2sns.CamelAws2snsSinkConnector", model.getConnectorClass());
//        assertEquals("camel.sink.path.topicNameOrArn", model.getOptions().get(0).getName());
//        assertEquals("camel.sink.endpoint.amazonSNSClient", model.getOptions().get(1).getName());
//        assertEquals("camel.sink.endpoint.autoCreateTopic", model.getOptions().get(2).getName());
//        assertEquals("false", model.getOptions().get(2).getDefaultValue());
//        assertNull(model.getOptions().get(1).getDefaultValue());
//        assertNull(model.getConverters());
//        assertNull(model.getTransforms());
//        assertNull(model.getAggregationStrategies());
//    }
    
    @Test
    void testCouchbaseOptions() throws Exception {
        Map<String, CamelKafkaConnectorModel> p = catalog.getConnectorsModel();
        CamelKafkaConnectorModel model = p.get("camel-couchbase-source");
        assertEquals("org.apache.camel.kafkaconnector", model.getGroupId());
        assertEquals("source", model.getType());
        assertEquals("org.apache.camel.kafkaconnector.couchbase.CamelCouchbaseSourceConnector", model.getConnectorClass());
        assertEquals("camel.source.path.protocol", model.getOptions().get(0).getName());
        assertNull(model.getOptions().get(0).getDefaultValue());
        assertNull(model.getConverters());
        assertNull(model.getTransforms());
        assertNull(model.getAggregationStrategies());
    }

    @Test
    void testAddConnector() throws Exception {
        String connectorName = "my-test-connector";
        catalog.addConnector(connectorName, "{\n"
                + "    \"connector\": {\n"
                + "        \"class\": \"org.apache.camel.kafkaconnector.my-test-connector.TestDemoConnector\",\n"
                + "        \"artifactId\": \"camel-my-test-connector-kafka-connector\",\n"
                + "        \"groupId\": \"org.apache.camel.kafkaconnector\",\n"
                + "        \"id\": \"my-test-connector\",\n"
                + "        \"type\": \"sink\",\n"
                + "        \"version\": \"0.6.0-SNAPSHOT\"\n"
                + "    },\n"
                + "    \"properties\": {\n"
                + "        \"camel.component.my-test-connector.demo\": {\n"
                + "            \"name\": \"camel.component.my-test-connector.demo\",\n"
                + "            \"description\": \"A demo description of the component\",\n"
                + "            \"defaultValue\": \"\\\"firstValue\\\"\",\n"
                + "            \"priority\": \"MEDIUM\",\n"
                + "            \"enum\": [\"firstValue\",\"secondValue\"]\n"
                + "        }\n"
                + "    }\n"
                + "}\n");
        
        assertTrue(catalog.getConnectorsName().contains(connectorName), "The new Connector wasn't added in the ConnectorNames list.");
        assertNotNull(catalog.getConnectorsModel().get(connectorName), "The new Connector wasn't added in the ConnectorModel map.");
        checkAddedConnectorContainsCorrectPropertyValues(connectorName);
    }

    private void checkAddedConnectorContainsCorrectPropertyValues(String connectorName) {
        CamelKafkaConnectorOptionModel camelKafkaConnectorOptionModel = catalog.getConnectorsModel().get(connectorName).getOptions().get(0);
        assertEquals("\"firstValue\"", camelKafkaConnectorOptionModel.getDefaultValue());
        assertEquals("camel.component.my-test-connector.demo", camelKafkaConnectorOptionModel.getName());
        assertEquals("MEDIUM", camelKafkaConnectorOptionModel.getPriority());
        assertEquals("A demo description of the component", camelKafkaConnectorOptionModel.getDescription());
        assertEquals(Arrays.asList("firstValue", "secondValue"), camelKafkaConnectorOptionModel.getPossibleEnumValues());
    }
    
    @Test
    void testRemoveConnector() throws Exception {
        String connectorName = "my-test-to-remove-connector";
        catalog.addConnector(connectorName, "{\n"
                + "    \"connector\": {\n"
                + "        \"class\": \"org.apache.camel.kafkaconnector.my-test-connector.TestDemoConnector\",\n"
                + "        \"artifactId\": \"camel-my-test-connector-kafka-connector\",\n"
                + "        \"groupId\": \"org.apache.camel.kafkaconnector\",\n"
                + "        \"id\": \"my-test-to-remove-connector\",\n"
                + "        \"type\": \"sink\",\n"
                + "        \"version\": \"0.6.0-SNAPSHOT\"\n"
                + "    },\n"
                + "    \"properties\": {}\n"
                + "}\n");
        
        catalog.removeConnector(connectorName);
        
        assertFalse(catalog.getConnectorsName().contains(connectorName), "The connector is still present in ConnectorNames list.");
        assertNull(catalog.getConnectorsModel().get(connectorName), "The connector model is still present in the ConnectorsModel map.");
    }
    
//    @Test
//    void testAws2SnsGetSingleOption() throws Exception {
//        Map<String, CamelKafkaConnectorModel> p = catalog.getConnectorsModel();
//        CamelKafkaConnectorOptionModel existingOption = catalog.getOptionModel("camel-aws2-sns-sink", "camel.sink.path.topicNameOrArn");
//        assertNotNull(existingOption);
//        assertEquals("true", existingOption.getRequired());
//        assertEquals("Topic name or ARN", existingOption.getDescription());
//        CamelKafkaConnectorOptionModel nonExistingOption = catalog.getOptionModel("camel-aws2-sns-sink", "camel.sink.path.topiNameOrAr");
//        assertNull(nonExistingOption);
//    }
    
//    @Test
//    void testConnectorContainsDescription() throws Exception {
//        Map<String, CamelKafkaConnectorModel> p = catalog.getConnectorsModel();
//        CamelKafkaConnectorModel model = p.get("camel-aws2-s3-sink");
//        assertEquals("Store and retrieve objects from AWS S3 Storage Service using AWS SDK version 2.x.", model.getDescription());
//    }
    
    @Test
    void testBasicConfigurationForSink() throws Exception {
        ConfigDef sinkConfigDef = catalog.getBasicConfigurationForSink();
        ConfigKey marshalConfigKey = sinkConfigDef.configKeys().get(CamelSinkConnectorConfig.CAMEL_SINK_MARSHAL_CONF);
        assertEquals(CamelSinkConnectorConfig.CAMEL_SINK_MARSHAL_CONF, marshalConfigKey.name);
        assertEquals(CamelSinkConnectorConfig.CAMEL_SINK_MARSHAL_DOC, marshalConfigKey.documentation);
        assertEquals(Type.STRING, marshalConfigKey.type);
    }
    
    @Test
    void testBasicConfigurationForSource() throws Exception {
        ConfigDef sourceConfigDef = catalog.getBasicConfigurationForSource();
        ConfigKey marshalConfigKey = sourceConfigDef.configKeys().get(CamelSourceConnectorConfig.CAMEL_SOURCE_MARSHAL_CONF);
        assertEquals(CamelSourceConnectorConfig.CAMEL_SOURCE_MARSHAL_CONF, marshalConfigKey.name);
        assertEquals(CamelSourceConnectorConfig.CAMEL_SOURCE_MARSHAL_DOC, marshalConfigKey.documentation);
        assertEquals(Type.STRING, marshalConfigKey.type);
    }

}
