package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {
    private final int[] numbers;
    private int positionOne = 0;
    private int positionTwo = 0;

    public EvenIterator(final int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
       int a = nextIterator(positionOne);
        positionOne = a + 1;
        return a != -1;
    }

    @Override
    public Integer next() {
        int a = nextIterator(positionTwo);
        if (a == -1) {
            throw new NoSuchElementException();
        }
        positionTwo = a + 1;
        return  numbers[a];
    }

    public int nextIterator(int position) {
        int a = -1;
        if (position < numbers.length) {
            for (int i = position; i < numbers.length; i++) {
                if (numbers[i] % 2 == 0) {
                    a = i;
                    break;
                }
                }
            }
        return a;
    }
}
