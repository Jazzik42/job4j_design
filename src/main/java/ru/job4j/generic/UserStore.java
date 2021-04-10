package ru.job4j.generic;

public class UserStore implements Store<User> {
    private final Store<User> mem = new MemStore<>();

    @Override
    public void add(User model) {
        mem.add(model);
    }

    @Override
    public void replace(String id, User model) {
        mem.replace(id, model);
    }

    @Override
    public void delete(String id) {
        mem.delete(id);
    }

    @Override
    public User findById(String id) {
        return mem.findById(id);
    }

    @Override
    public int indexById(String id) {
        return mem.indexById(id);
    }
}
