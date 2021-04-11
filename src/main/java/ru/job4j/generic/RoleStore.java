package ru.job4j.generic;

public class RoleStore implements Store<Role> {
    private final Store<Role> mem = new MemStore<>();

    @Override
    public void add(Role model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
         return mem.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return mem.delete(id);
    }

    @Override
    public Role findById(String id) {
        return mem.findById(id);
    }

    @Override
    public int indexById(String id) {
        return mem.indexById(id);
    }
}
