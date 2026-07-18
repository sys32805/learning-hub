# equals, hashCode & Immutable classes

**Level:** Product companies ask deeply

---

## Contract

1. If `a.equals(b)` → `a.hashCode() == b.hashCode()`  
2. Consistent while object doesn’t change fields used in equals  
3. Reflexive, symmetric, transitive, null-safe  

```java
final class UserId {
  private final String value;
  UserId(String value){ this.value = value; }

  @Override public boolean equals(Object o){
    if (this == o) return true;
    if (!(o instanceof UserId)) return false;
    return value.equals(((UserId)o).value);
  }
  @Override public int hashCode(){ return value.hashCode(); }
}
```

---

## Why both together?

`HashMap` / `HashSet` use `hashCode` to find bucket, then `equals` to find entry. Break contract → lost keys / duplicates.

---

## `==` vs `equals`

| | `==` | `equals` |
|---|------|----------|
| Objects | reference equality | logical equality (if overridden) |
| Primitives | value | N/A |

---

## Immutable class checklist

1. `final` class  
2. `private final` fields  
3. No setters  
4. Defensive copy of mutable inputs/outputs  
5. Prefer deep immutability  

```java
public final class Money {
  private final String currency;
  private final long paise;
  public Money(String currency, long paise) {
    this.currency = currency;
    this.paise = paise;
  }
  public String getCurrency(){ return currency; }
  public long getPaise(){ return paise; }
}
```

---

## Trick questions

**Q:** Why `String` is immutable?  
**A:** Security, string pool caching, thread-safety, hashCode caching.

**Q:** Can immutable class have mutable field?  
**A:** Only if never exposed / always copied — otherwise not truly immutable.

**Q:** `hashCode` always unique?  
**A:** No — collisions allowed. Equality requires equals.

**Q:** Override only `equals`?  
**A:** Broken for hash-based collections — always override both.

**Q:** `record` in modern Java?  
**A:** Auto equals/hashCode/toString — immutable-style data carriers (Java 16+).

Next: [Composition vs inheritance](03-dsa-and-interview/oops/composition-vs-inheritance.md)
