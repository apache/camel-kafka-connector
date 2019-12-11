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

package org.apache.camel.kafkaconnector.services.jms;

import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;

public class QpidDispatchRouterService extends JMSService {
    private static final int DEFAULT_AMQP_PORT = 5672;


    public QpidDispatchRouterService() {
        super(new ImageFromDockerfile()
                .withFileFromClasspath("Dockerfile",
                        "org/apache/camel/kafkaconnector/services/jms/qpid-dispatch-router/Dockerfile"));

        withExposedPorts(DEFAULT_AMQP_PORT);

        waitingFor(Wait.forListeningPort());
    }


    /**
     * Gets the port number used for exchanging messages using the AMQP protocol
     * @return the port number
     */
    public int getAMQPPort() {
        return getMappedPort(DEFAULT_AMQP_PORT);
    }


    /**
     * Gets the end point URL used exchanging messages using the AMQP protocol (ie.: tcp://host:${amqp.port})
     * @return the end point URL as a string
     */
    public String getAMQPEndpoint() {
        return String.format("amqp://localhost:%d", getAMQPPort());
    }

    @Override
    public String getDefaultEndpoint() {
        return getAMQPEndpoint();
    }
}
