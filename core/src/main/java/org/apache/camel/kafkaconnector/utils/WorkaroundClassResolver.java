package org.apache.camel.kafkaconnector.utils;

import org.apache.camel.impl.engine.DefaultClassResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//XXX: can be removed once upgrading to camel 3.0.0-M4 or newer
public class WorkaroundClassResolver extends DefaultClassResolver {
    private static Logger log = LoggerFactory.getLogger(WorkaroundClassResolver.class);
    @Override
    public Class<?> resolveMandatoryClass(String name) throws ClassNotFoundException {
        log.debug("WorkaroundClassResolver loading: {}", name);
        if (name != null && name.startsWith(":")) {
            log.debug("WorkaroundClassResolver calling super.resolveMandatoryClass with name: {}", name.substring(1));
            return super.resolveMandatoryClass(name.substring(1));
        } else {
            return super.resolveMandatoryClass(name);
        }
    }

}
