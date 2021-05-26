package ru.job4j.serialization;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class User {
    public String name;
    public boolean married;
    public int age;
    public int[] accounts;
    public ContactV2 contact;

    public User(String name, boolean married, int age, int[] accounts, ContactV2 contact) {
        this.name = name;
        this.married = married;
        this.age = age;
        this.accounts = accounts;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", married=" + married
                + ", age=" + age
                + ", accounts=" + Arrays.toString(accounts)
                + ", contact=" + contact
                + '}';
    }

    public static void main(String[] args) {
        User user = new User("Alewa", true, 32, new int[] {1, 2, 3},
                new ContactV2("123455"));
        Gson gson = new GsonBuilder().create();
        String userJs = gson.toJson(user);
        System.out.println(userJs);
        User userDeserial = gson.fromJson(userJs, User.class);
        System.out.println(userDeserial);
    }
}
