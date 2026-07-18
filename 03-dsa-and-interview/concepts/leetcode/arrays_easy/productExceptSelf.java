package rohit.leetcode.arrays_easy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class productExceptSelf {
  public static void main(String[] args) {
    ArrayList<Integer> ob = new ArrayList<>();
    int nums[] = { 1, 2, 3, 4, 10 };
    /* approch 1 */
    // partionOFarray(nums);
    /* practice approch */
    // test(nums);
    /* 3232. Find if Digit Game Can Be Won */
    // boolean result = canAliceWin(nums);
    // System.out.println("The score is "+ result);
    /* decimal to binary */
    int a  = 10;
    decimalToBinary(a);

  }

  static void decimalToBinary(int i) {
    StringBuilder ob = new StringBuilder();
    while( i > 0) {
      int digit = i % 2;
      ob.append(digit);
      i = i / 2;
      // System.err.println(i);
    }
    System.err.println(ob);
  }

  static boolean canAliceWin(int[] nums) {
    int alicScore = 0;
    int bobScore = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] < 10)
        alicScore += nums[i];
      else
        bobScore += nums[i];
    }
    System.err.println("boths result is" + alicScore + bobScore);
    return alicScore == bobScore ? false : true;
  }

  static void partionOFarray(int[] nums) {
    int pre = 1;
    int post = 1;
    int[] a = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      a[i] = pre;
      pre *= nums[i];
    }
    for (int i = nums.length - 1; i >= 0; i--) {
      a[i] *= post;
      post *= nums[i];
    }
  }

  static void test(int[] nums) {
    System.err.println(Arrays.toString(nums));
    int a[] = new int[nums.length];
    int b[] = new int[nums.length];
    int prefix = 1;
    int suffix = 1;
    for (int i = 0; i < nums.length; i++) {
      a[i] = prefix;
      prefix *= nums[i];
    }
    for (int i = nums.length - 1; i >= 0; i--) {
      a[i] *= suffix;
      suffix *= nums[i];
    }
    System.err.println(Arrays.toString(a));
  }
}
