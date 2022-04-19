import java.util.*;
import java.util.function.BinaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CalculatorOOP {

    private static int numOfCalculators;
    private static final ArrayList<String> highPriorityOps;

    static {
        numOfCalculators = 0;
        highPriorityOps = new ArrayList<>();
        highPriorityOps.add("*");
        highPriorityOps.add("/");
    }

//    private boolean isActive;
    private float scale;

    {
//        isActive = false;
        scale = 1000;
    }



    CalculatorOOP() {
        numOfCalculators = numOfCalculators + 1;
    }

    CalculatorOOP(CalcType calcType, float scale) {

        this.scale = scale;
        numOfCalculators = numOfCalculators + 1;

    }

    public static int getNumOfCalculators() {
        return numOfCalculators;
    }

    public float getBuffer() {
        return buffer;
    }

    public float getCurrentValue() {
        return currentValue;
    }

    private float buffer = 0;
    private float currentValue = 0;

    public void CA() {
        buffer = 0;
        currentValue = 0;
    }

    public void CE() {
        buffer = r(buffer - currentValue);
    }

    private float r(float value) {
        return (Math.round(value * scale) / scale);
    }

    private enum CalcType{
        SERIAL,
        UI
    }

    protected class CalculatorSerial extends AbstCalculator{

        public AbstCalculator setFirst(float first) {
            currentValue = first;
            return this;
        }

        private AbstCalculator addition(float d) {
            currentValue = addition(currentValue, d);
            return this;
        }

        private AbstCalculator subtraction(float d) {
            currentValue = subtraction(currentValue, d);
            return this;
        }

        private AbstCalculator division(float d) throws CalculatorException {
            currentValue = division(currentValue, d);
            return this;
        }

        private AbstCalculator multiplication(float d) {
            currentValue = multiplication(currentValue, d);
            return this;
        }
    }

    protected class CalculatorUI {

        public void runUI() {
//        check();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Для работы с калькулятором доступны следующие команды:" +
                    "\n\"q\" - для прекращения работы" +
                    "\n\"CE\" - для \"очищения дисплея\" = отмены последнего посчитанного выражения" +
                    "\n\"CA\" - для очистки буфера" +
                    "\n\"b\" - для вывода значения в буфере" +
                    "\n\nВведенные выражения для подсчета суммируются и сохраняются в буфере." +
                    "\nДля расчетов доступны следующие операции: сложение (+), вычитание (-), деление (/) или умножение (*)." +
                    "\nОкругление в расчетах производится до 3ех знаков после запятой." +
                    "\nДля ввода выражений используйте следующий формат:" +
                    "\nd # d # d # d - и так далее, где d - число с плавающей точкой и # - знак операции, с обязательными пробелами между." +
                    "\nПример: \"1 + 4.6 - 6 * 7.2 + 100 / 4.7\"");
            while (true) {
                String n = scanner.nextLine();
                if (n.equals("q")) {
                    break;
                }
                switch (n) {
                    case "CE":
                        CE();
                        break;
                    case "CA":
                        CA();
                        break;
                    case "b":
                        System.out.println("Значение в буфере = " + getBuffer());
                        break;
                    default:
//                    System.out.println("Результат расчета последнего введенного выражения = " + Calculator.run(n));
                }
            }
        }

        public float run(String n) {
            LinkedList<String> currList = Arrays.stream(n.split(" ")).collect(Collectors.toCollection(LinkedList::new));
            Pattern pattern = Pattern.compile("(\\d+(\\.\\d*)?)(\\s[+-/*]\\s(\\d+(\\.\\d*)?))+");
            if (!pattern.matcher(n).matches()) {
//            throw new CalculatorException("Введенное выражение не соответствует нужному формату!");
            }
            currentValue = operate(currList);
            buffer += currentValue;
            return currentValue;
        }

        private float operate(LinkedList<String> currList) {
            if (currList.size() == 1) {
                return r(Float.parseFloat(currList.get(0)));
            }

            boolean isNextOpHigh = (currList.size() > 3) && highPriorityOps.contains(currList.get(3));

            boolean isCurrOpHigh = false;
            BinaryOperator<Float> op = null;

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
//                throw new CalculatorException("\"" + currList.get(1) + "\" не является корректным оператором! ");
            }

            if (isCurrOpHigh) {
                return r(operate(getNextList(currList, op)));
            }
            return r(isNextOpHigh ? operate(getNextList(currList)) :
                    operate(getNextList(currList, op)));
        }

        private LinkedList<String> getNextList(LinkedList<String> currList) {
            LinkedList<String> s1 = new LinkedList<>(currList.subList(0, 2));
            String a = String.valueOf(r(operate(new LinkedList<>(currList.subList(2, 5)))));
            s1.add(a);
            s1.addAll(new LinkedList<>(currList.subList(5, currList.size())));
            return s1;
        }

        private LinkedList<String> getNextList(LinkedList<String> currList, BinaryOperator<Float> op) {
            String first = String.valueOf(r(op.apply(Float.parseFloat(currList.get(0)), Float.parseFloat(currList.get(2)))));
            LinkedList<String> s2 = currList.size() > 3 ? new LinkedList<>(currList.subList(3, currList.size())) : new LinkedList<>();
            s2.add(0, first);
            return s2;
        }

    }

//    private void check() {
//        if (!isActive) {
////            throw new CalculatorException("Действие не может быть выполнено, так как калькулятор выключен!");
//        }
//    }

//    public void on() {
//        isActive = true;
////        System.out.println("Калькулятор включен!");
//    }

//    public void off() {
//        isActive = false;
//        buffer = 0;
////        System.out.println("Калькулятор выключен.");
//    }

//    static class CalculatorException extends Exception {
//
//        public CalculatorException(String message) {
//            super(message);
//        }
//    }

//    float addition(Float[] args) {
//        return Calculator.opp(Calculator.addition, args);
//    }
//
//    float subtraction(Float[] args) {
//        return Calculator.opp(Calculator.subtraction, args);
//    }
//
//    float division(Float[] args) {
//        return Calculator.opp(Calculator.division, args);
//    }
//
//    float multiplication(Float[] args) {
//        return Calculator.opp(Calculator.multiplication, args);
//    }
//
//    private static class Calculator {
//
//        static BinaryOperator<Float> addition = Float::sum;
//        static BinaryOperator<Float> subtraction = (d1, d2) -> (d1 - d2);
//        static BinaryOperator<Float> division = (d1, d2) -> (d1 / d2);
//        static BinaryOperator<Float> multiplication = (d1, d2) -> (d1 * d2);
//
//        static private float opp(BinaryOperator<Float> op, Float[] args) {
//            float res = args[0];
//            for (int i = 1; i < args.length; i++) {
//                res = op.apply(res, args[i]);
//            }
//            return res;
//        }
//
//    }

}
