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
package org.apache.camel.kafkaconnector.utils;

import java.util.Map;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.camel.CamelContext;
import org.apache.camel.model.DataFormatDefinition;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.dataformat.ASN1DataFormat;
import org.apache.camel.model.dataformat.Any23DataFormat;
import org.apache.camel.model.dataformat.AvroDataFormat;
import org.apache.camel.model.dataformat.Base64DataFormat;
import org.apache.camel.model.dataformat.BeanioDataFormat;
import org.apache.camel.model.dataformat.BindyDataFormat;
import org.apache.camel.model.dataformat.CBORDataFormat;
import org.apache.camel.model.dataformat.CsvDataFormat;
import org.apache.camel.model.dataformat.FhirJsonDataFormat;
import org.apache.camel.model.dataformat.FhirXmlDataFormat;
import org.apache.camel.model.dataformat.GrokDataFormat;
import org.apache.camel.model.dataformat.GzipDataFormat;
import org.apache.camel.model.dataformat.HL7DataFormat;
import org.apache.camel.model.dataformat.IcalDataFormat;
import org.apache.camel.model.dataformat.JacksonXMLDataFormat;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.model.dataformat.JsonApiDataFormat;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.LZFDataFormat;
import org.apache.camel.model.dataformat.MimeMultipartDataFormat;
import org.apache.camel.model.dataformat.PGPDataFormat;
import org.apache.camel.model.dataformat.ProtobufDataFormat;
import org.apache.camel.model.dataformat.RssDataFormat;
import org.apache.camel.model.dataformat.SoapJaxbDataFormat;
import org.apache.camel.model.dataformat.SyslogDataFormat;
import org.apache.camel.model.dataformat.TarFileDataFormat;
import org.apache.camel.model.dataformat.ThriftDataFormat;
import org.apache.camel.model.dataformat.TidyMarkupDataFormat;
import org.apache.camel.model.dataformat.XMLSecurityDataFormat;
import org.apache.camel.model.dataformat.XStreamDataFormat;
import org.apache.camel.model.dataformat.YAMLDataFormat;
import org.apache.camel.model.dataformat.ZipDeflaterDataFormat;
import org.apache.camel.support.PropertyBindingSupport;




public class CamelKafkaConnectDataformat {
    private final String dataformatId;
    private final CamelKafkaConnectDataformatKind dataformatKind;

    public CamelKafkaConnectDataformat(String dataformatId, CamelKafkaConnectDataformatKind dataformatKind) {
        this.dataformatId = dataformatId;
        this.dataformatKind = dataformatKind;
    }

    public String getDataformatId() {
        return dataformatId;
    }

    public CamelKafkaConnectDataformatKind getDataformatKind() {
        return dataformatKind;
    }

    public enum CamelKafkaConnectDataformatKind {
        MARSHALL,
        UNMARSHALL;
    }

    public static void configureMarshalDataformat(RouteDefinition rd, CamelContext context, String dataFormatName, Properties camelProperties) {
        Predicate<String> keyFilter = key -> key.contains(".marshal." + dataFormatName + ".");
        DataFormatDefinition dfd = configureDataformat(context, dataFormatName, keyFilter, camelProperties);
        if (dfd != null) {
            rd.marshal(dfd);
        } else {
            rd.marshal(dataFormatName);
        }
    }


    public static void configureUnmarshalDataformat(RouteDefinition rd, CamelContext context, String dataFormatName, Properties camelProperties) {
        Predicate<String> keyFilter = key -> key.contains(".unmarshal." + dataFormatName + ".");
        DataFormatDefinition dfd = configureDataformat(context, dataFormatName, keyFilter, camelProperties);
        if (dfd != null) {
            rd.unmarshal(dfd);
        } else {
            rd.unmarshal(dataFormatName);
        }
    }

    public static DataFormatDefinition configureDataformat(CamelContext context, String dataFormatName, Predicate<String> keyFilter, Properties camelProperties) {
        Map<String, Object> dataFormatProps = camelProperties.stringPropertyNames()
                .stream()
                .filter(keyFilter)
                .collect(Collectors.toMap(key -> key.substring(key.lastIndexOf(".") + 1), key -> camelProperties.get(key)));

        DataFormatDefinition dataformat;

        switch (dataFormatName) {
            case "any23":
                dataformat = new Any23DataFormat();
                break;
            case "avro":
                dataformat = new AvroDataFormat();
                break;
            case "base64":
                dataformat = new Base64DataFormat();
                break;
            case "beanio":
                dataformat = new BeanioDataFormat();
                break;
            case "bindy":
                dataformat = new BindyDataFormat();
                break;
            case "cbor":
                dataformat = new CBORDataFormat();
                break;
            case "csv":
                dataformat = new CsvDataFormat();
                break;
            case "csvLazyLoad":
                dataformat = new CsvDataFormat(true);
                break;
            case "grok":
                dataformat = new GrokDataFormat();
                break;
            case "gzipDeflater":
                dataformat = new GzipDataFormat();
                break;
            case "hl7":
                dataformat = new HL7DataFormat();
                break;
            case "ical":
                dataformat = new IcalDataFormat();
                break;
            case "lzf":
                dataformat = new LZFDataFormat();
                break;
            case "mimeMultipart":
                dataformat = new MimeMultipartDataFormat();
                break;
            case "pgp":
                dataformat = new PGPDataFormat();
                break;
            case "jacksonxml":
                dataformat = new JacksonXMLDataFormat();
                break;
            case "jaxb":
                dataformat = new JaxbDataFormat();
                break;
            case "json":
                dataformat = new JsonDataFormat();
                break;
            case "jsonApi":
                dataformat = new JsonApiDataFormat();
                break;
            case "protobuf":
                dataformat = new ProtobufDataFormat();
                break;
            case "rss":
                dataformat = new RssDataFormat();
                break;
            case "soapjaxb":
                dataformat = new SoapJaxbDataFormat();
                break;
            case "soapjaxb12":
                SoapJaxbDataFormat soap = new SoapJaxbDataFormat();
                soap.setVersion("1.2");
                dataformat = soap;
                break;
            case "syslog":
                dataformat = new SyslogDataFormat();
                break;
            case "thrift":
                dataformat = new ThriftDataFormat();
                break;
            case "tidyMarkup":
                dataformat = new TidyMarkupDataFormat();
                break;
            case "xstream":
                dataformat = new XStreamDataFormat();
                break;
            case "yaml":
                dataformat = new YAMLDataFormat();
                break;
            case "secureXML":
                dataformat = new XMLSecurityDataFormat();
                break;
            case "tarFile":
                dataformat = new TarFileDataFormat();
                break;
            case "zipDeflater":
                dataformat = new ZipDeflaterDataFormat();
                break;
            case "asn1":
                dataformat = new ASN1DataFormat();
                break;
            case "fhirJson":
                dataformat = new FhirJsonDataFormat();
                break;
            case "fhirXml":
                dataformat = new FhirXmlDataFormat();
                break;
            default:
                return null;
        }
        PropertyBindingSupport.bindProperties(context, dataformat, dataFormatProps);
        return dataformat;
    }
}
