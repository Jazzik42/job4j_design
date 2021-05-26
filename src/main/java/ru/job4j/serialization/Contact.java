package ru.job4j.serialization;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(1234, "+7891011");
        File fileContact = Files.createTempFile(null, null).toFile();
        try (ObjectOutputStream objectOutContact = new ObjectOutputStream(
                new FileOutputStream(fileContact))) {
            objectOutContact.writeObject(contact);

            try (ObjectInputStream objectInContact = new ObjectInputStream(
                    new FileInputStream(fileContact)
            )) {
                Contact contactRead = (Contact) objectInContact.readObject();
                System.out.println(contactRead);
            }
        }
    }
}
