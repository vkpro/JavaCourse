package ru.luxoft.cources.calc;

import java.util.Scanner;

public class Calculator {
    static double num1 = 0;
    static double num2 = 0;
    static String operator = "";
    static double result = 0;
    static String userInput = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (!"q".equals(userInput)) {
            try {
                result = getCalcResult();
                System.out.println(result);
            } catch (IllegalArgumentException ex) {
                System.out.println("Wrong input!");
            }
        }
    }

    private static String getUserInput() {
        userInput = scanner.nextLine();
        return userInput;
    }

    private static double getCalcResult() throws IllegalArgumentException {
        num1 = Integer.parseInt(getUserInput());
        num2 = Integer.parseInt(getUserInput());
        operator = getUserInput();
        return calculate(num1, operator, num2);
    }

    public static double calculate(double num1, String operator, double num2) throws IllegalArgumentException {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    throw new IllegalArgumentException();
                }
            case "!":
                return fact(num1);
            default:
                throw new IllegalArgumentException();
        }
    }

    private static int fact(double num) {
        int res = 1;
        for (int i = 1; i <= (int) num; i++) {
            res *= i;
        }
        return res;
    }
}
