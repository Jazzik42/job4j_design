package ru.job4j.it;

import org.junit.Test;
import org.hamcrest.Matchers;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.is;


public class BackwardArrayItTest {
    @Test
    public void testHasNext() {
        int[] data = new int[] {1, 2, 3};
        BackwardArrayIt bachArrIt = new BackwardArrayIt(data);
        assertThat(bachArrIt.hasNext(), is(true));
        assertThat(bachArrIt.hasNext(), is(true));

    }
    @Test
    public void whenReadSequence() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {1, 2, 3}
        );
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {}
        );
        it.next();
    }
}

