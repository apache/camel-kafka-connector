package org.apache.camel.kafkaconnector.utils;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Endpoint;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;
import org.apache.camel.main.MainListener;
import org.apache.camel.main.MainSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CamelMainSupport {
    private static Logger log = LoggerFactory.getLogger(CamelMainSupport.class);

    private Main camelMain;
    private CamelContext camel;

    private final ExecutorService exService = Executors.newSingleThreadExecutor();
    private final CountDownLatch startFinishedSignal = new CountDownLatch(1);

    public CamelMainSupport(Map<String, String> props, String fromUrl, String toUrl) throws Exception {
        this.camel = new DefaultCamelContext();
        camelMain = new Main() {
            @Override
            protected ProducerTemplate findOrCreateCamelTemplate() {
                return camel.createProducerTemplate();
            }
            @Override
            protected CamelContext createCamelContext() {
                return camel;
            }
        };

        camelMain.addMainListener(new CamelMainFinishedListener());

        //XXX: can be removed once upgrading to camel 3.0.0-M4 or newer
        this.camel.getRegistry().bind("properties", new OrderedPropertiesComponent());
        this.camel.setClassResolver(new WorkaroundClassResolver());
        //XXX: end

        //XXX: can be removed once upgrading to camel 3.0.0-M4 or newer
        //order the properties ensuring that properties who's value starts with  "#class:", go first
        Map<String, String> orderedProps = new LinkedHashMap<>();
        props.keySet().stream()
                .filter( k -> props.get(k).startsWith("#class:"))
                .forEach( k -> orderedProps.put(k, props.get(k)));
        props.keySet().stream()
                .filter( k -> !props.get(k).startsWith("#class:"))
                .forEach( k -> orderedProps.put(k, props.get(k)));
        //XXX: end

        Properties camelProperties = new OrderedProperties();
        camelProperties.putAll(orderedProps);

        log.info("Setting initial properties in Camel context: [{}]", camelProperties);
        this.camel.getPropertiesComponent().setInitialProperties(camelProperties);

        log.info("Creating Camel route from({}).to({})", fromUrl, toUrl);
        this.camel.addRoutes(new RouteBuilder() {
            public void configure() {
                from(fromUrl).to(toUrl);
            }
        });
    }

    public void start() throws InterruptedException {
        log.info("Starting CamelContext");
        exService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    camelMain.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        startFinishedSignal.await();
        log.info("CamelContext started");
    }

    public void stop() {
        log.info("Stopping CamelContext");
        camelMain.stop();
        exService.shutdown();
        log.info("CamelContext stopped");
    }

    public ProducerTemplate createProducerTemplate() {
        return camel.createProducerTemplate();
    }

    public Endpoint getEndpoint(String endpointUrl) {
        return camel.getEndpoint(endpointUrl);
    }

    public ConsumerTemplate createConsumerTemplate() {
        return camel.createConsumerTemplate();
    }

    private class CamelMainFinishedListener implements MainListener {
        @Override
        public void configure(CamelContext context) {

        }

        @Override
        public void beforeStart(MainSupport main) {

        }

        @Override
        public void afterStart(MainSupport main) {
            startFinishedSignal.countDown();
        }

        @Override
        public void beforeStop(MainSupport main) {

        }

        @Override
        public void afterStop(MainSupport main) {

        }
    }
}
