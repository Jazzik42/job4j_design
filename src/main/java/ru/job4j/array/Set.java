package ru.job4j.array;

public interface Set<T> extends Iterable<T> {
    boolean add(T value);
    boolean contains(T value);
}
