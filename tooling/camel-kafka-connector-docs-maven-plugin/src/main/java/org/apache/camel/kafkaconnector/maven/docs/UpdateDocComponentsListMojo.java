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
import java.util.Collections;

import org.apache.camel.kafkaconnector.maven.docs.dto.CamelKafkaConnectorTableModel;
import org.apache.camel.kafkaconnector.maven.docs.dto.CamelKafkaConnectorTableOptionModel;
import org.apache.camel.maven.packaging.MvelHelper;
import org.apache.camel.tooling.util.Strings;
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
 * Generates the table of connectors in the connectors index page (by default
 * docs/modules/ROOT/pages/reference/index.adoc) and the list of connectors in the nav file (by default
 * docs/modules/ROOT/nav.adoc).
 */
@Mojo(name = "update-doc-connectors-list", threadSafe = true)
public class UpdateDocComponentsListMojo extends AbstractMojo {
    // The Kafka Connect ServiceLoader manifests (KIP-898) that every connector module, generated or hand-maintained,
    // ships for each type of connector it provides. They are only looked up in the module sources, so build output
    // such as a stale target/ directory cannot change the list of connectors.
    private static final String SINK_CONNECTOR_SERVICE_FILE = "src/main/resources/META-INF/services/org.apache.kafka.connect.sink.SinkConnector";
    private static final String SOURCE_CONNECTOR_SERVICE_FILE = "src/main/resources/META-INF/services/org.apache.kafka.connect.source.SourceConnector";

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
     * The Apache Repository
     */
    @Parameter(defaultValue = "https://repo.maven.apache.org/maven2/org/apache/camel/kafkaconnector/")
    protected String repositoryPath;
    
    
    /**
     * The Last Released version
     */
    @Parameter
    protected String lastReleasedVersion;

    /**
     * The prefix of the links to the connector reference pages.
     */
    @Parameter(defaultValue = "xref:reference/connectors/")
    protected String xrefConnectorLinkPrefix;

    /**
     * The suffix of the links to the sink connector reference pages, appended to the connector module name without
     * its "kafka-connector" suffix.
     */
    @Parameter(defaultValue = "kafka-sink-connector.adoc[Sink Docs]")
    protected String sinkConnectorLinkSuffix;

    /**
     * The suffix of the links to the source connector reference pages, appended to the connector module name without
     * its "kafka-connector" suffix.
     */
    @Parameter(defaultValue = "kafka-source-connector.adoc[Source Docs]")
    protected String sourceConnectorLinkSuffix;

    /**
     * The docs module directory holding the connectors index page and the nav file, absolute or relative to
     * projectBaseDir.
     */
    @Parameter(defaultValue = "docs/modules/ROOT/")
    protected String docsModuleDir;

    /**
     * The page with the table of connectors between the "// kafka-connectors list: START" and
     * "// kafka-connectors list: END" markers, absolute or relative to docsModuleDir.
     */
    @Parameter(defaultValue = "pages/reference/index.adoc")
    protected String connectorsIndexFile;

    /**
     * The nav file with the list of connectors between the "// connectors: START" and "// connectors: END" markers,
     * absolute or relative to docsModuleDir.
     */
    @Parameter(defaultValue = "nav.adoc")
    protected String navFile;

    /**
     * The maven project.
     */
    @Parameter(property = "connectors-project-name", defaultValue = "connectors", readonly = true)
    protected String connectorsProjectName;

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
        if (!project.getArtifactId().equals(connectorsProjectName)) {
            getLog().debug("Skipping project " + project.getArtifactId() + " since it is not " + connectorsProjectName + " can be configured with <connectors-project-name> option.");
            return;
        }
        executeComponentsReadme();
    }

    protected void executeComponentsReadme() throws MojoExecutionException, MojoFailureException {
        CamelKafkaConnectorTableModel tableModel = new CamelKafkaConnectorTableModel();

        if (connectorsDir != null && connectorsDir.isDirectory()) {
            tableModel.setOptions(findConnectors());
        }
        File docFolderWebsite = resolve(projectBaseDir, docsModuleDir);
        File docFileWebsite = resolve(docFolderWebsite, connectorsIndexFile);
        String changed = templateConnnectorsTable(tableModel);
        boolean updated = updateConnectorsTable(docFileWebsite, changed);
        if (updated) {
            getLog().info("Updated connectors table file: " + docFileWebsite);
        } else {
            getLog().debug("No changes to connectors table file: " + docFileWebsite);
        }
        File navWebsite = resolve(docFolderWebsite, navFile);
        boolean navUpdated = updateNav(navWebsite, tableModel);
        if (navUpdated) {
            getLog().info("Updated nav file: " + navWebsite);
        } else {
            getLog().debug("No changes to nav file: " + navWebsite);
        }
    }

    /**
     * Lists the connector modules of the connectors directory, sorted by name. A module provides a sink and/or a
     * source connector when its sources contain the matching Kafka Connect ServiceLoader manifest.
     */
    ArrayList<CamelKafkaConnectorTableOptionModel> findConnectors() throws MojoExecutionException {
        File[] modules = connectorsDir.listFiles(File::isDirectory);
        if (modules == null) {
            throw new MojoExecutionException("Cannot list the connector modules in " + connectorsDir);
        }
        ArrayList<CamelKafkaConnectorTableOptionModel> options = new ArrayList<>();
        for (File module : modules) {
            boolean sink = new File(module, SINK_CONNECTOR_SERVICE_FILE).isFile();
            boolean source = new File(module, SOURCE_CONNECTOR_SERVICE_FILE).isFile();
            if (!sink && !source) {
                continue;
            }
            CamelKafkaConnectorTableOptionModel singleConnector = new CamelKafkaConnectorTableOptionModel();
            singleConnector.setName(module.getName());
            String docsName = connectorDocsName(module.getName());
            if (sink) {
                singleConnector.setSink(true);
                singleConnector.setDocsSink(xrefConnectorLinkPrefix + docsName + sinkConnectorLinkSuffix);
            }
            if (source) {
                singleConnector.setSource(true);
                singleConnector.setDocsSource(xrefConnectorLinkPrefix + docsName + sourceConnectorLinkSuffix);
            }
            String downloadLinkTar = repositoryPath + singleConnector.getName() + "/" + lastReleasedVersion + "/" + singleConnector.getName() + "-" + lastReleasedVersion + "-package.tar.gz[Download]";
            singleConnector.setDownloadLinkTar(downloadLinkTar);
            options.add(singleConnector);
        }
        options.sort((model1, model2) -> {
            String name1 = model1.getName();
            String name2 = model2.getName();
            int res = String.CASE_INSENSITIVE_ORDER.compare(name1, name2);
            return (res != 0) ? res : name1.compareTo(name2);
        });
        return options;
    }

    /**
     * Returns the name of the reference pages of a connector module without their sink/source suffix. Connectors
     * generated from a Camel component whose scheme is not a valid artifact id (e.g. coap+tcp) keep the scheme there.
     */
    private static String connectorDocsName(String moduleName) {
        String connectorFinal = StringUtils.removeEnd(moduleName, "kafka-connector");
        if (connectorFinal.equalsIgnoreCase("camel-coap-tcp-")) {
            return "camel-coap+tcp-";
        } else if (connectorFinal.equalsIgnoreCase("camel-coaps-tcp-")) {
            return "camel-coaps+tcp-";
        }
        return connectorFinal;
    }

    private static File resolve(File baseDir, String path) {
        File file = new File(path);
        return file.isAbsolute() ? file : new File(baseDir, path);
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

    private boolean updateNav(File file, CamelKafkaConnectorTableModel model) throws MojoExecutionException {
        String changed = null;
        try {
            String template = loadText(UpdateDocComponentsListMojo.class.getClassLoader().getResourceAsStream("nav.mvel"));
            changed = (String)TemplateRuntime.eval(template, model, Collections.singletonMap("util", MvelHelper.INSTANCE));
        } catch (Exception e) {
            throw new MojoExecutionException("Error processing mvel template. Reason: " + e, e);
        }

        try {
            String text = loadText(file);

            String existing = Strings.between(text, "// connectors: START", "// connectors: END");
            if (existing != null) {
                // remove leading line breaks etc
                existing = existing.trim();
                changed = changed.trim();
                if (existing.equals(changed)) {
                    return false;
                } else {
                    String before = Strings.before(text, "// connectors: START");
                    String after = Strings.after(text, "// connectors: END");
                    text = before + "// connectors: START\n" + changed + "\n// connectors: END" + after;
                    writeText(file, text);
                    return true;
                }
            } else {
                getLog().warn("Cannot find markers in file " + file);
                getLog().warn("Add the following markers");
                getLog().warn("\t// connectors: START");
                getLog().warn("\t// connectors: END");
                return false;
            }
        } catch (Exception e) {
            throw new MojoExecutionException("Error reading file " + file + " Reason: " + e, e);
        }
    }
}
