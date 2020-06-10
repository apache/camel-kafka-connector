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

import org.apache.camel.kafkaconnector.cassandra.clients.CassandraClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A service for a local instance of Apache Cassandra running with TestContainers
 */
public class CassandraLocalContainerService implements CassandraService {
    private static final Logger LOG = LoggerFactory.getLogger(CassandraLocalContainerService.class);

    private CassandraContainer container;

    public CassandraLocalContainerService() {
        container = new CassandraContainer();

        container.start();
    }

    @Override
    public int getCQL3Port() {
        return container.getCQL3Port();
    }

    @Override
    public String getCassandraHost() {
        return container.getCassandraHost();
    }

    @Override
    public CassandraClient getClient() {
        String host = getCassandraHost();
        int port = getCQL3Port();

        return new CassandraClient(host, port);
    }

    @Override
    public void initialize() {
        LOG.info("Cassandra server running at address {}", getCQL3Endpoint());
    }

    @Override
    public void shutdown() {
        LOG.info("Stopping the Cassandra container");
        container.stop();
    }
}
