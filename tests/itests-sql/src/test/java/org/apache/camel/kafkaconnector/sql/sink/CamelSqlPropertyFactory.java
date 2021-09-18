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

package org.apache.camel.kafkaconnector.sql.sink;

import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;

public final class CamelSqlPropertyFactory extends SinkConnectorPropertyFactory<CamelSqlPropertyFactory> {
    private CamelSqlPropertyFactory() {

    }

    public CamelSqlPropertyFactory withServerName(String value) {
        return setProperty("camel.kamelet.postgresql-sink.serverName", value);
    }

    public CamelSqlPropertyFactory withUsername(String value) {
        return setProperty("camel.kamelet.postgresql-sink.username", value);
    }

    public CamelSqlPropertyFactory withPassword(String value) {
        return setProperty("camel.kamelet.postgresql-sink.password", value);
    }

    public CamelSqlPropertyFactory withQuery(String value) {
        return setProperty("camel.kamelet.postgresql-sink.query", value);
    }

    public CamelSqlPropertyFactory withDatabaseName(String value) {
        return setProperty("camel.kamelet.postgresql-sink.databaseName", value);
    }

    public CamelSqlPropertyFactory withPort(String port) {
        return setProperty("camel.kamelet.postgresql-sink.port", port);
    }

    public static CamelSqlPropertyFactory basic() {
        return new CamelSqlPropertyFactory()
                .withName("CamelSQLSinkConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.postgresqlsink.CamelPostgresqlsinkSinkConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .setProperty("camel.component.kamelet.location", "kamelets")
                .setProperty("camel.component.properties.environment-variable-mode", "1");
    }
}
