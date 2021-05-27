package ru.job4j.serialization;

public class ContactV2 {
    private final String phone;

    public ContactV2(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
                + '}';
    }
}

