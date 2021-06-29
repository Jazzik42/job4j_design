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


public class MainClass {
    private Map<String, String> values = new HashMap<>();
    private List<String> paths;

    private void search(Path path) throws IOException {
        Predicate<Path> predicate = ArgsValidation.validation(values.get("n"), values.get("t"));
        SimpleVisitFile sV = new SimpleVisitFile(predicate);
        Files.walkFileTree(path, sV);
        paths = sV.getList();
    }

    private void logWrite() {
        try (BufferedWriter bW = new BufferedWriter(new FileWriter(this.values.get("o")))) {
            for (String str : this.paths) {
                bW.write(str);
                bW.newLine();
            }
        } catch (IOException a) {
            a.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MainClass searcher = new MainClass();
        searcher.values = ArgsInit.init(args);
        Path path = Paths.get(searcher.values.get("d"));
        try {
            searcher.search(path);
            searcher.logWrite();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

