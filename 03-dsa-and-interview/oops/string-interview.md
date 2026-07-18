# String interview (Java)

**Level:** TCS → Product (very frequent)

---

## Pool & creation

```java
String a = "java";
String b = "java";
String c = new String("java");
System.out.println(a == b); // true (pool)
System.out.println(a == c); // false (heap object)
System.out.println(a.equals(c)); // true
```

- Literals → String Pool  
- `new String(...)` → new heap object (may also intern pool copy)  
- `intern()` → returns pool reference  

---

## Immutable & thread-safe

`String` is final + immutable → safe sharing, hashCode cache, security for URLs/paths.

---

## `String` vs `StringBuilder` vs `StringBuffer`

| | String | StringBuilder | StringBuffer |
|---|--------|---------------|--------------|
| Mutable | No | Yes | Yes |
| Thread-safe | Yes (immutable) | No | Yes (synchronized) |
| Speed | Slow for concat loops | Fastest | Slightly slower than Builder |

```java
// Bad in loop
String s = "";
for (int i=0;i<10000;i++) s += i;

// Good
StringBuilder sb = new StringBuilder();
for (int i=0;i<10000;i++) sb.append(i);
```

---

## Trick questions

**Q:** Why `String` is final?  
**A:** Prevent subclassing that could break immutability/security/pool assumptions.

**Q:** `String s = "a" + "b"` at compile time?  
**A:** Often constant-folded to `"ab"` by compiler.

**Q:** `String s = a + b` with variables?  
**A:** Compiler uses `StringBuilder` (Java version dependent) under the hood.

**Q:** `substring` memory leak (old Java)?  
**A:** Pre-Java 7u6, substring shared backing array — now copies.

**Q:** `equals` vs `==` for String?  
**A:** Always prefer `equals` for content. `==` is reference (pool tricks confuse).

**Q:** How many objects: `String s = new String("abc");`?  
**A:** Often 2 — pool `"abc"` + new heap String (if `"abc"` not already pooled differently — classic answer: 1 or 2 depending on pool already having literal).

**Q:** `StringBuilder` equals?  
**A:** Does not override equals meaningfully for content like String — compare `toString()`.

Next: [Object & memory](03-dsa-and-interview/oops/object-memory.md)
