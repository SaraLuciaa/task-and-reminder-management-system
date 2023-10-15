package structure.Queue;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue<T extends Comparable<T>> implements Cloneable {
    private List<T> heap;

    public PriorityQueue() {
        heap = new ArrayList<>();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void enqueue(T item) {
        heap.add(item);
        int index = heap.size() - 1;
        heapSort();
    }

    public void heapSort() {
        int n = heap.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            minHeapify(n, i);
        }

        List<T> sortedList = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            T item = heap.get(0);
            sortedList.add(item);
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

        if (leftChild < n && heap.get(leftChild).compareTo(heap.get(smallest)) < 0) {
            smallest = leftChild;
        }

        if (rightChild < n && heap.get(rightChild).compareTo(heap.get(smallest)) < 0) {
            smallest = rightChild;
        }

        if (smallest != i) {
            T temp = heap.get(i);
            heap.set(i, heap.get(smallest));
            heap.set(smallest, temp);

            minHeapify(n, smallest);
        }
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }

        T top = heap.get(0);
        T last = heap.remove(heap.size() - 1);

        if (!isEmpty()) {
            heap.set(0, last);
            heapSort();
        }

        return top;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public List<T> getHeap() {
        return heap;
    }

    public void remove(T item) {
        int index = -1;

        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).equals(item)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            int lastIndex = heap.size() - 1;
            if (index != lastIndex) {
                heap.set(index, heap.get(lastIndex));
                heap.remove(lastIndex);
                heapSort();
            } else {
                heap.remove(index);
            }
        }
    }

    @Override
    public PriorityQueue<T> clone() {
        try {
            PriorityQueue<T> clone = (PriorityQueue<T>) super.clone();
            clone.heap = new ArrayList<>(heap);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}