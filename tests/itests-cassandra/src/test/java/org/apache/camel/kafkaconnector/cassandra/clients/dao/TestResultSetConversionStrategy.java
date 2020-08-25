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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import org.apache.camel.component.cassandra.ResultSetConversionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestResultSetConversionStrategy implements ResultSetConversionStrategy {
    private static final Logger LOG = LoggerFactory.getLogger(TestResultSetConversionStrategy.class);

    @Override
    public Object getBody(ResultSet resultSet) {
        List<String> ret = new ArrayList<>();

        Iterator<Row> iterator = resultSet.iterator();
        while (iterator.hasNext()) {
            Row row = iterator.next();

            String data = row.getString("text");
            ret.add(data);
            LOG.info("Retrieved data: {}", data);
        }

        return ret;
    }
}
