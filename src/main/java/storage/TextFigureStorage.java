package storage;

import exceptions.FigureFormatException;
import exceptions.InvalidFigureParameterException;
import geometry2d.Circle;
import geometry2d.Figure;
import geometry2d.Rectangle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TextFigureStorage implements FigureStorage {

    @Override
    public List<Figure> read(Path path) throws IOException, FigureFormatException {
        List<Figure> figures = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String trimmed = line.strip();

                if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                    continue;
                }

                String[] parts = trimmed.split(";");

                if (parts.length == 2 && parts[0].trim().equalsIgnoreCase("circle")) {
                    figures.add(parseCircle(parts[1].trim(), lineNumber));
                } else if (parts.length == 3 && parts[0].trim().equalsIgnoreCase("rectangle")) {
                    figures.add(parseRectangle(parts[1].trim(), parts[2].trim(), lineNumber));
                } else {
                    throw new FigureFormatException(
                            lineNumber,
                            "ожидалось circle;радиус или rectangle;сторона1;сторона2"
                    );
                }
            }
        }

        return figures;
    }

    private Circle parseCircle(String value, int lineNumber) throws FigureFormatException {
        try {
            double radius = Double.parseDouble(value);
            return new Circle(radius);
        } catch (NumberFormatException | InvalidFigureParameterException e) {
            throw new FigureFormatException(
                    lineNumber,
                    "некорректный радиус: " + e.getMessage(),
                    e
            );
        }
    }

    private Rectangle parseRectangle(String aValue, String bValue, int lineNumber)
            throws FigureFormatException {
        try {
            double a = Double.parseDouble(aValue);
            double b = Double.parseDouble(bValue);
            return new Rectangle(a, b);
        } catch (NumberFormatException | InvalidFigureParameterException e) {
            throw new FigureFormatException(
                    lineNumber,
                    "некорректные стороны: " + e.getMessage(),
                    e
            );
        }
    }

    @Override
    public void write(Path path, List<Figure> figures) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (Figure figure : figures) {
                if (figure instanceof Circle circle) {
                    writer.write("circle;" + circle.getRadius());
                } else if (figure instanceof Rectangle rectangle) {
                    writer.write("rectangle;" + rectangle.getSideA() + ";" + rectangle.getSideB());
                } else {
                    throw new IOException("Неподдерживаемый тип фигуры: " + figure.getClass().getName());
                }
                writer.newLine();
            }
        }
    }
}