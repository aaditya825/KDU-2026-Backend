package ProducerConsumer_01;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        MessageQueue queue = new MessageQueue();

        // Producers
        Thread sender1 = new Thread(new MessageSender(queue));
        Thread sender2 = new Thread(new MessageSender(queue));
        Thread sender3 = new Thread(new MessageSender(queue));

        // Consumers
        Thread receiver1 = new Thread(new MessageReceiver(queue));
        Thread receiver2 = new Thread(new MessageReceiver(queue));
        Thread receiver3 = new Thread(new MessageReceiver(queue));

        sender1.start();
        sender2.start();
        sender3.start();

        receiver1.start();
        receiver2.start();
        receiver3.start();
    }
}
