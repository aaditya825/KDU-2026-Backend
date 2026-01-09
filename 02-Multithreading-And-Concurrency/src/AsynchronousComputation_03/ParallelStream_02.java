package AsynchronousComputation_03;

import java.util.ArrayList;
import java.util.List;

public class ParallelStream_02 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for(int i=1;i<=1_000_000;++i) {
            list.add(i);
        }

        // Sequential Stream
        long startSeq = System.currentTimeMillis();

        long sequentialSum = list.stream()
                .mapToLong(Integer::longValue)
                .sum();

        long endSeq = System.currentTimeMillis();

        // Parallel Stream

        long startPar = System.currentTimeMillis();

        long parallelSum = list.parallelStream()
                .mapToLong(Integer::longValue)
                .sum();

        long endPar = System.currentTimeMillis();

        // Results:

        System.out.println("Sequential Sum: " + sequentialSum);
        System.out.println("Sequential Time: " + (endSeq - startSeq) + " ms");

        System.out.println("Parallel Sum: " + parallelSum);
        System.out.println("Parallel Time: " + (endPar - startPar) + " ms");
    }
}
