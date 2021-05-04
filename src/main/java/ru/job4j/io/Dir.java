package ru.job4j.io;

import java.io.File;
import java.sql.SQLOutput;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:/Users/IdeaProjects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exists %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subFile : file.listFiles()) {
            System.out.printf("%s %d%n", subFile.getName(), subFile.length());
        }
    }
}
