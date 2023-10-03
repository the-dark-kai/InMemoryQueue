package org.example;

import org.example.Consumer.Consumer;
import org.example.Consumer.impl.ConsumerImpl;
import org.example.Producer.Producer;
import org.example.Producer.impl.ProducerImpl;
import org.example.Queue.Queue;
import org.example.Queue.impl.InMemoryQueueImpl;
import org.example.model.Offset;


//Orchestrate things from here
public class Main {

    private static Queue queueService = new InMemoryQueueImpl();
    private static Consumer consumer1 = new ConsumerImpl(queueService);
    private static Consumer consumer2 = new ConsumerImpl(queueService);

    private static Producer producer1 = new ProducerImpl(queueService);
    private static Producer producer2 = new ProducerImpl(queueService);

    private static void refresh() {
        queueService = new InMemoryQueueImpl();
        consumer1 = new ConsumerImpl(queueService);
        consumer2 = new ConsumerImpl(queueService);

        producer1 = new ProducerImpl(queueService);
        producer2 = new ProducerImpl(queueService);
    }

    public static void main(String[] args) {
        refresh();
        testSingleTopicPublishSubscriber();
        refresh();
        testLatestSubscription();
    }

    public static void testSingleTopicPublishSubscriber() {
        producer1.publish("Message 1", "Topic1");
        String subscriberId1 = consumer1.subscribe("Topic1", 0);
        consumer1.poll(subscriberId1, "Topic1", 1).forEach((message) -> {
            System.out.println("Received message: " + message);
        });
    }

    public static void testLatestSubscription() {
        producer1.publish("Message 1", "Topic1");
        producer1.publish("Message 2", "Topic1");
        producer1.publish("Message 3", "Topic1");
        String subscriberId1 = consumer1.subscribe("Topic1", Offset.LATEST);
        producer1.publish("Message 4", "Topic1");
        producer1.publish("Message 5", "Topic1");
        producer1.publish("Message 6", "Topic1");
        consumer1.poll(subscriberId1, "Topic1", 3).forEach((message) -> {
            System.out.println("Received message: " + message);
        });
    }
}