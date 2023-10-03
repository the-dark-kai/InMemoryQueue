package org.example.Queue.impl;

import org.example.Queue.TopicQueue;
import org.example.model.Offset;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArrayListTopicQueue implements TopicQueue {
    private List<Object> topicQueue;
    public ArrayListTopicQueue() {
        this.topicQueue = new ArrayList<>();
    }
    @Override
    public Collection<Object> getMessages(int offset, int count) {
        if((offset+count) > this.topicQueue.size()) {
            return topicQueue.subList(offset, topicQueue.size());
        }
        return topicQueue.subList(offset, offset+count);
    }

    @Override
    public boolean addMessage(Object message) {
        this.topicQueue.add(message);
        return true;
    }

    @Override
    public Integer resolveOffset(Offset offset) {
        switch(offset) {
            case LATEST: return this.topicQueue.size();
            default: return 0;
        }
    }
}
