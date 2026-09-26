package ru.university.lab2.arrays;

import java.util.Arrays;
import java.util.Random;

public class OneDimensionalArrays {

    public void run() {
        int[] numbers = Create_Random_Array(10);
        Print_Array(numbers);
        Print_Min_Max_Average(numbers);
        sortAndPrint(numbers);
        Сompare_Arrays();
    }

    private int[] Create_Random_Array(int size) {
        Random random = new Random();
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100) - 50;
        }

        return array;
    }

    private void Print_Array(int[] array) {
        for (int i : array) {
            System.out.print(array[i] + " ");
        }
    }

    private void Print_Min_Max_Average(int[] array) {
        int min = findMin(array);
        int max = findMax(array);
        double average = findAverage(array);

        System.out.println("min = " + min);
        System.out.println("max = " + max);
        System.out.println("average = " + average);
        System.out.println();
    }

    private int findMin(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    private int findMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    private double findAverage(int[] array) {
        long sum = 0;
        for (int value : array) {
            sum += value;
        }
        return (double) sum / array.length;
    }

    private void sortAndPrint(int[] array) {
        int[] copy = Arrays.copyOf(array, array.length);

        System.out.println("До:    " + Arrays.toString(copy));
        bubbleSort(copy);
        System.out.println("После: " + Arrays.toString(copy));
        System.out.println();
    }

    // Выбран алгоритм сортировки пузырьком:
    // многократные проходы, соседние элементы меняются местами, если стоят не по порядку.
    private void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    private void Сompare_Arrays() {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        int[] arr3 = arr1;

        System.out.println("arr1 == arr2:               " + (arr1 == arr2));
        System.out.println("arr1.equals(arr2):          " + arr1.equals(arr2));
        System.out.println("Arrays.equals(arr1, arr2):  " + Arrays.equals(arr1, arr2));
        System.out.println("arr1 == arr3:               " + (arr1 == arr3));
        System.out.println("arr1.equals(arr3):          " + arr1.equals(arr3));
        System.out.println("Arrays.equals(arr1, arr3):  " + Arrays.equals(arr1, arr3));
        System.out.println();

        // == сравнивает ссылки.
        // equals у массива не переопределён и тоже сравнивает ссылки.
        // Arrays.equals сравнивает длины и содержимое поэлементно.
    }
}
