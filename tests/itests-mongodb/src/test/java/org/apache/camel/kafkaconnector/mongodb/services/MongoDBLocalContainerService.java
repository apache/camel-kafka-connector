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

package org.apache.camel.kafkaconnector.mongodb.services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.MongoDBContainer;

public class MongoDBLocalContainerService implements MongoDBService {
    private static final Logger LOG = LoggerFactory.getLogger(MongoDBLocalContainerService.class);
    private static final int DEFAULT_MONGODB_PORT = 27017;
    private final MongoDBContainer container;

    public MongoDBLocalContainerService() {
        String containerName = System.getProperty("mongodb.container");

        if (containerName == null || containerName.isEmpty()) {
            container = new MongoDBContainer();
        } else {
            container = new MongoDBContainer(containerName);
        }

        container.start();
    }

    @Override
    public String getReplicaSetUrl() {
        return container.getReplicaSetUrl();
    }

    @Override
    public String getHost() {
        return container.getContainerIpAddress();
    }

    @Override
    public int getPort() {
        return container.getMappedPort(DEFAULT_MONGODB_PORT);
    }

    @Override
    public MongoClient getClient() {
        return MongoClients.create(getReplicaSetUrl());
    }

    @Override
    public void initialize() {
        LOG.info("MongoDB service running at {}", container.getReplicaSetUrl());
    }

    @Override
    public void shutdown() {
        LOG.info("Stopping the MongoDB container");
        container.stop();
    }
}
