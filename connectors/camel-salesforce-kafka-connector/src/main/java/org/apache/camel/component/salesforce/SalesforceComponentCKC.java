package org.apache.camel.component.salesforce;

import org.apache.camel.spi.PropertyConfigurer;

//XXX: temporary workaround waiting for https://issues.apache.org/jira/browse/CAMEL-15063 in camel 3.4.0
public class SalesforceComponentCKC extends SalesforceComponent {

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        getConfig().setHttpClient(getHttpClient());
    }

    @Override
    public PropertyConfigurer getComponentPropertyConfigurer() {
        return new SalesforceComponentConfigurer();
    }

    @Override
    public PropertyConfigurer getEndpointPropertyConfigurer() {
        return new SalesforceEndpointConfigurer();
    }
}
