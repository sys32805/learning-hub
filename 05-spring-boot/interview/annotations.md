# Spring Boot annotations (interview bank)

**Level:** Asked in almost every Spring interview  
**Also:** [IoC / DI](05-spring-boot/interview/ioc-di.md) · [REST](05-spring-boot/interview/rest-web.md)

---

## 1. Boot & config

| Annotation | Meaning |
|------------|---------|
| `@SpringBootApplication` | = `@Configuration` + `@EnableAutoConfiguration` + `@ComponentScan` |
| `@Configuration` | Class defines `@Bean` methods |
| `@EnableAutoConfiguration` | Auto-wires beans based on classpath |
| `@ComponentScan` | Scans for `@Component` and stereotypes |
| `@Bean` | Method produces a Spring-managed bean |
| `@PropertySource` | Load extra `.properties` |
| `@ConfigurationProperties` | Bind `application.yml` prefix to a class |
| `@Value("${key}")` | Inject single property |
| `@Profile("dev")` | Bean active only for profile |

```java
@SpringBootApplication
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
```

### Tricks

**Q:** Can we remove `@SpringBootApplication` and use the three separately?  
**A:** Yes — it is a convenience meta-annotation.

**Q:** `@ComponentScan` default package?  
**A:** Package of the annotated class **and below**. Put main class in root package.

**Q:** `@Value` vs `@ConfigurationProperties`?  
**A:** `@Value` = one key. `@ConfigurationProperties` = type-safe group of props (preferred for many keys).

---

## 2. Stereotype (components)

| Annotation | Layer |
|------------|--------|
| `@Component` | Generic Spring bean |
| `@Service` | Business logic (semantic) |
| `@Repository` | DAO; also translates persistence exceptions |
| `@Controller` | MVC controller (returns views) |
| `@RestController` | `@Controller` + `@ResponseBody` (REST JSON/XML) |

### Tricks

**Q:** Difference `@Service` vs `@Component`?  
**A:** Functionally same for DI; `@Service` is clearer + future tooling. `@Repository` adds exception translation.

**Q:** Is `@RestController` enough for JSON?  
**A:** Yes for return values — message converters (Jackson) serialize. Need starter-web on classpath.

---

## 3. Dependency injection

| Annotation | Use |
|------------|-----|
| `@Autowired` | Inject by type (field/ctor/setter) |
| `@Qualifier("beanName")` | Disambiguate multiple beans of same type |
| `@Primary` | Prefer this bean when many match |
| `@Inject` | JSR-330 alternative to `@Autowired` |
| `@Resource(name="x")` | JSR-250; by name first |
| `@Lazy` | Delay bean creation |
| `@Scope("prototype")` | New instance each get (default singleton) |

```java
@Service
public class OrderService {
  private final OrderRepository repo;
  // preferred: constructor injection (no @Autowired needed if one ctor)
  public OrderService(OrderRepository repo) {
    this.repo = repo;
  }
}
```

### Tricks

**Q:** Field vs constructor injection?  
**A:** Prefer **constructor** — immutability, easier tests, required deps clear.

**Q:** Required `@Autowired` on single constructor (Spring 4.3+)?  
**A:** No — optional.

**Q:** Circular dependency?  
**A:** Redesign; or `@Lazy` on one side (hack). Constructor cycles fail fast (good).

---

## 4. Web / REST

| Annotation | Use |
|------------|-----|
| `@RequestMapping` | Map URL + method (general) |
| `@GetMapping` / `@PostMapping` / `@PutMapping` / `@DeleteMapping` / `@PatchMapping` | Shortcuts |
| `@PathVariable` | `/users/{id}` |
| `@RequestParam` | `?page=1` |
| `@RequestBody` | JSON → object |
| `@ResponseBody` | Object → HTTP body |
| `@ResponseStatus` | Set HTTP status |
| `@CrossOrigin` | CORS |
| `@Valid` / `@Validated` | Bean validation |
| `@ExceptionHandler` | Handle exception in controller |
| `@ControllerAdvice` | Global exception / binding advice |
| `@RestControllerAdvice` | Advice for REST |

```java
@RestController
@RequestMapping("/api/users")
public class UserController {
  @GetMapping("/{id}")
  public User get(@PathVariable Long id) { ... }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public User create(@Valid @RequestBody UserRequest req) { ... }
}
```

### Tricks

**Q:** `@PathVariable` vs `@RequestParam`?  
**A:** Path segment vs query string.

**Q:** `@RequestBody` on GET?  
**A:** Unusual / discouraged — body on GET not reliable.

**Q:** Why `@ControllerAdvice`?  
**A:** Centralize error responses → consistent API errors.

---

## 5. JPA / data

| Annotation | Use |
|------------|-----|
| `@Entity` | JPA entity |
| `@Table(name="...")` | Table name |
| `@Id` / `@GeneratedValue` | Primary key |
| `@Column` | Column mapping |
| `@OneToMany` / `@ManyToOne` / `@ManyToMany` / `@OneToOne` | Relations |
| `@JoinColumn` | FK column |
| `@Transactional` | Transaction boundary (usually service) |
| `@Query` | Custom JPQL/native |
| `@Modifying` | Update/delete queries |
| `@EntityScan` / `@EnableJpaRepositories` | Custom package scan |

### Tricks

**Q:** Where put `@Transactional`?  
**A:** Prefer **service** layer, not repository (default repo methods already transactional).

**Q:** Self-invocation and `@Transactional`?  
**A:** Proxy doesn’t apply if you call `this.method()` inside same class — transaction may not start.

**Q:** `FetchType.LAZY` N+1?  
**A:** Classic issue — use join fetch / `@EntityGraph` / DTO query.

---

## 6. Validation

| Annotation | Use |
|------------|-----|
| `@NotNull` / `@NotBlank` / `@NotEmpty` | Null/blank checks |
| `@Size` / `@Min` / `@Max` / `@Email` | Constraints |
| `@Valid` | Trigger validation on nested object |

---

## 7. Scheduling / async / cache / AOP

| Annotation | Use |
|------------|-----|
| `@EnableScheduling` + `@Scheduled` | Cron / fixed rate |
| `@EnableAsync` + `@Async` | Run method async |
| `@EnableCaching` + `@Cacheable` / `@CacheEvict` / `@CachePut` | Caching |
| `@Aspect` / `@Before` / `@Around` / `@After` | AOP |
| `@EnableAspectJAutoProxy` | Enable AspectJ proxies |

---

## 8. Security (common)

| Annotation | Use |
|------------|-----|
| `@EnableWebSecurity` | Security config |
| `@PreAuthorize` / `@PostAuthorize` | Method security |
| `@Secured` / `@RolesAllowed` | Role checks |

---

## 9. Test

| Annotation | Use |
|------------|-----|
| `@SpringBootTest` | Full context test |
| `@WebMvcTest` | Slice: controllers only |
| `@DataJpaTest` | Slice: JPA |
| `@MockBean` | Replace bean with mock |
| `@JsonTest` | Jackson slice |

### Tricks

**Q:** `@SpringBootTest` vs `@WebMvcTest`?  
**A:** Full app vs fast controller slice — prefer slices when possible.

---

## Quick “say in interview” list (top 20)

`@SpringBootApplication`, `@RestController`, `@Service`, `@Repository`, `@Autowired`, `@Qualifier`, `@Bean`, `@Configuration`, `@GetMapping`, `@PostMapping`, `@PathVariable`, `@RequestParam`, `@RequestBody`, `@Valid`, `@Transactional`, `@Entity`, `@Id`, `@ControllerAdvice`, `@ConfigurationProperties`, `@Profile`

Next: [IoC / DI](05-spring-boot/interview/ioc-di.md)
