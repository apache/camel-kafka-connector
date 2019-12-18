/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.camel.kafkaconnector.services.kafka;

import java.io.File;
import java.net.URL;

import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class StrimziContainer extends DockerComposeContainer {
    private static final String COMPOSE_FILE;

    static {
        String file =  "strimzi/docker-compose.yaml";

        URL url = StrimziContainer.class.getResource(file);

        COMPOSE_FILE = url.getFile();
    }


    public StrimziContainer() {
        super(new File(COMPOSE_FILE));

        withExposedService("kafka", 9092);

        waitingFor("zookeeper", Wait.forListeningPort());
        waitingFor("kafka", Wait.forListeningPort());
    }
}
