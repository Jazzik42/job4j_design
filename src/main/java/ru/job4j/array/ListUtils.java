package ru.job4j.array;

import java.util.List;
import java.util.function.Predicate;
import java.util.*;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.previousIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
            if (!i.hasNext()) {
                i.add(value);
            }
        }
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (filter.test(it.next())) {
                it.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (filter.test(it.next())) {
                it.set(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (elements.contains(it.next())) {
                it.remove();
            }
        }
    }
}