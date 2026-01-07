package ExecutorsAndExplicitLocking_02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageQueue {

    private final Queue<String> queue = new LinkedList<>();

    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();

    public MessageQueue() {
        System.out.println("Message Queue Created.");
    }

    public void put(String message) {
        lock.lock();
        try {
            queue.add(message);
            notEmpty.signalAll(); // wake waiting receivers
        } finally {
            lock.unlock();
        }
    }

    public String take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await(); // releases lock & waits
            }
            return queue.poll();
        } finally {
            lock.unlock();
        }
    }
}
