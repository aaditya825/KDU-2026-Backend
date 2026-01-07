package AsynchronousComputation_03;

import java.util.concurrent.*;

public class Callable_And_Future_01 {
    public static void main(String[] args) {

        Callable<Integer> callable = ()->{
            int sum = 0;
            int N = 100;
            for(int i=1;i<=N;++i) {
                sum += i;
            }

            return sum;
        };

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(callable);

        System.out.println("Starting future.get()...");
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Completed future.get()...");

        executor.shutdown();
    }
}
