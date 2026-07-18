import java.util.ArrayList;

public class intersection {
  public static void main(String args[]){
    int[] array1 = new int[]{ 4, 9, 5 };
    int[] array2 = new int[]{ 9, 4, 9 };
    ArrayList<Integer> list1 = new ArrayList<>();
    ArrayList<Integer> list2 = new ArrayList<>();

    for(int i : array1){
      list1.add(i);
    }
    int count = 0;
    for(int j : array2){
      count++;
      if(list1.contains(j)){
        list2.add(j);
        if(list1.contains(j)) {
          list1.remove(Integer.valueOf(j));
        }
      }
    } 
    System.err.println(list2);
  }
}
