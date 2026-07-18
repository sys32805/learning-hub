import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Sequential stream
        List<Integer> squares = numbers.stream()
                .map(n -> n * n)      // Find the square of each number
                .filter(n -> n > 50)  // Filter out squares greater than 50
                .collect(Collectors.toList());

        System.out.println("Squares greater than 50: " + squares);
    }
}