# Multithreading — trick questions

**Full Q&A dump:** [03-multithreading.md](03-dsa-and-interview/oops/03-multithreading.md)

**Level:** Service basics → Product concurrency

---

## Core concepts

| Term | Meaning |
|------|---------|
| Process | Independent; own memory |
| Thread | Lightweight; shares process heap |
| Parallelism | Multiple cores at once |
| Concurrency | Progress on many tasks (may interleave) |

---

## Create threads

1. `extends Thread`  
2. `implements Runnable` (preferred — keep inheritance free)  
3. `Callable` + `Future` (return value / exception)  
4. ExecutorService / thread pools  

```java
ExecutorService pool = Executors.newFixedThreadPool(4);
Future<Integer> f = pool.submit(() -> 42);
```

---

## `synchronized` / visibility

- `synchronized` → mutual exclusion + happens-before  
- `volatile` → visibility, not atomic compound actions  
- `wait` / `notify` must be inside synchronized on same monitor  

---

## Trick questions

**Q:** `start()` vs `run()`?  
**A:** `start()` creates new thread then calls `run`. Calling `run()` directly = same thread (no concurrency).

**Q:** Why Runnable over Thread?  
**A:** Java single inheritance; composition; same task reused with pools.

**Q:** Can we restart a dead thread?  
**A:** No — create new Thread instance.

**Q:** Daemon thread?  
**A:** JVM exits when only daemons remain (`setDaemon(true)` before start).

**Q:** `sleep` vs `wait`?  
**A:** `sleep` doesn’t release lock. `wait` releases monitor; needs notify.

**Q:** Deadlock conditions?  
**A:** Mutual exclusion, hold-and-wait, no preemption, circular wait.

**Q:** `volatile` enough for `count++`?  
**A:** No — need `AtomicInteger` or synchronized.

**Q:** Thread-safe singleton (classic)?  
**A:** Enum singleton, or holder idiom, or double-check + `volatile`.

**Q:** Callable vs Runnable?  
**A:** Callable returns value and can throw checked exceptions.

**Q:** What is ThreadLocal?  
**A:** Per-thread variable copy — useful for context; must remove to avoid leaks in pools.

**Q:** Happens-before (one line)?  
**A:** Guarantees memory visibility order between actions (sync, volatile, start/join…).

```java
synchronized (lock) {
  while (!ready) lock.wait();
  // work
  lock.notifyAll();
}
```

Next: [Daily revision](03-dsa-and-interview/oops/daily-revision.md)
