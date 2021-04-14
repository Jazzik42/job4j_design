package ru.job4j.array;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {
    private Node<E> first;
    private Node<E> last;
    private int size = 0;
    private int modCount = 0;

    private class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        private Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(E value) {
        if (size == 0) {
            first = new Node<>(null, value, null);
            last = first;
        } else {
            Node<E> lastItem = new Node<>(last, value, null);
            last.next = lastItem;
            last = lastItem;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        int i = Objects.checkIndex(index, size);
        Node<E> buffFirst = first;
        Node<E> buffLast = last;
        E item;
        if (i == 0) {
            item = first.item;
        } else if (i == size) {
            item = last.item;
        } else {
            int a = size / i < size / 2 ? i : size - i;
            if (a == i) {
                for (int j = 0; j < a; j++) {
                    buffFirst = buffFirst.next;
                }
                item = buffFirst.item;
            } else {
                for (int j = size; j > a; j--) {
                    buffLast = buffLast.prev;
                }
                item = buffLast.item;
            }
        }
        return item;
    }

    @Override
    public Iterator<E> iterator() {
        class LinkedListIterator implements Iterator<E> {
            private int iteratorPosition = 0;
            private final int itModCount = modCount;
            private Node<E> itFirst = first;

            @Override
            public boolean hasNext() {
                if (itModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return iteratorPosition < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E item = itFirst.item;
                itFirst = itFirst.next;
                iteratorPosition++;
                return item;
            }
        }
        return new LinkedListIterator();
    }
}
