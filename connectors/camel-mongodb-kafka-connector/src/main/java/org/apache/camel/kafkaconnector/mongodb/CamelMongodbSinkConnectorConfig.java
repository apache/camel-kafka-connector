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
package org.apache.camel.kafkaconnector.mongodb;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelMongodbSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_MONGODB_PATH_CONNECTION_BEAN_CONF = "camel.sink.path.connectionBean";
    public static final String CAMEL_SINK_MONGODB_PATH_CONNECTION_BEAN_DOC = "Sets the connection bean reference used to lookup a client for connecting to a database.";
    public static final String CAMEL_SINK_MONGODB_PATH_CONNECTION_BEAN_DEFAULT = null;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_COLLECTION_CONF = "camel.sink.endpoint.collection";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_COLLECTION_DOC = "Sets the name of the MongoDB collection to bind to this endpoint";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_COLLECTION_DEFAULT = null;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_COLLECTION_INDEX_CONF = "camel.sink.endpoint.collectionIndex";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_COLLECTION_INDEX_DOC = "Sets the collection index (JSON FORMAT : { field1 : order1, field2 : order2})";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_COLLECTION_INDEX_DEFAULT = null;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_CREATE_COLLECTION_CONF = "camel.sink.endpoint.createCollection";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_CREATE_COLLECTION_DOC = "Create collection during initialisation if it doesn't exist. Default is true.";
    public static final Boolean CAMEL_SINK_MONGODB_ENDPOINT_CREATE_COLLECTION_DEFAULT = true;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_DATABASE_CONF = "camel.sink.endpoint.database";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_DATABASE_DOC = "Sets the name of the MongoDB database to target";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_DATABASE_DEFAULT = null;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_MONGO_CONNECTION_CONF = "camel.sink.endpoint.mongoConnection";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_MONGO_CONNECTION_DOC = "Sets the connection bean used as a client for connecting to a database.";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_MONGO_CONNECTION_DEFAULT = null;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_OPERATION_CONF = "camel.sink.endpoint.operation";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_OPERATION_DOC = "Sets the operation this endpoint will execute against MongoDB. One of: [findById] [findOneByQuery] [findAll] [findDistinct] [insert] [save] [update] [remove] [bulkWrite] [aggregate] [getDbStats] [getColStats] [count] [command]";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_OPERATION_DEFAULT = null;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_OUTPUT_TYPE_CONF = "camel.sink.endpoint.outputType";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_OUTPUT_TYPE_DOC = "Convert the output of the producer to the selected type : DocumentList Document or MongoIterable. DocumentList or MongoIterable applies to findAll and aggregate. Document applies to all other operations. One of: [DocumentList] [Document] [MongoIterable]";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_OUTPUT_TYPE_DEFAULT = null;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_MONGODB_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SINK_MONGODB_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_CURSOR_REGENERATION_DELAY_CONF = "camel.sink.endpoint.cursorRegenerationDelay";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_CURSOR_REGENERATION_DELAY_DOC = "MongoDB tailable cursors will block until new data arrives. If no new data is inserted, after some time the cursor will be automatically freed and closed by the MongoDB server. The client is expected to regenerate the cursor if needed. This value specifies the time to wait before attempting to fetch a new cursor, and if the attempt fails, how long before the next attempt is made. Default value is 1000ms.";
    public static final Long CAMEL_SINK_MONGODB_ENDPOINT_CURSOR_REGENERATION_DELAY_DEFAULT = 1000L;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_DYNAMICITY_CONF = "camel.sink.endpoint.dynamicity";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_DYNAMICITY_DOC = "Sets whether this endpoint will attempt to dynamically resolve the target database and collection from the incoming Exchange properties. Can be used to override at runtime the database and collection specified on the otherwise static endpoint URI. It is disabled by default to boost performance. Enabling it will take a minimal performance hit.";
    public static final Boolean CAMEL_SINK_MONGODB_ENDPOINT_DYNAMICITY_DEFAULT = false;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_READ_PREFERENCE_CONF = "camel.sink.endpoint.readPreference";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_READ_PREFERENCE_DOC = "Configure how MongoDB clients route read operations to the members of a replica set. Possible values are PRIMARY, PRIMARY_PREFERRED, SECONDARY, SECONDARY_PREFERRED or NEAREST One of: [PRIMARY] [PRIMARY_PREFERRED] [SECONDARY] [SECONDARY_PREFERRED] [NEAREST]";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_READ_PREFERENCE_DEFAULT = "PRIMARY";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SINK_MONGODB_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_WRITE_CONCERN_CONF = "camel.sink.endpoint.writeConcern";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_WRITE_CONCERN_DOC = "Configure the connection bean with the level of acknowledgment requested from MongoDB for write operations to a standalone mongod, replicaset or cluster. Possible values are ACKNOWLEDGED, W1, W2, W3, UNACKNOWLEDGED, JOURNALED or MAJORITY. One of: [ACKNOWLEDGED] [W1] [W2] [W3] [UNACKNOWLEDGED] [JOURNALED] [MAJORITY]";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_WRITE_CONCERN_DEFAULT = "ACKNOWLEDGED";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_WRITE_RESULT_AS_HEADER_CONF = "camel.sink.endpoint.writeResultAsHeader";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_WRITE_RESULT_AS_HEADER_DOC = "In write operations, it determines whether instead of returning WriteResult as the body of the OUT message, we transfer the IN message to the OUT and attach the WriteResult as a header.";
    public static final Boolean CAMEL_SINK_MONGODB_ENDPOINT_WRITE_RESULT_AS_HEADER_DEFAULT = false;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_STREAM_FILTER_CONF = "camel.sink.endpoint.streamFilter";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_STREAM_FILTER_DOC = "Filter condition for change streams consumer.";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_STREAM_FILTER_DEFAULT = null;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_PERSISTENT_ID_CONF = "camel.sink.endpoint.persistentId";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_PERSISTENT_ID_DOC = "One tail tracking collection can host many trackers for several tailable consumers. To keep them separate, each tracker should have its own unique persistentId.";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_PERSISTENT_ID_DEFAULT = null;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_PERSISTENT_TAIL_TRACKING_CONF = "camel.sink.endpoint.persistentTailTracking";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_PERSISTENT_TAIL_TRACKING_DOC = "Enable persistent tail tracking, which is a mechanism to keep track of the last consumed message across system restarts. The next time the system is up, the endpoint will recover the cursor from the point where it last stopped slurping records.";
    public static final Boolean CAMEL_SINK_MONGODB_ENDPOINT_PERSISTENT_TAIL_TRACKING_DEFAULT = false;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_COLLECTION_CONF = "camel.sink.endpoint.tailTrackCollection";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_COLLECTION_DOC = "Collection where tail tracking information will be persisted. If not specified, MongoDbTailTrackingConfig#DEFAULT_COLLECTION will be used by default.";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_COLLECTION_DEFAULT = null;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_DB_CONF = "camel.sink.endpoint.tailTrackDb";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_DB_DOC = "Indicates what database the tail tracking mechanism will persist to. If not specified, the current database will be picked by default. Dynamicity will not be taken into account even if enabled, i.e. the tail tracking database will not vary past endpoint initialisation.";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_DB_DEFAULT = null;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_FIELD_CONF = "camel.sink.endpoint.tailTrackField";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_FIELD_DOC = "Field where the last tracked value will be placed. If not specified, MongoDbTailTrackingConfig#DEFAULT_FIELD will be used by default.";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_FIELD_DEFAULT = null;
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_INCREASING_FIELD_CONF = "camel.sink.endpoint.tailTrackIncreasingField";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_INCREASING_FIELD_DOC = "Correlation field in the incoming record which is of increasing nature and will be used to position the tailing cursor every time it is generated. The cursor will be (re)created with a query of type: tailTrackIncreasingField greater than lastValue (possibly recovered from persistent tail tracking). Can be of type Integer, Date, String, etc. NOTE: No support for dot notation at the current time, so the field should be at the top level of the document.";
    public static final String CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_INCREASING_FIELD_DEFAULT = null;
    public static final String CAMEL_SINK_MONGODB_COMPONENT_MONGO_CONNECTION_CONF = "camel.component.mongodb.mongoConnection";
    public static final String CAMEL_SINK_MONGODB_COMPONENT_MONGO_CONNECTION_DOC = "Shared client used for connection. All endpoints generated from the component will share this connection client.";
    public static final String CAMEL_SINK_MONGODB_COMPONENT_MONGO_CONNECTION_DEFAULT = null;
    public static final String CAMEL_SINK_MONGODB_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.mongodb.lazyStartProducer";
    public static final String CAMEL_SINK_MONGODB_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_MONGODB_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_MONGODB_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.mongodb.basicPropertyBinding";
    public static final String CAMEL_SINK_MONGODB_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SINK_MONGODB_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;

    public CamelMongodbSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelMongodbSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_MONGODB_PATH_CONNECTION_BEAN_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MONGODB_PATH_CONNECTION_BEAN_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_MONGODB_PATH_CONNECTION_BEAN_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_COLLECTION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MONGODB_ENDPOINT_COLLECTION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_COLLECTION_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_COLLECTION_INDEX_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MONGODB_ENDPOINT_COLLECTION_INDEX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_COLLECTION_INDEX_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_CREATE_COLLECTION_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MONGODB_ENDPOINT_CREATE_COLLECTION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_CREATE_COLLECTION_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_DATABASE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MONGODB_ENDPOINT_DATABASE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_DATABASE_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_MONGO_CONNECTION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MONGODB_ENDPOINT_MONGO_CONNECTION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_MONGO_CONNECTION_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_OPERATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MONGODB_ENDPOINT_OPERATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_OPERATION_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_OUTPUT_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MONGODB_ENDPOINT_OUTPUT_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_OUTPUT_TYPE_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MONGODB_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MONGODB_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_CURSOR_REGENERATION_DELAY_CONF, ConfigDef.Type.LONG, CAMEL_SINK_MONGODB_ENDPOINT_CURSOR_REGENERATION_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_CURSOR_REGENERATION_DELAY_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_DYNAMICITY_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MONGODB_ENDPOINT_DYNAMICITY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_DYNAMICITY_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_READ_PREFERENCE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MONGODB_ENDPOINT_READ_PREFERENCE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_READ_PREFERENCE_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MONGODB_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_WRITE_CONCERN_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MONGODB_ENDPOINT_WRITE_CONCERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_WRITE_CONCERN_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_WRITE_RESULT_AS_HEADER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MONGODB_ENDPOINT_WRITE_RESULT_AS_HEADER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_WRITE_RESULT_AS_HEADER_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_STREAM_FILTER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MONGODB_ENDPOINT_STREAM_FILTER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_STREAM_FILTER_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_PERSISTENT_ID_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MONGODB_ENDPOINT_PERSISTENT_ID_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_PERSISTENT_ID_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_PERSISTENT_TAIL_TRACKING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MONGODB_ENDPOINT_PERSISTENT_TAIL_TRACKING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_PERSISTENT_TAIL_TRACKING_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_COLLECTION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_COLLECTION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_COLLECTION_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_DB_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_DB_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_DB_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_FIELD_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_FIELD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_FIELD_DOC);
        conf.define(CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_INCREASING_FIELD_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_INCREASING_FIELD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_ENDPOINT_TAIL_TRACK_INCREASING_FIELD_DOC);
        conf.define(CAMEL_SINK_MONGODB_COMPONENT_MONGO_CONNECTION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_MONGODB_COMPONENT_MONGO_CONNECTION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_COMPONENT_MONGO_CONNECTION_DOC);
        conf.define(CAMEL_SINK_MONGODB_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MONGODB_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_MONGODB_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_MONGODB_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_MONGODB_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.LOW, CAMEL_SINK_MONGODB_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        return conf;
    }
}