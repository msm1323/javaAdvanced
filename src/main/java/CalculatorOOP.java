import java.util.*;

public class CalculatorOOP extends SimpleCalculator {

    private static int numOfCalculators;

    static {
        numOfCalculators = 0;
    }

    private float scale;

    {
        scale = 1000;
    }

    // ------------------------------------------------

    CalculatorOOP(Float d) {
        setFirst(d);
    }

    CalculatorOOP(Float d, int scale) {
        setFirst(d);
        this.scale = scale;
    }

    CalculatorOOP(String d) {
        setFirst(d);
    }

    CalculatorOOP(String d, int scale) {
        setFirst(d);
        this.scale = scale;
    }

    CalculatorOOP() {
        setFirst();
    }

    CalculatorOOP(int scale) {
        setFirst();
        this.scale = scale;
    }

    // ------------------------------------------------

    private void setFirst() {
        try (Scanner scanner = new Scanner(System.in)) {
            currentValue = r(enterD(scanner));
            numOfCalculators = numOfCalculators + 1;
        } catch (InputMismatchException ex) {
            ex.printStackTrace();
        }
    }

    private void setFirst(Float first) {
        currentValue = r(enterD(first));
        numOfCalculators = numOfCalculators + 1;
    }

    private void setFirst(String first) {
        try {
            currentValue = r(enterD(first));
            numOfCalculators = numOfCalculators + 1;
        } catch (InputMismatchException ex) {
            ex.printStackTrace();
        }
    }

    // ------------------------------------------------

    public static int getNumOfCalculators() {
        return numOfCalculators;
    }

    public Float getCurrentValue() {
        return currentValue;
    }

    private float currentValue = 0;

    public void CA() {
        currentValue = 0;
    }

    private Float r(Float value) {
        return (Math.round(value * scale) / scale);
    }

    // ------------------------------------------------

    public CalculatorOOP calc(OpName opName, Float d) {
        switch (opName) {
            case ADD:
                currentValue = r(addition(currentValue, d));
                return this;
            case SUB:
                currentValue = r(subtraction(currentValue, d));
                return this;
            case DIV:
                try {
                    currentValue = r(division(currentValue, d));
                } catch (CalculatorException e) {
                    e.printStackTrace();
                }
                return this;
            case MUL:
                currentValue = r(multiplication(currentValue, d));
                return this;
        }
        return this;
    }

    // ------------------------------------------------

    public CalculatorOOP calc(OpName opName, String d) {
        try {
            calc(opName, Float.parseFloat(d));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            try {
                LinkedList<Float> list = enterListD(d);
                for (Float el : list) {
                    calc(opName, el);
                }
                return this;
            } catch (CalculatorException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    // ------------------------------------------------
    enum OpName {
        ADD,
        SUB,
        DIV,
        MUL
    }

}