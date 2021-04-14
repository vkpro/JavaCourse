package ru.luxoft.cources.lab6core;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TreeSet<Product> productList = new TreeSet<>();

        int count = 0;
        while (count < 5 && sc.hasNext()) {
            String s = sc.nextLine();
            if ("q".equals(s)) break;
            addProduct(productList, s);
            count++;
        }
        displayProductsStatistic(productList);
    }

    private static void displayProductsStatistic(TreeSet<Product> productList) {
        System.out.println(productList);
        System.out.println(getProductsSum(productList));
        System.out.println(getTopThreeProducts(productList));
    }

    private static void addProduct(TreeSet<Product> productList, String newProduct) {
        String[] parts = newProduct.split(" ");
        if (parts.length != 3) {
            System.out.println("Wrong number of arguments! Retry!");
            return;
        }
        String productName = parts[0];
        int productCost;
        int productCount;

        try {
            productCost = Integer.parseInt(parts[1]);
            productCount = Integer.parseInt(parts[2]);
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Retry!");
            return;
        }

        productList.add(new Product(productName, productCost, productCount));
    }

    private static long getProductsSum(TreeSet<Product> productList) {
        int sum = 0;
        for (Product p : productList) {
            sum += p.getCount() * p.getCost();
        }
        return sum;
    }

    private static Set<Product> getTopThreeProducts(SortedSet<Product> productList) {
        SortedSet<Product> countSortedProducts = new TreeSet<>(new ProductCountComparator());
        countSortedProducts.addAll(productList);

        return countSortedProducts.stream().limit(3).collect(Collectors.toSet());

//        return countSortedProducts.headSet(getProductAtIndex(countSortedProducts, 3));
    }

    private static Product getProductAtIndex(SortedSet<Product> products, int index) {
        Product result = null;
        Iterator<Product> iterator = products.iterator();
        for (int i = 0; i <= index; i++) {
            result = iterator.next();
        }
        return result;
    }
}
