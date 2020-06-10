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

package org.apache.camel.kafkaconnector.common.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PropertyUtils {
    private static final Logger LOG = LoggerFactory.getLogger(PropertyUtils.class);
    private static Properties properties = new Properties();

    private PropertyUtils() {

    }

    public static Properties getProperties() {
        return properties;
    }

    public static void load() {
        String fileName = System.getProperty("test.properties");

        if (fileName == null) {
            LOG.info("Test properties was not provided, therefore not loading any test properties");

            return;
        }

        try (InputStream stream = new FileInputStream(fileName)) {
            properties.load(stream);

            System.getProperties().putAll(properties);
        } catch (FileNotFoundException e) {
            LOG.error("Test properties provided at {} does not exist, therefore aborting the test execution",
                    fileName);

            Assert.fail("The given test properties file does not exist");
        } catch (IOException e) {
            LOG.error("I/O error reading the test properties at {}: {}",
                    fileName, e.getMessage(), e);

            Assert.fail("Unable to read the test properties file");
        }
    }
}
