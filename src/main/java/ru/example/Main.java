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
        // 1. Парсинг JSON с помощью Gson
        String json = Files.readString(Path.of("books.json"));
        Type listType = new TypeToken<List<Visitor>>() {}.getType();
        List<Visitor> visitors = new Gson().fromJson(json, listType);

        // Задание 1. Список посетителей и их количество
        System.out.println("=== Задание 1 ===");
        visitors.forEach(v -> System.out.println(v.getName() + " " + v.getSurname() + " (" + v.getPhone() + ")"));
        System.out.println("Всего посетителей: " + visitors.size());

        // Задание 2. Список и количество всех книг без повторений
        System.out.println("\n=== Задание 2 ===");
        List<Book> uniqueBooks = visitors.stream()
                .flatMap(v -> v.getFavoriteBooks().stream())
                .distinct() // использует equals/hashCode от Lombok
                .collect(Collectors.toList());
        uniqueBooks.forEach(b -> System.out.println(b.getName() + " — " + b.getAuthor()));
        System.out.println("Количество уникальных книг: " + uniqueBooks.size());

        // Задание 3. Отсортировать и вывести все книги по году издания
        System.out.println("\n=== Задание 3 ===");
        List<Book> allBooksSorted = visitors.stream()
                .flatMap(v -> v.getFavoriteBooks().stream())
                .sorted(Comparator.comparingInt(Book::getPublishingYear))
                .collect(Collectors.toList());
        allBooksSorted.forEach(b -> System.out.println(b.getPublishingYear() + " — " + b.getName() + " (" + b.getAuthor() + ")"));

        // Задание 4. Есть ли книга автора "Jane Austen"?
        System.out.println("\n=== Задание 4 ===");
        boolean hasAusten = visitors.stream()
                .flatMap(v -> v.getFavoriteBooks().stream())
                .anyMatch(b -> "Jane Austen".equals(b.getAuthor()));
        System.out.println(hasAusten ? "Да, есть книга Jane Austen" : "Нет, книг Jane Austen не найдено");

        // Задание 5. Максимальное число книг у одного посетителя
        System.out.println("\n=== Задание 5 ===");
        int maxBooks = visitors.stream()
                .mapToInt(v -> v.getFavoriteBooks().size())
                .max()
                .orElse(0);
        System.out.println("Максимальное число избранных книг: " + maxBooks);

        // Задание 6. SMS-рассылка
        System.out.println("\n=== Задание 6 ===");
        double averageBooks = visitors.stream()
                .mapToInt(v -> v.getFavoriteBooks().size())
                .average()
                .orElse(0.0);
        System.out.printf("Среднее число книг у посетителя: %.2f%n", averageBooks);

        List<SmsMessage> smsMessages = visitors.stream()
                .filter(Visitor::isSubscribed)
                .map(v -> {
                    int count = v.getFavoriteBooks().size();
                    String text;
                    if (count > averageBooks) {
                        text = "you are a bookworm";
                    } else if (count < averageBooks) {
                        text = "read more";
                    } else {
                        text = "fine";
                    }
                    return new SmsMessage(v.getPhone(), text);
                })
                .collect(Collectors.toList());

        smsMessages.forEach(sms -> System.out.println(sms.getPhoneNumber() + " -> " + sms.getMessage()));
    }
}
