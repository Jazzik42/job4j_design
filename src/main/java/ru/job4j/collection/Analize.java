package ru.job4j.collection;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        HashMap<Integer, Long> changeMap = Stream.concat(previous.stream(), current.stream()).distinct()
                .collect(
                        Collectors.groupingBy(
                                x -> x.id, HashMap::new, Collectors.counting()));
        long countChanged = changeMap.values().stream().filter(x -> x > 1).count();
        int changed = (int) countChanged;
        for (User user : previous) {
            changeMap.remove(user.id);
        }
        int added = changeMap.size();
        int deleted = added + previous.size() - current.size();
        return new Info(added, changed, deleted);
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added
                    && changed == info.changed
                    && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }
    }
}
