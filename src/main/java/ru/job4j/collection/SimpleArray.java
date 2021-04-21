package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int size = 0;
    private int modCount = 0;

    public SimpleArray() {
        array = new Object[10];
    }

    public SimpleArray(int initialCapacity) {
        array = new Object[initialCapacity];
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        int i = Objects.checkIndex(index, size);
        return (T) array[i];
    }

    public void add(T model) {
        if (size == array.length) {
            array = grow();
        }
        array[size++] = model;
        modCount++;
    }

    private Object[] grow() {
        array = Arrays.copyOf(array, array.length + (array.length >> 1));
        return array;
    }

    @Override
    public Iterator<T> iterator() {
        class SimpleArrayIterator implements Iterator<T> {
            private final int itModCount = modCount;
            private int iteratorPos = 0;

            @Override
            public boolean hasNext() {
                if (itModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return iteratorPos < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[iteratorPos++];
            }
        }
        return new SimpleArrayIterator();
    }
}