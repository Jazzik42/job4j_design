package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target.toString())))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(path.toString()))) {
                    zip.write(out.readAllBytes());
                    zip.closeEntry();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName aN = ArgsName.of(args);
        Path directorySource = Path.of(aN.get("d"));
        Predicate<Path> pr = x -> !x.toFile().getName().endsWith(aN.get("e"));
        Path directoryTarget = Path.of(aN.get("o"));
        List<Path> sources = Search.search(directorySource, pr);
        Zip zip = new Zip();
        zip.packFiles(sources, directoryTarget);
        new Zip().packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}
