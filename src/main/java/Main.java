import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите номер задания:\n" +
                "(1 - calculator, 2 - string array, 3 - array20, 4 - sweet gift)");
        switch (scanner.nextInt()) {
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
            default:
                System.out.println("Нет задания с введенным номером!");
        }

        scanner.close();

    }
}
