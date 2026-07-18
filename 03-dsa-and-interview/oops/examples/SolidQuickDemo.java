package oops.examples;

/** Tiny SOLID sketches: OCP + DIP. */
public class SolidQuickDemo {

    interface Discount {
        double apply(double price);
    }

    static class FestivalDiscount implements Discount {
        public double apply(double price) {
            return price * 0.9;
        }
    }

    static class NoDiscount implements Discount {
        public double apply(double price) {
            return price;
        }
    }

    /** Depends on abstraction (DIP), open to new discounts (OCP). */
    static class Checkout {
        private final Discount discount;

        Checkout(Discount discount) {
            this.discount = discount;
        }

        double pay(double price) {
            return discount.apply(price);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Checkout(new FestivalDiscount()).pay(100)); // 90.0
        System.out.println(new Checkout(new NoDiscount()).pay(100));       // 100.0
    }
}
