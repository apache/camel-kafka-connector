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

package org.apache.camel.kafkaconnector.cxf.sink;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class GreeterImpl extends org.apache.hello_world_soap_http.GreeterImpl {
    private static final Logger LOG = Logger.getLogger(GreeterImpl.class.getName());

    public String greetMe(String hi) {
        File outputFile = outputFile();

        try {
            outputFile.createNewFile();
            LOG.info("jaxws greetMe " + hi);

        } catch (IOException e) {
            LOG.warning("Failed to create result test file");
        }

        return "Greet " + hi;
    }

    public static File outputFile() {
        String path = GreeterImpl.class.getResource(".").getFile();

        return new File(path, "cxf.test.result");
    }
}
