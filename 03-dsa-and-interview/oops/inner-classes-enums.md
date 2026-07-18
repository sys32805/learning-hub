# Inner classes & Enums

**Level:** Service + Product

---

## Types of nested classes

| Type | Needs outer instance? | Can have static members? |
|------|----------------------|--------------------------|
| Static nested | No | Yes |
| Inner (non-static) | Yes | No (until newer Java nuances) |
| Local | Inside method | Limited |
| Anonymous | One-off | No name |

```java
class Outer {
  private int x = 1;
  class Inner { void show(){ System.out.println(x); } }
  static class Nested { void show(){ System.out.println("nested"); } }
}
```

```java
Runnable r = new Runnable() {
  public void run(){ System.out.println("anon"); }
};
```

---

## Why use them?

- Logical grouping  
- Encapsulation of helper types  
- Event listeners / adapters (anonymous / lambda now)  

---

## Enums

```java
enum Status {
  NEW, RUNNING, DONE;
  public boolean isTerminal(){ return this == DONE; }
}
```

- Enum constants are `public static final` instances  
- Can have fields, methods, constructors (private)  
- Comparable, Singleton-per-constant  
- Prefer enum over `int` constants  

### Enum tricks

**Q:** Can enum extend class?  
**A:** No — implicitly extends `Enum`. Can implement interfaces.

**Q:** Enum singleton?  
**A:** Yes — Josh Bloch: safest singleton (`INSTANCE`).

**Q:** `ordinal()` in DB?  
**A:** Risky if order changes — store `name()` or explicit code field.

**Q:** Switch on enum?  
**A:** Yes — exhaustiveness helps (esp. newer Java).

---

## Trick questions

**Q:** Can outer access private of inner?  
**A:** Yes (and vice versa).

**Q:** Memory leak with inner class?  
**A:** Non-static inner holds outer reference — don’t keep long-lived inner if outer is heavy.

**Q:** Lambda vs anonymous class?  
**A:** Lambda doesn’t create new `this`; can’t shadow outer `this` the same way; more concise for functional interfaces.

Next: [Design patterns](03-dsa-and-interview/oops/design-patterns.md)
