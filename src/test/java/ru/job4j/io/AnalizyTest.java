package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void when1test() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(
                new FileOutputStream(source.getAbsolutePath())))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println();
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(),
                target.getAbsolutePath());
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            bf.lines().forEach(x -> builder.append(x).append(System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(builder.toString(), is("10:57:01 ; 10:59:01"
                + System.lineSeparator()
                + "11:01:02 ; 11:02:02"
                + System.lineSeparator()));
    }

    @Test
    public void when2test() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(
                new FileOutputStream(source.getAbsolutePath())))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println();
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("400 11:02:02");
            out.println("400 11:04:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(),
                target.getAbsolutePath());
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            bf.lines().forEach(x -> builder.append(x).append(System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(builder.toString(), is("10:57:01 ; 10:59:01"
                + System.lineSeparator()
                + "11:01:02 ; 11:04:02"
                + System.lineSeparator()));
    }
}