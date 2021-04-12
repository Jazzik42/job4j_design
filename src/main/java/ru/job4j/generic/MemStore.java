package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int index = this.indexById(id);
        if (index == -1) {
            return false;
        }
        mem.set(index, model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        int i = indexById(id);
        if (i == -1) {
            return false;
        }
        mem.remove(i);
        return true;
    }

    @Override
    public T findById(String id) {
        int i = indexById(id);
        if (i == -1) {
            return null;
        }
        return mem.get(i);
    }

    @Override
    public int indexById(String id) {
        int i = -1;
        for (int j = 0; j < mem.size(); j++) {
            if (mem.get(j).getId().equals(id)) {
                i = j;
                break;
            }
        }
        return i;
    }
}
