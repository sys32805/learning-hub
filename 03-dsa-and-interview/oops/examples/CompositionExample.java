package oops.examples;

/** HAS-A composition: Car owns an Engine. */
public class CompositionExample {

    static class Engine {
        void start() {
            System.out.println("engine started");
        }
    }

    static class Car {
        private final Engine engine = new Engine();

        void start() {
            engine.start();
            System.out.println("car ready");
        }
    }

    public static void main(String[] args) {
        new Car().start();
    }
}
