public class LoggerTest {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("Starting the application...");
        logger2.log("This should come from the same logger instance.");

        System.out.println("logger1 == logger2? " + (logger1 == logger2));
    }
}
