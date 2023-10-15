package structure.Nodes;

public interface IHashNode<K, V> {
    K getKey();

    V getValue();

    HashNode<K, V> getNext();

    HashNode<K,V> getPrevious();

    void setNext(HashNode<K, V> next);

    void setPrevious(HashNode<K,V> previous);
}
