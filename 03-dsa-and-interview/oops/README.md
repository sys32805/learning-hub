# OOPs home

Read theory on this site. Full Java demos are embedded below and also on GitHub.

## Pages

- [Advanced OOPs — understanding + code](03-dsa-and-interview/oops/04-advanced-oops-interview.md)
- [Basics Q&A](03-dsa-and-interview/oops/01-oops-basics.md)
- [Exceptions](03-dsa-and-interview/oops/02-exception-handling.md)
- [Multithreading](03-dsa-and-interview/oops/03-multithreading.md)

## Polymorphism demo (your code)

```java
package oops.examples;

import java.util.Arrays;
import java.util.List;

public class PolymorphismDemo {
    static class Shape {
        double area() { return 0; }
    }
    static class Circle extends Shape {
        private final double r;
        Circle(double r) { this.r = r; }
        @Override double area() { return Math.PI * r * r; }
    }
    static class Rectangle extends Shape {
        private final double w, h;
        Rectangle(double w, double h) { this.w = w; this.h = h; }
        @Override double area() { return w * h; }
    }
    public static void main(String[] args) {
        List<Shape> shapes = Arrays.asList(new Circle(2), new Rectangle(3, 4));
        for (Shape s : shapes) {
            System.out.println(s.getClass().getSimpleName() + " area = " + s.area());
        }
    }
}
```

**Takeaway:** `Shape` reference, `Circle`/`Rectangle` object → runtime picks the right `area()`.

More demos: [examples on GitHub](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/oops/examples)
