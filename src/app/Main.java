package app;

import exceptions.FigureFormatException;
import exceptions.InvalidFigureParameterException;
import geometry2d.Figure;
import geometry3d.Cylinder;
import storage.BinaryFigureStorage;
import storage.FigureStorage;
import storage.TextFigureStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Path inputPath = resolvePath(
                    args,
                    0,
                    "Введите путь к входному текстовому файлу: "
            );
            Path outputPath = resolvePath(
                    args,
                    1,
                    "Введите путь к выходному бинарному файлу: "
            );

            FigureStorage textStorage = new TextFigureStorage();
            FigureStorage binaryStorage = new BinaryFigureStorage();

            List<Figure> figures = textStorage.read(inputPath);

            System.out.println("Прочитано фигур: " + figures.size());

            for (Figure figure : figures) {
                System.out.printf(
                        "%s | площадь=%.3f | периметр=%.3f%n",
                        figure,
                        figure.area(),
                        figure.perimeter()
                );
            }

            if (figures.isEmpty()) {
                System.out.println("Список пуст, цилиндр не создаётся.");
            } else {
                Figure base = figures.get(new Random().nextInt(figures.size()));
                double height = 5.0;

                Cylinder cylinder = new Cylinder(base, height);

                System.out.printf(
                        "Цилиндр: основание=%s, высота=%.3f, объём=%.3f%n",
                        cylinder.getBase(),
                        cylinder.getHeight(),
                        cylinder.volume()
                );
            }

            binaryStorage.write(outputPath, figures);
            System.out.println(
                    "Список успешно сохранён в бинарный файл: "
                            + outputPath.toAbsolutePath()
            );

        } catch (FigureFormatException e) {
            System.err.println("Ошибка формата: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("Причина: " + e.getCause().getMessage());
            }
        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Не найден класс при десериализации: " + e.getMessage());
        } catch (InvalidFigureParameterException e) {
            System.err.println("Некорректные параметры фигуры: " + e.getMessage());
        }
    }

    private static Path resolvePath(String[] args, int index, String prompt) {
        if (args.length > index) {
            return Path.of(args[index]);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return Path.of(scanner.nextLine());
    }
}