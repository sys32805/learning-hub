# REST API — interview questions

**Level:** TCS → Product  
**Study first:** [REST API basics](07-rest-api/README.md)  
**Spring-specific:** [REST & Web](05-spring-boot/interview/rest-web.md)

---

## A. Fundamentals

**Q1. What is a REST API?**  
An API that follows REST principles: resources identified by URIs, manipulated with standard HTTP methods, usually returning JSON representations over HTTP.

**Q2. What are the main REST constraints?**  
Client–server, stateless, cacheable, uniform interface, layered system, (optional) code-on-demand.

**Q3. What does “stateless” mean?**  
Each request carries all info needed (auth token, etc.). Server does not rely on stored client session for understanding the request. (You can still store data in DB — “stateless” ≠ “no database”.)

**Q4. Resource vs representation?**  
Resource = the conceptual entity (`Order 42`). Representation = JSON/XML you send/receive for it.

**Q5. Why prefer nouns in URLs?**  
URLs identify *what*; HTTP methods identify *action*. Verbs in paths duplicate what methods already express.

---

## B. HTTP methods & semantics

**Q6. GET vs POST?**  
GET reads (safe, cacheable, no body side effects). POST creates or triggers non-idempotent actions; may have a body.

**Q7. PUT vs PATCH?**  
PUT replaces the **whole** resource. PATCH updates **partial** fields.

**Q8. What is idempotent? Which methods are?**  
Same request N times → same server state. Idempotent: GET, PUT, DELETE, HEAD, OPTIONS. Not: POST (usually).

**Q9. Is DELETE idempotent?**  
Yes. First call deletes; later calls typically return 404 or 204 — resource stays gone.

**Q10. When would you use POST for an “update”?**  
When the operation is not a simple resource replace (e.g. `/orders/1/cancel`, workflow actions). Prefer clear resource design when possible.

---

## C. Status codes

**Q11. 200 vs 201 vs 204?**  
200 = success with body. 201 = created (often + `Location`). 204 = success, no body.

**Q12. 401 vs 403?**  
401 = not authenticated (who are you?). 403 = authenticated but not authorized (you can’t).

**Q13. When 404 vs 400?**  
404 = resource/path not found. 400 = request malformed / invalid syntax or obvious bad input.

**Q14. When 409?**  
Conflict — e.g. creating a user with an email that already exists, or optimistic lock failure.

**Q15. Should APIs return 500 for validation errors?**  
No. Validation → 400/422. 500 is for unexpected server failures.

---

## D. Design & best practices

**Q16. How do you version a REST API?**  
Most common: `/api/v1/...`. Alternatives: custom Accept header or query param. Explain trade-offs briefly.

**Q17. How do you paginate?**  
Query params (`page`, `size` or `limit`, `offset`) + metadata (`totalElements`). Cursor-based for large feeds.

**Q18. How do you handle filtering and sorting?**  
Query params: `?status=OPEN&sort=createdAt,desc`. Document allowed fields.

**Q19. What is HATEOAS?**  
Responses include links to related actions/resources. Nice in theory; many production APIs are “REST-ish” without full HATEOAS.

**Q20. What belongs in headers vs body vs query?**  
Auth/caching/content-type → headers. Filters/pagination → query. Resource data → body (POST/PUT/PATCH).

---

## E. Security

**Q21. How do you secure a REST API?**  
HTTPS, authentication (JWT/OAuth2/API key), authorization (roles/permissions), input validation, rate limiting, CORS lockdown, no secrets in URLs/logs.

**Q22. What is CORS?**  
Browser security: cross-origin AJAX only allowed if server sends proper `Access-Control-*` headers. Preflight uses OPTIONS.

**Q23. JWT in Authorization header — format?**  
`Authorization: Bearer <token>`

**Q24. Why not put tokens in query strings?**  
They leak into logs, history, Referer headers.

**Q25. CSRF vs REST + JWT?**  
CSRF mainly targets cookie-based session auth in browsers. Pure Bearer-token APIs are less CSRF-prone; still know the concept for interviews.

---

## F. Errors, contracts, tooling

**Q26. How should errors look?**  
Consistent JSON: status, message, timestamp, path, optional field errors.

**Q27. What is OpenAPI / Swagger?**  
Contract/docs for endpoints, models, status codes. Used for docs, client generation, and API review.

**Q28. Idempotency-Key — why?**  
Clients retry POST safely (payments). Server stores key → returns same result instead of duplicating.

**Q29. What is content negotiation?**  
Client `Accept` header chooses representation (`application/json` vs `xml`). Server responds accordingly or 406.

**Q30. REST vs SOAP — interview line?**  
REST: lightweight, HTTP+JSON, resource-oriented. SOAP: protocol, XML, WSDL, strict contract, often enterprise legacy.

---

## G. Scenario / tricky

**Q31. Design endpoints for a blog.**  

```text
GET    /posts
POST   /posts
GET    /posts/{id}
PUT    /posts/{id}
PATCH  /posts/{id}
DELETE /posts/{id}
GET    /posts/{id}/comments
POST   /posts/{id}/comments
```

**Q32. Client retries a failed POST and creates two orders. Fix?**  
Idempotency-Key, or unique business constraint (e.g. clientOrderId unique), return 409 on duplicate.

**Q33. GET /users returns 10,000 users — problem?**  
No pagination; slow, memory heavy. Add paging + maybe sparse fieldsets.

**Q34. Soft delete — what status on DELETE?**  
Often 204, resource marked deleted in DB. Later GET may 404 or return with `deleted: true` depending on product rules — state your choice.

**Q35. Can GET have a body?**  
Spec discourages it; many servers ignore GET bodies. Don’t design APIs that need GET bodies — use query/POST search endpoint.

---

## H. Spring Boot quick hits (if interviewer switches)

**Q36. `@Controller` vs `@RestController`?**  
`@RestController` = `@Controller` + `@ResponseBody` (return value → HTTP body).

**Q37. `@PathVariable` vs `@RequestParam` vs `@RequestBody`?**  
Path segment vs query param vs JSON body.

**Q38. How do you return 201 with Location?**  
`ResponseEntity.created(uri).body(dto)`.

**Q39. Global exception handling?**  
`@RestControllerAdvice` + `@ExceptionHandler`.

**Q40. Validation on request body?**  
`@Valid` / `@Validated` on `@RequestBody` + Bean Validation annotations (`@NotNull`, `@Size`, …).

---

## 2-minute verbal answer (memorize)

> REST exposes resources via URLs and HTTP methods. GET is safe; PUT/DELETE are idempotent; POST usually isn’t. We return proper status codes (201 on create, 401 vs 403), version with `/v1`, paginate lists, secure with HTTPS + token auth, and return consistent error JSON. For Spring, `@RestController`, mapping annotations, DTOs, and `@RestControllerAdvice` are the usual stack.

---

## Daily drill (10 questions)

1. Stateless meaning?  
2. PUT vs PATCH?  
3. Idempotent methods?  
4. 401 vs 403?  
5. 201 vs 204?  
6. Why version APIs?  
7. How to paginate?  
8. Secure REST checklist?  
9. CORS in one line?  
10. REST vs SOAP one-liner?

**Review basics:** [REST API](07-rest-api/README.md)
