import java.util.HashSet;
import java.util.Arrays;

class Solution {
    public long minimalKSum(int[] nums, int k) {
        // Sort the array to process the numbers in order.
        Arrays.sort(nums);

        long sum = 0;
        int prev = 0; // This variable tracks the previous number.
        int idx = 0; // Pointer to nums.

        // Step 1: Add missing numbers from 1 to nums[0] - 1, if any
        while (k > 0 && prev < nums[0] - 1) {
            sum += prev + 1;
            prev++;
            k--;
        }

        // Step 2: Process the numbers from the sorted array
        while (idx < nums.length && k > 0) {
            int current = nums[idx];
            int start = prev + 1;  // Start checking from the next number after `prev`.

            // Add missing numbers between prev and current
            while (start < current && k > 0) {
                sum += start;
                start++;
                k--;
            }

            prev = current; // Update prev to current number.
            idx++;
        }

        // Step 3: If there are still missing numbers (after max element in nums), add them
        while (k > 0) {
            sum += prev + 1;  // Add next missing number
            prev++;  // Move to the next number.
            k--;  // Decrease k.
        }

        return sum;
    }
}
