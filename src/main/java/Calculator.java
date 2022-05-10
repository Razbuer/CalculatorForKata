public class Calculator {
    private boolean isRomanNumbers;
    private int firstNumber;
    private int secondNumber;

    /**
     * Получение экземпляра Calculator
     * @return экземпляр Calculator
     * */
    public static Calculator getInstance() {
        return new Calculator();
    }

    /**
     * Основной метод для работы с библиотекой
     * @param expression строка в виде арифметической операции над двумя операндами
     * @return результат вычесления арифметической операции
     */
    public String calculate(String expression) {
        if (expression.split("\\n").length > 1)
            throw new IllegalArgumentException("Неверный формат выражения! Операция должна быть в одну строку");

        String[] partsExpression = expression.split("(\\+|-|\\*|/)");

        // Проверяем что формат верный и выражение содержит что-то разделённые знаками +, -, + или /
        if (partsExpression.length != 2)
            throw new IllegalArgumentException("Неверный формат выражения! Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        // Проверяем что нам пришло в качестве аргументов вычесления
        try {
            this.firstNumber = Integer.parseInt(partsExpression[0].trim());
            this.secondNumber = Integer.parseInt(partsExpression[1].trim());
            this.isRomanNumbers = false;
        } catch (NumberFormatException e) {
            try {
                this.firstNumber = parseRomanNumberToInteger(partsExpression[0].trim());
                this.secondNumber = parseRomanNumberToInteger(partsExpression[1].trim());
                this.isRomanNumbers = true;
            } catch (Exception e1) {
                throw new IllegalArgumentException("Неверный формат выражения! Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
        }

        // Проверяем что числа в диапазоне от 1 до 10
        if ( this.firstNumber <= 0 || this.firstNumber > 10 || this.secondNumber <= 0 || this.secondNumber > 10 )
            throw new IllegalArgumentException("Неверный формат выражения! Калькулятор принимает на вход числа от 1 до 10 включительно, не более");

        // Указываем операцию путём поиска оператора, который должен идти сразу за первым операндом
        int answer = switch(expression.charAt(partsExpression[0].length())) {
            case '+' -> firstNumber + secondNumber;
            case '-' -> firstNumber - secondNumber;
            case '*' -> firstNumber * secondNumber;
            case '/' -> firstNumber / secondNumber;
            default -> throw new IllegalArgumentException("Неверный формат выражения! Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления");
        };

        return this.isRomanNumbers ? parseIntegerToRomanNumber(answer) : Integer.toString(answer);
    }

    private Calculator() {}

    /**
     * Преобразовываем римские цифры к арабским
     * @param number строка в формате римского числа от 1 до 10
     * @return представление числа в арабской форме
     * */
    private int parseRomanNumberToInteger(String number) {
        return switch (number.toUpperCase()) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> throw new IllegalArgumentException("Неверный формат римского числа");
        };
    }

    /**
     * Преобразовываем арабского числа к римскому
     * @param number строка в формате арабского числа
     * @return представление числа в римской форме
     * */
    private String parseIntegerToRomanNumber(int number) {
        if (number < 1)
            throw new IllegalRomanNumberException("Результатом вычесления римских чисел могут быть только положительные числа");

        StringBuilder romanNumber = new StringBuilder();
        int sizeNumber = Integer.toString(number).length();

        if (sizeNumber >= 1) {
            // логика для первого справа разряда
            switch (number % 10) {
                case 1 -> romanNumber.insert(0, "I");
                case 2 -> romanNumber.insert(0, "II");
                case 3 -> romanNumber.insert(0, "III");
                case 4 -> romanNumber.insert(0, "IV");
                case 5 -> romanNumber.insert(0, "V");
                case 6 -> romanNumber.insert(0, "VI");
                case 7 -> romanNumber.insert(0, "VII");
                case 8 -> romanNumber.insert(0, "VIII");
                case 9 -> romanNumber.insert(0, "IX");
            }
            number /= 10;
            if (sizeNumber >= 2) {
                // логика для второго справа разряда
                switch (number % 10) {
                    case 1 -> romanNumber.insert(0, "X");
                    case 2 -> romanNumber.insert(0, "XX");
                    case 3 -> romanNumber.insert(0, "XXX");
                    case 4 -> romanNumber.insert(0, "IL");
                    case 5 -> romanNumber.insert(0, "L");
                    case 6 -> romanNumber.insert(0, "LX");
                    case 7 -> romanNumber.insert(0, "LXX");
                    case 8 -> romanNumber.insert(0, "LXXX");
                    case 9 -> romanNumber.insert(0, "XC");
                }
                number /= 10;
                if (sizeNumber >= 3) {
                    // логика для третьего справа разряда
                    switch (number % 10) {
                        case 1 -> romanNumber.insert(0, "C");
                        case 2 -> romanNumber.insert(0, "CC");
                        case 3 -> romanNumber.insert(0, "CCC");
                        case 4 -> romanNumber.insert(0, "CD");
                        case 5 -> romanNumber.insert(0, "D");
                        case 6 -> romanNumber.insert(0, "DC");
                        case 7 -> romanNumber.insert(0, "DCC");
                        case 8 -> romanNumber.insert(0, "DCCC");
                        case 9 -> romanNumber.insert(0, "CM");
                    }
                }
            }
        }

        return romanNumber.toString();
    }
}
