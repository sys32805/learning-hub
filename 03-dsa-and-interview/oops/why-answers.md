# OOPs / Java — WHY answers (interviewer depth)

Definitions get you past screening. **Why** gets you the offer.

Template:

```text
Choice → Why → What goes wrong otherwise → Trade-off
```

---

## 1. Why encapsulation (private fields + methods)?

**Why:** Protect invariants (balance can’t go negative via direct field write). Change internal representation without breaking callers. Reduce misuse.

**Otherwise:** Any code can corrupt state; refactoring becomes dangerous.

---

## 2. Why abstraction (interface / abstract class)?

**Why:** Callers depend on **what** an API does, not **how**. Lets you swap implementations (UPI/Card) and test with fakes.

**Otherwise:** High coupling to concrete classes; hard to extend (Open/Closed).

---

## 3. Why inheritance — and why not overuse it?

**Why:** Real IS-A + shared behavior/polymorphism.

**Why prefer composition often:** Inheritance is tight coupling to parent; parent changes break children; wrong IS-A creates Liskov bugs (`Square extends Rectangle`).

**Trade-off:** Inheritance is fine for stable frameworks (e.g. small abstract base); composition for behavior you may swap.

---

## 4. Why polymorphism?

**Why:** Write code against a parent/interface and plug in new types without rewriting callers (`Payment.pay()`).

**Otherwise:** Giant `if-else` on type flags — violates OCP, error-prone.

---

## 5. Why override vs overload?

**Override why:** Runtime behavior by actual object type (dynamic dispatch) — core of polymorphism.

**Overload why:** Same intent, different parameters — compile-time convenience.

**Why interviewers ask:** Confusing them means you don’t understand when binding happens (compile vs runtime).

---

## 6. Why method hiding (static) is not overriding?

**Why static belongs to class, not instance.** There’s no runtime polymorphic dispatch for static methods.

**Why it matters:** Calling via parent reference uses parent static method — surprising bugs if you “override” static mentally.

---

## 7. Why `abstract class` vs `interface`?

**Why abstract class:** Shared state + partial implementation + single IS-A hierarchy.

**Why interface:** Capability contract; multiple inheritance of type; API evolution with `default` methods.

**Why Java forbids multiple class inheritance:** Diamond ambiguity for state/implementation.

---

## 8. Why prefer interface + composition in modern design?

**Why:** Multiple behaviors, weaker coupling, easier testing, aligns with DIP/SOLID.

---

## 9. Why `equals` and `hashCode` together?

**Why:** Hash structures (`HashMap`/`HashSet`) use hash buckets then `equals`. Break the contract → lost entries / duplicates.

**Why both?** Equal objects must have equal hashCodes; hashCode alone doesn’t define equality.

---

## 10. Why is `String` immutable?

**Why:**
- Safe sharing / thread-safety  
- String pool reuse  
- Security (URLs, class names, network)  
- Cached `hashCode`  

**Otherwise:** One mutable shared string could corrupt keys in maps and create security holes.

---

## 11. Why `StringBuilder` over `String` concat in loops?

**Why:** Each `String +` may create new objects (immutable). Builder mutates a buffer → far fewer allocations.

**Why `StringBuffer` rarer now:** Thread-safe sync cost; most builders are local to one thread → `StringBuilder`.

---

## 12. Why immutability for value objects (`Money`, `UserId`)?

**Why:** Thread-safe sharing, safer as map keys, fewer side effects, easier reasoning.

**Trade-off:** More objects on updates (copy) — usually fine for small values.

---

## 13. Why composition over inheritance (GoF advice)?

**Why:** HAS-A lets you replace parts at runtime; avoids exposing parent API; reduces fragile base class problem.

**Example why `Stack extends Vector` was bad historically:** Stack users got irrelevant Vector methods.

---

## 14. Why SOLID (especially SRP / OCP / DIP)?

**Why SRP:** One reason to change → fewer merge conflicts, clearer tests.

**Why OCP:** Add behavior via new classes, not editing stable code → fewer regressions.

**Why DIP:** Depend on abstractions → swap DB/mail implementations; test with mocks.

**Why not blindly:** Too many tiny classes can hurt navigation — balance with team taste.

---

## 15. Why checked vs unchecked exceptions?

**Why checked:** Force callers to confront recoverable conditions (I/O).

**Why unchecked:** Programming bugs (NPE, illegal state) — don’t force noisy `throws` everywhere.

**Why Spring often uses unchecked:** Cleaner service APIs; global handlers translate to HTTP.

---

## 16. Why `try-with-resources`?

**Why:** Guaranteed close of `AutoCloseable` even on exceptions; avoids resource leaks; suppresses secondary close exceptions correctly.

---

## 17. Why generics?

**Why:** Compile-time type safety; remove casts; clearer APIs (`List<User>` not raw `List`).

**Why erasure exists:** Backward compatibility with pre-generic JVM bytecode — explains runtime limitations (`new T()`).

---

## 18. Why `Optional` as return type (not fields everywhere)?

**Why:** Makes “maybe missing” explicit at API boundary; reduces silent NPEs.

**Why not for fields/parameters always?** Noisy; not serializable-friendly; misuse as substitute for good domain modeling.

---

## 19. Why streams carefully?

**Why:** Expressive data pipelines for transforms/filters.

**Why not always:** Debugging harder; overkill for simple loops; parallel streams can hurt if misused.

---

## 20. Why multithreading — and why it’s hard?

**Why:** Throughput on multi-core; keep UI/server responsive.

**Why hard:** Race conditions, visibility, deadlocks. Prefer high-level tools (`ExecutorService`, concurrent collections, `@Transactional` boundaries) over hand-rolled sync.

---

## 21. Why `synchronized` / locks?

**Why:** Mutual exclusion for shared mutable state + memory visibility guarantees.

**Why not synchronize everything?** Contention kills performance; better design = less shared mutability.

---

## 22. Why immutability helps concurrency?

**Why:** No writes → no races. Share freely across threads.

---

## 23. Why design patterns (Factory, Strategy, Singleton)?

**Why Factory:** Hide creation complexity; return abstractions.

**Why Strategy:** Swap algorithms without conditionals (OCP).

**Why Singleton (carefully):** One instance for true shared resource — but prefer DI container scope over hand-rolled singleton in Spring apps.

**Why interviewer asks:** To see if you know **when**, not just UML.

---

## 24. Why Liskov Substitution matters?

**Why:** Polymorphism only works if subtypes don’t break parent contracts. Violations cause subtle bugs when code accepts parent type.

---

## 25. Why markers like `Serializable` exist?

**Why historically:** Tag capabilities. Modern Java prefers explicit interfaces with methods; markers still appear in legacy APIs.

---

## Oral drill (say WHY only)

1. Why encapsulation?  
2. Why interface over abstract class (typical case)?  
3. Why composition over inheritance?  
4. Why equals/hashCode contract?  
5. Why String immutable?  
6. Why constructor injection in Spring (link to OOPs)?  
7. Why immutability for concurrency?  
8. Why Strategy pattern?

Next: [Spring WHY answers](05-spring-boot/interview/why-answers.md)
