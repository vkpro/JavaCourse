package ru.luxoft.cources.lab8core;

public class Entry<K, V> {
    K key;
    V val;
    Entry<K, V> next;

    public Entry(K key, V val) {
        this.key = key;
        this.val = val;
    }

    public V setValueAndReturnOld(V val) {
        V tmp = this.val;
        this.val = val;
        return tmp;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return val;
    }

    public Entry<K, V> getNext() {
        return next;
    }

    public void setNext(Entry<K, V> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Entry<?, ?> e = (Entry<?, ?>) o;
        return this.key == e.key;
    }

    @Override
    public int hashCode() {
        int prime = 13;
        int mul = 11;
        return key == null ? 0 : prime * mul + key.hashCode();
    }


}
