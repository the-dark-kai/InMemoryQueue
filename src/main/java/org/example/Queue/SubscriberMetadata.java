package org.example.Queue;

public class SubscriberMetadata {
    private String topic;
    private Integer offset;

    public SubscriberMetadata(String topic, Integer offset) {
        this.topic = topic;
        this.offset = offset;
    };

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
