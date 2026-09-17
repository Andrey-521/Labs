package ru.university.lab2.numbers;

public final class FloatingPoint {

    private FloatingPoint() {
    }

    public static void run() {
        System.out.println("\n===== Вещественная арифметика =====");

        double sum = 0.1 + 0.2;

        System.out.println("0.1 + 0.2 = " + sum);
        System.out.println("Точное сравнение с 0.3: "
                + (sum == 0.3));
        System.out.println("Сравнение через epsilon: "
                + equalsWithEpsilon(sum, 0.3, 1e-9));

        double repeatedSum = 0.0;

        for (int i = 0; i < 10; i++) {
            repeatedSum += 0.1;
        }

        System.out.println("0.1, сложенное 10 раз: "
                + repeatedSum);

        double infinity = 1.0 / 0.0;
        double negativeInfinity = -1.0 / 0.0;
        double nan = 0.0 / 0.0;

        System.out.println("Infinity: " + infinity);
        System.out.println("-Infinity: " + negativeInfinity);
        System.out.println("NaN: " + nan);
        System.out.println("NaN == NaN: " + (nan == nan));
        System.out.println("Double.isNaN(NaN): "
                + Double.isNaN(nan));

        System.out.println("(int) 2.7: " + (int) 2.7);
        System.out.println("Math.round(2.7): " + Math.round(2.7));
        System.out.println("Math.floor(2.7): " + Math.floor(2.7));
        System.out.println("Math.ceil(2.7): " + Math.ceil(2.7));

        System.out.println("(int) -2.7: " + (int) -2.7);
        System.out.println("Math.round(-2.7): " + Math.round(-2.7));
        System.out.println("Math.floor(-2.7): " + Math.floor(-2.7));
        System.out.println("Math.ceil(-2.7): " + Math.ceil(-2.7));

        System.out.println("1 / 3 в float: " + (1.0f / 3.0f));
        System.out.println("1 / 3 в double: " + (1.0 / 3.0));
    }

    private static boolean equalsWithEpsilon(
            double first,
            double second,
            double epsilon) {

        return Math.abs(first - second) <= epsilon;
    }
}
