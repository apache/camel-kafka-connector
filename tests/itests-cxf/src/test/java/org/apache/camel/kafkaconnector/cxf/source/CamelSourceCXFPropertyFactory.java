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

package org.apache.camel.kafkaconnector.cxf.source;


import org.apache.camel.kafkaconnector.common.EndpointUrlBuilder;
import org.apache.camel.kafkaconnector.common.SourceConnectorPropertyFactory;


/**
 * Creates the set of properties used by a Camel CXF Source Connector
 */
final class CamelSourceCXFPropertyFactory extends SourceConnectorPropertyFactory<CamelSourceCXFPropertyFactory> {
    private CamelSourceCXFPropertyFactory() {

    }

    public CamelSourceCXFPropertyFactory withAddress(String address) {
        return setProperty("camel.source.path.address", address);
    }
    
    public CamelSourceCXFPropertyFactory withServiceClass(String serviceClass) {
        return setProperty("camel.source.endpoint.serviceClass", serviceClass);
    }
    
    public CamelSourceCXFPropertyFactory withPublishedEndpointUrl(String publishedEndpointUrl) {
        return setProperty("camel.source.endpoint.publishedEndpointUrl", publishedEndpointUrl);
    }
    
    public CamelSourceCXFPropertyFactory withDataFormat(String dataFormat) {
        return setProperty("camel.source.endpoint.dataFormat", dataFormat);
    }
        
    public static CamelSourceCXFPropertyFactory basic() {
        return new CamelSourceCXFPropertyFactory()
                .withName("CamelCXFSourceConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.cxf.CamelCxfSourceConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }

    public EndpointUrlBuilder<CamelSourceCXFPropertyFactory> withUrl(String cxfUrl) {
        String url = String.format("cxf://%s", cxfUrl);
        return new EndpointUrlBuilder<>(this::withSourceUrl, url);
    }

    
}
