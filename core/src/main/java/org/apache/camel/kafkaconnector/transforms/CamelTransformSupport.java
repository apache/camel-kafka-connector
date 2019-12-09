package org.apache.camel.kafkaconnector.transforms;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.transforms.Transformation;

public abstract class CamelTransformSupport<R extends ConnectRecord<R>> implements Transformation<R> {

    private final CamelContext camelContext = new DefaultCamelContext();

    protected CamelContext getCamelContext() {
        return camelContext;
    }
}
