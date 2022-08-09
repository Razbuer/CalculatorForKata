import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expression;

        System.out.println("Это калькулятор. По окончанию введите \"exit\"");

        while(!(expression = scanner.nextLine()).equals("exit")) {
            System.out.println(calc(expression));
        }

    }

    public static String calc(String input) {
        return Calculator.getInstance().calculate(input);
    }
}
