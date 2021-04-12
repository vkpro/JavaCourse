package ru.luxoft.cources.lab8core;

public class Entry<K, V> {
    Object key;
    Object val;

    public void setKey(Object key) {
        this.key = key;
    }

    public void setVal(Object val) {
        this.val = val;
    }

    public Object getKey() {
        return key;
    }

    public Object getVal() {
        return val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass().getName() != o.getClass().getName()) {
            return false;
        }
        Entry e = (Entry) o;
        if (this.key == e.key) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int prime = 13;
        int mul = 11;
        if (key != null) {
            int hashCode = prime * mul + key.hashCode();
            return hashCode;
        }
        return 0;
    }


}
