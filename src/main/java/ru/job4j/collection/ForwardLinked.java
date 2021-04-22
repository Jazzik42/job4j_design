package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node head;

    public void add(T element) {
        Node node = new Node(element, null);
        if (head == null) {
            head = node;
            return;
        }
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T item = node.item;
                node = node.next;
                return item;
            }
        };
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T deletedItem = head.item;
        head = head.next;
        return deletedItem;
    }

    public void addFirst(T item) {
        Node newNode = new Node(item, null);
        if (head == null) {
            head = newNode;
        } else {
            Node buff = head;
            head = newNode;
            head.next = buff;
        }
    }

    public boolean revert() {
        if (head == null || head.next == null) {
            return false;
        }
        Node buffer = null;
        Node last = head;
        while (last != null) {
            Node tail = last.next;
            last.next = buffer;
            buffer = last;
            last = tail;
            if (last == null) {
                head = buffer;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        return head == null;
    }

    private class Node {
        private T item;
        private Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}


