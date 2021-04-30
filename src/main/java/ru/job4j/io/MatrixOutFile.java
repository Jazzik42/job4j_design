package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class MatrixOutFile {
    public static void main(String[] args) throws IOException {
        try (FileOutputStream fl = new FileOutputStream("result.txt")) {
            for (int i = 1; i < 11; i++) {
                for (int j = 1; j < 11; j++) {
                    fl.write((i * j + " ").getBytes());
                }
                fl.write("\n".getBytes());
            }

        }
    }
}
