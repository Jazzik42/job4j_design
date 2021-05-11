package ru.job4j.io;

import java.io.*;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Analizy {
    private List<String> log = new ArrayList<>();

    public void unavailable(String source, String target) {
        try (BufferedReader bf = new BufferedReader(new FileReader(source))) {
            StringBuilder sb = new StringBuilder();
            String lineBuffer = null;
            for (String line = bf.readLine(); line != null; line = bf.readLine()) {
                if (!line.equals("")) {
                    lineBuffer = line;
                    if (sb.length() == 0 && (line.split(" ")[0].equals("400")
                            || line.split(" ")[0].equals("500"))) {
                        sb.append(line.split(" ")[1]).append(" ; ");
                    }
                    if (sb.length() != 0 && (!line.split(" ")[0].equals("400")
                            && !line.split(" ")[0].equals("500"))) {
                        sb.append(line.split(" ")[1]);
                        log.add(sb.toString());
                        sb = new StringBuilder();
                    }
                }
            }
            if (sb.length() != 0 && (Objects.requireNonNull(lineBuffer).split(" ")[0].equals("400")
                    || lineBuffer.split(" ")[0].equals("500"))) {
                sb.append(lineBuffer.split(" ")[1]);
                log.add(sb.toString());
            }
            writeStats(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeStats(String target) {
        try (FileWriter pr = new FileWriter(target, true)) {
            for (String line : log) {
                pr.append(line).append(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("./src/main/resources/unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
