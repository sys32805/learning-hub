package rohit.Java8.lambda;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerInterface {
  public static void main(String[] args) {
    Consumer<String> func = x -> System.out.println(x);
    func.accept("This is naveen here");
    List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
    list.forEach(x -> System.out.println("hey this no is " + x));
  }
}
/*
 * @Note
 * In stream example list.forEach(x -> System.out.println("hey this no is " + x));
 * made for best optimal use and since it is used for best optimal and reduce the noise hence
 * 1 can be passed through without breakts and hence list.forEach(x -> system.out.println(x))
 * can be write but it can be reduce more extent hence it will be like list.forEach(System.out::println)//this is an
 * internal Iterator
 */

 /*
  * Since it reduces the noise hence it reduce the menory and work of garbage collection hence it suitable for
  * for work
  */