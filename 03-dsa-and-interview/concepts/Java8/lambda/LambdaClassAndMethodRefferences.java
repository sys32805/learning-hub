package rohit.Java8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LambdaClassAndMethodRefferences {
  public static void main(String[] args) {
    List<String> nameList = Arrays.asList("null","1","2","3","4","5");
    nameList.forEach(new Consumer<String>() {
    @Override
    public void accept(String t){
      System.out.println(t);
    }
    });

    nameList.forEach(str-> System.out.println(str));
    nameList.forEach(System.out::println);
  }
}
