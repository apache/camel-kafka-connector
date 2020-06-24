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

package org.apache.camel.kafkaconnector.mongodb.sink;


import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;

final class CamelMongoDBPropertyFactory extends SinkConnectorPropertyFactory<CamelMongoDBPropertyFactory> {

    private CamelMongoDBPropertyFactory() {

    }

    public CamelMongoDBPropertyFactory withConnectionBean(String connectionBean, String ref) {
        setProperty("camel.sink.path.connectionBean", connectionBean);
        return withBeans(connectionBean, ref);
    }

    public CamelMongoDBPropertyFactory withDatabase(String database) {
        return setProperty("camel.sink.endpoint.database", database);
    }

    public CamelMongoDBPropertyFactory withOperation(String operation) {
        return setProperty("camel.sink.endpoint.operation", operation);
    }

    public CamelMongoDBPropertyFactory withCollection(String connection) {
        return setProperty("camel.sink.endpoint.collection", connection);
    }

    public static CamelMongoDBPropertyFactory basic() {
        return new CamelMongoDBPropertyFactory()
                .withName("CamelMongoDBSinkConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.mongodb.CamelMongodbSinkConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }
}
