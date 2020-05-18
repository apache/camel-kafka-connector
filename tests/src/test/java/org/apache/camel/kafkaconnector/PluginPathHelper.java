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

package org.apache.camel.kafkaconnector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.shaded.org.apache.commons.io.DirectoryWalker;

public final class PluginPathHelper {
    private static final Logger LOG = LoggerFactory.getLogger(PluginPathHelper.class);

    private static final String[] MODULES = {
        "connectors/camel-sjms2-kafka-connector", "connectors/camel-cql-kafka-connector",
        "connectors/camel-aws-sns-kafka-connector", "connectors/camel-aws-sqs-kafka-connector",
        "connectors/camel-aws-s3-kafka-connector", "connectors/camel-aws-kinesis-kafka-connector",
        "connectors/camel-elasticsearch-rest-kafka-connector", "connectors/camel-http-kafka-connector",
        "connectors/camel-timer-kafka-connector", "connectors/camel-file-kafka-connector",
        "connectors/camel-slack-kafka-connector", "connectors/camel-syslog-kafka-connector"
    };

    private static class PluginWalker extends DirectoryWalker<String> {
        @Override
        protected void handleFile(File file, int depth, Collection<String> results) throws IOException {
            String fileName = file.getName();

            if (fileName.endsWith(".jar")) {
                if (fileName.contains("kafka-connector") && fileName.contains("camel")) {
                    String parentDir = file.getParentFile().getCanonicalPath();
                    if (parentDir.endsWith("target")) {
                        String pluginDir = file.getParentFile().getCanonicalPath();
                        LOG.debug("Adding directory: {}", pluginDir);

                        results.add(pluginDir);
                    }
                }
            }
        }



        @Override
        protected boolean handleDirectory(File directory, int depth, Collection<String> results) throws IOException {
            String directoryName = directory.getName();

            if (directoryName.equals("target")) {
                String pluginDir = directory.getCanonicalPath();
                LOG.debug("Adding directory: {}", pluginDir);

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

    private static List<String> findPlugins(String...moduleDirs) {
        List<String> ret = new ArrayList<>();

        for (String module : moduleDirs) {
            String path = System.getProperty("project.basedir") + File.separator + module;
            LOG.debug("Base dir used for search: {}", path);
            PluginWalker pluginWalker = new PluginWalker();

            ret.addAll(pluginWalker.findPlugins(new File(path)));
        }

        return ret;
    }

    private static List<String> findPlugins() {
        /*
         * Only load the subset of modules that has a related test, otherwise the startup time for the
         * Kafka Connect runtime is extremely long
         */
        return findPlugins(MODULES);
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
     * Then for every connector jar file that it finds, it configures the embedded runtime to  includes the parent dir
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
    public static String pluginPaths() {
        String ret = findPlugins().stream().collect(Collectors.joining(","));
        LOG.info("Returning the following directories for the plugin path: {}", ret);

        return ret;
    }
}
