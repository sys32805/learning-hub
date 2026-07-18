# Recursion

**Idea:** A method calls itself until a **base case** stops it.

**Your files on GitHub:** [recursion folder](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/concepts/recursion)

---

## Factorial (your code)

```java
public class factorial {
  public static void main(String args[]){
    int number = 5;
    int result = factorial(number);
    System.out.println(" The result is " + result);
  }

  private static int factorial(int number) {
    if (number == 0){
      return 1; // base case
    } else {
      return number * factorial(number - 1);
      // 5*4*3*2*1 when it unwinds from 0
    }
  }
}
```

---

## Fibonacci (your code)

```java
public class Fibonacci {
  public static void main(String args[]){
    Fibonacci ob = new Fibonacci();
    int limit = 9;
    ob.series(); // loop version
    int result = ob.seriesUsingRecursion(limit);
    System.err.println(" The result is " + result);
  }

  private void series() {
    int previous = 0;
    int next = 1;
    int sum = 0;
    System.err.print(" The series -> " + 0 + ", " + 1);
    for(int i = 2 ; i <= 10 ; i++){
      sum = previous + next;
      next = previous;
      previous = sum;
      sum = next + previous;
      System.err.print(", " + sum);
    }
  }

  private int seriesUsingRecursion(int limit) {
    if(limit == 1) return 0;
    if(limit == 2) return 1;
    return seriesUsingRecursion(limit-1) + seriesUsingRecursion(limit-1);
  }
}
```

> Tip for interviews: classic fib should be `fib(n-1) + fib(n-2)`. Your file is good practice to debug.

---

## Water bottles (your code)

```java
public class waterBottal {
  public static void main (String[] args){
    int numBottles = 15;
    int numExchange = 4;
    waterBottal ob = new waterBottal();
    ob.getResultOfBottals(numBottles , numExchange );
  }

  private void getResultOfBottals( int numBottles , int numExchange) {
    int result = numBottles + ( numBottles - 1) / (numExchange - 1);
    System.err.println(" The result is " + result);
  }
}
```

---

## Other files

| File | Link |
|------|------|
| `palindrom.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/recursion/palindrom.java) |
| `sumArrayElements.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/recursion/sumArrayElements.java) |
| `print.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/recursion/print.java) |

[Concepts home](03-dsa-and-interview/concepts/README.md) · [Interview roadmap](03-dsa-and-interview/INTERVIEW-PREP.md)
