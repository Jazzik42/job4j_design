package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenValue() {
        URL paths = ClassLoader.getSystemResource("app.properties");
        Config config = new Config(paths.getPath());
        config.load();
        assertThat(config.value("hibernate.connection.url"),
                is("jdbc:postgresql://localhost:5432/idea_db"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenE() throws IOException {
        File fileConfig = folder.newFile("config");
        try (PrintWriter pr = new PrintWriter(new FileWriter(fileConfig))) {
            pr.println("23=Ivanov");
            pr.println("24=Mama");
            pr.println();
            pr.println("25=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Config config = new Config(fileConfig.getPath());
        config.load();
        config.value("25");
    }

    @Test
    public void whenPairWithoutComment() {
        URL paths = ClassLoader.getSystemResource("config");
        Config config = new Config(paths.getPath());
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
    }
}