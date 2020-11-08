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
package org.apache.camel.kafkaconnector.azurestorageblob;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelAzurestorageblobSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_PATH_ACCOUNT_NAME_CONF = "camel.source.path.accountName";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_PATH_ACCOUNT_NAME_DOC = "Azure account name to be used for authentication with azure blob services";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_PATH_ACCOUNT_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_PATH_CONTAINER_NAME_CONF = "camel.source.path.containerName";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_PATH_CONTAINER_NAME_DOC = "The blob container name";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_PATH_CONTAINER_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_AUTO_DISCOVER_CLIENT_CONF = "camel.source.endpoint.autoDiscoverClient";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_AUTO_DISCOVER_CLIENT_DOC = "Setting the autoDiscoverClient mechanism, if true, the component will look for a client instance in the registry automatically otherwise it will skip that checking.";
    public static final Boolean CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_AUTO_DISCOVER_CLIENT_DEFAULT = true;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_NAME_CONF = "camel.source.endpoint.blobName";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_NAME_DOC = "The blob name, to consume specific blob from a container. However on producer, is only required for the operations on the blob level";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_OFFSET_CONF = "camel.source.endpoint.blobOffset";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_OFFSET_DOC = "Set the blob offset for the upload or download operations, default is 0";
    public static final Long CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_OFFSET_DEFAULT = 0L;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_SERVICE_CLIENT_CONF = "camel.source.endpoint.blobServiceClient";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_SERVICE_CLIENT_DOC = "Client to a storage account. This client does not hold any state about a particular storage account but is instead a convenient way of sending off appropriate requests to the resource on the service. It may also be used to construct URLs to blobs and containers. This client contains operations on a service account. Operations on a container are available on BlobContainerClient through getBlobContainerClient(String), and operations on a blob are available on BlobClient through getBlobContainerClient(String).getBlobClient(String).";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_SERVICE_CLIENT_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_TYPE_CONF = "camel.source.endpoint.blobType";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_TYPE_DOC = "The blob type in order to initiate the appropriate settings for each blob type One of: [blockblob] [appendblob] [pageblob]";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_TYPE_DEFAULT = "blockblob";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_CLOSE_STREAM_AFTER_READ_CONF = "camel.source.endpoint.closeStreamAfterRead";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_CLOSE_STREAM_AFTER_READ_DOC = "Close the stream after read or keep it open, default is true";
    public static final Boolean CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_CLOSE_STREAM_AFTER_READ_DEFAULT = true;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_CREDENTIALS_CONF = "camel.source.endpoint.credentials";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_CREDENTIALS_DOC = "StorageSharedKeyCredential can be injected to create the azure client, this holds the important authentication information";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_CREDENTIALS_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_DATA_COUNT_CONF = "camel.source.endpoint.dataCount";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_DATA_COUNT_DOC = "How many bytes to include in the range. Must be greater than or equal to 0 if specified.";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_DATA_COUNT_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_FILE_DIR_CONF = "camel.source.endpoint.fileDir";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_FILE_DIR_DOC = "The file directory where the downloaded blobs will be saved to, this can be used in both, producer and consumer";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_FILE_DIR_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_MAX_RESULTS_PER_PAGE_CONF = "camel.source.endpoint.maxResultsPerPage";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_MAX_RESULTS_PER_PAGE_DOC = "Specifies the maximum number of blobs to return, including all BlobPrefix elements. If the request does not specify maxResultsPerPage or specifies a value greater than 5,000, the server will return up to 5,000 items.";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_MAX_RESULTS_PER_PAGE_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_MAX_RETRY_REQUESTS_CONF = "camel.source.endpoint.maxRetryRequests";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_MAX_RETRY_REQUESTS_DOC = "Specifies the maximum number of additional HTTP Get requests that will be made while reading the data from a response body.";
    public static final Integer CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_MAX_RETRY_REQUESTS_DEFAULT = 0;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_PREFIX_CONF = "camel.source.endpoint.prefix";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_PREFIX_DOC = "Filters the results to return only blobs whose names begin with the specified prefix. May be null to return all blobs.";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_PREFIX_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_REGEX_CONF = "camel.source.endpoint.regex";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_REGEX_DOC = "Filters the results to return only blobs whose names match the specified regular expression. May be null to return all if both prefix and regex are set, regex takes the priority and prefix is ignored.";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_REGEX_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_SERVICE_CLIENT_CONF = "camel.source.endpoint.serviceClient";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_SERVICE_CLIENT_DOC = "Client to a storage account. This client does not hold any state about a particular storage account but is instead a convenient way of sending off appropriate requests to the resource on the service. It may also be used to construct URLs to blobs and containers. This client contains operations on a service account. Operations on a container are available on BlobContainerClient through BlobServiceClient#getBlobContainerClient(String), and operations on a blob are available on BlobClient through BlobContainerClient#getBlobClient(String).";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_SERVICE_CLIENT_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_TIMEOUT_CONF = "camel.source.endpoint.timeout";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_TIMEOUT_DOC = "An optional timeout value beyond which a RuntimeException will be raised.";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_TIMEOUT_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF = "camel.source.endpoint.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_EXCEPTION_HANDLER_CONF = "camel.source.endpoint.exceptionHandler";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_EXCEPTION_HANDLER_DOC = "To let the consumer use a custom ExceptionHandler. Notice if the option bridgeErrorHandler is enabled then this option is not in use. By default the consumer will deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_EXCEPTION_HANDLER_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_EXCHANGE_PATTERN_CONF = "camel.source.endpoint.exchangePattern";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_EXCHANGE_PATTERN_DOC = "Sets the exchange pattern when the consumer creates an exchange. One of: [InOnly] [InOut] [InOptionalOut]";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_EXCHANGE_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.source.endpoint.basicPropertyBinding";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_SYNCHRONOUS_CONF = "camel.source.endpoint.synchronous";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_ACCESS_KEY_CONF = "camel.source.endpoint.accessKey";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_ACCESS_KEY_DOC = "Access key for the associated azure account name to be used for authentication with azure blob services";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_ACCESS_KEY_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_AUTO_DISCOVER_CLIENT_CONF = "camel.component.azure-storage-blob.autoDiscoverClient";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_AUTO_DISCOVER_CLIENT_DOC = "Setting the autoDiscoverClient mechanism, if true, the component will look for a client instance in the registry automatically otherwise it will skip that checking.";
    public static final Boolean CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_AUTO_DISCOVER_CLIENT_DEFAULT = true;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BLOB_NAME_CONF = "camel.component.azure-storage-blob.blobName";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BLOB_NAME_DOC = "The blob name, to consume specific blob from a container. However on producer, is only required for the operations on the blob level";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BLOB_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BLOB_OFFSET_CONF = "camel.component.azure-storage-blob.blobOffset";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BLOB_OFFSET_DOC = "Set the blob offset for the upload or download operations, default is 0";
    public static final Long CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BLOB_OFFSET_DEFAULT = 0L;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BLOB_TYPE_CONF = "camel.component.azure-storage-blob.blobType";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BLOB_TYPE_DOC = "The blob type in order to initiate the appropriate settings for each blob type One of: [blockblob] [appendblob] [pageblob]";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BLOB_TYPE_DEFAULT = "blockblob";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_CLOSE_STREAM_AFTER_READ_CONF = "camel.component.azure-storage-blob.closeStreamAfterRead";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_CLOSE_STREAM_AFTER_READ_DOC = "Close the stream after read or keep it open, default is true";
    public static final Boolean CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_CLOSE_STREAM_AFTER_READ_DEFAULT = true;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_CONFIGURATION_CONF = "camel.component.azure-storage-blob.configuration";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_CONFIGURATION_DOC = "The component configurations";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_CREDENTIALS_CONF = "camel.component.azure-storage-blob.credentials";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_CREDENTIALS_DOC = "StorageSharedKeyCredential can be injected to create the azure client, this holds the important authentication information";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_CREDENTIALS_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_DATA_COUNT_CONF = "camel.component.azure-storage-blob.dataCount";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_DATA_COUNT_DOC = "How many bytes to include in the range. Must be greater than or equal to 0 if specified.";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_DATA_COUNT_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_FILE_DIR_CONF = "camel.component.azure-storage-blob.fileDir";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_FILE_DIR_DOC = "The file directory where the downloaded blobs will be saved to, this can be used in both, producer and consumer";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_FILE_DIR_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_MAX_RESULTS_PER_PAGE_CONF = "camel.component.azure-storage-blob.maxResultsPerPage";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_MAX_RESULTS_PER_PAGE_DOC = "Specifies the maximum number of blobs to return, including all BlobPrefix elements. If the request does not specify maxResultsPerPage or specifies a value greater than 5,000, the server will return up to 5,000 items.";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_MAX_RESULTS_PER_PAGE_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_MAX_RETRY_REQUESTS_CONF = "camel.component.azure-storage-blob.maxRetryRequests";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_MAX_RETRY_REQUESTS_DOC = "Specifies the maximum number of additional HTTP Get requests that will be made while reading the data from a response body.";
    public static final Integer CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_MAX_RETRY_REQUESTS_DEFAULT = 0;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_PREFIX_CONF = "camel.component.azure-storage-blob.prefix";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_PREFIX_DOC = "Filters the results to return only blobs whose names begin with the specified prefix. May be null to return all blobs.";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_PREFIX_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_REGEX_CONF = "camel.component.azure-storage-blob.regex";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_REGEX_DOC = "Filters the results to return only blobs whose names match the specified regular expression. May be null to return all if both prefix and regex are set, regex takes the priority and prefix is ignored.";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_REGEX_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_SERVICE_CLIENT_CONF = "camel.component.azure-storage-blob.serviceClient";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_SERVICE_CLIENT_DOC = "Client to a storage account. This client does not hold any state about a particular storage account but is instead a convenient way of sending off appropriate requests to the resource on the service. It may also be used to construct URLs to blobs and containers. This client contains operations on a service account. Operations on a container are available on BlobContainerClient through BlobServiceClient#getBlobContainerClient(String), and operations on a blob are available on BlobClient through BlobContainerClient#getBlobClient(String).";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_SERVICE_CLIENT_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_TIMEOUT_CONF = "camel.component.azure-storage-blob.timeout";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_TIMEOUT_DOC = "An optional timeout value beyond which a RuntimeException will be raised.";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_TIMEOUT_DEFAULT = null;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BRIDGE_ERROR_HANDLER_CONF = "camel.component.azure-storage-blob.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.azure-storage-blob.basicPropertyBinding";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_ACCESS_KEY_CONF = "camel.component.azure-storage-blob.accessKey";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_ACCESS_KEY_DOC = "Access key for the associated azure account name to be used for authentication with azure blob services";
    public static final String CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_ACCESS_KEY_DEFAULT = null;

    public CamelAzurestorageblobSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelAzurestorageblobSourceConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_PATH_ACCOUNT_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_PATH_ACCOUNT_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_PATH_ACCOUNT_NAME_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_PATH_CONTAINER_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_PATH_CONTAINER_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_PATH_CONTAINER_NAME_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_AUTO_DISCOVER_CLIENT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_AUTO_DISCOVER_CLIENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_AUTO_DISCOVER_CLIENT_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_NAME_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_OFFSET_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_OFFSET_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_OFFSET_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_SERVICE_CLIENT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_SERVICE_CLIENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_SERVICE_CLIENT_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BLOB_TYPE_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_CLOSE_STREAM_AFTER_READ_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_CLOSE_STREAM_AFTER_READ_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_CLOSE_STREAM_AFTER_READ_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_CREDENTIALS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_CREDENTIALS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_CREDENTIALS_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_DATA_COUNT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_DATA_COUNT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_DATA_COUNT_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_FILE_DIR_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_FILE_DIR_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_FILE_DIR_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_MAX_RESULTS_PER_PAGE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_MAX_RESULTS_PER_PAGE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_MAX_RESULTS_PER_PAGE_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_MAX_RETRY_REQUESTS_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_MAX_RETRY_REQUESTS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_MAX_RETRY_REQUESTS_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_PREFIX_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_PREFIX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_PREFIX_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_REGEX_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_REGEX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_REGEX_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_SERVICE_CLIENT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_SERVICE_CLIENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_SERVICE_CLIENT_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_TIMEOUT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_TIMEOUT_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_EXCEPTION_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_EXCEPTION_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_EXCEPTION_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_EXCHANGE_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_EXCHANGE_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_EXCHANGE_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_ACCESS_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_ACCESS_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_ENDPOINT_ACCESS_KEY_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_AUTO_DISCOVER_CLIENT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_AUTO_DISCOVER_CLIENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_AUTO_DISCOVER_CLIENT_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BLOB_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BLOB_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BLOB_NAME_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BLOB_OFFSET_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BLOB_OFFSET_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BLOB_OFFSET_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BLOB_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BLOB_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BLOB_TYPE_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_CLOSE_STREAM_AFTER_READ_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_CLOSE_STREAM_AFTER_READ_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_CLOSE_STREAM_AFTER_READ_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_CONFIGURATION_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_CREDENTIALS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_CREDENTIALS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_CREDENTIALS_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_DATA_COUNT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_DATA_COUNT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_DATA_COUNT_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_FILE_DIR_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_FILE_DIR_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_FILE_DIR_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_MAX_RESULTS_PER_PAGE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_MAX_RESULTS_PER_PAGE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_MAX_RESULTS_PER_PAGE_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_MAX_RETRY_REQUESTS_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_MAX_RETRY_REQUESTS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_MAX_RETRY_REQUESTS_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_PREFIX_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_PREFIX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_PREFIX_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_REGEX_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_REGEX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_REGEX_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_SERVICE_CLIENT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_SERVICE_CLIENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_SERVICE_CLIENT_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_TIMEOUT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_TIMEOUT_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.LOW, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_ACCESS_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_ACCESS_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_AZURESTORAGEBLOB_COMPONENT_ACCESS_KEY_DOC);
        return conf;
    }
}