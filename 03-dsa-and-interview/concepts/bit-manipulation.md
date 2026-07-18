# Bit manipulation

**Idea:** Work with numbers at the binary level (`&`, `|`, `^`, `~`, `<<`, `>>`).

**When useful:** Flags, missing numbers, subsets, interview “bit tricks”.

**Folder on GitHub:** [bit_manipulation](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/concepts/bit_manipulation)

## Example — decimal to binary

[Open `NumToBinary.java`](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/bit_manipulation/NumToBinary.java)

```java
int num = 5;
String binary = "";
while (num > 0) {
  binary = (num % 2) + binary; // remainder is next bit
  num = num / 2;
}
System.out.println(binary); // 101
```

## Quick operators cheat sheet

| Op | Meaning | Example |
|----|---------|---------|
| `&` | AND | `6 & 3` → `2` |
| `\|` | OR | `6 \| 3` → `7` |
| `^` | XOR | `6 ^ 3` → `5` |
| `<<` | left shift | `1 << 3` → `8` |
| `>>` | right shift | `8 >> 2` → `2` |

## Other files

| File | GitHub |
|------|--------|
| `min.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/bit_manipulation/min.java) |
| `minBitFlips.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/concepts/bit_manipulation/minBitFlips.java) |

## Next

- [algorithms → bits](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/algorithms/src/com/interview/bits)
- [Concepts home](README.md)
