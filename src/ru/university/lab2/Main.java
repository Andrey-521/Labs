package ru.university.lab2;

import ru.university.lab2.numbers.*;
import ru.university.lab2.arrays.*;
import ru.university.lab2.strings.*;
import ru.university.lab2.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Укажите номер задания 1-9 (0 - выход).");
            return;
        }

        switch (args[0]) {
            case "1" -> new IntegerTraps().run();
            case "2" -> new FloatingPoint().run();
            case "3" -> new BitOperations().run();
            case "4" -> new StringLab().run();
            case "5" -> new OneDimensionalArrays().run();
            case "6" -> new MultidimensionalArrays().run();
            case "7" -> new Output().run();
            case "8" -> new menu();
            case "9" -> System.out.println("Задание выполнено успешно.");
            default -> System.out.println("Нет такого задания.");
        }
    }
}
