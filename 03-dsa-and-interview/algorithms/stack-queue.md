# Stack & Queue (interview)

**Goal:** monotonic stack, parentheses, histogram, BFS queue, sliding window max ideas.

**Package:** [com.interview.stackqueue](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/algorithms/src/com/interview/stackqueue)

## Patterns

```java
Deque<Integer> st = new ArrayDeque<>();
// monotonic decreasing stack example
for (int x : nums) {
  while (!st.isEmpty() && st.peek() < x) st.pop();
  st.push(x);
}
```

## Start with these files

| File | GitHub |
|------|--------|
| `MaximumHistogram.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/stackqueue/MaximumHistogram.java) |
| `RemoveExtraBrackets.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/stackqueue/RemoveExtraBrackets.java) |
| `ReverseStackUsingRecursion.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/stackqueue/ReverseStackUsingRecursion.java) |
| `CircularQueue.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/stackqueue/CircularQueue.java) |
| `MedianFinder.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/stackqueue/MedianFinder.java) |

## Next

[binary-search.md](binary-search.md)
