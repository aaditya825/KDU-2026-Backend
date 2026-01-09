package ProducerConsumer_01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MessageSender implements Runnable{
    private final MessageQueue messageQueue;

    public MessageSender(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");
        for (int i = 1; i <= 5; i++) {
            String value = LocalDateTime.now().format(formatter) + "_" + i;
            messageQueue.put(value);
        }
    }
}
