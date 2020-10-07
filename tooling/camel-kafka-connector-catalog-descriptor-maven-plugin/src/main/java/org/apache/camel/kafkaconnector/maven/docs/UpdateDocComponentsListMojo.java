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
package org.apache.camel.kafkaconnector.maven.docs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Updates connector.properties file
 */
@Mojo(name = "list-descriptor-files", threadSafe = true)
public class UpdateDocComponentsListMojo extends AbstractMojo {

    /**
     * The maven project.
     */
    @Parameter(property = "project", required = true, readonly = true)
    protected MavenProject project;

    /**
     * The directory for components catalog
     */
    @Parameter(defaultValue = "${project.directory}/../../connectors/")
    protected File connectorsDir;
    

    /**
     * The directory for components catalog
     */
    @Parameter(defaultValue = "${project.directory}/../../camel-kafka-connector-catalog/src/generated/resources/descriptors")
    protected File catalogDescriptorDir;
    
    /**
     * The maven project.
     */
    @Parameter(property = "connectors-project-name", defaultValue = "connectors", readonly = true)
    protected String connectorsProjectName;

    /**
     * Execute goal.
     *
     * @throws MojoExecutionException execution of the main class or one of the
     *             threads it generated failed.
     * @throws MojoFailureException something bad happened...
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (!project.getArtifactId().equals(connectorsProjectName)) {
            getLog().debug("Skipping project " + project.getArtifactId() + " since it is not " + connectorsProjectName + " can be configured with <connectors-project-name> option.");
            return;
        }
        try {
			executeComponentsReadme();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    protected void executeComponentsReadme() throws MojoExecutionException, MojoFailureException, IOException {

        if (connectorsDir != null && connectorsDir.isDirectory()) {
            File[] files = connectorsDir.listFiles();
            if (files != null) {
            	StringBuilder sb = new StringBuilder();
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    if (file.isDirectory()) {
                        File fileSource = FileUtils.getFile(file, "src/generated/descriptors/connector-source.properties");
                        File fileSink = FileUtils.getFile(file, "src/generated/descriptors/connector-sink.properties");
                        if (fileSource.exists()) {
                            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileSource), "UTF-8"))) {
                                String line = null;
                                while ((line = br.readLine()) != null) {
                                    sb.append(line);
                                    sb.append(System.lineSeparator());
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (fileSink.exists()) {
                            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileSink), "UTF-8"))) {
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
                File file = FileUtils.getFile(catalogDescriptorDir, "connectors.properties");
                file.createNewFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(sb.toString());
                }
            }
        }
    }
}
