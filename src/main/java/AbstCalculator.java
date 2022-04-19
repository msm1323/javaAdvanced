public abstract class AbstCalculator {

    float addition(float d1, float d2) {
        return d1 + d2;
    }

    float subtraction(float d1, float d2) {
        return d1 - d2;
    }

    float division(float d1, float d2) throws CalculatorException {
        if (d2==0) {
            throw new CalculatorException("На ноль делить нельзя!");
        }
        return d1 / d2;
    }

    float multiplication(float d1, float d2) {
        return d1 * d2;
    }

    static class CalculatorException extends Exception {

        public CalculatorException(String message) {
            super(message);
        }
    }

}
