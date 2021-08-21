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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import org.apache.camel.catalog.CamelCatalog;
import org.apache.camel.catalog.DefaultCamelCatalog;
import org.apache.camel.kafkaconnector.maven.model.KameletModel;
import org.apache.camel.kafkaconnector.maven.utils.MavenUtils;
import org.apache.camel.kafkaconnector.maven.utils.YamlKameletMapper;
import org.apache.camel.kamelets.catalog.KameletsCatalog;
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

    private static final String KAMELETS_DIR = "kamelets";
    private static final String KAMELETS_FILE_SUFFIX = ".kamelet.yaml";

    @Parameter(property = "overridePomFile", required = false, defaultValue = "false")
    protected Boolean overridePomFile;

    //components
    /**
     * The initial pom template file.
     */
    @Parameter(defaultValue = "camel-kafka-connector-template-pom.template")
    protected String initialPomTemplate;

    /**
     * Properties file to configure additional dependencies.
     */
    @Parameter(defaultValue = "camel-kafka-connector-fix-dependencies.properties")
    protected String fixDependenciesProperties;

    /**
     * A comma separated list of column separated GAV to include as dependencies
     * to the generated camel kafka connector. (i.e.
     * groupId:ArtifactId:version,groupId_2:ArtifactId_2:version_2)
     */
    @Parameter(defaultValue = "", readonly = true)
    protected String additionalComponentDependencies;

    //Kamelets
    /**
     * The initial kamelet pom template file.
     */
    @Parameter(defaultValue = "camel-kafka-connector-kamelet-template-pom.template")
    protected String initialKameletPomTemplate;

    /**
     * Properties kamelet file to configure additional dependencies.
     */
    @Parameter(defaultValue = "camel-kafka-connector-kamelet-fix-dependencies.properties")
    protected String fixKameletDependenciesProperties;

    /**
     * A comma separated list of column separated GAV to include as dependencies
     * to the generated camel kafka connector. (i.e.
     * groupId:ArtifactId:version,groupId_2:ArtifactId_2:version_2)
     */
    @Parameter(defaultValue = "", readonly = true)
    private String additionalKameletDependencies;

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
        // load some project version properties
        final Properties properties = new Properties();
        try (InputStream stream = new FileInputStream(rm.getResourceAsFile("project.properties"))) {
            properties.load(stream);
        }

        Map<String, String> kameletsResources = new HashMap<>();
        Set<String> camelComponentsUsedInKamelets = new HashSet<>();
        List<String> resourceNames;
        try (ScanResult scanResult = new ClassGraph().acceptPaths("/" + KAMELETS_DIR + "/").scan()) {
            resourceNames = scanResult.getAllResources().getPaths();
        }
        for (String fileName: resourceNames) {
            String pathInJar = "/" + fileName;
            String kamelet = new BufferedReader(
                    new InputStreamReader(KameletsCatalog.class.getResourceAsStream(pathInJar), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            KameletModel kameletModel = YamlKameletMapper.parseKameletYaml(kamelet);

            // filter all kamelets with type not in {source,sink}
            if ("source".equals(kameletModel.getType()) || "sink".equals(kameletModel.getType())) {
                kameletsResources.put(kameletModel.getName(), kamelet);
                camelComponentsUsedInKamelets.addAll(
                        kameletModel.getDependencies().stream()
                                .filter(d -> d.startsWith("camel:"))
                                .map(d -> d.replaceFirst("camel:", ""))
                                .collect(Collectors.toSet())
                );
            }
            //TODO: add include (filter) / exclude mechanism
            getLog().info("Kamelets found to be used to generate/update a kafka connector: " + kameletsResources.keySet());
        }

        for (String kamelet : kameletsResources.keySet()) {
            executeMojo(
                    plugin(
                            groupId(properties.getProperty("groupId")),
                            artifactId(properties.getProperty("artifactId")),
                            version(properties.getProperty("version"))
                    ),
                    goal("camel-kafka-connector-kamelet-create"),
                    configuration(
                            element(name("name"), kamelet),
                            element(name("initialKameletPomTemplate"), initialKameletPomTemplate),
                            element(name("noticeTemplate"), noticeTemplate),
                            element(name("licenseTemplate"), licenseTemplate),
                            element(name("fixKameletDependenciesProperties"), fixKameletDependenciesProperties),
                            element(name("packageFileTemplate"), packageFileTemplate),
                            element(name("overridePomFile"), overridePomFile.toString()),
                            element(name("connectorsProjectName"), connectorsProjectName)
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
                    goal("camel-kafka-connector-kamelet-update"),
                    configuration(
                            element(name("additionalDependencies"), additionalComponentDependencies),
                            element(name("name"), kamelet),
                            element(name("kameletYaml"), kameletsResources.get(kamelet)),
                            element(name("initialKameletPomTemplate"), initialKameletPomTemplate),
                            element(name("noticeTemplate"), noticeTemplate),
                            element(name("licenseTemplate"), licenseTemplate),
                            element(name("fixKameletDependenciesProperties"), fixKameletDependenciesProperties),
                            element(name("packageFileTemplate"), packageFileTemplate),
                            element(name("connectorsProjectName"), connectorsProjectName)
                    ),
                    executionEnvironment(
                            project,
                            session,
                            pluginManager
                    )
            );
        }

        CamelCatalog cc = new DefaultCamelCatalog();
        List<String> components;
        List<String> filteredComponents;
        if (filter == null || filter.isEmpty()) {
            components = cc.findComponentNames();
        } else {
            Set<String> filterComponentNames = new HashSet<>(Arrays.asList(filter.split(",")));
            components = cc.findComponentNames().stream().filter(componentName -> filterComponentNames.contains(componentName)).collect(Collectors.toList());
        }
        // exclude all components used in a kamelet
        camelComponentsUsedInKamelets.addAll(excludedComponents);
        excludedComponents = camelComponentsUsedInKamelets.stream().collect(Collectors.toList());
        if (excludedComponents == null || excludedComponents.isEmpty()) {
            filteredComponents = components;
        } else {
            filteredComponents = components.stream().filter(component -> !excludedComponents.contains(component)).collect(Collectors.toList());
        }
        if (filter != null && !filter.isEmpty()) {
            getLog().info("Filtered Components that will be used to generate a kafka connector: " + filter);
        }
        if (excludedComponents != null && !excludedComponents.isEmpty()) {
            getLog().info("Excluded Components that won't be used to generate a kafka connector: " + excludedComponents);
        }
        getLog().info("Components found to be used to generate/update a kafka connector: " + filteredComponents);

        for (String component : filteredComponents) {
            String cJson = cc.componentJSonSchema(component);

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
                            element(name("overridePomFile"), overridePomFile.toString()),
                            element(name("connectorsProjectName"), connectorsProjectName)
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
                            element(name("additionalDependencies"), additionalComponentDependencies),
                            element(name("name"), component),
                            element(name("componentJson"), cJson),
                            element(name("initialPomTemplate"), initialPomTemplate),
                            element(name("noticeTemplate"), noticeTemplate),
                            element(name("licenseTemplate"), licenseTemplate),
                            element(name("fixDependenciesProperties"), fixDependenciesProperties),
                            element(name("packageFileTemplate"), packageFileTemplate),
                            element(name("connectorsProjectName"), connectorsProjectName)
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
                List<String> sanitizedGeneratedFromComponentsConnectorsNames = filteredComponents.stream().map(MavenUtils::sanitizeMavenArtifactId).collect(Collectors.toList());
                List<String> sanitizedGeneratedFromKameletsConnectorsNames = kameletsResources.keySet().stream().map(MavenUtils::sanitizeMavenArtifactId).collect(Collectors.toList());
                // retrieve the list of existing camel kafka connectors
                String[] existingConnectorNames = projectDir.list((dir, filename) -> filename.endsWith(KAFKA_CONNECTORS_SUFFIX));
                if (existingConnectorNames != null) {
                    List<String> connectorsToRemove = Stream.of(existingConnectorNames).sorted().filter(filename -> {
                        String componentName = extractComponentName(filename);
                        // set to remove connectors that are not generated from camel components or a kamelet
                        return !sanitizedGeneratedFromComponentsConnectorsNames.contains(componentName) && !sanitizedGeneratedFromKameletsConnectorsNames.contains(componentName);
                    }).collect(Collectors.toList());

                    getLog().info("Connectors previously generated found to be removed: " + connectorsToRemove);

                    for (String component: connectorsToRemove) {

                        executeMojo(
                                plugin(
                                        groupId(properties.getProperty("groupId")),
                                        artifactId(properties.getProperty("artifactId")),
                                        version(properties.getProperty("version"))
                                ),
                                goal("camel-kafka-connector-delete"),
                                configuration(
                                        element(name("name"), component),
                                        element(name("connectorsProjectName"), connectorsProjectName)
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
        // remove starting "camel-"
        String name = connectorName.substring("camel-".length());
        // remove final KAFKA_CONNECTORS_SUFFIX
        return name.substring(0, name.length() - KAFKA_CONNECTORS_SUFFIX.length());
    }
}
