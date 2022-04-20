import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

abstract class SimpleCalculator {

    Float enterD(Scanner scanner) {
        return scanner.nextFloat();
    }

    Float enterD(Float f) {
        return f;
    }

    Float enterD(String f) {
        return Float.parseFloat(f);
    }

    LinkedList<Float> enterListD(Scanner scanner) throws CalculatorException {
        String n = scanner.nextLine();
        return enterListD(n);
    }

    LinkedList<Float> enterListD(String n) throws CalculatorException {
        Pattern pattern = Pattern.compile("(\\d+(\\.\\d*)?f?)(\\s(\\d+(\\.\\d*)?f?))*\\s*");
        if (!pattern.matcher(n).matches()) {
            throw new CalculatorException("Введенное выражение не соответствует нужному формату!");
        }
        return Arrays.stream(n.split(" ")).map(Float::parseFloat)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    Float addition(Float d1, Float d2) {
        return d1 + d2;
    }

    Float subtraction(Float d1, Float d2) {
        return d1 - d2;
    }

    Float division(Float d1, Float d2) throws CalculatorException {
        if (d2 == 0) {
            throw new CalculatorException("На ноль делить нельзя!");
        }
        return d1 / d2;
    }

    Float multiplication(Float d1, Float d2) {
        return d1 * d2;
    }

    static class CalculatorException extends Exception {
        CalculatorException(String message) {
            super(message);
        }
    }

}
