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
package org.apache.camel.kafkaconnector.maven.model;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public class KameletModel {
    private String name;
    private String type;
    private String description;
    private Set<String> dependencies;
    private List<KameletPropertyModel> properties;
    private Set<String> requiredProperties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getDependencies() {
        return ImmutableSet.copyOf(dependencies);
    }

    public void setDependencies(Set<String> dependencies) {
        this.dependencies = dependencies;
    }

    public List<KameletPropertyModel> getProperties() {
        return ImmutableList.copyOf(properties);
    }

    public void setProperties(List<KameletPropertyModel> properties) {
        this.properties = properties;
    }

    public Set<String> getRequiredProperties() {
        return ImmutableSet.copyOf(requiredProperties);
    }

    public void setRequiredProperties(Set<String> requiredProperties) {
        this.requiredProperties = requiredProperties;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "KameletModel{"
                + "name='" + name + '\''
                + ", type='" + type + '\''
                + ", description='" + description + '\''
                + ", dependencies=" + dependencies
                + ", properties=" + properties
                + ", requiredProperties=" + requiredProperties
                + '}';
    }
}
