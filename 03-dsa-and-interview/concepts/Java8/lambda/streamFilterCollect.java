package rohit.Java8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class streamFilterCollect {
  public static void main(String[] args) {
    // List<Customer> list = Arrays.asList(
    //     new Customer("rohit", 12),
    //     new Customer("roshan", 12),
    //     new Customer("alok", 12),
    //     new Customer("tarun", 12));
    List<String> list = Arrays.asList("rohit","rahul","roshan","alok");
    List<String> result = list.stream().filter(str -> !str.equals("rahul"))
    .collect(Collectors.toList());

    result.forEach(System.out::println);
    //get all name using map


  }
}