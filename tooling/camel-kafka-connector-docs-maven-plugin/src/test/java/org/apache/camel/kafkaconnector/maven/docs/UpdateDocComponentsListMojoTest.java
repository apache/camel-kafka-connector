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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.camel.kafkaconnector.maven.docs.dto.CamelKafkaConnectorTableOptionModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateDocComponentsListMojoTest {

    private static final String SINK_MANIFEST = "src/main/resources/META-INF/services/org.apache.kafka.connect.sink.SinkConnector";
    private static final String SOURCE_MANIFEST = "src/main/resources/META-INF/services/org.apache.kafka.connect.source.SourceConnector";
    private static final String REPOSITORY = "https://repo.maven.apache.org/maven2/org/apache/camel/kafkaconnector/";

    @TempDir
    private Path projectBaseDir;

    private Path connectorsDir;
    private UpdateDocComponentsListMojo mojo;

    @BeforeEach
    public void createMojo() throws IOException {
        connectorsDir = Files.createDirectories(projectBaseDir.resolve("connectors"));
        Files.writeString(connectorsDir.resolve("pom.xml"), "");

        // the configuration of the connectors aggregator, with the default values of the parameters
        mojo = new UpdateDocComponentsListMojo();
        mojo.connectorsDir = connectorsDir.toFile();
        mojo.projectBaseDir = projectBaseDir.toFile();
        mojo.repositoryPath = REPOSITORY;
        mojo.lastReleasedVersion = "4.22.0";
        mojo.xrefConnectorLinkPrefix = "xref:reference/connectors/";
        mojo.sinkConnectorLinkSuffix = "kafka-sink-connector.adoc[Sink Docs]";
        mojo.sourceConnectorLinkSuffix = "kafka-source-connector.adoc[Source Docs]";
        mojo.docsModuleDir = "docs/modules/ROOT/";
        mojo.connectorsIndexFile = "pages/reference/index.adoc";
        mojo.navFile = "nav.adoc";
    }

    @Test
    public void detectsConnectorModulesFromTheirServiceLoaderManifests() throws Exception {
        addFile("camel-foo-sink-kafka-connector", SINK_MANIFEST);
        addFile("camel-foo-source-kafka-connector", SOURCE_MANIFEST);
        addFile("camel-both-kafka-connector", SINK_MANIFEST);
        addFile("camel-both-kafka-connector", SOURCE_MANIFEST);
        // stale build output, e.g. left behind by a module removed from the sources, is never looked at
        addFile("camel-stale-kafka-connector", "target/classes/org/apache/camel/kafkaconnector/stale/CamelStaleSinkTask.class");
        addFile("camel-stale-kafka-connector", "target/classes/META-INF/services/org.apache.kafka.connect.sink.SinkConnector");
        addFile("camel-partly-stale-kafka-connector", SINK_MANIFEST);
        addFile("camel-partly-stale-kafka-connector", "target/classes/org/apache/camel/kafkaconnector/partlystale/CamelPartlyStaleSourceTask.class");
        addFile("camel-partly-stale-kafka-connector", "target/classes/META-INF/services/org.apache.kafka.connect.source.SourceConnector");
        addFile("target", "classes/META-INF/services/org.apache.kafka.connect.sink.SinkConnector");

        List<CamelKafkaConnectorTableOptionModel> connectors = mojo.findConnectors();

        assertEquals(4, connectors.size(), connectors.toString());
        assertConnector(connectors.get(0), "camel-both-kafka-connector",
                "xref:reference/connectors/camel-both-kafka-sink-connector.adoc[Sink Docs]",
                "xref:reference/connectors/camel-both-kafka-source-connector.adoc[Source Docs]");
        assertConnector(connectors.get(1), "camel-foo-sink-kafka-connector",
                "xref:reference/connectors/camel-foo-sink-kafka-sink-connector.adoc[Sink Docs]", "");
        assertConnector(connectors.get(2), "camel-foo-source-kafka-connector",
                "", "xref:reference/connectors/camel-foo-source-kafka-source-connector.adoc[Source Docs]");
        assertConnector(connectors.get(3), "camel-partly-stale-kafka-connector",
                "xref:reference/connectors/camel-partly-stale-kafka-sink-connector.adoc[Sink Docs]", "");
    }

    @Test
    public void keepsTheComponentSchemeInTheDocsLinksOfSanitizedConnectorNames() throws Exception {
        addFile("camel-coap-tcp-kafka-connector", SINK_MANIFEST);
        addFile("camel-coap-tcp-kafka-connector", SOURCE_MANIFEST);
        addFile("camel-coaps-tcp-kafka-connector", SINK_MANIFEST);

        List<CamelKafkaConnectorTableOptionModel> connectors = mojo.findConnectors();

        assertEquals(2, connectors.size(), connectors.toString());
        assertConnector(connectors.get(0), "camel-coap-tcp-kafka-connector",
                "xref:reference/connectors/camel-coap+tcp-kafka-sink-connector.adoc[Sink Docs]",
                "xref:reference/connectors/camel-coap+tcp-kafka-source-connector.adoc[Source Docs]");
        assertConnector(connectors.get(1), "camel-coaps-tcp-kafka-connector",
                "xref:reference/connectors/camel-coaps+tcp-kafka-sink-connector.adoc[Sink Docs]", "");
    }

    @Test
    public void updatesTheConnectorsTableAndNavBetweenTheirMarkers() throws Exception {
        addFile("camel-foo-sink-kafka-connector", SINK_MANIFEST);
        addFile("camel-both-kafka-connector", SINK_MANIFEST);
        addFile("camel-both-kafka-connector", SOURCE_MANIFEST);
        Path index = writeFile(projectBaseDir.resolve("docs/modules/ROOT/pages/reference/index.adoc"),
                "= Supported connectors\n\n// kafka-connectors list: START\nNumber of Camel Kafka connectors: 0\n// kafka-connectors list: END\n\nAfter the table\n");
        Path nav = writeFile(projectBaseDir.resolve("docs/modules/ROOT/nav.adoc"),
                "** xref:reference/index.adoc[Connectors]\n// connectors: START\n*** camel-removed-kafka-connector\n// connectors: END\n* xref:other.adoc[Other]\n");

        mojo.executeComponentsReadme();

        String table = Files.readString(index);
        assertTrue(table.startsWith("= Supported connectors\n\n// kafka-connectors list: START\nNumber of Camel Kafka connectors: 2"), table);
        assertTrue(table.contains("\n| *camel-both-kafka-connector* | true | true"
                + " | xref:reference/connectors/camel-both-kafka-sink-connector.adoc[Sink Docs]"
                + " | xref:reference/connectors/camel-both-kafka-source-connector.adoc[Source Docs]"
                + " | " + REPOSITORY + "camel-both-kafka-connector/4.22.0/camel-both-kafka-connector-4.22.0-package.tar.gz[Download]\n"), table);
        assertTrue(table.contains("\n| *camel-foo-sink-kafka-connector* | true | false"
                + " | xref:reference/connectors/camel-foo-sink-kafka-sink-connector.adoc[Sink Docs] | "
                + " | " + REPOSITORY + "camel-foo-sink-kafka-connector/4.22.0/camel-foo-sink-kafka-connector-4.22.0-package.tar.gz[Download]\n"), table);
        assertTrue(table.endsWith("\n|===\n// kafka-connectors list: END\n\nAfter the table\n"), table);
        assertFalse(table.contains("connectors: 0"), table);

        assertEquals("** xref:reference/index.adoc[Connectors]\n"
                + "// connectors: START\n"
                + "*** camel-both-kafka-connector\n"
                + "**** xref:reference/connectors/camel-both-kafka-source-connector.adoc[Source Docs]\n"
                + "**** xref:reference/connectors/camel-both-kafka-sink-connector.adoc[Sink Docs]\n"
                + "*** camel-foo-sink-kafka-connector\n"
                + "**** xref:reference/connectors/camel-foo-sink-kafka-sink-connector.adoc[Sink Docs]\n"
                + "// connectors: END\n"
                + "* xref:other.adoc[Other]\n", Files.readString(nav));
    }

    @Test
    public void usesTheConfiguredDocsFilesAndLinks() throws Exception {
        addFile("camel-foo-sink-kafka-connector", SINK_MANIFEST);
        addFile("camel-foo-source-kafka-connector", SOURCE_MANIFEST);
        Path docsModuleDir = projectBaseDir.resolve("site/modules/connectors");
        Path index = writeFile(docsModuleDir.resolve("pages/list.adoc"), "// kafka-connectors list: START\n// kafka-connectors list: END\n");
        Path nav = writeFile(docsModuleDir.resolve("partials/nav.adoc"), "// connectors: START\n// connectors: END\n");
        mojo.docsModuleDir = docsModuleDir.toString();
        mojo.connectorsIndexFile = "pages/list.adoc";
        mojo.navFile = "partials/nav.adoc";
        mojo.xrefConnectorLinkPrefix = "xref:connectors/";
        mojo.sinkConnectorLinkSuffix = "sink.adoc[Sink]";
        mojo.sourceConnectorLinkSuffix = "source.adoc[Source]";

        mojo.executeComponentsReadme();

        String table = Files.readString(index);
        assertTrue(table.contains("\n| *camel-foo-sink-kafka-connector* | true | false | xref:connectors/camel-foo-sink-sink.adoc[Sink] |  | "), table);
        assertTrue(table.contains("\n| *camel-foo-source-kafka-connector* | false | true |  | xref:connectors/camel-foo-source-source.adoc[Source] | "), table);
        assertEquals("// connectors: START\n"
                + "*** camel-foo-sink-kafka-connector\n"
                + "**** xref:connectors/camel-foo-sink-sink.adoc[Sink]\n"
                + "*** camel-foo-source-kafka-connector\n"
                + "**** xref:connectors/camel-foo-source-source.adoc[Source]\n"
                + "// connectors: END\n", Files.readString(nav));
        assertFalse(Files.exists(projectBaseDir.resolve("docs")), "the default docs module must not be touched");
    }

    private void addFile(String module, String path) throws IOException {
        writeFile(connectorsDir.resolve(module).resolve(path), "");
    }

    private static Path writeFile(Path file, String content) throws IOException {
        Files.createDirectories(file.getParent());
        return Files.writeString(file, content);
    }

    private static void assertConnector(CamelKafkaConnectorTableOptionModel connector, String name, String docsSink, String docsSource) {
        assertEquals(name, connector.getName());
        assertEquals(!docsSink.isEmpty(), connector.isSink(), name);
        assertEquals(docsSink, connector.getDocsSink(), name);
        assertEquals(!docsSource.isEmpty(), connector.isSource(), name);
        assertEquals(docsSource, connector.getDocsSource(), name);
        assertEquals(REPOSITORY + name + "/4.22.0/" + name + "-4.22.0-package.tar.gz[Download]", connector.getDownloadLinkTar(), name);
    }
}
