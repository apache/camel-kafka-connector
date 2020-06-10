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

package org.apache.camel.kafkaconnector.sjms2.services;

import java.util.Properties;

import org.apache.camel.kafkaconnector.sjms2.clients.JMSClient;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

public abstract class JMSContainer extends GenericContainer<JMSContainer> {


    public JMSContainer(ImageFromDockerfile dockerfile) {
        super(dockerfile);
    }

    /**
     * Gets the connection properties for accessing the service
     * @return
     */
    public abstract Properties getConnectionProperties();


    /**
     * Get a client that can access the container
     * @return
     */
    public abstract JMSClient getClient();

    /**
     * Gets the end point URL used exchanging messages through the default acceptor port
     * @return the end point URL as a string
     */
    public abstract String getDefaultEndpoint();
}
