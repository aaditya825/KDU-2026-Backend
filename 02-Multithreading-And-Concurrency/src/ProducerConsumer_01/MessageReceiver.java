package ProducerConsumer_01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageReceiver implements Runnable{
    private final MessageQueue messageQueue;

    public MessageReceiver(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                System.out.println("Printing message from thread:" + Thread.currentThread().getName() + " MESSAGE: " + messageQueue.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }}
