package ru.job4j.jdbc;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoadder {
    private Properties properties = new Properties();

    public void load(InputStream io) {
        try {
            properties.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getValues(String key) {
        return properties.getProperty(key);
    }
}
