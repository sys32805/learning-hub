package rohit.leetcode.arrays_easy;

public class printSubarrays {
  public static void main(String[] args){
    int[] a = new int[]{1,2,3,4,5,6,7,8,9};
    int k = 3;
    int l = 0;
    int n = a.length;
    for(int  i = 0 ; i < a.length ; i++){
      for( int  j = i ; j < a.length && j < i+3 ; j++){
        System.err.println(a[j]);
      }
      System.err.println();
    }
  }
}
