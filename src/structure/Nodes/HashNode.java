package structure.Nodes;

public class HashNode<K, V> implements IHashNode<K,V> {
    private K key;
    private V value;
    private HashNode<K, V> next;
    private HashNode<K,V> previous;

    public HashNode(K key, V value) {
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
    public HashNode<K, V> getNext() {
        return next;
    }

    public HashNode<K,V> getPrevious(){
        return previous;
    }

    public void setNext(HashNode<K, V> next) {
        this.next = next;
    }
    @Override
    public void setPrevious(HashNode<K, V> previous) {
        this.previous = previous;
    }

}
