# Exception handling — trick questions

**Full Q&A dump:** [02-exception-handling.md](03-dsa-and-interview/oops/02-exception-handling.md)

**Level:** TCS → Product

---

## Hierarchy (say this cleanly)

```
Throwable
├── Error          (JVM — OutOfMemoryError, StackOverflowError)
└── Exception
    ├── checked    (IOException, SQLException — must handle/declare)
    └── RuntimeException (unchecked — NPE, AIOOBE, IllegalArgument…)
```

---

## Keywords

| Keyword | Role |
|---------|------|
| `try` | risky code |
| `catch` | handle |
| `finally` | always (almost) |
| `throw` | create/throw exception object |
| `throws` | declare checked exceptions on method |

---

## Trick questions

**Q:** Does `finally` always run?  
**A:** Almost — not if JVM exits (`System.exit`), or thread killed, or crash before reaching it.

**Q:** `return` in `try` and `finally`?  
**A:** `finally` runs before method returns; `return` in `finally` **overrides** try’s return (bad practice).

**Q:** Multiple catch order?  
**A:** Child before parent. `catch (Exception)` then `catch (IOException)` → compile error.

**Q:** Can constructor throw exceptions?  
**A:** Yes. Checked must be declared with `throws`.

**Q:** `throw` vs `throws`?  
**A:** `throw` = action. `throws` = method declaration.

**Q:** Checked vs unchecked — when to use which in APIs?  
**A:** Recoverable / force caller to decide → checked. Programming bugs → unchecked.

**Q:** Why prefer try-with-resources?  
**A:** Auto-closes `AutoCloseable`; suppresses secondary exceptions properly (Java 7+).

**Q:** Can we catch `Error`?  
**A:** Technically yes — almost never should.

**Q:** Custom exception — extend what?  
**A:** Business recoverable → `Exception`. Programming failure → `RuntimeException`.

**Q:** `printStackTrace` in production?  
**A:** Prefer logger; don’t swallow empty catch.

```java
try (FileReader fr = new FileReader("a.txt")) {
  // use
} catch (IOException e) {
  throw new IllegalStateException("read failed", e); // keep cause
}
```

Next: [Multithreading tricks](03-dsa-and-interview/oops/multithreading-tricks.md)
