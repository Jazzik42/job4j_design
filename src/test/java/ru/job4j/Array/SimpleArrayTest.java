package ru.job4j.Array;

import org.junit.Before;
import org.junit.Test;
import org.hamcrest.Matchers;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddException() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        simpleArray.add(2);
        simpleArray.add(7);
        simpleArray.add(6);
        simpleArray.add(4);
    }

    @Test
    public void whenAddTrueInt() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        simpleArray.add(2);
        simpleArray.add(7);
        simpleArray.add(6);
        assertThat(simpleArray.get(2), is(6));
    }

    @Test
    public void whenAddTrueString() {
        SimpleArray<String> simpleArray = new SimpleArray<>(3);
        simpleArray.add("Abc");
        simpleArray.add("Cba");
        simpleArray.add("Abcde");
        assertThat(simpleArray.get(2), is("Abcde"));
    }

    @Test
    public void whenRemove() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(6);
        simpleArray.add(2);
        simpleArray.add(7);
        simpleArray.add(6);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(4);
        simpleArray.remove(2);
        assertThat(simpleArray.get(2), is(1));
    }

    @Test
    public void whenGet() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        simpleArray.add(2);
        simpleArray.add(7);
        simpleArray.add(6);
        assertThat(simpleArray.get(2), is(6));
    }

    @Test
    public void whenSet() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        simpleArray.add(2);
        simpleArray.add(7);
        simpleArray.add(6);
        simpleArray.set(2, 4);
        assertThat(simpleArray.get(2), is(4));
    }

    @Test
    public void whenIteratorJob() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        simpleArray.add(2);
        simpleArray.add(7);
        simpleArray.add(6);
        assertThat(simpleArray.iterator().next(), is(2));
        assertThat(simpleArray.iterator().next(), is(7));
        assertThat(simpleArray.iterator().next(), is(6));
        assertFalse(simpleArray.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorException() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        simpleArray.add(2);
        simpleArray.add(7);
        simpleArray.add(6);
        simpleArray.iterator().next();
       simpleArray.iterator().next();
        simpleArray.iterator().next();
        simpleArray.iterator().next();
    }
}