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

package org.apache.camel.kafkaconnector.common;

import java.util.function.Function;

public class EndpointUrlBuilder<T extends ConnectorPropertyFactory> {
    private final Function<String, T> setter;
    private String baseUrl;
    private boolean started;


    public EndpointUrlBuilder(Function<String, T> setter, String baseUrl) {
        this.setter = setter;
        this.baseUrl = baseUrl;
    }

    private EndpointUrlBuilder<T> start() {
        if (started) {
            throw new RuntimeException("Already started preparing the URL for formatting");
        }

        started = true;

        return this;
    }

    public EndpointUrlBuilder<T> append(String name, String value) {
        if (!started) {
            start();

            baseUrl = String.format("%s?%s=%s", baseUrl, name, value);
        } else {
            baseUrl = String.format("%s&%s=%s", baseUrl, name, value);
        }

        return this;
    }

    public EndpointUrlBuilder<T> appendIfAvailable(String name, String value) {
        if (value != null && !value.isEmpty()) {
            return append(name, value);
        }

        return this;
    }

    public EndpointUrlBuilder<T> append(String name, int value) {
        if (!started) {
            start();

            baseUrl = String.format("%s?%s=%d", baseUrl, name, value);
        } else {
            baseUrl = String.format("%s&%s=%d", baseUrl, name, value);
        }

        return this;
    }

    public T buildUrl() {
        return setter.apply(baseUrl);
    }
}
