package org.example.lab9.task2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class ShoeWarehouse {

    public static final List<String> SHOE_TYPES = List.of(
            "Nike",
            "Adidas",
            "Puma",
            "New Balance",
            "Reebok"
    );

    private final Deque<Order> orders = new ArrayDeque<>();

    private final int capacity;

    public ShoeWarehouse(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Вместимость должна быть положительной"
            );
        }

        this.capacity = capacity;
    }

    public synchronized void receiveOrder(Order order)
            throws InterruptedException {

        while (orders.size() >= capacity) {
            System.out.println(
                    "Склад заполнен. Производитель ожидает."
            );

            wait();
        }

        orders.addLast(order);

        System.out.println(
                "Принят заказ #" + order.id()
                        + " | " + order.shoeType()
                        + " | Количество: " + order.quantity()
        );

        notifyAll();
    }

    public synchronized Order fulfillOrder()
            throws InterruptedException {

        while (orders.isEmpty()) {
            wait();
        }

        Order order = orders.removeFirst();

        System.out.println(
                Thread.currentThread().getName()
                        + " получил заказ #" + order.id()
        );

        notifyAll();

        return order;
    }
}