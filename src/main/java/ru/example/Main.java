package ru.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        Path jsonPath = args.length > 0 ? Path.of(args[0]) : Path.of("books.json");
        List<Visitor> visitors = readVisitors(jsonPath);

        // № 1. Посетители библиотеки
        System.out.println("№ 1. Посетители:");
        visitors.forEach(System.out::println);
        System.out.println("Количество посетителей: " + visitors.stream().count());

        // Собираем все книги посетителей для следующих заданий.
        List<Book> allBooks = visitors.stream()
                .flatMap(visitor -> visitor.getFavoriteBooks().stream())
                .collect(Collectors.toList());

        // № 2. Книги без повторений
        List<Book> uniqueBooks = allBooks.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("\n№ 2. Уникальные книги:");
        uniqueBooks.forEach(System.out::println);
        System.out.println("Количество уникальных книг: " + uniqueBooks.stream().count());

        // № 3. Сортировка книг по году публикации
        List<Book> booksSortedByYear = allBooks.stream()
                .sorted((first, second) ->
                        Integer.compare(first.getPublishingYear(), second.getPublishingYear()))
                .collect(Collectors.toList());

        System.out.println("\n№ 3. Все книги по году издания:");
        booksSortedByYear.forEach(System.out::println);

        // № 4. Поиск книг автора Jane Austen
        boolean hasJaneAustenBook = allBooks.stream()
                .anyMatch(book -> "Jane Austen".equals(book.getAuthor()));

        System.out.println("\n№ 4. Есть книга Jane Austen: " + hasJaneAustenBook);

        // № 5. Максимальное число книг у одного посетителя
        int maxFavoriteBooks = visitors.stream()
                .mapToInt(visitor -> visitor.getFavoriteBooks().size())
                .max()
                .orElse(0);

        System.out.println("\n№ 5. Максимум книг у одного посетителя: " + maxFavoriteBooks);

        // Среднее считается по всем посетителям, независимо от подписки.
        double averageBookCount = visitors.stream()
                .mapToInt(visitor -> visitor.getFavoriteBooks().size())
                .average()
                .orElse(0.0);

        // № 6. Группировка подписчиков по тексту сообщения
        Map<String, List<Visitor>> visitorsByMessage = visitors.stream()
                .filter(Visitor::isSubscribed)
                .collect(Collectors.groupingBy(visitor ->
                        messageFor(visitor.getFavoriteBooks().size(), averageBookCount)));

        List<SmsMessage> smsMessages = visitorsByMessage.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .map(visitor -> new SmsMessage(visitor.getPhone(), entry.getKey())))
                .collect(Collectors.toList());

        System.out.println("\n№ 6. SMS-сообщения подписчикам:");
        smsMessages.forEach(System.out::println);
    }

    private static List<Visitor> readVisitors(Path path) throws IOException {
        Type visitorListType = new TypeToken<List<Visitor>>() {}.getType();
        Gson gson = new Gson();

        try (var reader = Files.newBufferedReader(path)) {
            List<Visitor> visitors = gson.fromJson(reader, visitorListType);
            return visitors == null ? List.of() : visitors;
        }
    }

    private static String messageFor(int bookCount, double averageBookCount) {
        if (bookCount > averageBookCount) {
            return "you are a bookworm";
        }
        if (bookCount < averageBookCount) {
            return "read more";
        }
        return "fine";
    }
}
