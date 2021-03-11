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

package org.apache.camel.kafkaconnector.https.sink;

import org.apache.camel.LoggingLevel;
import org.apache.camel.kafkaconnector.common.EndpointUrlBuilder;
import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;
import org.apache.camel.support.jsse.KeyStoreParameters;
import org.apache.camel.support.jsse.SSLContextParameters;
import org.apache.camel.support.jsse.TrustManagersParameters;

final class CamelHTTPSPropertyFactory extends SinkConnectorPropertyFactory<CamelHTTPSPropertyFactory> {
    private CamelHTTPSPropertyFactory() {
    }

    public CamelHTTPSPropertyFactory withHttpUri(String uri) {
        return setProperty("camel.sink.path.httpUri", uri);
    }

    public CamelHTTPSPropertyFactory withHttpMethod(String method) {
        return setProperty("camel.sink.endpoint.httpMethod", method);
    }

    public CamelHTTPSPropertyFactory withSslContextParameters(String bean, String keyStore, String password) {
        withBeans("ksp", classRef(KeyStoreParameters.class));
        withBeans("ksp.resource", keyStore);
        withBeans("ksp.password", password);

        withBeans("tmp", classRef(TrustManagersParameters.class));
        withBeans("tmp.keyStore", "#bean:ksp");

        withBeans(bean, classRef(SSLContextParameters.class));
        withBeans(bean + ".trustManagers", "#bean:tmp");

        return setProperty("camel.sink.endpoint.sslContextParameters", "#bean:" + bean);
    }

    public CamelHTTPSPropertyFactory withX509HostnameVerifier(String bean, Class<?> verifierClass) {
        withBeans(bean, classRef(verifierClass));
        return setProperty("camel.sink.endpoint.x509HostnameVerifier", "#bean:" + bean);
    }

    public EndpointUrlBuilder<CamelHTTPSPropertyFactory> withUrl(String host, int port, String path) {
        String url = String.format("https://%s:%s/%s", host, port, path);
        return new EndpointUrlBuilder<>(this::withSinkUrl, url);
    }

    public static CamelHTTPSPropertyFactory basic() {
        return new CamelHTTPSPropertyFactory()
                .withName("CamelHttpsSinkConnector")
                .withConnectorClass("org.apache.camel.kafkaconnector.https.CamelHttpsSinkConnector")
                .withTasksMax(1)
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withSinkContentLogginglevel(LoggingLevel.DEBUG);
    }
}
