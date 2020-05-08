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

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.camel.kafkaconnector.maven.docs.dto.CamelKafkaConnectorTableModel;
import org.apache.camel.kafkaconnector.maven.docs.dto.CamelKafkaConnectorTableOptionModel;
import org.apache.camel.maven.packaging.MvelHelper;
import org.apache.camel.tooling.util.Strings;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;
import org.mvel2.templates.TemplateRuntime;

import static org.apache.camel.tooling.util.PackageHelper.loadText;
import static org.apache.camel.tooling.util.PackageHelper.writeText;

/**
 * Updates the documentation in: - docs/modules/ROOT/pages/connectors.adoc
 */
@Mojo(name = "update-doc-connectors-list", threadSafe = true)
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
     * The project directory
     */
    @Parameter(defaultValue = "${basedir}/../")
    protected File projectBaseDir;

    /**
     * Maven ProjectHelper.
     */
    @Component
    private MavenProjectHelper projectHelper;

    /**
     * Execute goal.
     *
     * @throws MojoExecutionException execution of the main class or one of the
     *             threads it generated failed.
     * @throws MojoFailureException something bad happened...
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        executeComponentsReadme();
    }

    protected void executeComponentsReadme() throws MojoExecutionException, MojoFailureException {
        CamelKafkaConnectorTableModel tableModel = new CamelKafkaConnectorTableModel();
        List<CamelKafkaConnectorTableOptionModel> options = new ArrayList<CamelKafkaConnectorTableOptionModel>();

        if (connectorsDir != null && connectorsDir.isDirectory()) {
            File[] files = connectorsDir.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    if (file.isDirectory()) {
                        Collection sinkConnector = FileUtils.listFiles(file, new RegexFileFilter(".*SinkTask.*"), DirectoryFileFilter.DIRECTORY);
                        Collection sourceConnector = FileUtils.listFiles(file, new RegexFileFilter(".*SourceTask.*"), DirectoryFileFilter.DIRECTORY);
                        if (sinkConnector.size() > 0 || sourceConnector.size() > 0) {
                            CamelKafkaConnectorTableOptionModel singleConnector = new CamelKafkaConnectorTableOptionModel();
                            singleConnector.setName(file.getName());
                            if (sinkConnector.size() > 0) {
                                singleConnector.setSink(true);
                                String connectorFinal = StringUtils.removeEnd(file.getName(), "kafka-connector");
                                String finalLink = "xref:connectors/" + connectorFinal + "kafka-sink-connector.adoc[Sink Docs]";
                                singleConnector.setDocsSink(finalLink);
                            }
                            if (sourceConnector.size() > 0) {
                                singleConnector.setSource(true);
                                String connectorFinal = StringUtils.removeEnd(file.getName(), "kafka-connector");
                                String finalLink = "xref:connectors/" + connectorFinal + "kafka-source-connector.adoc[Source Docs]";
                                singleConnector.setDocsSource(finalLink);
                            }
                            options.add(singleConnector);
                        }
                    }
                }
                tableModel.setOptions(options);
            }
        }
        File docFolderWebsite = new File(projectBaseDir, "docs/modules/ROOT/pages/");
        File docFileWebsite = new File(docFolderWebsite, "connectors.adoc");
        String changed = templateConnnectorsTable(tableModel);
        boolean updated = updateConnectorsTable(docFileWebsite, changed);
        if (updated) {
            getLog().info("Updated connectors table file: " + docFileWebsite);
        } else {
            getLog().debug("No changes to connectors table file: " + docFileWebsite);
        }
    }

    private String templateConnnectorsTable(CamelKafkaConnectorTableModel model) throws MojoExecutionException {

        try {
            String template = null;
            template = loadText(UpdateDocComponentsListMojo.class.getClassLoader().getResourceAsStream("connectors.mvel"));
            String out = (String)TemplateRuntime.eval(template, model, Collections.singletonMap("util", MvelHelper.INSTANCE));
            return out;
        } catch (Exception e) {
            throw new MojoExecutionException("Error processing mvel template. Reason: " + e, e);
        }
    }

    private boolean updateConnectorsTable(File file, String changed) throws MojoExecutionException {
        try {
            if (!file.exists()) {
                // include markers for new files
                changed = "// kafka-connectors list: START\n" + changed + "\n// kafka-connectors list: END\n";
                writeText(file, changed);
                return true;
            }

            String text = loadText(new FileInputStream(file));

            String existing = Strings.between(text, "// kafka-connectors list: START", "// kafka-connectors list: END");
            if (existing != null) {
                // remove leading line breaks etc
                existing = existing.trim();
                changed = changed.trim();
                if (existing.equals(changed)) {
                    return false;
                } else {
                    String before = Strings.before(text, "// kafka-connectors list: START");
                    String after = Strings.after(text, "// kafka-connectors list: END");
                    text = before + "// kafka-connectors list: START\n" + changed + "\n// kafka-connectors list: END" + after;
                    writeText(file, text);
                    return true;
                }
            } else {
                getLog().warn("Cannot find markers in file " + file);
                getLog().warn("Add the following markers");
                getLog().warn("\t// kafka-connectors list: START");
                getLog().warn("\t// kafka-connectors list: END");
                return false;
            }
        } catch (Exception e) {
            throw new MojoExecutionException("Error reading file " + file + " Reason: " + e, e);
        }
    }
}
