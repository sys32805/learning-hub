# Encapsulation & access modifiers

**Level:** TCS → Product

---

## Access table

| Modifier | Class | Package | Subclass | World |
|----------|:-----:|:-------:|:--------:|:-----:|
| `private` | Y | N | N | N |
| default (package) | Y | Y | N* | N |
| `protected` | Y | Y | Y | N |
| `public` | Y | Y | Y | Y |

\*Subclass **outside package** cannot access default members.

```java
package a;
public class A {
  private int p;
  int d;          // default
  protected int r;
  public int u;
}
```

---

## Encapsulation best practices

1. Fields `private`  
2. Expose via methods  
3. Validate in setters  
4. Prefer immutable where possible  

---

## Trick questions

**Q:** Can a class be `private`?  
**A:** Top-level class: only `public` or default. Nested class can be `private`.

**Q:** Can `protected` be accessed without inheritance?  
**A:** Yes — same package can access `protected` members.

**Q:** Difference `protected` vs default?  
**A:** `protected` = package + subclasses (even other packages). Default = package only.

**Q:** Outer class access private of inner?  
**A:** Yes — nested classes can access each other’s privates.

**Q:** Is getter/setter always good encapsulation?  
**A:** Not if they blindly expose mutable internals (e.g. return live `List` reference). Return copies / unmodifiable views.

**Q:** Package-private use case?  
**A:** Hide API inside module/package — clean layering (product design).

Next: [static / final / this / super](03-dsa-and-interview/oops/static-final-this-super.md)
