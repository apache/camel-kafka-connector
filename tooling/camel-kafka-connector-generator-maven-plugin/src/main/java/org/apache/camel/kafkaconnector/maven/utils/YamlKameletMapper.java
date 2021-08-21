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
package org.apache.camel.kafkaconnector.maven.utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.apache.camel.kafkaconnector.maven.model.KameletModel;
import org.apache.camel.kafkaconnector.maven.model.KameletPropertyModel;

public final class YamlKameletMapper {
    public static final ObjectMapper YAML_MAPPER = new YAMLMapper()
            .configure(YAMLGenerator.Feature.WRITE_DOC_START_MARKER, false)
            .configure(YAMLGenerator.Feature.LITERAL_BLOCK_STYLE, true)
            .configure(YAMLGenerator.Feature.MINIMIZE_QUOTES, true);

    private YamlKameletMapper() { }

    public static KameletModel parseKameletYaml(URL src) throws IOException {
        ObjectNode kamelet = YAML_MAPPER.readValue(src, ObjectNode.class);
        return parseKameletYaml(kamelet);
    }

    public static KameletModel parseKameletYaml(String src) throws JsonProcessingException {
        ObjectNode kamelet = YAML_MAPPER.readValue(src, ObjectNode.class);
        return parseKameletYaml(kamelet);
    }

    public static KameletModel parseKameletYaml(ObjectNode kameletYamlNode) {
        KameletModel km = new KameletModel();
        km.setType(kameletYamlNode.at("/metadata/labels/camel.apache.org~1kamelet.type").asText());

        km.setName(kameletYamlNode.at("/metadata/name").asText());

        km.setDescription(kameletYamlNode.at("/spec/definition/description").asText());

        Set<String> requiredProperties = new HashSet<>();
        kameletYamlNode.at("/spec/definition/required").forEach(req -> requiredProperties.add(req.asText()));
        km.setRequiredProperties(requiredProperties);

        Set<String> dependencies = new HashSet<>();
        kameletYamlNode.at("/spec/dependencies").forEach(req -> dependencies.add(req.asText()));
        km.setDependencies(dependencies);

        List<KameletPropertyModel> kpms = new ArrayList<>();
        Iterator<Map.Entry<String, JsonNode>> it = kameletYamlNode.at("/spec/definition/properties").fields();
        while (it.hasNext()) {
            Map.Entry<String, JsonNode> property = it.next();
            KameletPropertyModel kpm = new KameletPropertyModel();
            kpm.setName(property.getKey());
            JsonNode propertyFields = property.getValue();
            kpm.setDefaultValue(propertyFields.get("default") != null ? propertyFields.get("default").asText() : null);
            kpm.setExample(propertyFields.get("example") != null ? propertyFields.get("example").asText() : null);
            kpm.setDescription(propertyFields.get("description") != null ? propertyFields.get("description").asText() : null);
            kpm.setFormat(propertyFields.get("format") != null ? propertyFields.get("format").asText() : null);
            kpm.setTitle(propertyFields.get("title") != null ? propertyFields.get("title").asText() : null);
            kpm.setType(propertyFields.get("type") != null ? propertyFields.get("type").asText() : null);
            kpms.add(kpm);
        }
        km.setProperties(kpms);

        return km;
    }
}
