package ru.job4j.filesearch;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsInit {
    private static final Pattern PATTERN = Pattern.compile("-\\w+=.+");
    private static final Map<String, String> VALUES = new HashMap<>();

    public static Map<String, String> init(String[] args) throws IllegalArgumentException {
        VALUES.put("d", "null");
        VALUES.put("n", "null");
        VALUES.put("t", "null");
        VALUES.put("o", "null");
        if (args.length != 4) {
            throw new IllegalArgumentException("Enter arguments");
        }
        for (String arg : args) {
            Matcher mt = PATTERN.matcher(arg);
            if (!mt.matches()) {
                throw new IllegalArgumentException("Enter valid arguments");
            }
            String[] buff = arg.split("[-=]");
            if (VALUES.computeIfPresent(buff[1], (key, value) -> value = buff[2]) == null) {
                throw new IllegalArgumentException("Enter valid arguments");
            }
        }
        return VALUES;
    }
}
