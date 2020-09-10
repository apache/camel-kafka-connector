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
package org.apache.camel.kafkaconnector.maven.dto;

import java.util.List;

public class CamelKafkaConnectorModel {
    private String groupId;
    private String artifactId;
    private String version;
    private String title;
    private String connectorClass;
    private List<String> converters;
    private List<String> transforms;
    private List<String> aggregationStrategies;
    private List<CamelKafkaConnectorOptionModel> options;

    public List<CamelKafkaConnectorOptionModel> getOptions() {
        return options;
    }

    public void setOptions(List<CamelKafkaConnectorOptionModel> options) {
        this.options = options;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getConnectorClass() {
        return connectorClass;
    }

    public void setConnectorClass(String connectorClass) {
        this.connectorClass = connectorClass;
    }

    public List<String> getConverters() {
        return converters;
    }

    public void setConverters(List<String> converters) {
        this.converters = converters;
    }

    public List<String> getTransforms() {
        return transforms;
    }

    public void setTransforms(List<String> transforms) {
        this.transforms = transforms;
    }

    public List<String> getAggregationStrategies() {
        return aggregationStrategies;
    }

    public void setAggregationStrategies(List<String> aggregationStrategies) {
        this.aggregationStrategies = aggregationStrategies;
    }
}
