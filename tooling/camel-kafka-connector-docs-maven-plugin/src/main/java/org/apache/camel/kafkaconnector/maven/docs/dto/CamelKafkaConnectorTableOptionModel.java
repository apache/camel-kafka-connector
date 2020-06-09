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
package org.apache.camel.kafkaconnector.maven.docs.dto;

public class CamelKafkaConnectorTableOptionModel {

    private String name;
    private boolean sink;
    private boolean source;
    private String docsSink = "";
    private String docsSource = "";
    private String downloadLinkZip = "";
    private String downloadLinkTar = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSink() {
        return sink;
    }

    public void setSink(boolean sink) {
        this.sink = sink;
    }

    public boolean isSource() {
        return source;
    }

    public void setSource(boolean source) {
        this.source = source;
    }

    public String getDocsSink() {
        return docsSink;
    }

    public void setDocsSink(String docsSink) {
        this.docsSink = docsSink;
    }

    public String getDocsSource() {
        return docsSource;
    }

    public void setDocsSource(String docsSource) {
        this.docsSource = docsSource;
    }

	public String getDownloadLinkZip() {
		return downloadLinkZip;
	}

	public void setDownloadLinkZip(String downloadLinkZip) {
		this.downloadLinkZip = downloadLinkZip;
	}

	public String getDownloadLinkTar() {
		return downloadLinkTar;
	}

	public void setDownloadLinkTar(String downloadLinkTar) {
		this.downloadLinkTar = downloadLinkTar;
	}

	@Override
    public String toString() {
        return "CamelKafkaConnectorTableOptionModel [name=" + name + ", sink=" + sink + ", source=" + source + ", docsSink=" + docsSink + ", docsSource=" + docsSource + "]";
    }

}
