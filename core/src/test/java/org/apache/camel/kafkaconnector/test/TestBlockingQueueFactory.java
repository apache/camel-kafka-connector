package org.apache.camel.kafkaconnector.test;

import org.apache.camel.component.seda.ArrayBlockingQueueFactory;
import org.apache.camel.component.seda.BlockingQueueFactory;

import java.util.concurrent.BlockingQueue;

public class TestBlockingQueueFactory implements BlockingQueueFactory {
    private Integer counter;

    BlockingQueueFactory bqf = new ArrayBlockingQueueFactory();

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    @Override
    public BlockingQueue create() {
        return bqf.create();
    }

    @Override
    public BlockingQueue create(int capacity) {
        return bqf.create(capacity);
    }
}
