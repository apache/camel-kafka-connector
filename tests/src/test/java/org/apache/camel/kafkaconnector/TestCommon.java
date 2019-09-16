/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.camel.kafkaconnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static junit.framework.TestCase.fail;

/**
 * Common test constants and utilities
 */
public final class TestCommon {
    private static final Logger log = LoggerFactory.getLogger(TestCommon.class);

    private TestCommon() {}

    /**
     * The default topic for usage during the tests
     */
    public static final String DEFAULT_TEST_TOPIC = "mytopic";

    /**
     * The default JMS queue name used during the tests
     */
    public static final String DEFAULT_JMS_QUEUE = "ckc.queue";

    /**
     * The default JMS queue name used during the tests
     */
    public static final String DEFAULT_SQS_QUEUE = "ckc";


    public static void failOnConnectorError(Throwable error, Properties connectorProps, String name) {
        log.error("Failed to create job for {} with properties", name, connectorProps,
                error);
        fail("Failed to create job for " + name);
    }
}
