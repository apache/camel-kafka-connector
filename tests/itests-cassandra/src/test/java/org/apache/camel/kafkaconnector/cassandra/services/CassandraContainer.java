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
package org.apache.camel.kafkaconnector.cassandra.services;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

/**
 * A local instance of Apache Cassandra
 */
public class CassandraContainer extends GenericContainer<CassandraContainer> {
    private static final String CASSANDRA_IMAGE = "cassandra:3.11";
    private static final int CQL3_PORT = 9042;

    public CassandraContainer() {
        super(CASSANDRA_IMAGE);

        withExposedPorts(CQL3_PORT);

        waitingFor(Wait.forListeningPort());
    }

    public int getCQL3Port() {
        return getMappedPort(CQL3_PORT);
    }

    public String getCassandraHost() {
        return getContainerIpAddress();
    }

}
