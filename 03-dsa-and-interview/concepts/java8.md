# Java 8 (Streams & lambdas)

**Idea:** Write less boilerplate for collections using **lambdas** and the **Stream API** (`filter`, `map`, `collect`, …).

**Folders on GitHub:**

- [java-8-basics](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/concepts/Java8/java-8-basics)
- [lambda](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/concepts/Java8/lambda)

## Core pattern

```java
List<String> names = Arrays.asList("ann", "bob", "amy");

List<String> result = names.stream()
    .filter(n -> n.startsWith("a"))
    .map(String::toUpperCase)
    .collect(Collectors.toList());
```

## Basics — open these first

| File | GitHub |
|------|--------|
| `FilterExample.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/Java8/java-8-basics/FilterExample.java) |
| `MapExample.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/Java8/java-8-basics/MapExample.java) |
| `CollectExample.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/Java8/java-8-basics/CollectExample.java) |
| `ReduceExample.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/Java8/java-8-basics/ReduceExample.java) |
| `SortedExample.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/Java8/java-8-basics/SortedExample.java) |
| `ForEachExample.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/Java8/java-8-basics/ForEachExample.java) |
| `DistinctExample.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/Java8/java-8-basics/DistinctExample.java) |
| `FlatMapExample.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/Java8/java-8-basics/FlatMapExample.java) |

## Lambdas / functional interfaces

| File | GitHub |
|------|--------|
| `functionalInterfaceConcept.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/Java8/lambda/functionalInterfaceConcept.java) |
| `predicateFunctionalInterface.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/Java8/lambda/predicateFunctionalInterface.java) |
| `ConsumerInterface.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/Java8/lambda/ConsumerInterface.java) |
| `SupplierInterface.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/Java8/lambda/SupplierInterface.java) |
| `streamFilterCollect.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/Java8/lambda/streamFilterCollect.java) |

## Next

- [Concepts home](03-dsa-and-interview/concepts/README.md)
- OOPs functional interfaces note: [OOPs advanced](03-dsa-and-interview/oops/04-advanced-oops-interview.md)
