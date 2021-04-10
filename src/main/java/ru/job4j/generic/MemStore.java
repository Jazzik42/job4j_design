package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MemStore<T extends Base> implements Store<T> {
    private List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
    mem.add(model);
    }

    @Override
    public void replace(String id, T model) {
    mem.set(mem.indexOf(findById(id)), model);
    }

    @Override
    public void delete(String id) {
    mem.remove(findById(id));
    }

    @Override
    public T findById(String id) {
        for (T mems : mem) {
            if (mems.getId().equals(id)) {
                return mems;
            }
        }
        throw new NoSuchElementException();
    }
}
