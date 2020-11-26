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

public class SshRemoteService implements SshService {

    private static final int DEFAULT_SSH_PORT = 22;

    @Override
    public void initialize() {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public int getSshPort() {
        String strPort = System.getProperty("ssh.port");

        if (strPort != null) {
            return Integer.parseInt(strPort);
        }

        return DEFAULT_SSH_PORT;
    }

    @Override
    public String getSshHost() {
        return System.getProperty("ssh.host");
    }
}
