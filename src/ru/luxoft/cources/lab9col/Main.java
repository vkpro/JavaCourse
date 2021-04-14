package ru.luxoft.cources.lab9col;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeMap<String, TreeMap<String, Integer>> clients = new TreeMap<>();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String s = sc.nextLine();
            if ("q".equals(s)) break;

            String[] parts = s.split(" ");
            try {
                getInputData(clients, parts);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong input! Use format <client> <product> <count>");
            }
        }
        displayStatistic(clients);
    }

    private static void getInputData(TreeMap<String, TreeMap<String, Integer>> clients, String[] parts) {
        String name = parts[0];
        String productName = parts[1];
        Integer count = Integer.parseInt(parts[2]);

        TreeMap<String, Integer> temp = clients.computeIfAbsent(name, n -> new TreeMap<>());

        temp.putIfAbsent(productName, 0);
        temp.computeIfPresent(productName, (k, v) -> v + count);
    }

    private static void displayStatistic(TreeMap<String, TreeMap<String, Integer>> clients) {
        for (Map.Entry<String, TreeMap<String, Integer>> entry : clients.entrySet()) {
            String key = entry.getKey();
            TreeMap<String, Integer> value = entry.getValue();
            System.out.println(key + ":");

            for (Map.Entry<String, Integer> product : value.entrySet()) {
                String keyProduct = product.getKey();
                Integer valueProduct = product.getValue();
                System.out.println(keyProduct + " " + valueProduct);
            }
        }
    }
}

