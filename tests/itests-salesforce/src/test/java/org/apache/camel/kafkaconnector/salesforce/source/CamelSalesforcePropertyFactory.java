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

package org.apache.camel.kafkaconnector.salesforce.source;

import org.apache.camel.kafkaconnector.common.EndpointUrlBuilder;
import org.apache.camel.kafkaconnector.common.SourceConnectorPropertyFactory;

final class CamelSalesforcePropertyFactory extends SourceConnectorPropertyFactory<CamelSalesforcePropertyFactory> {
    private CamelSalesforcePropertyFactory() {

    }

    public CamelSalesforcePropertyFactory withClientId(String value) {
        return setProperty("camel.component.salesforce.clientId", value);
    }

    public CamelSalesforcePropertyFactory withClientSecret(String value) {
        return setProperty("camel.component.salesforce.clientSecret", value);
    }

    public CamelSalesforcePropertyFactory withPassword(String value) {
        return setProperty("camel.component.salesforce.password", value);
    }

    public CamelSalesforcePropertyFactory withUserName(String value) {
        return setProperty("camel.component.salesforce.userName", value);
    }

    public CamelSalesforcePropertyFactory withNotifyForFields(String fields) {
        return setProperty("camel.source.endpoint.notifyForFields", fields);
    }

    public CamelSalesforcePropertyFactory withNotifyForOperations(String value) {
        return setProperty("camel.source.endpoint.notifyForOperations", value);
    }

    public CamelSalesforcePropertyFactory withNotifyForOperationCreate(String value) {
        return setProperty("camel.component.salesforce.notifyForOperationCreate", value);
    }

    public CamelSalesforcePropertyFactory withNotifyForOperationDelete(String value) {
        return setProperty("camel.component.salesforce.notifyForOperationDelete", value);
    }

    public CamelSalesforcePropertyFactory withNotifyForOperationUpdate(String value) {
        return setProperty("camel.component.salesforce.notifyForOperationUpdate", value);
    }

    public CamelSalesforcePropertyFactory withHttpClient(String value) {
        return setProperty("camel.source.endpoint.httpClient", classRef(value));
    }

    public CamelSalesforcePropertyFactory withSObjectName(String value) {
        return setProperty("camel.source.endpoint.sObjectName", value);
    }

    public CamelSalesforcePropertyFactory withSObjectQuery(String value) {
        return setProperty("camel.source.endpoint.sObjectQuery", value);
    }

    public CamelSalesforcePropertyFactory withSObjectClass(String value) {
        return setProperty("camel.component.salesforce.sObjectClass", value);
    }

    public CamelSalesforcePropertyFactory withUpdateTopic(boolean value) {
        return setProperty("camel.source.endpoint.updateTopic", value);
    }

    public CamelSalesforcePropertyFactory withLoginUrl(String value) {
        return setProperty("camel.component.salesforce.loginUrl", value);
    }

    public CamelSalesforcePropertyFactory withTopicName(String value) {
        return setProperty("camel.source.path.topicName", value);
    }

    public CamelSalesforcePropertyFactory withRawPayload(boolean value) {
        return setProperty("camel.source.endpoint.rawPayload", value);
    }

    public CamelSalesforcePropertyFactory withPackages(String value) {
        return setProperty("camel.component.salesforce.packages", value);
    }

    public CamelSalesforcePropertyFactory withReplayId(int value) {
        return setProperty("camel.source.endpoint.replayId", value);
    }

    public CamelSalesforcePropertyFactory withApiVersion(String value) {
        return setProperty("camel.component.salesforce.apiVersion", value);
    }

    public EndpointUrlBuilder<CamelSalesforcePropertyFactory> withUrl(String topic) {
        String queueUrl = String.format("salesforce:%s", topic);

        return new EndpointUrlBuilder<>(this::withSourceUrl, queueUrl);
    }

    public static CamelSalesforcePropertyFactory basic() {
        return new CamelSalesforcePropertyFactory()
                .withName("CamelSalesforceConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.salesforce.CamelSalesforceSourceConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withLoginUrl("https://login.salesforce.com");

    }

}
