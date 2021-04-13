package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] data;
    private int indexArray = 0;

    public SimpleArray(int capacity) {
        data = new Object[capacity];
    }

    public void add(T model) {
        data[Objects.checkIndex(indexArray++, data.length)] = model;
    }

    public void set(int index, T model) {
        data[Objects.checkIndex(index, indexArray)] = model;
    }

    public void remove(int index) {
        int id = Objects.checkIndex(index, indexArray);
        System.arraycopy(data, id + 1, data, id, data.length - id - 1);
        data[indexArray - 1] = null;
        indexArray--;
    }

    public Object get(int index) {
        return data[Objects.checkIndex(index, indexArray)];
    }

    @Override
    public Iterator<T> iterator() {
        class SimpleArrayIterator implements Iterator<T> {
            private final Object[] data = SimpleArray.this.data;
            private int position = 0;

            @Override
            public boolean hasNext() {
                return position < indexArray;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) data[position++];
            }
        }
        return new SimpleArrayIterator();
    }
}
