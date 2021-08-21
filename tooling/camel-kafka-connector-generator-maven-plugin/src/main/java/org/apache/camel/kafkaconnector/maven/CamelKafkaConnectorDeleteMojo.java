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
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import static org.apache.camel.kafkaconnector.maven.utils.MavenUtils.concat;
import static org.apache.camel.kafkaconnector.maven.utils.MavenUtils.sanitizeMavenArtifactId;

@Mojo(name = "camel-kafka-connector-delete", threadSafe = true,
        defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class CamelKafkaConnectorDeleteMojo extends AbstractCamelComponentKafkaConnectorMojo {

    @Parameter(property = "name", required = true)
    protected String name;

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
            deleteConnector();
        } catch (Exception e) {
            throw new MojoFailureException("Fail to delete connector " + name, e);
        }
    }

    private void deleteConnector() throws MojoFailureException, IOException {
        getLog().info("Deleting camel kafka connector for " + name);
        String sanitizedName = sanitizeMavenArtifactId(name);
        File directory = new File(projectDir, "camel-" + sanitizedName + KAFKA_CONNECTORS_SUFFIX);
        if (!directory.exists()) {
            throw new MojoFailureException("Camel kafka connector does not exist: " + name);
        }
        FileUtils.deleteDirectory(directory);
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
        lines = concat(lines.subList(0, modulesStart).stream(),
                lines.subList(modulesStart, modulesEnd).stream()
                        .filter(s -> !s.contains("<module>camel-" + sanitizedName + KAFKA_CONNECTORS_SUFFIX + "</module>")),
                lines.subList(modulesEnd, lines.size()).stream())
                .collect(Collectors.toList());
        Files.write(parent, lines);
    }
}
