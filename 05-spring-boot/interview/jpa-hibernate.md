# JPA / Hibernate with Spring Boot

**Level:** Mid → Product  
**Related:** [Annotations](05-spring-boot/interview/annotations.md)

---

## Stack

Spring Data JPA → Hibernate (default provider) → JDBC → Database

```java
@Entity
@Table(name = "users")
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, unique = true)
  private String email;
}
```

```java
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
  @Query("select u from User u where u.email like %?1%")
  List<User> search(String email);
}
```

---

## `ddl-auto` values

| Value | Meaning |
|-------|---------|
| `none` | No schema change |
| `validate` | Validate only |
| `update` | Update schema (dev) |
| `create` | Drop+create |
| `create-drop` | Drop on shutdown |

**Prod:** prefer Flyway/Liquibase migrations — not `update`.

---

## Relationships (quick)

```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id")
private User user;

@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Order> orders = new ArrayList<>();
```

- Owning side has `@JoinColumn` / FK  
- `mappedBy` = inverse side  

---

## Transactions

```java
@Service
public class UserService {
  @Transactional
  public void register(User u) { repo.save(u); }
}
```

- Read-only: `@Transactional(readOnly = true)`  
- Rollback on RuntimeException by default; checked exceptions need `rollbackFor`  

---

## Trick questions

**Q:** JPA vs Hibernate?  
**A:** JPA = specification/API. Hibernate = implementation.

**Q:** `save` vs `saveAndFlush`?  
**A:** `save` may delay SQL; `saveAndFlush` forces sync to DB immediately.

**Q:** Dirty checking?  
**A:** In a persistence context, changed managed entities are flushed automatically.

**Q:** Detached entity?  
**A:** Outside persistence context — changes not tracked until merge.

**Q:** N+1 problem?  
**A:** One query for parents + N for children — fix with join fetch / entity graph / batch.

**Q:** Why LAZY default for many associations?  
**A:** Performance — don’t load graphs unless needed. Watch Open Session In View (OSIV).

**Q:** OSIV (`spring.jpa.open-in-view`)?  
**A:** Keeps session open during view rendering — convenient, can hide N+1; product teams often disable.

**Q:** `@Transactional` on private method?  
**A:** Won’t work via Spring proxy — must be public (and called through proxy).

Next: [Security](05-spring-boot/interview/security.md)
