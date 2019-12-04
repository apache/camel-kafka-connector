/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.camel.kakfaconnector;

import java.util.Properties;


/**
 * An interface for producing different types of Kafka connect properties. The CLI runtime
 * equivalent for this file is the connect-standalone.properties
 */
public interface KafkaConnectPropertyProducer {

    /**
     * Gets the properties used to configure the Kafka connect runtime
     * @return a Properties object containing the set of properties for the Kafka connect
     * runtime
     */
    Properties getProperties();
}
