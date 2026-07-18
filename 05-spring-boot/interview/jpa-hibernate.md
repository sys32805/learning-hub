# JPA / Hibernate with Spring Boot — detailed

**Level:** Mid → Product (very common)  
**Related:** [Annotations](05-spring-boot/interview/annotations.md)

---

## 1. JPA vs Hibernate vs Spring Data JPA

| Name | What it is |
|------|------------|
| **JPA** | Specification (interfaces/annotations): `EntityManager`, `@Entity` |
| **Hibernate** | Most popular JPA **implementation** |
| **Spring Data JPA** | Extra layer: repository interfaces, query methods, paging |

Boot’s `spring-boot-starter-data-jpa` wires all three together with sensible defaults (HikariCP pool, Hibernate, transaction manager).

---

## 2. Entity mapping — detailed

```java
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 120)
  private String email;

  @Column(name = "full_name", nullable = false)
  private String name;

  @Enumerated(EnumType.STRING)
  private UserStatus status;

  @CreationTimestamp
  private Instant createdAt; // Hibernate annotation example
}
```

### ID generation strategies

| Strategy | Idea |
|----------|------|
| `IDENTITY` | DB auto-increment (MySQL) |
| `SEQUENCE` | DB sequence (PostgreSQL/Oracle) — often best for batching |
| `UUID` | Application-generated UUID |
| `TABLE` | Rarely used today |

---

## 3. Relationships — owning side & fetch types

```java
@Entity
public class Order {
  @Id @GeneratedValue
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id") // owning side — has FK column
  private User user;
}

@Entity
public class User {
  @Id @GeneratedValue
  private Long id;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Order> orders = new ArrayList<>();
}
```

**Rules:**

- **Owning side** decides the FK (`@JoinColumn`)  
- `mappedBy` = inverse side (“mapped by the other field”)  
- Prefer `FetchType.LAZY` for `@ManyToOne` / collections (Boot 3 / Hibernate 6 defaults lean lazy)  

### Cascade & orphanRemoval

- `cascade = ALL` → persist/remove parent affects children  
- `orphanRemoval = true` → remove child from collection → DELETE row  

Use carefully — cascading deletes can wipe more than expected.

---

## 4. Spring Data repositories

```java
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);

  List<User> findByStatusOrderByCreatedAtDesc(UserStatus status);

  Page<User> findByNameContaining(String name, Pageable pageable);

  @Query("select u from User u where lower(u.email) = lower(?1)")
  Optional<User> findByEmailIgnoreCase(String email);

  @Modifying
  @Query("update User u set u.status = :status where u.id = :id")
  int updateStatus(@Param("id") Long id, @Param("status") UserStatus status);
}
```

**Derived query methods:** Spring parses method names → JPQL.

**Paging:**

```java
PageRequest.of(0, 20, Sort.by("createdAt").descending());
```

---

## 5. Persistence context & entity states

| State | Meaning |
|-------|---------|
| **Transient** | `new User()` — not managed |
| **Managed** | Attached to persistence context — changes tracked |
| **Detached** | Was managed; session closed — changes not tracked |
| **Removed** | Marked for delete |

**Dirty checking:** for managed entities, Hibernate detects field changes and issues UPDATE on flush — you often don’t call `save` again inside the same transaction (still common to call `save` for clarity).

---

## 6. Transactions — detailed

```java
@Service
public class UserService {
  private final UserRepository users;

  @Transactional
  public User register(CreateUserRequest req) {
    if (users.existsByEmail(req.getEmail())) {
      throw new IllegalStateException("Email taken");
    }
    return users.save(toEntity(req));
  }

  @Transactional(readOnly = true)
  public User get(Long id) {
    return users.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User " + id));
  }
}
```

### Important rules

1. Put `@Transactional` on **service**, not controller  
2. Repository methods are transactional by default for single repo calls; **business use-cases spanning multiple repos** need service-level TX  
3. Rollback default: **RuntimeException** / Error  
4. Checked exceptions: do **not** rollback unless `rollbackFor`  
5. Self-invocation skips proxy → no TX  

```java
@Transactional(rollbackFor = Exception.class)
public void importFile() throws IOException { ... }
```

---

## 7. N+1 problem (must explain with example)

```text
SELECT * FROM users;              -- 1 query
for each user:
  SELECT * FROM orders WHERE user_id=?;  -- N queries
```

Symptoms: slow API, lots of SQL in logs.

**Fixes:**

1. `join fetch` in `@Query`  
2. `@EntityGraph`  
3. `@BatchSize`  
4. DTO projection query (select only needed columns)  
5. Avoid accidental lazy load in JSON serialization  

```java
@Query("select u from User u left join fetch u.orders where u.id = :id")
Optional<User> findWithOrders(@Param("id") Long id);
```

---

## 8. Open Session In View (OSIV)

Property: `spring.jpa.open-in-view` (default often `true` in Boot).

**Meaning:** Hibernate session stays open while the view/JSON is rendered, so lazy loads in the controller/serializer “work”.

**Problem:** Hides N+1; DB work happens outside service transaction boundaries.

**Product preference:** often set `spring.jpa.open-in-view=false` and fetch what you need in the service layer.

---

## 9. `ddl-auto` and migrations

| Value | Use |
|-------|-----|
| `none` | Prod with external migrations |
| `validate` | Verify entities match schema |
| `update` | Dev convenience — alter tables |
| `create` / `create-drop` | Tests / demos |

**Production:** Flyway or Liquibase versioned SQL/migrations — never rely on `update`.

---

## 10. Connection pool (HikariCP)

Creating DB connections is expensive. Boot uses **HikariCP** by default.

Tune:

```yaml
spring:
  datasource:
    hikari:
      maximum-pool-size: 10
      minimum-idle: 2
      connection-timeout: 30000
```

---

## 11. Trick questions

**Q: JPA vs Hibernate?**  
JPA = API/spec. Hibernate = implementation.

**Q: `save` vs `saveAndFlush`?**  
`save` may delay SQL until flush/commit. `saveAndFlush` forces SQL now (useful before native queries needing the row).

**Q: Why LazyInitializationException?**  
Session closed; code accessed a lazy collection/proxy outside an open persistence context.

**Q: Equals/hashCode on entities?**  
Dangerous with generated IDs (null before persist). Prefer business keys carefully or avoid putting entities in HashSet across states.

**Q: Can I use `Optional` in entities?**  
Not for persistent fields typically — use on repository return types.

**Q: Native query vs JPQL?**  
JPQL is entity-oriented and portable. Native SQL is DB-specific — use when needed for performance/hints.

**Q: First-level cache?**  
Persistence context cache for one session/transaction — same ID returns same instance.

Next: [Security detailed](05-spring-boot/interview/security.md)
