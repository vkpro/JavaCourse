package ru.luxoft.cources.lab8core;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class HashMapImpl<K, V> {
    private final int numberOfBuckets;
    private final Entry<K, V>[] buckets;
    private int size = 0;

    public HashMapImpl() {
        this(100);
    }

    @SuppressWarnings("unchecked")
    public HashMapImpl(int numberOfBuckets) {
        this.numberOfBuckets = numberOfBuckets;
        buckets = new Entry[numberOfBuckets + 1];
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean containsKey(K key) {
        return getEntryByKeyFromBucket(key).isPresent();
    }

    public boolean containsValue(V value) {
        return IntStream.range(0, buckets.length)
                .mapToObj(i -> isValueInBucket(i, value))
                .anyMatch(b -> true);
    }

    public V get(K key) {
        return getEntryByKeyFromBucket(key)
                .map(Entry::getValue)
                .orElse(null);
    }

    public V put(K key, V value) {
        Optional<Entry<K, V>> entry = getEntryByKeyFromBucket(key);
        if (entry.isPresent()) {
            return entry.get().setValueAndReturnOld(value);
        } else {
            size++;
            appendElementToBucket(key, value);
            return null;
        }
    }

    public V remove(K key) {
        int location = bucketNumber(key);
        if (isBucketEmpty(location))
            return null;

        Optional<Entry<K, V>> entry = getEntryParentByKeyFromBucket(key);
        if (entry.isEmpty()) {
            if (Objects.equals(buckets[location].getKey(), key)) {
                size--;
                return removeEntryFromBucketHead(location);
            } else {
                return null;
            }
        } else {
            size--;
            return removeEntry(entry.get());
        }
    }

    private boolean isBucketEmpty(int bucket) {
        return buckets[bucket] == null;
    }

    private int bucketNumber(K key) {
        return key == null ? 0 : key.hashCode() % numberOfBuckets + 1;
    }

    private void appendElementToBucket(K key, V value) {
        int location = bucketNumber(key);
        if (isBucketEmpty(location)) {
            buckets[location] = new Entry<>(key, value);
        } else {
            getEntryFromBucketByCondition(key, e -> e.getNext() == null)
                    .ifPresent(e -> e.setNext(new Entry<>(key, value)));
        }
    }

    private V removeEntry(Entry<K, V> entry) {
        V retValue = entry.getNext().getValue();
        entry.setNext(entry.getNext().getNext());
        return retValue;
    }

    private V removeEntryFromBucketHead(int location) {
        V retValue = buckets[location].getValue();
        buckets[location] = buckets[location].getNext();
        return retValue;
    }

    private boolean isValueInBucket(int location, V value) {
        Predicate<Entry<K, V>> sameValue = e -> Objects.equals(value, e.getValue());
        return (!isBucketEmpty(location))
                && getEntryFromBucketByCondition(buckets[location].getKey(), sameValue).isPresent();
    }

    private Optional<Entry<K, V>> getEntryFromBucketByCondition(K key, Predicate<Entry<K, V>> predicate) {
        int location = bucketNumber(key);
        Entry<K, V> entry = buckets[location];
        while (entry != null && !predicate.test(entry)) {
            entry = entry.getNext();
        }
        return Optional.ofNullable(entry);
    }

    private Optional<Entry<K, V>> getEntryByKeyFromBucket(K key) {
        return getEntryFromBucketByCondition(key, e -> Objects.equals(e.getKey(), key));
    }

    private Optional<Entry<K, V>> getEntryParentByKeyFromBucket(K key) {
        Predicate<Entry<K, V>> nextHasSameKey = e -> Objects.equals(e.getNext().getKey(), key);
        Predicate<Entry<K, V>> nextIsNotNull = e -> e.getNext() != null;

        return getEntryFromBucketByCondition(key, nextIsNotNull.and(nextHasSameKey));
    }
}
