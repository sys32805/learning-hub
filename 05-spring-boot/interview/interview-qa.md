# Spring Boot — interview Q&A bank

Short answers you can say in interviews. Deeper pages: [home](05-spring-boot/interview/README.md)

---

## Fundamentals

**Q1. What is Spring Boot?**  
Opinionated framework on top of Spring that auto-configures apps, uses starters, and runs with an embedded server for fast production-ready services.

**Q2. What are Spring Boot starters?**  
Dependency descriptors (e.g. `starter-web`) that pull a curated set of libraries so you don’t manage versions manually.

**Q3. What is auto-configuration?**  
Boot configures beans automatically based on classpath, existing beans, and properties, using conditional annotations.

**Q4. What is `@SpringBootApplication`?**  
Meta-annotation combining `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.

**Q5. Embedded server?**  
Server shipped inside the app (Tomcat default). Run with `java -jar` — no external Tomcat required.

**Q6. JAR vs WAR?**  
JAR = executable with embedded server (common). WAR = deploy to external servlet container.

**Q7. Spring Boot Actuator?**  
Library for production endpoints: health, metrics, info, readiness/liveness (with config).

**Q8. How do profiles work?**  
Environment-specific config (`dev`, `prod`). Activate via `spring.profiles.active` or env var.

---

## DI / Beans

**Q9. What is IoC?**  
Control of object creation/wiring is inverted to the container (ApplicationContext).

**Q10. What is DI?**  
Dependencies are injected into objects rather than created inside them.

**Q11. Types of injection?**  
Constructor (preferred), setter, field.

**Q12. Bean scopes?**  
Singleton (default), prototype, request, session, application.

**Q13. `@Bean` vs `@Component`?**  
`@Component` for your classes; `@Bean` in `@Configuration` for third-party / factory methods.

**Q14. `@Qualifier` vs `@Primary`?**  
Qualifier picks by name; Primary sets default when multiple candidates exist.

**Q15. Circular dependency?**  
A needs B and B needs A — redesign; sometimes `@Lazy` (avoid relying on it).

---

## Web / REST

**Q16. `@Controller` vs `@RestController`?**  
RestController adds `@ResponseBody` — returns data (JSON), not view names.

**Q17. `@PathVariable` vs `@RequestParam`?**  
Path variable in URI path; request param in query string.

**Q18. `@RequestBody`?**  
Maps HTTP body (JSON) to Java object via HttpMessageConverter.

**Q19. `@ControllerAdvice`?**  
Global handler for exceptions, binding, model attributes across controllers.

**Q20. How return custom status?**  
`ResponseEntity`, `@ResponseStatus`, or exception handler with status.

**Q21. Validation in REST?**  
`spring-boot-starter-validation` + `@Valid` + annotations like `@NotBlank`.

---

## Data / JPA

**Q22. Spring Data JPA?**  
Abstraction over JPA — repository interfaces, derived queries, `@Query`.

**Q23. JpaRepository methods?**  
`save`, `findById`, `findAll`, `delete`, pagination/sorting support.

**Q24. `@Transactional` purpose?**  
Defines transaction boundary — commit/rollback as a unit of work.

**Q25. Lazy vs Eager?**  
Lazy loads on access; Eager loads immediately. Prefer Lazy + careful fetching.

**Q26. N+1 problem?**  
Extra queries per child collection — fix with join fetch / entity graphs / DTO queries.

**Q27. `ddl-auto=update` in production?**  
Risky — use migrations (Flyway/Liquibase).

---

## Security

**Q28. Authentication vs authorization?**  
Who you are vs what you’re allowed to do.

**Q29. Password storage?**  
BCrypt (or stronger) hashed passwords — never plain text.

**Q30. JWT idea?**  
Signed token with claims; client sends Bearer token; server validates without session (stateless).

**Q31. CSRF?**  
Attack using user’s browser session cookie; protect browser apps; often disabled for pure token APIs with care.

---

## Misc / product

**Q32. How externalize config?**  
`application.yml`, env vars, command-line args, config server (Cloud).

**Q33. `@Async`?**  
Run method in another thread — need `@EnableAsync` and return `CompletableFuture` sometimes.

**Q34. Scheduling?**  
`@EnableScheduling` + `@Scheduled(cron=...)`.

**Q35. Testing annotations?**  
`@SpringBootTest`, `@WebMvcTest`, `@DataJpaTest`, `@MockBean`.

**Q36. How Boot knows Tomcat?**  
`starter-web` brings embedded Tomcat; auto-config starts it.

**Q37. Override port?**  
`server.port=9090` or env `SERVER_PORT`.

**Q38. What is Micrometer?**  
Metrics facade used with Actuator for Prometheus/etc.

**Q39. Difference Spring MVC vs WebFlux?**  
MVC = servlet/blocking. WebFlux = reactive/non-blocking.

**Q40. How do you debug a failed auto-config?**  
Enable `debug=true` or `--debug` — Boot prints auto-config report (positive/negative matches).

---

## Service-company lightning round

- Annotations list (top 15)  
- `@Autowired` explanation  
- Singleton default  
- GET vs POST  
- Checked vs why `@Transactional` rollback defaults  

## Product-company extras

- Proxy self-invocation  
- OSIV  
- DTO vs entity  
- HikariCP  
- SecurityFilterChain  
- Idempotency & status codes  

Next: [Daily revision](05-spring-boot/interview/daily-revision.md)
