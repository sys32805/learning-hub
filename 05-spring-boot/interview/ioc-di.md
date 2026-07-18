# IoC, DI & Beans — detailed

**Level:** High frequency (service + product)  
**Related:** [Annotations](05-spring-boot/interview/annotations.md)

---

## 1. IoC and DI in plain English

### Without Spring (tight coupling)

```java
class OrderService {
  private PaymentClient payments = new UpiPaymentClient(); // hard-wired
}
```

Problems: hard to test, hard to swap UPI → Card, `OrderService` controls creation.

### With DI (loose coupling)

```java
class OrderService {
  private final PaymentClient payments;
  OrderService(PaymentClient payments) { // someone else provides it
    this.payments = payments;
  }
}
```

**IoC (Inversion of Control):** You don’t control *when/how* objects are created — the **container** does.

**DI (Dependency Injection):** The specific way IoC is done — dependencies are **pushed in**.

**Interview line:**  
> IoC is the principle; DI is the pattern Spring uses to implement IoC.

---

## 2. The container: BeanFactory vs ApplicationContext

| | `BeanFactory` | `ApplicationContext` |
|---|---------------|----------------------|
| Role | Basic bean storage/retrieval | Enterprise container |
| Eager singletons | Lazy by default | Eager by default (fail fast) |
| Extra | — | Events, i18n, AOP integration, Environment |
| In Boot | Rarely touch | Always — `SpringApplication` creates one |

Common implementations: `AnnotationConfigApplicationContext`, `GenericWebApplicationContext` (web).

You usually never `new` the context yourself in Boot — `SpringApplication.run` does it.

---

## 3. What is a Spring Bean?

A bean is an object:

1. Instantiated  
2. Assembled (dependencies injected)  
3. Managed  
4. Destroyed  

by the Spring container.

**How a class becomes a bean:**

- Stereotype: `@Component` / `@Service` / …  
- `@Bean` method in `@Configuration`  
- Spring Data repository interface  
- Auto-configuration  

---

## 4. Bean lifecycle (explain step by step)

Say this sequence in interviews:

```text
1. Load bean definition (from annotations/config)
2. Instantiate (constructor)
3. Inject dependencies (populate properties)
4. Aware interfaces (BeanNameAware, ApplicationContextAware, ...)
5. BeanPostProcessor.postProcessBeforeInitialization
6. Init callbacks:
     - @PostConstruct
     - InitializingBean.afterPropertiesSet()
     - custom init-method
7. BeanPostProcessor.postProcessAfterInitialization
   (AOP proxies often applied here)
8. Bean ready for use
9. On shutdown:
     - @PreDestroy
     - DisposableBean.destroy()
     - custom destroy-method
```

```java
@Component
public class CacheWarmup {
  @PostConstruct
  public void warm() {
    System.out.println("Loading cache after DI is done");
  }

  @PreDestroy
  public void clear() {
    System.out.println("Cleanup before JVM/context stops");
  }
}
```

**Why `@PostConstruct` exists:** constructor runs *before* all injection finishes for some styles; init method runs when the bean is fully wired.

---

## 5. Bean scopes — detailed

### Singleton (default)

One instance **per Spring container**.

```java
@Service
public class OrderService { } // singleton
```

Shared across all requests/threads → **your fields must be thread-safe** (or immutable). Don’t store request-specific state in singleton fields.

### Prototype

New instance every time the container **asks** for that bean.

```java
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReportBuilder { }
```

### Web scopes

| Scope | Lifetime |
|-------|----------|
| `request` | One HTTP request |
| `session` | One HTTP session |
| `application` | ServletContext |

Need web-aware context.

### Classic trap: prototype inside singleton

```java
@Service // singleton
public class ReportService {
  private final ReportBuilder builder; // prototype injected ONCE at startup
  public ReportService(ReportBuilder builder) {
    this.builder = builder;
  }
}
```

You get **one** `ReportBuilder` forever.

**Fixes:**

```java
private final ObjectProvider<ReportBuilder> builders;
public void run() {
  ReportBuilder b = builders.getObject(); // new each time
}
```

or `@Lookup` method injection.

---

## 6. Injection strategies — detailed comparison

### Constructor injection (preferred)

```java
@Service
public class OrderService {
  private final OrderRepository orders;
  private final PaymentClient payments;

  public OrderService(OrderRepository orders, PaymentClient payments) {
    this.orders = orders;
    this.payments = payments;
  }
}
```

**Why preferred:**

- Dependencies required → object can’t exist half-built  
- Fields can be `final`  
- Easy unit test: `new OrderService(mockRepo, mockPay)`  
- Cycles are visible early  

### Setter injection

Good for **optional** collaborators.

### Field injection

```java
@Autowired
private OrderRepository orders; // works, but avoid in new code
```

Spring sets via reflection. Harder to test without Spring.

---

## 7. Resolving ambiguity (multiple implementations)

```java
public interface Notifier {
  void send(String to, String msg);
}
```

Options:

1. `@Primary` on the default implementation  
2. `@Qualifier("sms")` on bean + injection point  
3. Inject `List<Notifier>` and publish to all  
4. `@Profile` so only one exists per environment  

---

## 8. Circular dependencies

```text
A → B → A
```

**Constructor injection:** Spring fails at startup (good — forces redesign).  
**Field/setter injection:** Spring may partially create and inject (bad smell).  
**`@Lazy`:** delays one side (workaround, not design).

**Real fix:** extract third class `C`, or use events, or rethink boundaries.

---

## 9. Proxies (important for product interviews)

Many Spring features use **proxies**:

- `@Transactional`  
- `@Async`  
- `@Cacheable`  
- Security `@PreAuthorize`  
- Some `@Scope` proxies  

If you call `this.transactionalMethod()` inside the same class, you call the **real object**, not the proxy → annotation **does not apply**.

**Fix:** move method to another bean, or self-inject the proxy (rare), or use AspectJ weaving (advanced).

---

## 10. `@Bean` vs `@Component` — deeper

| Question | `@Component` | `@Bean` |
|----------|--------------|---------|
| Who calls `new`? | Spring | Your `@Bean` method |
| Can annotate 3rd-party class? | No | Yes |
| Conditional creation? | Possible with `@Conditional` | Easy in method body |
| Multiple instances of same type? | One class → one bean (unless `@Scope`) | Multiple `@Bean` methods |

---

## 11. Trick questions (full answers)

**Q: Are singleton beans thread-safe?**  
The container gives one instance. Thread-safety is **your** responsibility. Prefer immutable state + thread-safe collaborators.

**Q: Default scope?**  
Singleton.

**Q: Eager or lazy singletons in ApplicationContext?**  
Eager by default (created at startup). `@Lazy` delays. Prototype is always lazy until requested.

**Q: Can interface be a bean?**  
Not by putting `@Component` on interface. Implementation is the bean; or `@Bean` returns interface type.

**Q: What does BeanPostProcessor do?**  
Wrap/customize beans during init. Autowiring and AOP infrastructure use this heavily.

**Q: ApplicationContext vs BeanFactory — which does Boot use?**  
ApplicationContext.

**Q: How to run code after all beans are ready?**  
`ApplicationRunner` / `CommandLineRunner` beans, or listen to `ContextRefreshedEvent` / `ApplicationReadyEvent`.

```java
@Component
public class Startup implements ApplicationRunner {
  public void run(ApplicationArguments args) {
    System.out.println("App ready");
  }
}
```

Next: [REST & Web detailed](05-spring-boot/interview/rest-web.md)
