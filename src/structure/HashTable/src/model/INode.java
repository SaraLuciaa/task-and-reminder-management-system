package model;

public interface INode<K, V> {
    K getKey();

    V getValue();

    INode<K, V> getNext();

    void setNext(INode<K, V> next);
}
