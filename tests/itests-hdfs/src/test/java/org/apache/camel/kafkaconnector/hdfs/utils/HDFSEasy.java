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

package org.apache.camel.kafkaconnector.hdfs.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HDFSEasy {
    private static final Logger LOG = LoggerFactory.getLogger(HDFSEasy.class);

    private DistributedFileSystem dfs = new DistributedFileSystem();

    public HDFSEasy(String host, int port) throws URISyntaxException, IOException {
        dfs.initialize(new URI("hdfs://" + host + ":" + port), new Configuration());
    }

    public List<LocatedFileStatus> listFiles(Path path) throws IOException {
        RemoteIterator<LocatedFileStatus> i = dfs.listFiles(path, false);

        List<LocatedFileStatus> retList = new ArrayList<>();
        while (i.hasNext()) {
            LocatedFileStatus locatedFileStatus = i.next();
            retList.add(locatedFileStatus);
        }

        return retList;
    }

    public boolean delete(Path path) {
        try {
            if (dfs.exists(path)) {
                LOG.debug("Removing HDFS directory {}", path.getName());
                if (!dfs.delete(path, true)) {
                    LOG.debug("Failed to remove directory {}", path.getName());

                    return false;
                }

                return true;
            }
        } catch (IOException e) {
            LOG.warn("Unable to remove HDFS directory {}: {}", path.getName(), e.getMessage(), e);
        }

        return false;
    }

    public String readFile(Path filePath) throws IOException {
        final FSDataInputStream streamReader = dfs.open(filePath);
        final Scanner scanner = new Scanner(streamReader);

        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }

        return sb.toString();
    }

    public String readFile(String filePath) throws IOException {
        return readFile(new Path(filePath));
    }

    public int countFiles(Path path) throws IOException {
        RemoteIterator<LocatedFileStatus> i = dfs.listFiles(path, false);
        int files = 0;
        while (i.hasNext()) {
            files++;
            i.next();
        }

        return files;
    }

    /**
     * Checks if a set of (minimum number of) files was created on the given path representing a directory
     * @param path the path to check for the files
     * @param minFiles the number of files created (using 0 just checks if the directory is there)
     * @return true if the path contains at least minFiles and false otherwise
     */
    public boolean filesCreated(Path path, int minFiles) {
        try {
            return countFiles(path) >= minFiles;
        } catch (Exception e) {
            LOG.warn("I/O exception while checking if file {} exists", path.getName());

            return false;
        }
    }

    public boolean filesCreated(Path path) {
        return filesCreated(path, 0);
    }

    public boolean filesCreated(String path) {
        return filesCreated(new Path(path));
    }

    public boolean exists(Path path) {
        try {
            return dfs.exists(path);
        } catch (Exception e) {
            LOG.warn("I/O exception while checking if file {} exists", path.getName());

            return false;
        }
    }
}
