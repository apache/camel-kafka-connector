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

package org.apache.camel.kafkaconnector.sink.syslog;

import org.apache.camel.kafkaconnector.SinkConnectorPropertyFactory;


/**
 * Creates the set of properties used by a Camel Syslog Sink Connector
 */
final class CamelSyslogPropertyFactory extends SinkConnectorPropertyFactory<CamelSyslogPropertyFactory> {
    private CamelSyslogPropertyFactory() {

    }

    public CamelSyslogPropertyFactory withHost(String host) {
        return setProperty("camel.sink.path.host", "//" + host);
    }

    public CamelSyslogPropertyFactory withPort(int port) {
        return setProperty("camel.sink.path.port", String.valueOf(port));
    }

    public CamelSyslogPropertyFactory withProtocol(String protocol) {
        return setProperty("camel.sink.path.protocol", protocol);
    }

    public static CamelSyslogPropertyFactory basic() {
        return new CamelSyslogPropertyFactory()
                .withName("CamelSyslogSinkConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.syslog.CamelSyslogSinkConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }
}
