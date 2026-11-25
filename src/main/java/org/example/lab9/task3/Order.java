package org.example.lab9.task3;

public record Order(
        int id,
        String shoeType,
        int quantity
) {
    public Order {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "ID должен быть положительным"
            );
        }

        if (shoeType == null || shoeType.isBlank()) {
            throw new IllegalArgumentException(
                    "Тип обуви не указан"
            );
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Количество должно быть положительным"
            );
        }
    }
}