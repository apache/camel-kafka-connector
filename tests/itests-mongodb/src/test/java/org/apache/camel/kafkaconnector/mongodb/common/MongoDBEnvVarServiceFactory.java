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
package org.apache.camel.kafkaconnector.mongodb.common;

import java.util.function.Supplier;

import org.apache.camel.test.infra.common.services.SimpleTestServiceBuilder;
import org.apache.camel.test.infra.mongodb.services.MongoDBRemoteService;
import org.apache.camel.test.infra.mongodb.services.MongoDBService;

public final class MongoDBEnvVarServiceFactory {
    private MongoDBEnvVarServiceFactory() {
    }

    public static SimpleTestServiceBuilder<MongoDBService> builder() {
        return new SimpleTestServiceBuilder("mongodb");
    }

    public static MongoDBService createService() {
        return createService(MongoDBLocalContainerEnvVarService::new);
    }

    public static MongoDBService createService(Supplier<MongoDBService> localMapping) {
        return (MongoDBService)builder().addLocalMapping(localMapping).addRemoteMapping(MongoDBRemoteService::new).build();
    }

    public static MongoDBService createService(String username, String password) {
        MongoDBLocalContainerEnvVarService mongoDBLocalContainerEnvVarService = new MongoDBLocalContainerEnvVarService();
        mongoDBLocalContainerEnvVarService.addEnvProperty("MONGO_INITDB_ROOT_USERNAME", username);
        mongoDBLocalContainerEnvVarService.addEnvProperty("MONGO_INITDB_ROOT_PASSWORD", password);
        return createService(() -> mongoDBLocalContainerEnvVarService);
    }
}
