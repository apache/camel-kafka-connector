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

package org.apache.camel.kafkaconnector.ftp.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

import org.apache.camel.test.infra.ftp.services.FtpInfraService;
import org.apache.camel.test.infra.ftp.services.embedded.EmbeddedConfiguration;
import org.apache.camel.test.infra.ftp.services.embedded.FtpsEmbeddedService;

/**
 * Creates the embedded FTPS server used by the tests and provides the connector configuration that verifies the
 * identity of the FTPS and SFTP servers.
 */
public final class FtpTestServers {
    // The key store bundled with camel-test-infra-ftp, holding the self-signed certificate of the FTPS server. It is
    // used from there rather than copied into this module, so that it follows the Camel upgrades.
    private static final String BUNDLED_KEY_STORE = "/server.jks";
    private static final String KEY_STORE_PASSWORD = "password";
    private static final Path KEY_STORE = Paths.get("target", "ftps", "server.jks").toAbsolutePath();
    private static final Path KNOWN_HOSTS = Paths.get("target", "sftp", "known_hosts").toAbsolutePath();

    private FtpTestServers() {

    }

    /**
     * Creates an FTPS server running in explicit mode: the ftps Kamelets have no option to use the implicit one.
     *
     * @return the FTPS service, to be registered as a JUnit extension
     */
    public static FtpsEmbeddedService createFtpsService() {
        copyBundledKeyStore();

        return new BundledKeyStoreFtpsService();
    }

    /**
     * The connector configuration that makes the ftps component trust the certificate of the FTPS server, through the
     * global SSL configuration of the Camel context.
     *
     * @return the connector properties
     */
    public static Properties ftpsTrustProperties() {
        final String keyStore = "file:" + KEY_STORE;

        Properties properties = new Properties();
        properties.put("camel.ssl.enabled", "true");
        // Camel main only creates the global SSL context parameters when a key store is configured
        properties.put("camel.ssl.keyStore", keyStore);
        properties.put("camel.ssl.keystorePassword", KEY_STORE_PASSWORD);
        properties.put("camel.ssl.trustStore", keyStore);
        properties.put("camel.ssl.trustStorePassword", KEY_STORE_PASSWORD);
        properties.put("camel.component.ftps.useGlobalSslContextParameters", "true");

        return properties;
    }

    /**
     * The connector configuration that makes the sftp component verify the host key of the SFTP server against a
     * known_hosts file. The sftp Kamelets have no option for it, so it is set on the component.
     *
     * @param service the running SFTP service
     * @return the connector properties
     */
    public static Properties sftpKnownHostsProperties(FtpInfraService service) {
        try {
            Files.createDirectories(KNOWN_HOSTS.getParent());
            Files.writeString(KNOWN_HOSTS, service.knownHostsEntry() + System.lineSeparator());
        } catch (IOException e) {
            throw new UncheckedIOException("Unable to write the known hosts file " + KNOWN_HOSTS, e);
        }

        Properties properties = new Properties();
        properties.put("camel.component.sftp.knownHostsFile", KNOWN_HOSTS.toString());

        return properties;
    }

    private static void copyBundledKeyStore() {
        try (InputStream keyStore = FtpsEmbeddedService.class.getResourceAsStream(BUNDLED_KEY_STORE)) {
            if (keyStore == null) {
                throw new IllegalStateException("The key store bundled with camel-test-infra-ftp was not found");
            }

            Files.createDirectories(KEY_STORE.getParent());
            Files.copy(keyStore, KEY_STORE, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new UncheckedIOException("Unable to copy the FTPS key store to " + KEY_STORE, e);
        }
    }

    /**
     * FtpsEmbeddedService only looks for its key store at ./src/test/resources/server.jks, so it is pointed to the copy
     * of the bundled one instead.
     */
    private static final class BundledKeyStoreFtpsService extends FtpsEmbeddedService {
        BundledKeyStoreFtpsService() {
            // explicit mode, TLSv1.2 (as in the Camel FTPS tests using this server) and no client authentication
            super(new EmbeddedConfiguration.SecurityConfiguration(false, "TLSv1.2", false));

            embeddedConfigurationTemplate.withKeyStore(KEY_STORE.toString());
        }
    }
}
