package structure.Queue;

import java.util.ArrayList;
import java.util.List;

class PriorityQueue<T> {
    private List<Entry<T>> heap;

    private static class Entry<T> {
        T item;
        int priority;

        Entry(T item, int priority) {
            this.item = item;
            this.priority = priority;
        }
    }

    public PriorityQueue() {
        heap = new ArrayList<>();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void enqueue(T item, int priority) {
        Entry<T> entry = new Entry<>(item, priority);
        heap.add(entry);
        int index = heap.size() - 1;
        bubbleUp(index);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("The priority queue is empty.");
        }

        Entry<T> top = heap.get(0);
        Entry<T> last = heap.remove(heap.size() - 1);

        if (!isEmpty()) {
            heap.set(0, last);
            trickleDown(0);
        }

        return top.item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("The priority queue is empty.");
        }
        return heap.get(0).item;
    }

    public int size() {
        return heap.size();
    }

    private void bubbleUp(int index) {
        Entry<T> entry = heap.get(index);
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            Entry<T> parentEntry = heap.get(parentIndex);
            if (entry.priority >= parentEntry.priority) {
                break;
            }
            heap.set(index, parentEntry);
            index = parentIndex;
        }
        heap.set(index, entry);
    }

    private void trickleDown(int index) {
        int size = heap.size();
        Entry<T> entry = heap.get(index);
        while (true) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int minIndex = index;

            if (leftChildIndex < size && heap.get(leftChildIndex).priority < heap.get(minIndex).priority) {
                minIndex = leftChildIndex;
            }

            if (rightChildIndex < size && heap.get(rightChildIndex).priority < heap.get(minIndex).priority) {
                minIndex = rightChildIndex;
            }

            if (minIndex == index) {
                break;
            }

            heap.set(index, heap.get(minIndex));
            index = minIndex;
        }
        heap.set(index, entry);
    }
}
