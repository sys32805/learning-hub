package rohit.slidingWindow;

import java.util.Arrays;

public class FrequencyOfMostFrequentElement {

  public static int maxFrequency(int[] nums, int k) {
    Arrays.sort(nums);

    int maxCount = 0;
    int left = 0;
    int sum = 0;

    for (int right = 0; right < nums.length; right++) {

      sum += nums[right];

      while ((right - left + 1) * nums[right] - sum > k) {
        sum -= nums[left];
        left++;
      }
      maxCount = Math.max(maxCount, right - left + 1);
    }
    return maxCount;
  }

  public static void main(String[] args) {
    int[] nums = { 1, 2, 4 };
    int k = 5;
    System.out.println(maxFrequency(nums, k));
  }
}
