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

package org.apache.camel.kafkaconnector.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.shaded.org.apache.commons.io.DirectoryWalker;

public final class PluginPathHelper {
    private static final Logger LOG = LoggerFactory.getLogger(PluginPathHelper.class);

    private static PluginPathHelper instance;
    private List<String> connectors;

    private static class PluginWalker extends DirectoryWalker<String> {

        public PluginWalker() {
            super(null, 2);
        }

        @Override
        protected void handleFile(File file, int depth, Collection<String> results) throws IOException {
            String fileName = file.getName();

            if (fileName.endsWith(".jar")) {
                if (fileName.contains("kafka-connector") && fileName.contains("camel")) {
                    String parentDir = file.getParentFile().getCanonicalPath();
                    if (parentDir.endsWith("target")) {
                        LOG.debug("Adding directory (1): {}: {}", depth, parentDir);

                        results.add(parentDir);
                    }
                }
            }
        }


        @Override
        protected boolean handleDirectory(File directory, int depth, Collection<String> results) throws IOException {
            String directoryName = directory.getName();

            if (directoryName.equals("target")) {
                String pluginDir = directory.getCanonicalPath();
                LOG.debug("Adding directory (2): {}: {}", depth, pluginDir);

                results.add(pluginDir);
            }

            return true;
        }

        public List<String> findPlugins(File startDir) {
            List<String> results = new ArrayList<>();

            try {
                walk(startDir, results);
            } catch (IOException e) {
                LOG.error("I/O error while traversing candidate plugin dirs: {}", e.getMessage(), e);
            }
            return results;
        }
    }

    private PluginPathHelper() {

    }

    private List<String> findPluginPaths() {
        List<String> pluginPaths = new ArrayList<>();

        for (String module : connectors) {
            String path = System.getProperty("project.basedir") + File.separator + module;
            File pathFile = new File(path);
            try {
                LOG.debug("Base dir used for search: {}", pathFile.getCanonicalPath());
            } catch (IOException e) {
                LOG.error("I/O exception: {}", e.getMessage(), e);
            }
            PluginWalker pluginWalker = new PluginWalker();

            pluginPaths.addAll(pluginWalker.findPlugins(pathFile));
        }

        return pluginPaths;
    }

    /*
     * We need to construct a list of directories containing *only* the connector classes (ie.: those that
     * specialize Kafka's Connector abstract class.
     *
     * Our build generates jar files with for every connector and puts that in the target directory which the
     * build dir (ie.: ${project.root}/core/target/camel-kafka-connector-0.0.1-SNAPSHOT.jar,
     * ${project.root}/connectors/camel-sjms2-kafka-connector/target/camel-sjms2-kafka-connector-0.0.1-SNAPSHOT.jar,
     * etc).
     *
     * The code within the pluginPaths traverses the directories for the core and connectors module, filtering any
     * file that matches all of the following conditions:
     * 1) ends with jar
     * 2) is located in the target directory
     * 3) contains the strings 'camel' and 'kafka-connector' as part of their name.
     *
     * Then for every connector jar file that it finds, it configures the embedded runtime to includes the parent dir
     * into the configuration.
     *
     * Why it does this?
     *
     * 1) Because having the connector classes in the classpath could cause library conflicts causing the connectors
     * and the Kafka connect runtime to fail.
     * 2) Having the connectors on the classpath causes the following error to appear multiple times in the logs:
     * 'Plugin class loader for connector: [name] was not found'
     *
     * ref: https://docs.confluent.io/current/connect/userguide.html
     */
    public String pluginPaths() {
        String ret = String.join(",", findPluginPaths());
        LOG.info("Returning the following directories for the plugin path: {}", ret);

        return ret;
    }

    public void registerConnector(String[] connectorNames) {
        // prepend "connectors/" to all connectors name
        connectors = Stream.of(connectorNames)
                .map(connectorName -> "connectors" + File.separator + connectorName)
                .collect(Collectors.toList());
    }

    public static PluginPathHelper getInstance() {
        if (instance == null) {
            instance = new PluginPathHelper();
        }

        return instance;
    }
}
