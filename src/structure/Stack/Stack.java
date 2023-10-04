package structure.Stack;

import java.util.Arrays;

public class Stack<E> {
    private Object[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public Stack() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void push(E element) {
        if (size == data.length) {
            resize();
        }
        data[size] = element;
        size++;
    }

    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("The stack is empty");
        }
        E element = (E) data[size - 2];
        data[size - 1] = null;
        size--;
        return element;
    }

    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("The stack is empty");
        }
        return (E) data[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize() {
        int newCapacity = data.length * 2;
        data = Arrays.copyOf(data, newCapacity);
    }
}

