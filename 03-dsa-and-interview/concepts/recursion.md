# Recursion

**Idea:** A method calls itself until a **base case** stops it. Every call waits on the stack for the next return.

**When to use:** Factorial, Fibonacci, tree/graph DFS, divide-and-conquer.

**Folder on GitHub:** [concepts/recursion](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/concepts/recursion)

## Mental model

```
factorial(5)
  → 5 * factorial(4)
       → 4 * factorial(3)
            → … → factorial(0) = 1
```

## Example 1 — Factorial

[Open `factorial.java` on GitHub](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/recursion/factorial.java)

```java
public class factorial {
  public static void main(String args[]) {
    int number = 5;
    System.out.println(" The result is " + factorial(number));
  }

  private static int factorial(int number) {
    if (number == 0) {
      return 1; // base case
    }
    return number * factorial(number - 1);
  }
}
```

**Takeaway:** Always write the base case first.

## Example 2 — Fibonacci (your practice file)

[Open `Fibonacci.java` on GitHub](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/recursion/Fibonacci.java)

You have both a loop version and a recursive version in that file. Classic recursive form:

```java
private int fib(int n) {
  if (n <= 1) return n;          // base
  return fib(n - 1) + fib(n - 2); // recursive step
}
```

> Note: naive recursion is slow for large `n` (recomputes work). Interviews often ask you to fix it with DP / memoization.

## Other files in this folder

| File | GitHub |
|------|--------|
| `waterBottal.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/recursion/waterBottal.java) |
| `palindrom.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/recursion/palindrom.java) |
| `sumArrayElements.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/recursion/sumArrayElements.java) |
| `print.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/recursion/print.java) |

## Next

- More recursion drills: [algorithms → recursion](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/algorithms/src/com/interview/recursion)
- Back to [Concepts home](README.md)
