import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.stream.Stream;

public class FileSystemTask {

    public void run(String surname, String name) throws IOException {
        Path root = Path.of(surname);
        Path originalFile = root.resolve(name);

        // Создаём корневую директорию и файл с именем пользователя.
        Files.createDirectories(root);
        Files.writeString(originalFile, "Файл пользователя: " + name);

        // Создаём dir1/dir2/dir3.
        Path dir1 = root.resolve("dir1");
        Path dir2 = dir1.resolve("dir2");
        Path dir3 = dir2.resolve("dir3");
        Files.createDirectories(dir3);

        // Копируем файл с именем пользователя в dir3.
        Files.copy(
                originalFile,
                dir3.resolve(name),
                StandardCopyOption.REPLACE_EXISTING
        );

        // Создаём дополнительные файлы.
        Files.writeString(dir1.resolve("file1"), "Содержимое file1");
        Files.writeString(dir2.resolve("file2"), "Содержимое file2");

        System.out.println("Содержимое директории до удаления dir1:");
        printTree(root);

        // Удаляем dir1 и всё его содержимое, начиная с самых глубоких путей.
        try (Stream<Path> paths = Files.walk(dir1)) {
            for (Path path : paths
                    .sorted(Comparator.reverseOrder())
                    .toList()) {
                Files.delete(path);
            }
        }

        System.out.println("\nСодержимое после удаления dir1:");
        printTree(root);
    }

    private void printTree(Path root) throws IOException {
        try (Stream<Path> paths = Files.walk(root)) {
            paths.forEach(path -> {
                String type = Files.isDirectory(path) ? "D: " : "F: ";
                System.out.println(type + root.relativize(path));
            });
        }
    }
}
