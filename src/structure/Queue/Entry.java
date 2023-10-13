package structure.Queue;

public class Entry<T> {
    T item;
    long priority;

    Entry(T item, long priority) {
        this.item = item;
        this.priority = priority;
    }

    public T getItem() {
        return item;
    }
}