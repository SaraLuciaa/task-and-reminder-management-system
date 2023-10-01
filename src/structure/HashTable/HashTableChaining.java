package structure.HashTable;


import structure.Nodes.HashNode;

public class HashTableChaining<K,V> implements IHashTable<K,V> {
    private HashNode<K,V>[] array;

    public HashTableChaining(int size) {
        this.array = new HashNode[size];
    }

    public int hash(K key) throws ConversionException {
        return convertStringToNatural(key.toString()) % array.length;
    }

    public int convertStringToNatural(String str) throws ConversionException {
        int result = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int valueUnicode = (int) c;

            if (valueUnicode > 127) {
                throw new ConversionException("Invalid character in UNICODE range 0-128: " + c);
            }

            result = result * 128 + valueUnicode;
        }

        return result;
    }

    public String add(K key, V value){
        try {
            int k = hash(key);
            if(array[k]==null){
                array[k] = new HashNode<K, V>(key, value) {
                };
            } else {
                HashNode<K,V> newN = new HashNode<K, V>(key, value) {
                };
                newN.setNext(array[k]);
                array[k] = newN;
            }
            return "Node was added at position " + key;
        } catch (ConversionException e) {
            return "ConversionException: " + e.getMessage();
        }
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public void remove(K key) {

    }
}
