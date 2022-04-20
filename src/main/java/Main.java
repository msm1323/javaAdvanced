import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите номер задания:\n" +
                "(1 - calculator, 2 - string array, 3 - array20, 4 - sweet gift, 5 - OOP Calculator" +
                ", 6 - File analysis)");
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
                CalculatorOOP calculatorOOP = new CalculatorOOP(50f);
                calculatorOOP.calc(CalculatorOOP.OpName.ADD, 8f).calc(CalculatorOOP.OpName.SUB, 2f);
                System.out.println("----next");
                System.out.println(calculatorOOP.getCurrentValue());

                calculatorOOP.calc(CalculatorOOP.OpName.DIV, "2");
                System.out.println("----next");
                System.out.println(calculatorOOP.getCurrentValue());

                calculatorOOP.calc(CalculatorOOP.OpName.ADD, "5f 6.2 1");
                System.out.println("----next");
                System.out.println(calculatorOOP.getCurrentValue());

                calculatorOOP.calc(CalculatorOOP.OpName.MUL, 3f);
                System.out.println("----next");
                System.out.println(calculatorOOP.getCurrentValue());

//                CalculatorOOP calculatorOOP2 = new CalculatorOOP(100);
//                calculatorOOP2.calc(CalculatorOOP.OpName.ADD, 82f);
//                System.out.println("----next");
//                System.out.println(calculatorOOP2.getCurrentValue());

                System.out.println("NumOfCalculators = " + CalculatorOOP.getNumOfCalculators());
                break;
            case 6:
                FileAnalysis fileAnalysis = new FileAnalysis();
                fileAnalysis.analyse();
                break;
            default:
                System.out.println("Нет задания с введенным номером!");
        }

        scanner.close();

    }
}
