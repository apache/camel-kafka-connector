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
package org.apache.camel.kafkaconnector.common.utils;

import java.io.IOException;
import java.net.ConnectException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NetworkUtils {
    
    public static final int  DEFAULT_ENDING_PORT = 65535;
    public static final int  DEFAULT_STARTING_PORT = 49152;
    public static int freeStartingPort = DEFAULT_STARTING_PORT;
    private static String hostname;
    private static final Logger LOG = LoggerFactory.getLogger(NetworkUtils.class);

    private NetworkUtils() {
        // utils class
    }

    public static int getFreePort() {
        return getFreePort(getHostname());
    }

    public static int getFreePort(String host) {
        return getFreePort(host, freeStartingPort, DEFAULT_ENDING_PORT);
    }

    public static int getFreePort(String host, Protocol protocol) {
        return getFreePort(host, freeStartingPort, DEFAULT_ENDING_PORT, protocol);
    }

    public static int getFreePort(String host, int startingPort, int endingPort) {
        return getFreePort(host, startingPort, endingPort, Protocol.TCP);
    }

    public static synchronized int getFreePort(String host, int startingPort, int endingPort, Protocol protocol) {
        int freePort = 0;
        for (int i = startingPort; i <= endingPort; i++) {
            boolean found = checkPort(host, i, protocol);
            if (found) {
                freePort = i;
                break;
            }
        }
        return freePort;
    }

    public static boolean checkPort(String host, int port, Protocol protocol) {
        try {
            switch (protocol) {
                case TCP:
                    try (ServerSocket ss = new ServerSocket()) {
                        ss.setReuseAddress(true);
                        ss.bind(new InetSocketAddress(host, port), 1);
                        ss.getLocalPort();
                        if (port == freeStartingPort) {
                            freeStartingPort++;
                        }
                        return true;
                    } catch (IOException e) {
                        return false;
                    }
                case UDP:
                    (new DatagramSocket(new InetSocketAddress(host, port))).close();
                    if (port == freeStartingPort) {
                        freeStartingPort++;
                    }
                    return true;
                default:
                    return false;
            }
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean portIsOpen(String host, int port) {
        try (Socket socket = new Socket(host, port)) {
            return true;
        } catch (UnknownHostException e) {
            LOG.warn("Unknown host: {}", host);
            return false;
        } catch (IOException e) {
            if (e instanceof ConnectException) {
                LOG.info("Port {} is likely closed: {}", port, e.getMessage());
            } else {
                LOG.warn("Unhandled I/O exception: {}", e.getMessage(), e);
            }

            return false;
        }
    }

    public enum Protocol {
        UDP,
        TCP
    }

    public static String getHostname() {
        if (hostname == null) {
            try {
                hostname = InetAddress.getLocalHost().getCanonicalHostName();
            } catch (UnknownHostException e) {
                LOG.warn("Will default to 'localhost' because the code could not get the local hostname: {}",
                        e.getMessage(), e);

                hostname = "localhost";
            }
        }

        return hostname;
    }

    public static String getAddress(String protocol) {
        return String.format("%s://%s:%d", protocol, NetworkUtils.getHostname(), NetworkUtils.getFreePort());
    }

    public static String getAddress(String protocol, int port) {
        return String.format("%s://%s:%d", protocol, NetworkUtils.getHostname(), port);
    }
}
