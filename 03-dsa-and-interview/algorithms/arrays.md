# Arrays (interview)

**Goal:** two pointers, prefix sums, kadane, Dutch flag, stock buy/sell, merge intervals thinking.

**Package:** [com.interview.array](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/algorithms/src/com/interview/array) (76 files)

## Patterns to master

```java
// Two pointers (sorted array)
int lo = 0, hi = a.length - 1;
while (lo < hi) {
  int sum = a[lo] + a[hi];
  if (sum == target) { /* found */ break; }
  if (sum < target) lo++; else hi--;
}

// Kadane — max subarray sum
int best = a[0], cur = a[0];
for (int i = 1; i < a.length; i++) {
  cur = Math.max(a[i], cur + a[i]);
  best = Math.max(best, cur);
}
```

## Start with these files

| File | Why | GitHub |
|------|-----|--------|
| `BuySellStockProfit.java` | Classic DP / greed | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/array/BuySellStockProfit.java) |
| `FirstPositiveMissing.java` | Index-as-hash trick | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/array/FirstPositiveMissing.java) |
| `FourSum.java` | k-sum / two pointers | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/array/FourSum.java) |
| `DuplicateNumberDetection.java` | Cycle / set | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/array/DuplicateNumberDetection.java) |
| `CommonThreeSortedArray.java` | Multi-pointer | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/array/CommonThreeSortedArray.java) |
| `ChunkMerge.java` | Merge intervals style | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/array/ChunkMerge.java) |

## Related on site

- Concepts: [data-structures.md](03-dsa-and-interview/concepts/data-structures.md)  
- LeetCode: [problems](04-leetcode/Leetcode/README.md)  
- Next topic: [strings.md](03-dsa-and-interview/algorithms/strings.md)
