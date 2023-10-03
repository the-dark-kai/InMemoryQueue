package org.example.Consumer;

import org.example.model.Offset;

import java.util.Collection;

public interface Consumer {
    public String subscribe(String topic, Offset offset);
    public String subscribe(String topic, int offset);
    public Collection<Object> poll(String subscriberId, String topic, int noOfMessages);
}
