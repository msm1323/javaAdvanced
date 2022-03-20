import java.util.Arrays;
import java.util.Random;

public class Array20 {

    void run() {
        Random r = new Random();
        int[] arr = new int[20];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(21) - 11;
        }
        System.out.println("Сформированный массив: " + Arrays.toString(arr));

        int[] arrSorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arrSorted);

        int maxNegEl = 11;
        int minPosEl = -11;
        for (int el : arrSorted) {

            if (el < 0) {
                maxNegEl = el;
            }

            if (minPosEl == -11 && el > 0) {
                minPosEl = el;
            }
        }
        System.out.println("Максимальное отрицательное число: = " + maxNegEl);
        System.out.println("Минимальное положительное число: = " + minPosEl);

        int n = 0, p = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == maxNegEl) {
                n = i;
            }
            if (arr[i] == minPosEl) {
                p = i;
            }
        }

        if (maxNegEl == 11) {
            System.out.println("В массиве нет отрицательных чисел!");
        }
        if (minPosEl == -11) {
            System.out.println("В массиве нет положительных чисел!");
        }

        if (maxNegEl != 11 && minPosEl != -11){
            int temp = arr[n];
            arr[n] = arr[p];
            arr[p] = temp;

            System.out.println("Массив после перестановки максимального отрицательного и минимального положительного элемента местами: " + Arrays.toString(arr));

        }

    }

}
