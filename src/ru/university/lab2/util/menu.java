package ru.university.lab2.util;

import ru.university.lab2.numbers.*;
import ru.university.lab2.strings.*;
import ru.university.lab2.arrays.*;

import java.util.Scanner;

public class menu {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            printHelp();
            System.out.print("Укажите номер задания 1-9 (0 - выход): ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Ошибка: введите целое число.");
                scanner.next();
                choice = -1;
            }

            switch (choice) {
                case 0 -> System.out.println("Выход.");
                case 1 -> new IntegerTraps().run();
                case 2 -> new FloatingPoint().run();
                case 3 -> new BitOperations().run();
                case 4 -> new StringLab().run();
                case 5 -> new OneDimensionalArrays().run();
                case 6 -> new MultidimensionalArrays().run();
                case 7 -> new Output().run();
                case 8 -> printHelp();
                case 9 -> System.out.println("Задание выполнено успешно.");
                default -> System.out.println("Нет такого задания.");
            }
        } while (choice != 0);
    }

    private void printHelp() {
        System.out.println("""
                ========== Лабораторная работа №2 ==========
                1 - Целочисленные ловушки
                2 - Вещественная арифметика
                3 - Побитовые операции
                4 - Обработка текста
                5 - Одномерные массивы
                6 - Многомерные массивы
                7 - Методы и передача аргументов
                8 - Консольное меню
                9 - Информация о сборке
                0 - Выход
                ===========================================
                """);
    }
}
