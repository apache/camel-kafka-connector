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
package org.apache.camel.test.infra.aws2.services;

import java.time.Duration;

import org.testcontainers.containers.wait.strategy.Wait;

public class AWSContainerWithTimeout extends AWSContainer {
    private static final String DEFAULT_STARTUP_TIMEOUT = "60";

    public AWSContainerWithTimeout() {
        super();
    }

    public AWSContainerWithTimeout(String imageName, Service... services) {
        super(imageName, services);
    }

    @Override
    protected void setupContainer() {
        int startupTimeout = Integer.parseInt(System.getProperty("aws.container.startup.timeout", DEFAULT_STARTUP_TIMEOUT));
        this.withExposedPorts(new Integer[]{4566});
        this.waitingFor(Wait.forLogMessage(".*Ready\\.\n", 1)
                            .withStartupTimeout(Duration.ofSeconds(startupTimeout)));
    }
}
