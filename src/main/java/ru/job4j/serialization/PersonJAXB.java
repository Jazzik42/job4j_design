package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonJAXB {

    @XmlAttribute
    private boolean sex;
    @XmlAttribute
    private int age;
    private ContactJAXB contact;
    @XmlElementWrapper
    @XmlElement(name = "status")
    private String[] statuses;

    public PersonJAXB() {
    }

    public PersonJAXB(boolean sex, int age, ContactJAXB contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public static void main(String[] args) throws Exception {
        final PersonJAXB person = new PersonJAXB(false, 30,
                new ContactJAXB("11-111"), "Worker", "Married");

        JAXBContext context = JAXBContext.newInstance(PersonJAXB.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String result;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            result = writer.getBuffer().toString();
            System.out.println(result);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            try (StringReader reader = new StringReader(result)) {
                PersonJAXB personJAXB = (PersonJAXB) unmarshaller.unmarshal(reader);
                System.out.println(personJAXB);
            }
        }
    }
}

