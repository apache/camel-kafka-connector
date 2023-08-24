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
package org.apache.camel.kafkaconnector.mongodb.common;

import org.apache.camel.test.infra.common.services.ContainerService;
import org.apache.camel.test.infra.mongodb.services.MongoDBLocalContainerService;
import org.apache.camel.test.infra.mongodb.services.MongoDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class CkcMongoDBLocalContainerService implements MongoDBService, ContainerService<GenericContainer> {
    private static final Logger LOG = LoggerFactory.getLogger(MongoDBLocalContainerService.class);
    private static final int DEFAULT_MONGODB_PORT = 27017;
    private final GenericContainer container;
    private final String username;
    private final String password;

    public CkcMongoDBLocalContainerService(String username, String password) {
        this(System.getProperty("mongodb.container"), username, password);
    }

    public CkcMongoDBLocalContainerService() {
        this(System.getProperty("mongodb.container"));
    }

    public CkcMongoDBLocalContainerService(String imageName) {
        this.container = this.initContainer(imageName);
        this.password = null;
        this.username = null;
    }

    public CkcMongoDBLocalContainerService(String imageName, String username, String password) {
        this.container = this.initContainer(imageName);
        this.password = password;
        this.username = username;
    }

    public CkcMongoDBLocalContainerService(GenericContainer container) {
        this.container = container;
        this.password = null;
        this.username = null;
    }

    protected GenericContainer initContainer(String imageName) {
        return imageName != null && !imageName.isEmpty() ? new GenericContainer(imageName).withExposedPorts(DEFAULT_MONGODB_PORT) : new GenericContainer();
    }

    public String getReplicaSetUrl() {
        if (username == null || password == null) {
            return String.format("mongodb://%s:%s", this.container.getContainerIpAddress(), this.container.getMappedPort(DEFAULT_MONGODB_PORT));
        } else {
            return String.format("mongodb://%s:%s@%s:%s", username, password, this.container.getContainerIpAddress(), this.container.getMappedPort(DEFAULT_MONGODB_PORT));
        }
    }

    public String getConnectionAddress() {
        return this.container.getContainerIpAddress() + ":" + this.container.getMappedPort(DEFAULT_MONGODB_PORT);
    }

    public void registerProperties() {
        System.setProperty("mongodb.url", this.getReplicaSetUrl());
        System.setProperty("mongodb.connection.address", this.getConnectionAddress());
    }

    public void initialize() {
        LOG.info("Trying to start the MongoDB service");
        this.container.waitingFor(Wait.forLogMessage("(?i).*waiting for connections.*", 1));
        this.container.start();
        this.registerProperties();
        LOG.info("MongoDB service running at {}", getReplicaSetUrl());
    }

    public void shutdown() {
        LOG.info("Stopping the MongoDB container");
        this.container.stop();
    }

    public GenericContainer getContainer() {
        return this.container;
    }
}
