# REST & Spring Web

**Level:** TCS → Product  
**Related:** [Annotations](05-spring-boot/interview/annotations.md)

---

## REST controller pattern

```java
@RestController
@RequestMapping("/api/orders")
public class OrderController {
  private final OrderService service;
  public OrderController(OrderService service) { this.service = service; }

  @GetMapping("/{id}")
  public OrderDto get(@PathVariable Long id) {
    return service.find(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OrderDto create(@Valid @RequestBody CreateOrderRequest req) {
    return service.create(req);
  }
}
```

---

## HTTP status & exceptions

| Situation | Typical status |
|-----------|----------------|
| Created | 201 |
| No body | 204 |
| Bad input | 400 |
| Unauthorized | 401 |
| Forbidden | 403 |
| Not found | 404 |
| Conflict | 409 |
| Server error | 500 |

```java
@RestControllerAdvice
public class ApiErrors {
  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String,String> notFound(EntityNotFoundException ex) {
    return Map.of("error", ex.getMessage());
  }
}
```

---

## Content negotiation

- Default JSON via Jackson (`MappingJackson2HttpMessageConverter`)
- `produces` / `consumes` on mappings
- `Accept` / `Content-Type` headers

---

## Filters vs Interceptors vs AOP

| | Filter | HandlerInterceptor | AOP |
|---|--------|--------------------|-----|
| Level | Servlet | Spring MVC | Spring beans |
| Sees | All requests | Controller requests | Join points |
| Use | Auth header, CORS, logging | Pre/post controller | Transactions, metrics |

---

## Trick questions

**Q:** `@Controller` vs `@RestController`?  
**A:** `@RestController` = `@Controller` + `@ResponseBody` on every method.

**Q:** Put business logic in controller?  
**A:** No — thin controller, logic in `@Service`.

**Q:** Idempotent methods?  
**A:** GET, PUT, DELETE should be idempotent; POST usually not.

**Q:** Why DTO instead of entity in API?  
**A:** Hide internals, avoid lazy-load serialization issues, version API independently.

**Q:** `@RequestParam` required default?  
**A:** `true` — missing param → 400 unless `required=false` or defaultValue.

**Q:** CORS how?  
**A:** `@CrossOrigin` or global `WebMvcConfigurer` / Security CORS config.

**Q:** What is CSRF?  
**A:** Cross-site request forgery — relevant for browser sessions; often disabled for pure JWT APIs (know trade-offs).

Next: [JPA / Hibernate](05-spring-boot/interview/jpa-hibernate.md)
