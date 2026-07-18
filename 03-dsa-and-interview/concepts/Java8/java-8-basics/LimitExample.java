import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LimitExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");

        List<String> limitedWords = words.stream()
            .limit(3)
            .collect(Collectors.toList());

        System.out.println(limitedWords);
    }
}