package structure.HashTable;

import structure.Nodes.HashNode;

public class HashTableChaining<K, V extends Cloneable> implements IHashTable<K, V>,Cloneable{
    private HashNode<K, V>[] array;
    private int size;

    public HashTableChaining(int size) {
        this.size = size;
        this.array = new HashNode[size];
    }

    public int hash(K key) {
        return Math.abs(key.hashCode() % size);
    }

    public HashNode<K, V>[] getArray() {
        return array;
    }

    public String add(K key, V value) {
        try {
            int index = hash(key);
            if (array[index] == null) {
                array[index] = new HashNode<>(key, value);
            } else {
                HashNode<K, V> newNode = new HashNode<>(key, value);
                newNode.setNext(array[index]);
                array[index] = newNode;
            }
            return "Node was added at position " + index;
        } catch (NullPointerException e) {
            return "NullPointerException: " + e.getMessage();
        }
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> currentNode = array[index];
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return currentNode.getValue();
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    public V set(K key, V newValue) {
        int index = hash(key);
        HashNode<K, V> currentNode = array[index];
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                currentNode.setValue(newValue);
                return currentNode.getValue();
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index = hash(key);
        HashNode<K, V> currentNode = array[index];
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    @Override
    public void remove(K key) {
        int index = hash(key);
        HashNode<K, V> currentNode = array[index];
        HashNode<K, V> previousNode = null;

        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                if (previousNode == null) {
                    array[index] = currentNode.getNext();
                } else {
                    previousNode.setNext(currentNode.getNext());
                }
                return;
            }
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }
    }

    @Override
    public HashTableChaining<K, V> clone() {
        try {
            HashTableChaining<K, V> clone = (HashTableChaining<K, V>) super.clone();

            clone.array = new HashNode[this.size];
            for (int i = 0; i < this.size; i++) {
                if (this.array[i] != null) {
                    clone.array[i] = this.array[i].clone();
                }
            }

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
