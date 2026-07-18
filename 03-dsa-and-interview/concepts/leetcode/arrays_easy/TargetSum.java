package rohit.leetcode.arrays_easy;

public class TargetSum {
  public static void main(String[] args){
    int a[]  = {1,2,4,5,7};
    int target = 8;
    int length = a.length-1;
    while (length != 0) {
      int val = a[length];
      for(int i = 0 ; i < a.length-1 ; i++){
        if(val + a[i] == target && length != i){
          System.err.println("Target sum found at " + length + " , " + i );
        }
      }
      length--;
    }
  }
}
