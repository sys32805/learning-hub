# SOLID principles (Java interviews)

**Level:** Product / senior-junior filter

---

## S — Single Responsibility

One class = one reason to change.

```java
// Bad: OrderService saves DB + sends email + prints PDF
// Good:
class OrderRepository { void save(Order o){} }
class OrderMailer { void send(Order o){} }
```

**Trick:** “One method only?” → No — one **responsibility / reason to change**.

---

## O — Open/Closed

Open for extension, closed for modification.

```java
interface Discount { double apply(double price); }
class FestiveDiscount implements Discount {
  public double apply(double p){ return p*0.9; }
}
```

Add new discount without editing old classes.

---

## L — Liskov Substitution

Subclass must replace parent without breaking callers.

**Violation:** Child throws unexpected exception or weakens parent contract.

---

## I — Interface Segregation

Many small interfaces > one fat interface.

```java
interface Workable { void work(); }
interface Eatable { void eat(); }
// Robot implements Workable only
```

---

## D — Dependency Inversion

Depend on abstractions; inject implementations.

```java
class OrderService {
  private final OrderRepository repo;
  OrderService(OrderRepository repo){ this.repo = repo; }
}
```

---

## Trick questions

**Q:** SOLID vs OOPs pillars?  
**A:** Pillars = language concepts. SOLID = design rules using those concepts.

**Q:** Which SOLID does Strategy pattern map to?  
**A:** Mostly **O** and **D**.

**Q:** Can SRP conflict with performance?  
**A:** Sometimes more classes — still prefer clarity unless proven hotspot.

**Q:** Real example from Spring?  
**A:** Controllers depend on service interfaces; repos abstracted — DIP + SRP.

Next: [String interview](03-dsa-and-interview/oops/string-interview.md)
