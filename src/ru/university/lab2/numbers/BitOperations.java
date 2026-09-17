package ru.university.lab2.numbers;

public final class BitOperations {

    private BitOperations() {
    }

    public static void run() {
        System.out.println("\n===== Побитовые операции =====");

        int x = 5;
        int y = 3;

        System.out.println("x = " + x + ", y = " + y);
        System.out.println("x & y = " + (x & y));
        System.out.println("x | y = " + (x | y));
        System.out.println("x ^ y = " + (x ^ y));
        System.out.println("~x = " + (~x));
        System.out.println("x << 1 = " + (x << 1));
        System.out.println("x >> 1 = " + (x >> 1));

        int negative = -8;

        System.out.println("-8 >> 1 = " + (negative >> 1));
        System.out.println("-8 >>> 1 = " + (negative >>> 1));

        System.out.println("10 — чётное: " + isEven(10));
        System.out.println("11 — чётное: " + isEven(11));

        System.out.println("8 — степень двойки: "
                + isPowerOfTwo(8));
        System.out.println("10 — степень двойки: "
                + isPowerOfTwo(10));

        System.out.println("Единичных битов в 29: "
                + countSetBits(29));
    }

    private static boolean isEven(int number) {
        return (number & 1) == 0;
    }

    private static boolean isPowerOfTwo(int number) {
        return number > 0 && (number & (number - 1)) == 0;
    }

    private static int countSetBits(int number) {
        int count = 0;

        while (number != 0) {
            count += number & 1;
            number >>>= 1;
        }

        return count;
    }
}
