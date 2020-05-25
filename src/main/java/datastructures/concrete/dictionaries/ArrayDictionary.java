package datastructures.concrete.dictionaries;

import datastructures.interfaces.IDictionary;
import misc.exceptions.NoSuchKeyException;

/**
 * See IDictionary for more details on what this class should do
 */
public class ArrayDictionary<K, V> implements IDictionary<K, V> {
    // You may not change or rename this field: we will be inspecting
    // it using our private tests.
    private Pair<K, V>[] pairs;
    private int size;
    // You're encouraged to add extra fields (and helper methods) though!

    public ArrayDictionary() {
        this.pairs = this.makeArrayOfPairs(5);
    }

    /**
     * This method will return a new, empty array of the given size
     * that can contain Pair<K, V> objects.
     *
     * Note that each element in the array will initially be null.
     */
    @SuppressWarnings("unchecked")
    private Pair<K, V>[] makeArrayOfPairs(int arraySize) {
        // It turns out that creating arrays of generic objects in Java
        // is complicated due to something known as 'type erasure'.
        //
        // We've given you this helper method to help simplify this part of
        // your assignment. Use this helper method as appropriate when
        // implementing the rest of this class.
        //
        // You are not required to understand how this method works, what
        // type erasure is, or how arrays and generics interact. Do not
        // modify this method in any way.
        return (Pair<K, V>[]) (new Pair[arraySize]);

    }

    @Override
    public V get(K key) {
        if (this.containsKey(key)) {
            for (int i = 0; i < this.size(); i++) {
                if (this.pairs[i].key == null ? key == null: this.pairs[i].key.equals(key)) {
                    return this.pairs[i].value;
                }
            }
        }else {
            throw new NoSuchKeyException();
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        if (containsKey(key)){
            for (int i = 0; i < this.size(); i++) {
                if (this.pairs[i].key == null ? key == null: this.pairs[i].key.equals(key)) {
                    this.pairs[i] = new Pair<K, V>(key, value);
                }
            }
        }else if (this.pairs[this.pairs.length-1] == null) {
            for (int i = 0; i < this.pairs.length; i++) {
                if (this.pairs[i] == null) {
                    this.pairs[i] = new Pair<K, V>(key, value);
                    this.size++;
                    return;
                }
            }
        }else {
            Pair<K, V>[] newArray = this.makeArrayOfPairs(this.size() * 2);
            int i;
            for (i = 0; i < this.size(); i++) {
                newArray[i] = this.pairs[i];
            }
            newArray[i] = new Pair<K, V>(key, value);
            this.pairs = newArray;
            this.size++;
        }
        
    }

    @Override
    public V remove(K key) {
        if (this.containsKey(key)) {
            V removed = null;
            for (int i = 0; i < this.size(); i++) {
                if ((this.pairs[i].key == null ? key == null: this.pairs[i].key.equals(key))) {
                    removed = this.pairs[i].value;
                    this.pairs[i] = this.pairs[this.size()-1];
                    this.pairs[this.size()-1] = null;
                    this.size--;
                    return removed;
                }
            }
            return removed;
        }else {
           throw new NoSuchKeyException();
        }
    }

    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < this.size(); i++) {
            if ((this.pairs[i].key == null ? key == null : this.pairs[i].key.equals(key))){
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    private static class Pair<K, V> {
        public K key;
        public V value;
        // You may add constructors and methods to this class as necessary.
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }
    }
}
