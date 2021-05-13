package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Files;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DuplicatesVisitorTest {
    File dir1;
    File dir12;
    File dir13;
    File dir14;
    File file1;
    File file2;
    File file3;
    File file4;
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    @Rule
    public TemporaryFolder tF = new TemporaryFolder();

    @Before
    public void initialFile() throws IOException {
        dir1 = tF.newFolder("dir1");
        dir12 = tF.newFolder("dir1", "x", "a");
        dir13 = tF.newFolder("dir1", "y");
        dir14 = tF.newFolder("dir1", "z", "c");

        file1 = tF.newFile("dir1/ABC.txt");
        file2 = tF.newFile("dir1/x/a/ABC.txt");
        file3 = tF.newFile("dir1/y/ABC.txt");
        file4 = tF.newFile("dir1/z/c/ABC.txt");
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(System.out);
    }

    @Test
    public void whenTwoDuplicate() throws IOException {
        try (PrintWriter pr1 = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(file1)));
             PrintWriter pr2 = new PrintWriter(
                     new BufferedOutputStream(new FileOutputStream(file2)));
             PrintWriter pr3 = new PrintWriter(
                     new BufferedOutputStream(new FileOutputStream(file3)));
             PrintWriter pr4 = new PrintWriter(
                     new BufferedOutputStream(new FileOutputStream(file4)))
        ) {
            pr1.write("abc");
            pr2.write("abc");
            pr3.write("abc");
            pr4.write("abcde");
        } catch (Exception e) {
            e.printStackTrace();
        }
        DuplicatesVisitor dupVis = new DuplicatesVisitor();
        Files.walkFileTree(dir1.toPath(), dupVis);
        assertThat(output.toString(), is(
                "Map contains a duplicate file:"
                        + file2.getAbsolutePath()
                        + System.lineSeparator()
                        + "Map contains a duplicate file:"
                        + file3.getAbsolutePath() + System.lineSeparator()
        ));
    }

    @Test
    public void whenNotDuplicate() throws IOException {
        try (PrintWriter pr1 = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(file1)));
             PrintWriter pr2 = new PrintWriter(
                     new BufferedOutputStream(new FileOutputStream(file2)));
             PrintWriter pr3 = new PrintWriter(
                     new BufferedOutputStream(new FileOutputStream(file3)));
             PrintWriter pr4 = new PrintWriter(
                     new BufferedOutputStream(new FileOutputStream(file4)))
        ) {
            pr1.write("abC");
            pr2.write("abcf");
            pr3.write("A");
            pr4.write("abcde");
        } catch (Exception e) {
            e.printStackTrace();
        }
        DuplicatesVisitor dupVis = new DuplicatesVisitor();
        Files.walkFileTree(dir1.toPath(), dupVis);
        assertThat(output.toString(), is(""));
    }
}
