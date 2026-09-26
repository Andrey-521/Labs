package ru.university.lab2.numbers;

public final class IntegerTraps {

    private void Min_Max_Values() {
        //Тип данных Byte занимает 1 байт или 8 бит, поэтому в него умещается 2^8 чисел
        System.out.println("Min byte " + Byte.MIN_VALUE);
        System.out.println("Max byte " + Byte.MAX_VALUE);

        //Тип данных Short занимает 2 байта или 16 бит, поэтому в него умещается 2^16 чисел
        System.out.println("Min short " + Short.MIN_VALUE);
        System.out.println("Max short " + Short.MAX_VALUE);

        //Тип данных Short занимает 4 байта или 32 бит, поэтому в него умещается 2^32 чисел
        System.out.println("Min int " + Integer.MIN_VALUE);
        System.out.println("Max int " + Integer.MAX_VALUE);

        //Тип данных Long занимает 8 байта или 64 бит, поэтому в него умещается 2^64 чисел
        System.out.println("Min long " + Long.MIN_VALUE);
        System.out.println("Max long " + Long.MAX_VALUE);
    }

    private void Integer_Max_Value_Plus_One() {
        //После переполнения происходит переход от максимума к минимуму
        int max = Integer.MAX_VALUE;
        int result = max + 1;
        System.out.println("Integer.MAX_VALUE + 1 = " + result);
    }

    private void Different_between_Int_Long() {
        int result_one = Integer.MAX_VALUE * 2; //Происходит переполнение, а значит отсчет идет покругу
        long result_two = (long) Integer.MAX_VALUE * 2;

        System.out.println("Integer.MAX_VALUE * 2 in int: " + result_one);
        System.out.println("Integer.MAX_VALUE * 2 in long: " + result_two);
    }

    private void Sign_of_the_Remainder_of_Division() {
        System.out.println("5 / 2 = " + (5 / 2));
        System.out.println("-5 / 2 = " + (- 5 / 2));
        System.out.println("5 % 2 = " + (5 % 2));
        System.out.println("-5 % 2 = " + (5 % 2));
    }

    private int Long_to_Int() {
        long number = Integer.MAX_VALUE * 5;
        int result = (int) number;
        return result;
    }

    private void Working_with_Char() {
        char letter = 'a';
        char next_letter = (char) (letter + 1);
        System.out.println("Прибавим к a 1: " + next_letter);

        int sum_letters = 'A' + 'B';
        System.out.println("Сумма A и B как число: " + sum_letters);
        System.out.println("Сумма A и B как строка: " + (char) sum_letters);
    }

    private boolean is_Overflow(int a, int b) {
        int sum = a + b;
        if (sum >= Integer.MAX_VALUE) {
            System.out.println("Переполнение");
            return true;
        }
        else {
            System.out.println("Нет переполнения");
            return false;
        }
    }

    private void Check_to_MaxInt() {
        int num1 = Integer.MAX_VALUE;
        int num2 = 900;
        is_Overflow(num1, num2);
        int num3 = 900;
        int num4 = 25000;
        is_Overflow(num3, num4);
    }

    public void run() {
        Min_Max_Values();
        Integer_Max_Value_Plus_One();
        Different_between_Int_Long();
        Sign_of_the_Remainder_of_Division();
        Working_with_Char();
        Check_to_MaxInt();
    }
}
