# Bit manipulation

**Idea:** Work with numbers in binary (`& | ^ << >>`).

**Your folder:** [bit_manipulation](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/concepts/bit_manipulation)

---

## Decimal → binary (your code)

```java
package rohit.bit_manipulation;

public class NumToBinary {
  public static void main(String[] args) {
    int num  = 5;
    String binary = "";
    while(num > 0){
      binary = (num % 2) + binary;
      num = num / 2;
    }
    System.out.println(binary); // 101
  }
}
```

---

## Operators cheat sheet

| Op | Meaning |
|----|---------|
| `&` | AND |
| `\|` | OR |
| `^` | XOR |
| `<<` | left shift |
| `>>` | right shift |

## Other files

| File | GitHub |
|------|--------|
| `min.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/bit_manipulation/min.java) |
| `minBitFlips.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/bit_manipulation/minBitFlips.java) |

[Concepts home](03-dsa-and-interview/concepts/README.md)
