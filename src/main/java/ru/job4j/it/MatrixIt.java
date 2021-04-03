package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean a = true;

        for (int j = 0; j < data.length; j++) {
            if (row == data.length) {
                a = false;
                break;
            }
            if (data[row].length == 0) {
                row++;
                a = false;
                continue;
            }
            if (column < data[row].length) {
                    a = true;
                    break;
                } else {
                    row++;
                    column = 0;
                }
            }
        return a;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}