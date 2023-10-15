package structure.Queue;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue<T> implements Cloneable{
    private List<Entry<T>> heap;

    public PriorityQueue() {
        heap = new ArrayList<>();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void enqueue(T item, long priority) {
        Entry<T> entry = new Entry<>(item, priority);
        heap.add(entry);
        int index = heap.size() - 1;
        heapSort();
    }

    public void heapSort() {
        int n = heap.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            minHeapify(n, i);
        }

        List<Entry<T>> sortedList = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            Entry<T> entry = heap.get(0);
            sortedList.add(entry);
            heap.set(0, heap.get(i));
            minHeapify(i, 0);
        }

        heap.clear();
        heap.addAll(sortedList);
    }

    private void minHeapify(int n, int i) {
        int smallest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < n && heap.get(leftChild).priority < heap.get(smallest).priority) {
            smallest = leftChild;
        }

        if (rightChild < n && heap.get(rightChild).priority < heap.get(smallest).priority) {
            smallest = rightChild;
        }

        if (smallest != i) {
            Entry<T> temp = heap.get(i);
            heap.set(i, heap.get(smallest));
            heap.set(smallest, temp);

            minHeapify(n, smallest);
        }
    }


    public T dequeue() {
        if (isEmpty()) {
            return null;
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
            return null;
        }
        return heap.get(0).item;
    }

    public int size() {
        return heap.size();
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

    public List<Entry<T>> getHeap() {
        return heap;
    }

    public void remove(T item) {
        int index = -1;
        int size = heap.size();

        for (int i = 0; i < size; i++) {
            if (heap.get(i).item.equals(item)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            heap.set(index, heap.get(size - 1));
            heap.remove(size - 1);
            if(index != size-1) {
                trickleDown(index);
            }
        }
    }

    @Override
    public PriorityQueue<T> clone() {
        try {
            PriorityQueue clone = (PriorityQueue) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
