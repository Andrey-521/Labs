package ru.university.lab2.util;

public class Output {

    public void run() {
        Overload();
        Varargs();
        Power();
    }

    private void Overload() {
        print(42);
        print(3.14);
        print("Java");
        print(new int[]{1, 2, 3});
        System.out.println();
    }

    private void print(int value) {
        System.out.println("int: " + value);
    }

    private void print(double value) {
        System.out.println("double: " + value);
    }

    private void print(String value) {
        System.out.println("String: " + value);
    }

    private void print(int[] value) {
        System.out.print("int[]: ");
        for (int v : value) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    private void Varargs() {
        System.out.println("sum() = " + sum());
        System.out.println("sum(1, 2, 3) = " + sum(1, 2, 3));

        int[] array = {4, 5, 6};
        System.out.println("sum(array) = " + sum(array));
        System.out.println();
    }

    private int sum(int... numbers) {
        int total = 0;
        for (int number : numbers) {
            total += number;
        }
        return total;
    }

    private void Power() {
        int base = 2;

        for (int exp = 0; exp <= 10; exp++) {
            int rec = powerRecursive(base, exp);
            int iter = powerIterative(base, exp);
            double math = Math.pow(base, exp);

            System.out.printf("%d^%d: rec=%d, iter=%d, Math.pow=%.0f%n",
                    base, exp, rec, iter, math);
        }
        System.out.println();

        // Итеративная реализация обычно быстрее: она не создаёт кадры стека на каждый шаг.
        // Рекурсия добавляет накладные расходы на вызовы и может привести к переполнению стека.
    }

    private int powerRecursive(int base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        return base * powerRecursive(base, exponent - 1);
    }

    private int powerIterative(int base, int exponent) {
        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
}
