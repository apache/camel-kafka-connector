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
package org.apache.camel.kafkaconnector.azureblob;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelAzureblobSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_AZUREBLOB_PATH_CONTAINER_OR_BLOB_URI_CONF = "camel.sink.path.containerOrBlobUri";
    private static final String CAMEL_SINK_AZUREBLOB_PATH_CONTAINER_OR_BLOB_URI_DOC = "Container or Blob compact Uri";
    private static final String CAMEL_SINK_AZUREBLOB_PATH_CONTAINER_OR_BLOB_URI_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_AZURE_BLOB_CLIENT_CONF = "camel.sink.endpoint.azureBlobClient";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_AZURE_BLOB_CLIENT_DOC = "The blob service client";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_AZURE_BLOB_CLIENT_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_OFFSET_CONF = "camel.sink.endpoint.blobOffset";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_OFFSET_DOC = "Set the blob offset for the upload or download operations, default is 0";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_OFFSET_DEFAULT = "0";
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_TYPE_CONF = "camel.sink.endpoint.blobType";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_TYPE_DOC = "Set a blob type, 'blockblob' is default One of: [blockblob] [appendblob] [pageblob]";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_TYPE_DEFAULT = "blockblob";
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_CLOSE_STREAM_AFTER_READ_CONF = "camel.sink.endpoint.closeStreamAfterRead";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_CLOSE_STREAM_AFTER_READ_DOC = "Close the stream after read or keep it open, default is true";
    private static final Boolean CAMEL_SINK_AZUREBLOB_ENDPOINT_CLOSE_STREAM_AFTER_READ_DEFAULT = true;
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_CREDENTIALS_CONF = "camel.sink.endpoint.credentials";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_CREDENTIALS_DOC = "Set the storage credentials, required in most cases";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_CREDENTIALS_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_DATA_LENGTH_CONF = "camel.sink.endpoint.dataLength";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_DATA_LENGTH_DOC = "Set the data length for the download or page blob upload operations";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_DATA_LENGTH_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_FILE_DIR_CONF = "camel.sink.endpoint.fileDir";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_FILE_DIR_DOC = "Set the file directory where the downloaded blobs will be saved to";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_FILE_DIR_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_PUBLIC_FOR_READ_CONF = "camel.sink.endpoint.publicForRead";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_PUBLIC_FOR_READ_DOC = "Storage resources can be public for reading their content, if this property is enabled then the credentials do not have to be set";
    private static final Boolean CAMEL_SINK_AZUREBLOB_ENDPOINT_PUBLIC_FOR_READ_DEFAULT = false;
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_STREAM_READ_SIZE_CONF = "camel.sink.endpoint.streamReadSize";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_STREAM_READ_SIZE_DOC = "Set the minimum read size in bytes when reading the blob content";
    private static final Integer CAMEL_SINK_AZUREBLOB_ENDPOINT_STREAM_READ_SIZE_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_VALIDATE_CLIENT_URICONF = "camel.sink.endpoint.validateClientURI";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_VALIDATE_CLIENT_URIDOC = "Whether to validate the Azure client URI";
    private static final Boolean CAMEL_SINK_AZUREBLOB_ENDPOINT_VALIDATE_CLIENT_URIDEFAULT = true;
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_METADATA_CONF = "camel.sink.endpoint.blobMetadata";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_METADATA_DOC = "Set the blob meta-data";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_METADATA_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_PREFIX_CONF = "camel.sink.endpoint.blobPrefix";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_PREFIX_DOC = "Set a prefix which can be used for listing the blobs";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_PREFIX_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_CLOSE_STREAM_AFTER_WRITE_CONF = "camel.sink.endpoint.closeStreamAfterWrite";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_CLOSE_STREAM_AFTER_WRITE_DOC = "Close the stream after write or keep it open, default is true";
    private static final Boolean CAMEL_SINK_AZUREBLOB_ENDPOINT_CLOSE_STREAM_AFTER_WRITE_DEFAULT = true;
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_AZUREBLOB_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_OPERATION_CONF = "camel.sink.endpoint.operation";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_OPERATION_DOC = "Blob service operation hint to the producer One of: [getBlob] [deleteBlob] [listBlobs] [updateBlockBlob] [uploadBlobBlocks] [commitBlobBlockList] [getBlobBlockList] [createAppendBlob] [updateAppendBlob] [createPageBlob] [updatePageBlob] [resizePageBlob] [clearPageBlob] [getPageBlobRanges]";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_OPERATION_DEFAULT = "listBlobs";
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_STREAM_WRITE_SIZE_CONF = "camel.sink.endpoint.streamWriteSize";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_STREAM_WRITE_SIZE_DOC = "Set the size of the buffer for writing block and page blocks";
    private static final Integer CAMEL_SINK_AZUREBLOB_ENDPOINT_STREAM_WRITE_SIZE_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_USE_FLAT_LISTING_CONF = "camel.sink.endpoint.useFlatListing";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_USE_FLAT_LISTING_DOC = "Specify if the flat or hierarchical blob listing should be used";
    private static final Boolean CAMEL_SINK_AZUREBLOB_ENDPOINT_USE_FLAT_LISTING_DEFAULT = true;
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_AZUREBLOB_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SINK_AZUREBLOB_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_CREDENTIALS_ACCOUNT_KEY_CONF = "camel.sink.endpoint.credentialsAccountKey";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_CREDENTIALS_ACCOUNT_KEY_DOC = "Set the storage account key used during authentication phase";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_CREDENTIALS_ACCOUNT_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_CREDENTIALS_ACCOUNT_NAME_CONF = "camel.sink.endpoint.credentialsAccountName";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_CREDENTIALS_ACCOUNT_NAME_DOC = "Set the storage account name used during authentication phase";
    private static final String CAMEL_SINK_AZUREBLOB_ENDPOINT_CREDENTIALS_ACCOUNT_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_AZURE_BLOB_CLIENT_CONF = "camel.component.azure-blob.azureBlobClient";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_AZURE_BLOB_CLIENT_DOC = "The blob service client";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_AZURE_BLOB_CLIENT_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_OFFSET_CONF = "camel.component.azure-blob.blobOffset";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_OFFSET_DOC = "Set the blob offset for the upload or download operations, default is 0";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_OFFSET_DEFAULT = "0";
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_TYPE_CONF = "camel.component.azure-blob.blobType";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_TYPE_DOC = "Set a blob type, 'blockblob' is default One of: [blockblob] [appendblob] [pageblob]";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_TYPE_DEFAULT = "blockblob";
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_CLOSE_STREAM_AFTER_READ_CONF = "camel.component.azure-blob.closeStreamAfterRead";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_CLOSE_STREAM_AFTER_READ_DOC = "Close the stream after read or keep it open, default is true";
    private static final Boolean CAMEL_SINK_AZUREBLOB_COMPONENT_CLOSE_STREAM_AFTER_READ_DEFAULT = true;
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_CREDENTIALS_CONF = "camel.component.azure-blob.credentials";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_CREDENTIALS_DOC = "Set the storage credentials, required in most cases";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_CREDENTIALS_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_DATA_LENGTH_CONF = "camel.component.azure-blob.dataLength";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_DATA_LENGTH_DOC = "Set the data length for the download or page blob upload operations";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_DATA_LENGTH_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_FILE_DIR_CONF = "camel.component.azure-blob.fileDir";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_FILE_DIR_DOC = "Set the file directory where the downloaded blobs will be saved to";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_FILE_DIR_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_PUBLIC_FOR_READ_CONF = "camel.component.azure-blob.publicForRead";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_PUBLIC_FOR_READ_DOC = "Storage resources can be public for reading their content, if this property is enabled then the credentials do not have to be set";
    private static final Boolean CAMEL_SINK_AZUREBLOB_COMPONENT_PUBLIC_FOR_READ_DEFAULT = false;
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_STREAM_READ_SIZE_CONF = "camel.component.azure-blob.streamReadSize";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_STREAM_READ_SIZE_DOC = "Set the minimum read size in bytes when reading the blob content";
    private static final Integer CAMEL_SINK_AZUREBLOB_COMPONENT_STREAM_READ_SIZE_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_VALIDATE_CLIENT_URICONF = "camel.component.azure-blob.validateClientURI";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_VALIDATE_CLIENT_URIDOC = "Whether to validate the Azure client URI";
    private static final Boolean CAMEL_SINK_AZUREBLOB_COMPONENT_VALIDATE_CLIENT_URIDEFAULT = true;
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_METADATA_CONF = "camel.component.azure-blob.blobMetadata";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_METADATA_DOC = "Set the blob meta-data";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_METADATA_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_PREFIX_CONF = "camel.component.azure-blob.blobPrefix";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_PREFIX_DOC = "Set a prefix which can be used for listing the blobs";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_PREFIX_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_CLOSE_STREAM_AFTER_WRITE_CONF = "camel.component.azure-blob.closeStreamAfterWrite";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_CLOSE_STREAM_AFTER_WRITE_DOC = "Close the stream after write or keep it open, default is true";
    private static final Boolean CAMEL_SINK_AZUREBLOB_COMPONENT_CLOSE_STREAM_AFTER_WRITE_DEFAULT = true;
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.azure-blob.lazyStartProducer";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_AZUREBLOB_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_OPERATION_CONF = "camel.component.azure-blob.operation";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_OPERATION_DOC = "Blob service operation hint to the producer One of: [getBlob] [deleteBlob] [listBlobs] [updateBlockBlob] [uploadBlobBlocks] [commitBlobBlockList] [getBlobBlockList] [createAppendBlob] [updateAppendBlob] [createPageBlob] [updatePageBlob] [resizePageBlob] [clearPageBlob] [getPageBlobRanges]";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_OPERATION_DEFAULT = "listBlobs";
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_STREAM_WRITE_SIZE_CONF = "camel.component.azure-blob.streamWriteSize";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_STREAM_WRITE_SIZE_DOC = "Set the size of the buffer for writing block and page blocks";
    private static final Integer CAMEL_SINK_AZUREBLOB_COMPONENT_STREAM_WRITE_SIZE_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_USE_FLAT_LISTING_CONF = "camel.component.azure-blob.useFlatListing";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_USE_FLAT_LISTING_DOC = "Specify if the flat or hierarchical blob listing should be used";
    private static final Boolean CAMEL_SINK_AZUREBLOB_COMPONENT_USE_FLAT_LISTING_DEFAULT = true;
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.azure-blob.basicPropertyBinding";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_AZUREBLOB_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_CONFIGURATION_CONF = "camel.component.azure-blob.configuration";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_CONFIGURATION_DOC = "The Blob Service configuration";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_CREDENTIALS_ACCOUNT_KEY_CONF = "camel.component.azure-blob.credentialsAccountKey";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_CREDENTIALS_ACCOUNT_KEY_DOC = "Set the storage account key used during authentication phase";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_CREDENTIALS_ACCOUNT_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_AZUREBLOB_COMPONENT_CREDENTIALS_ACCOUNT_NAME_CONF = "camel.component.azure-blob.credentialsAccountName";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_CREDENTIALS_ACCOUNT_NAME_DOC = "Set the storage account name used during authentication phase";
    private static final String CAMEL_SINK_AZUREBLOB_COMPONENT_CREDENTIALS_ACCOUNT_NAME_DEFAULT = null;

    public CamelAzureblobSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelAzureblobSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_AZUREBLOB_PATH_CONTAINER_OR_BLOB_URI_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_PATH_CONTAINER_OR_BLOB_URI_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AZUREBLOB_PATH_CONTAINER_OR_BLOB_URI_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_AZURE_BLOB_CLIENT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_ENDPOINT_AZURE_BLOB_CLIENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_AZURE_BLOB_CLIENT_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_OFFSET_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_OFFSET_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_OFFSET_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_TYPE_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_CLOSE_STREAM_AFTER_READ_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AZUREBLOB_ENDPOINT_CLOSE_STREAM_AFTER_READ_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_CLOSE_STREAM_AFTER_READ_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_CREDENTIALS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_ENDPOINT_CREDENTIALS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_CREDENTIALS_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_DATA_LENGTH_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_ENDPOINT_DATA_LENGTH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_DATA_LENGTH_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_FILE_DIR_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_ENDPOINT_FILE_DIR_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_FILE_DIR_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_PUBLIC_FOR_READ_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AZUREBLOB_ENDPOINT_PUBLIC_FOR_READ_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_PUBLIC_FOR_READ_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_STREAM_READ_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SINK_AZUREBLOB_ENDPOINT_STREAM_READ_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_STREAM_READ_SIZE_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_VALIDATE_CLIENT_URICONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AZUREBLOB_ENDPOINT_VALIDATE_CLIENT_URIDEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_VALIDATE_CLIENT_URIDOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_METADATA_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_METADATA_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_METADATA_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_PREFIX_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_PREFIX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_BLOB_PREFIX_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_CLOSE_STREAM_AFTER_WRITE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AZUREBLOB_ENDPOINT_CLOSE_STREAM_AFTER_WRITE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_CLOSE_STREAM_AFTER_WRITE_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AZUREBLOB_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_OPERATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_ENDPOINT_OPERATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_OPERATION_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_STREAM_WRITE_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SINK_AZUREBLOB_ENDPOINT_STREAM_WRITE_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_STREAM_WRITE_SIZE_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_USE_FLAT_LISTING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AZUREBLOB_ENDPOINT_USE_FLAT_LISTING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_USE_FLAT_LISTING_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AZUREBLOB_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AZUREBLOB_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_CREDENTIALS_ACCOUNT_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_ENDPOINT_CREDENTIALS_ACCOUNT_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_CREDENTIALS_ACCOUNT_KEY_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_ENDPOINT_CREDENTIALS_ACCOUNT_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_ENDPOINT_CREDENTIALS_ACCOUNT_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_ENDPOINT_CREDENTIALS_ACCOUNT_NAME_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_AZURE_BLOB_CLIENT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_COMPONENT_AZURE_BLOB_CLIENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_AZURE_BLOB_CLIENT_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_OFFSET_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_OFFSET_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_OFFSET_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_TYPE_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_CLOSE_STREAM_AFTER_READ_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AZUREBLOB_COMPONENT_CLOSE_STREAM_AFTER_READ_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_CLOSE_STREAM_AFTER_READ_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_CREDENTIALS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_COMPONENT_CREDENTIALS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_CREDENTIALS_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_DATA_LENGTH_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_COMPONENT_DATA_LENGTH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_DATA_LENGTH_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_FILE_DIR_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_COMPONENT_FILE_DIR_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_FILE_DIR_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_PUBLIC_FOR_READ_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AZUREBLOB_COMPONENT_PUBLIC_FOR_READ_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_PUBLIC_FOR_READ_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_STREAM_READ_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SINK_AZUREBLOB_COMPONENT_STREAM_READ_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_STREAM_READ_SIZE_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_VALIDATE_CLIENT_URICONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AZUREBLOB_COMPONENT_VALIDATE_CLIENT_URIDEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_VALIDATE_CLIENT_URIDOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_METADATA_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_METADATA_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_METADATA_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_PREFIX_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_PREFIX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_BLOB_PREFIX_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_CLOSE_STREAM_AFTER_WRITE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AZUREBLOB_COMPONENT_CLOSE_STREAM_AFTER_WRITE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_CLOSE_STREAM_AFTER_WRITE_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AZUREBLOB_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_OPERATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_COMPONENT_OPERATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_OPERATION_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_STREAM_WRITE_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SINK_AZUREBLOB_COMPONENT_STREAM_WRITE_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_STREAM_WRITE_SIZE_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_USE_FLAT_LISTING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AZUREBLOB_COMPONENT_USE_FLAT_LISTING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_USE_FLAT_LISTING_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AZUREBLOB_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_COMPONENT_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_CONFIGURATION_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_CREDENTIALS_ACCOUNT_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_COMPONENT_CREDENTIALS_ACCOUNT_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_CREDENTIALS_ACCOUNT_KEY_DOC);
        conf.define(CAMEL_SINK_AZUREBLOB_COMPONENT_CREDENTIALS_ACCOUNT_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AZUREBLOB_COMPONENT_CREDENTIALS_ACCOUNT_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AZUREBLOB_COMPONENT_CREDENTIALS_ACCOUNT_NAME_DOC);
        return conf;
    }
}