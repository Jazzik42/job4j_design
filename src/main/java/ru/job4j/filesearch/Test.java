package ru.job4j.filesearch;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    private String mask;
    private String typeMask;
    private static final Map<String, String> VALUES = new HashMap<>();
    private static final Pattern PATTERN = Pattern.compile("-\\w+=.+");

    public Test(String mask, String typeMask) {
        this.mask = mask;
        this.typeMask = typeMask;
    }

    private Predicate<Path> validation() {
        Predicate<Path> predicate = null;
        if (typeMask.equals("name")) {
            predicate = x -> x.getFileName().toString().equals(mask);
        } else if (typeMask.equals("mask")) {
            mask = mask.replace(".", "\\.");
            mask = mask.replace("*", ".*");
            predicate = x -> x.getFileName().toString().matches(mask);
        } else if (typeMask.equals("regex")) {
            predicate = x -> x.toString().matches(mask);
        }
        if (predicate == null) {
            throw new IllegalArgumentException("Enter correct search arguments");
        }
        return predicate;
    }

    private static void init(String[] args) throws IllegalArgumentException {
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
    }

    public static void main(String[] args) {
        VALUES.put("d", "null");
        VALUES.put("n", "null");
        VALUES.put("t", "null");
        VALUES.put("o", "null");
        init(args);
        Test test = new Test(VALUES.get("n"), VALUES.get("t"));
        Path path = Paths.get(VALUES.get("d"));
        List<String> paths = null;
        try {
            Predicate<Path> predicate = test.validation();
            SimpleVisitFile sV = new SimpleVisitFile(predicate);
            Files.walkFileTree(path, sV);
            paths = sV.getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (BufferedWriter bW = new BufferedWriter(new FileWriter(VALUES.get("o")))) {
            for (String str : paths) {
                bW.write(str);
                bW.newLine();
            }
        } catch (IOException a) {
            a.printStackTrace();
        }

    }
}

