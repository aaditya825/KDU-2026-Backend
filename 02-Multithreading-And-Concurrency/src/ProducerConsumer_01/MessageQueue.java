package ProducerConsumer_01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MessageQueue {
    Queue<String> messageQueue = new LinkedList<>();

    MessageQueue() {
        System.out.println("Message Queue Created.");
    }

    public synchronized void put(String message) {
        messageQueue.add(message);
        notifyAll();
    }

    public synchronized String take() throws InterruptedException {
        while(messageQueue.isEmpty()) {
            wait();  // Wait until a message is available
        }
        notifyAll();
        return messageQueue.poll();
    }


}
