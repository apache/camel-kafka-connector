package org.apache.camel.kafkaconnector.utils;

import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.component.properties.PropertiesLocation;
import org.apache.camel.util.FilePathResolver;
import org.apache.camel.util.ObjectHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//XXX: can be removed once upgrading to camel 3.0.0-M4 or newer
public class OrderedPropertiesComponent extends PropertiesComponent {
    @Override
    protected Properties doLoadProperties(List<PropertiesLocation> paths) {
        Properties prop = new OrderedProperties();

        // use initial properties
        if (getInitialProperties() != null) {
            prop.putAll(getInitialProperties());
        }

        // use locations
        if (paths != null) {
            // location may contain JVM system property or OS environment variables
            // so we need to parse those
            List<PropertiesLocation> locations = parseLocations(paths);

            Properties locationsProp = getPropertiesResolver().resolveProperties(getCamelContext(), true, locations);
            prop.putAll(locationsProp);
        }

        // use override properties
        if (getOverrideProperties() != null) {
            // make a copy to avoid affecting the original properties
            Properties override = new Properties();
            override.putAll(prop);
            override.putAll(getOverrideProperties());
            prop = override;
        }

        return prop;
    }

    private List<PropertiesLocation> parseLocations(List<PropertiesLocation> locations) {
        List<PropertiesLocation> answer = new ArrayList<>();

        for (PropertiesLocation location : locations) {
            log.trace("Parsing location: {}", location);

            try {
                String path = FilePathResolver.resolvePath(location.getPath());
                log.debug("Parsed location: {}", path);
                if (ObjectHelper.isNotEmpty(path)) {
                    answer.add(new PropertiesLocation(
                            location.getResolver(),
                            path,
                            location.isOptional())
                    );
                }
            } catch (IllegalArgumentException e) {
                log.debug("Ignored missing location: {}", location);
            }
        }

        // must return a not-null answer
        return answer;
    }
}
