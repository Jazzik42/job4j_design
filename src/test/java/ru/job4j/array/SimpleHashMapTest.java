package ru.job4j.array;

import org.junit.Test;
import ru.job4j.collection.SimpleHashMap;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test
    public void whenConstructor() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        assertThat(map.getCapacity(), is(16));
    }

    @Test
    public void whenConstructorWithCapacity() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>(20);
        assertThat(map.getCapacity(), is(32));
    }

    @Test
    public void whenConstructorWithCapacityAndLoadFactor() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>(33, 0.80f);
        assertThat(map.getCapacity(), is(64));
        assertThat(map.getLoadFactor(), is(0.80f));
    }

    @Test
    public void whenInsertAndConstructorWithCapacityMapAndGet() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("One", 1);
        map.insert("Two", 2);
        map.insert("Three", 3);
        SimpleHashMap<String, Integer> result = new SimpleHashMap<>(map);
        assertThat(result.get("Two"), is(2));
    }

    @Test
    public void whenInsertFalse() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("One", 1);
        map.insert("Two", 2);
        map.insert("Two", 1);
        assertThat(map.get("Two"), is(1));
    }

    @Test
    public void whenInsertNull() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("One", 1);
        map.insert("Two", 2);
        map.insert(null, 1);
        assertThat(map.get(null), is(1));
        map.insert(null, 3);
        assertThat(map.get(null), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetNoSuchElementException() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("One", 1);
        map.insert("Two", 2);
        map.get("Three");
    }

    @Test
    public void whenDeleteTrue() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("One", 1);
        map.insert("Two", 2);
        assertTrue(map.delete("Two"));
        assertThat(map.getSize(), is(1));
    }

    @Test
    public void whenDeleteFalse() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("One", 1);
        map.insert("Two", 2);
        assertFalse(map.delete("Three"));
        assertThat(map.getSize(), is(2));
    }

    @Test
    public void whenResizeTest() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        map.insert(1, 1);
        map.insert(2, 1);
        map.insert(3, 1);
        map.insert(4, 1);
        map.insert(5, 1);
        map.insert(6, 1);
        map.insert(7, 1);
        map.insert(8, 1);
        map.insert(9, 1);
        map.insert(10, 1);
        map.insert(11, 1);
        map.insert(12, 1);
        map.insert(13, 1);
        assertThat(map.getSize(), is(13));
        assertThat(map.getCapacity(), is(32));
    }

    @Test
    public void whenResizeTestWithLoadFactor() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>(16, 0.5f);
        map.insert(1, 1);
        map.insert(2, 1);
        map.insert(3, 1);
        map.insert(4, 1);
        map.insert(5, 1);
        map.insert(6, 1);
        map.insert(7, 1);
        map.insert(8, 1);
        map.insert(9, 1);
        assertThat(map.getSize(), is(9));
        assertThat(map.getCapacity(), is(32));
    }

    @Test
    public void whenIterator() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        map.insert(1, 1);
        map.insert(2, 1);
        map.insert(3, 1);
        Iterator<SimpleHashMap.Node<Integer, Integer>> it = map.iterator();
        assertTrue(it.hasNext());
        assertNotNull(it.next());
        assertTrue(it.hasNext());
        assertNotNull(it.next());
        assertTrue(it.hasNext());
        assertNotNull(it.next());
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNoSuchElementException() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        Iterator<SimpleHashMap.Node<Integer, Integer>> it = map.iterator();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorConcurrentModificationException() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        map.insert(1, 1);
        Iterator<SimpleHashMap.Node<Integer, Integer>> it = map.iterator();
        map.insert(2, 1);
        it.next();
    }
}
