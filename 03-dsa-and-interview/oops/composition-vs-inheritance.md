# Composition vs Inheritance

**Level:** Strong signal for product interviews

---

## Definitions

| | Inheritance | Composition |
|---|-------------|-------------|
| Relation | IS-A | HAS-A |
| Coupling | Tight | Loose |
| Runtime change | Hard | Easy (swap parts) |
| Reuse | Via hierarchy | Via delegation |

```java
// Bad IS-A stretch
class Stack extends ArrayList<Integer> {} // exposes unwanted List methods

// Better HAS-A
class Stack {
  private final Deque<Integer> data = new ArrayDeque<>();
  void push(int x){ data.push(x); }
  int pop(){ return data.pop(); }
}
```

```java
class Engine { void start(){ System.out.println("vroom"); } }
class Car {
  private final Engine engine = new Engine();
  void start(){ engine.start(); }
}
```

---

## When inheritance is OK

- True IS-A  
- Parent is stable  
- Need runtime polymorphism on that hierarchy  
- Liskov Substitution holds  

---

## Trick / senior questions

**Q:** Prefer composition over inheritance — why?  
**A:** Less coupling, easier testing, avoid fragile base class, reuse without exposing parent API.

**Q:** Liskov violation example?  
**A:** `Square extends Rectangle` where `setWidth` changes height — breaks Rectangle users.

**Q:** Delegation vs inheritance?  
**A:** Delegation = composition calling wrapped object’s methods.

**Q:** Can composition achieve polymorphism?  
**A:** Yes — depend on interface, inject implementations (Strategy).

Next: [SOLID](03-dsa-and-interview/oops/solid.md)
