package model;

public class Node<K, V> implements INode<K,V> {
    private K key;
    private V value;
    private INode<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public INode<K, V> getNext() {
        return next;
    }

    @Override
    public void setNext(INode<K, V> next) {
        this.next = next;
    }
}
