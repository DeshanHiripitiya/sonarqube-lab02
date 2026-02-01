package main.java.com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testCalculate() {
        Calculator calc = new Calculator();
        
        assertEquals(15, calc.calculate(10, 5, "add"));
        assertEquals(15, calc.calculate(10, 5, "add-again"));
        assertEquals(5, calc.calculate(10, 5, "sub"));
        assertEquals(5, calc.calculate(10, 5, "sub-again"));
        assertEquals(50, calc.calculate(10, 5, "mul"));
        assertEquals(2, calc.calculate(10, 5, "div"));
        assertEquals(0, calc.calculate(10, 0, "div"));
        assertEquals(0, calc.calculate(10, 5, "mod")); // 10 % 5 = 0
        assertEquals(1, calc.calculate(10, 3, "mod")); // 10 % 3 = 1
        assertEquals(1024, calc.calculate(2, 10, "pow"));
        assertEquals(0, calc.calculate(10, 5, "unknown"));
    }

    @Test
    void testDuplicatedMethods() {
        Calculator calc = new Calculator();
        assertEquals(15, calc.sumValues(10, 5));
        assertEquals(15, calc.addAgain(10, 5));
    }
}
