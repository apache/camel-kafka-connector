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

package org.apache.camel.kafkaconnector.sink.salesforce;

import org.apache.camel.kafkaconnector.SourceConnectorPropertyFactory;

public class CamelSalesforcePropertyFactory extends SourceConnectorPropertyFactory<CamelSalesforcePropertyFactory> {

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

    public CamelSalesforcePropertyFactory withLoginUrl(String value) {
        return setProperty("camel.component.salesforce.loginUrl", value);
    }

    public CamelSalesforcePropertyFactory withRawPayload(boolean value) {
        return setProperty("camel.component.salesforce.rawPayload", value);
    }

    public CamelSalesforcePropertyFactory withPackages(String value) {
        return setProperty("camel.component.salesforce.packages", value);
    }

    public CamelSalesforcePropertyFactory withApiVersion(String value) {
        return setProperty("camel.component.salesforce.apiVersion", value);
    }

    public CamelSalesforcePropertyFactory withOperationName(String value) {
        return setProperty("camel.sink.path.operationName", value);
    }

    public CamelSalesforcePropertyFactory withSObjectName(String value) {
        return setProperty("camel.sink.endpoint.sObjectName", value);
    }

    public static CamelSalesforcePropertyFactory basic() {
        return new CamelSalesforcePropertyFactory()
                .withName("CamelSalesforceConnector")
                .withLoginUrl("https://login.salesforce.com")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.salesforce.CamelSalesforceSinkConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }


}
