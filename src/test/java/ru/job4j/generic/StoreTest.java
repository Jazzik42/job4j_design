package ru.job4j.generic;


import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StoreTest {

    @Test
    public void getIdRole() {
        Role roleOne = new Role("1");
        Role roleTwo = new Role("2");
        assertThat(roleOne.getId(), is("1"));
        assertThat(roleTwo.getId(), is("2"));
    }

    @Test
    public void whenRoleAddAndFindByIdRoleStore() {
        RoleStore container = new RoleStore();
        Role roleOne = new Role("1");
        Role roleTwo = new Role("2");
        container.add(roleOne);
        container.add(roleTwo);
        assertThat(container.findById("1"), is(roleOne));
    }

    @Test
    public void whenUserAddAndFindByIdUserStore() {
        UserStore container = new UserStore();
        User userOne = new User("1");
        User userTwo = new User("2");
        container.add(userOne);
        container.add(userTwo);
        assertThat(container.findById("1"), is(userOne));
    }

    @Test
    public void whenUserReplaceUserStore() {
        UserStore container = new UserStore();
        User userOne = new User("1");
        User userTwo = new User("2");
        container.add(userOne);
        container.add(userTwo);
        User replace = new User("3");
        container.replace("2", replace);
        assertThat(container.findById("3"), is(replace));
    }

    @Test
    public void whenRoleReplaceRoleStore() {
        RoleStore container = new  RoleStore();
        Role roleOne = new Role("1");
        Role roleTwo = new Role("2");
        container.add(roleOne);
        container.add(roleTwo);
        Role replace = new Role("3");
        container.replace("2", replace);
        assertThat(container.findById("3"), is(replace));
    }

    @Test
    public void whenRoleDeleteRoleStore() {
        RoleStore container = new  RoleStore();
        Role roleOne = new Role("1");
        Role roleTwo = new Role("2");
        container.add(roleOne);
        container.add(roleTwo);
        Role replace = new Role("3");
        container.replace("2", replace);
        assertThat(container.findById("3"), is(replace));
    }

    @Test
    public void whenUserDeleteUserStore() {
        UserStore container = new UserStore();
        User userOne = new User("1");
        User userTwo = new User("2");
        container.add(userOne);
        container.add(userTwo);
        User replace = new User("3");
        container.replace("2", replace);
        assertThat(container.findById("3"), is(replace));
    }

    @Test
    public void whenUserFindByIdUserStore() {
        UserStore container = new UserStore();
        User userOne = new User("1");
        User userTwo = new User("2");
        container.add(userOne);
        container.add(userTwo);
        assertThat(container.findById("2"), is(userTwo));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenUserFindByIdFalseUserStore() {
        UserStore container = new UserStore();
        User userOne = new User("1");
        User userTwo = new User("2");
        container.add(userOne);
        container.add(userTwo);
        container.findById("3");
    }

    @Test
    public void whenRoleFindByIdUserStore() {
        RoleStore container = new RoleStore();
        Role roleOne = new Role("1");
        Role roleTwo = new Role("2");
        container.add(roleOne);
        container.add(roleTwo);
        assertThat(container.findById("2"), is(roleTwo));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenRoleFindByIdFalseUserStore() {
        RoleStore container = new RoleStore();
        Role roleOne = new Role("1");
        Role roleTwo = new Role("2");
        container.add(roleOne);
        container.add(roleTwo);
        container.findById("3");
    }

    @Test
    public void whenRoleIndexById() {
        RoleStore container = new RoleStore();
        Role roleOne = new Role("1");
        Role roleTwo = new Role("2");
        container.add(roleOne);
        container.add(roleTwo);
        assertThat(container.indexById("1"), is(0));
    }

    @Test
    public void whenRoleIndexByIdFalse() {
        RoleStore container = new RoleStore();
        Role roleOne = new Role("1");
        Role roleTwo = new Role("2");
        container.add(roleOne);
        container.add(roleTwo);
        assertThat(container.indexById("3"), is(-1));
    }
}