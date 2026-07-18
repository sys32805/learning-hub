# Binary search (interview)

**Goal:** classic search, first/last occurrence, search in rotated array, answer-on-range (binary search on answer).

**Package:** [com.interview.binarysearch](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/algorithms/src/com/interview/binarysearch)

## Pattern

```java
int lo = 0, hi = n - 1;
while (lo <= hi) {
  int mid = lo + (hi - lo) / 2;
  if (ok(mid)) { /* record + move */ }
  else { /* other side */ }
}
```

## Start with these files

| File | GitHub |
|------|--------|
| `BinarySearch.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/binarysearch/BinarySearch.java) |
| `FirstOccurrenceOfNumberInSortedArray.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/binarysearch/FirstOccurrenceOfNumberInSortedArray.java) |
| `CircularBinarySearch.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/binarysearch/CircularBinarySearch.java) |
| `MedianOfTwoSortedArray.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/binarysearch/MedianOfTwoSortedArray.java) |
| `FloorAndCeilingSortedArray.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/binarysearch/FloorAndCeilingSortedArray.java) |

## Related LeetCode in your repo

- [0033 search in rotated sorted array](https://github.com/sys32805/learning-hub/tree/main/04-leetcode/Leetcode/0033-search-in-rotated-sorted-array)
- [0004 median of two sorted arrays](https://github.com/sys32805/learning-hub/tree/main/04-leetcode/Leetcode/0004-median-of-two-sorted-arrays)

## Next

[trees.md](trees.md)
