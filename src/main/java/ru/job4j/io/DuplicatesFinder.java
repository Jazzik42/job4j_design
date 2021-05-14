package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor dV = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), dV);
        for (String file : dV.getList()) {
            System.out.println(file);
        }
    }
}
