package ru.luxoft.cources.lab6core;

import java.util.Objects;

class Product implements Comparable<Product> {

    private final String name;
    private final Integer cost;
    private final Integer count;

    public Product(String name, Integer cost, Integer count) {
        this.name = name;
        this.cost = cost;
        this.count = count;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public String toString() {
        return
                "{name='" + name + '\'' +
                        ", cost=" + cost +
                        ", count=" + count +
                        '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name) && cost.equals(product.cost) && count.equals(product.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost, count);
    }

    @Override
    public int compareTo(Product o) {
        return this.name.compareTo(o.name);
    }
}
