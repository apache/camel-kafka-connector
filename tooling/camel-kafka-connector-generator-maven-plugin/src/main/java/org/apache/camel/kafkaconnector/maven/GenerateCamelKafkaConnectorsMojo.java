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
package org.apache.camel.kafkaconnector.maven;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.camel.catalog.CamelCatalog;
import org.apache.camel.catalog.DefaultCamelCatalog;
import org.apache.camel.kafkaconnector.maven.utils.MavenUtils;
import org.apache.camel.tooling.model.ComponentModel;
import org.apache.camel.tooling.model.JsonMapper;
import org.apache.maven.ProjectDependenciesResolver;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.BuildPluginManager;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.ProjectBuilder;
import org.codehaus.plexus.resource.loader.FileResourceCreationException;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;

import static org.apache.camel.kafkaconnector.maven.utils.MavenUtils.sanitizeMavenArtifactId;
import static org.twdata.maven.mojoexecutor.MojoExecutor.artifactId;
import static org.twdata.maven.mojoexecutor.MojoExecutor.configuration;
import static org.twdata.maven.mojoexecutor.MojoExecutor.element;
import static org.twdata.maven.mojoexecutor.MojoExecutor.executeMojo;
import static org.twdata.maven.mojoexecutor.MojoExecutor.executionEnvironment;
import static org.twdata.maven.mojoexecutor.MojoExecutor.goal;
import static org.twdata.maven.mojoexecutor.MojoExecutor.groupId;
import static org.twdata.maven.mojoexecutor.MojoExecutor.name;
import static org.twdata.maven.mojoexecutor.MojoExecutor.plugin;
import static org.twdata.maven.mojoexecutor.MojoExecutor.version;

/**
 * Generate Camel Kafka Connector for the component
 */
@Mojo(name = "generate-camel-kafka-connectors", threadSafe = true,
        requiresDependencyCollection = ResolutionScope.COMPILE_PLUS_RUNTIME,
        requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME,
        defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class GenerateCamelKafkaConnectorsMojo extends AbstractCamelKafkaConnectorMojo {

    private static final String GENERATED_SECTION_START = "START OF GENERATED CODE";
    private static final String GENERATED_SECTION_START_COMMENT = "<!--" + GENERATED_SECTION_START + "-->";
    private static final String GENERATED_SECTION_END = "END OF GENERATED CODE";
    private static final String GENERATED_SECTION_END_COMMENT = "<!--" + GENERATED_SECTION_END + "-->";

    @Parameter(property = "overridePomFile", required = false, defaultValue = "false")
    protected Boolean overridePomFile;

    /**
     * The maven session.
     */
    @Parameter(defaultValue = "${session}", readonly = true)
    private MavenSession session;

    @Component
    private BuildPluginManager pluginManager;

    /**
     * The Camel Component Filter to select for which components generate the corresponding camel kafka connector.
     */
    @Parameter(defaultValue = "", readonly = true)
    private String filter;

    /**
     * The Camel Component Exclusion List to select for which components must be skipped while generating kafka connector.
     */
    @Parameter(defaultValue = "", readonly = true)
    private List excludedComponents = Collections.EMPTY_LIST;

    @Component
    private ProjectDependenciesResolver projectDependenciesResolver;

    @Component
    private ProjectBuilder projectBuilder;

    @Override
    protected String getMainDepArtifactId() {
        throw new UnsupportedOperationException("getMainDepArtifactId() in GenerateCamelKafkaConnectorsMojo");
    }

    @Override
    protected void executeAll() throws MojoExecutionException, IOException, ResourceNotFoundException, FileResourceCreationException {
        CamelCatalog cc = new DefaultCamelCatalog();
        List<String> components;
        List<String> filteredComponents;
        if (filter == null || filter.isEmpty()) {
            components = cc.findComponentNames();
        } else {
            Set<String> filterComponentNames = new HashSet<>(Arrays.asList(filter.split(",")));
            components = cc.findComponentNames().stream().filter(componentName -> filterComponentNames.contains(componentName)).collect(Collectors.toList());
        }
        if (excludedComponents == null || excludedComponents.isEmpty()) {
            filteredComponents = components;
        } else {
            filteredComponents = components.stream().filter(component -> !excludedComponents.contains(component)).collect(Collectors.toList());
        }
        if (filter != null && !filter.isEmpty()) {
            getLog().info("Filtered Components that will be generated: " + filter);
        }
        if (excludedComponents != null && !excludedComponents.isEmpty()) {
            getLog().info("Excluded Components that won't be generated: " + excludedComponents);
        }
        getLog().info("Components found to be generated/updated: " + filteredComponents);

        //TODO: evaluate dataformats to include in each camel kafka connector generated placing them as a comma separated GAV in:
        String additionalDependencies = "";

        final Properties properties = new Properties();
        properties.load(new FileInputStream(rm.getResourceAsFile("project.properties")));
        for (String component : filteredComponents) {
            String cJson = cc.componentJSonSchema(component);
            ComponentModel cm = JsonMapper.generateComponentModel(cJson);

            executeMojo(
                    plugin(
                            groupId(properties.getProperty("groupId")),
                            artifactId(properties.getProperty("artifactId")),
                            version(properties.getProperty("version"))
                    ),
                    goal("camel-kafka-connector-create"),
                    configuration(
                            element(name("name"), component),
                            element(name("componentJson"), cJson),
                            element(name("initialPomTemplate"), initialPomTemplate),
                            element(name("noticeTemplate"), noticeTemplate),
                            element(name("licenseTemplate"), licenseTemplate),
                            element(name("fixDependenciesProperties"), fixDependenciesProperties),
                            element(name("packageFileTemplate"), packageFileTemplate),
                            element(name("overridePomFile"), overridePomFile.toString())
                    ),
                    executionEnvironment(
                            project,
                            session,
                            pluginManager
                    )
            );

            executeMojo(
                    plugin(
                            groupId(properties.getProperty("groupId")),
                            artifactId(properties.getProperty("artifactId")),
                            version(properties.getProperty("version"))
                    ),
                    goal("camel-kafka-connector-update"),
                    configuration(
                            element(name("additionalDependencies"), additionalDependencies),
                            element(name("name"), component),
                            element(name("componentJson"), cJson),
                            element(name("initialPomTemplate"), initialPomTemplate),
                            element(name("noticeTemplate"), noticeTemplate),
                            element(name("licenseTemplate"), licenseTemplate),
                            element(name("fixDependenciesProperties"), fixDependenciesProperties),
                            element(name("packageFileTemplate"), packageFileTemplate)
                    ),
                    executionEnvironment(
                            project,
                            session,
                            pluginManager
                    )
            );
        }

        if (removeMissingComponents) {
            if (projectDir != null && projectDir.isDirectory()) {
                // sanitize names, as there are some camel components with + signal which are sanitized when creating the kafka connector
                List<String> sanitizedComponentNames = components.stream().map(MavenUtils::sanitizeMavenArtifactId).collect(Collectors.toList());
                // retrieve the list of camel kafka connectors
                String[] connectorNames = projectDir.list((dir, filename) -> filename.endsWith(KAFKA_CONNECTORS_SUFFIX));
                if (connectorNames != null) {
                    List<String> connectorsToRemove = Stream.of(connectorNames).sorted().filter(filename -> {
                        String componentName = extractComponentName(filename);
                        // set to remove connectors that are not in camel catalog or are explicitly excluded
                        return !sanitizedComponentNames.contains(componentName) || excludedComponents.contains(componentName);

                    }).collect(Collectors.toList());

                    for (String component: connectorsToRemove) {

                        executeMojo(
                                plugin(
                                        groupId(properties.getProperty("groupId")),
                                        artifactId(properties.getProperty("artifactId")),
                                        version(properties.getProperty("version"))
                                ),
                                goal("camel-kafka-connector-delete"),
                                configuration(
                                        element(name("name"), component)
                                ),
                                executionEnvironment(
                                        project,
                                        session,
                                        pluginManager
                                )
                        );
                    }
                }
            }
        }
    }

    private String extractComponentName(String connectorName) {
        String name = connectorName.substring("camel-".length());
        return name.substring(0, name.length() - KAFKA_CONNECTORS_SUFFIX.length());
    }
}
