package ru.job4j.io;

import java.io.*;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader bf = new BufferedReader(new FileReader(source))) {
            List<String> strLines = bf.lines().collect(Collectors.toList());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < strLines.size(); i++) {
                String s = strLines.get(i);
                if (!s.equals("")) {
                    if (sb.length() == 0 && (s.split(" ")[0].equals("400") || s.split(" ")[0].equals("500"))) {
                        sb.append(s.split(" ")[1]).append(" ; ");
                    }
                    if (sb.length() != 0 && (!s.split(" ")[0].equals("400") && !s.split(" ")[0].equals("500"))) {
                        sb.append(s.split(" ")[1]);
                        writeStats(target, sb.toString());
                        sb = new StringBuilder();
                    } else if (i == strLines.size() - 1) {
                        sb.append(s.split(" ")[1]);
                        writeStats(target, sb.toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeStats(String target, String line) {
        try (FileWriter pr = new FileWriter(target, true)) {
            pr.append(line).append(System.lineSeparator());
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
