# Four pillars of OOPs

**Level:** TCS → Product  
**Also see:** [Advanced guide](03-dsa-and-interview/oops/04-advanced-oops-interview.md)

---

## 1. Encapsulation

**Meaning:** Bundle data + methods; hide state; expose controlled behavior.

```java
class BankAccount {
  private double balance; // hidden
  public void deposit(double amt) {
    if (amt > 0) balance += amt;
  }
  public double getBalance() { return balance; }
}
```

### Trick Q

**Q:** Is making fields `public` still encapsulation?  
**A:** Weak encapsulation. Encapsulation exists (class boundary) but **data hiding** is broken. Interviewers want both.

**Q:** Encapsulation vs Abstraction?  
**A:** Encapsulation = protect data. Abstraction = hide complexity / show essentials.

---

## 2. Abstraction

**Meaning:** Show *what*, hide *how* (`interface` / `abstract class`).

```java
interface Payment { boolean pay(double amount); }
class UpiPayment implements Payment {
  public boolean pay(double amount) { /* UPI details hidden */ return true; }
}
```

### Trick Q

**Q:** Can abstraction exist without encapsulation?  
**A:** Yes theoretically (public fields behind an interface), but good design uses both.

---

## 3. Inheritance

**Meaning:** IS-A reuse via `extends` (one class) / `implements` (many interfaces).

```java
class Animal { void eat() {} }
class Dog extends Animal { void bark() {} }
```

### Trick Q

**Q:** Why no multiple class inheritance in Java?  
**A:** Avoid **diamond problem** ambiguity. Multiple interfaces are allowed.

**Q:** Is inheritance always good for reuse?  
**A:** No — prefer **composition** when not a true IS-A (product companies love this).

---

## 4. Polymorphism

**Meaning:** One interface, many behaviors.

- **Compile-time:** overloading  
- **Runtime:** overriding (dynamic dispatch)

```java
Animal a = new Dog();
a.eat(); // Dog version if overridden
```

### Trick Q

**Q:** Can we override `static` methods?  
**A:** No — they are **hidden**, not overridden. Call depends on **reference type**.

**Q:** Can constructors be inherited?  
**A:** No. Subclass calls `super(...)` explicitly or implicitly.

---

## Must-say in interview (30 sec)

> Encapsulation protects state, abstraction simplifies API, inheritance models IS-A carefully, polymorphism enables extensibility without changing callers — that’s Open/Closed in practice.

Next: [Class / Object / Constructor](03-dsa-and-interview/oops/class-object-constructor.md)
