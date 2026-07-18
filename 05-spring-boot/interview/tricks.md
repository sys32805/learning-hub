# Spring Boot — trick questions

**Level:** Product filter + strong service rounds  
Use after: [core](05-spring-boot/interview/core-basics.md) · [annotations](05-spring-boot/interview/annotations.md)

---

## Auto-config & beans

**Q:** I defined my own `DataSource` bean — will Boot still create one?  
**A:** Usually no — `@ConditionalOnMissingBean` skips auto-config DataSource.

**Q:** Two `@RestController` same mapping — what happens?  
**A:** Startup fails (ambiguous mapping) or conflict — app won’t run cleanly.

**Q:** `@ComponentScan` on a subpackage only — beans in parent package?  
**A:** Not scanned unless included.

---

## Injection traps

**Q:** Why is field injection disliked?  
**A:** Hard to test, hidden deps, immutability lost, may hide circular deps.

**Q:** `@Autowired` on `final` field?  
**A:** Must use constructor injection — field injection can’t set final.

**Q:** Prototype inside singleton?  
**A:** Injected once — stale prototype. Use `ObjectProvider<T>` / `@Lookup`.

---

## Transactions & proxies

**Q:** Calling `@Transactional` method from same class?  
**A:** Proxy bypassed → no new transaction (self-invocation).

**Q:** Checked exception inside `@Transactional` — rollback?  
**A:** Default **no** — only unchecked. Use `rollbackFor = Exception.class`.

**Q:** `@Transactional` on controller?  
**A:** Works but discouraged — keep at service; controllers stay thin.

---

## JPA traps

**Q:** LazyInitializationException?  
**A:** Session closed; accessing LAZY outside transaction/session.

**Q:** Equals/hashCode on entity with ID?  
**A:** Careful — ID null before persist; prefer business key or careful patterns.

**Q:** `Optional` from repository — orElseThrow?  
**A:** Prefer explicit 404 mapping vs returning null.

---

## REST traps

**Q:** Returning entity with bidirectional relation to JSON?  
**A:** Infinite recursion — use `@JsonManagedReference`/`@JsonIgnore` or DTOs.

**Q:** PUT vs PATCH?  
**A:** PUT replace (idempotent full); PATCH partial update.

---

## Security / prod

**Q:** Default security password?  
**A:** Boot generates password in logs if security on and no user configured — never for prod.

**Q:** Actuator endpoints exposed?  
**A:** Lock down; don’t expose `env`/`heapdump` publicly.

**Q:** Secrets in `application.yml` in Git?  
**A:** No — env vars, vault, K8s secrets, encrypted config.

---

## Performance / design

**Q:** Why connection pool (HikariCP)?  
**A:** Creating DB connections is expensive — reuse.

**Q:** `@Async` without `@EnableAsync`?  
**A:** Runs synchronously — annotation ignored.

**Q:** How Boot metrics?  
**A:** Actuator + Micrometer (Prometheus, etc.).

---

## One-minute drill (answer all)

1. What does `@SpringBootApplication` contain?  
2. Constructor vs field injection?  
3. Singleton vs prototype?  
4. `@Controller` vs `@RestController`?  
5. JPA vs Hibernate?  
6. N+1 and one fix?  
7. Authn vs Authz?  
8. Why DTO over entity in API?  

Next: [Full Q&A bank](05-spring-boot/interview/interview-qa.md)
