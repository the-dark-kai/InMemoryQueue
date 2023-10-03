package org.example.Consumer.impl;

import org.example.Queue.Queue;
import org.example.model.Offset;

import java.util.Collection;

public class ConsumerImpl implements org.example.Consumer.Consumer {

    private Queue queueService;

    public ConsumerImpl(Queue queueService) {
        this.queueService = queueService;
    }
    @Override
    public String subscribe(String topic, Offset offset) {
        return queueService.subscribe(topic, offset);
    }

    @Override
    public String subscribe(String topic, int offset) {
        return queueService.subscribe(topic, offset);
    }

    @Override
    public Collection<Object> poll(String subscriberId, String topic, int noOfMessages) {
        return this.queueService.poll(subscriberId, topic, noOfMessages);
    }
}
