package model;

public interface INode<E> {
    E getData();
    Node<E> getNext();
    void setNext(Node<E> next);
}