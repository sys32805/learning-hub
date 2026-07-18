# IoC, DI & Beans

**Level:** High frequency  
**Related:** [Annotations](05-spring-boot/interview/annotations.md)

---

## IoC & DI

- **IoC (Inversion of Control):** Framework creates/manages objects; you don’t `new` everything.
- **DI (Dependency Injection):** Dependencies are provided (ctor/setter/field) instead of looked up.

Container: **ApplicationContext** (BeanFactory is lower-level).

---

## Bean lifecycle (say in order)

1. Instantiate  
2. Populate properties (DI)  
3. `BeanNameAware` / `BeanFactoryAware` / `ApplicationContextAware`  
4. `BeanPostProcessor.before`  
5. `@PostConstruct` / `InitializingBean.afterPropertiesSet` / custom init  
6. `BeanPostProcessor.after`  
7. Bean ready  
8. On destroy: `@PreDestroy` / `DisposableBean` / custom destroy  

```java
@Component
public class Demo {
  @PostConstruct
  void init() { System.out.println("ready"); }
  @PreDestroy
  void stop() { System.out.println("bye"); }
}
```

---

## Scopes

| Scope | Meaning |
|-------|---------|
| `singleton` | One per container (default) |
| `prototype` | New instance each request from container |
| `request` | One per HTTP request (web) |
| `session` | One per HTTP session |
| `application` | ServletContext level |

**Trick:** Injecting `prototype` into `singleton` → prototype created **once** unless you use `ObjectFactory` / `@Lookup` / Provider.

---

## ApplicationContext vs BeanFactory

| | BeanFactory | ApplicationContext |
|---|-------------|-------------------|
| Features | Basic DI | + events, i18n, AOP easy |
| Eager singletons | Lazy by default | Eager by default |
| Use | Rarely direct | Always in Spring Boot |

---

## `@Bean` vs `@Component`

| | `@Component` | `@Bean` |
|---|--------------|---------|
| Where | On class | On `@Configuration` method |
| Use case | Your classes | 3rd-party classes you can’t annotate |

---

## Trick questions

**Q:** Default bean scope?  
**A:** Singleton.

**Q:** Are Spring beans thread-safe?  
**A:** Container creates one singleton — **your code** must be thread-safe if shared mutable state.

**Q:** `@Component` on interface?  
**A:** No — on concrete class (or config `@Bean` returning implementation).

**Q:** Multiple implementations — how inject?  
**A:** `@Qualifier` / `@Primary` / inject `List<MyInterface>`.

**Q:** What is BeanPostProcessor?  
**A:** Hook to customize beans around init (used heavily by Spring internally — `@Autowired` processing etc.).

**Q:** `@Configuration` full vs lite?  
**A:** `@Configuration` uses CGLIB proxy so `@Bean` calls go through container. `@Component` + `@Bean` methods = lite (no inter-bean proxy).

Next: [REST & Web](05-spring-boot/interview/rest-web.md)
