package ru.luxoft.cources.calc;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    static double num1 = 0;
    static double num2 = 0;
    static String operator = "";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                Double result = getCalcResult();
                if (result != null) {
                    System.out.format("%.1f %s %.1f = %.1f\n", num1, operator, num2, result);
                } else {
                    break;
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Wrong input! Use `<num1> <+,-,*,/,!> <num2>`");
            }
        }
    }

    private static Double getCalcResult() throws IllegalArgumentException {
        String inputString = scanner.nextLine();

        if ("q".equals(inputString.trim())) return null;
        List<String> inputParams = Arrays.asList(inputString.split(" "));

        if (inputParams.size() >= 3) {
            num1 = Integer.parseInt(inputParams.get(0));
            operator = inputParams.get(1);
            num2 = Integer.parseInt(inputParams.get(2));
        } else if (inputParams.size() >= 2) {
            num1 = Integer.parseInt(inputParams.get(0));
            operator = inputParams.get(1);
        } else {
            throw new IllegalArgumentException();
        }
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
