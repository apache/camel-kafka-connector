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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import freemarker.template.Template;
import org.apache.camel.kafkaconnector.maven.utils.MavenUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.codehaus.plexus.resource.loader.FileResourceCreationException;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;

import static org.apache.camel.kafkaconnector.maven.utils.MavenUtils.writeFileIfChanged;
import static org.apache.camel.kafkaconnector.maven.utils.MavenUtils.writeXmlFormatted;

/**
 * Generate Camel Kafka Connector for the component
 */
@Mojo(name = "camel-kafka-connector-update", threadSafe = true,
        requiresDependencyCollection = ResolutionScope.COMPILE_PLUS_RUNTIME,
        requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME,
        defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class CamelKafkaConnectorUpdateMojo extends AbstractCamelKafkaConnectorMojo {

    private static final String GENERATED_SECTION_START = "START OF GENERATED CODE";
    private static final String GENERATED_SECTION_START_COMMENT = "<!--" + GENERATED_SECTION_START + "-->";
    private static final String GENERATED_SECTION_END = "END OF GENERATED CODE";
    private static final String GENERATED_SECTION_END_COMMENT = "<!--" + GENERATED_SECTION_END + "-->";

    private static final String EXCLUDE_DEPENDENCY_PROPERTY_PREFIX = "exclude_";
    private static final String XML_FEATURES_DISALLOW_DOCTYPE_DECL = "http://apache.org/xml/features/disallow-doctype-decl";

    @Parameter(property = "name", required = true)
    protected String name;

    /**
     * The maven session.
     */
    @Parameter(defaultValue = "${session}", readonly = true)
    private MavenSession session;

    /**
     * A comma separated list of column separated GAV to include as dependencies to the generated camel kafka connector.
     * (i.e. groupId:ArtifactId:version,groupId_2:ArtifactId_2:version_2)
     */
    @Parameter(defaultValue = "", readonly = true)
    private String additionalDependencies;

//    @Component
//    private ProjectDependenciesResolver projectDependenciesResolver;
//
//    @Component
//    private ProjectBuilder projectBuilder;

    @Override
    protected String getMainDepArtifactId() {
        return "camel-" + name;
    }

    @Override
    protected void executeAll() throws MojoFailureException {
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
            updateConnector();
        } catch (Exception e) {
            throw new MojoFailureException("Fail to update connector " + name, e);
        }
    }

    private void updateConnector() throws Exception {
        // create the starter directory
        File connectorDir = new File(projectDir, "camel-" + name + KAFKA_CONNECTORS_SUFFIX);
        if (!connectorDir.exists() || !connectorDir.isDirectory()) {
            getLog().info("Connector " + name + " can not be updated since directory " + connectorDir.getAbsolutePath() + " dose not exist.");
            throw new MojoFailureException("Directory already exists: " + connectorDir);
        }

        // create the base pom.xml
        Document pom = createBasePom(connectorDir);

        // Apply changes to the starter pom
        fixExcludedDependencies(pom);
        fixAdditionalDependencies(pom, additionalDependencies);
        fixAdditionalRepositories(pom);

        // Write the starter pom
        File pomFile = new File(connectorDir, "pom.xml");
        writeXmlFormatted(pom, pomFile, getLog());

        // write package
        Document pkg = createPackageFile();
        File pkgFile = new File(connectorDir, "src/main/assembly/package.xml");
        writeXmlFormatted(pkg, pkgFile, getLog());

        // write LICENSE, USAGE
        writeStaticFiles(connectorDir);
    }

    private void fixExcludedDependencies(Document pom) throws Exception {
        //add dependencies to be excluded form camel component dependency
        Set<String> loggingImpl = new HashSet<>();

        // excluded dependencies
        Set<String> configExclusions = new HashSet<>();
        Properties properties = new Properties();
        properties.load(new FileInputStream(rm.getResourceAsFile(fixDependenciesProperties)));
        String artExcl = properties.getProperty(EXCLUDE_DEPENDENCY_PROPERTY_PREFIX + getMainDepArtifactId());
        getLog().debug("Configured exclusions: " + artExcl);
        if (artExcl != null && artExcl.trim().length() > 0) {
            for (String dep : artExcl.split(",")) {
                getLog().debug("Adding configured exclusion: " + dep);
                configExclusions.add(dep);
            }
        }

        Set<String> libsToRemove = new TreeSet<>();
        libsToRemove.addAll(loggingImpl);
        libsToRemove.addAll(configExclusions);
//        libsToRemove = filterIncludedArtifacts(libsToRemove);

        if (libsToRemove.size() > 0) {
            getLog().info("Camel-kafka-connector: the following dependencies will be removed from the connector: " + libsToRemove);
            MavenUtils.addExclusionsToDependency(pom, getMainDepArtifactId(), libsToRemove, GENERATED_SECTION_START, GENERATED_SECTION_END);
        }
    }

    private void fixAdditionalDependencies(Document pom, String additionalDependencies) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream(rm.getResourceAsFile(fixDependenciesProperties)));

        Set<String> deps = new TreeSet<>();
        deps.addAll(MavenUtils.csvToSet(properties.getProperty(getMainDepArtifactId())));
        deps.addAll(MavenUtils.csvToSet(additionalDependencies));

        Set<String> globalProps = MavenUtils.csvToSet(properties.getProperty("global"));
        boolean inGlobal = false;
        for (String gp : globalProps) {
            String camelGav = getMainDepGroupId() + ":" + getMainDepArtifactId();
            String camelSpringBootGav = project.getGroupId() + ":" + project.getArtifactId();
            if (gp.equals(camelGav) || gp.equals(camelSpringBootGav)) {
                inGlobal = true;
                break;
            }
        }

        if (!inGlobal) {
            // add global properties for all modules not in global properties
            deps.addAll(globalProps);
        }

        if (deps.size() > 0) {
            getLog().debug("The following dependencies will be added to the starter: " + deps);
            MavenUtils.addDependencies(pom, deps, GENERATED_SECTION_START, GENERATED_SECTION_END);
        }
    }

    private void fixAdditionalRepositories(Document pom) throws Exception {
        if (project.getFile() != null) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, Boolean.TRUE);
            dbf.setFeature(XML_FEATURES_DISALLOW_DOCTYPE_DECL, true);
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document originalPom = builder.parse(project.getFile());

            XPath xpath = XPathFactory.newInstance().newXPath();
            Node repositories = (Node) xpath.compile("/project/repositories").evaluate(originalPom, XPathConstants.NODE);
            if (repositories != null) {
                pom.getDocumentElement().appendChild(pom.createComment(GENERATED_SECTION_START));
                pom.getDocumentElement().appendChild(pom.importNode(repositories, true));
                pom.getDocumentElement().appendChild(pom.createComment(GENERATED_SECTION_END));
            }
        } else {
            getLog().warn("Cannot access the project pom file to retrieve repositories");
        }
    }

//TODO: reneable if needed.
//  private Set<String> filterIncludedArtifacts(Set<String> artifacts) {
//
//        Set<Artifact> dependencies;
//        try {
//            Artifact artifact = project.getArtifactMap().get(getMainDepGroupId() + ":" + getMainDepArtifactId());
//            ProjectBuildingResult result = projectBuilder.build(artifact, project.getProjectBuildingRequest());
//            MavenProject prj = result.getProject();
//            prj.setRemoteArtifactRepositories(project.getRemoteArtifactRepositories());
//            dependencies = projectDependenciesResolver.resolve(prj, Collections.singleton(Artifact.SCOPE_COMPILE), session);
//        } catch (Exception e) {
//            throw new RuntimeException("Unable to build project dependency tree", e);
//        }
//
//        Set<String> included = new TreeSet<>();
//        dependencies.stream()
//                .filter(a -> !Artifact.SCOPE_TEST.equals(a.getScope()))
//                .map(a -> a.getGroupId() + ":" + a.getArtifactId())
//                .forEach(included::add);
//        included.retainAll(artifacts);
//
//        return included;
//    }

    private Document createPackageFile() throws ResourceNotFoundException, FileResourceCreationException, IOException {
        getLog().info("Creating a new package.xml for the connector.");
        Template packageTemplate = MavenUtils.getTemplate(rm.getResourceAsFile(packageFileTemplate));
        Map<String, String> props = new HashMap<>();
        try {
            return MavenUtils.createCrateXmlDocumentFromTemplate(packageTemplate, props);
        } catch (Exception e) {
            getLog().error("Cannot create package.xml file from Template: " + packageTemplate + " with properties: " + props, e);
        }
        return null;
    }

    private Document createBasePom(File connectorDir) {
        try {
            File pomFile = new File(connectorDir, "pom.xml");
            if (pomFile.exists()) {
                try (InputStream in = new FileInputStream(pomFile)) {
                    String content = IOUtils.toString(in, StandardCharsets.UTF_8);
                    boolean editablePom = content.contains(GENERATED_SECTION_START_COMMENT);
                    if (editablePom) {
                        content = MavenUtils.removeGeneratedSections(content, GENERATED_SECTION_START_COMMENT, GENERATED_SECTION_END_COMMENT, 10);
                        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

                        Document pom;
                        try (InputStream contentIn = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8))) {
                            pom = builder.parse(contentIn);
                        }

                        getLog().debug("Reusing the existing pom.xml for the starter");
                        return pom;
                    } else {
                        getLog().error("Cannot use the existing pom.xml file since it is not editable. It does not contain " + GENERATED_SECTION_START_COMMENT);
                    }
                }
            } else {
                getLog().info("Creating a new pom.xml for the connector from scratch.");
                Template pomTemplate = MavenUtils.getTemplate(rm.getResourceAsFile(initialPomTemplate));
                Map<String, String> props = new HashMap<>();
                props.put("version", project.getVersion());
                props.put("componentId", getComponentId());
                props.put("componentName", name);
                props.put("componentDescription", getMainDepArtifactId());
                try {
                    return MavenUtils.createCrateXmlDocumentFromTemplate(pomTemplate, props);
                } catch (Exception e) {
                    getLog().error("Cannot create pom.xml file from Template: " + pomTemplate + " with properties: " + props, e);
                }
            }
        } catch (Exception e) {
            getLog().error("Cannot use the existing pom.xml file or create a new one.", e);
        }

        return null;
    }

    private void writeStaticFiles(File connectorDir) throws IOException, ResourceNotFoundException, FileResourceCreationException {
        String notice;
        String license;
        try (InputStream isNotice = new FileInputStream(rm.getResourceAsFile(noticeTemplate));
             InputStream isLicense = new FileInputStream(rm.getResourceAsFile(licenseTemplate))) {
            notice = IOUtils.toString(isNotice, StandardCharsets.UTF_8);
            license = IOUtils.toString(isLicense, StandardCharsets.UTF_8);
        }

        writeFileIfChanged(notice, new File(connectorDir, "src/main/resources/META-INF/NOTICE.txt"), getLog());
        writeFileIfChanged(license, new File(connectorDir, "src/main/resources/META-INF/LICENSE.txt"), getLog());
    }

    private String getComponentId() {
        String componentName = getMainDepArtifactId();
        String componentId = componentName.replace("camel-", "");
        return componentId;
    }
}
