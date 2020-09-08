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

package org.apache.camel.kafkaconnector.jdbc.client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseClient {
    private static final Logger LOG = LoggerFactory.getLogger(DatabaseClient.class);
    private PGSimpleDataSource datasource;
    private final Connection connection;

    public DatabaseClient(String url) throws SQLException {
        LOG.info("Opening a new database connection using the URL {}", url);

        datasource = new PGSimpleDataSource();
        datasource.setURL(url);
        datasource.setUser("ckc");
        datasource.setPassword("ckcDevel123");
        connection = datasource.getConnection();
    }

    public void runQuery(String query, Consumer<ResultSet> consumer) throws SQLException {
        try (ResultSet rs = connection.prepareStatement(query).executeQuery()) {

            while (rs.next()) {
                consumer.accept(rs);
            }
        }
    }

    public int count(String table) throws SQLException {
        String query = String.format("select count(*) as count from %s", table);

        try (ResultSet rs = connection.prepareStatement(query).executeQuery()) {
            while (rs.next()) {
                return rs.getInt("count");
            }
        }

        return 0;
    }

    public boolean hasAtLeastRecords(String table, int expected) throws SQLException {
        int count = count(table);

        return count >= expected;
    }
}
