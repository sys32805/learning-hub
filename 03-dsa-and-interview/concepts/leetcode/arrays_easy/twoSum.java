package rohit.leetcode.arrays_easy;

import java.util.Arrays;
import java.util.HashMap;

public class twoSum {
  public static void main(String[] args) {
    int[] nums = new int[] { 2, 7, 11, 15 };
    int target = 9;
    basicApproch(nums, target);
    usinghash(nums, target);
  }

   static void basicApproch(int[] nums, int target) {
    int length = nums.length - 1;
    while (length != 0) {
      for (int i = 0; i < nums.length; i++) {
        if (nums[length] + nums[i] == target && i != length) {
          System.err.println("The index are " + length + " and " + i);
        }
      }
      length--;
    }
  }

  static void usinghash(int[] nums, int target) {
    HashMap<Integer, Integer> ob = new HashMap<>();
    int[] indexs = new int[2];
    for (int i = 0; i < nums.length; i++) {
      int targetRemainingNo = target - nums[i];

      if (ob.containsKey(targetRemainingNo)) {
        indexs[1] = ob.get(targetRemainingNo);
        indexs[0] = i;
      }
      ob.put(nums[i], i);
    }
    System.err.println(Arrays.toString(indexs));
  }
}
