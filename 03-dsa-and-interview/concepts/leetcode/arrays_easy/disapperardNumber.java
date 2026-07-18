package rohit.leetcode.arrays_easy;

/**
 * disapperardNumber
 */

/*
 * This approch use o(n) and o(n) space and time
 */
import java.util.*;
public class disapperardNumber {
  public static void main(String[] args){
    ArrayList<Integer> list  = new ArrayList<Integer>();
    int a[] = new int[]{ 1, 1};
    int b[] = new int[a.length];
    int length  = a.length;
    int index = 0;
    int max = a[0];
    int count = 0;
    int i = 0;

    while(length > 0) {
      if(max < a[index]){
        max= a[index];
      }
      count++;
      length--;
      index++;
    }
    int newmax = max > count ? max : count;
    System.err.println("The count is " + count);
    for (i = 1; i <= newmax; i++) {
      list.add(i);
    }
    i = 0;
    int newLength = a.length;
    while (newLength > 0) {
      if(list.contains(a[i])){
        list.remove(Integer.valueOf(a[i]));
      }
      i++;
      newLength--;
    }
    System.err.println(" The result is " + list);
  }
}