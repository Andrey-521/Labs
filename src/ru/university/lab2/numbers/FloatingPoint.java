package ru.university.lab2.numbers;

import java.util.Random;

public class FloatingPoint {

    public void run() {
        Point_One_Plus_Point_Two();
        Loop_Sum();
        Epsilon_Compare();
        Special_Values();
        Rounding();
        Float_vs_Double();
    }

    private void Point_One_Plus_Point_Two() {
        double result = 0.1 + 0.2;
        System.out.println("0.1 + 0.2 = " + result);
        //0.1 и 0.2 не представимы точно в двоичной плавающей точке, т.к. в двоичной системе числа 0.1 и 0.2 - бесконечные дроби
    }

    private void Loop_Sum() {
        double sum = 0.0;
        for (int i = 0; i < 10; i++) {
            sum += 0.1;
        }
        System.out.println("sum = " + sum);
        System.out.println("sum == 1.0 ? " + (sum == 1.0));
    }

    private void Epsilon_Compare() {
        double x = 0.1 + 0.2;
        double y = 0.3;
        System.out.println("compareDouble(x, y, 1e-9) = " + compareDouble(x, y, 1e-9));
        System.out.println("compareDouble(1.0, 1.1, 0.05) = " + compareDouble(1.0, 1.1, 0.05));
    }

    private boolean compareDouble(double a, double b, double epsilon) {
        return Math.abs(a - b) <= epsilon;
    }

    private void Special_Values() {
        double positiveInfinity = 1.0 / 0.0;
        double negativeInfinity = -1.0 / 0.0;
        double nan = 0.0 / 0.0;

        System.out.println("1.0 / 0.0 = " + positiveInfinity);
        System.out.println("-1.0 / 0.0 = " + negativeInfinity);
        System.out.println("0.0 / 0.0 = " + nan);
        System.out.println("NaN == NaN ? " + (nan == nan));
        // NaN не равен даже самому себе
    }

    private void Rounding() {
        double[] values = {2.7, -2.7};

        for (double value : values) {
            System.out.println("value = " + value);
            System.out.println("  (int) = " + (int) value);
            System.out.println("  Math.round = " + Math.round(value));
            System.out.println("  Math.floor = " + Math.floor(value));
            System.out.println("  Math.ceil = " + Math.ceil(value));
        }
    }

    private void Float_vs_Double() {
        float floatSum = 0.0f;
        double doubleSum = 0.0;

        for (int i = 0; i < 10; i++) {
            floatSum += 0.1f;
            doubleSum += 0.1;
        }

        System.out.println("float sum  = " + floatSum);
        System.out.println("double sum = " + doubleSum);
        // float хранит около 7 значащих цифр, double — около 15-16
    }
}
