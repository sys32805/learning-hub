# Dynamic programming (interview)

**Goal:** define state, transition, base case; 1D/2D DP; classic knapsack / LIS / coins / edit distance.

**Package:** [com.interview.dynamic](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/algorithms/src/com/interview/dynamic) (61 files)

## Pattern

```java
// Example: coin change (min coins) sketch
int[] dp = new int[amount + 1];
Arrays.fill(dp, amount + 1);
dp[0] = 0;
for (int a = 1; a <= amount; a++) {
  for (int c : coins) {
    if (c <= a) dp[a] = Math.min(dp[a], dp[a - c] + 1);
  }
}
```

## Start with these files

| File | GitHub |
|------|--------|
| `CoinChanging.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/dynamic/CoinChanging.java) |
| `CoinChangingMinimumCoin.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/dynamic/CoinChangingMinimumCoin.java) |
| `BitonicSequence.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/dynamic/BitonicSequence.java) |
| `BoxStacking.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/dynamic/BoxStacking.java) |
| `BurstBalloons.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/dynamic/BurstBalloons.java) |
| More classics | [folder](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/algorithms/src/com/interview/dynamic) |

## Related

- Recursion first: [concepts/recursion.md](../concepts/recursion.md)  
- Back to [Interview roadmap](../INTERVIEW-PREP.md)
