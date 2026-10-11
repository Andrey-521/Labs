package storage;

import exceptions.ShapeException;
import geometry2d.Circle;
import geometry2d.Figure;
import geometry2d.Rectangle;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class TextShapeStorage implements ShapeStorage {

    @Override
    public void write(Path path, List<Figure> figures)
            throws IOException, ShapeException {
        if (path == null || figures == null) {
            throw new IllegalArgumentException("Путь и список фигур не должны быть null");
        }

        try (PrintWriter writer = new PrintWriter(
                Files.newBufferedWriter(path))) {

            for (Figure figure : figures) {
                if (figure instanceof Circle) {
                    Circle circle = (Circle) figure;
                    writer.println("CIRCLE " + circle.getRadius());
                } else if (figure instanceof Rectangle) {
                    Rectangle rectangle = (Rectangle) figure;
                    writer.println(
                            "RECTANGLE "
                                    + rectangle.getWidth()
                                    + " "
                                    + rectangle.getHeight()
                    );
                } else {
                    throw new ShapeException(
                            "Нельзя записать неизвестный тип фигуры: "
                                    + figure.getClass().getName()
                    );
                }
            }

            if (writer.checkError()) {
                throw new IOException("Ошибка при записи файла: " + path);
            }
        }
    }

    @Override
    public List<Figure> read(Path path)
            throws IOException, ShapeException {
        if (path == null) {
            throw new IllegalArgumentException("Путь не должен быть null");
        }

        List<Figure> figures = new ArrayList<>();

        try (Scanner scanner = new Scanner(Files.newBufferedReader(path))) {
            int lineNumber = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                lineNumber++;

                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split("\\s+");

                try {
                    String type = parts[0].toUpperCase(Locale.ROOT);

                    switch (type) {
                        case "CIRCLE":
                            if (parts.length != 2) {
                                throw new ShapeException(
                                        "Для круга нужно указать один параметр"
                                );
                            }

                            figures.add(new Circle(Double.parseDouble(parts[1])));
                            break;

                        case "RECTANGLE":
                            if (parts.length != 3) {
                                throw new ShapeException(
                                        "Для прямоугольника нужно указать два параметра"
                                );
                            }

                            figures.add(new Rectangle(
                                    Double.parseDouble(parts[1]),
                                    Double.parseDouble(parts[2])
                            ));
                            break;

                        default:
                            throw new ShapeException(
                                    "Неизвестный тип фигуры: " + parts[0]
                            );
                    }
                } catch (NumberFormatException e) {
                    throw new ShapeException(
                            "Строка " + lineNumber
                                    + ": параметры должны быть числами"
                    );
                } catch (ShapeException e) {
                    throw new ShapeException(
                            "Строка " + lineNumber + ": " + e.getMessage()
                    );
                }
            }
        }

        return figures;
    }
}
