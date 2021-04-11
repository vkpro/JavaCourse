package ru.luxoft.cources.lab6core;

import java.util.Scanner;
import java.util.TreeSet;

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
        System.out.println(getTopProducts(productList));
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

    private static TreeSet<Product> getTopProducts(TreeSet<Product> productList) {
        TreeSet<Product> countSortedProducts = new TreeSet<>(new ProductCountComparator());
        countSortedProducts.addAll(productList);

        TreeSet<Product> top3products = new TreeSet<>(new ProductCountComparator());

        int i = 0;
        for (Product p : countSortedProducts) {
            if (i == 3) break;
            top3products.add(p);
            i++;
        }
        return top3products;
    }
}
