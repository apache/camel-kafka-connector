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

package org.apache.camel.kafkaconnector.ssh.source;

import org.apache.camel.kafkaconnector.common.SourceConnectorPropertyFactory;

final class CamelSshPropertyFactory extends SourceConnectorPropertyFactory<CamelSshPropertyFactory> {

    private CamelSshPropertyFactory() {

    }

    public CamelSshPropertyFactory withHost(String host) {
        return setProperty("camel.source.path.host", host);
    }

    public CamelSshPropertyFactory withPort(String port) {
        return setProperty("camel.source.path.port", port);
    }

    public CamelSshPropertyFactory withDelay(String value) {
        return setProperty("camel.source.endpoint.delay", value);
    }

    public CamelSshPropertyFactory withUsername(String username) {
        return setProperty("camel.source.endpoint.username", username);
    }

    public CamelSshPropertyFactory withPassword(String password) {
        return setProperty("camel.source.endpoint.password", password);
    }

    public CamelSshPropertyFactory withPollcommand(String pollCommand) {
        return setProperty("camel.source.endpoint.pollCommand", pollCommand);
    }

    public static CamelSshPropertyFactory basic() {
        return new CamelSshPropertyFactory().withName("CamelSshSourceConnector").withTasksMax(1)
            .withConnectorClass("org.apache.camel.kafkaconnector.ssh.CamelSshSourceConnector").withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }
}
