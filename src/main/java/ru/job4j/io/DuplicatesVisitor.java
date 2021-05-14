package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, Long> map = new HashMap<>();
    private List<String> outList = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(
                Files.size(file), file.toFile().getName());
        if (map.putIfAbsent(fileProperty, 1L) != null) {
            outList.add(file.toString());
        }
        return CONTINUE;
    }

    public List<String> getList() {
        return outList;
    }

    private class FileProperty {
        private long size;
        private String name;

        private FileProperty(long size, String name) {
            this.size = size;
            this.name = name;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getSize() {
            return size;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            FileProperty that = (FileProperty) o;
            return size == that.size
                    && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(size, name);
        }
    }
}
