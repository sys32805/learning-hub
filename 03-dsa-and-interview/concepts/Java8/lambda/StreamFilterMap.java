package rohit.Java8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFilterMap {
  public static void main(String[] args) {
    List<Customer> list = Arrays.asList(
        new Customer("rohit", 12),
        new Customer("roshan", 12),
        new Customer("alok", 12),
        new Customer("tarun", 12));
    List<String> names = list.stream().map((Customer :: getName)).collect(Collectors.toList());
    List<String> age = list.stream().filter(x-> x.getAge()%2 != 0).findAny().orElse(x);

    System.out.println(names);

  }
}
