package ru.university.lab2.arrays;

public final class MultidimensionalArrays {

    private MultidimensionalArrays() {
    }

    public static void run() {
        System.out.println("\n===== Многомерные массивы =====");

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };

        System.out.println("Исходная матрица:");
        printMatrix(matrix);

        System.out.println("Транспонированная матрица:");
        printMatrix(transpose(matrix));

        int[][] first = {
                {1, 2, 3},
                {4, 5, 6}
        };

        int[][] second = {
                {7, 8},
                {9, 10},
                {11, 12}
        };

        System.out.println("Произведение матриц:");
        printMatrix(multiply(first, second));
    }

    private static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] result = new int[columns][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        return result;
    }

    private static int[][] multiply(int[][] first, int[][] second) {
        if (first[0].length != second.length) {
            throw new IllegalArgumentException(
                    "Матрицы нельзя перемножить"
            );
        }

        int[][] result =
                new int[first.length][second[0].length];

        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < second[0].length; j++) {
                for (int k = 0; k < second.length; k++) {
                    result[i][j] += first[i][k] * second[k][j];
                }
            }
        }

        return result;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%5d", value);
            }

            System.out.println();
        }
    }
}
