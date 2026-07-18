# Advanced Java OOPs — understanding + code

Read each section in order. For every topic: **idea → why interviewers ask → code → takeaway**.

Related: [01-oops-basics.md](03-dsa-and-interview/oops/01-oops-basics.md) | [examples on GitHub](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/oops/examples)

---

## 1. Four pillars (with real meaning)

### Encapsulation — protect what changes

**Understanding:** Bundle data + methods in a class, and hide fields so callers cannot break object state. You expose *behavior* (`deposit`), not raw fields (`balance`).

**Why asked:** Shows you design safe APIs, not just “private = encapsulation”.

```java
public class BankAccount {
    private double balance; // hidden state

    public BankAccount(double opening) {
        if (opening < 0) throw new IllegalArgumentException("invalid");
        this.balance = opening;
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("amount");
        balance += amount; // only valid path to change state
    }

    public double getBalance() {
        return balance;
    }
}
```

**Takeaway:** Encapsulation = controlled access + invariants, not only `private`.

---

### Abstraction — show what, hide how

**Understanding:** Caller needs *what* to do (`pay`), not *how* (card network, OTP, retries).

```java
public interface PaymentGateway {
    boolean pay(double amount); // what
}

public class StripeGateway implements PaymentGateway {
    @Override
    public boolean pay(double amount) {
        // how: HTTP call, retries, logging — hidden from caller
        return amount > 0;
    }
}

// Client depends on abstraction
public class CheckoutService {
    private final PaymentGateway gateway;

    public CheckoutService(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public void checkout(double amount) {
        gateway.pay(amount);
    }
}
```

**Takeaway:** Abstraction reduces coupling; swap Stripe for Razorpay without changing `CheckoutService`.

---

### Inheritance — IS-A reuse (use carefully)

**Understanding:** Child *is a* parent. Good for shared structure; bad when forced (Fragile Base Class).

```java
public class Animal {
    public void eat() {
        System.out.println("eating");
    }
}

public class Dog extends Animal {
    public void bark() {
        System.out.println("bark");
    }
}

// Dog IS-A Animal
Animal a = new Dog();
a.eat();
```

**Takeaway:** Use inheritance only for true IS-A + stable base class.

---

### Polymorphism — one call, many behaviors

**Understanding:** Same method name/call site; runtime picks the right implementation (overriding) or compile time picks overload.

```java
public class Shape {
    public double area() { return 0; }
}

public class Circle extends Shape {
    private final double r;
    public Circle(double r) { this.r = r; }

    @Override
    public double area() { return Math.PI * r * r; }
}

public class Rectangle extends Shape {
    private final double w, h;
    public Rectangle(double w, double h) { this.w = w; this.h = h; }

    @Override
    public double area() { return w * h; }
}

// Runtime polymorphism
Shape s1 = new Circle(2);
Shape s2 = new Rectangle(3, 4);
System.out.println(s1.area()); // Circle
System.out.println(s2.area()); // Rectangle
```

**Takeaway:** Polymorphism removes `if (type == CIRCLE)` chains — Open/Closed friendly.

---

## 2. Encapsulation vs Abstraction

| | Encapsulation | Abstraction |
|---|---------------|-------------|
| Focus | Protect data / state | Hide complexity / show essentials |
| Tool | access modifiers, getters | interfaces, abstract classes |
| Question | “Who can touch this field?” | “What does the client need to know?” |

They work together: private fields (encapsulation) behind a small interface (abstraction).

---

## 3. Is Java 100% OOP?

**No.** Reasons:

- Primitives: `int`, `boolean` are not objects.
- `static` methods/fields belong to the class, not an instance.

Java is **object-oriented by design**, not “pure OOP” like Smalltalk.

---

## 4. Abstract class vs interface

**Understanding:**

- **Abstract class** = shared base + optional state + partial implementation.
- **Interface** = capability contract; a class can implement many.

```java
public abstract class Employee {
    protected String name;

    public Employee(String name) { this.name = name; }

    public abstract double salary(); // must implement

    public void printName() {       // shared concrete code
        System.out.println(name);
    }
}

public interface BonusEligible {
    double bonus();
}

public class PermanentEmployee extends Employee implements BonusEligible {
    public PermanentEmployee(String name) { super(name); }

    @Override
    public double salary() { return 50_000; }

    @Override
    public double bonus() { return salary() * 0.1; }
}
```

**When to choose**

- Need fields + constructor + shared code → **abstract class**
- Need multiple capabilities (`Serializable`, `Comparable`) → **interface**

---

## 5. Overloading vs overriding

```java
public class Calculator {
    // OVERLOADING — compile time (same name, different params)
    public int add(int a, int b) { return a + b; }
    public double add(double a, double b) { return a + b; }
}

public class Printer {
    public void print() { System.out.println("base"); }
}

public class ColorPrinter extends Printer {
    // OVERRIDING — runtime (same signature)
    @Override
    public void print() { System.out.println("color"); }
}

Printer p = new ColorPrinter();
p.print(); // "color" — dynamic dispatch
```

| | Overloading | Overriding |
|---|-------------|------------|
| Binding | Compile time | Runtime |
| Params | Must differ | Must match |
| Inheritance | Optional | Required |

Runnable demos: [OverloadingVsOverriding.java](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/oops/examples/OverloadingVsOverriding.java)

---

## 6. Method hiding (static)

Static methods are bound to the **reference type**, not the object type.

```java
class Logger {
    static void log(String msg) {
        System.out.println("parent: " + msg);
    }
}

class FileLogger extends Logger {
    static void log(String msg) {          // HIDDEN, not overridden
        System.out.println("file: " + msg);
    }
}

Logger ref = new FileLogger();
ref.log("hi");        // parent: hi  (reference is Logger)
FileLogger.log("hi"); // file: hi
```

See [MethodHiding.java](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/oops/examples/MethodHiding.java)

---

## 7. Composition vs inheritance

```java
// Inheritance (IS-A) — tight coupling
class EngineCar extends Engine { } // awkward: a car is not an engine

// Composition (HAS-A) — preferred for reuse
class Engine {
    void start() { System.out.println("engine on"); }
}

class Car {
    private final Engine engine = new Engine();

    void start() {
        engine.start();
        System.out.println("car ready");
    }
}
```

**Interview line:** Prefer composition unless there is a clear, stable IS-A hierarchy.

Demo: [CompositionExample.java](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/oops/examples/CompositionExample.java)

---

## 8. Diamond problem

```text
        A
       / \
      B   C
       \ /
        D
```

If `B` and `C` both override `A.method()`, which one does `D` get?

**Java classes:** cannot `extends` two classes → no diamond for classes.

**Java interfaces (default methods):** conflict must be resolved:

```java
interface A { default void hi() { System.out.println("A"); } }
interface B { default void hi() { System.out.println("B"); } }

class C implements A, B {
    @Override
    public void hi() {
        A.super.hi(); // you must choose
    }
}
```

---

## 9. Runtime polymorphism (dynamic dispatch)

JVM looks at the **actual object**, not the variable type, for overridden instance methods.

```java
List<Shape> shapes = List.of(new Circle(1), new Rectangle(2, 3));
for (Shape s : shapes) {
    System.out.println(s.area()); // each object's area()
}
```

This is the basis of Strategy / Template Method style design.

Demo: [PolymorphismDemo.java](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/oops/examples/PolymorphismDemo.java)

---

## 10. Access modifiers

```java
public class Demo {
    private int a;    // this class only
    int b;            // package
    protected int c;  // package + subclasses
    public int d;     // everywhere
}
```

**Takeaway:** Start with `private`; widen only when needed.

---

## 11. `this` and `super`

```java
class Person {
    private String name;

    Person(String name) {
        this.name = name; // field vs param
    }

    Person() {
        this("unknown"); // call another constructor
    }
}

class Employee extends Person {
    private String id;

    Employee(String name, String id) {
        super(name); // parent constructor first
        this.id = id;
    }
}
```

---

## 12. `final`

```java
final class MathUtils { }           // cannot extend

class Base {
    final void lock() { }           // cannot override
}

class Sample {
    final int MAX = 100;            // cannot reassign
}
```

---

## 13. Coupling & cohesion

- **High cohesion:** one class = one job (`OrderService` places orders only).
- **Low coupling:** depend on interfaces, not concrete classes.

Bad: `OrderService` directly `new MysqlOrderRepo()` and also sends email + SMS inside the same class.

Good: `OrderService` depends on `OrderRepository` + `Notifier` interfaces.

---

## 14. SOLID (with tiny examples)

### S — Single Responsibility

```java
// Bad: one class does persistence + email
// Good:
class OrderRepository { void save(Order o) { } }
class OrderMailer { void send(Order o) { } }
```

### O — Open/Closed

```java
interface Discount {
    double apply(double price);
}

class FestivalDiscount implements Discount {
    public double apply(double price) { return price * 0.9; }
}
// Add new discount types without editing old ones
```

### L — Liskov Substitution

Subclass must honor parent contract. Don’t make `Square extends Rectangle` if setting width also changes height — callers of `Rectangle` break.

### I — Interface Segregation

```java
// Bad
interface Worker { void work(); void eat(); }

// Good
interface Workable { void work(); }
interface Eatable { void eat(); }
```

### D — Dependency Inversion

```java
class OrderService {
    private final OrderRepository repo; // abstraction

    OrderService(OrderRepository repo) { // inject
        this.repo = repo;
    }
}
```

Demo: [SolidQuickDemo.java](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/oops/examples/SolidQuickDemo.java)

---

## 15. Association / Aggregation / Composition

```java
// Association: Student uses Course
// Aggregation: Department has Professors (professors can exist alone)
// Composition: House has Rooms (rooms don't exist without house)

class Room {
    String name;
    Room(String name) { this.name = name; }
}

class House {
    private final List<Room> rooms = new ArrayList<>();

    House() {
        rooms.add(new Room("Kitchen")); // owned by House
    }
}
```

---

## 16. Marker & functional interfaces

```java
// Marker — no methods; metadata for JVM/frameworks
public interface Serializable { }

// Functional — single abstract method (SAM) → lambdas
@FunctionalInterface
public interface Calculator {
    int calc(int a, int b);
}

Calculator add = (a, b) -> a + b;
```

---

## 17. Immutable class pattern

```java
public final class Money {
    private final String currency;
    private final long amountPaise;

    public Money(String currency, long amountPaise) {
        this.currency = currency;
        this.amountPaise = amountPaise;
    }

    public String getCurrency() { return currency; }
    public long getAmountPaise() { return amountPaise; }
    // no setters
}
```

**Why:** thread-safe sharing, predictable state (like `String`).

---

## 18. `equals` and `hashCode`

```java
public final class UserId {
    private final String value;

    public UserId(String value) { this.value = value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserId)) return false;
        return value.equals(((UserId) o).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
```

**Contract:** if `a.equals(b)` then `a.hashCode() == b.hashCode()`. Break this → broken `HashMap`/`HashSet`.

Demo: [EqualsHashCodeDemo.java](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/oops/examples/EqualsHashCodeDemo.java)

---

## 19. Interview red flags

| Weak answer | Stronger answer |
|-------------|-----------------|
| “Inheritance for all reuse” | Prefer composition; inherit for true IS-A |
| “Interfaces can’t have methods” | They can: `default` / `static` (Java 8+) |
| Mix overload / override | Overload = compile; override = runtime |
| “Java is 100% OOP” | No — primitives + static |

---

## 20. Prep checklist

- [ ] Explain four pillars with your own examples  
- [ ] Abstract class vs interface + when  
- [ ] Overload vs override + static hiding  
- [ ] Composition vs inheritance  
- [ ] SOLID one-liners + one code sketch  
- [ ] `equals`/`hashCode` contract  
- [ ] Why no multiple class inheritance  

Then open files under [examples on GitHub](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/oops/examples) and run them in IntelliJ.
