package ru.job4j.serialization;

import javax.xml.bind.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

@XmlElement(value = "contact")
public class ContactJAXB {

    @XmlAttribute
    private String phone;

    public ContactJAXB() {
    }

    public ContactJAXB(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
                + '}';
    }
}
