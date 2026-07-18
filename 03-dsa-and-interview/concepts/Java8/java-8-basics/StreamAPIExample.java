import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamAPIExample {
    public static void main(String[] args) {
        // Create a stream from a list
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry", "fig", "grape");

        // Filter words that start with a vowel
        List<String> filteredWords = words.stream()
                .filter(word -> word.matches("^[aeiou].*"))
                .collect(Collectors.toList());

        System.out.println("Filtered Words: " + filteredWords);

        // Map words to their lengths
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());

        System.out.println("Word Lengths: " + wordLengths);

        // FlatMap example with nested lists
        List<List<String>> nestedList = Arrays.asList(
                Arrays.asList("a", "b", "c"),
                Arrays.asList("d", "e", "f"),
                Arrays.asList("g", "h", "i")
        );

        List<String> flatList = nestedList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println("Flat List: " + flatList);

        // Reduce example to concatenate strings
        String concatenated = words.stream()
                .reduce("", (a, b) -> a + b);

        System.out.println("Concatenated String: " + concatenated);

        // Group words by their length
        Map<Integer, List<String>> groupedByLength = words.stream()
                .collect(Collectors.groupingBy(String::length));

        System.out.println("Grouped by Length: " + groupedByLength);

        // Create and use a parallel stream to calculate the sum of squares
        int sumOfSquares = IntStream.range(1, 11)
                .parallel()
                .map(x -> x * x)
                .sum();

        System.out.println("Sum of Squares: " + sumOfSquares);
    }
}