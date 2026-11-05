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
import java.util.TreeMap;

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
    
    @Test
    void testCouchbaseOptions() throws Exception {
        Map<String, CamelKafkaConnectorModel> p = catalog.getConnectorsModel();
        CamelKafkaConnectorModel model = p.get("camel-aws2-iam-sink");
        assertEquals("org.apache.camel.kafkaconnector", model.getGroupId());
        assertEquals("sink", model.getType());
        assertEquals("org.apache.camel.kafkaconnector.aws2iam.CamelAws2iamSinkConnector", model.getConnectorClass());
        assertEquals("camel.sink.path.label", model.getOptions().get(0).getName());
        assertNull(model.getOptions().get(0).getDefaultValue());
        assertNull(model.getConverters());
        assertNull(model.getTransforms());
        assertNull(model.getAggregationStrategies());
    }

    @Test
    void testSyslogConnectors() throws Exception {
        assertSyslogConnector("camel-syslog-source", "source", "org.apache.camel.kafkaconnector.syslog.CamelSyslogSourceConnector");
        assertSyslogConnector("camel-syslog-sink", "sink", "org.apache.camel.kafkaconnector.syslog.CamelSyslogSinkConnector");
    }

    private void assertSyslogConnector(String connectorName, String type, String connectorClass) {
        assertTrue(catalog.getConnectorsName().contains(connectorName), connectorName + " is missing from the connector names.");
        CamelKafkaConnectorModel model = catalog.getConnectorsModel().get(connectorName);
        assertNotNull(model, connectorName + " is missing from the connector models.");
        assertEquals("org.apache.camel.kafkaconnector", model.getGroupId());
        assertEquals("camel-syslog-kafka-connector", model.getArtifactId());
        assertEquals(type, model.getType());
        assertEquals(connectorClass, model.getConnectorClass());
        assertNotNull(model.getDescription());
        assertNotNull(model.getVersion());
        assertFalse(model.getVersion().contains("${"), "The " + connectorName + " version placeholder was not resolved: " + model.getVersion());
        CamelKafkaConnectorOptionModel protocol = catalog.getOptionModel(connectorName, "camel." + type + ".path.protocol");
        assertNotNull(protocol);
        assertEquals("true", protocol.getRequired());
        assertEquals(Arrays.asList("tcp", "udp"), protocol.getPossibleEnumValues());
    }

    @Test
    void testSyslogOptionsMatchNetty() throws Exception {
        // the syslog connectors reuse the netty connector configuration, but their catalog
        // metadata is hand-maintained: catch it drifting from the generated netty metadata
        assertSameOptions("camel-netty-source", "camel-syslog-source");
        assertSameOptions("camel-netty-sink", "camel-syslog-sink");
    }

    private void assertSameOptions(String nettyConnectorName, String syslogConnectorName) {
        Map<String, String> nettyOptions = describeOptions(nettyConnectorName);
        Map<String, String> syslogOptions = describeOptions(syslogConnectorName);
        String message = syslogConnectorName + " options are out of sync with " + nettyConnectorName
                + ": update connectors/camel-syslog-kafka-connector/src/generated/resources/" + syslogConnectorName
                + ".json with the properties of connectors/camel-netty-kafka-connector/src/generated/resources/" + nettyConnectorName + ".json";
        assertFalse(nettyOptions.isEmpty());
        assertEquals(nettyOptions.keySet(), syslogOptions.keySet(), message);
        nettyOptions.forEach((name, option) -> assertEquals(option, syslogOptions.get(name), message + " (option " + name + ")"));
    }

    private Map<String, String> describeOptions(String connectorName) {
        CamelKafkaConnectorModel model = catalog.getConnectorsModel().get(connectorName);
        assertNotNull(model, connectorName + " is missing from the connector models.");
        Map<String, String> options = new TreeMap<>();
        for (CamelKafkaConnectorOptionModel option : model.getOptions()) {
            options.put(option.getName(), Arrays.asList(option.getDescription(), option.getDefaultValue(), option.getPriority(),
                    option.getRequired(), option.getPossibleEnumValues()).toString());
        }
        return options;
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
