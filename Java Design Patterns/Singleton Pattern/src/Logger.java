import java.io.ObjectStreamException;
import java.io.Serializable;

// Logger class implementing Singleton pattern
public class Logger implements Serializable, Cloneable {

    // Private static instance variable
    private static volatile Logger instance;

    // Private constructor to prevent instantiation
    private Logger() {
        // Optional: initialization code
    }

    // Public method to provide global access point
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) { // Double-checked locking
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    // Prevent cloning
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone singleton object");
    }

    // Ensure singleton during deserialization
    protected Object readResolve() throws ObjectStreamException {
        return getInstance();
    }

    // Example method for logging
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}
