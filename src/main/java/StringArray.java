import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class StringArray {

    void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите размерность массива:");
        int n = scanner.nextInt();
        String[] arr = new String[n];

        System.out.println("Введите слова для заполнения массива:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.next();
        }
        Arrays.sort(arr, Comparator.comparingInt(String::length).reversed());

        System.out.println("Самое длинное слово в массиве:\n" + arr[0]);

        scanner.close();
    }
}
