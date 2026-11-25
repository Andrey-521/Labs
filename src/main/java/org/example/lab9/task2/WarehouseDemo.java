package org.example.lab9.task2;

public class WarehouseDemo {

    private static final int ORDER_COUNT = 20;

    private static final int ORDERS_PER_CONSUMER = 5;

    private static final int CONSUMER_COUNT =
            ORDER_COUNT / ORDERS_PER_CONSUMER;

    public static void main(String[] args)
            throws InterruptedException {

        ShoeWarehouse warehouse = new ShoeWarehouse(5);

        Thread producer = new Thread(
                new Producer(warehouse, ORDER_COUNT),
                "Producer"
        );

        Thread[] consumers = new Thread[CONSUMER_COUNT];

        for (int i = 0; i < CONSUMER_COUNT; i++) {

            consumers[i] = new Thread(
                    new Consumer(
                            warehouse,
                            ORDERS_PER_CONSUMER
                    ),
                    "Consumer-" + (i + 1)
            );

            consumers[i].start();
        }

        producer.start();

        producer.join();

        for (Thread consumer : consumers) {
            consumer.join();
        }

        System.out.println(
                "Все 20 заказов успешно обработаны."
        );
    }
}