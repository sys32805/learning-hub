# Spring Boot — core basics

**Level:** TCS → Product  
**Related:** [Annotations](05-spring-boot/interview/annotations.md)

---

## Spring vs Spring Boot

| | Spring | Spring Boot |
|---|--------|-------------|
| Config | Lots of XML / Java config | Opinionated auto-config |
| Server | Deploy WAR often | Embedded Tomcat/Jetty/Undertow |
| Goal | Flexible DI framework | Fast production-ready apps |
| Starters | Manual deps | `spring-boot-starter-*` |

**One-liner:** Spring Boot = Spring + auto-configuration + starters + embedded server + Actuator.

---

## What is auto-configuration?

Boot looks at **classpath** + properties and creates beans for you (e.g. `DataSource` if JDBC driver present).

- Driven by `@EnableAutoConfiguration`
- Conditions: `@ConditionalOnClass`, `@ConditionalOnMissingBean`, etc.
- Override by defining your own `@Bean` of same type, or exclude:

```java
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
```

---

## Starters (remember these)

| Starter | Purpose |
|---------|---------|
| `spring-boot-starter-web` | REST / MVC + Tomcat |
| `spring-boot-starter-data-jpa` | JPA + Hibernate |
| `spring-boot-starter-security` | Security |
| `spring-boot-starter-test` | JUnit, Mockito, etc. |
| `spring-boot-starter-validation` | Bean Validation |
| `spring-boot-starter-actuator` | Health, metrics |

---

## `application.properties` / `application.yml`

```yaml
server.port: 8080
spring.datasource.url: jdbc:mysql://localhost:3306/db
spring.jpa.hibernate.ddl-auto: update
spring.profiles.active: dev
```

Profiles: `application-dev.yml`, activate with `spring.profiles.active=dev`.

---

## Embedded server

Default: Tomcat. Change via dependency (`jetty` / `undertow`) and exclude Tomcat.

**JAR vs WAR:** Boot prefers executable JAR (`java -jar app.jar`). WAR for external app servers.

---

## Actuator

Production endpoints: `/actuator/health`, `/actuator/info`, metrics. Secure them in real apps.

---

## Trick questions

**Q:** Is Spring Boot a replacement for Spring?  
**A:** No — it builds on Spring Framework.

**Q:** How does Boot know what to configure?  
**A:** Auto-config classes + `@Conditional*` on classpath/beans/properties.

**Q:** How to disable a specific auto-config?  
**A:** `exclude` on `@SpringBootApplication` or `spring.autoconfigure.exclude`.

**Q:** What is fat/uber JAR?  
**A:** Executable JAR containing app + dependencies + embedded server.

**Q:** `spring-boot-starter-parent` benefit?  
**A:** Dependency & plugin management (versions aligned).

**Q:** Component scan misses my bean?  
**A:** Class outside main package and not scanned — move package or `@ComponentScan(basePackages=...)`.

Next: [Annotations](05-spring-boot/interview/annotations.md)
