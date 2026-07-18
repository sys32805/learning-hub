# Spring Boot — daily revision (15–25 min)

Mark topics you can explain **with one example + one trick**.

---

## 7-day loop

| Day | Topic | Page |
|-----|-------|------|
| 1 | Core Boot | [core-basics](05-spring-boot/interview/core-basics.md) |
| 2 | Annotations | [annotations](05-spring-boot/interview/annotations.md) |
| 3 | IoC / DI | [ioc-di](05-spring-boot/interview/ioc-di.md) |
| 4 | REST | [rest-web](05-spring-boot/interview/rest-web.md) |
| 5 | JPA | [jpa-hibernate](05-spring-boot/interview/jpa-hibernate.md) |
| 6 | Security | [security](05-spring-boot/interview/security.md) |
| 7 | Tricks + Q&A drill | [tricks](05-spring-boot/interview/tricks.md) · [Q&A](05-spring-boot/interview/interview-qa.md) |

Then repeat aloud without notes.

---

## 5-minute warm-up (every day)

1. What is `@SpringBootApplication`?  
2. Constructor injection why?  
3. `@RestController` vs `@Controller`  
4. `@Transactional` where + self-invocation trap  
5. Authn vs Authz  

---

## Company lens

| Type | Focus |
|------|--------|
| Service (TCS…) | Annotations, DI, REST mappings, basic JPA, what is Boot |
| Product | Auto-config conditions, proxies, N+1, security filter chain, DTO, Actuator |

---

## Practice in code

Open a small project under [02-projects](https://github.com/sys32805/learning-hub/tree/main/05-spring-boot/02-projects/Spring-Boot-Advanced-Projects) and find real usages of `@RestController`, `@Service`, `@Autowired` / ctor, `@Entity`.
