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

package org.apache.camel.kafkaconnector.jdbc.sink;


import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;

public final class CamelJDBCPropertyFactory extends SinkConnectorPropertyFactory<CamelJDBCPropertyFactory> {
    private CamelJDBCPropertyFactory() {

    }

    public CamelJDBCPropertyFactory withDataSource(String value) {
        return setProperty("camel.component.jdbc.dataSource", value);
    }

    public CamelJDBCPropertyFactory withDataSourceName(String value) {
        return setProperty("camel.sink.path.dataSourceName", value);
    }

    public CamelJDBCPropertyFactory withUseHeaderAsParameters(boolean value) {
        return setProperty("camel.sink.endpoint.useHeadersAsParameters", value);
    }

    public static CamelJDBCPropertyFactory basic() {
        return new CamelJDBCPropertyFactory()
                .withName("CamelJDBCSinkConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.jdbc.CamelJdbcSinkConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }
}
