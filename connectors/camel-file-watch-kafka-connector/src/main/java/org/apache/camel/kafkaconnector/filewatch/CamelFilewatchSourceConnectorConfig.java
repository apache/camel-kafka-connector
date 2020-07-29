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
package org.apache.camel.kafkaconnector.filewatch;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelFilewatchSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_FILEWATCH_PATH_PATH_CONF = "camel.source.path.path";
    private static final String CAMEL_SOURCE_FILEWATCH_PATH_PATH_DOC = "Path of directory to consume events from.";
    private static final String CAMEL_SOURCE_FILEWATCH_PATH_PATH_DEFAULT = null;
    public static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_ANT_INCLUDE_CONF = "camel.source.endpoint.antInclude";
    private static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_ANT_INCLUDE_DOC = "ANT style pattern to match files. The file is matched against path relative to endpoint path. Pattern must be also relative (not starting with slash)";
    private static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_ANT_INCLUDE_DEFAULT = "**";
    public static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_AUTO_CREATE_CONF = "camel.source.endpoint.autoCreate";
    private static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_AUTO_CREATE_DOC = "Auto create directory if does not exists.";
    private static final Boolean CAMEL_SOURCE_FILEWATCH_ENDPOINT_AUTO_CREATE_DEFAULT = true;
    public static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF = "camel.source.endpoint.bridgeErrorHandler";
    private static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final Boolean CAMEL_SOURCE_FILEWATCH_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_CONCURRENT_CONSUMERS_CONF = "camel.source.endpoint.concurrentConsumers";
    private static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_CONCURRENT_CONSUMERS_DOC = "The number of concurrent consumers. Increase this value, if your route is slow to prevent buffering in queue.";
    private static final Integer CAMEL_SOURCE_FILEWATCH_ENDPOINT_CONCURRENT_CONSUMERS_DEFAULT = 1;
    public static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_EVENTS_CONF = "camel.source.endpoint.events";
    private static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_EVENTS_DOC = "Comma separated list of events to watch. One of: [CREATE] [MODIFY] [DELETE]";
    private static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_EVENTS_DEFAULT = "CREATE,MODIFY,DELETE";
    public static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_FILE_HASHER_CONF = "camel.source.endpoint.fileHasher";
    private static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_FILE_HASHER_DOC = "Reference to io.methvin.watcher.hashing.FileHasher. This prevents emitting duplicate events on some platforms. For working with large files and if you dont need detect multiple modifications per second per file, use #lastModifiedTimeFileHasher. You can also provide custom implementation in registry.";
    private static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_FILE_HASHER_DEFAULT = "#murmur3FFileHasher";
    public static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_POLL_THREADS_CONF = "camel.source.endpoint.pollThreads";
    private static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_POLL_THREADS_DOC = "The number of threads polling WatchService. Increase this value, if you see OVERFLOW messages in log.";
    private static final Integer CAMEL_SOURCE_FILEWATCH_ENDPOINT_POLL_THREADS_DEFAULT = 1;
    public static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_QUEUE_SIZE_CONF = "camel.source.endpoint.queueSize";
    private static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_QUEUE_SIZE_DOC = "Maximum size of queue between WatchService and consumer. Unbounded by default.";
    private static final Integer CAMEL_SOURCE_FILEWATCH_ENDPOINT_QUEUE_SIZE_DEFAULT = 2147483647;
    public static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_RECURSIVE_CONF = "camel.source.endpoint.recursive";
    private static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_RECURSIVE_DOC = "Watch recursive in current and child directories (including newly created directories).";
    private static final Boolean CAMEL_SOURCE_FILEWATCH_ENDPOINT_RECURSIVE_DEFAULT = true;
    public static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_USE_FILE_HASHING_CONF = "camel.source.endpoint.useFileHashing";
    private static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_USE_FILE_HASHING_DOC = "Enables or disables file hashing to detect duplicate events. If you disable this, you can get some events multiple times on some platforms and JDKs. Check java.nio.file.WatchService limitations for your target platform.";
    private static final Boolean CAMEL_SOURCE_FILEWATCH_ENDPOINT_USE_FILE_HASHING_DEFAULT = true;
    public static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_EXCEPTION_HANDLER_CONF = "camel.source.endpoint.exceptionHandler";
    private static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_EXCEPTION_HANDLER_DOC = "To let the consumer use a custom ExceptionHandler. Notice if the option bridgeErrorHandler is enabled then this option is not in use. By default the consumer will deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_EXCEPTION_HANDLER_DEFAULT = null;
    public static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_EXCHANGE_PATTERN_CONF = "camel.source.endpoint.exchangePattern";
    private static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_EXCHANGE_PATTERN_DOC = "Sets the exchange pattern when the consumer creates an exchange. One of: [InOnly] [InOut] [InOptionalOut]";
    private static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_EXCHANGE_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.source.endpoint.basicPropertyBinding";
    private static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SOURCE_FILEWATCH_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_SYNCHRONOUS_CONF = "camel.source.endpoint.synchronous";
    private static final String CAMEL_SOURCE_FILEWATCH_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SOURCE_FILEWATCH_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SOURCE_FILEWATCH_COMPONENT_BRIDGE_ERROR_HANDLER_CONF = "camel.component.file-watch.bridgeErrorHandler";
    private static final String CAMEL_SOURCE_FILEWATCH_COMPONENT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final Boolean CAMEL_SOURCE_FILEWATCH_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_FILEWATCH_COMPONENT_CONCURRENT_CONSUMERS_CONF = "camel.component.file-watch.concurrentConsumers";
    private static final String CAMEL_SOURCE_FILEWATCH_COMPONENT_CONCURRENT_CONSUMERS_DOC = "The number of concurrent consumers. Increase this value, if your route is slow to prevent buffering in queue.";
    private static final Integer CAMEL_SOURCE_FILEWATCH_COMPONENT_CONCURRENT_CONSUMERS_DEFAULT = 1;
    public static final String CAMEL_SOURCE_FILEWATCH_COMPONENT_FILE_HASHER_CONF = "camel.component.file-watch.fileHasher";
    private static final String CAMEL_SOURCE_FILEWATCH_COMPONENT_FILE_HASHER_DOC = "Reference to io.methvin.watcher.hashing.FileHasher. This prevents emitting duplicate events on some platforms. For working with large files and if you dont need detect multiple modifications per second per file, use #lastModifiedTimeFileHasher. You can also provide custom implementation in registry.";
    private static final String CAMEL_SOURCE_FILEWATCH_COMPONENT_FILE_HASHER_DEFAULT = "#murmur3FFileHasher";
    public static final String CAMEL_SOURCE_FILEWATCH_COMPONENT_POLL_THREADS_CONF = "camel.component.file-watch.pollThreads";
    private static final String CAMEL_SOURCE_FILEWATCH_COMPONENT_POLL_THREADS_DOC = "The number of threads polling WatchService. Increase this value, if you see OVERFLOW messages in log.";
    private static final Integer CAMEL_SOURCE_FILEWATCH_COMPONENT_POLL_THREADS_DEFAULT = 1;
    public static final String CAMEL_SOURCE_FILEWATCH_COMPONENT_QUEUE_SIZE_CONF = "camel.component.file-watch.queueSize";
    private static final String CAMEL_SOURCE_FILEWATCH_COMPONENT_QUEUE_SIZE_DOC = "Maximum size of queue between WatchService and consumer. Unbounded by default.";
    private static final Integer CAMEL_SOURCE_FILEWATCH_COMPONENT_QUEUE_SIZE_DEFAULT = 2147483647;
    public static final String CAMEL_SOURCE_FILEWATCH_COMPONENT_USE_FILE_HASHING_CONF = "camel.component.file-watch.useFileHashing";
    private static final String CAMEL_SOURCE_FILEWATCH_COMPONENT_USE_FILE_HASHING_DOC = "Enables or disables file hashing to detect duplicate events. If you disable this, you can get some events multiple times on some platforms and JDKs. Check java.nio.file.WatchService limitations for your target platform.";
    private static final Boolean CAMEL_SOURCE_FILEWATCH_COMPONENT_USE_FILE_HASHING_DEFAULT = true;
    public static final String CAMEL_SOURCE_FILEWATCH_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.file-watch.basicPropertyBinding";
    private static final String CAMEL_SOURCE_FILEWATCH_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SOURCE_FILEWATCH_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;

    public CamelFilewatchSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelFilewatchSourceConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_FILEWATCH_PATH_PATH_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_FILEWATCH_PATH_PATH_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_FILEWATCH_PATH_PATH_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_ENDPOINT_ANT_INCLUDE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_FILEWATCH_ENDPOINT_ANT_INCLUDE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_ENDPOINT_ANT_INCLUDE_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_ENDPOINT_AUTO_CREATE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_FILEWATCH_ENDPOINT_AUTO_CREATE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_ENDPOINT_AUTO_CREATE_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_FILEWATCH_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_ENDPOINT_CONCURRENT_CONSUMERS_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_FILEWATCH_ENDPOINT_CONCURRENT_CONSUMERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_ENDPOINT_CONCURRENT_CONSUMERS_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_ENDPOINT_EVENTS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_FILEWATCH_ENDPOINT_EVENTS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_ENDPOINT_EVENTS_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_ENDPOINT_FILE_HASHER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_FILEWATCH_ENDPOINT_FILE_HASHER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_ENDPOINT_FILE_HASHER_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_ENDPOINT_POLL_THREADS_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_FILEWATCH_ENDPOINT_POLL_THREADS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_ENDPOINT_POLL_THREADS_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_ENDPOINT_QUEUE_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_FILEWATCH_ENDPOINT_QUEUE_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_ENDPOINT_QUEUE_SIZE_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_ENDPOINT_RECURSIVE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_FILEWATCH_ENDPOINT_RECURSIVE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_ENDPOINT_RECURSIVE_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_ENDPOINT_USE_FILE_HASHING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_FILEWATCH_ENDPOINT_USE_FILE_HASHING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_ENDPOINT_USE_FILE_HASHING_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_ENDPOINT_EXCEPTION_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_FILEWATCH_ENDPOINT_EXCEPTION_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_ENDPOINT_EXCEPTION_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_ENDPOINT_EXCHANGE_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_FILEWATCH_ENDPOINT_EXCHANGE_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_ENDPOINT_EXCHANGE_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_FILEWATCH_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_FILEWATCH_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_COMPONENT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_FILEWATCH_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_COMPONENT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_COMPONENT_CONCURRENT_CONSUMERS_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_FILEWATCH_COMPONENT_CONCURRENT_CONSUMERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_COMPONENT_CONCURRENT_CONSUMERS_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_COMPONENT_FILE_HASHER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_FILEWATCH_COMPONENT_FILE_HASHER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_COMPONENT_FILE_HASHER_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_COMPONENT_POLL_THREADS_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_FILEWATCH_COMPONENT_POLL_THREADS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_COMPONENT_POLL_THREADS_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_COMPONENT_QUEUE_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_FILEWATCH_COMPONENT_QUEUE_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_COMPONENT_QUEUE_SIZE_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_COMPONENT_USE_FILE_HASHING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_FILEWATCH_COMPONENT_USE_FILE_HASHING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_COMPONENT_USE_FILE_HASHING_DOC);
        conf.define(CAMEL_SOURCE_FILEWATCH_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_FILEWATCH_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_FILEWATCH_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        return conf;
    }
}