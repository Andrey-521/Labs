package storage;

import geometry2d.Figure;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BinaryFigureStorage implements FigureStorage {

    @Override
    public List<Figure> read(Path path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))) {
            Object value = in.readObject();

            if (!(value instanceof List<?> rawList)) {
                throw new IOException(
                        "Ожидался список фигур, получено: "
                                + (value == null ? "null" : value.getClass().getName())
                );
            }

            List<Figure> result = new ArrayList<>();

            for (Object item : rawList) {
                if (!(item instanceof Figure figure)) {
                    throw new IOException("Элемент списка не является Figure: " + item);
                }
                result.add(figure);
            }

            return result;
        }
    }

    @Override
    public void write(Path path, List<Figure> figures) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))) {
            out.writeObject(figures);
        }
    }
}