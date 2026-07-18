# Graphs (interview)

**Goal:** BFS/DFS, cycle detection, topological sort, shortest path (Dijkstra / Bellman-Ford), bipartite.

**Package:** [com.interview.graph](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/algorithms/src/com/interview/graph)

## Patterns

```java
// BFS adjacency list
Map<Integer, List<Integer>> g = ...;
boolean[] seen = new boolean[n];
Queue<Integer> q = new ArrayDeque<>();
q.add(start); seen[start] = true;
while (!q.isEmpty()) {
  int u = q.poll();
  for (int v : g.getOrDefault(u, Collections.emptyList())) {
    if (!seen[v]) { seen[v] = true; q.add(v); }
  }
}
```

## Start with these files

| File | GitHub |
|------|--------|
| `BellmanFordShortestPath.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/graph/BellmanFordShortestPath.java) |
| `BiparteGraph.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/graph/BiparteGraph.java) |
| `ArticulationPoint.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/graph/ArticulationPoint.java) |
| `AlientDictionary.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/graph/AlientDictionary.java) |
| Cycle / topo | browse [folder](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/algorithms/src/com/interview/graph) |

## Next

[dp.md](/03-dsa-and-interview/algorithms/dp)
