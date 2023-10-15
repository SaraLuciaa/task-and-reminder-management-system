package structure.Queue;

public interface IQueue<E> {
    boolean isEmpty();
    E peek() throws QueueException;
    E poll() throws QueueException;
    void offer(E item);
}
