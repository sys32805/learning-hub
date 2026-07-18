package rohit;

import java.util.ArrayList;
import java.util.List;

public class monotonic {
  public static void main(String[] args) {
    int[] a = { 1, 2, 3, 1, 5 };
    int left = 0;
    boolean isMonotonic = false;
    List<Integer> sb = new ArrayList<>();
    int right = a.length - 1;
      while (left < right) {
        if(a[left] < a[left+1]){
          isMonotonic = true;
        } else if(a[left] > a[left + 1]){
          isMonotonic = true;
        } else{
          isMonotonic =false;
        }
        left++;
      }
      System.out.println(isMonotonic);
  }
}
