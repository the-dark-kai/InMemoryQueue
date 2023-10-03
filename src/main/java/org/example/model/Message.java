package org.example.model;

public class Message<K> {
    private K message;

    public K getMessage() {
        return message;
    }

    public void setMessage(K message) {
        this.message = message;
    }
}
