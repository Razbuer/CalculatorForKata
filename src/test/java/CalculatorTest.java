import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    public final Calculator calculator = Calculator.getInstance();

    // Операция 1 + 2 должна вернуть 3 с арабскими цифрами
    @Test
    public void operation1Plus2ShouldReturn3Arabic() {
        assertEquals("3", calculator.calculate("1+2"));
    }

    // Операция 3 - 2 должна вернуть 1 с арабскими цифрами
    @Test
    public void operation3Minus2ShouldReturn1Arabic() {
        assertEquals("1", calculator.calculate("3-2"));
    }

    // Операция 3 * 2 должна вернуть 6 с арабскими цифрами
    @Test
    public void operation3Multiplication2ShouldReturn6Arabic() {
        assertEquals("6", calculator.calculate("3*2"));
    }

    // Операция 6 / 2 должна вернуть 3 с арабскими цифрами
    @Test
    public void operation6Divide2ShouldReturn3Arabic() {
        assertEquals("3", calculator.calculate("6/2"));
    }

    // Операция должна иметь всего 2 операнда
    @Test
    public void operationShouldHave2Operands() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("1+2+3"));
    }

    // Калькулятор должен работать только с одним типом чисел одновременно
    @Test
    public void calculatorShouldOnlyWorkOneTypeParameters() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("2+V"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("V+2"));
    }

    // Операция должна быть только на одной линии
    @Test
    public void punchOnLine() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("2\n+\n3"));
    }

    // Операция 1 + 2 должна вернуть 3 с римскими цифрами
    @Test
    public void operation1Plus2ShouldReturn3Roman() {
        assertEquals("III", calculator.calculate("I+II"));
    }

    // Операция 3 - 2 должна вернуть 1 с римскими цифрами
    @Test
    public void operation3Minus2ShouldReturn1Roman() {
        assertEquals("I", calculator.calculate("III-II"));
    }

    // Операция 3 * 2 должна вернуть 6 с римскими цифрами
    @Test
    public void operation3Multiplication2ShouldReturn6Roman() {
        assertEquals("VI", calculator.calculate("III*II"));
    }

    // Операция 6 / 2 должна вернуть 3 с римскими цифрами
    @Test
    public void operation6Divide2ShouldReturn3Roman() {
        assertEquals("III", calculator.calculate("VI/II"));
    }

    // Операнд не должен быть менее 1
    @Test
    public void operandsShouldNotBeLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("0+1"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("-1+1"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("1+-1"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("1+0"));
    }

    // Операнд не должен быть более 10
    @Test
    public void operandsShouldNotBeMoreThan10() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("11+1"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("1+11"));
    }

    // Только челые числа
    @Test
    public void operandsShouldBeInteger() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("11.12+1"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("1+22.12"));
    }

    // При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций, приложение выбрасывает исключение и завершает свою работу
    @Test
    public void unknownOperation() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("5%2"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("5>>2"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("5>>>2"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("5<<2"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("5==2"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("5>2"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("5<2"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("5^2"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("5&2"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("5&&2"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("5|2"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("5||2"));
    }

    // Результатом работы калькулятора с римскими числами могут быть только положительные числа
    @Test
    public void resultRomanNumbersShouldBePositive() {
        assertThrows(IllegalRomanNumberException.class, () -> calculator.calculate("V-X"));
        assertThrows(IllegalRomanNumberException.class, () -> calculator.calculate("V-V"));
    }


}
