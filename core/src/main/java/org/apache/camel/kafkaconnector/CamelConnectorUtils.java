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
package org.apache.camel.kafkaconnector;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public final class CamelConnectorUtils {
    public static final TimeZone UTC = TimeZone.getTimeZone("UTC");

    private CamelConnectorUtils() {
    }

    public static Date truncateDate(Date value) {
        Calendar calendar = Calendar.getInstance(CamelConnectorUtils.UTC);
        calendar.setTime(value);
        calendar.set(Calendar.YEAR, 0);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 0);

        return calendar.getTime();
    }

    public static Date truncateTime(Date value) {
        Calendar calendar = Calendar.getInstance(CamelConnectorUtils.UTC);
        calendar.setTime(value);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
}
