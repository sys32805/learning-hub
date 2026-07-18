# Linked list (interview)

**Goal:** reverse, middle, cycle, merge, dummy head, fast/slow pointers.

**Package:** [com.interview.linklist](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/algorithms/src/com/interview/linklist)

## Patterns

```java
// Reverse iterative
ListNode prev = null, cur = head;
while (cur != null) {
  ListNode next = cur.next;
  cur.next = prev;
  prev = cur;
  cur = next;
}
return prev;

// Fast / slow — middle or cycle
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
  slow = slow.next;
  fast = fast.next.next;
}
```

## Start with these files

| File | GitHub |
|------|--------|
| `DeleteDuplicateNodes.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/linklist/DeleteDuplicateNodes.java) |
| `AddNumberRepresentedByLinkList.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/linklist/AddNumberRepresentedByLinkList.java) |
| `FlattenLinkList.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/linklist/FlattenLinkList.java) |
| `CopyLinkListWIthArbitPointer.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/linklist/CopyLinkListWIthArbitPointer.java) |
| `Merge sorted style` | browse [folder](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/algorithms/src/com/interview/linklist) |

## Related

- Concepts SSL/DLL: [data-structures.md](/03-dsa-and-interview/concepts/data-structures)  
- Next: [stack-queue.md](/03-dsa-and-interview/algorithms/stack-queue)
