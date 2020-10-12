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

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.apache.camel.kafkaconnector.model.CamelKafkaConnectorModel;
import org.apache.camel.kafkaconnector.model.CamelKafkaConnectorOptionModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CamelKafkaConnectorCatalogTest {

    static CamelKafkaConnectorCatalog catalog;
   @BeforeAll
    public static void createCamelCatalog() {
        catalog = new CamelKafkaConnectorCatalog();
    }

    @Test
    void testConnectors() throws Exception {
        List<String> list = catalog.getConnectorsName();
        assertTrue(list.contains("camel-aws2-s3-sink"));
        assertTrue(list.contains("camel-aws2-s3-source"));
    }

    @Test
    void testOptions() throws Exception {
        Map<String, CamelKafkaConnectorModel> p = catalog.getConnectorsModel();
        CamelKafkaConnectorModel model = p.get("camel-aws2-s3-sink");
        assertEquals("org.apache.camel.kafkaconnector", model.getGroupId());
        assertEquals("sink", model.getType());
        assertEquals("org.apache.camel.kafkaconnector.aws2s3.CamelAws2s3SinkConnector", model.getConnectorClass());
        assertEquals(61, model.getOptions().size());
        assertEquals("camel.sink.path.bucketNameOrArn", model.getOptions().get(0).getName());
        assertEquals("camel.sink.endpoint.amazonS3Client", model.getOptions().get(1).getName());
        assertEquals("camel.sink.endpoint.autoCreateBucket", model.getOptions().get(2).getName());
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
                + "            \"defaultValue\": \"\\\"false\\\"\",\n"
                + "            \"priority\": \"MEDIUM\"\n"
                + "        }\n"
                + "    }\n"
                + "}\n");
        
        assertTrue(catalog.getConnectorsName().contains(connectorName));
        assertNotNull(catalog.getConnectorsModel().get(connectorName));
        CamelKafkaConnectorOptionModel camelKafkaConnectorOptionModel = catalog.getConnectorsModel().get(connectorName).getOptions().get(0);
        assertEquals("\"false\"", camelKafkaConnectorOptionModel.getDefaultValue());
        assertEquals("camel.component.my-test-connector.demo", camelKafkaConnectorOptionModel.getName());
        assertEquals("MEDIUM", camelKafkaConnectorOptionModel.getPriority());
        assertEquals("A demo description of the component", camelKafkaConnectorOptionModel.getDescription());
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
        
        assertTrue(catalog.getConnectorsName().contains(connectorName));
        assertNotNull(catalog.getConnectorsModel().get(connectorName));
        
        catalog.removeConnector(connectorName);
        
        assertFalse(catalog.getConnectorsName().contains(connectorName));
        assertNull(catalog.getConnectorsModel().get(connectorName));
    }

}
