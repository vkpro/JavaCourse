package ru.luxoft.cources.lab8core;

public class HashMapImpl {
    private final int capacity = 100;
    private int size = 0;
    private Entry table[] = new Entry[capacity];

    public static void main(String[] args) {
        HashMapImpl myHashMap = new HashMapImpl();
        myHashMap.put(1, "A");
        myHashMap.put(2, "B");
        myHashMap.put(3, "C");
        System.out.println("HashMap size: " + myHashMap.size());
        System.out.println("Value of key 1: " + myHashMap.get(1));
        System.out.println("Value of key 2: " + myHashMap.get(2));
        System.out.println("Value of key 3: " + myHashMap.get(3));
    }

    private int Hashing(int hashCode) {
        int location = hashCode % capacity;
        System.out.println("Location:" + location);
        return location;
    }

    public int size() {
        // TODO Auto-generated method stub
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean containsKey(Object key) {
        if (key == null) {
            if (table[0].getKey() == null) {
                return true;
            }
        }
        int location = Hashing(key.hashCode());
        Entry e = null;
        try {
            e = table[location];
        } catch (NullPointerException ex) {

        }
        return e != null && e.getKey() == key;
    }

    public boolean containsValue(Object value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && table[i].getVal() == value) {
                return true;
            }
        }
        return false;
    }

    public Object get(Object key) {
        Object ret = null;
        if (key == null) {
            Entry e = null;
            try {
                e = table[0];
            } catch (NullPointerException ex) {

            }
            if (e != null) {
                return e.getVal();
            }
        } else {
            int location = Hashing(key.hashCode());
            Entry e = null;
            try {
                e = table[location];
            } catch (NullPointerException ex) {

            }
            if (e != null && e.getKey() == key) {
                return e.getVal();
            }
        }
        return ret;
    }

    public Object put(Object key, Object val) {
        Object ret = null;
        if (key == null) {
            ret = putForNullKey(val);
            return ret;
        } else {
            int location = Hashing(key.hashCode());
            if (location >= capacity) {
                System.out.println("Rehashing required");
                return null;
            }
            Entry e = null;
            try {
                e = table[location];
            } catch (NullPointerException ex) {

            }
            if (e != null && e.getKey() == key) {
                ret = e.getVal();
            } else {
                Entry eNew = new Entry();
                eNew.setKey(key);
                eNew.setVal(val);
                table[location] = eNew;
                size++;
            }
        }
        return ret;
    }

    private Object putForNullKey(Object val) {
        Entry e = null;
        try {
            e = table[0];
        } catch (NullPointerException ex) {

        }
        Object ret = null;
        if (e != null && e.getKey() == null) {
            ret = e.getVal();
            e.setVal(val);
            return ret;
        } else {
            Entry eNew = new Entry();
            eNew.setKey(null);
            eNew.setVal(val);
            table[0] = eNew;
            size++;
        }
        return ret;
    }

    public Object remove(Object key) {
        int location = Hashing(key.hashCode());
        Object ret = null;
        if (table[location].getKey() == key) {
            for (int i = location; i < table.length; i++) {
                table[i] = table[i + 1];
            }
        }
        return ret;
    }
}
