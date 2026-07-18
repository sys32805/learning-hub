import java.util.Arrays;
import java.util.List;

public class ForEachExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry");

        words.stream()
            .forEach(System.out::println);
    }
}