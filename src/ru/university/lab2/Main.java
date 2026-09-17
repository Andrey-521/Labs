package ru.university.lab2;

import ru.university.lab2.arrays.MultidimensionalArrays;
import ru.university.lab2.arrays.OneDimensionalArrays;
import ru.university.lab2.numbers.BitOperations;
import ru.university.lab2.numbers.FloatingPoint;
import ru.university.lab2.numbers.IntegerTraps;
import ru.university.lab2.strings.StringLab;

public class Main {

    public static void main(String[] args) {
        IntegerTraps.run();
        FloatingPoint.run();
        BitOperations.run();
        StringLab.run();
        OneDimensionalArrays.run();
        MultidimensionalArrays.run();
    }
}
