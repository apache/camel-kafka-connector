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
package org.apache.camel.kafkaconnector.ssh.services;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

/**
 * A local instance of an Ubuntu Server with SSH enabled
 */
public class SshContainer extends GenericContainer<SshContainer> {
    private static final String SSH_IMAGE = "rastasheep/ubuntu-sshd:14.04";
    private static final int SSH_PORT = 22;

    public SshContainer() {
        super(SSH_IMAGE);

        withExposedPorts(SSH_PORT);

        waitingFor(Wait.forListeningPort());
    }

    public int getSSHPort() {
        return getMappedPort(SSH_PORT);
    }

    public String getSSHHost() {
        return getContainerIpAddress();
    }

}
