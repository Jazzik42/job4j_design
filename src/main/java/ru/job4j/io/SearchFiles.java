package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;


public class SearchFiles extends SimpleFileVisitor<Path> {
    private List<Path> paths = new ArrayList<>();
    private Predicate<Path> predicate;

    public SearchFiles(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (predicate.test(file)) {
            paths.add(file);
        }
        return CONTINUE;
    }

    public List<Path> getPaths() {
        return paths;
    }
}
