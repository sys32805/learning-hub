# Collections

**Idea:** Choose the right structure — `List`, `Set`, `Map`, `Queue` — and know big-O for add/get/contains.

**Folder on GitHub:** [collection](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/concepts/collection)

## Cheat sheet

| Type | When | Example |
|------|------|---------|
| `ArrayList` | Indexed list, fast get | interview arrays → list |
| `LinkedList` | Frequent insert/delete mid | rare in interviews |
| `HashSet` | Unique, fast contains | remove duplicates |
| `TreeSet` | Sorted unique | ordered keys |
| `HashMap` | key → value | frequency maps |
| `TreeMap` | sorted keys | range queries |

## Frequency map pattern (very common)

```java
Map<Character, Integer> freq = new HashMap<>();
for (char c : s.toCharArray()) {
  freq.put(c, freq.getOrDefault(c, 0) + 1);
}
```

## Your files

Browse everything here: [collection on GitHub](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/concepts/collection)

Also see interview notes under concepts: [interview/](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/concepts/interview)

## Next

- [Java 8 streams](/03-dsa-and-interview/concepts/java8) (collections + streams together)
- [Concepts home](/03-dsa-and-interview/concepts/README)
