package org.example.Producer;

public interface Producer {
    public boolean publish(Object msg, String topic);
}
