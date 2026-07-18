package rohit.collection;
import java.util.ArrayList;

public class ArrayListExample {
  public static void main(String[] args) {
    // Creating an ArrayList of Strings
    ArrayList<String> list = new ArrayList<>();

    // Adding elements to the ArrayList
    list.add("Apple");
    list.add("Banana");
    list.add("Cherry");

    // Displaying the ArrayList
    System.out.println("ArrayList elements: " + list);

    // Accessing elements using get() method
    String firstElement = list.get(0);
    System.out.println("First element: " + firstElement);

    // Checking if an element exists in the ArrayList
    boolean containsBanana = list.contains("Banana");
    System.out.println("Does list contain 'Banana'? " + containsBanana);

    // Removing an element from the ArrayList
    list.remove("Cherry");
    System.out.println("ArrayList after removing 'Cherry': " + list);

    // Size of the ArrayList
    int size = list.size();
    System.out.println("Size of ArrayList: " + size);

    // Iterating over ArrayList using enhanced for loop
    System.out.print("ArrayList elements: ");
    for (String fruit : list) {
      System.out.print(fruit + " ");
    }
    System.out.println();

    // Clearing the ArrayLis  t
    list.clear();
    System.out.println("ArrayList after clearing: " + list);
  }
}
