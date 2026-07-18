package oops.examples;

/**
 * Method hiding: static methods bind to the reference type, not the object type.
 * Compare with instance method overriding.
 */
public class MethodHiding {

    static class Logger {
        static void log(String msg) {
            System.out.println("parent: " + msg);
        }

        void instanceLog(String msg) {
            System.out.println("parent-instance: " + msg);
        }
    }

    static class FileLogger extends Logger {
        static void log(String msg) {
            System.out.println("file: " + msg);
        }

        @Override
        void instanceLog(String msg) {
            System.out.println("file-instance: " + msg);
        }
    }

    public static void main(String[] args) {
        Logger ref = new FileLogger();

        ref.log("hi");          // parent: hi  (static → reference type)
        FileLogger.log("hi");   // file: hi

        ref.instanceLog("hi");  // file-instance: hi  (override → object type)
    }
}
