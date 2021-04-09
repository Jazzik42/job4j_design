package ru.job4j.Array;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] data;
    private int indexArray = 0;
    public Iterator<T> iterator = null;

    public SimpleArray(int capacity) {
        data = new Object[capacity];
    }

    public void add(T model) {
        data[Objects.checkIndex(indexArray++, data.length)] = model;
       }

    public void set(int index, T model) {
        data[Objects.checkIndex(index, data.length)] = model;
    }

    public void remove(int index) {
        System.arraycopy(data, index + 1, data, index, data.length - index - 1);
        data[indexArray - 1] = null;
    indexArray--;
    }


    public Object get(int index) {
    return data[Objects.checkIndex(index, data.length)];
    }


    @Override
    public Iterator<T> iterator() {
    class SimpleArrayIterator implements Iterator<T> {

        private final Object[] data = SimpleArray.this.data;
        private int position = 0;

        @Override
        public boolean hasNext() {
            return position < data.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return  (T) data[position++];
        }
    }
    if (iterator == null) {
        iterator = new SimpleArrayIterator();
    }
    return iterator;
    }
}
