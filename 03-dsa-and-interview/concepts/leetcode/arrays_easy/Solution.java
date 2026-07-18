package rohit.leetcode.arrays_easy;

import java.util.*;
class Solution {

  public static int[] nextGreaterElements(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];
    Arrays.fill(result, -1); // Initialize result array with -1
    System.err.println(" Checkout " + Arrays.toString(result));
    Stack<Integer> stack = new Stack<>();

    // Traverse the array from right to left
    for (int i = n - 1; i >= 0; i--) {
      // Pop elements from stack while they are smaller than nums[i]
      while (!stack.isEmpty() && stack.peek() <= nums[i]) {
        stack.pop();
      }

      // If stack is not empty, stack.peek() is the next greater element for nums[i]
      if (!stack.isEmpty()) {
        result[i] = stack.peek();
      }

      // Push current element onto stack
      stack.push(nums[i]);
    }

    return result;
  }

  public static void main(String[] args) {
    int[] nums = { 4, 2, 7, 3, 1, 5, 6 };
    int[] result = nextGreaterElements(nums);

    System.out.println("Next Greater Elements:");
    for (int i = 0; i < nums.length; i++) {
      System.out.println(nums[i] + " --> " + result[i]);
    }
  }
}
