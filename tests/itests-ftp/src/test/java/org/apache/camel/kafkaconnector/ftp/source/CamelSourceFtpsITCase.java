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

package org.apache.camel.kafkaconnector.ftp.source;

import java.nio.file.Path;

import org.apache.camel.kafkaconnector.ftp.common.FtpTestServers;
import org.apache.camel.test.infra.ftp.services.FtpService;
import org.junit.jupiter.api.extension.RegisterExtension;

public class CamelSourceFtpsITCase extends CamelSourceFtpTestSupport {
    @RegisterExtension
    public static FtpService service = FtpTestServers.createFtpsService();

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-ftps-source-kafka-connector"};
    }

    @Override
    protected Path localDirectory() {
        // the FTPS user home is the root directory of the server
        return service.getFtpRootDir().resolve(DIRECTORY);
    }

    @Override
    protected CamelFtpPropertyFactory connectorPropertyFactory() {
        return CamelFtpPropertyFactory.ftps()
                .withConnectionHost(service.host())
                .withConnectionPort(service.port())
                .withUsername(service.username())
                .withPassword(service.password())
                .withDirectoryName(DIRECTORY)
                .merge(FtpTestServers.ftpsTrustProperties());
    }
}
