package ru.job4j.array;

import java.util.Iterator;

public interface List<E> extends Iterable<E> {

    void add(E value);

    E get(int index);

}
