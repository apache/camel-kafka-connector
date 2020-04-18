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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.camel.catalog.CamelCatalog;
import org.apache.camel.catalog.DefaultCamelCatalog;
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
        //TODO: implement an exclusion mechanism
        if (filter == null || filter.isEmpty()) {
            components = cc.findComponentNames();
        } else {
            Set<String> filterComponentNames = new HashSet<>(Arrays.asList(filter.split(",")));
            components = cc.findComponentNames().stream().filter(componentName -> filterComponentNames.contains(componentName)).collect(Collectors.toList());
        }
        getLog().info("Components found to be generated/updated: " + components);

        //TODO: evaluate dataformats to include in each camel kafka connector generated placing them as a comma separated GAV in:
        String additionalDependencies = "";

        final Properties properties = new Properties();
        properties.load(new FileInputStream(rm.getResourceAsFile("project.properties")));
        for (String component : components) {
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
                            element(name("packageFileTemplate"), packageFileTemplate)
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

        //TODO: optionally delete submodules not in catalog
    }
}
