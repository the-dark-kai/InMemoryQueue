package org.example.Producer.impl;

import org.example.Queue.Queue;

public class ProducerImpl implements org.example.Producer.Producer {

    private Queue queueService;

    public ProducerImpl(Queue queueService) {
        this.queueService = queueService;
    }
    @Override
    public boolean publish(Object msg, String topic) {
        return this.queueService.publish(msg, topic);
    }
}
