# Generics (OOPs / Java interview)

**Level:** Product mid rounds (and strong service)

---

## Why generics?

Type safety at compile time; remove raw casts.

```java
List<String> list = new ArrayList<>();
list.add("a");
String s = list.get(0); // no cast
```

---

## Type parameters & bounds

```java
class Box<T> {
  private T value;
  void set(T v){ value = v; }
  T get(){ return value; }
}

class NumberBox<T extends Number> {
  double sum(T a, T b){ return a.doubleValue() + b.doubleValue(); }
}
```

---

## Wildcards

| Form | Meaning | Typical use |
|------|---------|-------------|
| `?` | unknown | read Object-ish |
| `? extends T` | producer | get values as T |
| `? super T` | consumer | put values of T |

**PECS:** Producer Extends, Consumer Super.

```java
void copy(List<? extends Number> src, List<? super Number> dest) {
  for (Number n : src) dest.add(n);
}
```

---

## Type erasure

Generics erased at runtime → `List<String>` and `List<Integer>` same raw type at JVM.

**Tricks from erasure:**
- Cannot `new T()`
- Cannot create generic array easily
- Cannot overload `void m(List<String>)` and `void m(List<Integer>)`
- `instanceof List<String>` illegal — use `List<?>`  

---

## Trick questions

**Q:** Raw type `List` vs `List<?>`?  
**A:** Raw disables checks (legacy). `List<?>` is unknown type — safer unknown.

**Q:** Can generic method be static?  
**A:** Yes — declare its own type param: `static <T> void swap(T[] a, int i, int j)`.

**Q:** Covariance of arrays vs generics?  
**A:** Arrays covariant (`Object[] o = new String[]`) → can fail at runtime. Generics invariant (`List<Object> ≠ List<String>`).

**Q:** Bounded wildcard vs bounded type param?  
**A:** Use type param when you need to name/relate the type across signature; wildcard when flexible in/out only.

**Q:** Bridge methods?  
**A:** Compiler-generated for polymorphism with erased generics (advanced).

Next: [Exceptions tricks](03-dsa-and-interview/oops/exceptions-tricks.md)
