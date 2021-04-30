package ru.job4j.array;

import org.junit.Test;
import ru.job4j.collection.Analize;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {

    @Test
    public void whenNotChangesDeletedAdded() {
        Analize analize = new Analize();
        Analize.User user1 = new Analize.User(1, "Name1");
        Analize.User user2 = new Analize.User(2, "Name2");
        Analize.User user3 = new Analize.User(3, "Name3");
        List<Analize.User> previous = new ArrayList<>(List.of(user1, user2, user3));
        List<Analize.User> current = new ArrayList<>(List.of(user1, user2, user3));
        assertThat(analize.diff(previous, current).toString(),
                is((new Analize.Info(0, 0, 0)).toString()));
    }

    @Test
    public void whenChange() {
        Analize analize = new Analize();
        Analize.User user1 = new Analize.User(1, "Name1");
        Analize.User user2 = new Analize.User(2, "Name2");
        Analize.User user3 = new Analize.User(3, "Name3");
        List<Analize.User> previous = new ArrayList<>(List.of(user1, user2, user3));
        Analize.User user3changed = new Analize.User(3, "Name5");
        List<Analize.User> current = new ArrayList<>(List.of(user1, user2, user3changed));
        assertThat(analize.diff(previous, current),
                is(new Analize.Info(0, 1, 0)));
    }

    @Test
    public void whenDeleted() {
        Analize analize = new Analize();
        Analize.User user1 = new Analize.User(1, "Name1");
        Analize.User user2 = new Analize.User(2, "Name2");
        Analize.User user3 = new Analize.User(3, "Name3");
        List<Analize.User> previous = new ArrayList<>(List.of(user1, user2, user3));
        List<Analize.User> current = new ArrayList<>(List.of(user1, user2));
        assertThat(analize.diff(previous, current),
                is(new Analize.Info(0, 0, 1)));
    }

    @Test
    public void whenAdded() {
        Analize analize = new Analize();
        Analize.User user1 = new Analize.User(1, "Name1");
        Analize.User user2 = new Analize.User(2, "Name2");
        Analize.User user3 = new Analize.User(3, "Name3");
        List<Analize.User> previous = new ArrayList<>(List.of(user1, user2, user3));
        Analize.User user4 = new Analize.User(4, "Name4");
        List<Analize.User> current = new ArrayList<>(List.of(user1, user2, user3, user4));
        assertThat(analize.diff(previous, current),
                is(new Analize.Info(1, 0, 0)));
    }

    @Test
    public void whenDeletedAndAdded() {
        Analize analize = new Analize();
        Analize.User user1 = new Analize.User(1, "Name1");
        Analize.User user2 = new Analize.User(2, "Name2");
        Analize.User user3 = new Analize.User(3, "Name3");
        List<Analize.User> previous = new ArrayList<>(List.of(user1, user2, user3));
        Analize.User user4 = new Analize.User(4, "Name4");
        Analize.User user5 = new Analize.User(5, "Name5");
        List<Analize.User> current = new ArrayList<>(List.of(user4, user5));
        assertThat(analize.diff(previous, current),
                is(new Analize.Info(2, 0, 3)));
    }

    @Test
    public void whenDeletedAndChanged() {
        Analize analize = new Analize();
        Analize.User user1 = new Analize.User(1, "Name1");
        Analize.User user2 = new Analize.User(2, "Name2");
        Analize.User user3 = new Analize.User(3, "Name3");
        List<Analize.User> previous = new ArrayList<>(List.of(user1, user2, user3));
        Analize.User user3changed = new Analize.User(3, "Name5");
        List<Analize.User> current = new ArrayList<>(List.of(user2, user3changed));
        assertThat(analize.diff(previous, current),
                is(new Analize.Info(0, 1, 1)));
    }

    @Test
    public void whenChangedAndAdded() {
        Analize analize = new Analize();
        Analize.User user1 = new Analize.User(1, "Name1");
        Analize.User user2 = new Analize.User(2, "Name2");
        Analize.User user3 = new Analize.User(3, "Name3");
        List<Analize.User> previous = new ArrayList<>(List.of(user1, user2, user3));
        Analize.User user4 = new Analize.User(4, "Name4");
        Analize.User user3changed = new Analize.User(3, "Name5");
        List<Analize.User> current = new ArrayList<>(List.of(user1, user2, user3changed, user4));
        assertThat(analize.diff(previous, current),
                is(new Analize.Info(1, 1, 0)));
    }

    @Test
    public void whenDeletedAddedChanged() {
        Analize analize = new Analize();
        Analize.User user1 = new Analize.User(1, "Name1");
        Analize.User user2 = new Analize.User(2, "Name2");
        Analize.User user3 = new Analize.User(3, "Name3");
        List<Analize.User> previous = new ArrayList<>(List.of(user1, user2, user3));
        Analize.User user1changed = new Analize.User(1, "Name2");
        Analize.User user4 = new Analize.User(4, "Name4");
        Analize.User user5 = new Analize.User(5, "Name5");
        List<Analize.User> current = new ArrayList<>(List.of(user1changed, user4, user5));
        assertThat(analize.diff(previous, current),
                is(new Analize.Info(2, 1, 2)));
    }

    @Test
    public void whenAdd() {
        Analize analize = new Analize();
        List<Analize.User> previous = new ArrayList<>();
        Analize.User user1 = new Analize.User(1, "Name2");
        List<Analize.User> current = new ArrayList<>(List.of(user1));
        assertThat(analize.diff(previous, current),
                is(new Analize.Info(1, 0, 0)));
    }
}
