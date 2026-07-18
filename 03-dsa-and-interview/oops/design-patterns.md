# Design patterns (OOPs interview set)

**Level:** Product / mid-senior (know 6–8 well)

---

## Creational

### Singleton
One instance globally.

```java
enum Db { INSTANCE;
  public void query(){}
}
```

**Tricks:** Double-checked locking needs `volatile`; enum safest; reflection/`clone` can break lazy singletons.

### Factory / Abstract Factory
Hide creation logic — return interface type.

### Builder
For many constructor params (Lombok `@Builder` / custom).

---

## Structural

### Adapter
Wrap incompatible API to expected interface.

### Decorator
Add behavior without subclass explosion (`InputStream` wrappers).

### Facade
Simple entry over complex subsystem.

### Proxy
Control access (lazy load, security, Spring AOP proxies).

---

## Behavioral

### Strategy
Swap algorithm at runtime (payment methods).

### Observer
Publish-subscribe (listeners, event bus).

### Template Method
Skeleton in parent; steps in child.

### Command
Encapsulate request as object (undo queues).

---

## Map to SOLID

| Pattern | SOLID link |
|---------|------------|
| Strategy | OCP + DIP |
| Decorator | OCP |
| Factory | DIP |
| Facade | SRP for clients |

---

## Trick questions

**Q:** Singleton vs static class?  
**A:** Singleton is an object (can implement interface, lazy init). Static utility has no instance state.

**Q:** Spring bean scope vs Singleton pattern?  
**A:** Container-managed single instance ≠ classic Singleton class; usually prefer DI over hand-rolled Singleton.

**Q:** Decorator vs Inheritance?  
**A:** Decorator composes wrappers; more flexible at runtime.

**Q:** Which pattern in `Collections.unmodifiableList`?  
**A:** Often discussed as Decorator / wrapper.

**Q:** MVC is a pattern?  
**A:** Architectural — know briefly for web interviews.

Next: [Daily revision](03-dsa-and-interview/oops/daily-revision.md)
