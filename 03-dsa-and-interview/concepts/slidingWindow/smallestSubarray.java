package rohit.slidingWindow;

import java.util.Arrays;

public class smallestSubarray {
  public static void main(String[] args){
    int[] nums = { 2, 1, 5, 2, 3, 2 };
    int n = nums.length;
    int min_length = Integer.MAX_VALUE;
    int left = 0;
    int s = 7;
    int current_sum = 0;
    for(int right = 0 ; right < nums.length ; right++){
      current_sum += nums[right];
      while(current_sum >= s){
        min_length = Math.min(min_length, right - left + 1);
        current_sum -= nums[left];
        left++;
      }
    }
    int result =  min_length == Integer.MAX_VALUE ? 0 : min_length;
    System.err.println("result is " + result);
  }
}
