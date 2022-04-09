import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class CalculatorOOP implements Switchable {

    private static int numOfCalculators;
    private static final ArrayList<String> highPriorityOps;
    private static float scale;

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

    public static void setScale(float newScale) {
        scale = newScale;
    }

    public static int getNumOfCalculators() {
        return numOfCalculators;
    }

    private float getBuffer() {
        return buffer;
    }

    private float buffer = 0;
    private float preValue = 0;

    private void check() throws CalculatorException {
        if (!isActive) {
            throw new CalculatorException("Действие не может быть выполнено, так как калькулятор выключен!");
        }
    }

    public void run() throws CalculatorException {
        check();
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
                    System.out.println("Результат расчета последнего введенного выражения = " + run(
                            Arrays.stream(n.split(" ")).collect(Collectors.toCollection(LinkedList::new))));
            }
        }
    }

    private float run(LinkedList<String> currList) throws CalculatorException {
        preValue = operate(currList);
        buffer += preValue;
        return preValue;
    }

    private float operate(LinkedList<String> currList) throws CalculatorException {
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
        return r(isNextOpHigh ? operate(getNextList(currList)) :
                operate(getNextList(currList, op)));
    }

    private LinkedList<String> getNextList(LinkedList<String> currList) throws CalculatorException {
        LinkedList<String> s1 = new LinkedList<>(currList.subList(0, 2));
        String a = String.valueOf(r(operate(new LinkedList<>(currList.subList(2, 5)))));
        s1.add(a);
        if (currList.size() > 5) {
            s1.addAll(new LinkedList<>(currList.subList(5, currList.size())));
        }
        return s1;
    }

    private LinkedList<String> getNextList(LinkedList<String> currList, BinaryOperator<Float> op) {
        String first = String.valueOf(r(op.apply(Float.parseFloat(currList.get(0)), Float.parseFloat(currList.get(2)))));
        LinkedList<String> s2 = currList.size() > 3 ? new LinkedList<>(currList.subList(3, currList.size())) : new LinkedList<>();
        s2.add(0, first);
        return s2;
    }

    private void CA() {
        buffer = 0;
        preValue = 0;
    }

    private void CE() {
        buffer = r(buffer - preValue);
    }

    private static float r(float value) {
        return (Math.round(value * scale) / scale);
    }

    public void on() {
        isActive = true;
        System.out.println("Калькулятор включен!");
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
