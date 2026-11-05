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

package org.apache.camel.kafkaconnector.ftp.sink;

import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;

/**
 * Property factory for the ftp-sink, ftps-sink and sftp-sink connectors, whose Kamelets share the same options.
 */
final class CamelFtpPropertyFactory extends SinkConnectorPropertyFactory<CamelFtpPropertyFactory> {
    private final String kamelet;

    private CamelFtpPropertyFactory(String kamelet) {
        this.kamelet = kamelet;
    }

    public CamelFtpPropertyFactory withConnectionHost(String connectionHost) {
        return withKameletProperty("connectionHost", connectionHost);
    }

    public CamelFtpPropertyFactory withConnectionPort(int connectionPort) {
        return withKameletProperty("connectionPort", String.valueOf(connectionPort));
    }

    public CamelFtpPropertyFactory withUsername(String username) {
        return withKameletProperty("username", username);
    }

    public CamelFtpPropertyFactory withPassword(String password) {
        return withKameletProperty("password", password);
    }

    public CamelFtpPropertyFactory withDirectoryName(String directoryName) {
        return withKameletProperty("directoryName", directoryName);
    }

    public CamelFtpPropertyFactory withStrictHostKeyChecking(String strictHostKeyChecking) {
        return withKameletProperty("strictHostKeyChecking", strictHostKeyChecking);
    }

    private CamelFtpPropertyFactory withKameletProperty(String name, String value) {
        return setProperty("camel.kamelet." + kamelet + "." + name, value);
    }

    public static CamelFtpPropertyFactory ftp() {
        return basic("ftp-sink", "CamelFtpSinkConnector",
                "org.apache.camel.kafkaconnector.ftpsink.CamelFtpsinkSinkConnector");
    }

    public static CamelFtpPropertyFactory ftps() {
        return basic("ftps-sink", "CamelFtpsSinkConnector",
                "org.apache.camel.kafkaconnector.ftpssink.CamelFtpssinkSinkConnector");
    }

    public static CamelFtpPropertyFactory sftp() {
        return basic("sftp-sink", "CamelSftpSinkConnector",
                "org.apache.camel.kafkaconnector.sftpsink.CamelSftpsinkSinkConnector");
    }

    private static CamelFtpPropertyFactory basic(String kamelet, String name, String connectorClass) {
        return new CamelFtpPropertyFactory(kamelet)
                .withName(name)
                .withTasksMax(1)
                .withConnectorClass(connectorClass)
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .setProperty("camel.component.kamelet.location", "kamelets");
    }
}
