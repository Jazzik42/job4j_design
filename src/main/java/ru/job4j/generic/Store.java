package ru.job4j.generic;

public interface Store<T extends Base> {

    void add(T model);

    void replace(String id, T model);

    void delete(String id);

    T findById(String id);

    int indexById(String id);
}
