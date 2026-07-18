# Abstract class vs Interface

**Level:** Asked in almost every Java interview

---

## Comparison

| Point | Abstract class | Interface |
|-------|----------------|-----------|
| Inheritance | One `extends` | Many `implements` |
| Fields | Instance fields OK | `public static final` |
| Constructor | Yes | No |
| Methods | Abstract + concrete | Abstract + `default`/`static`/`private` (Java 8/9+) |
| Access | Can be protected etc. | Methods public (default methods public) |
| Use | Shared base + state | Capability / contract |

```java
abstract class Shape {
  abstract double area();
  void print() { System.out.println(area()); }
}
interface Drawable { void draw(); }
class Circle extends Shape implements Drawable {
  double r;
  double area(){ return Math.PI*r*r; }
  public void draw(){ System.out.println("circle"); }
}
```

---

## When to choose (product answer)

- Need **state + partial implementation + IS-A** → abstract class  
- Need **multiple behaviors / API contract** → interface  
- Prefer **interface + composition** for flexibility  

---

## Trick questions

**Q:** Can abstract class have constructor?  
**A:** Yes — called via `super()` when subclass is created. Cannot `new AbstractClass()`.

**Q:** Can we create object of abstract class?  
**A:** No directly. Possible anonymous subclass: `new Shape(){ double area(){return 0;} }`.

**Q:** Interface with no methods?  
**A:** Marker interface (`Serializable`, `Cloneable`).

**Q:** Functional interface?  
**A:** Exactly one abstract method — usable with lambdas (`Runnable`, `Comparator`).

**Q:** `default` method conflict?  
**A:** Implementing class must override and choose (`A.super.m()`).

**Q:** Can interface extend class?  
**A:** No. Interface extends interfaces; class implements interfaces.

**Q:** Abstract method in non-abstract class?  
**A:** Compile error — class must be abstract.

**Q:** Why interfaces after Java 8 still useful if they have method bodies?  
**A:** Multiple inheritance of type + evolution of APIs without breaking implementors (`default`).

Next: [Access modifiers](03-dsa-and-interview/oops/access-modifiers.md)
