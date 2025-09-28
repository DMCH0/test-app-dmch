package com.testapp.helper;

import java.io.IOException;

public class PropertyReader {

    public static final String PATH_TO_CONFIG = "config.properties";

    public static String readProperty(String configPath, String propertyName) {
        String result = System.getProperty(propertyName, null);
        if ((result) != null && !result.isEmpty()) {
            return result;
        }
        result = System.getenv(propertyName);
        if ((result) != null && !result.isEmpty()) {
            return result;
        }

        try {
            System.getProperties().load(ClassLoader.getSystemResourceAsStream(configPath));
        } catch (IOException io) {
            io.getCause();
        }
        return System.getProperty(propertyName);
    }
}
