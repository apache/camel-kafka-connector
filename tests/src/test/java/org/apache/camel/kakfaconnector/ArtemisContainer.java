/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.camel.kakfaconnector;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;

/**
 * A specialized container that can be used to create Apache Artemis broker
 * instances.
 */
public class ArtemisContainer extends GenericContainer {
    private static final int DEFAULT_MQTT_PORT = 1883;
    private static final int DEFAULT_AMQP_PORT = 5672;
    private static final int DEFAULT_ADMIN_PORT = 8161;
    private static final int DEFAULT_ACCEPTOR_PORT = 61616;


    public ArtemisContainer() {
        super(new ImageFromDockerfile()
                .withFileFromClasspath("Dockerfile",
                        "org/apache/camel/kakfaconnector/Dockerfile"));

        withExposedPorts(new Integer[]{DEFAULT_MQTT_PORT, DEFAULT_AMQP_PORT,
                DEFAULT_ADMIN_PORT, DEFAULT_ACCEPTOR_PORT});

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
        return String.format("tcp://localhost:%d", getAMQPPort());
    }


    /**
     * Gets the port number used for exchanging messages using the MQTT protocol
     * @return the port number
     */
    public int getMQTTPort() {
        return getMappedPort(DEFAULT_MQTT_PORT);
    }


    /**
     * Gets the end point URL used exchanging messages using the MQTT protocol (ie.: tcp://host:${mqtt.port})
     * @return the end point URL as a string
     */
    public String getMQTTEndpoint() {
        return String.format("tcp://localhost:%d", getMQTTPort());
    }


    /**
     * Gets the port number used for accessing the web management console or the management API
     * @return the port number
     */
    public int getAdminPort() {
        return getMappedPort(DEFAULT_ADMIN_PORT);
    }


    /**
     * Gets the end point URL used for accessing the web management console or the management API
     * @return the admin URL as a string
     */
    public String getAdminURL() {
        return String.format("http://localhost:%d", getAdminPort());
    }


    /**
     * Gets the port number used for exchanging messages using the default acceptor port
     * @return the port number
     */
    public int getDefaultAcceptorPort() {
        return getMappedPort(DEFAULT_ACCEPTOR_PORT);
    }


    /**
     * Gets the end point URL used exchanging messages through the default acceptor port
     * @return the end point URL as a string
     */
    public String getDefaultAcceptorEndpoint() {
        return String.format("tcp://localhost:%d", getDefaultAcceptorPort());
    }



    /**
     * Gets the port number used for exchanging messages using the Openwire protocol
     * @return the port number
     */
    public int getOpenwirePort() {
        return getDefaultAcceptorPort();
    }


    /**
     * Gets the end point URL used exchanging messages using the Openwire protocol (ie.: tcp://host:${amqp.port})
     * @return the end point URL as a string
     */
    public String getOpenwireEndpoint() {
        return String.format("tcp://localhost:%d", getOpenwirePort());
    }
}
