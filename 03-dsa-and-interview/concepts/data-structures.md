# Data structures

**Folder on GitHub:** [datastructures](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/concepts/datastructures)

Study one subfolder at a time. Click **Open** to read the Java file on GitHub.

## Arrays

| File | GitHub |
|------|--------|
| `MinElement.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/datastructures/Arrays/MinElement.java) |
| `TargetElement.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/datastructures/Arrays/TargetElement.java) |
| `mergeArrays.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/datastructures/Arrays/mergeArrays.java) |
| `deleteElement.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/datastructures/Arrays/deleteElement.java) |
| `pascalTranglre.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/datastructures/Arrays/pascalTranglre.java) |

## Sorting & search

| File | GitHub |
|------|--------|
| `BinarySearch.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/datastructures/sort/BinarySearch.java) |
| `insertionSort.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/datastructures/sort/insertionSort.java) |
| `mergeSort.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/datastructures/sort/mergeSort.java) |
| `CountingSort.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/datastructures/sort/CountingSort.java) |

## Rotation

| File | GitHub |
|------|--------|
| `RotateElementBy_k.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/datastructures/rotation/RotateElementBy_k.java) |
| `search_an_element_in_a_sorted_and_pivoted_array.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/datastructures/rotation/search_an_element_in_a_sorted_and_pivoted_array.java) |
| More | [Folder](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/concepts/datastructures/rotation) |

## Linked lists (SSL / DLL)

| Area | GitHub |
|------|--------|
| Singly linked list ops | [SSL](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/concepts/datastructures/SSL) |
| Doubly linked list | [DLL](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/concepts/datastructures/DLL) |

## Stack / String / Tree

| Area | GitHub |
|------|--------|
| Stack | [Open](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/concepts/datastructures/Stack) |
| String | [Open](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/concepts/datastructures/String) |
| Tree | [Open](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/concepts/datastructures/tree) |

## Binary search reminder

```java
int lo = 0, hi = a.length - 1;
while (lo <= hi) {
  int mid = lo + (hi - lo) / 2;
  if (a[mid] == target) return mid;
  if (a[mid] < target) lo = mid + 1;
  else hi = mid - 1;
}
return -1;
```

## Next

- [Algorithms home](../algorithms/README.md)
- [Concepts home](README.md)
