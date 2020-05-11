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

package org.apache.camel.kafkaconnector.clients.salesforce;

import java.util.ArrayList;
import java.util.List;

public final class SfdxCommand {
    private List<String> commands = new ArrayList<>();

    private SfdxCommand() {
        commands.add("sfdx");
    }

    public SfdxCommand withArgument(String argument) {
        commands.add(argument);

        return this;
    }

    public SfdxCommand withArgument(String argument, String value) {
        commands.add(argument);
        commands.add(value);

        return this;
    }

    public static SfdxCommand forceDataRecordCreate() {
        SfdxCommand command = new SfdxCommand();

        return command.withArgument("force:data:record:create");
    }

    public static SfdxCommand forceDataRecordDelete() {
        SfdxCommand command = new SfdxCommand();

        return command.withArgument("force:data:record:delete");
    }

    public static SfdxCommand forceDataRecordUpdate() {
        SfdxCommand command = new SfdxCommand();

        return command.withArgument("force:data:record:update");
    }

    public String[] commands() {
        return commands.toArray(new String[0]);
    }
}
