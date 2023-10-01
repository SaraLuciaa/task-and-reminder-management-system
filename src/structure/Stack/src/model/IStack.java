package model;

public interface IStack<E> {
    boolean isEmpty();
    void push(E data);
    E peek() throws StackException;
    E pop() throws StackException;
}