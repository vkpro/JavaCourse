package ru.luxoft.cources.lab8core;

import java.util.Objects;
import java.util.function.Predicate;

public class HashMapImpl<K, V> {
    private final int capacity = 100;
    private int size = 0;
    private Entry<K, V>[] buckets = new Entry[capacity];
    private Entry<K, V> nullBucket = null;

    public static void main(String[] args) {
        HashMapImpl<Integer, String> myHashMap = new HashMapImpl<>();
        myHashMap.put(1, "A");
        myHashMap.put(2, "B");
        myHashMap.put(3, "C");
        System.out.println("HashMap size: " + myHashMap.size());
        System.out.println("Value of key 1: " + myHashMap.get(1));
        System.out.println("Value of key 2: " + myHashMap.get(2));
        System.out.println("Value of key 3: " + myHashMap.get(3));
        System.out.println("Value of key 4: " + myHashMap.get(4));
        System.out.println("Value of key null: " + myHashMap.get(null));
        myHashMap.put(null, "<null>");
        System.out.println("Value of key null: " + myHashMap.get(null));
        System.out.println("HashMap size: " + myHashMap.size());

        System.out.println("Old val " + myHashMap.remove(2));
        System.out.println("Old val " + myHashMap.remove(2));
        System.out.println("Old val " + myHashMap.remove(null));
        System.out.println("Old val " + myHashMap.remove(null));
        System.out.println("HashMap size: " + myHashMap.size());

    }

    private int hashing(int hashCode) {
        int location = hashCode % capacity;
        System.out.println("Location:" + location);
        return location;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean containsKey(K key) {
        if (key == null) {
            return nullBucket != null;
        }
        return getEntryByKeyFromBucket(key) != null;
    }

    private Entry<K, V> getEntryFromBucketByCondition(K key, Predicate<Entry<K, V>> predicate) {
        if (key == null) {
            if (nullBucket == null) {
                return null;
            } else {
                return predicate.test(nullBucket) ? nullBucket : null;
            }
        }

        int location = hashing(key.hashCode());
        Entry<K, V> entry = buckets[location];
        while (entry != null && !predicate.test(entry)) {
            entry = entry.getNext();
        }
        return entry;
    }

    private Entry<K, V> getEntryByKeyFromBucket(K key) {
        int location = hashing(key.hashCode());
        Entry<K, V> element = buckets[location];
        while (element != null && !element.getKey().equals(key)) {
            element = element.getNext();
        }
        return element;
    }

    private Entry<K, V> getEntryParentByKeyFromBucket(K key) {
        int location = hashing(key.hashCode());
        Entry<K, V> element = buckets[location];
        if (element == null || element.getNext() == null) {
            return null;
        } else {
            while (element.getNext() != null && !element.getNext().getKey().equals(key)) {
                element = element.getNext();
            }
        }
        return element;
    }

    public boolean containsValue(V value) {
        for (int i = 0; i < buckets.length; i++) {
            if (isValueInBucket(i, value)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValueInBucket(int location, V value) {
        Entry<K, V> element = buckets[location];
        while (element != null) {
            if (Objects.equals(value, element.getValue())) {
                return true;
            } else {
                element = element.getNext();
            }
        }
        return false;
    }

    public V get(K key) {
        if (key == null) {
            return nullBucket == null ? null : nullBucket.getValue();
        } else {
            Entry<K, V> entry = getEntryByKeyFromBucket(key);
            return entry == null ? null : entry.getValue();
        }
    }

    public V put(K key, V value) {
        if (size >= capacity) {
            System.out.println("Rehashing required");
            return null;
        }

        if (key == null) {
            if (nullBucket == null) {
                nullBucket = new Entry<>(null, value);
                size++;
                return null;
            } else {
                return nullBucket.setValueAndReturnOld(value);
            }
        } else {
            Entry<K, V> entry = getEntryByKeyFromBucket(key);
            if (entry == null) {
                int location = hashing(key.hashCode());
                size++;
                entry = getLastElementInBucket(location);
                Entry<K, V> newEntry = new Entry<>(key, value);

                if (entry == null) {
                    buckets[location] = newEntry;
                } else {
                    entry.setNext(newEntry);
                }
                return null;
            } else {
                return entry.setValueAndReturnOld(value);
            }
        }
    }

    private Entry<K, V> getLastElementInBucket(int location) {
        if (buckets[location] == null) {
            return null;
        } else {
            Entry<K, V> element = buckets[location];
            while (element.getNext() != null) {
                element = element.getNext();
            }
            return element;
        }
    }

    public V remove(K key) {
        if (key == null) {
            if (nullBucket != null) {
                V retValue = nullBucket.getValue();
                nullBucket = null;
                size--;
                return retValue;
            } else {
                return null;
            }
        }

        int location = hashing(key.hashCode());
        Entry<K, V> entry = getEntryParentByKeyFromBucket(key);
        if (entry == null) {
            if (buckets[location] != null && buckets[location].getKey().equals(key)) {
                V retValue = buckets[location].getValue();
                buckets[location] = buckets[location].getNext();
                size--;
                return retValue;
            } else {
                return null;
            }
        } else {
            V retValue = entry.getNext().getValue();
            entry.setNext(entry.getNext().getNext());
            size--;
            return retValue;
        }
    }
}
