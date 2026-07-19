# REST API

Core REST concepts for interviews and day-to-day API design (not Spring-only).

**Related:** [Interview questions](07-rest-api/interview-questions.md) · [Spring REST & Web](05-spring-boot/interview/rest-web.md)

---

## 1. What is REST?

**REST** (Representational State Transfer) is an architectural style for APIs over HTTP.

You expose **resources** (nouns) with URLs, and use **HTTP methods** (verbs) to act on them.

```text
Resource: Order
URL:      /api/v1/orders/42
GET  → read that order
PUT  → replace that order
DELETE → remove that order
```

**Not REST:** RPC-style actions like `/api/doCreateOrder` mixed with random verbs in the path.

---

## 2. Resource design

| Good | Avoid |
|------|--------|
| `/users/10/orders` | `/getUserOrders?id=10` |
| `/products/55` | `/productManager/fetch` |
| Nouns + hierarchy | Verbs in the URL |

**Collection vs item**

- Collection: `/orders`
- Item: `/orders/{id}`

Use **plural nouns** consistently (`/orders`, not `/order` for the collection).

---

## 3. HTTP methods

| Method | Meaning | Body? | Idempotent? | Safe? |
|--------|---------|-------|-------------|-------|
| GET | Read | No | Yes | Yes |
| POST | Create / non-idempotent action | Yes | No | No |
| PUT | Replace full resource | Yes | Yes | No |
| PATCH | Partial update | Yes | Usually yes* | No |
| DELETE | Remove | Optional | Yes | No |
| HEAD | Like GET, headers only | No | Yes | Yes |
| OPTIONS | Allowed methods / CORS preflight | No | Yes | Yes |

\*PATCH is idempotent if your design makes repeating the same patch a no-op.

**Safe** = does not change server state.  
**Idempotent** = repeating the request leaves the same state.

---

## 4. Status codes (must know)

| Code | Meaning | Typical use |
|------|---------|-------------|
| 200 | OK | Successful GET / PUT / PATCH |
| 201 | Created | Successful POST (often with `Location` header) |
| 204 | No Content | Successful DELETE / update with empty body |
| 400 | Bad Request | Invalid JSON / validation |
| 401 | Unauthorized | Missing / bad auth |
| 403 | Forbidden | Authenticated but not allowed |
| 404 | Not Found | Resource missing |
| 409 | Conflict | Duplicate / version conflict |
| 422 | Unprocessable Entity | Semantic validation failed (sometimes used) |
| 429 | Too Many Requests | Rate limited |
| 500 | Internal Server Error | Unexpected server failure |
| 502 / 503 | Bad Gateway / Unavailable | Downstream / maintenance |

**Rule of thumb:** 4xx = client mistake, 5xx = server mistake.

---

## 5. Request / response basics

**Headers you should know**

| Header | Role |
|--------|------|
| `Content-Type` | Body format (`application/json`) |
| `Accept` | What client wants back |
| `Authorization` | Bearer token / Basic / API key |
| `Location` | URL of newly created resource |
| `Cache-Control` | Caching policy |
| `ETag` / `If-Match` | Optimistic concurrency |

**JSON body example**

```http
POST /api/v1/orders HTTP/1.1
Content-Type: application/json
Authorization: Bearer <token>

{
  "customerId": 10,
  "items": [{ "sku": "A1", "qty": 2 }]
}
```

```http
HTTP/1.1 201 Created
Location: /api/v1/orders/901
Content-Type: application/json

{
  "id": 901,
  "status": "CREATED"
}
```

---

## 6. Versioning

Common approaches:

1. **URL:** `/api/v1/orders` (most common in interviews)
2. **Header:** `Accept: application/vnd.myapp.v1+json`
3. **Query:** `/orders?version=1` (less preferred)

Pick one and stay consistent. Breaking changes → new version.

---

## 7. Pagination, filtering, sorting

```text
GET /orders?page=0&size=20&status=OPEN&sort=createdAt,desc
```

Response often includes:

```json
{
  "content": [ ... ],
  "page": 0,
  "size": 20,
  "totalElements": 134
}
```

Avoid returning huge unpaged lists.

---

## 8. Idempotency & safety in practice

- **GET** must not create/update data as a side effect.
- Retrying **PUT/DELETE** should be safe.
- Retrying **POST** may create duplicates → use **Idempotency-Key** header for payments/orders when asked in product interviews.

---

## 9. HATEOAS (optional depth)

Hypermedia: responses include links to related actions.

```json
{
  "id": 901,
  "status": "CREATED",
  "_links": {
    "self": { "href": "/orders/901" },
    "cancel": { "href": "/orders/901/cancel" }
  }
}
```

Many real APIs skip full HATEOAS; know the idea for interviews.

---

## 10. REST vs SOAP vs GraphQL (one-liners)

| | REST | SOAP | GraphQL |
|--|------|------|---------|
| Style | Resources + HTTP | XML messaging protocol | Query language over HTTP |
| Payload | Usually JSON | XML | JSON |
| Contract | OpenAPI / informal | WSDL | Schema |
| Over-fetch | Possible | Possible | Client asks for fields |

---

## 11. Security checklist

- HTTPS only
- Auth: JWT / OAuth2 / session (know trade-offs)
- Never put secrets in URLs
- Validate input; don’t trust client
- CORS only for needed origins
- Rate limiting on public APIs
- Least privilege on endpoints

---

## 12. Error response shape (good habit)

```json
{
  "timestamp": "2026-07-19T10:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "qty must be > 0",
  "path": "/api/v1/orders"
}
```

Same shape for all errors helps clients.

---

## Quick revision

1. REST = resources + HTTP methods + status codes  
2. GET safe; PUT/DELETE idempotent; POST not  
3. 201 + Location on create; 204 on empty success  
4. Version in URL is the common answer  
5. Auth, validation, pagination, clear errors = production-ready API  

**Next:** [REST API interview questions](07-rest-api/interview-questions.md)
