import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollectExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry");

        List<String> collectedWords = words.stream()
            .collect(Collectors.toList());

        System.out.println(collectedWords);
    }
}