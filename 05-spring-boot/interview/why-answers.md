# Spring Boot — WHY answers (interviewer depth)

Good interviewers don’t stop at “What is X?”  
They ask: **Why do we do this? Why not the alternative? What breaks if we skip it?**

Practice this template every time:

```text
1. What I choose
2. Why (benefit / risk avoided)
3. Trade-off / when I would choose otherwise
```

---

## 1. Why Spring Boot (not plain Spring)?

**What:** Boot = Spring + starters + auto-config + embedded server.

**Why:**
- Faster delivery — less XML/Java config for common cases  
- Same patterns across teams (conventions)  
- Easy packaging (`java -jar`) for Docker/K8s  

**Why not only Boot magic?**  
You still need Spring concepts (DI, AOP, MVC). Boot hides wiring; it doesn’t remove the need to understand it.

**Follow-up:** “How do you debug auto-config?” → `--debug` report, `@ConditionalOnMissingBean`, exclude auto-config.

---

## 2. Why `@SpringBootApplication` on the root package?

**Why:** `@ComponentScan` starts from that class’s package **downward**. Put main class high enough so services/controllers are found.

**What if wrong?** `NoSuchBeanDefinitionException` — beans exist as classes but aren’t registered.

**Trade-off:** Huge monorepos may use explicit `scanBasePackages` instead of one mega root.

---

## 3. Why constructor injection (not field `@Autowired`)?

**Why:**
- Dependencies are **required** and obvious  
- Fields can be `final` → safer, clearer design  
- Easy unit test: `new Service(mockA, mockB)` without Spring  
- Circular deps fail **early** at startup (good)

**Why field injection is disliked:**
- Hidden deps  
- Harder tests  
- Reflection mutates private fields  
- Encourages “God” classes  

**When setter is OK:** truly optional collaborator.

**Follow-up:** “What if 10 constructor args?” → class has too many responsibilities (SRP); split services.

---

## 4. Why IoC / DI at all?

**Why:**
- Swap implementations (UPI vs Card) without editing callers  
- Test with mocks  
- Centralize lifecycle (singleton, destroy hooks)  

**What if `new` everywhere?** Tight coupling, hard tests, duplicate config, inconsistent lifecycle.

---

## 5. Why singleton default scope?

**Why:** Creating objects is cheap, but creating **heavy** graphs repeatedly is wasteful. One shared service instance is enough if **stateless**.

**Why not always prototype?** Memory + GC pressure; usually unnecessary.

**Critical why:** Singleton ≠ thread-safe. We keep services **stateless** so many threads can share one bean safely.

---

## 6. Why `@Service` / `@Repository` stereotypes (not only `@Component`)?

**Why:**
- Documents layer intent for humans and tools  
- `@Repository` enables persistence exception translation  
- Clear architecture in code reviews  

**Technically:** For DI registration, `@Service` ≈ `@Component`. The **why** is clarity + layering, not magic.

---

## 7. Why `@RestController` instead of `@Controller` for APIs?

**Why:** REST APIs return **data** (JSON), not HTML view names. `@RestController` adds `@ResponseBody` so return values are serialized to the body.

**What if you use `@Controller` by mistake?** Spring treats `String` returns as **view names** → 404 template errors.

---

## 8. Why DTOs instead of returning entities?

**Why:**
- Hide internal fields / relations  
- Avoid Jackson ↔ Hibernate lazy-load / infinite recursion  
- Version API without changing DB schema  
- Validate input shapes separately from persistence model  

**Trade-off:** Extra mapping code. Worth it for any serious API.

**Follow-up:** “How do you map?” → manual, MapStruct, etc.

---

## 9. Why put business logic in `@Service`, not controller?

**Why:**
- Controllers should only translate HTTP ↔ application calls  
- Same logic reusable from events/schedulers/CLI later  
- Transactions belong with use-cases (service), not HTTP layer  
- Easier to unit test without MockMvc  

**What if logic in controller?** Fat controllers, duplicated rules, hard testing, TX boundaries wrong.

---

## 10. Why `@Transactional` on service methods?

**Why:** A use-case often touches **multiple** DB operations that must succeed/fail together (atomicity).

**Why not only on repository?**  
Single repo method is already transactional, but `save` + `save` + external consistency needs **one** business transaction.

**Why rollback defaults to unchecked only?**  
Historical Spring design: checked exceptions often mean “handled recoverable cases.” You override with `rollbackFor` when needed.

**Why self-invocation fails?**  
TX is applied by a **proxy**. `this.method()` bypasses proxy → no TX. Why proxies? Non-invasive AOP without forcing you to write TX code by hand.

---

## 11. Why LAZY fetching by default (associations)?

**Why:** Don’t load the whole object graph if the use-case only needs parent fields — saves memory and SQL.

**What if EAGER everywhere?** Slow queries, huge joins, accidental loading.

**What you must do instead:** Explicitly fetch what you need (`join fetch`, entity graph) in that use-case.

---

## 12. Why is N+1 bad — and why join fetch?

**Why N+1 hurts:** Latency multiplies with data size; DB round-trips dominate.

**Why join fetch:** Load parent+children in fewer queries when you **know** you need children.

**Why not always join fetch?** Over-fetching; cartesian products on multiple bags — choose per use-case / DTO query.

---

## 13. Why disable OSIV in many production apps?

**Why OSIV exists:** Keep session open so lazy loads work during view/JSON rendering.

**Why disable:**
- Hides N+1 until production load  
- DB work happens outside clear service boundaries  
- Encourages lazy access in wrong layers  

**Trade-off:** You must fetch required data in the service transaction deliberately — which is the better design.

---

## 14. Why Flyway/Liquibase instead of `ddl-auto=update`?

**Why:**
- Schema changes are **versioned, reviewable, repeatable**  
- Prod-safe; no surprise ALTER from Hibernate  
- Same migration path across environments  

**Why `update` is dangerous in prod:** Non-deterministic diffs, hard rollbacks, silent destructive changes.

---

## 15. Why HikariCP connection pool?

**Why:** Opening a DB connection is expensive (network + auth). Pool reuses connections → lower latency, controlled DB load.

**What if no pool?** Under traffic, connection storms → DB exhaustion / timeouts.

---

## 16. Why `@RestControllerAdvice` for errors?

**Why:** Consistent error JSON + status codes for all controllers; clients can rely on a contract; no duplicated try/catch.

**What if each controller catches?** Inconsistent responses, leaked stack traces, missed cases.

---

## 17. Why validation on DTO with `@Valid`?

**Why:** Reject bad input **before** business logic / DB. Fail fast with 400 and field messages.

**Why not only DB constraints?** DB errors are harder to map to friendly API errors; some rules aren’t DB-level.

---

## 18. Why JWT for APIs (and why not always)?

**Why JWT:**
- Stateless — easy horizontal scale  
- Natural for mobile/SPA calling APIs  

**Why not always:**
- Hard to revoke before expiry  
- Stolen token works until TTL  
- Large tokens / secret management  

**When sessions win:** Server-rendered apps, instant revoke, simpler CSRF model with cookies carefully.

**Interview gold:** Always state the **trade-off**, not “JWT is best.”

---

## 19. Why BCrypt (not plain or SHA-256 alone)?

**Why:** Password hashing must be **slow + salted** to resist brute force. BCrypt is adaptive. SHA-256 is fast — good for checksums, **bad** alone for passwords.

---

## 20. Why `STATELESS` session policy with JWT?

**Why:** Avoid creating HTTP sessions you don’t use; each request authenticates via token. Clearer scaling story.

---

## 21. Why method security (`@PreAuthorize`) in addition to URL rules?

**Why:** URL rules protect HTTP entry points; method security protects **service use-cases** even if called from another path later (scheduler, event). Defense in depth.

---

## 22. Why profiles (`dev` / `prod`)?

**Why:** Same artifact, different config (DB URL, logging, ddl). Prevents “works on my machine” and accidental prod settings in local.

---

## 23. Why Actuator — and why lock it down?

**Why:** Ops needs health/metrics without SSH into the box (K8s probes, monitoring).

**Why lock down:** Endpoints like `env` / `heapdump` leak secrets and internals.

---

## 24. Why interfaces for repositories / services sometimes?

**Why:** Easier testing (mock interface), multiple implementations, aligns with DIP. Spring Data already uses interfaces for repos.

**Trade-off:** Don’t create interfaces blindly for every class — use when you need abstraction.

---

## 25. Why tests (`@WebMvcTest` / service unit tests)?

**Why:** Catch regressions early; document behavior; refactor safely. Slices are faster than full `@SpringBootTest` for most feedback loops.

**Why not only manual Postman?** Doesn’t run in CI; doesn’t scale with team.

---

## Drill: answer only the WHY (60 seconds each)

1. Why constructor injection?  
2. Why DTO?  
3. Why `@Transactional` on service?  
4. Why LAZY + explicit fetch?  
5. Why not `ddl-auto=update` in prod?  
6. Why JWT trade-offs?  
7. Why BCrypt?  
8. Why global exception handler?  

If you only remember definitions, you sound junior.  
If you explain **why + trade-off**, you sound mid/senior.

Next: [OOPs WHY answers](03-dsa-and-interview/oops/why-answers.md)
