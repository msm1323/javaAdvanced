import java.util.Scanner;

/**
 * @author msm1323  2022
 */

class Calculator {

    void operate() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Введите название операции - сложение, вычитание, деление или умножение:");
        //наименование операции для калькулятора
        String opName = scanner.next();

        System.out.println("Введите первое дробное число:");
        //первое дробное число
        float d1 = scanner.nextFloat();

        //второе дробное число
        System.out.println("Введите второе дробное число:");
        float d2 = scanner.nextFloat();

        switch (opName) {
            case "сложение":
                System.out.printf("Результат: %.4f", addition(d1, d2));
                break;
            case "вычитание":
                System.out.printf("Результат: %.4f", subtraction(d1, d2));
                break;
            case "деление":
                System.out.printf("Результат: %.4f", division(d1, d2));
                break;
            case "умножение":
                System.out.printf("Результат: %.4f", multiplication(d1, d2));
                break;
            default:
                System.out.printf("В калькуляторе нет операции \"%s\"!", opName);
        }
        scanner.close();
    }

    /**
     * @param d1 первое слогаемое
     * @param d2 второе слогаемое
     * @return результат сложения двух дробных чисел
     */
    private float addition(float d1, float d2) {
        return d1 + d2;
    }

    /**
     * @param d1 уменьшаемое
     * @param d2 вычитаемое
     * @return результат вычитания двух дробных чисел
     */
    private float subtraction(float d1, float d2) {
        return d1 - d2;
    }

    /**
     * @param d1 делимое
     * @param d2 делитель
     * @return результат деления двух дробных чисел
     */
    private float division(float d1, float d2) {
        return d1 / d2;
    }

    /**
     * @param d1 первый множитель
     * @param d2 второй множитель
     * @return произведение двух дробных чисел
     */
    private float multiplication(float d1, float d2) {
        return d1 * d2;
    }

}
