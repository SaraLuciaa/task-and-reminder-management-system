package structure.HashTable;

public interface IHashTable<K,V> {
    String add(K key, V value);
    V get(K key);
    boolean containsKey(K key);
    void remove(K key);
}
