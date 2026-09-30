package ru.university.lab2.arrays;

import java.util.Arrays;
import java.util.Random;

public final class OneDimensionalArrays {

    private OneDimensionalArrays() {
    }

    public static void run() {
        System.out.println("\n===== Одномерные массивы =====");

        int[] array = new int[10];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }

        System.out.println("До сортировки: "
                + Arrays.toString(array));

        System.out.println("Минимум: " + findMinimum(array));
        System.out.println("Максимум: " + findMaximum(array));
        System.out.println("Среднее: " + findAverage(array));

        bubbleSort(array);

        System.out.println("После сортировки: "
                + Arrays.toString(array));

        int[] first = {1, 2, 3};
        int[] second = {1, 2, 3};

        System.out.println("first == second: "
                + (first == second));
        System.out.println("first.equals(second): "
                + first.equals(second));
        System.out.println("Arrays.equals(first, second): "
                + Arrays.equals(first, second));
    }

    private static int findMinimum(int[] array) {
        int minimum = array[0];

        for (int value : array) {
            if (value < minimum) {
                minimum = value;
            }
        }

        return minimum;
    }

    private static int findMaximum(int[] array) {
        int maximum = array[0];

        for (int value : array) {
            if (value > maximum) {
                maximum = value;
            }
        }

        return maximum;
    }

    private static double findAverage(int[] array) {
        int sum = 0;

        for (int value : array) {
            sum += value;
        }

        return (double) sum / array.length;
    }

    private static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temporary = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temporary;
                }
            }
        }
    }
}
