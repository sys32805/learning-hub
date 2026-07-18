import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SkipExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");

        List<String> skippedWords = words.stream()
            .skip(2)
            .collect(Collectors.toList());

        System.out.println(skippedWords);
    }
}