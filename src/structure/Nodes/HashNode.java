package structure.Nodes;

public class HashNode<K, V> implements IHashNode<K,V> {
    private K key;
    private V value;
    private IHashNode<K, V> next;
    private IHashNode<K,V> previous;

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
    public IHashNode<K, V> getNext() {
        return next;
    }

    public IHashNode<K,V> getPrevious(){
        return previous;
    }
    public void setNext(IHashNode<K, V> next) {
        this.next = next;
    }
    @Override
    public void setPrevious(IHashNode<K, V> previous) {
        this.previous = previous;
    }

}
