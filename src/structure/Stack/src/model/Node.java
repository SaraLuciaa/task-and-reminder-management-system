package model;

public class Node<E> implements INode<E> {
    private E data;
    private Node<E> next;

    public Node(E data) {
        this.data = data;
        this.next = null;
    }

    public E getData() {
        return data;
    }

    @Override
    public Node<E> getNext() {
        return next;
    }

    @Override
    public void setNext(Node<E> next) {
        this.next = next;
    }
}
