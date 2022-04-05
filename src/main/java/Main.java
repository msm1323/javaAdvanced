import java.util.Scanner;

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
                calculatorOOP.on();
                calculatorOOP.run();
                calculatorOOP.off();
                break;
            default:
                System.out.println("Нет задания с введенным номером!");
        }

        scanner.close();

    }
}
