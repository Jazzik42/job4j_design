package ru.job4j.serialization;

public class ContactOrgJson {
    private String phone;

    public ContactOrgJson() {
    }

    public ContactOrgJson(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
                + '}';
    }
}

