package oops.examples;

/** Compile-time overloading vs runtime overriding. */
public class OverloadingVsOverriding {

    static class Calculator {
        int add(int a, int b) {
            return a + b;
        }

        double add(double a, double b) {
            return a + b;
        }
    }

    static class Printer {
        void print() {
            System.out.println("base printer");
        }
    }

    static class ColorPrinter extends Printer {
        @Override
        void print() {
            System.out.println("color printer");
        }
    }

    public static void main(String[] args) {
        Calculator c = new Calculator();
        System.out.println(c.add(1, 2));       // overload → int
        System.out.println(c.add(1.5, 2.5));   // overload → double

        Printer p = new ColorPrinter();
        p.print(); // override → color printer (runtime)
    }
}
