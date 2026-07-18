# Sliding window

**Idea:** Keep a window of size `k` (or growing/shrinking) and slide it across the array — usually O(n).

**Your folder:** [slidingWindow](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/concepts/slidingWindow)

---

## Max sum of subarray of size k (your code)

```java
package rohit.slidingWindow;

public class maxSumSubArray {
  public static void main(String[] args){
    int a[] = new int[] { -1, -2, -3, -4};
    int k = 3;
    int sum = 0;
    int maxSum = 0;

    // first window
    for (int i = 0; i < k; i++) {
      sum = sum + a[i];
    }
    maxSum = sum;

    // slide: add right, remove left
    for(int j = k ; j < a.length ; j++){
      sum += a[j];
      sum -= a[j-k];
      maxSum = Math.max(maxSum, sum);
    }
    System.err.println(maxSum);
  }
}
```

---

## Other files in your folder

| File | GitHub |
|------|--------|
| `smallestSubarray.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/slidingWindow/smallestSubarray.java) |
| `LongestSubstringWithoutRepeatingCharacters.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/slidingWindow/LongestSubstringWithoutRepeatingCharacters.java) |
| `FrequencyOfMostFrequentElement.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/slidingWindow/FrequencyOfMostFrequentElement.java) |

[Concepts home](03-dsa-and-interview/concepts/README.md) · [Strings algorithms](03-dsa-and-interview/algorithms/strings.md)
