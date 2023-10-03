package org.example.Queue.impl;

import org.example.Queue.Queue;
import org.example.Queue.SubscriberMetadata;
import org.example.Queue.TopicQueue;
import org.example.model.Offset;

import java.util.*;

public class InMemoryQueueImpl implements Queue {
    Map<String, TopicQueue> topicMap;
    Map<String, SubscriberMetadata> subscriberMetadataMap;

    public InMemoryQueueImpl() {
        this.topicMap = new HashMap<>();
        this.subscriberMetadataMap = new HashMap<>();
    }

    private void createTopic(String topic) {
        this.topicMap.put(topic, new ArrayListTopicQueue());
    }
    @Override
    public boolean publish(Object msg, String topic) {
        if(!this.topicMap.containsKey(topic)) {
            createTopic(topic);
        }
        this.topicMap.get(topic).addMessage(msg);
        return true;
    }

    @Override
    public String subscribe(String topic, Offset offset) {
        TopicQueue topicQueue = this.topicMap.get(topic);
        int offsetNum = topicQueue.resolveOffset(offset);
        return subscribe(topic, offsetNum);
    }

    @Override
    public String subscribe(String topic, int offset) {
        String newSubscriberId = UUID.randomUUID().toString();
        subscriberMetadataMap.put(newSubscriberId, new SubscriberMetadata(topic, offset));
        return newSubscriberId;
    }

    @Override
    public Collection<Object> poll(String subscriberId, String topic, int noOfMessages) {
        TopicQueue topicQueue = this.topicMap.get(topic);
        int currentOffset = this.subscriberMetadataMap.get(subscriberId).getOffset();
        Collection<Object> messages = topicQueue.getMessages(currentOffset, noOfMessages);
        updateSubscriberOffset(subscriberId, currentOffset + noOfMessages);
        return messages;
    }

    private void updateSubscriberOffset(String subscriberId, int offset) {
        this.subscriberMetadataMap.get(subscriberId).setOffset(offset);
    };
}
