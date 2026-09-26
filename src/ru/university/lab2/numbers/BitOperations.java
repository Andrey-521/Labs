package ru.university.lab2.numbers;

public class BitOperations {

    public void run() {
        Operators();
        Shift_Difference();
        Parity();
        Power_of_Two();
        Bit_Count();
        Swap_with_Xor();
    }

    private void Operators() {
        int a = 0b1100; // 12
        int b = 0b1010; // 10

        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a & b   = " + (a & b));
        System.out.println("a | b   = " + (a | b));
        System.out.println("a ^ b   = " + (a ^ b));
        System.out.println("~a      = " + (~a));
        System.out.println("a << 1  = " + (a << 1));
        System.out.println("a >> 1  = " + (a >> 1));
        System.out.println("a >>> 1 = " + (a >>> 1));
    }

    private void Shift_Difference() {
        int negative = -8;

        System.out.println("negative = " + negative + " (binary: " + Integer.toBinaryString(negative) + ")");
        System.out.println("negative >> 1  = " + (negative >> 1)
                + " (binary: " + Integer.toBinaryString(negative >> 1) + ")");
        System.out.println("negative >>> 1 = " + (negative >>> 1)
                + " (binary: " + Integer.toBinaryString(negative >>> 1) + ")");
        // >> сохраняет знак, >>> всегда сдвигает с нулём слева
    }

    private void Parity() {
        int[] numbers = {0, 1, 2, 3, 10, 11, -4, -7};

        for (int n : numbers) {
            System.out.println(n + " чётное? " + isEven(n));
        }
    }

    private boolean isEven(int value) {
        return (value & 1) == 0;
    }

    private void Power_of_Two() {
        int[] numbers = {1, 2, 3, 4, 8, 16, 18, 0, -8};

        for (int n : numbers) {
            System.out.println(n + " степень двойки? " + isPowerOfTwo(n));
        }
    }

    private boolean isPowerOfTwo(int value) {
        return value > 0 && (value & (value - 1)) == 0;
    }

    private void Bit_Count() {
        int[] numbers = {0, 1, 7, 8, 15, 255, -1};

        for (int n : numbers) {
            System.out.println(n + " -> " + countOneBits(n));
        }
    }

    private int countOneBits(int value) {
        int count = 0;
        int current = value;

        while (current != 0) {
            count += current & 1;
            current >>>= 1;
        }
        return count;
    }

    private void Swap_with_Xor() {
        int a = 5;
        int b = 9;

        System.out.println("до: a = " + a + ", b = " + b);

        a ^= b;
        b ^= a;
        a ^= b;

        System.out.println("после: a = " + a + ", b = " + b);
    }
}
