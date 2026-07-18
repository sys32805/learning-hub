package rohit.leetcode.arrays_easy;

public class happyNumber {
  public static void main(String[] args) {
    Integer.MIN_VALUE
  }
}

class Solution {
  // Function to find the maximum sum subarray
  public int maxSubArray(int[] arr) {
    // Initialize variables
    int max_so_far = Integer.MIN_VALUE; // This will store the overall maximum sum
    int max_ending_here = 0; // This will store the sum of the current subarray

    // Iterate through the array
    for (int i = 0; i < arr.length; i++) {
      // Update max_ending_here to either the current element itself
      // or the sum of the current element and the previous subarray sum
      max_ending_here = Math.max(arr[i], max_ending_here + arr[i]);

      // Update max_so_far to the maximum of itself or max_ending_here
      max_so_far = Math.max(max_so_far, max_ending_here);
    }

    return max_so_far;
  }
}
