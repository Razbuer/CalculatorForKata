public class Main {
    public static void main(String[] args) {
        System.out.println(calc("1 + 2"));          // Output: 3
        System.out.println(calc("VI / III"));       // Output: II
        //System.out.println(calc("I - II"));         // Output: throws Exception //т.к. в римской системе нет отрицательных чисел
        //System.out.println(calc("I + 1"));          // Output: throws Exception //т.к. используются одновременно разные системы счисления
        //System.out.println(calc("1"));              // Output: throws Exception //т.к. строка не является математической операцией
        //System.out.println(calc("1 + 2 + 3"));      // Output: throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)

    }

    public static String calc(String input) {
        return Calculator.getInstance().calculate(input);
    }
}
