package ru.university.lab2.numbers;

public final class IntegerTraps {

    private IntegerTraps() {
    }

    public static void run() {
        System.out.println("\n===== Целочисленные ловушки =====");

        System.out.println("byte:  "
                + Byte.MIN_VALUE + " ... " + Byte.MAX_VALUE);
        System.out.println("short: "
                + Short.MIN_VALUE + " ... " + Short.MAX_VALUE);
        System.out.println("int:   "
                + Integer.MIN_VALUE + " ... " + Integer.MAX_VALUE);
        System.out.println("long:  "
                + Long.MIN_VALUE + " ... " + Long.MAX_VALUE);

        int max = Integer.MAX_VALUE;

        System.out.println("Integer.MAX_VALUE + 1 = " + (max + 1));
        System.out.println("Integer.MAX_VALUE * 2 = " + (max * 2));
        System.out.println("(long) Integer.MAX_VALUE * 2 = "
                + ((long) max * 2));

        System.out.println("5 / 2 = " + (5 / 2));
        System.out.println("-5 / 2 = " + (-5 / 2));
        System.out.println("5 % 2 = " + (5 % 2));
        System.out.println("-5 % 2 = " + (-5 % 2));

        long largeValue = (long) Integer.MAX_VALUE + 100;
        int converted = (int) largeValue;

        System.out.println("long-значение: " + largeValue);
        System.out.println("После приведения к int: " + converted);

        char letter = 'A';
        System.out.println("Следующая буква после A: "
                + (char) (letter + 1));

        System.out.println("'0' + '1' как число: " + ('0' + '1'));
        System.out.println("'0' + '1' как символ: "
                + (char) ('0' + '1'));

        AdditionResult result =
                addWithOverflowCheck(Integer.MAX_VALUE, 1);

        System.out.println("Проверка переполнения:");
        System.out.println("Результат: " + result.value);
        System.out.println("Переполнение: " + result.overflow);
    }

    private static AdditionResult addWithOverflowCheck(int a, int b) {
        int result = a + b;

        boolean overflow =
                (b > 0 && result < a)
                        || (b < 0 && result > a);

        return new AdditionResult(result, overflow);
    }

    private static final class AdditionResult {
        private final int value;
        private final boolean overflow;

        private AdditionResult(int value, boolean overflow) {
            this.value = value;
            this.overflow = overflow;
        }
    }
}
