package oops.examples;

import java.util.Arrays;
import java.util.List;

/** Runtime polymorphism via dynamic dispatch. */
public class PolymorphismDemo {

    static class Shape {
        double area() {
            return 0;
        }
    }

    static class Circle extends Shape {
        private final double r;

        Circle(double r) {
            this.r = r;
        }

        @Override
        double area() {
            return Math.PI * r * r;
        }
    }

    static class Rectangle extends Shape {
        private final double w;
        private final double h;

        Rectangle(double w, double h) {
            this.w = w;
            this.h = h;
        }

        @Override
        double area() {
            return w * h;
        }
    }

    public static void main(String[] args) {
        List<Shape> shapes = Arrays.asList(new Circle(2), new Rectangle(3, 4));
        for (Shape s : shapes) {
            System.out.println(s.getClass().getSimpleName() + " area = " + s.area());
        }
    }
}
