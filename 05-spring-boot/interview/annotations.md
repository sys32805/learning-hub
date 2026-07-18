# Spring Boot annotations — detailed interview guide

**Level:** Every company asks this  
**Related:** [Core](05-spring-boot/interview/core-basics.md) · [IoC](05-spring-boot/interview/ioc-di.md) · [REST](05-spring-boot/interview/rest-web.md)

Don’t only memorize names — explain **what problem it solves** and **where you put it**.

---

## 1. `@SpringBootApplication` (start here)

```java
@SpringBootApplication
public class OrderApplication {
  public static void main(String[] args) {
    SpringApplication.run(OrderApplication.class, args);
  }
}
```

It is a **meta-annotation** composed of:

| Part | Job |
|------|-----|
| `@SpringBootConfiguration` (`@Configuration`) | Marks class as a source of bean definitions |
| `@EnableAutoConfiguration` | Turn on Boot auto-config |
| `@ComponentScan` | Discover `@Component` / `@Service` / … |

**You can write the three separately** — same effect. Boot gives one annotation for convenience.

**Attributes you may see:**

```java
@SpringBootApplication(
  scanBasePackages = "com.company",
  exclude = { SecurityAutoConfiguration.class }
)
```

---

## 2. Stereotype annotations (create your beans)

These tell Spring: “manage this class as a bean.”

### `@Component`

Generic bean. Use when the class is not clearly service/repo/controller.

```java
@Component
public class TimeProvider {
  public Instant now() { return Instant.now(); }
}
```

### `@Service`

**Business logic** layer. Same technical effect as `@Component` for registration, but:

- Documents intent  
- AOP/tooling sometimes treat it specially  
- Interview answer: “semantic stereotype for service layer”

```java
@Service
public class PricingService {
  public BigDecimal discount(BigDecimal price) {
    return price.multiply(new BigDecimal("0.90"));
  }
}
```

### `@Repository`

Persistence access. Extra benefit: Spring can **translate** low-level DB exceptions into `DataAccessException` hierarchy (useful with JDBC templates; with Spring Data JPA the interface is often enough).

```java
@Repository
public class OrderDao {
  // custom JDBC / EntityManager code
}
```

Spring Data style (more common):

```java
public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByStatus(String status);
}
```

(Interface becomes a bean via Spring Data — you don’t put `@Repository` yourself usually, though it’s implied.)

### `@Controller` vs `@RestController`

| | `@Controller` | `@RestController` |
|---|---------------|-------------------|
| Returns | View name (Thymeleaf/JSP) or body if `@ResponseBody` | Body directly (JSON) |
| Composition | MVC controller | `@Controller` + `@ResponseBody` |

```java
@Controller
public class PageController {
  @GetMapping("/home")
  public String home(Model model) {
    model.addAttribute("title", "Hi");
    return "home"; // templates/home.html
  }
}

@RestController
@RequestMapping("/api/orders")
public class OrderController {
  @GetMapping("/{id}")
  public OrderDto get(@PathVariable Long id) { ... }
}
```

**Detailed difference:**  
With `@Controller`, return type `String` means **view name**.  
With `@RestController`, return type `String` means **response body text**. That single fact catches many juniors.

---

## 3. Dependency injection annotations

### `@Autowired`

Injects a dependency **by type**.

```java
@Service
public class OrderService {
  private final OrderRepository repo;

  // Best practice: constructor injection
  public OrderService(OrderRepository repo) {
    this.repo = repo;
  }
}
```

From Spring 4.3+, if there is **only one constructor**, `@Autowired` on it is optional.

**Three injection styles:**

| Style | Pros | Cons |
|-------|------|------|
| Constructor | Required deps clear, testable, `final` fields | Verbose if many deps (sign of SRP issue) |
| Setter | Optional deps | Mutable, easy to forget to set |
| Field | Short | Hard to test, hides deps, can’t use `final` |

**Product answer:** Prefer constructor injection always.

### `@Qualifier` — when multiple beans match

```java
public interface PaymentGateway { void pay(long amount); }

@Service
@Qualifier("upi")
public class UpiGateway implements PaymentGateway { ... }

@Service
@Qualifier("card")
public class CardGateway implements PaymentGateway { ... }

@Service
public class CheckoutService {
  public CheckoutService(@Qualifier("upi") PaymentGateway gateway) {
    this.gateway = gateway;
  }
}
```

### `@Primary`

Marks the default bean when several implement the same type — used if you don’t want Qualifier everywhere.

```java
@Service
@Primary
public class DefaultMailSender implements MailSender { ... }
```

### `@Lazy`

Don’t create the bean until first use. Can break circular dependency temporarily (better to redesign).

### `@Scope`

```java
@Component
@Scope("prototype")
public class ReportBuilder { ... }
```

Default is **singleton** — one shared instance per container.

---

## 4. `@Bean` + `@Configuration` (factory methods)

Use when you **cannot** annotate a class (third-party) or need custom construction.

```java
@Configuration
public class AppConfig {

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.findAndRegisterModules();
    return mapper;
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.setConnectTimeout(Duration.ofSeconds(2)).build();
  }
}
```

**`@Component` vs `@Bean` (say clearly):**

- `@Component` on **your** class → Spring instantiates it  
- `@Bean` method → **you** instantiate; Spring manages the returned object  

### Full `@Configuration` vs lite mode

- Class with `@Configuration`: CGLIB proxy; calling another `@Bean` method goes through container (singleton respected).  
- `@Bean` methods on a plain `@Component`: “lite”; inter-bean calls may create a **new** instance — subtle bug.

---

## 5. Web / REST annotations (detailed)

### Mapping family

```java
@RestController
@RequestMapping("/api/users")
public class UserController {

  @GetMapping("/{id}")
  public UserDto get(@PathVariable Long id) { ... }

  @GetMapping
  public Page<UserDto> list(@RequestParam(defaultValue = "0") int page) { ... }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDto create(@Valid @RequestBody CreateUserRequest req) { ... }

  @PutMapping("/{id}")
  public UserDto replace(@PathVariable Long id, @RequestBody UpdateUserRequest req) { ... }

  @PatchMapping("/{id}")
  public UserDto patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) { ... }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) { ... }
}
```

| Annotation | Binds |
|------------|--------|
| `@PathVariable` | `/users/5` → `id=5` |
| `@RequestParam` | `/users?page=1` → `page=1` |
| `@RequestBody` | JSON body → Java object |
| `@RequestHeader` | Header value |
| `@ResponseStatus` | Force HTTP status |
| `@Valid` | Run Bean Validation before method body |

### `@RequestParam` details

```java
@GetMapping("/search")
public List<User> search(
    @RequestParam String q,                    // required by default
    @RequestParam(required = false) String sort,
    @RequestParam(defaultValue = "10") int size) { ... }
```

Missing required param → **400 Bad Request**.

### `@ControllerAdvice` / `@RestControllerAdvice`

Central place for exceptions → consistent error JSON.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ApiError handleNotFound(EntityNotFoundException ex) {
    return new ApiError("NOT_FOUND", ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiError handleValidation(MethodArgumentNotValidException ex) {
    String msg = ex.getBindingResult().getFieldErrors().stream()
        .map(f -> f.getField() + ": " + f.getDefaultMessage())
        .collect(Collectors.joining(", "));
    return new ApiError("VALIDATION_ERROR", msg);
  }
}
```

**Why interviewers care:** shows you build APIs for clients, not just happy-path demos.

---

## 6. JPA / transaction annotations

### Entity mapping (essentials)

```java
@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 32)
  private String status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;
}
```

### `@Transactional` — detailed

```java
@Service
public class OrderService {
  private final OrderRepository orders;
  private final PaymentClient payments;

  @Transactional
  public Order place(CreateOrderCommand cmd) {
    Order order = orders.save(Order.newFrom(cmd));
    payments.charge(order); // if this throws RuntimeException → whole TX rolls back
    return order;
  }

  @Transactional(readOnly = true)
  public Order get(Long id) {
    return orders.findById(id).orElseThrow();
  }
}
```

**Rules to say in interview:**

1. Prefer on **service** methods, not controllers  
2. Default rollback on **unchecked** exceptions  
3. For checked: `@Transactional(rollbackFor = Exception.class)`  
4. Proxy-based — **self-invocation** (`this.method()`) skips the proxy → no transaction  
5. `readOnly = true` can optimize flush behavior for reads  

---

## 7. Validation annotations

Need `spring-boot-starter-validation`.

```java
public class CreateUserRequest {
  @NotBlank
  private String name;

  @Email
  @NotBlank
  private String email;

  @Min(18)
  private int age;
}
```

Trigger with `@Valid` on `@RequestBody`.

| Annotation | Meaning |
|------------|---------|
| `@NotNull` | Not null (can be blank string) |
| `@NotBlank` | Not null, not empty, not whitespace (CharSequence) |
| `@NotEmpty` | Not null, not empty (collection/string) |
| `@Size(min,max)` | Length / collection size |
| `@Email` | Email format |

---

## 8. Config & environment annotations

```java
@Value("${app.feature.newCheckout:false}")
private boolean newCheckout;

@ConfigurationProperties(prefix = "app.feature")
@Component
public class FeatureProps {
  private boolean newCheckout;
  // getters/setters
}

@Bean
@Profile("dev")
public CommandLineRunner seedData() {
  return args -> System.out.println("Seeding DEV data");
}
```

`@Profile("dev")` bean exists only when `dev` profile is active.

---

## 9. Cross-cutting: schedule, async, cache, AOP

```java
@EnableScheduling
@SpringBootApplication
public class App {}

@Component
public class Jobs {
  @Scheduled(cron = "0 0 * * * *") // every hour
  public void hourly() { ... }
}
```

```java
@EnableAsync
...
@Async
public CompletableFuture<String> slow() {
  return CompletableFuture.completedFuture("done");
}
```

```java
@EnableCaching
...
@Cacheable("users")
public User find(Long id) { ... }

@CacheEvict(value = "users", key = "#id")
public void delete(Long id) { ... }
```

Without `@EnableAsync` / `@EnableCaching` / `@EnableScheduling`, the annotations are **ignored** — classic trick question.

---

## 10. Testing annotations

| Annotation | What it loads |
|------------|----------------|
| `@SpringBootTest` | Full application context (slow, integration) |
| `@WebMvcTest(UserController.class)` | Only web layer + controller |
| `@DataJpaTest` | JPA slice + in-memory DB often |
| `@MockBean` | Replace a bean with Mockito mock in context |
| `@JsonTest` | Jackson serialization slice |

```java
@WebMvcTest(OrderController.class)
class OrderControllerTest {
  @Autowired MockMvc mockMvc;
  @MockBean OrderService orderService;

  @Test
  void getOrder() throws Exception {
    when(orderService.get(1L)).thenReturn(sample());
    mockMvc.perform(get("/api/orders/1"))
           .andExpect(status().isOk());
  }
}
```

---

## 11. Security annotations (overview)

```java
@PreAuthorize("hasRole('ADMIN')")
public void deleteAll() { ... }

@PreAuthorize("hasAuthority('ORDER_WRITE')")
public Order create(Order o) { ... }
```

Needs method security enabled (`@EnableMethodSecurity` in Boot 3).

Full story: [Security page](05-spring-boot/interview/security.md)

---

## 12. Top 25 annotations — flash list

`@SpringBootApplication`, `@Configuration`, `@Bean`, `@Component`, `@Service`, `@Repository`, `@Controller`, `@RestController`, `@Autowired`, `@Qualifier`, `@Primary`, `@Scope`, `@Lazy`, `@Value`, `@ConfigurationProperties`, `@Profile`, `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PathVariable`, `@RequestParam`, `@RequestBody`, `@Valid`, `@ResponseStatus`, `@RestControllerAdvice`, `@ExceptionHandler`, `@Entity`, `@Id`, `@GeneratedValue`, `@Transactional`, `@Scheduled`, `@Async`, `@Cacheable`, `@SpringBootTest`, `@MockBean`

For each, be ready with: **layer + one sentence + one trick**.

---

## 13. Trick questions (annotations)

**Q: Is `@Service` required for DI to work?**  
No — `@Component` works. `@Service` is convention + clarity.

**Q: Can `@RestController` return a Thymeleaf page?**  
Not the normal way — use `@Controller` for views.

**Q: `@Autowired` on private fields — does Spring break encapsulation?**  
It uses reflection. Works, but constructor injection is preferred.

**Q: Two `@GetMapping` same path — what happens?**  
Application context fails on startup (ambiguous mapping).

**Q: Does `@Transactional` work on private methods?**  
No (Spring proxy). Method should be public and called through the Spring proxy.

**Q: `@RequestBody` required on GET?**  
Don’t design APIs that way; many clients/proxies ignore GET bodies.

Next: [IoC / DI detailed](05-spring-boot/interview/ioc-di.md)
