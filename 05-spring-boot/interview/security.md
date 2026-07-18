# Spring Security — detailed

**Level:** Product + many service interviews  
**Code samples in repo:** [03-auth](https://github.com/sys32805/learning-hub/tree/main/05-spring-boot/03-auth) · [security projects](https://github.com/sys32805/learning-hub/tree/main/05-spring-boot/02-projects/Spring-Boot-Advanced-Projects/d-security)

---

## 1. Authentication vs Authorization

| Term | Question | Example |
|------|----------|---------|
| **Authentication** | Who are you? | Login with email/password, validate JWT |
| **Authorization** | What can you do? | Only `ADMIN` can delete users |

Spring Security handles both via filters + `SecurityContext`.

---

## 2. Core objects

| Object | Role |
|--------|------|
| `Authentication` | Principal + credentials + authorities |
| `SecurityContext` | Holds current `Authentication` (ThreadLocal via `SecurityContextHolder`) |
| `UserDetails` | Your user data for auth (username, password hash, roles) |
| `UserDetailsService` | Load user by username from DB |
| `PasswordEncoder` | Hash/verify passwords (BCrypt) |
| `SecurityFilterChain` | Ordered list of security filters for HTTP |

---

## 3. Filter chain (mental model)

```text
HTTP request
  → Security filters (UsernamePasswordAuthenticationFilter, BearerToken..., ExceptionTranslationFilter, ...)
    → If authenticated/authorized
      → Controller
    → Else 401/403
```

You rarely write filters from scratch at first — you **configure** the chain.

---

## 4. Modern Boot 3 configuration style

`WebSecurityConfigurerAdapter` is **removed**. Use a `@Bean SecurityFilterChain`:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable()) // often for pure REST + JWT; know the trade-off
      .sessionManagement(sm ->
          sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(auth -> auth
          .requestMatchers("/api/public/**", "/actuator/health").permitAll()
          .requestMatchers("/api/admin/**").hasRole("ADMIN")
          .anyRequest().authenticated()
      )
      .httpBasic(Customizer.withDefaults()); // or formLogin / oauth2 / JWT filter

    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
```

### What each piece means

- **`csrf.disable()`** — CSRF token not required. OK for stateless token APIs; **not** default-safe for cookie-session browser apps.  
- **`STATELESS`** — no HTTP session; each request must carry credentials (e.g. JWT).  
- **`permitAll`** — no auth needed.  
- **`hasRole("ADMIN")`** — requires authority `ROLE_ADMIN`.  
- **`authenticated()`** — any logged-in user.  

---

## 5. Password encoding (never skip)

```java
String hash = passwordEncoder.encode("plainPassword");
boolean ok = passwordEncoder.matches("plainPassword", hash);
```

**Never** store plain text.  
**Never** invent your own hashing. Use BCrypt / Argon2 / PBKDF2 via Spring’s encoders.

---

## 6. In-memory vs DB users

### In-memory ( demos only )

```java
@Bean
UserDetailsService users(PasswordEncoder encoder) {
  UserDetails admin = User.withUsername("admin")
      .password(encoder.encode("secret"))
      .roles("ADMIN")
      .build();
  return new InMemoryUserDetailsManager(admin);
}
```

### Database (real apps)

Implement `UserDetailsService`:

```java
@Service
public class DbUserDetailsService implements UserDetailsService {
  private final UserRepository users;

  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    AppUser u = users.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
    return User.withUsername(u.getEmail())
        .password(u.getPasswordHash())
        .roles(u.getRole().name()) // e.g. USER
        .build();
  }
}
```

---

## 7. JWT authentication (common product pattern)

### Flow

```text
1. POST /login { email, password }
2. Server authenticates → builds JWT (sub, roles, exp) → returns token
3. Client sends: Authorization: Bearer <jwt>
4. Filter validates signature + expiry → sets SecurityContext
5. Controller runs with authenticated principal
```

### Why JWT is popular

- Stateless (scales horizontally)  
- API-friendly for SPAs / mobile  

### Trade-offs (say these)

| Pros | Cons |
|------|------|
| No server session store | Hard to revoke before expiry |
| Works across services | Token theft = access until expiry |
| Simple for microservices | Must protect signing secret / use asymmetric keys carefully |

**Mitigations:** short access-token TTL, refresh tokens, blocklist for logout (if required), rotate secrets, HTTPS only.

### Where to store token on client

| Storage | Risk |
|---------|------|
| Memory | Safest from XSS persistence; lost on refresh |
| `localStorage` | Vulnerable to XSS |
| HttpOnly cookie | Better vs XSS; need CSRF strategy |

There is no perfect answer — show you understand **XSS vs CSRF**.

---

## 8. Method security

```java
@Configuration
@EnableMethodSecurity
public class MethodSecurityConfig {}

@Service
public class UserAdminService {

  @PreAuthorize("hasRole('ADMIN')")
  public void deleteUser(Long id) { ... }

  @PreAuthorize("hasAuthority('ORDER_WRITE') or hasRole('ADMIN')")
  public Order create(OrderRequest req) { ... }

  @PreAuthorize("#userId == authentication.principal.id")
  public User getOwnProfile(Long userId) { ... }
}
```

### `hasRole` vs `hasAuthority`

- `hasRole("ADMIN")` → looks for `ROLE_ADMIN`  
- `hasAuthority("ROLE_ADMIN")` → exact authority string  
- Custom permissions often use `hasAuthority("ORDER_WRITE")` without `ROLE_` prefix  

---

## 9. CORS + Security together

Browsers send preflight `OPTIONS`. Security config must allow CORS:

```java
http.cors(Customizer.withDefaults());
```

plus a `CorsConfigurationSource` bean. `@CrossOrigin` alone may be insufficient when Security is on.

---

## 10. Actuator & security

Lock down:

```yaml
management:
  endpoints:
    web:
      exposure:
        include: health,info
```

Require auth for everything except health if needed. Never expose `env`, `heapdump`, `beans` publicly.

---

## 11. Common interview scenarios — how to answer

**Q: Explain authentication flow in your project.**  
Describe: login endpoint → UserDetailsService → PasswordEncoder → issue session/JWT → filter validates → SecurityContext → method/URL rules.

**Q: How do you secure a REST API?**  
HTTPS, auth (JWT/OAuth2), authorization rules, input validation, least privilege, no secrets in git, rate limiting (gateway), Actuator locked.

**Q: Session vs JWT?**  
Session: server state, easy revoke, sticky/session store. JWT: client carries token, stateless, revoke harder.

**Q: What is a SecurityContext?**  
Holds Authentication for the current thread so any layer can call `SecurityContextHolder.getContext().getAuthentication()`.

---

## 12. Trick questions

**Q: 401 vs 403?**  
401 unauthenticated; 403 authenticated but forbidden.

**Q: Default user when you only add starter-security?**  
Boot may create a default user and print a generated password in logs — **never for production**.

**Q: Why BCrypt?**  
Adaptive hashing with salt — expensive to brute force.

**Q: Can I put `@PreAuthorize` on a private method?**  
Same proxy limitation as `@Transactional` — public methods called via Spring proxy.

**Q: CSRF with JWT in Authorization header?**  
Browsers don’t auto-attach that header cross-site the same way as cookies — CSRF risk differs. Cookie-based JWT needs CSRF care.

**Q: What changed from Security 5 adapter style?**  
Component-based `SecurityFilterChain` bean; lambda DSL; Boot 3 / Security 6 + Jakarta.

Next: [Trick questions pack](05-spring-boot/interview/tricks.md)
