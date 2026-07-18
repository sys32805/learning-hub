# Inheritance — tricks & traps

**Level:** Service → Product

---

## Rules in Java

- Single class inheritance (`extends`)  
- Multiple interface inheritance (`implements`)  
- Everything except constructors is eligible for inheritance (with access rules)  
- `private` members are not inherited (not visible in child)  
- `final` class cannot be extended; `final` method cannot be overridden  

```java
class Parent {
  int x = 10;
  void show() { System.out.println("P"); }
}
class Child extends Parent {
  int x = 20;
  @Override void show() { System.out.println("C"); }
}
```

---

## Trick questions

**Q:** Output?

```java
Parent p = new Child();
System.out.println(p.x);
p.show();
```

**A:** `10` then `C`.  
**Why:** Fields are resolved by **reference type** (Parent). Methods (instance) by **object type** (Child). Huge product-company trap.

**Q:** Can child access parent `private` field?  
**A:** Not directly. Use getters or `protected`.

**Q:** Covariance of return type?

```java
class P { Object m(){ return null; } }
class C extends P { @Override String m(){ return "hi"; } } // OK
```

**A:** Allowed — covariant return types.

**Q:** Narrowing access while overriding?  
**A:** Illegal. Child cannot make method more private (`public` → `protected` fails).

**Q:** Diamond problem with default methods?

```java
interface A { default void hi(){ System.out.println("A"); } }
interface B { default void hi(){ System.out.println("B"); } }
class C implements A,B {
  public void hi(){ A.super.hi(); } // must resolve
}
```

**Q:** `super` vs inheritance of fields?  
**A:** `super.x` accesses parent field when child hides it.

**Q:** Fragile base class problem?  
**A:** Changes in parent unexpectedly break children — reason to prefer composition.

---

## Interview one-liner

> Inheritance is for true IS-A with stable contracts; method dispatch is polymorphic, field access is not.

Next: [Polymorphism tricks](03-dsa-and-interview/oops/polymorphism-tricks.md)
