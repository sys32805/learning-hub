# Object class & JVM memory (OOPs lens)

**Level:** Mid → Product

---

## Important `Object` methods

| Method | Use |
|--------|-----|
| `equals` | Logical equality |
| `hashCode` | Hash collections |
| `toString` | Debug / logging |
| `clone` | Shallow copy (fragile — prefer copy ctor) |
| `getClass` | Runtime type |
| `wait` / `notify` / `notifyAll` | Thread coordination |
| `finalize` | Deprecated — don’t use |

---

## Memory areas (interview sketch)

| Area | Stores |
|------|--------|
| Heap | Objects, arrays |
| Stack | Frames, local primitives, references |
| Metaspace | Class metadata |
| String Pool | Interned strings (on heap) |

```java
void demo() {
  int x = 10;           // stack
  Student s = new Student(); // s on stack, object on heap
}
```

---

## Pass by value

Java is **pass-by-value**:
- Primitives: copy of value  
- Objects: copy of **reference** (not the object)  

```java
void change(Student s){ s.name = "X"; }      // visible
void rebind(Student s){ s = new Student(); } // caller's ref unchanged
```

---

## Trick questions

**Q:** Where are static variables stored?  
**A:** With class data (historically Method Area / Metaspace-related; say “class-level on heap/metaspace” carefully — interviewer wants: not per-instance).

**Q:** StackOverflowError vs OutOfMemoryError?  
**A:** Stack = deep recursion / large frames. OOM = heap (or metaspace) exhausted.

**Q:** Shallow vs deep clone?  
**A:** Shallow copies refs; nested objects shared. Deep copies graph.

**Q:** Why avoid `clone()`?  
**A:** Broken contract, protected, shallow by default — prefer factory / copy constructor.

**Q:** Does `new` always allocate?  
**A:** Yes for normal objects; escape analysis may stack-allocate/scalar-replace in JIT (advanced).

Next: [Inner classes & enums](03-dsa-and-interview/oops/inner-classes-enums.md)
