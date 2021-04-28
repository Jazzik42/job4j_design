package ru.job4j.array;

import org.junit.Test;
import ru.job4j.collection.SimpleTree;
import ru.job4j.collection.Tree;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleTreeTest {

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void whenAddParentFalse() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertFalse(tree.add(4, 3));
    }

    @Test
    public void whenAddChildFalse() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertFalse(tree.add(1, 2));
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7).isPresent(),
                is(false));
    }

    @Test
    public void whenIsBinaryTrue() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        tree.add(3, 5);
        assertTrue(tree.isBinary());
    }

    @Test
    public void whenIsBinaryFalse() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 6);
        tree.add(3, 4);
        tree.add(3, 5);
        assertFalse(tree.isBinary());
    }
}
