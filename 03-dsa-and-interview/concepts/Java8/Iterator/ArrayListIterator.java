package rohit.Java8.Iterator;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListIterator {
  public static void main(String[] args) {
    ArrayList<String> list =  new ArrayList<>();
    list.add("Twilight classic");
    list.add("malignant");
    list.add("x+y");
    list.add("Back to the future");
    // 1 using foreach method
    list.forEach(show -> System.out.println(show));
    System.out.println("=====================================");
    // 2. using Iterator
    Iterator<String> it = list.iterator();
    while (it.hasNext()) {
      var String = it.next();
      System.out.println(String);
    }
    // System.out.println("=====================================");
  }
}
