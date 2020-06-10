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

package org.apache.camel.kafkaconnector.cassandra.clients.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.schemabuilder.SchemaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDataDao {
    public static final String KEY_SPACE = "ckc_ks";
    public static final String TABLE_NAME = "test_data";

    private static final Logger LOG = LoggerFactory.getLogger(TestDataDao.class);


    private final Session session;

    public TestDataDao(Session session) {
        this.session = session;
    }

    public void createKeySpace() {
        Map<String, Object> replication = new HashMap<>();

        replication.put("class", "SimpleStrategy");
        replication.put("replication_factor", 3);

        String statement = SchemaBuilder.createKeyspace(KEY_SPACE)
                .ifNotExists()
                .with()
                .replication(replication).getQueryString();

        LOG.info("Executing {}", statement);

        session.execute(statement);
    }

    public void useKeySpace() {
        // Use String.format because "Bind variables cannot be used for keyspace names"
        String statement = String.format("USE %s", KEY_SPACE);

        session.execute(statement);
    }

    public void createTable() {
        String statement = SchemaBuilder.createTable(TABLE_NAME)
                .addPartitionKey("id", DataType.timeuuid())
                .addClusteringColumn("text", DataType.text())
                .getQueryString();

        LOG.info("Executing create table {}", statement);

        session.execute(statement);
    }

    public void dropTable() {
        String statement = SchemaBuilder.dropTable(TABLE_NAME)
                .getQueryString();

        LOG.info("Executing drop table {}", statement);

        session.execute(statement);
    }

    public boolean hasEnoughData(long expected) {
        ResultSet rs = session.execute("select count(*) from test_data");

        if (rs == null) {
            return false;
        }

        List<Row> all = rs.all();
        if (all == null || all.size() == 0) {
            return false;
        }

        long count = all.get(0).getLong("count");

        return count == expected;
    }

    public String getInsertStatement() {
        return "insert into test_data(id, text) values (now(), ?)";
    }


    public void getData(Consumer<String> consumer) {
        ResultSet rs = session.execute("select * from test_data");

        if (rs != null) {
            Iterator<Row> iterator = rs.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String data = row.getString("text");
                LOG.info("Retrieved data: {}", data);
                consumer.accept(data);
            }
        } else {
            LOG.warn("No records were returned");
        }
    }
}
