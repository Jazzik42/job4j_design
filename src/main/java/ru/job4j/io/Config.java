package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String conf = this.toString();
        for (String str : conf.split(System.lineSeparator())) {
            if (str.contains("=") && !str.startsWith("#")) {
                String[] arrayKeyValues = str.split("=");
                if (arrayKeyValues.length != 2) {
                    throw new IllegalArgumentException();
                }
                values.put(arrayKeyValues[0], arrayKeyValues[1]);
            }
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
          bf.lines().forEach(stringJoiner::add);
        } catch (Exception e) {
          e.printStackTrace();
        }
        return stringJoiner.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
