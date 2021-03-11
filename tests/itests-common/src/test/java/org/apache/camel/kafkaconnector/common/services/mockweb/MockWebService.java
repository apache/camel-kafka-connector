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

package org.apache.camel.kafkaconnector.common.services.mockweb;

import java.security.KeyStore;
import java.util.stream.IntStream;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockWebService implements BeforeEachCallback, AfterEachCallback {
    private static final Logger LOG = LoggerFactory.getLogger(MockWebService.class);

    private boolean useHttps;
    private String keystore;
    private String keystorePassword;
    private String truststore;
    private String truststorePassword;

    private MockWebServer server;

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        LOG.debug("Starting MockWebServer...");
        server = new MockWebServer();
        if (useHttps) {
            KeyManagerFactory kmFactory = null;
            if (keystore != null) {
                KeyStore keyStore = KeyStore.getInstance("JKS");
                String password = keystorePassword == null ? "" : keystorePassword;
                keyStore.load(getClass().getResourceAsStream(keystore), password.toCharArray());
                kmFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                kmFactory.init(keyStore, password.toCharArray());
            }
            TrustManagerFactory tmFactory = null;
            if (truststore != null) {
                KeyStore trustStore = KeyStore.getInstance("JKS");
                String password = truststorePassword == null ? "" : truststorePassword;
                trustStore.load(getClass().getResourceAsStream(truststore), password.toCharArray());
                tmFactory = TrustManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                tmFactory.init(trustStore);
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(
                    kmFactory == null ? null : kmFactory.getKeyManagers(),
                    tmFactory == null ? null : tmFactory.getTrustManagers(),
                    null);
            server.useHttps(sslContext.getSocketFactory(), false);
            LOG.debug("Use HTTPS: keystore={}, truststore={}", keystore, truststore);
        }
        server.start();
        LOG.info("MockWebServer started");
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        if (server != null) {
            server.shutdown();
            server = null;
            LOG.info("MockWebServer shutdown");
        }
    }

    public void enqueueResponses(int count) {
        IntStream.range(0, count).forEach(i -> {
            server.enqueue(new MockResponse().setResponseCode(200));
        });
    }

    public MockWebServer getServer() {
        return server;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private boolean useHttps;
        private String keystore;
        private String keystorePassword;
        private String truststore;
        private String truststorePassword;

        public Builder useHttps() {
            useHttps = true;
            return this;
        }

        public Builder withKeystore(String store, String password) {
            this.keystore = store;
            this.keystorePassword = password;
            return this;
        }

        public Builder withTruststore(String store, String password) {
            this.truststore = store;
            this.truststorePassword = password;
            return this;
        }

        public MockWebService build() {
            MockWebService service = new MockWebService();
            service.useHttps = this.useHttps;
            service.keystore = this.keystore;
            service.keystorePassword = this.keystorePassword;
            service.truststore = this.truststore;
            service.truststorePassword = this.truststorePassword;
            return service;
        }
    }
}
