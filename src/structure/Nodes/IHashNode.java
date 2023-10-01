package structure.Nodes;

public interface IHashNode<K, V> {
    K getKey();

    V getValue();

    IHashNode<K, V> getNext();

    IHashNode<K,V> getPrevious();

    void setNext(IHashNode<K, V> next);

    void setPrevious(IHashNode<K, V> previous);
}
