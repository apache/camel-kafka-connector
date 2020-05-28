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

package org.apache.camel.kafkaconnector.cassandra.clients;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.apache.camel.kafkaconnector.cassandra.clients.dao.TestDataDao;

/**
 * A simple client for Cassandra for testing purposes
 */
public class CassandraClient {
    private Cluster cluster;
    private Session session;

    public CassandraClient(String host, int port) {
        cluster = Cluster.builder()
                .addContactPoint(host)
                .withPort(port)
                .build();

        session = cluster.connect();
    }


    public TestDataDao newTestDataDao() {
        return new TestDataDao(this.session);
    }

}
