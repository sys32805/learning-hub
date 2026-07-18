# Polymorphism — tricks & traps

**Level:** TCS → FAANG-style Java rounds

---

## Two types

| Type | How | When bound |
|------|-----|------------|
| Overloading | Same name, different params | Compile time |
| Overriding | Same signature in subclass | Runtime |

```java
class Calc {
  int add(int a, int b) { return a+b; }
  double add(double a, double b) { return a+b; } // overload
}
class Printer {
  void print() { System.out.println("base"); }
}
class ColorPrinter extends Printer {
  @Override void print() { System.out.println("color"); }
}
Printer p = new ColorPrinter();
p.print(); // color
```

---

## Trick questions

**Q:** Is this overriding?

```java
class A { static void s(){ System.out.println("A"); } }
class B extends A { static void s(){ System.out.println("B"); } }
A ref = new B();
ref.s();
```

**A:** Prints `A`. Static = **method hiding**. Not polymorphism.

**Q:** Overload with `null`?

```java
void m(String s){}
void m(Object o){}
m(null); // which?
```

**A:** Calls `m(String)` — most specific match. If `m(Integer)` and `m(String)` both exist → **compile error** (ambiguous).

**Q:** Can we override `private` / `final` / `static`?  
**A:** No.

**Q:** Overriding and exceptions?  
**A:** Child can throw fewer / narrower checked exceptions, not broader new checked ones.

**Q:** `varargs` overloading trap?

```java
void m(int a){}
void m(int... a){}
m(5); // prefers exact int
```

**Q:** Runtime polymorphism needs?  
**A:** Inheritance + method overriding + parent reference to child object.

**Q:** Does operator overloading exist in Java?  
**A:** No (except `+` for String — language feature, not user-defined).

---

## Product follow-up

**Q:** How does JVM do dynamic dispatch?  
**A:** Virtual method table (vtable) / invokevirtual — method chosen from actual object class.

Next: [Abstract vs Interface](03-dsa-and-interview/oops/abstract-vs-interface.md)
