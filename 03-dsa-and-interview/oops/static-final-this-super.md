# static, final, this, super

**Level:** High frequency in every company

---

## `static`

Belongs to **class**, not instance. Loaded with class. Shared across objects.

```java
class Counter {
  static int count;
  static void inc(){ count++; }
}
```

### Tricks

**Q:** Can static method use `this`?  
**A:** No — no instance context.

**Q:** Can static method be overridden?  
**A:** No — hidden. See [polymorphism tricks](03-dsa-and-interview/oops/polymorphism-tricks.md).

**Q:** Static block vs instance block?  
**A:** Static block runs once at class load. Instance block runs every object creation before constructor.

**Q:** Order of init?  
**A:** Super static → child static → super instance → super ctor → child instance → child ctor.

---

## `final`

| On | Meaning |
|----|---------|
| variable | cannot reassign |
| method | cannot override |
| class | cannot extend |

```java
final class MathUtils {}
final int MAX = 10;
final List<String> list = new ArrayList<>();
list.add("a"); // OK — reference final, object mutable
```

### Tricks

**Q:** `final` reference means immutable object?  
**A:** No — only reference can’t change. Object can mutate unless class is immutable.

**Q:** Blank final?  
**A:** `final` field assigned once in constructor / instance block.

---

## `this` / `super`

```java
class A {
  int x;
  A(int x){ this.x = x; }
}
class B extends A {
  B(int x){ super(x); }
  void show(){ super.toString(); }
}
```

### Tricks

**Q:** `this()` and `super()` together?  
**A:** Impossible in same constructor — both need first line.

**Q:** `super` to call parent static?  
**A:** Unusual; static should be called with class name.

Next: [equals / hashCode / immutable](03-dsa-and-interview/oops/equals-hashcode-immutable.md)
