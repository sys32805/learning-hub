package rohit.collection.collectionInterfaceBasedQuestion;

import java.util.Arrays;
import java.util.Collections;

public class RotateElementBy_k {
  public static void main(String[] args){
    int[] a = new int[]{1,2,3,4,5,6,7,8,9,10};
    System.err.println("Before rotation of array");
    System.err.println(Arrays.toString(a));
    int k = 5;
    int[] b =  new int[a.length];
    int u = 0;
    int i = 0;
    int limit = a.length;
    for(i = k ; i < limit; i++){
      b[u] = a[i];
      u++;
    System.err.println("result is " + i);
    }

    u = 0;
    while(k > 0){
      b[5+u] = a[u];
      u++;
      k--;
    }
    System.err.println(Arrays.toString(b) + " " + k);
  }
}
