package rohit.Java8.lambda;

import java.util.function.Supplier;

public class SupplierInterface {
  public static void main(String[] args) {
      //take no argument and return the result
      SayMyName(()->"rohit");
  }
  public static void SayMyName(Supplier<String> name){
    System.out.println(name.get());
  }
}
