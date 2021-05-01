package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream fl = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = fl.read()) != -1) {
                text.append((char) read);
            }
            String[] textArray = text.toString().split(System.lineSeparator());
            for (String str : textArray) {
                if (Integer.parseInt(str) % 2 == 0) {
                    System.out.println(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
