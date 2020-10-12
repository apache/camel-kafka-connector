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

package org.apache.camel.kafkaconnector.azure.storage.blob.sink;

import org.apache.camel.kafkaconnector.common.EndpointUrlBuilder;
import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;

public class CamelSinkAzureStorageBlobPropertyFactory extends SinkConnectorPropertyFactory<CamelSinkAzureStorageBlobPropertyFactory> {

    public CamelSinkAzureStorageBlobPropertyFactory withAccountName(String value) {
        return setProperty("camel.sink.path.accountName", value);
    }

    public CamelSinkAzureStorageBlobPropertyFactory withContainerName(String value) {
        return setProperty("camel.sink.path.containerName", value);
    }

    public CamelSinkAzureStorageBlobPropertyFactory withAccessKey(String value) {
        return setProperty("camel.sink.endpoint.accessKey", value);
    }

    public CamelSinkAzureStorageBlobPropertyFactory withBlobName(String value) {
        return setProperty("camel.component.azure-storage-blob.blobName", value);
    }

    public CamelSinkAzureStorageBlobPropertyFactory withConfiguration(String configurationClass) {
        return setProperty("camel.component.azure-storage-blob.configuration", classRef(configurationClass));
    }

    public CamelSinkAzureStorageBlobPropertyFactory withOperation(String value) {
        return setProperty("camel.sink.endpoint.operation", value);
    }

    public EndpointUrlBuilder<CamelSinkAzureStorageBlobPropertyFactory> withUrl(String path) {
        String sinkUrl = String.format("azure-storage-blob://%s", path);
    
        return new EndpointUrlBuilder<>(this::withSinkUrl, sinkUrl);
    }


    public static CamelSinkAzureStorageBlobPropertyFactory basic() {
        return new CamelSinkAzureStorageBlobPropertyFactory()
                    .withTasksMax(1)
                    .withName("CamelAzurestorageblobSinkConnector")
                    .withConnectorClass("org.apache.camel.kafkaconnector.azurestorageblob.CamelAzurestorageblobSinkConnector")
                    .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                    .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");

    }
}
