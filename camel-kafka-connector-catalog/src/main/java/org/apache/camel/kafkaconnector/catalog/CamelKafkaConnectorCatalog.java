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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.camel.kafkaconnector.model.CamelKafkaConnectorModel;
import org.apache.camel.kafkaconnector.model.CamelKafkaConnectorOptionModel;
import org.apache.camel.tooling.model.JsonMapper;
import org.apache.camel.util.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamelKafkaConnectorCatalog {

    static List<String> connectorsName = new ArrayList<String>();
    static Map<String, CamelKafkaConnectorModel> connectorsModel = new HashMap<String, CamelKafkaConnectorModel>();
    private static final Logger LOG = LoggerFactory.getLogger(CamelKafkaConnectorCatalog.class);
    private static final String CONNECTORS_DIR = "connectors";
    private static final String DESCRIPTORS_DIR = "descriptors";
    private static final String CONNECTORS_PROPERTIES = "connectors.properties";

    public CamelKafkaConnectorCatalog() {
        initCatalog();
        generateModel();
    }

    private void generateModel() {
        for (String connector : connectorsName) {
            connectorsModel.put(connector, getConnectorModel(connector));
        }
    }

    private void initCatalog() {
        try (InputStream input = CamelKafkaConnectorCatalog.class.getResourceAsStream(File.separator + DESCRIPTORS_DIR + File.separator + CONNECTORS_PROPERTIES)) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            while (reader.ready()) {
                String connector = reader.readLine();
                if (connector.equalsIgnoreCase("camel-coap-tcp-source")) {
                    connectorsName.add("camel-coap+tcp-source");
                } else if (connector.equalsIgnoreCase("camel-coaps-tcp-source")) {
                    connectorsName.add("camel-coaps+tcp-source");
                } else if (connector.equalsIgnoreCase("camel-coaps-tcp-sink")) {
                    connectorsName.add("camel-coaps+tcp-sink");
                } else if (connector.equalsIgnoreCase("camel-coap-tcp-sink")) {
                    connectorsName.add("camel-coap+tcp-sink");
                } else {
                    connectorsName.add(connector);
                }
            }
        } catch (FileNotFoundException e) {
            LOG.error("Cannot find file: {}", e.getMessage(), e);
        } catch (IOException e) {
            LOG.error("IO Exception: {}", e.getMessage(), e);
        }
    }

    public String getConnectorAsJson(String connectorName) {
        String result = null;
        try (InputStream connectorModelInputSream = CamelKafkaConnectorCatalog.class.getResourceAsStream(File.separator + CONNECTORS_DIR + File.separator + connectorName + ".json")) {
            result = new BufferedReader(new InputStreamReader(connectorModelInputSream, StandardCharsets.UTF_8))
                .lines()
                .map(String::trim) // to change line
                .collect(Collectors.joining());
        } catch (IOException e) {
            LOG.error("IO Exception: {}", e.getMessage(), e);
        }
        return result;
    }

    private CamelKafkaConnectorModel getConnectorModel(String connectorName) {
        CamelKafkaConnectorModel model = new CamelKafkaConnectorModel();
        String json = getConnectorAsJson(connectorName);
        JsonObject obj = JsonMapper.deserialize(json);
        JsonObject wrapper = (JsonObject)obj.get("connector");
        model.setConnectorClass((String)wrapper.get("class"));
        model.setArtifactId((String)wrapper.get("artifactId"));
        model.setGroupId((String)wrapper.get("groupId"));
        model.setType((String)wrapper.get("type"));
        model.setVersion((String)wrapper.get("version"));
        model.setOptions((List<CamelKafkaConnectorOptionModel>)getConnectorOptionModel(obj));
        return model;
    }

    private List<CamelKafkaConnectorOptionModel> getConnectorOptionModel(JsonObject obj) {
        List<CamelKafkaConnectorOptionModel> model = new ArrayList<CamelKafkaConnectorOptionModel>();
        JsonObject wrapper = (JsonObject)obj.get("properties");
        Set<String> options = wrapper.keySet();
        for (String string : options) {
            JsonObject object = (JsonObject)wrapper.get(string);
            CamelKafkaConnectorOptionModel singleModel = new CamelKafkaConnectorOptionModel();
            singleModel.setDefaultValue((String)object.get("defaultValue"));
            singleModel.setPriority((String)object.get("priority"));
            singleModel.setDescription((String)object.get("description"));
            singleModel.setName((String)object.get("name"));
            model.add(singleModel);
        }
        return model;
    }

    public List<String> getConnectorsName() {
        return connectorsName;
    }

    public Map<String, CamelKafkaConnectorModel> getConnectorsModel() {
        return connectorsModel;
    }
}
