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

package org.apache.camel.kafkaconnector;

import org.testcontainers.containers.GenericContainer;

public class HTTPEchoService extends GenericContainer {
    private static final int DEFAULT_HTTP_PORT = 80;
    private static final int DEFAULT_HTTPS_PORT = 443;

    public HTTPEchoService() {
        super("kennethreitz/httpbin");

        withExposedPorts(new Integer[]{DEFAULT_HTTP_PORT, DEFAULT_HTTPS_PORT});
    }

    /**
     * Gets the port number used for exchanging messages using the HTTP protocol
     *
     * @return the port number
     */
    public int getHTTPPort() {
        return getMappedPort(DEFAULT_HTTP_PORT);
    }

    /**
     * Gets the port number used for exchanging messages using the HTTPS protocol
     *
     * @return the port number
     */
    public int getHTTPSPort() {
        return getMappedPort(DEFAULT_HTTPS_PORT);
    }


    /**
     * Gets the end point URL used exchanging messages using the HTTP protocol (ie.: http://host:${http.port})
     *
     * @return the end point URL as a string
     */
    public String getHTTPEndpoint() {
        return String.format("http://localhost:%d", getHTTPPort());
    }

    /**
     * Gets the end point URL used exchanging messages using the HTTPS protocol (ie.: https://host:${https.port})
     *
     * @return the end point URL as a string
     */
    public String getHTTPSEndpoint() {
        return String.format("https://localhost:%d", getHTTPSPort());
    }

}


