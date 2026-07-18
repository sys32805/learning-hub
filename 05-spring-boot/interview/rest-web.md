# REST & Spring Web — detailed

**Level:** TCS → Product  
**Related:** [Annotations](05-spring-boot/interview/annotations.md)

---

## 1. What is a REST API in Spring terms?

You expose resources over HTTP:

| HTTP | Typical meaning | Idempotent? |
|------|-----------------|-------------|
| GET | Read | Yes |
| POST | Create / action | No |
| PUT | Replace full resource | Yes |
| PATCH | Partial update | Usually yes (design-dependent) |
| DELETE | Remove | Yes |

**Idempotent** = same request repeated → same server state.

Spring MVC (`starter-web`) maps HTTP → controller methods via annotations.

---

## 2. End-to-end request flow

```text
Client HTTP request
  → Embedded Tomcat
    → Servlet Filter chain (encoding, security, CORS…)
      → DispatcherServlet
        → HandlerMapping (find controller method)
          → HandlerInterceptor (preHandle)
            → Controller method
              → Service → Repository
            ← return DTO / ResponseEntity
          → message converter (DTO → JSON)
        ← HTTP response
```

Knowing this flow helps explain Filters vs Interceptors vs `@ControllerAdvice`.

---

## 3. Building a proper REST controller

```java
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

  private final OrderService service;

  public OrderController(OrderService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public OrderResponse get(@PathVariable Long id) {
    return service.get(id);
  }

  @PostMapping
  public ResponseEntity<OrderResponse> create(
      @Valid @RequestBody CreateOrderRequest request) {
    OrderResponse created = service.create(request);
    return ResponseEntity
        .created(URI.create("/api/v1/orders/" + created.getId()))
        .body(created);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
```

### Why `ResponseEntity`?

Gives control of:

- Status code  
- Headers (`Location`, caching)  
- Body  

### Why DTO (`OrderResponse`) not `@Entity`?

1. Hide internal fields / relations  
2. Avoid Jackson ↔ Hibernate lazy-load disasters  
3. Version API independently of DB schema  
4. Security — don’t accidentally expose passwords/tokens  

---

## 4. Status codes you should actually use

| Case | Code |
|------|------|
| OK with body | 200 |
| Created | 201 + `Location` header |
| No body (delete) | 204 |
| Validation / bad input | 400 |
| Unauthorized (not logged in) | 401 |
| Forbidden (logged in, no permission) | 403 |
| Not found | 404 |
| Conflict (duplicate email) | 409 |
| Unprocessable / business rule | 422 (optional) |
| Server bug | 500 |

---

## 5. Validation — full pattern

**pom:** `spring-boot-starter-validation`

```java
public class CreateOrderRequest {
  @NotNull
  private Long productId;

  @Min(1)
  private int quantity;

  @NotBlank
  private String customerEmail;
}
```

```java
@PostMapping
public OrderResponse create(@Valid @RequestBody CreateOrderRequest req) { ... }
```

If invalid → `MethodArgumentNotValidException` → handle in `@RestControllerAdvice` → 400 with field messages.

---

## 6. Global exception handling (production style)

```java
public record ApiError(String code, String message, Instant timestamp) {
  static ApiError of(String code, String message) {
    return new ApiError(code, message, Instant.now());
  }
}

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ApiError notFound(EntityNotFoundException ex) {
    return ApiError.of("NOT_FOUND", ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiError validation(MethodArgumentNotValidException ex) {
    String details = ex.getBindingResult().getFieldErrors().stream()
        .map(err -> err.getField() + " " + err.getDefaultMessage())
        .collect(Collectors.joining("; "));
    return ApiError.of("VALIDATION_ERROR", details);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiError generic(Exception ex) {
    // log full stack internally
    return ApiError.of("INTERNAL_ERROR", "Unexpected error");
  }
}
```

**Interview point:** don’t leak stack traces to clients.

---

## 7. Filters vs Interceptors vs AOP

| Mechanism | Works at | Typical use |
|-----------|----------|-------------|
| **Servlet Filter** | Before DispatcherServlet | CORS, gzip, auth token parse, logging all HTTP |
| **HandlerInterceptor** | Around controller | Add model attrs, permission checks for MVC |
| **ControllerAdvice** | Around controller exceptions/binding | Error JSON |
| **AOP (`@Around`)** | Around Spring bean methods | Timing, audit, transactions |

```java
@Component
public class RequestTimingFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                  FilterChain chain) throws ServletException, IOException {
    long start = System.currentTimeMillis();
    try {
      chain.doFilter(req, res);
    } finally {
      long took = System.currentTimeMillis() - start;
      // log took
    }
  }
}
```

---

## 8. Content negotiation & Jackson

Default: JSON via Jackson.

```java
@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
public OrderResponse get(...) { ... }
```

**Infinite recursion trap:**

```java
// User has List<Order>, Order has User → JSON cycle
```

Fixes: `@JsonIgnore`, `@JsonManagedReference`/`@JsonBackReference`, or **DTOs** (best).

---

## 9. CORS

Browser blocks cross-origin calls unless server allows.

```java
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class Api { }
```

Or global:

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**")
            .allowedOrigins("https://myapp.com")
            .allowedMethods("GET", "POST", "PUT", "DELETE");
  }
}
```

With Spring Security, CORS must also be configured in the security filter chain.

---

## 10. Versioning APIs (know options)

1. URL: `/api/v1/orders`  
2. Header: `Accept: application/vnd.company.v1+json`  
3. Query: `/orders?version=1`  

Most common in interviews: **URL versioning**.

---

## 11. Trick questions

**Q: `@Controller` vs `@RestController`?**  
RestController implies `@ResponseBody` on every method — return value is the HTTP body, not a view name.

**Q: Where should business logic live?**  
Service layer. Controllers only translate HTTP ↔ application calls.

**Q: Is POST always create?**  
Often yes for collections (`POST /orders`), but POST can also trigger actions (`POST /orders/1/cancel`). Prefer clear resource design.

**Q: PUT vs PATCH?**  
PUT replaces the whole resource representation; PATCH sends partial changes.

**Q: Why 401 vs 403?**  
401 = not authenticated. 403 = authenticated but not allowed.

**Q: What is CSRF and when disable?**  
Browser + cookie session attacks. Stateless JWT APIs often disable CSRF; browser session apps should keep protection.

**Q: How does Spring convert JSON to object?**  
`HttpMessageConverter` (Jackson `MappingJackson2HttpMessageConverter`) based on `Content-Type`.

Next: [JPA detailed](05-spring-boot/interview/jpa-hibernate.md)
