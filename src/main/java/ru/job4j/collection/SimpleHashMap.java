package ru.job4j.collection;

import java.util.*;

public class SimpleHashMap<K, V> {
    private Node<K, V>[] table;
    private int capacity = 16;
    private int size;
    private int modCount = 0;
    private float loadFactor = 0.75f;

    public SimpleHashMap() {
    }

    public SimpleHashMap(int initialCapacity) {
        for (int i = 4; i < 31; i++) {
            if (initialCapacity < (1 << i)) {
                capacity = 1 << i;
                break;
            }
        }
        table = (Node<K, V>[]) new Node[capacity];
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public float getLoadFactor() {
        return loadFactor;
    }

    public SimpleHashMap(int initialCapacity, float loadFactor) {
        this(initialCapacity);
        if (loadFactor > 0 && loadFactor < 1) {
            this.loadFactor = loadFactor;
        }
    }

    public SimpleHashMap(SimpleHashMap<K, V> map) {
        this.capacity = map.capacity;
        this.loadFactor = map.loadFactor;
        for (Iterator<Node<K, V>> it = map.iterator(); it.hasNext();) {
            Node<K, V> nodes = it.next();
            this.insert(nodes.key, nodes.value);
        }
    }

    public boolean insert(K key, V value) {
        if (table == null) {
            table = (Node<K, V>[]) new Node[capacity];
        }
        if (size >= capacity * loadFactor) {
            resize();
        }
        int hash = hash(key);
        int i = index(hash);
        Node<K, V> p = table[i];
        if (p == null) {
            table[i] = new Node<>(hash, key, value);
            modCount++;
            size++;
            return true;
        } else if (hash == (p.hash)
                && (Objects.equals(key, p.key))) {
            modCount++;
            p.setValue(value);
            return true;
        }
        return false;
    }

    public boolean delete(K key) {
        int hash = hash(key);
        int i = index(hash);
        Node<K, V> p = table[i];
        if (p != null) {
            if (hash == (p.hash)
                    && (key == p.key || key.equals(p.key))) {
                table[i] = null;
                modCount++;
                size--;
                return true;
            }
        }
        return false;
    }

    private void resize() {
        Node<K, V>[] buffer = table;
        this.size = 0;
        this.modCount = 0;
        this.capacity = capacity * 2;
        table = (Node<K, V>[]) new Node[capacity];
        for (Node<K, V> nodes : buffer) {
            if (nodes != null) {
                this.insert(nodes.key, nodes.value);
            }
        }
    }

    private int index(int hash) {
        return hash & (table.length - 1);
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int h = key.hashCode();
        return h ^ (h >>> 16);
    }

    public V get(K key) {
        int hash = hash(key);
        int i = index(hash);
        Node<K, V> p = table[i];
        if (p == null) {
            throw new NoSuchElementException();
        }
        V rsl = null;
        if (hash == (p.hash)
                && (key == p.key || key.equals(p.key))) {
            rsl = p.value;
        }
        return rsl;
    }

    public static class Node<K, V> {
        private int hash;
        private K key;
        private V value;

        private Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        private void setValue(V value) {
            this.value = value;
        }
    }

    public Iterator<Node<K, V>> iterator() {
        class HashMapIterator implements Iterator<Node<K, V>> {
            int itModCount = modCount;
            int position = 0;

            @Override
            public boolean hasNext() {
                if (itModCount != modCount) {
                    throw new ConcurrentModificationException();
                } else {
                    if (table != null) {
                        for (int i = position; i < table.length; i++) {
                            if (table[i] != null) {
                                position = i;
                                return true;
                            }
                        }
                    }
                }
                return false;
            }

            @Override
            public Node<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[position++];
            }
        }
        return new HashMapIterator();
    }
}
