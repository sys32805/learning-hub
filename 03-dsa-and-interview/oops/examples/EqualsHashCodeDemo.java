package oops.examples;

/** equals/hashCode contract for use in HashMap/HashSet. */
public class EqualsHashCodeDemo {

    static final class UserId {
        private final String value;

        UserId(String value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof UserId)) {
                return false;
            }
            return value.equals(((UserId) o).value);
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public static void main(String[] args) {
        UserId a = new UserId("u-1");
        UserId b = new UserId("u-1");
        UserId c = new UserId("u-2");

        System.out.println(a.equals(b));                 // true
        System.out.println(a.hashCode() == b.hashCode()); // true
        System.out.println(a.equals(c));                 // false
    }
}
