package ru.university.lab2.arrays;

import java.util.Random;

public class MultidimensionalArrays {

    public void run() {
        int[][] matrix = createMatrix(3, 4);

        printMatrix(matrix);

        int[][] transposed = transpose(matrix);
        printMatrix(transposed);

        int[][] a = createMatrix(2, 3);
        int[][] b = createMatrix(3, 2);

        System.out.println("A:");
        printMatrix(a);

        System.out.println("B:");
        printMatrix(b);

        int[][] product = multiply(a, b);
        if (product != null) {
            System.out.println("A * B:");
            printMatrix(product);
        }

        int[][] wrong = multiply(a, a);
        System.out.println("Попытка A * A: "
                + (wrong == null ? "размеры не согласованы" : "выполнено"));
        System.out.println();
    }

    private int[][] createMatrix(int rows, int cols) {
        Random random = new Random();
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(10);
            }
        }

        return matrix;
    }

    private void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%4d", value);
            }
            System.out.println();
        }
        System.out.println();
    }

    private int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        return result;
    }

    private int[][] multiply(int[][] a, int[][] b) {
        if (a[0].length != b.length) {
            System.out.println("Нельзя умножить: число столбцов A ("
                    + a[0].length + ") не равно числу строк B ("
                    + b.length + ")");
            return null;
        }

        int rows = a.length;
        int cols = b[0].length;
        int inner = a[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int sum = 0;
                for (int k = 0; k < inner; k++) {
                    sum += a[i][k] * b[k][j];
                }
                result[i][j] = sum;
            }
        }

        return result;
    }
}
