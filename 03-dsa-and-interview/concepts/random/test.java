package rohit;
import java.util.*;
public class test {
  public static void main(String[] args){
    int[] a = { 1, 2, 3 };
    int[] b = { 1, 2, 3 };
    int[] nums = {1,1,1,3};

    // int i = 0 ;
    // int j = 1;
    // int k ;
    // for (k = 0; k < nums.length; k++) {
    //   if( (nums[i] == nums[j]) && (nums[j] == nums[k])){
    //       System.out.println("This is here");
    //       nums[j] = nums[k];
    //   }
    // }
    // System.out.println(Arrays.toString(nums));
    String s = "abcda";
    int val = 0;
    int[] k = new int[s.length()];
    for(int i = 0 ; i < s.length()-1 ; i++){
      int index = 0 ;
      val = s.charAt(i)-s.charAt(i+1);
      k[index++] = val;
    }
    System.out.println(Arrays.toString(k));
  }
  static int arrayToint(int[] a , int[] b){
    String resulta = "";
    String resultb = "";
    for(int i = 0 ; i < a.length ; i++){
      resulta += Integer.toString(a[i]);
      resultb += Integer.toString(b[i]);
    }
    return Integer.parseInt(resulta) + Integer.parseInt(resultb);
  }
}
