import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelStreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Sequential stream
        List<Integer> squaresSequential = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("Sequential Squares: " + squaresSequential);

        // Parallel stream
        List<Integer> squaresParallel = numbers.parallelStream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("Parallel Squares: " + squaresParallel);

        // Parallel stream with IntStream
        int sumOfSquares = IntStream.range(1, 11)
                .parallel()
                .map(x -> x * x)
                .sum();
        System.out.println("Sum of Squares (Parallel): " + sumOfSquares);
    }
}