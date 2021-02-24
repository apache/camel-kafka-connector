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

import java.util.List;

import org.apache.camel.kafkaconnector.model.CamelKafkaConnectorModel;
import org.apache.camel.kafkaconnector.model.CamelKafkaConnectorOptionModel;
import org.apache.camel.util.json.JsonArray;
import org.apache.camel.util.json.JsonObject;

public final class JsonMapperKafkaConnector {

    private static final String KAFKA_CONNECTOR_GROUPID_SUFFIX = ".kafkaconnector";
    private static final String KAFKA_CONNECTOR_ARTIFACTID_SUFFIX = "-kafka-connector";

    private JsonMapperKafkaConnector() {
    }

    public static JsonObject asJsonObject(CamelKafkaConnectorModel model) {
        JsonObject obj = new JsonObject();
        obj.put("class", model.getConnectorClass());
        obj.put("artifactId", model.getArtifactId() + KAFKA_CONNECTOR_ARTIFACTID_SUFFIX);
        obj.put("groupId", model.getGroupId() + KAFKA_CONNECTOR_GROUPID_SUFFIX);
        obj.put("id", model.getTitle() + "-" + model.getType());
        obj.put("type", model.getType());
        obj.put("version", model.getVersion());
        obj.put("description", model.getDescription());
        JsonObject wrapper = new JsonObject();
        wrapper.put("connector", obj);
        wrapper.put("properties", asJsonObject(model.getOptions()));
        if (!model.getConverters().isEmpty()) {
            wrapper.put("converters", new JsonArray(model.getConverters()));
        }
        if (!model.getTransforms().isEmpty()) {
            wrapper.put("transforms", new JsonArray(model.getTransforms()));
        }
        if (!model.getAggregationStrategies().isEmpty()) {
            wrapper.put("aggregationStrategies", new JsonArray(model.getAggregationStrategies()));
        }
        return wrapper;
    }

    public static JsonObject asJsonObject(List<CamelKafkaConnectorOptionModel> options) {
        JsonObject json = new JsonObject();
        options.forEach(option -> json.put(option.getName(), asJsonObject(option)));
        return json;
    }

    public static JsonObject asJsonObject(CamelKafkaConnectorOptionModel model) {
        JsonObject obj = new JsonObject();
        obj.put("name", model.getName());
        obj.put("description", model.getDescription());
        if (model.getDefaultValue() != null) {
            obj.put("defaultValue", model.getDefaultValue());
        }
        obj.put("priority", model.getPriority());
        obj.put("required", model.getRequired());
        List<String> possibleEnumValues = model.getPossibleEnumValues();
        if (possibleEnumValues != null && !possibleEnumValues.isEmpty()) {
            obj.put("enum", possibleEnumValues);
        }
        return obj;
    }
}
