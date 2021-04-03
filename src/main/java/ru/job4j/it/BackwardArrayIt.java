package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int position;
    private int count = 1;

    public BackwardArrayIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return position >= 0;
    }

    @Override
    public Integer next() {
        position = data.length - count++;
    if (!hasNext()) {
        throw new NoSuchElementException();
    } else {
        return data[position];
    }
    }
    }

