# Sliding window

**Idea:** Maintain a window `[left, right]` over an array/string. Slide it to find max/min/longest/shortest subarray that meets a condition — usually **O(n)** instead of O(n²).

**Folder on GitHub:** [slidingWindow](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/concepts/slidingWindow)

## Pattern

```text
left = 0
for right in 0..n-1:
  add a[right] into window
  while window is invalid:
    remove a[left]
    left++
  update answer
```

## Your practice files

| File | Typical idea | GitHub |
|------|--------------|--------|
| `maxSumSubArray.java` | Max sum of window size k | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/slidingWindow/maxSumSubArray.java) |
| `smallestSubarray.java` | Smallest window with sum ≥ target | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/slidingWindow/smallestSubarray.java) |
| `LongestSubstringWithoutRepeatingCharacters.java` | Longest unique substring | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/slidingWindow/LongestSubstringWithoutRepeatingCharacters.java) |
| `FrequencyOfMostFrequentElement.java` | Frequency with window ops | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/slidingWindow/FrequencyOfMostFrequentElement.java) |

## Tiny fixed-window example

```java
// max sum of any subarray of size k
int maxSum = 0, window = 0;
for (int i = 0; i < k; i++) window += a[i];
maxSum = window;
for (int i = k; i < a.length; i++) {
  window += a[i] - a[i - k]; // slide
  maxSum = Math.max(maxSum, window);
}
```

## Next

- [Concepts home](/03-dsa-and-interview/concepts/README)
- Practice similar problems in [04-leetcode](/04-leetcode/README)
