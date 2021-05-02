package ru.job4j.io;

import java.io.*;
import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class LogFilter {
    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            list = bf.lines().filter(x -> {
                String[] str = x.split(" ");
                return str[str.length - 2].equals("404");
            }).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter pr = new PrintWriter(new BufferedOutputStream(
                new FileOutputStream(file)))) {
            for (String st : log) {
                pr.write(st + System.lineSeparator());
            }
        } catch (Exception e) {
          e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
        save(log, "404.txt");
    }
}
