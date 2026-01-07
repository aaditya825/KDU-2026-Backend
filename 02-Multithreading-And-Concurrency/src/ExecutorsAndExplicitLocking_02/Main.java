package ExecutorsAndExplicitLocking_02;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        MessageQueue queue = new MessageQueue();

        int Count_sender = 3;
        int Count_receiver = 3;

        ExecutorService senderExecutor = Executors.newFixedThreadPool(3);
        ExecutorService receiverExecutor = Executors.newFixedThreadPool(3);


        for (int i = 0; i < Count_sender; ++i) {
            // Execute sender
            senderExecutor.execute(new MessageSender(queue));
        }

        for (int i = 0; i < Count_receiver; ++i) {
            // Execute sender
            receiverExecutor.execute(new MessageReceiver(queue));
        }

        senderExecutor.shutdown();
        receiverExecutor.shutdown();
    }
}
