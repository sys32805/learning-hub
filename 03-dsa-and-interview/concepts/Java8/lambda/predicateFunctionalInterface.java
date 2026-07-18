//package rohit.Java8.lambda;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
public class predicateFunctionalInterface {
  public static void main(String[] args) {
    Predicate<Integer> func = x -> x > 5;
    List<Integer> List = Arrays.asList(1,2,3,4,5,6,3,4,6,6,7,8,8,9);
    //using stream api we need to found the values which are greater then 5
    List<Integer> colList =  List.stream().filter(func).collect(Collectors.toList());
    System.out.println(colList);
    List<String> list2 = Arrays.asList("rohit","rohit1","roh","rohan","sjdjsd");
    Predicate<String>  fun3 = x -> x.startsWith("roh");
    var collect2 = list2.stream().filter(fun3.negate()).collect(Collectors.toList());
    System.out.println(collect2);
  }
}
