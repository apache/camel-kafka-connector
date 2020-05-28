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

package org.apache.camel.kafkaconnector.salesforce.clients;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class SalesforceCliContainer extends GenericContainer<SalesforceCliContainer> {
    private static final Logger LOG = LoggerFactory.getLogger(SalesforceCliContainer.class);
    private static final String HOST_PATH = System.getProperty("it.test.salesforce.sfdx.path");


    public SalesforceCliContainer() {
        super("salesforce/salesforcedx");

        withFileSystemBind(HOST_PATH, "/root/.sfdx");

        withCommand("/bin/bash", "-c", "echo running ; while true ; do sleep 1 ; echo running ; done");
        waitingFor(Wait.forLogMessage(".*running.*", 1));
    }

    public ExecResult execCommand(SfdxCommand sfdxCommand) throws IOException, InterruptedException {
        return execInContainer(sfdxCommand.commands());
    }

    public static boolean verifyCommand(SfdxCommand sfdxCommand, ExecResult result) {
        if (result.getExitCode() != 0) {
            LOG.error("Unable to execute command (stdout): {}", sfdxCommand.commands());
            LOG.error("Command stdout: {}", result.getStdout());
            LOG.error("Command stderr: {}", result.getStderr());

            return false;
        }

        return true;
    }
}
