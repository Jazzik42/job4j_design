package ru.job4j.array;

import org.junit.Test;
import ru.job4j.collection.SimpleArray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenAddThenGet() {
        SimpleArray<Integer> sA = new SimpleArray<>(2);
        sA.add(1);
        sA.add(2);
        sA.add(3);
        assertThat(sA.get(2), is(3));
    }

    @Test
    public void whenArrayLengthCheck() {
        SimpleArray<Integer> sA = new SimpleArray<>(2);
        sA.add(1);
        sA.add(2);
        sA.add(3);
        assertThat(sA.size(), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleArray<String> array = new SimpleArray<>();
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetException() {
        SimpleArray<Integer> sA = new SimpleArray<>(2);
        sA.add(1);
        sA.add(2);
        sA.add(3);
        sA.get(4);
    }

    @Test
    public void whenIterator() {
        SimpleArray<Integer> sA = new SimpleArray<>(2);
        sA.add(1);
        sA.add(2);
        sA.add(3);
        Iterator<Integer> it = sA.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(1));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(2));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(3));
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNoSuchElementExp() {
        SimpleArray<Integer> sA = new SimpleArray<>(2);
        sA.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorConcurrentModificationException() {
        SimpleArray<Integer> sA = new SimpleArray<>(2);
        sA.add(1);
        Iterator<Integer> it = sA.iterator();
        sA.add(2);
        it.next();
    }
}