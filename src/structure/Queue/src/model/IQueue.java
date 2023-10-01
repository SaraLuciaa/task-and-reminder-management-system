package model;

public interface IQueue<E> {
    boolean isEmpty();
    E peek() throws QueueException;
    E poll() throws QueueException;
    boolean offer(E item);
}
