package calc;

public class Calc {
    public static int calc(int num1, String operator, int num2) {
        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> -1;
        };
    }

    public static void main(String[] args) {
        int num1 = Integer.parseInt(args[0]);
        String operator = args[1];
        int num2 = Integer.parseInt(args[2]);

        int result = calc(num1, operator, num2);
        System.out.format("%d %s %d = %d", num1, operator, num2, result);
    }
}
