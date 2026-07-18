/*
  Given an array of integers arr[] of size N and an integer,
  the task is to rotate the array elements to the left by d positions.
 */
package rohit.datastructures.rotation;

import java.util.Arrays;

public class Program_for_array_left_rotation_by_d_positions{
  public static void main(String args[]){
    int a[] = new int[]{1,2,3,4,5,6,7};
    int r = 2;
    int l = a.length;
    Program_for_array_left_rotation_by_d_positions ob = new Program_for_array_left_rotation_by_d_positions();
    ob.rotation(a ,r ,l);

  }

  public  void rotation(int[] a, int r, int l) {
   r  = 2;
   int i = 0;
   int j = 0;
   int k = 0;
   int temp[] = new int[a.length];
   for(j = r; j < a.length ; j++){
     temp[k++] = a[j];
    }
    for(i = 0; i < r ; i++){
     temp[k++] = a[i];
    }
   System.out.println(Arrays.toString(temp));
  }
}
