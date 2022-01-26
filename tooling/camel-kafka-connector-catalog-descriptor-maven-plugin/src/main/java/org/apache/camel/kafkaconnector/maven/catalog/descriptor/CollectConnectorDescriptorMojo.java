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
package org.apache.camel.kafkaconnector.maven.catalog.descriptor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Updates connector.properties file
 */
@Mojo(name = "update-descriptor-files", threadSafe = true)
public class CollectConnectorDescriptorMojo extends AbstractMojo {

    /**
     * The maven project.
     */
    @Parameter(property = "project", required = true, readonly = true)
    protected MavenProject project;

    /**
     * The directory for connectors
     */
    @Parameter(defaultValue = "${project.directory}/../../connectors/")
    protected File connectorsDir;

    /**
     * The directory for catalog descriptors
     */
    @Parameter(defaultValue = "${project.directory}/../src/generated/resources/descriptors")
    protected File catalogDescriptorDir;

    /**
     * Execute goal.
     */
    @Override
    public void execute() {
        try {
            executeComponentsReadme();
        } catch (IOException e) {
            getLog().error("An error occurred while running camel-kakfa-connector-descriptor-maven-plugin:update-descriptor-files", e);
        }
    }

    protected void executeComponentsReadme() throws IOException {
        getLog().info("About to scan all projects in" + connectorsDir + " in order to gather descriptors files");
        if (connectorsDir != null && connectorsDir.isDirectory()) {
            File[] files = connectorsDir.listFiles();
            getLog().info("Found " + (files != null ? String.valueOf(files.length) : "0") + "directories:");
            if (files != null) {
                Arrays.sort(files);
                Stream.of(files).filter(f -> f.isDirectory()).forEach(f -> getLog().info(f.getPath()));
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    if (file.isDirectory()) {
                        fillStringBuilderWithContentOf(sb, file, "src/generated/descriptors/connector-source.properties");
                        fillStringBuilderWithContentOf(sb, file, "src/generated/descriptors/connector-sink.properties");
                    }
                }
                File file = FileUtils.getFile(catalogDescriptorDir, "connectors.properties");
                getLog().info("About to write descriptor to: " + catalogDescriptorDir);
                if (!file.exists()) {
                    // Create the structure
                    File parent = file.getParentFile();
                    parent.mkdirs();
                }
                file.createNewFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(sb.toString());
                }
            }
        }
    }

    private void fillStringBuilderWithContentOf(StringBuilder sb, File file, String path) {
        File fileToRead = FileUtils.getFile(file, path);
        if (fileToRead.exists()) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileToRead), StandardCharsets.UTF_8))) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
