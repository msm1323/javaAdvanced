import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CalculatorOopTests {

    @Test
    void setFirstFloatTest() {
        CalculatorOOP calculatorOOP = new CalculatorOOP(70f);
        Float expected = 70f;
        Assert.assertEquals("Текущее значение не равно начальному!", expected, calculatorOOP.getCurrentValue());
    }

    @Test
    void setFirstStringTest() {
        CalculatorOOP calculatorOOP = new CalculatorOOP("4");
        Float unexpected = 4f;
        Assert.assertNotEquals("Текущее значение равно не ожидаемому!", unexpected, calculatorOOP.getCurrentValue());
    }

    @Test
    void calcSerialFloatTest() {
        CalculatorOOP calculatorOOP = new CalculatorOOP("5");
        calculatorOOP.calc(CalculatorOOP.OpName.ADD, 5f).calc(CalculatorOOP.OpName.SUB, 3f)
                .calc(CalculatorOOP.OpName.DIV, 2f).calc(CalculatorOOP.OpName.MUL, 4f);
        Float expected = 14f;
        Assert.assertEquals("", expected, calculatorOOP.getCurrentValue());
    }

    @Test
    void calcSerialStringTest() {
        CalculatorOOP calculatorOOP = new CalculatorOOP("5");
        calculatorOOP.calc(CalculatorOOP.OpName.ADD, "5").calc(CalculatorOOP.OpName.SUB, "3f")
                .calc(CalculatorOOP.OpName.DIV, "2f").calc(CalculatorOOP.OpName.MUL, "4");
        Float expected = 14f;
        Assert.assertEquals("", expected, calculatorOOP.getCurrentValue());
    }

    @Test
    void CA_Test() {
        CalculatorOOP calculatorOOP = new CalculatorOOP("10");
        calculatorOOP.calc(CalculatorOOP.OpName.ADD, "5").calc(CalculatorOOP.OpName.SUB, "3f")
                .CA();
        calculatorOOP.calc(CalculatorOOP.OpName.ADD, "5");
        Float expected = 5f;
        Assert.assertEquals("", expected, calculatorOOP.getCurrentValue());
    }

    @Test
    void divisionExceptionTest() {
        CalculatorOOP calculatorOOP = new CalculatorOOP("0");
        Assert.assertThrows(SimpleCalculator.CalculatorException.class, () -> {
            calculatorOOP.division(calculatorOOP.getCurrentValue(), 0f);
        });
    }

/*    todo:
- методы SimpleCalculators доступны лишние, убрать/заменить их cnhernehf vlf
- обнуление после CA() и задача начального значения 0 никак не обрабатывается
etc.
*/

}
