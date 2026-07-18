# Spring Security (interview)

**Level:** Product + many service interviews  
**Code:** [03-auth](https://github.com/sys32805/learning-hub/tree/main/05-spring-boot/03-auth) · [security projects](https://github.com/sys32805/learning-hub/tree/main/05-spring-boot/02-projects/Spring-Boot-Advanced-Projects/d-security)

---

## Core ideas

| Term | Meaning |
|------|---------|
| Authentication | Who are you? |
| Authorization | What can you do? |
| Principal | Current user |
| GrantedAuthority | Role/permission (`ROLE_USER`) |
| SecurityContext | Holds Authentication (ThreadLocal) |

---

## Filter chain

Request → Security filters (UsernamePassword / JWT / …) → Controller

Modern Boot 3: `SecurityFilterChain` `@Bean` (not `WebSecurityConfigurerAdapter` — removed).

```java
@Bean
SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  http.csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> auth
          .requestMatchers("/api/public/**").permitAll()
          .anyRequest().authenticated())
      .httpBasic(Customizer.withDefaults());
  return http.build();
}
```

---

## Password encoding

Always encode: `BCryptPasswordEncoder` (or Argon2). Never store plain text.

---

## JWT (common API pattern)

1. Login → validate user → issue JWT  
2. Client sends `Authorization: Bearer <token>`  
3. Filter validates JWT → sets SecurityContext  

**Pros:** Stateless APIs. **Cons:** Revocation harder; protect secrets; short expiry + refresh.

---

## Method security

```java
@PreAuthorize("hasRole('ADMIN')")
public void deleteUser(Long id) { ... }
```

Enable: `@EnableMethodSecurity` (Boot 3) / `@EnableGlobalMethodSecurity` (older).

---

## Trick questions

**Q:** Authentication vs authorization?  
**A:** Identity vs permissions.

**Q:** `hasRole('ADMIN')` vs `hasAuthority('ADMIN')`?  
**A:** `hasRole` adds `ROLE_` prefix automatically.

**Q:** Why CSRF for browsers?  
**A:** Session cookie auth can be abused cross-site. Token APIs often disable CSRF carefully.

**Q:** Stateless session policy?  
**A:** `SessionCreationPolicy.STATELESS` for JWT — no HTTP session.

**Q:** Where store JWT on client?  
**A:** Memory / httpOnly cookie debates — know XSS vs CSRF trade-offs.

**Q:** `@EnableWebSecurity` still needed?  
**A:** Often optional in Boot if `SecurityFilterChain` bean present — still commonly used/known.

Next: [Trick questions](05-spring-boot/interview/tricks.md)
