# Trees (interview)

**Goal:** DFS/BFS traversals, BST ops, LCA, path sums, serialize ideas, balanced trees awareness.

**Package:** [com.interview.tree](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/algorithms/src/com/interview/tree) (78 files)

## Patterns

```java
// DFS
void dfs(TreeNode n) {
  if (n == null) return;
  dfs(n.left);
  dfs(n.right);
}

// BFS
Queue<TreeNode> q = new ArrayDeque<>();
q.add(root);
while (!q.isEmpty()) {
  TreeNode cur = q.poll();
  if (cur.left != null) q.add(cur.left);
  if (cur.right != null) q.add(cur.right);
}
```

## Start with these files

| File | GitHub |
|------|--------|
| `BinaryTree.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/tree/BinaryTree.java) |
| `BinaryTreeMaximumPathSum.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/tree/BinaryTreeMaximumPathSum.java) |
| `BinaryTreeToDoubleLinkList.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/tree/BinaryTreeToDoubleLinkList.java) |
| `AVLTree.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/tree/AVLTree.java) |
| `AddGreaterValueNodeToEveryNode.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/tree/AddGreaterValueNodeToEveryNode.java) |

## Next

[graphs.md](/03-dsa-and-interview/algorithms/graphs)
