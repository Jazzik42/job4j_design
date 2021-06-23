package ru.job4j.filesearch;

import java.io.FileNotFoundException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SimpleVisitFile extends SimpleFileVisitor<Path> {
    private Predicate<Path> predicate;
    private List<String> files = new ArrayList<>();

    public SimpleVisitFile(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    public List<String> getList() throws FileNotFoundException {
        if (files.size() == 0) {
            throw new FileNotFoundException("File not found");
        }
        return files;
    }

    public FileVisitResult visitFile(Path path, BasicFileAttributes attr) {
        if (predicate.test(path)) {
            files.add(path.toString());
        }
        return CONTINUE;
    }
}
