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
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

import javax.annotation.Generated;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.xml.sax.SAXException;

import freemarker.template.Template;
import org.apache.camel.kafkaconnector.maven.utils.MavenUtils;
import org.apache.camel.tooling.model.BaseOptionModel;
import org.apache.camel.tooling.model.ComponentModel;
import org.apache.camel.tooling.model.JsonMapper;
import org.apache.camel.tooling.util.srcgen.JavaClass;
import org.apache.camel.tooling.util.srcgen.Method;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.codehaus.plexus.resource.loader.FileResourceCreationException;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;


import static org.apache.camel.kafkaconnector.maven.utils.MavenUtils.sanitizeMavenArtifactId;
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
    private static final String ADDITIONAL_COMMON_PROPERTIES_PROPERTY_PREFIX = "additional_properties_";
    private static final String XML_FEATURES_DISALLOW_DOCTYPE_DECL = "http://apache.org/xml/features/disallow-doctype-decl";


    private static final Map<String, Class<?>> PRIMITIVEMAP;
    private static final Map<String, String> CONFIGDEFMAP;

    private static final Map<String, String> RESERVEDWORDSUBSTITUTIONMAP;

    static {
        PRIMITIVEMAP = new HashMap<>();
        PRIMITIVEMAP.put("boolean", Boolean.class);
        PRIMITIVEMAP.put("long", Long.class);
        PRIMITIVEMAP.put("int", Integer.class);
        PRIMITIVEMAP.put("short", Short.class);
        PRIMITIVEMAP.put("double", Double.class);
        PRIMITIVEMAP.put("float", Float.class);

        CONFIGDEFMAP = new HashMap<>();
        CONFIGDEFMAP.put("boolean", "ConfigDef.Type.BOOLEAN");
        CONFIGDEFMAP.put("long", "ConfigDef.Type.LONG");
        CONFIGDEFMAP.put("int", "ConfigDef.Type.INT");
        CONFIGDEFMAP.put("short", "ConfigDef.Type.SHORT");
        CONFIGDEFMAP.put("double", "ConfigDef.Type.DOUBLE");
        CONFIGDEFMAP.put("float", "ConfigDef.Type.DOUBLE");

        RESERVEDWORDSUBSTITUTIONMAP = new HashMap<>();
        RESERVEDWORDSUBSTITUTIONMAP.put("class", "clazz");
    }

    protected DynamicClassLoader projectClassLoader;

    @Parameter(property = "name", required = true)
    protected String name;

    @Parameter(property = "componentJson", required = true)
    protected String componentJson;

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

    protected DynamicClassLoader getProjectClassLoader() {
        if (projectClassLoader == null) {
            final List<String> classpathElements;
            try {
                classpathElements = project.getTestClasspathElements();
            } catch (org.apache.maven.artifact.DependencyResolutionRequiredException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            projectClassLoader = DynamicClassLoader.createDynamicClassLoader(classpathElements);
        }
        return projectClassLoader;
    }

    private void updateConnector() throws Exception {
        String sanitizedName = sanitizeMavenArtifactId(name);
        // create the starter directory
        File connectorDir = new File(projectDir, "camel-" + sanitizedName + KAFKA_CONNECTORS_SUFFIX);
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

        // generate classes
        ComponentModel model = JsonMapper.generateComponentModel(componentJson);
        if (model.isConsumerOnly()) {
            createClasses(sanitizedName, connectorDir, model, ConnectorType.SOURCE);
        } else if (model.isProducerOnly()) {
            createClasses(sanitizedName, connectorDir, model, ConnectorType.SINK);
        } else {
            createClasses(sanitizedName, connectorDir, model, ConnectorType.SOURCE);
            createClasses(sanitizedName, connectorDir, model, ConnectorType.SINK);
        }
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
            String camelKafkaConnectorGav = project.getGroupId() + ":" + project.getArtifactId();
            if (gp.equals(camelGav) || gp.equals(camelKafkaConnectorGav)) {
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

    private Document createBasePom(File connectorDir) throws IOException, SAXException, ParserConfigurationException {
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
                    throw new UnsupportedOperationException("Cannot use the existing pom.xml file since it is not editable. It does not contain " + GENERATED_SECTION_START_COMMENT);
                }
            }
        } else {
            getLog().error("The pom.xml file is not present, please use camel-kafka-connector-create first.");
            throw new UnsupportedOperationException("The pom.xml file is not present, please use camel-kafka-connector-create first.");
        }
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

    private void createClasses(String sanitizedName, File connectorDir, ComponentModel model, ConnectorType ct) throws MojoFailureException, ResourceNotFoundException, FileResourceCreationException, IOException {
        String ctCapitalizedName = StringUtils.capitalize(ct.name().toLowerCase());
        String ctLowercaseName = ct.name().toLowerCase();
        String packageName = "org.apache.camel.kafkaconnector." + RESERVEDWORDSUBSTITUTIONMAP.getOrDefault(sanitizedName.replace("-", ""), sanitizedName.replace("-", ""));
        Map<String, String> additionalProperties = new HashMap<>();
        Properties properties = new Properties();
        properties.load(new FileInputStream(rm.getResourceAsFile(fixDependenciesProperties)));
        String commonPropertyValue = properties.getProperty(ADDITIONAL_COMMON_PROPERTIES_PROPERTY_PREFIX + getMainDepArtifactId());
        getLog().debug("Additional common connector properties: " + commonPropertyValue);
        addProperties(additionalProperties, commonPropertyValue);
        String sourceOrSinkPropertyValue = properties.getProperty(ADDITIONAL_COMMON_PROPERTIES_PROPERTY_PREFIX + ctLowercaseName + "_" + getMainDepArtifactId());
        getLog().debug("Additional " + ctLowercaseName + " connector properties: " + sourceOrSinkPropertyValue);
        addProperties(additionalProperties, sourceOrSinkPropertyValue);

        //Camel{sanitizedName}{Sink,Source}ConnectorConfig.java
        String javaClassConnectorConfigName = "Camel" + StringUtils.capitalize(sanitizedName.replace("-", "")) + ctCapitalizedName + "ConnectorConfig";
        final JavaClass javaClassConnectorConfig = new JavaClass(getProjectClassLoader());
        javaClassConnectorConfig.setPackage(packageName);
        javaClassConnectorConfig.setName(javaClassConnectorConfigName);
        javaClassConnectorConfig.addAnnotation(Generated.class).setStringValue("value", "This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.");
        javaClassConnectorConfig.extendSuperType("Camel" + ctCapitalizedName + "ConnectorConfig");
        javaClassConnectorConfig.addImport("java.util.Map");
        javaClassConnectorConfig.addImport("org.apache.camel.kafkaconnector.Camel" + ctCapitalizedName + "ConnectorConfig");
        javaClassConnectorConfig.addImport("org.apache.kafka.common.config.ConfigDef");

        javaClassConnectorConfig.addMethod().setConstructor(true).setName(javaClassConnectorConfigName)
                .addParameter("ConfigDef", "config")
                .addParameter("Map<String, String>", "parsedConfig")
                .setPublic()
                .setBody("super(config, parsedConfig);");
        javaClassConnectorConfig.addMethod().setConstructor(true).setName(javaClassConnectorConfigName)
                .addParameter("Map<String, String>", "parsedConfig")
                .setPublic()
                .setBody("this(conf(), parsedConfig);");

        Method confMethod = javaClassConnectorConfig.addMethod().setConstructor(false).setName("conf")
                .addParameter("Map<String, String>", "parsedConfig")
                .setReturnType("ConfigDef")
                .setPublic()
                .setStatic()
                .setBody("ConfigDef conf = new ConfigDef(Camel" + ctCapitalizedName + "ConnectorConfig.conf());\n");

        Predicate<? super BaseOptionModel> filterEndpointOptions;
        switch (ct) {
            case SINK:
                filterEndpointOptions = new Predicate<BaseOptionModel>() {
                    @Override
                    public boolean test(BaseOptionModel optionModel) {
                        return optionModel.getLabel() == null
                                || optionModel.getLabel().contains("producer")
                                || (!optionModel.getLabel().contains("producer")
                                && !optionModel.getLabel().contains("consumer"));
                    }
                };
                break;
            case SOURCE:
                filterEndpointOptions = new Predicate<BaseOptionModel>() {
                    @Override
                    public boolean test(BaseOptionModel optionModel) {
                        return optionModel.getLabel() == null
                                || optionModel.getLabel().contains("consumer")
                                || (!optionModel.getLabel().contains("producer")
                                && !optionModel.getLabel().contains("consumer"));
                    }
                };
                break;
            default:
                throw new UnsupportedOperationException("Connector type not supported: " + ct + " must be one of " + ConnectorType.SINK + ", " + ConnectorType.SOURCE);
        }

        model.getEndpointPathOptions().stream()
                .filter(filterEndpointOptions)
                .forEachOrdered(epo -> addConnectorOptions(sanitizedName, ct, javaClassConnectorConfig, confMethod, "PATH",  ctLowercaseName, "path", epo));
        model.getEndpointParameterOptions().stream()
                .filter(filterEndpointOptions)
                .forEachOrdered(epo -> addConnectorOptions(sanitizedName, ct, javaClassConnectorConfig, confMethod, "ENDPOINT", ctLowercaseName, "endpoint", epo));
        model.getComponentOptions().stream()
                .filter(filterEndpointOptions)
                .forEachOrdered(co -> addConnectorOptions(sanitizedName, ct, javaClassConnectorConfig, confMethod, "COMPONENT", "component", sanitizedName, co));

        confMethod.setBody(confMethod.getBody() + "return conf;");

        String javaClassConnectorConfigFileName = packageName.replaceAll("\\.", "\\/") + "/" + javaClassConnectorConfigName + ".java";
        MavenUtils.writeSourceIfChanged(javaClassConnectorConfig, javaClassConnectorConfigFileName, false, connectorDir, rm.getResourceAsFile(javaFilesHeader));

        //Camel{sanitizedName}{Sink,Source}Task.java
        String javaClassTaskName = "Camel" + StringUtils.capitalize(sanitizedName.replace("-", "")) + ctCapitalizedName + "Task";
        final JavaClass javaClassTask = new JavaClass(getProjectClassLoader());
        javaClassTask.setPackage(packageName);
        javaClassTask.setName(javaClassTaskName);
        javaClassTask.addAnnotation(Generated.class).setStringValue("value", "This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.");
        javaClassTask.extendSuperType("Camel" + ctCapitalizedName + "Task");
        javaClassTask.addImport("java.util.HashMap");
        javaClassTask.addImport("java.util.Map");
        javaClassTask.addImport("org.apache.camel.kafkaconnector.Camel" + ctCapitalizedName + "ConnectorConfig");
        javaClassTask.addImport("org.apache.camel.kafkaconnector.Camel" + ctCapitalizedName + "Task");

        javaClassTask.addMethod().setConstructor(false).setName("getCamel" + ctCapitalizedName + "ConnectorConfig")
                .setProtected()
                .addParameter("Map<String, String>", "props")
                .setReturnType("Camel" + ctCapitalizedName + "ConnectorConfig")
                .setBody("return new Camel" + StringUtils.capitalize(sanitizedName.replace("-", "")) + ctCapitalizedName + "ConnectorConfig(props);")
                .addAnnotation(Override.class);
        Method getDefaultConfigMethod = javaClassTask.addMethod().setConstructor(false).setName("getDefaultConfig")
                .setProtected()
                .setReturnType("Map<String, String>")
                .setBody("return new HashMap<String, String>() {{\n");
        getDefaultConfigMethod.setBody(getDefaultConfigMethod.getBody() + "    put(Camel" + ctCapitalizedName + "ConnectorConfig.CAMEL_" + ct + "_COMPONENT_CONF, \"" + model.getScheme() + "\");\n");
        for (String key : new TreeSet<String>(additionalProperties.keySet())) {
            getDefaultConfigMethod.setBody(getDefaultConfigMethod.getBody() + "    put(\"" + key + "\", \"" + additionalProperties.get(key) + "\");\n");
        }
        getDefaultConfigMethod.setBody(getDefaultConfigMethod.getBody() + "}};\n");
        getDefaultConfigMethod.addAnnotation(Override.class);
        String javaClassTaskFileName = packageName.replaceAll("\\.", "\\/") + "/" + javaClassTaskName + ".java";
        MavenUtils.writeSourceIfChanged(javaClassTask, javaClassTaskFileName, false, connectorDir, rm.getResourceAsFile(javaFilesHeader));

        //Camel{sanitizedName}{Sink,Source}Connector.java
        String javaClassConnectorName = "Camel" + StringUtils.capitalize(sanitizedName.replace("-", "")) + ctCapitalizedName + "Connector";
        final JavaClass javaClassConnector = new JavaClass(getProjectClassLoader());
        javaClassConnector.setPackage(packageName);
        javaClassConnector.setName(javaClassConnectorName);
        javaClassConnector.addAnnotation(Generated.class).setStringValue("value", "This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.");
        javaClassConnector.extendSuperType("Camel" + ctCapitalizedName + "Connector");
        javaClassConnector.addImport("org.apache.camel.kafkaconnector.Camel" + ctCapitalizedName + "Connector");
        javaClassConnector.addImport("org.apache.kafka.common.config.ConfigDef");
        javaClassConnector.addImport("org.apache.kafka.connect.connector.Task");

        javaClassConnector.addMethod().setConstructor(false).setName("config")
                .setPublic()
                .setReturnType("ConfigDef")
                .setBody("return Camel" + StringUtils.capitalize(sanitizedName.replace("-", "")) + ctCapitalizedName + "ConnectorConfig.conf();")
                .addAnnotation(Override.class);
        javaClassConnector.addMethod().setConstructor(false).setName("taskClass")
                .setPublic()
                .setReturnType("Class<? extends Task>")
                .setBody("return Camel" + StringUtils.capitalize(sanitizedName.replace("-", "")) + ctCapitalizedName + "Task.class;")
                .addAnnotation(Override.class);

        String javaClassConnectorFileName = packageName.replaceAll("\\.", "\\/") + "/" + javaClassConnectorName + ".java";
        MavenUtils.writeSourceIfChanged(javaClassConnector, javaClassConnectorFileName, false, connectorDir, rm.getResourceAsFile(javaFilesHeader));
    }

    private void addProperties(Map<String, String> additionalProperties, String additionalProp) {
        if (additionalProp != null && additionalProp.trim().length() > 0) {
            for (String prop : additionalProp.split(",")) {
                getLog().debug("Additional property before key value split: " + prop);
                String[] keyValue = prop.split("=");
                getLog().debug("Additional property key value: " + keyValue);
                additionalProperties.put(keyValue[0], keyValue[1]);
            }
        }
    }

    private void addConnectorOptions(String sanitizedName, ConnectorType ct, JavaClass javaClass, Method confMethod, String propertyQualifier, String firstNamespace, String secondNamespace, BaseOptionModel epo) {
        String propertyName = epo.getName();

        String regex = "([A-Z][a-z]+)";
        String replacement = "$1_";

        String propertyPrefix = "CAMEL_" + ct + "_" + sanitizedName.replace("-", "").toUpperCase() + "_" + propertyQualifier.toUpperCase() + "_" + StringUtils.capitalize(propertyName).replaceAll(regex, replacement).toUpperCase();
        String propertyValue = "camel." + firstNamespace + "." + secondNamespace + "." + epo.getName();

        String confFieldName = propertyPrefix + "CONF";
        javaClass.addField().setFinal(true).setPublic().setStatic(true)
                .setName(confFieldName)
                .setType(String.class)
                .setStringInitializer(propertyValue);

        String docFieldName = propertyPrefix + "DOC";
        String docLiteralInitializer = epo.getDescription();
        if (epo.getEnums() != null && !epo.getEnums().isEmpty()) {
            docLiteralInitializer = docLiteralInitializer + " One of:";
            String enumOptionListing = epo.getEnums().stream().reduce("", (s, s2) -> s + " [" + s2 + "]");
            docLiteralInitializer = docLiteralInitializer + enumOptionListing;
        }
        javaClass.addField().setFinal(true).setPublic().setStatic(true)
                .setName(docFieldName)
                .setType(String.class)
                .setStringInitializer(docLiteralInitializer);

        String defaultFieldName = propertyPrefix + "DEFAULT";
        Class<?> defaultValueClass = PRIMITIVEMAP.getOrDefault(epo.getShortJavaType(), String.class);
        String defaultValueClassLiteralInitializer = epo.getDefaultValue() ==  null ? "null" : epo.getDefaultValue().toString();
        if (!defaultValueClassLiteralInitializer.equals("null") && defaultValueClass.equals(String.class)) {
            defaultValueClassLiteralInitializer = "\"" + defaultValueClassLiteralInitializer + "\"";
        } else if (!defaultValueClassLiteralInitializer.equals("null") && defaultValueClass.equals(Long.class)) {
            defaultValueClassLiteralInitializer = defaultValueClassLiteralInitializer + "L";
        } else if (!defaultValueClassLiteralInitializer.equals("null") && defaultValueClass.equals(Float.class)) {
            defaultValueClassLiteralInitializer = defaultValueClassLiteralInitializer + "F";
        } else if (!defaultValueClassLiteralInitializer.equals("null") && defaultValueClass.equals(Double.class)) {
            defaultValueClassLiteralInitializer = defaultValueClassLiteralInitializer + "D";
        }
        javaClass.addField().setFinal(true).setPublic().setStatic(true)
                .setName(defaultFieldName)
                .setType(defaultValueClass)
                .setLiteralInitializer(defaultValueClassLiteralInitializer);

        String confType = CONFIGDEFMAP.getOrDefault(epo.getShortJavaType(), "ConfigDef.Type.STRING");
        String confPriority = epo.isDeprecated() ? "ConfigDef.Importance.LOW" : "ConfigDef.Importance.MEDIUM";
        confPriority = epo.isRequired() ? "ConfigDef.Importance.HIGH" : confPriority;
        confMethod.setBody(confMethod.getBody() + "conf.define(" + confFieldName + ", " + confType + ", " + defaultFieldName + ", " + confPriority + ", " + docFieldName + ");\n");
    }

    private enum ConnectorType {
        SINK,
        SOURCE
    }
}
