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

package org.apache.camel.kafkaconnector.hdfs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.Network;

public class ContainerLocalHDFSService implements HDFSService {
    private static final Logger LOG = LoggerFactory.getLogger(ContainerLocalHDFSService.class);
    private final NameNodeContainer nameNodeContainer;
    private final DataNodeContainer dataNodeContainer;

    public ContainerLocalHDFSService() {
        Network network = Network.newNetwork();

        nameNodeContainer = new NameNodeContainer(network);
        dataNodeContainer = new DataNodeContainer(network);
    }

    @Override
    public String getHDFSHost() {
        return nameNodeContainer.getContainerIpAddress();
    }

    @Override
    public int getPort() {
        return nameNodeContainer.getIpcPort();
    }

    @Override
    public void initialize() {
        nameNodeContainer.start();

        String hdfsNameNodeWeb = nameNodeContainer.getContainerIpAddress() + ":" + nameNodeContainer.getHttpPort();
        LOG.info("HDFS Name node web UI running at address http://{}", hdfsNameNodeWeb);

        dataNodeContainer.start();

        String hdfsDataNodeWeb = dataNodeContainer.getContainerIpAddress() + ":" + dataNodeContainer.getHttpPort();
        LOG.info("HDFS Data node web UI running at address http://{}", hdfsDataNodeWeb);
        LOG.info("HDFS Data node running at address {}:{}", getHDFSHost(), getPort());
    }

    @Override
    public void shutdown() {
        dataNodeContainer.stop();
        nameNodeContainer.stop();
    }
}
