import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws CalculatorOOP.CalculatorException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите номер задания:\n" +
                "(1 - calculator, 2 - string array, 3 - array20, 4 - sweet gift, 5 - OOP Calculator)");
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1:
                Calculator task1 = new Calculator();
                task1.operate();
                break;
            case 2:
                StringArray task2 = new StringArray();
                task2.run();
                break;
            case 3:
                Array20 task3 = new Array20();
                task3.run();
                break;
            case 4:
                SweetGift task4 = new SweetGift();
                task4.run();
                break;
            case 5:
                CalculatorOOP calculatorOOP = new CalculatorOOP();
                System.out.println("Для работы с калькулятором доступны следующие команды:" +
                        "\n\"on\" - включение калькулятора" +
                        "\n\"off\" - выключение калькулятора" +
                        "\n\"q\" - для прекращения работы" +
                        "\n\"CE\" - для \"очищения дисплея\" = отмены последнего посчитанного выражения" +
                        "\n\"CA\" - для очистки буфера" +
                        "\n\"b\" - для вывода значения в буфере" +
                        "\n\nВведенные выражения для подсчета суммируются и сохраняются в буфере." +
                        "\nДля расчетов доступны следующие операции: сложение (+), вычитание (-), деление (/) или умножение (*)." +
                        "\nОкругление в расчетах производится до 3ех знаков после запятой." +
                        "\nДля ввода выражений используйте следующий формат:" +
                        "\nd # d # d # d - и так далее, где d - число с плавающей точкой и # - знак операции, с обязательными пробелами между." +
                        "\nПример: \"1 + 4.6 - 6 * 7.2 + 100 / 4.7\"" +
                        "\n//перед началом работы не забудьте включить калькулятор");
                while (true) {
                    String n = scanner.nextLine();
                    if (n.equals("q")) {
                        calculatorOOP.off();
                        break;
                    }
                    switch (n) {
                        case "on":
                            calculatorOOP.on();
                            break;
                        case "CE":
                            calculatorOOP.CE();
                            break;
                        case "CA":
                            calculatorOOP.CA();
                            break;
                        case "b":
                            System.out.println("Значение в буфере = " + calculatorOOP.getBuffer());
                            break;
                        case "off":
                            calculatorOOP.off();
                            break;
                        default:
                            System.out.println("Результат расчетов последнего введенного выражения = " + calculatorOOP.run(
                                    Arrays.stream(n.split(" ")).collect(Collectors.toList())));

                    }
                }
                break;
            default:
                System.out.println("Нет задания с введенным номером!");
        }

        scanner.close();

    }
}
