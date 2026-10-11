package app;

import exceptions.ShapeException;
import geometry2d.Figure;
import geometry3d.Cylinder;
import storage.BinaryShapeStorage;
import storage.ShapeStorage;
import storage.TextShapeStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path inputPath = Path.of("input.txt");
        Path outputPath = Path.of("output.bin");

        try {
            ShapeStorage textStorage = new TextShapeStorage();
            List<Figure> figures = textStorage.read(inputPath);

            System.out.println("Фигуры из текстового файла:");
            for (Figure figure : figures) {
                System.out.printf(
                        "%s, площадь = %.2f, периметр = %.2f%n",
                        figure,
                        figure.area(),
                        figure.perimeter()
                );
            }

            if (!figures.isEmpty()) {
                Cylinder cylinder = new Cylinder(figures.get(0), 5.0);
                System.out.printf(
                        "Объём цилиндра с первой фигурой в качестве основания: %.2f%n",
                        cylinder.volume()
                );
            }

            ShapeStorage binaryStorage = new BinaryShapeStorage();
            binaryStorage.write(outputPath, figures);

            System.out.println("Фигуры записаны в бинарный файл: " + outputPath);

            List<Figure> restoredFigures = binaryStorage.read(outputPath);
            System.out.println("Количество фигур после чтения бинарного файла: "
                    + restoredFigures.size());

        } catch (IOException | ShapeException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
