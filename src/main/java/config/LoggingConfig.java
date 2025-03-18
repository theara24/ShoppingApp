package config;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingConfig {
    private static final Logger LOGGER = Logger.getLogger("ShoppingAppLogger");

    static {
        try {
            FileHandler fileHandler = new FileHandler("shoppingapp.log", true); // Append mode
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.INFO);
            LOGGER.setUseParentHandlers(false); // Disable console logging by default
        } catch (IOException e) {
            System.err.println("Failed to initialize logging: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}