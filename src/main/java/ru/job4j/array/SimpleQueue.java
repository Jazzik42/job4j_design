package ru.job4j.array;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int count = 0;

    public T poll() {
        int index = count;
        for (int i = 0; i < index - 1; i++) {
            out.push(in.pop());
        }
        count--;
        T rsl = in.pop();
        for (int i = 0; i < index - 1; i++) {
            in.push(out.pop());
        }
        return rsl;
    }

    public void push(T value) {
        in.push(value);
        count++;
    }
}

