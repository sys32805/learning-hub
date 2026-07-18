# Spring Boot — core basics (detailed)

**Level:** TCS → Product  
**Next:** [Annotations](05-spring-boot/interview/annotations.md)

---

## 1. What problem does Spring Boot solve?

Before Boot, a typical Spring app needed:

- Manual dependency versions in Maven/Gradle  
- Lots of XML or Java `@Configuration`  
- Explicit DataSource, DispatcherServlet, ViewResolver wiring  
- External Tomcat + WAR deployment  

**Spring Boot** keeps Spring’s power (DI, AOP, MVC, Security, Data) but adds **conventions**:

1. **Starters** — one dependency pulls a tested set of libraries  
2. **Auto-configuration** — if a jar is on the classpath, Boot wires sensible defaults  
3. **Embedded server** — run as `java -jar app.jar`  
4. **Production features** — Actuator health/metrics  

**Interview one-liner:**  
> Spring Boot is not a new framework; it is an opinionated way to build Spring applications faster with less boilerplate.

---

## 2. Spring Framework vs Spring Boot vs Spring Cloud

| Layer | Role |
|-------|------|
| **Spring Framework** | Core: IoC container, MVC, AOP, transactions |
| **Spring Boot** | Packaging + auto-config + starters + embedded server |
| **Spring Cloud** | Distributed systems: config server, discovery, gateway, circuit breaker |

You almost always use **Framework + Boot**. Cloud only when microservices need it.

---

## 3. How a Boot app starts (flow)

```text
main()
  → SpringApplication.run(App.class, args)
    → Create ApplicationContext
    → Load application.properties / yml (+ profiles)
    → @ComponentScan (find your beans)
    → @EnableAutoConfiguration (create framework beans)
    → Refresh context (create singletons)
    → Start embedded Tomcat
    → App ready on port 8080 (default)
```

```java
@SpringBootApplication
public class DemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}
```

`@SpringBootApplication` = three annotations (see Annotations page):

- `@Configuration` — this class can define `@Bean`s  
- `@EnableAutoConfiguration` — turn on Boot’s auto-config  
- `@ComponentScan` — scan this package and **sub-packages**  

**Why main class package matters:**  
If your main class is in `com.company.app` but a `@Service` is in `com.company.other`, it is **not scanned** unless you add `basePackages`.

---

## 4. Auto-configuration — deep explanation

### Idea

Boot ships many `*AutoConfiguration` classes. Each says:

> “If condition X is true, create bean Y.”

Examples of conditions:

| Condition | Meaning |
|-----------|---------|
| `@ConditionalOnClass` | Class exists on classpath (e.g. `DataSource.class`) |
| `@ConditionalOnMissingBean` | You did not already define that bean |
| `@ConditionalOnProperty` | A property is set / has a value |
| `@ConditionalOnWebApplication` | App is a web app |

### Example mental model

You add:

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

Classpath now has Hibernate + Spring Data. Boot may auto-create:

- `DataSource` (from `spring.datasource.*`)  
- `EntityManagerFactory`  
- `TransactionManager`  
- Repository infrastructure  

If **you** declare your own `@Bean DataSource`, Boot usually backs off (`OnMissingBean`).

### How to see what happened

Run with:

```text
java -jar app.jar --debug
```

or set `debug=true` in properties. Boot prints **positive matches** (what applied) and **negative matches** (what skipped and why). This is a **product-company** favorite.

### How to disable one auto-config

```java
@SpringBootApplication(exclude = {
  DataSourceAutoConfiguration.class
})
```

or:

```properties
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
```

---

## 5. Starters — why they matter

A **starter** is a POM/BOM-style dependency that groups libraries that work together.

| Starter | What you get conceptually |
|---------|---------------------------|
| `spring-boot-starter-web` | Spring MVC, Jackson, embedded Tomcat, validation hooks |
| `spring-boot-starter-data-jpa` | Spring Data JPA + Hibernate + JDBC |
| `spring-boot-starter-security` | Spring Security filter chain defaults |
| `spring-boot-starter-validation` | Hibernate Validator |
| `spring-boot-starter-actuator` | Production endpoints |
| `spring-boot-starter-test` | JUnit 5, Mockito, AssertJ, MockMvc, etc. |
| `spring-boot-starter-parent` | Parent POM: dependency management + plugin defaults |

**Interview tip:** Saying “starter-web gives Tomcat + MVC + Jackson” scores better than only “it is a dependency.”

---

## 6. Configuration files (properties / YAML)

### Locations (priority idea)

Boot loads config from several places. Higher priority overrides lower. Common ones:

1. Command-line args  
2. OS environment variables  
3. `application-{profile}.yml`  
4. `application.yml`  

### Example `application.yml`

```yaml
server:
  port: 8080

spring:
  application:
    name: order-service
  profiles:
    active: dev
  datasource:
    url: jdbc:mysql://localhost:3306/orders
    username: root
    password: secret
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

logging:
  level:
    root: INFO
    com.company: DEBUG
```

### Profiles (dev / test / prod)

`application-dev.yml`:

```yaml
spring:
  jpa:
    show-sql: true
```

`application-prod.yml`:

```yaml
spring:
  jpa:
    show-sql: false
    hibernate:
      ddl-auto: validate
```

Activate:

```properties
spring.profiles.active=prod
```

or env: `SPRING_PROFILES_ACTIVE=prod`

**Why profiles matter in interviews:** same codebase, different DB URLs, logging, feature flags — without changing code.

### `@Value` vs `@ConfigurationProperties`

```java
@Value("${server.port}")
private int port; // fine for one value
```

```java
@ConfigurationProperties(prefix = "app.mail")
public class MailProps {
  private String host;
  private int port;
  // getters/setters
}
```

```yaml
app:
  mail:
    host: smtp.example.com
    port: 587
```

Prefer **ConfigurationProperties** for groups of related settings (type-safe, validated, IDE-friendly).

---

## 7. Embedded server & packaging

### Embedded Tomcat

`starter-web` brings embedded Tomcat. Boot starts it inside the same JVM as your app.

Change port: `server.port=9090`

Switch to Jetty: exclude Tomcat, add Jetty starter (know the idea; exact Maven coords less important).

### Fat JAR (executable JAR)

`spring-boot-maven-plugin` builds a JAR that contains:

- Your classes  
- All dependencies  
- Embedded server  
- Boot loader  

Run:

```bash
java -jar target/myapp-0.0.1-SNAPSHOT.jar
```

### WAR

Use WAR when company mandates external WebLogic/Tomcat. You extend `SpringBootServletInitializer`. Most modern microservices use JAR.

---

## 8. Actuator (production readiness)

Add `spring-boot-starter-actuator`.

Common endpoints:

| Endpoint | Use |
|----------|-----|
| `/actuator/health` | Liveness/readiness for K8s |
| `/actuator/info` | App info |
| `/actuator/metrics` | JVM, HTTP, custom metrics |
| `/actuator/env` | Properties (sensitive — protect!) |

In production:

- Expose only needed endpoints  
- Secure with Spring Security  
- Never leave `env` / `heapdump` public  

---

## 9. Layered architecture (how to talk about your project)

```text
Controller  →  Service  →  Repository  →  DB
   |              |             |
  DTO          Business      Entity/JPA
```

| Layer | Annotation | Responsibility |
|-------|------------|----------------|
| Controller | `@RestController` | HTTP in/out only |
| Service | `@Service` | Business rules, `@Transactional` |
| Repository | `@Repository` / Spring Data interface | Persistence |
| Entity | `@Entity` | DB mapping (prefer not as API model) |

Interviewers love: “Controller is thin; logic stays in service.”

---

## 10. Trick questions (with full answers)

**Q: Is Spring Boot a replacement for Spring?**  
No. Boot **uses** Spring. Without Spring Framework there is no Boot.

**Q: What does opinionated mean?**  
Boot chooses defaults (Tomcat, Hibernate, HikariCP, JSON). You can override, but zero-config works for common cases.

**Q: How does Boot decide to create a DataSource?**  
Driver on classpath + `spring.datasource.url` (or embedded DB like H2) + no user-defined DataSource bean → auto-config creates one (typically HikariCP).

**Q: Component scan missed my bean — symptoms?**  
`NoSuchBeanDefinitionException` at startup or injection. Cause: class outside scanned packages, missing stereotype annotation, or wrong profile.

**Q: Difference `spring-boot-starter` vs `spring-boot-starter-parent`?**  
`starter` = library set. `starter-parent` = parent POM managing versions/plugins.

**Q: Can I use Spring Boot without web?**  
Yes — `spring-boot-starter` only (batch, CLI, messaging workers).

**Q: What is the Spring Boot version you use?**  
Be ready to say Boot 2.x vs 3.x: Boot 3 needs Java 17+, `javax.*` → `jakarta.*`.

---

## 11. Say this in 60 seconds (memorize)

> Spring Boot sits on Spring Framework. It uses starters to pull dependencies, auto-configuration to create beans based on classpath and properties, and an embedded server so we run a fat JAR. Configuration lives in application.yml with profiles for environments. We structure code as controller → service → repository. Actuator gives health and metrics for production.

Next: [Annotations — detailed](05-spring-boot/interview/annotations.md)
