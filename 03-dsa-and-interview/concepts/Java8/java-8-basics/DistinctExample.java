import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DistinctExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "apple", "cherry", "banana");

        List<String> distinctWords = words.stream()
            .distinct()
            .collect(Collectors.toList());

        System.out.println(distinctWords);
    }
}