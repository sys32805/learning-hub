# Class, Object, Constructor

**Level:** TCS → Mid product

---

## Core

| Term | Meaning |
|------|---------|
| Class | Blueprint |
| Object | Instance in heap |
| Constructor | Initializes object; name = class; no return type |
| `new` | Allocates memory + calls constructor |

```java
class Student {
  String name;
  Student() { this.name = "unknown"; }           // default
  Student(String name) { this.name = name; }     // parameterized
  Student(Student s) { this.name = s.name; }     // copy-style
}
```

---

## Constructor rules

1. If you write **any** constructor, Java does **not** add default no-arg.  
2. First line of subclass constructor: `super(...)` or `this(...)`.  
3. Constructor can be `private` (Singleton pattern).

---

## Trick questions

**Q:** Difference between constructor and method?  
**A:** Constructor: no return type, same name as class, called on creation. Method: has return type, any name, called explicitly.

**Q:** Can constructor be `final` / `static` / `abstract`?  
**A:** No.

**Q:** Can we have return type in constructor?  
**A:** If you write `void Student(){}` it becomes a **method**, not constructor — classic trick.

**Q:** Why constructor cannot be inherited?  
**A:** Constructors initialize a specific class. Subclass needs its own; uses `super` to init parent part.

**Q:** Object created without `new`?  
**A:** Possible via deserialization, cloning, reflection, `String` literals (special) — product interview follow-up.

**Q:** `this()` vs `super()` in constructor?  
**A:** Both must be first statement — **cannot use both** in same constructor.

---

## Code trap

```java
class A {
  void A() { System.out.println("not constructor"); } // METHOD
  A() { System.out.println("constructor"); }
}
```

Next: [Inheritance tricks](03-dsa-and-interview/oops/inheritance-tricks.md)
