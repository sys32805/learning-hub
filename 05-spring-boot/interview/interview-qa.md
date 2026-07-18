# Spring Boot — interview Q&A bank (detailed answers)

Short enough to speak, long enough to show understanding.  
Deep dives: [core](05-spring-boot/interview/core-basics.md) · [annotations](05-spring-boot/interview/annotations.md) · [IoC](05-spring-boot/interview/ioc-di.md)

---

## Fundamentals

**Q1. What is Spring Boot?**  
Spring Boot is an opinionated layer on top of the Spring Framework. It reduces boilerplate using **starters** (dependency bundles), **auto-configuration** (beans created based on classpath/properties), an **embedded server**, and **Actuator** for production monitoring. It does not replace Spring — it makes Spring apps faster to build and run.

**Q2. What are starters?**  
Starter POMs like `spring-boot-starter-web` pull a curated, version-aligned set of libraries (MVC, Jackson, Tomcat, etc.). You depend on one artifact instead of managing many jars and versions yourself.

**Q3. What is auto-configuration?**  
Boot ships configuration classes that conditionally create beans. Conditions include classpath (`@ConditionalOnClass`), missing user beans (`@ConditionalOnMissingBean`), and properties. Example: JDBC driver present + datasource URL → Boot creates a `DataSource` (HikariCP).

**Q4. What is `@SpringBootApplication`?**  
A meta-annotation combining `@SpringBootConfiguration` (`@Configuration`), `@EnableAutoConfiguration`, and `@ComponentScan`. It marks the main class, enables auto-config, and scans the main package and sub-packages for components.

**Q5. What is an embedded server?**  
The web server (Tomcat by default) runs inside your application process. You start the app with `java -jar app.jar` without deploying a WAR to an external Tomcat — ideal for microservices and containers.

**Q6. JAR vs WAR?**  
Executable JAR (fat/uber jar) packages app + dependencies + embedded server — most common with Boot. WAR is for deploying onto an external application server when the organization requires it.

**Q7. What is Actuator?**  
A production-ready module exposing operational endpoints: health, metrics, info, and more. Use it for Kubernetes probes and monitoring (Micrometer). Always secure sensitive endpoints.

**Q8. How do profiles work?**  
Profiles separate config per environment (`dev`, `test`, `prod`) via `application-dev.yml` etc. Activate with `spring.profiles.active` or environment variables so the same artifact runs differently per env.

**Q9. What does “opinionated” mean?**  
Boot chooses defaults (Tomcat, Hibernate, HikariCP, JSON) that work for most apps. You can override any default, but you are not forced to configure everything from scratch.

**Q10. Boot 2 vs Boot 3 (high level)?**  
Boot 3 requires Java 17+, migrates `javax.*` → `jakarta.*`, and uses newer Security configuration style (`SecurityFilterChain`).

---

## DI / Beans

**Q11. What is IoC?**  
Inversion of Control means the framework controls object creation and lifecycle instead of your code calling `new` everywhere. Spring’s container owns how beans are created and wired.

**Q12. What is DI?**  
Dependency Injection is the technique: collaborators are provided to a class (constructor/setter/field) rather than looked up or constructed internally. This loosens coupling and improves testability.

**Q13. Types of injection? Which is best?**  
Constructor (best default), setter (optional deps), field (avoid in new code). Constructor allows `final` fields, clear required dependencies, and easy unit tests without Spring.

**Q14. Bean scopes?**  
Singleton (default, one per container), prototype (new instance per request from container), plus web scopes: request, session, application.

**Q15. `@Bean` vs `@Component`?**  
`@Component` (and stereotypes) annotate **your** classes for component scanning. `@Bean` methods inside `@Configuration` are factory methods — used especially when you cannot annotate a third-party class or need custom creation logic.

**Q16. `@Qualifier` vs `@Primary`?**  
When multiple beans share a type, `@Primary` marks the default choice; `@Qualifier` selects a specific bean by name/id at the injection point.

**Q17. Circular dependency?**  
A needs B and B needs A. Prefer redesign. Constructor injection fails fast at startup. `@Lazy` is a workaround, not a good design.

**Q18. Bean lifecycle (short version)?**  
Instantiate → inject dependencies → aware callbacks → BeanPostProcessor before → `@PostConstruct`/init → BeanPostProcessor after (proxies) → ready → on shutdown `@PreDestroy`/destroy.

**Q19. Are singleton beans thread-safe?**  
Not automatically. One shared instance means mutable fields need synchronization or, better, keep services stateless.

**Q20. Prototype inside singleton trap?**  
The prototype is created once when the singleton is created, unless you use `ObjectProvider`, `Provider`, or `@Lookup`.

---

## Web / REST

**Q21. `@Controller` vs `@RestController`?**  
`@RestController` = `@Controller` + `@ResponseBody`. Return values are written to the HTTP body (JSON), not resolved as view names.

**Q22. `@PathVariable` vs `@RequestParam`?**  
Path variable is part of the URL path (`/users/5`). Request param is a query string value (`?page=1`).

**Q23. What does `@RequestBody` do?**  
Maps the HTTP request body (usually JSON) to a Java object using `HttpMessageConverter` (Jackson).

**Q24. What is `@ControllerAdvice` / `@RestControllerAdvice`?**  
A global component for exception handling and related cross-cutting web concerns so all APIs return consistent error responses.

**Q25. How do you return custom HTTP status?**  
`ResponseEntity`, `@ResponseStatus` on methods/exceptions, or set status in an `@ExceptionHandler`.

**Q26. How does validation work in REST?**  
Add validation starter, annotate DTO fields (`@NotBlank`, `@Email`), put `@Valid` on `@RequestBody`, handle `MethodArgumentNotValidException` to return 400 with field errors.

**Q27. Why DTOs instead of entities in APIs?**  
Decouple API from DB schema, avoid lazy-loading/json recursion issues, hide sensitive fields, allow API versioning without changing entities.

**Q28. Filter vs Interceptor?**  
Filter is servlet-level (all requests entering the container). HandlerInterceptor is Spring MVC-level around controller execution. Security usually sits in the filter chain.

**Q29. PUT vs PATCH?**  
PUT replaces the full resource representation (idempotent). PATCH applies partial updates.

**Q30. 401 vs 403?**  
401 = authentication required/failed. 403 = identity known but permission denied.

---

## Data / JPA

**Q31. JPA vs Hibernate?**  
JPA is the specification (APIs/annotations). Hibernate is the most common implementation that Boot uses by default with Spring Data JPA.

**Q32. What is Spring Data JPA?**  
It provides repository abstractions (`JpaRepository`), derived query methods, pagination/sorting, and `@Query` support so you write less boilerplate DAO code.

**Q33. What does `@Transactional` do?**  
Defines a transaction boundary. All DB work in that method succeeds or rolls back together. Prefer on service-layer use cases.

**Q34. Lazy vs Eager fetching?**  
Lazy loads associations on access; Eager loads immediately with the parent. Prefer Lazy and fetch explicitly what the use case needs to avoid performance problems.

**Q35. What is the N+1 problem?**  
One query loads parents, then one query per parent loads children — total 1+N queries. Fix with join fetch, `@EntityGraph`, batching, or DTO queries.

**Q36. LazyInitializationException?**  
A lazy association was accessed after the persistence context/session was closed (often outside `@Transactional` or with OSIV disabled).

**Q37. What is OSIV?**  
Open Session In View keeps the Hibernate session open during view/JSON rendering. Convenient but can hide N+1; many production teams disable it.

**Q38. `ddl-auto=update` in production?**  
Risky/not recommended. Use Flyway or Liquibase for versioned schema migrations; use `validate`/`none` in prod.

**Q39. Why HikariCP?**  
Default JDBC pool in Boot — reuses DB connections because creating connections is expensive.

**Q40. `save` vs `saveAndFlush`?**  
`save` may delay SQL until flush/commit. `saveAndFlush` forces synchronization to the database immediately.

---

## Security

**Q41. Authentication vs authorization?**  
Authentication verifies identity. Authorization decides permissions for that identity.

**Q42. How are passwords stored?**  
Hashed with a strong password encoder like BCrypt (salted, adaptive). Never store plain text; never build custom crypto.

**Q43. What is JWT used for?**  
After login, server issues a signed token. Client sends `Authorization: Bearer <token>`. Server validates signature/expiry and builds the SecurityContext — typically with stateless sessions.

**Q44. JWT drawbacks?**  
Revocation before expiry is hard; stolen tokens work until expiry; secret/key management matters. Mitigate with short TTL, refresh tokens, HTTPS, careful storage.

**Q45. What is CSRF?**  
Cross-Site Request Forgery — browser automatically sends cookies to your site from another site’s attack page. Protect cookie-based session apps; understand trade-offs before disabling CSRF for APIs.

**Q46. `hasRole('ADMIN')` means?**  
Checks for authority `ROLE_ADMIN`.

**Q47. What is `SecurityFilterChain`?**  
The modern (Boot 3 / Security 6) way to configure HTTP security rules as a bean, replacing `WebSecurityConfigurerAdapter`.

**Q48. SessionCreationPolicy.STATELESS?**  
Spring Security will not create or use an HTTP session — typical for JWT APIs.

---

## Misc / production

**Q49. How do you externalize configuration?**  
`application.yml`, profile-specific files, environment variables, command-line args, and optionally Spring Cloud Config. Higher-priority sources override lower ones.

**Q50. What is `@Async`?**  
Runs a method in another thread via a proxy. Requires `@EnableAsync`. Self-invocation won’t work. Useful for non-blocking side work (emails, notifications).

**Q51. Scheduling?**  
`@EnableScheduling` + `@Scheduled` (fixedRate/fixedDelay/cron) for periodic jobs inside the app.

**Q52. Important test annotations?**  
`@SpringBootTest` (full context), `@WebMvcTest` (web slice), `@DataJpaTest` (JPA slice), `@MockBean` (replace bean with mock). Prefer slices for speed.

**Q53. How to change server port?**  
`server.port=9090` or env `SERVER_PORT`.

**Q54. What is Micrometer?**  
Metrics facade used with Actuator — export to Prometheus, etc.

**Q55. Spring MVC vs WebFlux?**  
MVC is servlet/thread-per-request (blocking style). WebFlux is reactive/non-blocking for high concurrency I/O. Most business apps still use MVC unless they need reactive stacks.

**Q56. How to read auto-config decisions?**  
Run with `--debug` and inspect the auto-configuration report.

**Q57. What is a fat JAR?**  
A single executable archive containing your classes, dependencies, and embedded server, built by the Spring Boot plugin.

**Q58. Where should business logic live?**  
In `@Service` classes — controllers stay thin (HTTP mapping only).

**Q59. How do you handle global API errors?**  
`@RestControllerAdvice` + `@ExceptionHandler` returning a standard error DTO and proper status codes.

**Q60. What do you secure before production?**  
Secrets out of git, HTTPS, authn/authz, validation, Actuator lockdown, least-privilege DB users, sensible CORS, logging without sensitive data.

---

## Lightning round (service companies)

Say one line each: `@SpringBootApplication`, `@Autowired`, `@RestController`, `@Service`, `@Transactional`, singleton default, GET vs POST, Actuator health.

## Product extras

Say one paragraph each: auto-config conditions, proxy self-invocation, N+1 + OSIV, SecurityFilterChain + JWT trade-offs, DTO vs entity, HikariCP.

Next: [Daily revision](05-spring-boot/interview/daily-revision.md)
