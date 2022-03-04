import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите название операции - сложение, вычитание, деление или умножение:");
        String opName = scanner.next();

        System.out.println("Введите первое дробное число:");
        float d1 = scanner.nextFloat();
        System.out.println("Введите второе дробное число:");
        float d2 = scanner.nextFloat();

        switch (opName) {
            case "сложение":
                System.out.printf("Результат: %.4f", calculator.addition(d1, d2));
                break;
            case "вычитание":
                System.out.printf("Результат: %.4f", calculator.subtraction(d1, d2));
                break;
            case "деление":
                System.out.printf("Результат: %.4f", calculator.division(d1, d2));
                break;
            case "умножение":
                System.out.printf("Результат: %.4f", calculator.multiplication(d1, d2));
                break;
            default:
                System.out.printf("В калькуляторе нет операции \"%s\"!", opName);
        }

    }
}
