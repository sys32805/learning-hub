# Spring Boot — trick questions (detailed answers)

Use after reading the topic pages. Each answer is full enough to say in an interview.

---

## A. Auto-configuration & startup

**Q1. I added `starter-data-jpa` but app fails saying DataSource URL missing. Why?**  
Boot found JDBC/JPA on classpath and tried to auto-configure a DataSource. Without `spring.datasource.url` (or an embedded DB like H2), it fails. Either add URL credentials or exclude `DataSourceAutoConfiguration` if you don’t need DB yet.

**Q2. I defined my own `DataSource` `@Bean`. Will Boot create another?**  
Usually no — auto-config uses `@ConditionalOnMissingBean(DataSource.class)` and backs off.

**Q3. How do you debug why a bean was / wasn’t created?**  
Start with `--debug` or `debug=true` and read the auto-configuration report (positive/negative matches).

**Q4. Component scan doesn’t see my `@Service`. Checklist?**  
1) Stereotype present  
2) Package under main class package (or listed in `scanBasePackages`)  
3) Not excluded by filter  
4) Correct `@Profile` active  
5) Class is concrete (not abstract without setup)

---

## B. DI & proxies

**Q5. Why is field injection disliked in product companies?**  
Hidden dependencies, harder unit tests, can’t use `final`, encourages huge classes, can mask design smells.

**Q6. Prototype bean injected into singleton — what happens?**  
Prototype is created once at singleton creation time (unless `ObjectProvider` / `@Lookup`).

**Q7. Why doesn’t `@Transactional` work when called from another method in the same class?**  
Spring applies transactions via a **proxy**. `this.other()` bypasses the proxy, so no transactional advice runs.

**Q8. Circular dependency with constructor injection — result?**  
Startup failure. That’s good — forces redesign. Field injection may “solve” it silently and hide a design bug.

**Q9. Are Spring singletons thread-safe?**  
Container creates one instance. Thread-safety of mutable fields is your job. Prefer stateless services.

---

## C. Web / REST

**Q10. Returning JPA entity from controller — what goes wrong?**  
Lazy-load exceptions, infinite JSON recursion on bidirectional links, accidental exposure of internal fields, tight API↔DB coupling. Use DTOs.

**Q11. `@RequestParam` missing — status code?**  
400 Bad Request (if required).

**Q12. Difference 401 and 403?**  
401: not authenticated. 403: authenticated but not authorized.

**Q13. Why prefer `ResponseEntity` sometimes?**  
Full control of status + headers + body (e.g. 201 + Location).

**Q14. Is `@RestController` enough to produce JSON?**  
Yes if Jackson is on classpath (via `starter-web`) and return type is serializable. Still need correct Accept/Content-Type in edge cases.

---

## D. JPA / transactions

**Q15. Explain N+1 with an example.**  
One query loads 50 users; then accessing `user.getOrders()` triggers 50 more queries. Fix with join fetch / entity graph / DTO query.

**Q16. LazyInitializationException — meaning?**  
Code accessed a lazy association after the persistence context/session closed.

**Q17. Does `@Transactional` rollback on checked exceptions?**  
Not by default. Use `rollbackFor = Exception.class`.

**Q18. Where should `@Transactional` live?**  
Service layer for use-cases. Repo methods already transactional for single operations, but multi-step business needs service TX.

**Q19. OSIV — what and why disable?**  
Open Session In View keeps session open during view rendering. Convenient but hides N+1; many teams set `spring.jpa.open-in-view=false`.

**Q20. `ddl-auto=update` in production?**  
No. Use Flyway/Liquibase migrations; prefer `validate` or `none`.

---

## E. Security

**Q21. JWT vs session cookies — when?**  
JWT: stateless APIs, mobile/SPA, horizontal scale. Session: traditional server-rendered apps, easier server-side revoke.

**Q22. Why BCrypt?**  
Salted, adaptive slow hash — resists brute force.

**Q23. `hasRole('ADMIN')` looks for what authority?**  
`ROLE_ADMIN`.

**Q24. CSRF with stateless JWT in header?**  
Lower CSRF risk than cookie sessions because browsers don’t auto-send custom Authorization headers on cross-site requests the same way. Cookie-based auth still needs CSRF protection.

**Q25. Default security password in logs?**  
Generated only for quick demos when no user configured — never production.

---

## F. Async / cache / schedule

**Q26. `@Async` method runs on same thread — why?**  
Missing `@EnableAsync`, or self-invocation bypassed proxy, or calling non-public method.

**Q27. `@Cacheable` not caching?**  
Missing `@EnableCaching`, wrong key, or method called via `this`.

---

## G. One-minute oral drill

Answer each in ≤20 seconds:

1. What is `@SpringBootApplication`?  
2. Constructor vs field injection?  
3. Singleton vs prototype?  
4. Auto-configuration in one sentence?  
5. `@Controller` vs `@RestController`?  
6. JPA vs Hibernate?  
7. N+1 fix?  
8. Authn vs Authz?  
9. Why DTO?  
10. Self-invocation problem?

Next: [Full Q&A bank (expanded)](05-spring-boot/interview/interview-qa.md)
