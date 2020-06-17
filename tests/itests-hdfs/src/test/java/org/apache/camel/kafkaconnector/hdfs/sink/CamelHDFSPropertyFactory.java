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

package org.apache.camel.kafkaconnector.hdfs.sink;


import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;

final class CamelHDFSPropertyFactory extends SinkConnectorPropertyFactory<CamelHDFSPropertyFactory> {
    private CamelHDFSPropertyFactory() {

    }

    public CamelHDFSPropertyFactory withHostname(String value) {
        return setProperty("camel.sink.path.hostName", value);
    }

    public CamelHDFSPropertyFactory withPort(int value) {
        return setProperty("camel.sink.path.port", value);
    }

    public CamelHDFSPropertyFactory withPath(String value) {
        return setProperty("camel.sink.path.path", value);
    }

    public CamelHDFSPropertyFactory withSplitStrategy(String value) {
        return setProperty("camel.sink.endpoint.splitStrategy", value);
    }

    public CamelHDFSPropertyFactory withReplication(int value) {
        return setProperty("camel.sink.endpoint.replication", value);
    }

    public CamelHDFSPropertyFactory withOwner(String value) {
        return setProperty("camel.sink.endpoint.owner", value);
    }

    public CamelHDFSPropertyFactory withAppend(boolean value) {
        return setProperty("camel.sink.endpoint.append", value);
    }

    public CamelHDFSPropertyFactory withBufferSize(int value) {
        return setProperty("camel.sink.endpoint.bufferSize", value);
    }


    public static CamelHDFSPropertyFactory basic() {
        return new CamelHDFSPropertyFactory()
                .withName("CamelHDFSSinkConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.hdfs.CamelHdfsSinkConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withReplication(1);
    }

}
