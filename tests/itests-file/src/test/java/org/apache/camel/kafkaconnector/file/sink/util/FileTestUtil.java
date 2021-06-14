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

package org.apache.camel.kafkaconnector.file.sink.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class FileTestUtil {
    private static final Logger LOG = LoggerFactory.getLogger(FileTestUtil.class);

    private FileTestUtil() {

    }

    public static int checkFileContents(File sinkFile, Function<Integer, String> consumer) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(sinkFile));

        int currentLine = 0;
        String line;

        do {
            line = reader.readLine();
            if (line != null) {
                assertEquals(consumer.apply(currentLine), line, String.format("Unexpected data: %s", line));
                currentLine++;
            }
        } while (line != null);

        return currentLine;
    }

    public static void waitForFile(File sinkFile, File doneFile) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = sinkFile.getParentFile().toPath();

        if (doneFile.exists()) {
            return;
        }

        // We watch for both the file creation and truncation
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);

        int retries = 30;
        do {
            WatchKey watchKey = watchService.poll(1, TimeUnit.SECONDS);

            if (watchKey == null) {
                continue;
            }

            for (WatchEvent<?> event : watchKey.pollEvents()) {

                /*
                  It should return a Path object for ENTRY_CREATE and ENTRY_MODIFY events
                 */
                Object context = event.context();
                if (!(context instanceof Path)) {
                    LOG.warn("Received an unexpected event of kind {} for context {}", event.kind(), event.context());
                    continue;
                }

                Path contextPath = (Path) context;

                if (contextPath.toString().equals(doneFile.getName())) {
                    LOG.info("Sink file at the build path {} had a matching event of type: {}", sinkFile.getPath(),
                            event.kind());

                    return;
                } else {
                    LOG.debug("Ignoring a watch event at build path {} of type {} for file: {}", sinkFile.getPath(),
                            event.kind(), contextPath.getFileName());
                }
            }
            watchKey.reset();
            retries--;
        } while (!doneFile.exists() && retries > 0);
    }
}
