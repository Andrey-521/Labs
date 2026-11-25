package org.example.lab9.task2;

public class Producer implements Runnable {

    private final ShoeWarehouse warehouse;

    private final int orderCount;

    public Producer(
            ShoeWarehouse warehouse,
            int orderCount
    ) {
        this.warehouse = warehouse;
        this.orderCount = orderCount;
    }

    @Override
    public void run() {

        try {
            for (int i = 1; i <= orderCount; i++) {

                String shoeType =
                        ShoeWarehouse.SHOE_TYPES.get(
                                (i - 1)
                                        % ShoeWarehouse.SHOE_TYPES.size()
                        );

                Order order = new Order(
                        i,
                        shoeType,
                        i % 4 + 1
                );

                warehouse.receiveOrder(order);
            }

            System.out.println(
                    "Производитель закончил создание заказов."
            );

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            System.err.println(
                    "Производитель был прерван."
            );
        }
    }
}