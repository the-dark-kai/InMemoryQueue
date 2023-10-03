package org.example.Queue;

import org.example.model.Offset;

import java.util.Collection;

public interface TopicQueue {
    public Collection<Object> getMessages(int offset, int count);
    public boolean addMessage(Object message);

    public Integer resolveOffset(Offset offset);
}
