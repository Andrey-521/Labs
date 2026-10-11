package storage;

import exceptions.ShapeException;
import geometry2d.Figure;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BinaryShapeStorage implements ShapeStorage {

    @Override
    public void write(Path path, List<Figure> figures)
            throws IOException {
        if (path == null || figures == null) {
            throw new IllegalArgumentException("Путь и список фигур не должны быть null");
        }

        try (ObjectOutputStream output = new ObjectOutputStream(
                Files.newOutputStream(path))) {
            output.writeObject(new ArrayList<>(figures));
        }
    }

    @Override
    public List<Figure> read(Path path)
            throws IOException, ShapeException {
        if (path == null) {
            throw new IllegalArgumentException("Путь не должен быть null");
        }

        try (ObjectInputStream input = new ObjectInputStream(
                Files.newInputStream(path))) {
            Object object = input.readObject();

            if (!(object instanceof List<?>)) {
                throw new IOException("В файле сохранён некорректный объект");
            }

            List<?> storedObjects = (List<?>) object;
            List<Figure> figures = new ArrayList<>();

            for (Object storedObject : storedObjects) {
                if (!(storedObject instanceof Figure)) {
                    throw new IOException(
                            "В бинарном файле найден объект, который не является фигурой"
                    );
                }

                figures.add((Figure) storedObject);
            }

            return figures;
        } catch (ClassNotFoundException e) {
            throw new IOException("Не удалось прочитать класс из бинарного файла", e);
        }
    }
}
