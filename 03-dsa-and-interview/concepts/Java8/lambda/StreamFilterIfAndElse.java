package rohit.Java8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamFilterIfAndElse {
  public static void main(String[] args) {
    List<Customer> list = Arrays.asList(
        new Customer("rohit", 12),
        new Customer("roshan", 12),
        new Customer("alok", 12),
        new Customer("tarun", 12));
    // find as store in list
    List<Customer> result = list.stream().filter(str -> "tarun".equals(str.getName())).collect(Collectors.toList());
    // else if found
    Customer resultNew = list.stream().filter(str -> "tarun".equals(str.getName())).findAny().orElse(null);
    // System.out.println(resultNew.getName() + " " + resultNew.getAge());
    //find using multiple conditions
    // find name with tarun and age 12
  }
}
