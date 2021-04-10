package ru.job4j.generic;

public class RoleStore implements Store<Role> {
    private final Store<Role> mem = new MemStore<>();

    @Override
    public void add(Role model) {
        mem.add(model);
    }

    @Override
    public void replace(String id, Role model) {
    mem.replace(id, model);
    }

    @Override
    public void delete(String id) {
    mem.delete(id);
    }

    @Override
    public Role findById(String id) {
        return mem.findById(id);
    }
}
