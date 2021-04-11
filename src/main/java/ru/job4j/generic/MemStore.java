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
        if (index != -1) {
            mem.set(index, model);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        T t = findById(id);
        if (t != null) {
            mem.remove(t);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (T mems : mem) {
            if (mems.getId().equals(id)) {
                return mems;
            }
        }
        return null;
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
