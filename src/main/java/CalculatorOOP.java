import java.util.*;
import java.util.function.BinaryOperator;

public class CalculatorOOP implements Switchable {

    private static int numOfCalculators;
    private static final ArrayList<String> highPriorityOps;
    private static final float scale;

    static {
        numOfCalculators = 0;
        scale = 1000;

        highPriorityOps = new ArrayList<>();
        highPriorityOps.add("*");
        highPriorityOps.add("/");
    }

    private boolean isActive;

    {
        isActive = false;
    }

    CalculatorOOP() {
        numOfCalculators = numOfCalculators + 1;
    }

    public static int getNumOfCalculators() {
        return numOfCalculators;
    }

    public float getBuffer() throws CalculatorException {
        check();
        return buffer;
    }

    private float buffer = 0;
    private float preValue = 0;

    public void on() {
        isActive = true;
        System.out.println("Калькулятор включен!");
    }

    private void check() throws CalculatorException {
        if (!isActive) {
            throw new CalculatorException("Действие не может быть выполнено, так как калькулятор выключен!");
        }
    }

    public float run(List<String> currList) throws CalculatorException {
        preValue = operate(currList);
        System.out.println("preValue = " + preValue);
        System.out.println("buffer = " + buffer);
        buffer += preValue;
        System.out.println("buffer2 = " + buffer);
        return preValue;
    }

    private float operate(List<String> currList) throws CalculatorException {

        check();
        System.out.println("currList.size() = " + currList.size());

        if (currList.size() == 1) {
            return r(Float.parseFloat(currList.get(0)));
        }

        boolean isNextOpHigh = (currList.size() > 3) && highPriorityOps.contains(currList.get(3));

        boolean isCurrOpHigh = false;
        BinaryOperator<Float> op;

        switch (currList.get(1)) {
            case "+":
                op = Float::sum;
                break;
            case "-":
                op = (d1, d2) -> (d1 - d2);
                break;
            case "/":
                isCurrOpHigh = true;
                op = (d1, d2) -> (d1 / d2);
                break;
            case "*":
                isCurrOpHigh = true;
                op = (d1, d2) -> (d1 * d2);
                break;
            default:
                throw new CalculatorException("\"" + currList.get(1) + "\" не является корректным оператором! ");
        }

        if (isCurrOpHigh) {
            return r(operate(getNextList(currList, op)));
        }
        return r(isNextOpHigh ? operate(getNextList1(currList)) :
                operate(getNextList(currList, op)));
    }

    private List<String> getNextList1(List<String> currList) throws CalculatorException {
        List<String> s1 = currList.subList(0, 2);
        s1.add(String.valueOf(r(operate(currList.subList(2, 5)))));
        List<String> s2 = currList.subList(5, currList.size());
        s1.addAll(s2);
        return s1;
    }

    private List<String> getNextList(List<String> currList, BinaryOperator<Float> op) {
        List<String> s2 = currList.subList(3, currList.size());
        s2.add(0, String.valueOf(r(op.apply(Float.parseFloat(currList.get(0)), Float.parseFloat(currList.get(2))))));
        return s2;
    }

    public void CA() throws CalculatorException {
        check();
        buffer = 0;
        preValue = 0;
    }

    public void CE() throws CalculatorException {
        check();
        buffer -= preValue;
    }

    private static float r(float value) {
        System.out.println("value = " + value);
        float temp = (float) (Math.ceil(value * scale) / scale);
        System.out.println("temp = " + temp);
        return temp;
    }

    public void off() {
        isActive = false;
        buffer = 0;
        System.out.println("Калькулятор выключен.");
    }

    static class CalculatorException extends Exception {

        public CalculatorException(String message) {
            super(message);
        }
    }

}
