package ru.luxoft.cources.calc;

import java.util.Scanner;

public class Calculator {
    static double num1 = 0;
    static double num2 = 0;
    static String operator = "";
    static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isExit = false;
        while (!isExit) {
            try {
                System.out.println(getCalcResult());
            } catch (IllegalArgumentException ex) {
                System.out.println("Wrong input!");
            } catch (ExitException ex) {
                System.out.println("Exit!");
                isExit = true;
            }
        }
    }

    private static String getUserInput() {
        return SCANNER.nextLine();
    }

    private static double getCalcResult() {
        String userInput = getUserInput();
        if ("q".equals(userInput)) {
            throw new ExitException();
        }
        num1 = Integer.parseInt(userInput);
        num2 = Integer.parseInt(getUserInput());
        operator = getUserInput();
        return calculate();
    }

    public static double calculate() {
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

class ExitException extends RuntimeException {
}
