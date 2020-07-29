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
package org.apache.camel.kafkaconnector.lpr;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelLprSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_LPR_PATH_HOSTNAME_CONF = "camel.sink.path.hostname";
    private static final String CAMEL_SINK_LPR_PATH_HOSTNAME_DOC = "Hostname of the printer";
    private static final String CAMEL_SINK_LPR_PATH_HOSTNAME_DEFAULT = null;
    public static final String CAMEL_SINK_LPR_PATH_PORT_CONF = "camel.sink.path.port";
    private static final String CAMEL_SINK_LPR_PATH_PORT_DOC = "Port number of the printer";
    private static final Integer CAMEL_SINK_LPR_PATH_PORT_DEFAULT = null;
    public static final String CAMEL_SINK_LPR_PATH_PRINTERNAME_CONF = "camel.sink.path.printername";
    private static final String CAMEL_SINK_LPR_PATH_PRINTERNAME_DOC = "Name of the printer";
    private static final String CAMEL_SINK_LPR_PATH_PRINTERNAME_DEFAULT = null;
    public static final String CAMEL_SINK_LPR_ENDPOINT_COPIES_CONF = "camel.sink.endpoint.copies";
    private static final String CAMEL_SINK_LPR_ENDPOINT_COPIES_DOC = "Number of copies to print";
    private static final Integer CAMEL_SINK_LPR_ENDPOINT_COPIES_DEFAULT = 1;
    public static final String CAMEL_SINK_LPR_ENDPOINT_DOC_FLAVOR_CONF = "camel.sink.endpoint.docFlavor";
    private static final String CAMEL_SINK_LPR_ENDPOINT_DOC_FLAVOR_DOC = "Sets DocFlavor to use.";
    private static final String CAMEL_SINK_LPR_ENDPOINT_DOC_FLAVOR_DEFAULT = null;
    public static final String CAMEL_SINK_LPR_ENDPOINT_FLAVOR_CONF = "camel.sink.endpoint.flavor";
    private static final String CAMEL_SINK_LPR_ENDPOINT_FLAVOR_DOC = "Sets DocFlavor to use.";
    private static final String CAMEL_SINK_LPR_ENDPOINT_FLAVOR_DEFAULT = null;
    public static final String CAMEL_SINK_LPR_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    private static final String CAMEL_SINK_LPR_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_LPR_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_LPR_ENDPOINT_MEDIA_SIZE_CONF = "camel.sink.endpoint.mediaSize";
    private static final String CAMEL_SINK_LPR_ENDPOINT_MEDIA_SIZE_DOC = "Sets the stationary as defined by enumeration names in the javax.print.attribute.standard.MediaSizeName API. The default setting is to use North American Letter sized stationary. The value's case is ignored, e.g. values of iso_a4 and ISO_A4 may be used.";
    private static final String CAMEL_SINK_LPR_ENDPOINT_MEDIA_SIZE_DEFAULT = "na-letter";
    public static final String CAMEL_SINK_LPR_ENDPOINT_MEDIA_TRAY_CONF = "camel.sink.endpoint.mediaTray";
    private static final String CAMEL_SINK_LPR_ENDPOINT_MEDIA_TRAY_DOC = "Sets MediaTray supported by the javax.print.DocFlavor API, for example upper,middle etc.";
    private static final String CAMEL_SINK_LPR_ENDPOINT_MEDIA_TRAY_DEFAULT = null;
    public static final String CAMEL_SINK_LPR_ENDPOINT_MIME_TYPE_CONF = "camel.sink.endpoint.mimeType";
    private static final String CAMEL_SINK_LPR_ENDPOINT_MIME_TYPE_DOC = "Sets mimeTypes supported by the javax.print.DocFlavor API";
    private static final String CAMEL_SINK_LPR_ENDPOINT_MIME_TYPE_DEFAULT = null;
    public static final String CAMEL_SINK_LPR_ENDPOINT_ORIENTATION_CONF = "camel.sink.endpoint.orientation";
    private static final String CAMEL_SINK_LPR_ENDPOINT_ORIENTATION_DOC = "Sets the page orientation. One of: [portrait] [landscape] [reverse-portrait] [reverse-landscape]";
    private static final String CAMEL_SINK_LPR_ENDPOINT_ORIENTATION_DEFAULT = "portrait";
    public static final String CAMEL_SINK_LPR_ENDPOINT_PRINTER_PREFIX_CONF = "camel.sink.endpoint.printerPrefix";
    private static final String CAMEL_SINK_LPR_ENDPOINT_PRINTER_PREFIX_DOC = "Sets the prefix name of the printer, it is useful when the printer name does not start with //hostname/printer";
    private static final String CAMEL_SINK_LPR_ENDPOINT_PRINTER_PREFIX_DEFAULT = null;
    public static final String CAMEL_SINK_LPR_ENDPOINT_SEND_TO_PRINTER_CONF = "camel.sink.endpoint.sendToPrinter";
    private static final String CAMEL_SINK_LPR_ENDPOINT_SEND_TO_PRINTER_DOC = "etting this option to false prevents sending of the print data to the printer";
    private static final Boolean CAMEL_SINK_LPR_ENDPOINT_SEND_TO_PRINTER_DEFAULT = true;
    public static final String CAMEL_SINK_LPR_ENDPOINT_SIDES_CONF = "camel.sink.endpoint.sides";
    private static final String CAMEL_SINK_LPR_ENDPOINT_SIDES_DOC = "Sets one sided or two sided printing based on the javax.print.attribute.standard.Sides API One of: [one-sided] [duplex] [tumble] [two-sided-short-edge] [two-sided-long-edge]";
    private static final String CAMEL_SINK_LPR_ENDPOINT_SIDES_DEFAULT = "one-sided";
    public static final String CAMEL_SINK_LPR_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    private static final String CAMEL_SINK_LPR_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_LPR_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_LPR_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    private static final String CAMEL_SINK_LPR_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SINK_LPR_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_LPR_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.lpr.lazyStartProducer";
    private static final String CAMEL_SINK_LPR_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_LPR_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_LPR_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.lpr.basicPropertyBinding";
    private static final String CAMEL_SINK_LPR_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_LPR_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;

    public CamelLprSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelLprSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_LPR_PATH_HOSTNAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_LPR_PATH_HOSTNAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_LPR_PATH_HOSTNAME_DOC);
        conf.define(CAMEL_SINK_LPR_PATH_PORT_CONF, ConfigDef.Type.INT, CAMEL_SINK_LPR_PATH_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_LPR_PATH_PORT_DOC);
        conf.define(CAMEL_SINK_LPR_PATH_PRINTERNAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_LPR_PATH_PRINTERNAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_LPR_PATH_PRINTERNAME_DOC);
        conf.define(CAMEL_SINK_LPR_ENDPOINT_COPIES_CONF, ConfigDef.Type.INT, CAMEL_SINK_LPR_ENDPOINT_COPIES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_LPR_ENDPOINT_COPIES_DOC);
        conf.define(CAMEL_SINK_LPR_ENDPOINT_DOC_FLAVOR_CONF, ConfigDef.Type.STRING, CAMEL_SINK_LPR_ENDPOINT_DOC_FLAVOR_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_LPR_ENDPOINT_DOC_FLAVOR_DOC);
        conf.define(CAMEL_SINK_LPR_ENDPOINT_FLAVOR_CONF, ConfigDef.Type.STRING, CAMEL_SINK_LPR_ENDPOINT_FLAVOR_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_LPR_ENDPOINT_FLAVOR_DOC);
        conf.define(CAMEL_SINK_LPR_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_LPR_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_LPR_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_LPR_ENDPOINT_MEDIA_SIZE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_LPR_ENDPOINT_MEDIA_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_LPR_ENDPOINT_MEDIA_SIZE_DOC);
        conf.define(CAMEL_SINK_LPR_ENDPOINT_MEDIA_TRAY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_LPR_ENDPOINT_MEDIA_TRAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_LPR_ENDPOINT_MEDIA_TRAY_DOC);
        conf.define(CAMEL_SINK_LPR_ENDPOINT_MIME_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_LPR_ENDPOINT_MIME_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_LPR_ENDPOINT_MIME_TYPE_DOC);
        conf.define(CAMEL_SINK_LPR_ENDPOINT_ORIENTATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_LPR_ENDPOINT_ORIENTATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_LPR_ENDPOINT_ORIENTATION_DOC);
        conf.define(CAMEL_SINK_LPR_ENDPOINT_PRINTER_PREFIX_CONF, ConfigDef.Type.STRING, CAMEL_SINK_LPR_ENDPOINT_PRINTER_PREFIX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_LPR_ENDPOINT_PRINTER_PREFIX_DOC);
        conf.define(CAMEL_SINK_LPR_ENDPOINT_SEND_TO_PRINTER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_LPR_ENDPOINT_SEND_TO_PRINTER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_LPR_ENDPOINT_SEND_TO_PRINTER_DOC);
        conf.define(CAMEL_SINK_LPR_ENDPOINT_SIDES_CONF, ConfigDef.Type.STRING, CAMEL_SINK_LPR_ENDPOINT_SIDES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_LPR_ENDPOINT_SIDES_DOC);
        conf.define(CAMEL_SINK_LPR_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_LPR_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_LPR_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_LPR_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_LPR_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_LPR_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_LPR_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_LPR_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_LPR_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_LPR_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_LPR_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_LPR_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        return conf;
    }
}