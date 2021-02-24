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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.w3c.dom.Document;

import freemarker.template.Template;
import org.apache.camel.kafkaconnector.maven.utils.MavenUtils;
import org.apache.camel.tooling.model.ComponentModel;
import org.apache.camel.tooling.model.JsonMapper;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import static org.apache.camel.kafkaconnector.maven.utils.MavenUtils.sanitizeMavenArtifactId;
import static org.apache.camel.kafkaconnector.maven.utils.MavenUtils.writeXmlFormatted;

@Mojo(name = "camel-kafka-connector-create", threadSafe = true,
        defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class CamelKafkaConnectorCreateMojo extends AbstractCamelKafkaConnectorMojo {

    @Parameter(property = "name", required = true)
    protected String name;

    @Parameter(property = "componentJson", required = true)
    protected String componentJson;

    @Parameter(property = "overridePomFile", required = false, defaultValue = "false")
    protected Boolean overridePomFile;

    @Override
    protected String getMainDepArtifactId() {
        return "camel-" + name;
    }

    @Override
    public void executeAll() throws MojoFailureException {
        if (name == null || name.isEmpty()) {
            throw new MojoFailureException("Connector name must be specified as the parameter");
        }
        if (name.startsWith("camel-")) {
            name = name.substring("camel-".length());
        }
        if (name.endsWith(KAFKA_CONNECTORS_SUFFIX)) {
            name = name.substring(0, name.length() - KAFKA_CONNECTORS_SUFFIX.length());
        }
        try {
            createConnector();
        } catch (Exception e) {
            throw new MojoFailureException("Fail to create connector " + name, e);
        }
    }

    private void createConnector() throws Exception {
        getLog().info("Creating camel kafka connector for " + name);
        String sanitizedName = sanitizeMavenArtifactId(name);
        //check if the connector is already created
        File directory = new File(projectDir, "camel-" + sanitizedName + KAFKA_CONNECTORS_SUFFIX);
        if (directory.exists()) {
            if (directory.isDirectory()) {
                getLog().info("Connector " + name + " already exists since a sub directory named: " + directory.getName() + " already exists.");
                if (Boolean.TRUE.equals(overridePomFile)) {
                    getLog().info("Since overridePomFile is " + overridePomFile + " regenerating pom file for connector:" + name + " in directory named: " + directory.getName());
                    generateAndWritePom(sanitizedName, directory);
                    addConnectorAsProjectSubmodule(sanitizedName);
                }
                return;
            } else {
                throw new MojoFailureException("Can not create directory as a file already exists: " + directory);
            }
        }
        if (!directory.mkdirs()) {
            throw new MojoFailureException("Unable to create directory: " + directory);
        }
        generateAndWritePom(sanitizedName, directory);
        addConnectorAsProjectSubmodule(sanitizedName);
    }

    private void generateAndWritePom(String sanitizedName, File directory) throws Exception {
        //create initial connector pom
        ComponentModel cm = JsonMapper.generateComponentModel(componentJson);
        getLog().info("Creating a new pom.xml for the connector from scratch");
        Template pomTemplate = MavenUtils.getTemplate(rm.getResourceAsFile(initialPomTemplate));
        Map<String, String> props = new HashMap<>();
        props.put("version", project.getVersion());
        props.put("dependencyId", cm.getArtifactId());
        props.put("dependencyGroup", cm.getGroupId());
        props.put("componentName", name);
        props.put("componentSanitizedName", sanitizedName);
        props.put("componentDescription", name);
        try {
            Document pom = MavenUtils.createCrateXmlDocumentFromTemplate(pomTemplate, props);
            // Write the starter pom
            File pomFile = new File(directory, "pom.xml");
            writeXmlFormatted(pom, pomFile, getLog());
        } catch (Exception e) {
            getLog().error("Cannot create pom.xml file from Template: " + pomTemplate + " with properties: " + props, e);
            throw e;
        }
    }

    private void addConnectorAsProjectSubmodule(String sanitizedName) throws IOException {
        //add connector as a sub module
        Path parent = new File(projectDir, "pom.xml").toPath();
        List<String> lines = Files.readAllLines(parent);
        int modulesStart = -1;
        int modulesEnd = -1;
        for (int i = 0; i < lines.size(); i++) {
            String s = lines.get(i);
            if (s.contains("<modules>")) {
                modulesStart = i + 1;
            } else if (s.contains("</modules>")) {
                modulesEnd = i;
            }
        }
        lines = MavenUtils.concat(lines.subList(0, modulesStart).stream(),
                Stream.concat(lines.subList(modulesStart, modulesEnd).stream(),
                        Stream.of("        <module>camel-" + sanitizedName + KAFKA_CONNECTORS_SUFFIX + "</module>"))
                        .sorted().distinct(),
                lines.subList(modulesEnd, lines.size()).stream())
                .collect(Collectors.toList());
        Files.write(parent, lines);
    }
}
